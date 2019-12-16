from sklearn.linear_model import LinearRegression
import pandas
import math
import numpy as np

def calculateDistance(x1,y1,x2,y2):
  return math.sqrt((x2 - x1)**2 + (y2 - y1)**2)

df = pandas.read_csv('podatki/clu/125.csv')

centroidA = [-48, -18]
centroidB = [-43, -36]

for i in range(100):
  tockeA = []
  tockeB = []

  for i, tocka in df.iterrows():
    # print("X:", row[0], "Y:", row[1])
    distA = calculateDistance(tocka[0], tocka[1], centroidA[0], centroidA[1])
    distB = calculateDistance(tocka[0], tocka[1], centroidB[0], centroidB[1])
    if distB < distA:
      tockeB.append(tocka)
    else:
      tockeA.append(tocka)

  centroidA = np.mean(tockeA, axis = 0)
  centroidB = np.mean(tockeB, axis = 0)

  print(len(tockeA), len(tockeB))