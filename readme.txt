Object Oriented Programming Project
------------------------------------
Student: Andy Blankley
------------------------------------
Id: G00313312
------------------------------------
Multithreaded RailFence Cypher
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------

1. Class runner provides the user an option to enter text input. 

2. The runner class reads the users input. It then encrypts it with a key of 5 using the RailFence class. This all depends on the users choice of input. 

3. The program will output the encrypted text along with the matrix of the text while in a 2D array in CSV format.

4. It will then parse the "quadgrams.txt" file into a set concurrent hashmap of <string, double> and that map is then used to create a TextScorer object "txtScorer".

5. After this, the Runner class then creates an instance of CypherBreaker which calls its pre-made method init() method.

6. The init() method creates a new Decrypter thread for each key starting from 2 up until the cyphertext.length/2 and starts its own thread with a blocking queue in it.

7. Each of these threads then decypts the cyphertext using the RailFence decrypt method and then add the plaintext, key and score to a Resultable object.

8. The result is then put onto the queue and on the CypherBreaker class the queue thread checks if the current result being read off the queue has a higher score than
   the local result variable in CypherBreaker. 

9. If the result is greater than the local result it is changed to the higher scoring one.

10. The synchronized function increment() is invoked, everytime a result is read from the queue, which will check for the end of the queue and add a poison object.

11. When the queue has reached the end and the poison object added, it will print the result that it believes is the most english sounding, the key used to 
get this result, and its score and it will match users inputed text and the key used to encrypt it.

12. Program will then finish.

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------