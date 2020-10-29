#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Oct 27 22:15:51 2020

@author: jdt
"""


import os
import pandas as pd
from math import radians, cos, sin, asin, sqrt, acos

radio=5
def titulo(cadena):
    print('')
    #print('*'*40)
    print(str(cadena))
    # print('*'*40)

def cargarArchivo():
    #os.chdir('../csvaccidentes//')
    train = pd.read_csv('US_Accidents_Dec19.csv')
    return train

def filtro2(lon1, lat1):
    lon1, lat1, lon2, lat2 = map(radians, [lon1, lat1, -81.652626, 41.12336])
   
    dist = acos(sin(lat1)*sin(lat2)+cos(lat1)*cos(lat2)*cos(lon1-lon2))
    return (dist * 6371) <= radio

def filtro(lon1, lat1):
    lon1, lat1, lon2, lat2 = map(radians, [lon1, lat1, -120.96671, 40.11178])
    dlon = lon2 - lon1 
    dlat = lat2 - lat1 
    a = sin(dlat/2)**2 + cos(lat1) * cos(lat2) * sin(dlon/2)**2
    c = 2 * asin(sqrt(a)) 
    return (c*6371)<=radio