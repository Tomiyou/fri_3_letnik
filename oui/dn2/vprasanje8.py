from sklearn.linear_model import LinearRegression
from sklearn.cluster import AgglomerativeClustering
import pandas

df = pandas.read_csv('podatki/clu/79.csv')

cluster = AgglomerativeClustering(n_clusters=2, affinity='manhattan', linkage='complete')
cluster.fit_predict(df)
labels = cluster.labels_
print(sum(labels))
print(len(labels) - sum(labels))