# Descrbtion of project.java



## Global Variable Section
long[] item = new long[13668104]:

    The array stores type long, and it stores the item ID from the rating4.txt file  
    13668104 is number of rows in the text file.
  
long[] user = new long[13668104]:

    The array stores type long, and it stores the userID from the rating4.txt file  
    13668104 is number of rows in the text file.
  
int[] rating = new int[13668104]:

    The array stores type integer, and it stores the rating from the rating4.txt file  
    13668104 is number of rows in the text file.
  
int[] status = new int[13668104]:

    The array stores type integer, and it stores the status from the rating4.txt file  
    13668104 is number of rows in the text file.
  
long[] type = new long[13668104]:

    The array stores type long, and it stores the type of item from the rating4.txt file  
    13668104 is number of rows in the text file.
  
long[] reviewer = new long[13668104]:

    The array stores type long, and it stores the reviewer ID from the rating4.txt file  
    13668104 is number of rows in the text file.
  
Map<Long, Integer> index = new <Long, Integer> HashMap():

    The dictionary stores the index number of the user ID in the user[] array.
   


long[] item1 = new long[13668104]:

    The array stores type long, and it stores the item ID from the category2.txt file  
    13668104 is number of rows in the text file.
  
long[] user1 = new long[13668104]:

    The array stores type long, and it stores the user ID from the category2.txt file  
    13668104 is number of rows in the text file.
  
long[] type1= new long[13668104]:

    The array stores type long, and it stores the type of item from the category2.txt file  
    13668104 is number of rows in the text file.
  
long[] reviewer1 = new long[13668104]:

    The array stores type long, and it stores the reviewer ID from the category2.txt file  
    13668104 is number of rows in the text file.
  
Map<Long, Integer> index1 = new <Long, Integer> HashMap():

    The dictionary stores the index number of the reviewer ID in the reviewer1[] array.



long[] user3 = new long[120486]:

    The array stores type long, and it stores the the all the user ID.







## Main Function Section
Varible name "filename" contains the address of rating4.txt. The while loop read every element form the file.
    
    The item id from first column of the file will be stored in array item[]
    The user id from second column of the file will be stored in array user[]
    The rating from thrid column of the file will be stored in array rating[]
    The display status of the rating from foruth column will be stroed in array status[]
    The type of item will from fifth column will be stroed in array type[]
    The reviewer id form the sixth column will be stored in array reviewer[]
    The index will store the index of the user id in array user[]
  
Varible name "filename1" contains the address of category2.txt. The while loop read every element form the file.
    
    The item id from first column of the file will be stored in array item1[]
    The user id from second column of the file will be stored in array user1[]
    The type of item from third column will be stroed in array type1[]
    The reviewer id from the foruth column will be stored in array reviewer1[]
    The index1 will store the index of the reviwer id in array reviewer1[]
    
Varible name "filename2" contains the address of list.txt. The while loop read every element form the file.
    
    The user list for intermediate set is stored in array user3[]
    
  
## Feathres Section
  
Set<Long> Iu(long u):
    
    This function takes long u and it returns the set of items rated by u.
    //u is the user ID.
 
Set<Long> Iur(long u, int r):
  
    This function takes long u and intger r, and it returns the set of items rated r (where r 2 f1; 2; 3; 4; 5g) by u.
    // u is the user ID and r is the rating value.
   
Set<Long> Cu(long u):
  
    This function takes long u, and it returns the set or multiset (depending on the feature) of
    categories of the items in Iu.
    // u is the user ID.
  
Set<Long> Iuc(long u, long c):
    
    This function takes long u and long c, and it returns the set of items in category c rated by u.
    // u is the user ID and c is the category of the item.

Set<Long> Du(long u):
  
    This function takes long u, and it returns the set or multiset (depending on the feature) of categories of the reviews (items)           produced by u.
    // u is the user ID.
  
  
## Help function section

int rui(long u, long i):
    
    This function takes long u and long i, and it returns the integer rating value  of this item i rated by u.
    // u is the user ID and i is the item ID.
  
double ru_avg(long u):
    
    This function takes long u, and it returns the average ratings by u.
    // u is the user ID.
  
Set<Long> Iu_up(long u):
    
    This function takes long u, and it returns the union of I(u,4) with I(u,5).
    // u is the user ID.
 
Set<Long> Iu_down(long u):
    
    This function takes long u, and it returns the union of I(u,1) with I(u,2).
    // u is the user ID.
 
double ruc(long u, long c):
    
    This function takes long u and long c, it returns the double value after calculation.
    // u is the user ID and c is the category of the item.
 
