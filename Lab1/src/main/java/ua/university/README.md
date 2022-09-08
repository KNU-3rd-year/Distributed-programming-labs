# Lab 1

## Part 1:
### Task:
Topic: Processes, streams (threads).
Task: Compose a program (process) that runs two background threads (threads) in parallel. Use thread synchronization on a shared resource.
Description of the program:
When the program is started with the "Start" button, two threads Tthread1 and TThread2 are simultaneously started in parallel, which try to set the "slider" to its position (1st position 10, 2nd position 90)
Threads can be prioritized, and depending on the priority, preference is given to one or the other thread
Threads are destroyed when the program terminates.

### Solution:
[Main.java](Main.java)  
[MyThread.java](MyThread.java)