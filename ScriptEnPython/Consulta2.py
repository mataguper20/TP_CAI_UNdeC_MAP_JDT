from acctransito_utiles import titulo, cargarArchivo
from time import time


titulo("Inicio Ingesta")
df = cargarArchivo()
titulo("Fin Ingesta")

df['util'] = 0
titulo('Ciudades mas Peligrosas')
inicio = time()
df_agrupado= df[['City','util']].groupby(['City'], as_index=False).count().sort_values(by='util', ascending=False)
print(df_agrupado)
fin = time()
duracion1= fin-inicio
titulo("City Calculado en:")
print(duracion1)

titulo('Weather_Condition mas Peligrosas')
inicio = time()
df_agrupado= df[['Weather_Condition','util']].groupby(['Weather_Condition'], as_index=False).count().sort_values(by='util', ascending=False)
print(df_agrupado)
fin = time()
duracion2= fin-inicio
titulo("Weather_Condition Calculado en:")
print(duracion2)

titulo('State mas Peligrosas')
inicio = time()
df_agrupado= df[['State','util']].groupby(['State'], as_index=False).count().sort_values(by='util', ascending=False)
print(df_agrupado)
fin = time()
duracion3= fin-inicio
titulo("State Calculado en:")
print(duracion3)

titulo('Tiempo Promedio')
print((duracion1+duracion2+duracion3)/3.0)