double ru_hat(long u):
    
    This function takes long u, it returns the double value after calulation.
    // u is the user ID.




## Parameter Calculation Section
double f1a(long u, long v):
    
    This function takes long u and long v, and it returns the double value of parameter f1a after calculation.
    // u is the user ID and v is the another user ID.
  
double f1b(long u, long v):
    
    This function takes long u and long v, and it returns the double value of parameter f1b after calculation.
    // u is the user ID and v is the another user ID.
 
double f1c(long u, long v):

    This function takes long u and long v, and it returns the double value of parameter f1c after calculation.
    // u is the user ID and v is the another user ID.
  
double f1d(long u, long v):
    
    This function takes long u and long v, and it returns the double value of parameter f1d after calculation.
    // u is the user ID and v is the another user ID.
  
double f1e(long u, long v):
  
    This function takes long u and long v, and it returns the double value of parameter f1e after calculation.
    // u is the user ID and v is the another user ID.    
  
double f2a(long u, long v):
    
    This function takes long u and long v, and it returns the double value of parameter f2a after calculation.
    // u is the user ID and v is the another user ID.
  
double f2b(long u, long v):
    
    This function takes long u and long v, and it returns the double value of parameter f2b after calculation.
    // u is the user ID and v is the another user ID.
  
double f2c(long u, long v):
    
    This function takes long u and long v, and it returns the double value of parameter f2c after calculation.
    // u is the user ID and v is the another user ID.
  
double f3(long u, long v):
    
    This function takes long u and long v, and it returns the double value of parameter f3 after calculation.
    // u is the user ID and v is the another user ID.
  
  
 ## output
### f1a
 User u | User v | f1a | Trust statement 
  --- | --- | --- | --- 
1199781|	236296|	1.000000000	|1
199781|	308739|	1.000000000	|1
199781|	238184|	0.730296743	|1
199781|	210284|	0.777613345	|-1
199781|	1640730500 |0.885270413	|1
199781|	371087 |0.894427191	|1
.......|.......|.......|.......

### f1b
 User u | User v | f1b | Trust statement 
  --- | --- | --- | --- 
199781|	210284|	0.001655972|-1
199781|	223652|	0.011494253|1
199781|	243427|	0.003496503	|1
199781|	292382|	0.028571429	|1
199784|	213850|	0.200000000|1
199828|	577933188|	0.025641026|1
.......|.......|.......|.......


### f1c
 User u | User v | f1c | Trust statement 
  --- | --- | --- | --- 
199781|	210284|	0.001655972|	-1
199781|	223652|	0.011494253|	1
199781|	243427|	0.003496503|	1
199781|	292382|	0.028571429|	1
199784|	213850|	0.200000000|	1
199828|	577933188|	0.025641026|	1
.......|.......|.......|.......



### f1d
 User u | User v | f1d | Trust statement 
  --- | --- | --- | --- 
199781|	210284|	0.000191168|	-1
199781|	223652|	0.002083333	|1
199781|	243427|	0.001035197|	1
199781|	684774|	0.001647446	|-1
199828|	256232|	0.001319261|	1
199828|	214801|	0.001218027|	1
.......|.......|.......|.......




### f1e
 User u | User v | f1e | Trust statement 
  --- | --- | --- | --- 
199781|	238184|	0.000079246|	1
199781|	210284|	0.000042279	|-1
199781|	205491|	0.000069080	|1
199781|	368762|	0.000063099	|1
199781|	241578|	0.000056398	|1
199831|	20098289540|	0.000048319|	1
.......|.......|.......|.......



### f2a
 User u | User v | f2a | Trust statement 
  --- | --- | --- | --- 
199781|	236296|	0.043269231|	1
199781|	308739|	0.033415842	|1
199781|	205535|	0.008350731	|1
199781|	238184|	0.023682293	|1
199781|	434399|	0.004175365	|-1
199781|	2453704580|	0.002159827|	-1
.......|.......|.......|.......

### f2b
 User u | User v | f2b | Trust statement 
  --- | --- | --- | --- 
199781|	236296|	0.593826030|	1
199781|	308739|	0.660070456|	1
199781|	205535|	0.114707867|	1
199781|	238184|	0.649136868|	1
199781|	434399|	1.000000000|	-1
199781|	719295|	0.580230964|	1
.......|.......|.......|.......

### f2c
 User u | User v | f2c | Trust statement 
  --- | --- | --- | --- 
