from sklearn.linear_model import LinearRegression
import pandas

df = pandas.read_csv('podatki/reg/80.csv')
X = df.iloc[:,:-1]
y = df[["Y"]]

reg = LinearRegression().fit(X, y)
print(reg.coef_)
# print(reg.score(X, y))