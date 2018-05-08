## Describtion on preprocessing data
All the data comes from [Epinions Trust Network Datasets](http://www.trustlet.org/epinions.html)

In this project I mainly focus on the **Extended Epinions dataset**.
In the extended epinonins dataset, it contains **user_rating.txt, mc.txt, and rating.txt.**

### Original text file 
   
  1.user_rating.txt:
    
    Trust is the mechanism by which the user makes a statement that he likes 
    the content or the behavior of particular user and would like to see more 
    of what the users does in the site. Distrust is the opposite of the trust 
    in which the user says that they do want to see lesser of the operations 
    performed by that user.

  2.mc.txt:
    
    Each article is written by a user.
    
    
  3.rating.txt:
  
    Ratings are quantified statements made by users regarding the quality of 
    a content in the site. Ratings is the basis on which the contents are sorted 
    and filtered.
     
  ## Details
  ### user_rating.txt:
  
  Below is the column desrbition:
  
 MY_ID  | OTHER_ID   | VALUE | CREATION 
--- | --- | --- | ---
3287060356|	232085 |-1|	2001/01/10
3288305540|	709420 | 1|	2001/01/10
3290337156|	204418 |-1|	2001/01/10
3294138244|	269243 |-1|	2001/01/10
3294138244|	170692484 |-1|	2001/01/10
3296759684|	347967364 | 1|	2001/01/10
  ....... |  ....... |  ....... |  .......
  

*MY_ID*:    

This stores Id of the member who is making the trust/distrust statement

*OTHER_ID*:   

The other ID is the ID of the member being trusted/distrusted

*VALUE*:    

Value = 1 for trust and -1 for distrust

*CREATION*:   

It is the date on which the trust was made

    Size: 23.3 Megabytes

    Columns: 4

    Row: 841372
  
  
  
  
  ### mc.txt:
  
  Below is the column desrbition:
  

CONTENT_ID  | AUTHOR_ID   | SUBJECT_ID  
--- | --- | --- 
1445594|718357|149002425217
1445595|220568|149003604865
1445596|717325|5303145344
1445597|360156|192620893057
1445598|718857|149002163073
1445600|513114|34252
.......|.......|.......


*CONTENT_ID*: 

The object ID of the article.

*AUTHOR_ID*: 

The ID of the user who wrote the article.

*SUBJECT_ID*: 

The ID of the subject that the article is supposed to be about.
    
    Size: 40.3 Megabytes

    Columns: 3

    Row: 1560145
    
  

### rating.txt:
  
  Below is the column desrbition:
  
  OBJECT_ID  | MEMBER_ID | RATING | STATUS | LAST_MODIFIED | TYPE | VERTICAL_ID
  --- | --- | --- | --- | --- | --- | ---
139431556|	591156|	5|	0|	2001/01/10|		1|	2518365
139431556|	1312460676|	5|	0|	2001/01/10|		1|	2518365
139431556|	204358|	5|	0|	2001/01/10|		1|	2518365
139431556|	368725|	5|	0|	2001/01/10|		1|	2518365
139431556|	277629|	5|	0|	2001/01/10|		1|	2518365
139431556|	246386|	5|	0|	2001/01/10|		1|	2518365
.......|.......|.......|.......|.......|.......|.......


*OBJECT_ID*:

The object ID is the object that is being rated. The only valid objects 
at the present time are the content_id of the member_content table. 
This means that at present this table only stores the ratings on reviews 
and essays

*MEMBER_ID*:

Stores the id of the member who is rating the object

*RATING*:

Stores the 1-5 (1- Not helpful , 2 - Somewhat Helpful, 
3 - Helpful 4 - Very Helpful 5- Most Helpful) rating of the object by member.

*STATUS*:

The display status of the rating. 1 :- means the member has 
chosen not to show his rating of the object and 0 meaning the 
member does not mind showing his name beside the rating.

*LAST_MODIFIED*:

The latest date on which the member modified his rating of the object
TYPE If and when we allow more than just content rating to be stored 
in this table, then this column would store the type of the object being rated.

*VERTICAL_ID*:

Vertical_id of the review.

    Size: 683 Megabytes

    Columns: 7

    Rows: 13668320
    
    
 
## Process of how the data set is being processsed.
In order to improve the program's running effiency, it is essential to use a well processed input dataset. The original text files  separates the rater, type of the item and the producer. The key idear in this processing step is to combine the rater who rated the item i, the reivewer who produced the item i, the tpye of the item.

### Removing unnecessary information from original text
First of all, we need to clean the dataset for the better calculation accuracy and running effiency.


For user_rating.txt, I deleted the **CREATION** column. 


**user_rating.txt** after being processed:

MY_ID  | OTHER_ID   | VALUE 
--- | --- | --- 
3287060356|	232085 |-1
3288305540|	709420 | 1
3290337156|	204418 |-1
3294138244|	269243 |-1
3294138244|	170692484 |-1
3296759684|	347967364 | 1
  ....... |  ....... |  ....... 




For rating.txt I deleted LAST_MODIFIED column and TYPE column, and VERTICAL_ID column. 





**rating.txt** after being processed:



OBJECT_ID  | MEMBER_ID | RATING | STATUS 
  --- | --- | --- | --- 
139431556|	591156|	5|	0
139431556|	1312460676|	5|	0
139431556|	204358|	5|	0
139431556|	368725|	5|	0
139431556|	277629|	5|	0
139431556|	246386|	5|	0
.......|.......|.......|.......






### Store dataset into the program:
I used **Scanner inputstream = new Scanner(file)** to take input from the rating.txt, and mc.txt;

item[ ]:
      
      It stores the the item id from user_rating.txt.

user[ ]: 

      It stores the user id from user_rating.txt.
 
item1[ ]: 

      It stores the item id from mc.txt.

type1[ ]:

      It stores the type of the item from mc.txt.
  
review1[ ]:

      It stores the reviewer id from mc.txt.




### Pairing the rater with type of the item.
Here is code segment of paring the rater with the type of rated item.

```java
 public static long search(long i) {
        
        for (int k = 0; k < 1559803; k++) {
            if (i == item1[k]) {
                
                return type1[k];
                
            }
            
        }
        
        return -1;
        
    }
    
    public static void output() {
        
        try {
            PrintStream out = new PrintStream(new FileOutputStream(
                    "C:\\Users\\s6770\\Desktop\\project1\\sources\\rater.txt"));
            
            long output = 0;
            
            for (int i = 0; i < 13668320; i++) {
                output = search(item[i]);
                out.println(output);
            }
            
            out.close();
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
    }

int i =0;

```
The basic idear is sending every item id  in item[ ] to function **Search**, This function will return the typed of that item. It output the type into the type.txt file. 

### Pairing the rater with reivewer.
Here is code segment of paring the rater with reivewer.

```java
 public static long search(long i) {
        
        for (int k = 0; k < 1559803; k++) {
            if (i == item1[k]) {
                
                return reviewer1[k];
                
            }
            
        }
        
        return -1;
        
    }
    
    public static void output() {
        
        try {
            PrintStream out = new PrintStream(new FileOutputStream(
                    "C:\\Users\\s6770\\Desktop\\project1\\sources\\reivewer.txt"));
            
            long output = 0;
            
            for (int i = 0; i < 13668320; i++) {
                output = search(item[i]);
                out.println(output);
            }
            
            out.close();
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
    }

int i =0;

```
The basic idear is sending every item id  in item[ ] to function **Search**, This function will return reviewer id. It output the id into the reviewer.txt file. 

## Combining type.txt and reviewer.txt
After we get the two text files we can start combing rating.txt, type.txt and reviewer.txt.


Here is the new rating.txt file：


OBJECT_ID  | MEMBER_ID | RATING | STATUS | TYPE | REVIEWER 
---|---|---|---|---|---
762106|	302684|	5|	0|	5382|	410833
763070|	407724|	5|	0|	5382|	296847
762106|	282012|	4|	0|5382|410833
762106|319858|5|0|5382|410833
762106|216630|5|0|5382|410833
762067|228431|5|0|5382|246598
763070|206439|5|0|5382|296847
---|---|---|---|---|---

## sorting

Before we start sorting, it is important to remove all the noisy data(rows with data missing)

OBJECT_ID  | MEMBER_ID | RATING | STATUS | TYPE | REVIEWER 
---|---|---|---|---|---
839877|	200338|	4|	0	|   |


After cleaing the data, we can start sort the rows depends on the column feature.

In rating.txt, I sorted rows depends on the user id from smallest value to largest value.


OBJECT_ID  | MEMBER_ID | RATING | STATUS | TYPE | REVIEWER 
---|---|---|---|---|---
1292308|199775|5|	0|6283|521788
3386805892|199781|4|0|159845|212098
2206633604|199781|5|0|40251|243427
750400|199781|3|	0|160847|200202
3606023812|199781|5	|0|	139535|	222936
1196199556|	199781|	5|	0|	5617849216|	528728
 ---|---|---|---|---|---
 
 


I also create a copy of  rating.txt, and I renamed it with **category.txt**



In category.txt, I sorted rows depends on the reviewer id from smallest value to largest value.



OBJECT_ID  | MEMBER_ID  | TYPE | REVIEWER |RATING | STATUS
---|---|---|---|---|---
7892471428|	296496|	6283|	199775|	3 | 0
7892471428|	687460|	6283|	199775|	3 | 0
7892471428|	244660|	6283|	199775|	3 | 0
7892471428|	292701|	6283|	199775|	3 | 0
805720|	622084|	5583836032|	199776|	5 | 0
805720|	307076|	5583836032|	199776|	5 | 0
 ---|---|---|---|---|---
 
 
 
 
 
In user_rating.txt, I sorted rows depend on the MY_ID.
 
MY_ID  | OTHER_ID   | VALUE 
--- | --- | --- 
199781|	236296|	1
199781|	308739|	1
199781|	205535|	1
199781|	238184|	1
199781|	434399|	-1
199781|	2453704580|	-1
--- | --- | --- 