199781|	308739|	0.197260310|	1
199781|	238184|	0.080212478	|1
199781|	2320404356|	0.897905968|	1
199781|	210284|	0.407746844|	-1
199781|	243232|	0.202448644|	-1
199781|	1640730500|	0.130130620|	1
.......|.......|.......|.......

### f3
 User u | User v | f3 | Trust statement 
  --- | --- | --- | --- 
199781|	236296|	0.008196721|	1
199781|	308739|	0.027027027|	1
199781|	238184|	0.005154639|	1
199781|	2453704580|	0.025641026|	-1
199781|	719295|	0.023255814|	1
199781|	210284|	0.017241379|	-1
.......|.......|.......|.......

### f4
 User u | User v | f4 | Trust statement 
  --- | --- | --- | --- 
199781|	236296|	0.278046765|	1
199781|	308739|	0.718136020	|1
199781|	238184|	0.416143580	|1
199781|	719295|	0.141522551	|1
199781|	210284|	0.632878593	|-1
199781|	243232|	0.319541383	|-1
.......|.......|.......|.......

### f5a
 User u | User v | f5a | Trust statement 
  --- | --- | --- | --- 
199781|	308739|	4.855870183|	1
199781|	205535|	4.862934211	|1
199781|	238184|	5.002500642	|1
199781|	2453704580|	0.026540221|	-1
199781|	2320404356|	4.844219779	|1
199781|	719295|	4.854903376|	1
.......|.......|.......|.......

### f5b
 User u | User v | f5b | Trust statement 
  --- | --- | --- | --- 
199781|	308739|	0.002415459|	1
199781|	205535|	0.002415459	|1
199781|	238184|	0.004830918	|1
199781|	2320404356|	0.002415459|	1
199781|	719295|	0.004830918|	1
199781|	1640730500|	0.004830918|	1
.......|.......|.......|.......


### f5c
 User u | User v | f5c | Trust statement 
  --- | --- | --- | --- 
199781|	1510707076|	0.047619048|	-1
199781|	286650|	0.047619048|	-1
199828|	621186948|	0.285714286|	-1
199828|	481857412|	0.142857143|	-1
199831|	237979|	0.021739130|	-1
199855|	204681|	0.024390244|	-1
.......|.......|.......|.......



### f6a
 User u | User v | f6a | Trust statement 
  --- | --- | --- | --- 
199781|	236296|	0.061304169|	1
199781|	308739|	0.053407853	|1
199781|	205535|	0.122617175	|1
199781|	238184|	0.049441736	|1
199781|	434399|	0.103293782	|-1
199781|	2453704580|	0.045822518|	-1
.......|.......|.......|.......




### f6b
 User u | User v | f6b | Trust statement 
  --- | --- | --- | --- 
199781|	236296|	0.045292387|	1
199781|	308739|	0.039844096	|1
199781|	205535|	0.108233280	|1
199781|	238184|	0.037675205	|1
199781|	434399|	0.063300435	|-1
199781|	2453704580|	0.034729006|	-1
.......|.......|.......|.......



### f6c
 User u | User v | f6c | Trust statement 
  --- | --- | --- | --- 
199781|	236296|	1.214999700|	1
199781|	308739|	0.857856843|	1
199781|	205535|	1.357856843|	1
199781|	238184|	0.673646317	|1
199781|	434399|	0.607856843	|-1
199781|	2453704580|	0.357856843|	-1
.......|.......|.......|.......


### f7a
 User v | f7a 
  --- | --- 
236296|	0.147624040
308739|	0.155853085
205535|	0.162917113
238184|	0.302483544
2453704580|	0.326523123
2320404356|	0.144202681
.......|.......


### f7b
 User u | f7b  
  --- | --- 
199837|	0.130576177
199837|	0.130576177
199850|	0.016492252
199850|	0.016492252
199850|	0.016492252
199850|	0.016492252
.......|.......

### f7c
 User u | User v | f7c | Trust statement 
  --- | --- | --- | --- 
199781|	236296|	0.546308214|	1
199781|	308739|	0.554537259	|1
199781|	205535|	0.561601287	|1
199781|	238184|	0.701167718	|1
199781|	434399|	0.001069366	|-1
199781|	2453704580|	0.725207297|	-1
.......|.......|.......|.......

### f8
 User u | User v | f8 | Trust statement 
  --- | --- | --- | --- 
199781|	238184|	0.020270270|	1
199781|	1640730500|	0.024242424|	1
199781|	371087|	0.011111111|	1
199781|	515078|	0.013157895|	1
199781|	216430|	0.026431718|	1
199781|	241374|	0.019607843	|1
.......|.......|.......|.......






