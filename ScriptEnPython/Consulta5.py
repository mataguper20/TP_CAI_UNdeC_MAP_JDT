from time import time
from acctransito_utiles import titulo, cargarArchivo

df = cargarArchivo()


inicio = time()
media=df['Distance(mi)'].mean()
fin = time()
duracion= fin-inicio
titulo("Media")
print(media)
titulo("Calculado en:")
print(duracion)