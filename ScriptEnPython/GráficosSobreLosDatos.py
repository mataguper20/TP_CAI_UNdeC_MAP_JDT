import os
import pandas as pd
import matplotlib.pyplot as plt 
import datetime as dt
os.chdir('E:/CAI/')
datos = pd.read_csv('US_Accidents_Dec19.csv')
df = pd.DataFrame(datos)



dfTiempo = pd.DataFrame()
dfTiempo['Start_Time']= pd.to_datetime(datos['Start_Time'])
dfTiempo['hour']= dfTiempo['Start_Time'].dt.hour
dfTiempo['year']= dfTiempo['Start_Time'].dt.year
dfTiempo['month']= dfTiempo['Start_Time'].dt.month
dfTiempo['week']= dfTiempo['Start_Time'].dt.week

dfTiempo.head()



plt.figure(figsize =(15,10))
dfTiempo.groupby(['year']).size().plot.bar(color='green')
#plt.title("Accidentes agrupados por año")
plt.ylabel('Cantidad de Accidentes')
plt.xlabel('Año')
plt.grid()


plt.figure(figsize =(15,10))
dfTiempo.groupby(['month']).size().plot.bar(color='green')
#plt.title("Accidentes agrupados por meses")
plt.ylabel('Cantidad de Accidentes')
plt.xlabel('Mes')
plt.grid()


plt.figure(figsize =(15,10))
dfTiempo.groupby(['year','month']).size().plot.bar(color='green')
#plt.title("Accidentes agrupados por año y mes")
plt.ylabel('Cantidad de Accidentes')
plt.xlabel('Año - Mes')
plt.grid()


plt.figure(figsize =(15,10))
dfTiempo.groupby(['hour']).size().plot.bar(color='green')
#plt.title("Accidentes agrupados por horas")
plt.ylabel('Cantidad de Accidentes')
plt.xlabel('Horas')
plt.grid()

plt.figure(figsize =(15,10))
dfTiempo.groupby(['week']).size().plot.bar(color='green')
#plt.title("Accidentes agrupados por semana")
plt.ylabel('Cantidad de Accidentes')
plt.xlabel('Semanas')
plt.grid()


plt.figure(figsize =(10,5))
dfTiempo['day_zone'] = pd.cut((dfTiempo['hour']),bins=(0,6,12,18,24), labels=["Madrugada", "Mañana", "Tarde", "Noche"])
dfTiempo.groupby(['day_zone']).size().plot.bar(color='green')
#plt.title("Accidentes agrupados por momento del día")
plt.ylabel('Cantidad de Accidentes')
plt.xlabel('Momento del día')
plt.grid()



plt.figure(figsize=(14,8))
graf=df[df.Severity<5]
salida=graf.plot(kind='scatter', x='Start_Lng',y='Start_Lat',label='Severity',c='Severity',cmap=plt.get_cmap('jet'),colorbar=True,alpha=0.4,figsize=(20,15))
salida.legend()
plt.ioff()

