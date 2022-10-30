# Lab 7
**Topic**: Console implementation of a single-level system

## Task:
Create a program in which to implement CRUD operations. 

Console implementation of a single-level system:
1. Work with XML
2. DBMS (MySQL) databases

| Requirements                                        | Implementation            |
|-----------------------------------------------------|---------------------------|
| Subject area                                        | Schedule of classes       |
| Objects                                             | Days of the week, Classes |
| StructuresChecking the structure of an XML document | XML schema                |

DATABASE STRUCTURE:
```sql
Enter password: ****
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 22
Server version: 8.0.31 MySQL Community Server - GPL

Copyright (c) 2000, 2022, Oracle and/or its affiliates.

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

mysql> USE SCHEDULE
Database changed
mysql> CREATE TABLE DAYS
->  (ID INTEGER NOT NULL,
-> DAY_OF_WEEK INTEGER);
Query OK, 0 rows affected (0.23 sec)

mysql> CREATE TABLE LESSONS
->  (ID INTEGER NOT NULL,
-> ID_DAY INTEGER NOT NULL,
-> SUBJECT_NAME CHAR (32),
-> TEACHER_NAME CHAR (32),
-> START_TIME CHAR (32),
-> END_TIME CHAR (32));
Query OK, 0 rows affected (0.23 sec)

mysql> ALTER TABLE DAYS
-> ADD PRIMARY KEY (ID);
Query OK, 0 rows affected (0.25 sec)
Records: 0  Duplicates: 0  Warnings: 0

mysql> ALTER TABLE LESSONS
-> ADD PRIMARY KEY (ID);
Query OK, 0 rows affected (0.04 sec)
Records: 0  Duplicates: 0  Warnings: 0

mysql> ALTER TABLE LESSONS
-> ADD FOREIGN KEY (ID_DAY)
-> REFERENCES DAYS (ID);
Query OK, 0 rows affected (0.07 sec)
Records: 0  Duplicates: 0  Warnings: 0

mysql> INSERT INTO DAYS VALUES (1, 0);
Query OK, 1 row affected (0.19 sec)

mysql> INSERT INTO DAYS VALUES (2, 1);
Query OK, 1 row affected (0.09 sec)

mysql> INSERT INTO CITIES VALUES (1, 1, 'Math', 'John Smith', '08:40:00', '10:15:00');
ERROR 1146 (42S02): Table 'schedule.cities' doesn't exist
mysql> INSERT INTO LESSONS VALUES (1, 1, 'Math', 'John Smith', '08:40:00', '10:15:00');
Query OK, 1 row affected (0.01 sec)

mysql> INSERT INTO LESSONS VALUES (2, 1, 'OOP', 'Max Fox', '10:35:00', '11:10:00');
Query OK, 1 row affected (0.01 sec)

mysql> INSERT INTO LESSONS VALUES (3, 2, 'OOP', 'Max Fox', '08:40:00', '10:15:00');
Query OK, 1 row affected (0.01 sec)

mysql> SELECT * FROM LESSONS INNER JOIN DAYS ON LESSONS.ID_DAY = DAYS.ID;
+----+--------+--------------+--------------+------------+----------+----+-------------+
| ID | ID_DAY | SUBJECT_NAME | TEACHER_NAME | START_TIME | END_TIME | ID | DAY_OF_WEEK |
+----+--------+--------------+--------------+------------+----------+----+-------------+
|  1 |      1 | Math         | John Smith   | 08:40:00   | 10:15:00 |  1 |           0 |
|  2 |      1 | OOP          | Max Fox      | 10:35:00   | 11:10:00 |  1 |           0 |
|  3 |      2 | OOP          | Max Fox      | 08:40:00   | 10:15:00 |  2 |           1 |
+----+--------+--------------+--------------+------------+----------+----+-------------+
3 rows in set (0.00 sec)

mysql> SELECT * FROM LESSONS INNER JOIN DAYS ON LESSONS.ID_DAY = 1;Y
+----+--------+--------------+--------------+------------+----------+----+-------------+
| ID | ID_DAY | SUBJECT_NAME | TEACHER_NAME | START_TIME | END_TIME | ID | DAY_OF_WEEK |
+----+--------+--------------+--------------+------------+----------+----+-------------+
|  2 |      1 | OOP          | Max Fox      | 10:35:00   | 11:10:00 |  1 |           0 |
|  1 |      1 | Math         | John Smith   | 08:40:00   | 10:15:00 |  1 |           0 |
|  2 |      1 | OOP          | Max Fox      | 10:35:00   | 11:10:00 |  2 |           1 |
|  1 |      1 | Math         | John Smith   | 08:40:00   | 10:15:00 |  2 |           1 |
+----+--------+--------------+--------------+------------+----------+----+-------------+
4 rows in set (0.00 sec)

    -> ;
ERROR 1064 (42000): You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near 'Y' at line 1
mysql> SELECT * FROM LESSONS INNER JOIN DAYS ON LESSONS.ID_DAY = 1;
+----+--------+--------------+--------------+------------+----------+----+-------------+
| ID | ID_DAY | SUBJECT_NAME | TEACHER_NAME | START_TIME | END_TIME | ID | DAY_OF_WEEK |
+----+--------+--------------+--------------+------------+----------+----+-------------+
|  2 |      1 | OOP          | Max Fox      | 10:35:00   | 11:10:00 |  1 |           0 |
|  1 |      1 | Math         | John Smith   | 08:40:00   | 10:15:00 |  1 |           0 |
|  2 |      1 | OOP          | Max Fox      | 10:35:00   | 11:10:00 |  2 |           1 |
|  1 |      1 | Math         | John Smith   | 08:40:00   | 10:15:00 |  2 |           1 |
+----+--------+--------------+--------------+------------+----------+----+-------------+
4 rows in set (0.00 sec)

mysql> SELECT * FROM DAYS;
+----+-------------+
| ID | DAY_OF_WEEK |
+----+-------------+
|  1 |           0 |
|  2 |           1 |
+----+-------------+
2 rows in set (0.00 sec)

mysql> SELECT * FROM LESSONS;
+----+--------+--------------+--------------+------------+----------+
| ID | ID_DAY | SUBJECT_NAME | TEACHER_NAME | START_TIME | END_TIME |
+----+--------+--------------+--------------+------------+----------+
|  1 |      1 | Math         | John Smith   | 08:40:00   | 10:15:00 |
|  2 |      1 | OOP          | Max Fox      | 10:35:00   | 11:10:00 |
|  3 |      2 | OOP          | Max Fox      | 08:40:00   | 10:15:00 |
+----+--------+--------------+--------------+------------+----------+
3 rows in set (0.00 sec)

mysql> SELECT * FROM LESSONS INNER JOIN DAYS ON LESSONS.ID_DAY = DAYS.ID WHERE DAYS.DAY_OF_WEEK = 0;
+----+--------+--------------+--------------+------------+----------+----+-------------+
| ID | ID_DAY | SUBJECT_NAME | TEACHER_NAME | START_TIME | END_TIME | ID | DAY_OF_WEEK |
+----+--------+--------------+--------------+------------+----------+----+-------------+
|  1 |      1 | Math         | John Smith   | 08:40:00   | 10:15:00 |  1 |           0 |
|  2 |      1 | OOP          | Max Fox      | 10:35:00   | 11:10:00 |  1 |           0 |
+----+--------+--------------+--------------+------------+----------+----+-------------+
2 rows in set (0.00 sec)
```