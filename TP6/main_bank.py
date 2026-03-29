from fastapi import FastAPI
from pydantic import BaseModel
import pickle
import os

app = FastAPI(title="Bank Credit Risk API")

class Client(BaseModel):
    revolving_utilization: float
    age: int
    late_30_59: int
    debt_ratio: float
    monthly_income: float
    open_credit_lines: int
    late_90: int
    real_estate_loans: int
    late_60_89: int
    dependents: float
    default_history: int = 0  # 0 = pas de défaut, 1 = défaut

db_clients = []

model = None
if os.path.exists("bank_model.pkl"):
    with open("bank_model.pkl", "rb") as f:
        model = pickle.load(f)


# CRUD

@app.post("/clients/")
def create_client(client: Client):
    db_clients.append(client)
    return {"message": "Client ajouté", "client": client}

@app.get("/clients/")
def list_clients():
    return {"clients": db_clients, "count": len(db_clients)}

@app.get("/clients/{client_id}")
def get_client(client_id: int):
    if client_id < 0 or client_id >= len(db_clients):
        return {"message": "Client introuvable"}
    return db_clients[client_id]

@app.put("/clients/{client_id}")
def update_client(client_id: int, client: Client):
    if client_id < 0 or client_id >= len(db_clients):
        return {"message": "Client introuvable"}
    db_clients[client_id] = client
    return {"message": "Client mis à jour", "client": client}

@app.delete("/clients/{client_id}")
def delete_client(client_id: int):
    if client_id < 0 or client_id >= len(db_clients):
        return {"message": "Client introuvable"}
    deleted = db_clients.pop(client_id)
    return {"message": "Client supprimé", "client": deleted}


# KPI

@app.get("/kpi/total_clients")
def total_clients():
    return {"total_clients": len(db_clients)}

@app.get("/kpi/average_income")
def average_income():
    if len(db_clients) == 0:
        return {"average_income": 0}
    avg = sum(c.monthly_income for c in db_clients) / len(db_clients)
    return {"average_income": avg}

@app.get("/kpi/default_rate")
def default_rate():
    if len(db_clients) == 0:
        return {"default_rate": 0}
    defaults = sum(1 for c in db_clients if c.default_history == 1)
    rate = (defaults / len(db_clients)) * 100
    return {"default_rate": rate}

@app.get("/kpi/risky_clients")
def risky_clients():
    if model is None:
        return {"message": "Modèle non entraîné"}

    count = 0
    for c in db_clients:
        features = [[
            c.revolving_utilization,
            c.age,
            c.late_30_59,
            c.debt_ratio,
            c.monthly_income,
            c.open_credit_lines,
            c.late_90,
            c.real_estate_loans,
            c.late_60_89,
            c.dependents
        ]]
        pred = model.predict(features)[0]
        if pred == 1:
            count += 1

    return {"risky_clients": count}


# Prediction

@app.post("/predict")
def predict_client(client: Client):
    if model is None:
        return {"message": "Modèle non entraîné"}

    features = [[
        client.revolving_utilization,
        client.age,
        client.late_30_59,
        client.debt_ratio,
        client.monthly_income,
        client.open_credit_lines,
        client.late_90,
        client.real_estate_loans,
        client.late_60_89,
        client.dependents
    ]]

    pred = model.predict(features)[0]

    if pred == 0:
        decision = "Accepté"
    else:
        if client.late_90 >= 3 or client.debt_ratio > 1:
            decision = "Refusé"
        else:
            decision = "Risque"

    return {
        "prediction": int(pred),
        "decision": decision
    }