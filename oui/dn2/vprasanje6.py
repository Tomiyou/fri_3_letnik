from sklearn.neighbors import KNeighborsRegressor
from sklearn.model_selection import cross_val_score
from sklearn.metrics import mean_squared_error, make_scorer
import pandas

data = pandas.read_csv('podatki/reg/88.csv')
trainingSet = data.head(39)
testingSet = data.iloc[39:]

X_train = trainingSet.iloc[:,:-1]
y_train = trainingSet[["Y"]]
X_test = testingSet.iloc[:,:-1]
y_test = testingSet[["Y"]]

cv_scores = []
neighbors = list(range(1, 20))
for i in neighbors:
  knn = KNeighborsRegressor(n_neighbors = i)
  scores = cross_val_score(knn, X_train, y_train, scoring=make_scorer(mean_squared_error), cv=10)
  cv_scores.append((scores.mean(), i))

cv_scores.sort()
for score in cv_scores:
  print(score)

opt_k = cv_scores[0][1]
print(opt_k)
reg = KNeighborsRegressor(n_neighbors = opt_k).fit(X_train,y_train)
Y_pred = reg.predict(X_test)

print(mean_squared_error(y_test,Y_pred))