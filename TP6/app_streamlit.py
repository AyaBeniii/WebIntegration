import streamlit as st
import requests
import pandas as pd

API_URL = "http://127.0.0.1:8000"

st.title("Banque à distance - Dashboard & Prédiction")

menu = st.sidebar.selectbox("Choisir une page", ["Dashboard", "Prédiction"])

if menu == "Dashboard":
    st.header("Tableau de bord")

    total_clients = requests.get(f"{API_URL}/kpi/total_clients").json()
    avg_income = requests.get(f"{API_URL}/kpi/average_income").json()
    default_rate = requests.get(f"{API_URL}/kpi/default_rate").json()
    risky_clients = requests.get(f"{API_URL}/kpi/risky_clients").json()
    clients = requests.get(f"{API_URL}/clients/").json()

    st.metric("Nombre total de clients", total_clients.get("total_clients", 0))
    st.metric("Revenu moyen", round(avg_income.get("average_income", 0), 2))
    st.metric("Taux de défaut (%)", round(default_rate.get("default_rate", 0), 2))
    st.metric("Clients à risque", risky_clients.get("risky_clients", 0))

    if "clients" in clients:
        data = [c for c in clients["clients"]]
        if len(data) > 0:
            df = pd.DataFrame(data)
            st.subheader("Liste des clients")
            st.dataframe(df)

elif menu == "Prédiction":
    st.header("Prédiction du risque")

    revolving_utilization = st.number_input("Revolving Utilization", value=0.5)
    age = st.number_input("Age", value=30)
    late_30_59 = st.number_input("Retards 30-59 jours", value=0)
    debt_ratio = st.number_input("Debt Ratio", value=0.4)
    monthly_income = st.number_input("Revenu mensuel", value=5000.0)
    open_credit_lines = st.number_input("Nombre de lignes de crédit", value=5)
    late_90 = st.number_input("Retards 90 jours", value=0)
    real_estate_loans = st.number_input("Prêts immobiliers", value=1)
    late_60_89 = st.number_input("Retards 60-89 jours", value=0)
    dependents = st.number_input("Nombre de dépendants", value=0.0)

    if st.button("Prédire"):
        payload = {
            "revolving_utilization": revolving_utilization,
            "age": age,
            "late_30_59": late_30_59,
            "debt_ratio": debt_ratio,
            "monthly_income": monthly_income,
            "open_credit_lines": open_credit_lines,
            "late_90": late_90,
            "real_estate_loans": real_estate_loans,
            "late_60_89": late_60_89,
            "dependents": dependents,
            "default_history": 0
        }

        response = requests.post(f"{API_URL}/predict", json=payload).json()
        st.subheader("Résultat")
        st.write(response)