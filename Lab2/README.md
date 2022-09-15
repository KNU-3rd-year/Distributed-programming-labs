# Lab 2
**Topic**: multithreading

## Video solution:
You can find a [short video](Lab%202%20solution%20video%20DP.mp4) about this lab!

## Part 1:
### Task:
**Topic:**
Using an independent implementation of the synchronizer

**Task:**
The first task is about Winnie the Pooh. 
The bees, having calculated at the end of the month the losses from the presence of Winnie the Pooh in the forest, 
decided to find him and punish him. 
To search for the bear, they divided the forest into sections, each of which is combed by one swarm of bees. 
If a bear is found in its territory, the pack carries out a demonstration punishment and returns to the hive.
If the area is checked and no Winnie the Pooh is found in it, the swarm also returns to the hive. 
You need to create a multi-threaded application that simulates the actions of bees. 
When deciding to use the task portfolio paradigm.

### Solution:
[Main1.java](src/main/java/ua/university/part1/Main1.java)  
[Beehive.java](src/main/java/ua/university/part1/Beehive.java)  
[Territory.java](src/main/java/ua/university/part1/Territory.java)

---

## Part 2:
### Task:
**Topic:**
Using the standard library

**Task:**
The first military task. 
On a dark, dark night, ensigns Ivanov, Petrov and Nechiporchuk are engaged in stealing military property 
from their native military unit. Being intelligent people and excellent in combat and formation training, 
the ensigns introduced a division of labor: Ivanov takes the property out of the warehouse, 
Petrov loads it into the truck, and Nechiporchuk calculates the value of the property. 
It is necessary to compile a multi-threaded application that simulates the activity of ensigns. 
When deciding to use the paradigm "producer-consumer" with active expectation.

### Solution:
[Main2.java](src/main/java/ua/university/part2/Main2.java)  
[Storage.java](src/main/java/ua/university/part2/Storage.java)  
[StorageItem.java](src/main/java/ua/university/part2/StorageItem.java)