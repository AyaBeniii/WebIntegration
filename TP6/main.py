from fastapi import FastAPI
from pydantic import BaseModel
import pickle

app = FastAPI(title="E-commerce API")

class Item(BaseModel):
    name: str
    price: float
    in_stock: bool = True
    sales: int
    views: int

db = []

#  Route POST avec données JSON
@app.post("/items/")
def create_item(item: Item):
    db.append(item)
    return {"message": "Item créé", "item": item}

# Route GET avec paramètre
@app.get("/items/{item_id}")
def read_item(item_id: int):
    return {
        "name": db[item_id].name if item_id < len(db) else "Unknown",
        "price": db[item_id].price if item_id < len(db) else 0,
        "in_stock": db[item_id].in_stock if item_id < len(db) else 0,
        "sales": db[item_id].sales if item_id < len(db) else 0,
        "views": db[item_id].views if item_id < len(db) else 0
        }

# Route GET pour lister tous les items
@app.get("/items/")
def list_items():
    return {"items": db, "count": len(db)}

@app.get("/kpi/total_products")
def total_products():
    return {"total": len(db)}

@app.get("/kpi/total_value")
def total_value():
    total = sum(item.price for item in db)
    return {"total_value": total}

@app.get("/kpi/in_stock")
def in_stock_products():
    count = sum(1 for item in db if item.in_stock)
    return {"in_stock": count}

# charger le modèle entraîné 
with open("model.pkl", "rb") as f:
    model = pickle.load(f)

# Route pour faire une prédiction
@app.post("/predict")
def predict(item: Item):
    features = [[item.price, item.sales, item.views]]
    prediction = model.predict(features)[0]
    return {"prediction": prediction }