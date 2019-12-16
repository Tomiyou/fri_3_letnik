from sklearn.linear_model import LinearRegression
from sklearn.model_selection import train_test_split
from sklearn.metrics import mean_squared_error
import pandas

data = pandas.read_csv('podatki/reg/165.csv')
trainingSet = data.head(31)
testingSet = data.iloc[31:]
# X = df[["X6"]]
# trainingSetX = 
# trainingSetY = trainingSet[["Y"]]

X_train = trainingSet.iloc[:,:-1]
y_train = trainingSet[["Y"]]
X_test = testingSet.iloc[:,:-1]
y_test = testingSet[["Y"]]

# X_train, X_test, y_train, y_test = train_test_split(X, y, train_size=33, random_state=0)
# print(X_train)
# print(y_train)
# print(X_test)
# print(y_test)

reg = LinearRegression().fit(X_train, y_train)
# y_pred = reg.predict(X_train)
y_pred = reg.predict(X_test)

print("MSE:", mean_squared_error(y_test, y_pred))
# print("MSE:", mean_squared_error(y_train, y_pred))
# print(reg.score(X, y))