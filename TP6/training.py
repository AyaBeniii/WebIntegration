from sklearn.tree import DecisionTreeClassifier
import pickle
# Exemple de données d'entraînement (prix, ventes, vues, label)
data = [
{"price": 50, "in_stock": 1, "sales": 200, "views": 1000, "label": "High"},
{"price": 1200, "in_stock": 1, "sales": 20, "views": 200, "label": "Low"},
{"price": 300, "in_stock": 1, "sales": 80, "views": 500, "label": "Medium"},
{"price": 20, "in_stock": 1, "sales": 300, "views": 1500, "label": "High"}
]
# Préparer les données pour l'entraînement
X = [[d["price"], d["sales"], d["views"]] for d in data]
y = [d["label"] for d in data]
# Entraîner un modèle de classification simple
model = DecisionTreeClassifier()
model.fit(X, y)
# Sauvegarder le modèle
with open("model.pkl", "wb") as f:
    pickle.dump(model, f)