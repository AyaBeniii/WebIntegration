from zeep import Client

wsdl = "http://localhost:9000/calculatrice?wsdl"
client = Client(wsdl=wsdl)

print("Addition :", client.service.add(10, 5))
print("Multiplication :", client.service.multiply(4, 3))
print("Soustraction :", client.service.subtract(9, 2))
print("Division :", client.service.divide(20, 4))