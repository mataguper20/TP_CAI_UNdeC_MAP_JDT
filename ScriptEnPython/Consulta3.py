import numpy as np # linear algebra
import pandas as pd # data processing, CSV file I/O (e.g. pd.read_csv)
import matplotlib.pyplot as plt
import seaborn as sns

from time import time
from acctransito_utiles import titulo, cargarArchivo, filtro, filtro2


titulo("Inicio Ingesta")
df = cargarArchivo()
titulo("Fin Ingesta")

titulo("Eliminando posibles faltantes")
df.dropna(subset=['Start_Lat', 'Start_Lng'])

print("Longitud",len(df))

titulo("Inicio Procesamiento")
inicio = time()
Lista=[]
for h in df.index:
    if filtro2(df['Start_Lng'][h], df['Start_Lat'][h]):
        Lista.append([df['Start_Lat'][h],df['Start_Lng'][h]])            
    
fin = time()

duracion= fin-inicio
titulo("Calculado en:")
print(duracion)
titulo("Puntos Hallados")
print(len(Lista))
print(Lista)



states = df.State.unique()
print(states)

count_by_state=[]
for i in df.State.unique():
    count_by_state.append(df[df['State']==i].count()['ID'])
    


plt.figure(figsize=(25,15))
plt.title("States")
sns.barplot(states, count_by_state)

df = cargarArchivo()
print("Hola")
plt.figure(figsize=(14,8))
graf=df[df.Severity<5]
salida=graf.plot(kind='scatter', x='Start_Lng',y='Start_Lat',label='Severity',c='Severity',cmap=plt.get_cmap('jet'),colorbar=True,alpha=0.4,figsize=(10,10))
salida.legend()
plt.ioff()
print("Adios")

