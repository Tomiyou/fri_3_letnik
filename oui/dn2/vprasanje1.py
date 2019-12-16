import pandas

df = pandas.read_csv('podatki/reg/87.csv')
print(df[["X7", "Y"]].corr())