from sklearn.neighbors import KNeighborsRegressor
from sklearn.model_selection import cross_val_score
from sklearn.metrics import mean_squared_error, make_scorer
import pandas

file = pandas.read_csv('podatki/reg/129.csv')
ucna = file.head(30)
testna = file.iloc[30:]
#ucna = file.copy()
#print(ucna)

ucna_Y = ucna.iloc[:,-1].values
ucna_X = ucna.iloc[:,:-1].values
testna_Y = testna.iloc[:,-1].values
testna_X = testna.iloc[:,:-1].values
print(ucna_Y)

opt_k = 20
min = 100000

for i in range(1, 27):
    
    scores = cross_val_score(KNeighborsRegressor(n_neighbors = i),ucna_X,ucna_Y, \
        scoring=make_scorer(mean_squared_error), cv=10)
    zdajsnji = scores.mean()
    if (zdajsnji < min) :
        opt_k = i
        min = zdajsnji

    print("{:.3f}\n".format(scores.mean()))
    prejsnji = scores.mean()

reg = KNeighborsRegressor(n_neighbors = opt_k).fit(ucna_X,ucna_Y)
Y_pred = reg.predict(testna_X)

print(opt_k)
print(mean_squared_error(testna_Y,Y_pred))