# Lab 3
**Topic**: Binary semaphores: Protecting shared memory access

## Part 1:
You can find this part in another repository.

---

## Part 2:
### Task:
**Topic:**
Using an independent implementation of the synchronizer

**Task:**
A task about a hairdresser. There is a hair salon in a quiet town. 
The hairdressing salon is small, only the hairdresser and one visitor can go there. 
The hairdresser serves the visitor all his life. When there is no one in the cabin, he sleeps in a chair. 
When a visitor comes and sees a sleeping barber, he will be his, 
sits in a chair and sleeps while the barber is busy cutting hair. 
If a visitor comes and the hairdresser is busy, he gets in line and falls asleep. 
After the haircut, the hairdresser himself sees off the visitor. 
If there are other visitors who are waiting, the hairdresser wakes one of them up and waits for him to sit 
in the barber's chair and start the haircut. If no one is there, he sits down in his chair again and falls asleep. 
Create a multi-threaded application that simulates the working day of a hair salon.

### Solution:
[Main_Lab1_part2.java](src/main/java/ua/university/part2/Main_Lab1_part2.java)  
[Barber.java](src/main/java/ua/university/part2/Barber.java)  
[Client.java](src/main/java/ua/university/part2/Client.java)  
[Barbershop.java](src/main/java/ua/university/part2/Barbershop.java)

---

## Part 3:
### Task:
**Topic:**
Using the standard library

**Task:**
Assignment about smokers. There are three smoker processes and one mediator process. 
A smoker continuously rolls cigarettes and smokes them. To roll a cigarette, you need tobacco, paper and matches. 
One process-smoker has tobacco, the second has paper, and the third has matches. 
The mediator places two different random components on the table. That smoker process, which has a third component, 
takes the components from the table, rolls a cigarette, and smokes. 
The mediator waits until the smoker finishes smoking, then the process repeats. 
Create a multithreaded application that simulates the behavior of smokers and an intermediary. 
When solving the task, use semaphores.

### Solution:
[Main_Lab3_part3.java](src/main/java/ua/university/part3/Main_Lab3_part3.java)  
[Smoker.java](src/main/java/ua/university/part3/Smoker.java)  
[Diller.java](src/main/java/ua/university/part3/Diller.java)  
[Component.java](src/main/java/ua/university/part3/Component.java)