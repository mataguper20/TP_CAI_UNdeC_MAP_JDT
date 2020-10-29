from acctransito_utiles import titulo, cargarArchivo
from time import time


titulo("Inicio Ingesta")
df = cargarArchivo()
titulo("Fin Ingesta")


titulo('Accidentes entre fechas')
inicio = time()
filtro = (df['Start_Time'] > '2016-02-08 08:46:00') & (df['Start_Time'] <= '2018-10-10 08:46:00')
df_ef=df.loc[filtro]
print(df_ef)
fin = time()
duracion= fin-inicio
titulo("Calculado en:")
print(duracion)
