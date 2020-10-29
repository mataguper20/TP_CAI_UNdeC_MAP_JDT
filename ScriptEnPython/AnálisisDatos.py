from acctransito_utiles import titulo, cargarArchivo
import matplotlib.pyplot as plt
import pandas as pd

df=cargarArchivo()

titulo('Analisis')
titulo('Atributos del Archivo US_Accidents_Dec19.csv')
campos = df.columns.values
print(campos)

titulo('Info de US_Accidents_Dec19.csv')
print(df.info())

titulo('Resumen Atributos Numéricos')
print(df.describe())

titulo('Resumen Atributos Nominales')
print(df.describe(include=['O']))

campos_numericos=[key for key in dict(df.dtypes) if dict(df.dtypes)[key] in ['float64', 'int64']]
campos_nominales=[key for key in dict(df.dtypes) if dict(df.dtypes)[key] not in ['float64', 'int64']]

for i in campos_numericos:
    titulo('Nombre:'+i)
    plt.figure()
    df.boxplot(column=[i])
    print('Cantidad: '+str(df[i].count()))
    print('Media  : '+str(df[i].mean()))
    print('Mínimo   : '+str(df[i].min()))
    print('Máximo   : '+str(df[i].max()))

for i in campos_nominales:
    titulo('Nombre: '+i)
    print('Cantidad: '+str(df[i].count()))
    l=pd.unique(df[i])
    if len(l)<=5:
        print('Categorías:'+str(l))
    
    
    
    




