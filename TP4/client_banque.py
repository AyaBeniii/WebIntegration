from zeep import Client

wsdl = "http://localhost:9001/banque?wsdl"
client = Client(wsdl=wsdl)

print("Solde initial :", client.service.consulterSolde(1))
print("Apres depot :", client.service.deposer(1, 200))
print("Apres retrait :", client.service.retirer(1, 100))

compte = client.service.getCompte(1)
print("Compte id :", compte.id)
print("Compte solde :", compte.solde)