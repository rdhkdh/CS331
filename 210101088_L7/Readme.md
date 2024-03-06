# CS331 Lab-7: Student Grade Analysis
Ridhiman Dhindsa, 210101088    
Date: 6 March 2024  

## Problem Description:
Given a list of pairs consisting of student names and grades, we are required to develop a prolog program that analuzes and outputs statistics- average grade, highest grade, lowest grade, list of passed students and list of failed students.  

## Steps to Run:
1. Open terminal in project directory.  
2. Run the Prolog interpreter using `swipl`
3. Load the database by typing `[grades].`  
4. Call the predicate `analyze_student_grades/5` with the desired input list.  

## Example Usage:
We have the following Prolog file named `grades.pl` containing the predicate definition:

```prolog
?- [grades].
true.

?- analyze_student_grades([['Alice',49],['Bob',60],['Charles',32],['Dan',77],['Esgred',90],['Finn',21],['Gina',88],['Harry',100]],Average,Highest,Lowest,PassList,FailList).
Average = 64.625,
Highest = 100,
Lowest = 21,
PassList = [['Bob', 60], ['Dan', 77], ['Esgred', 90], ['Gina', 88], ['Harry', 100]],
FailList = [['Alice', 49], ['Charles', 32], ['Finn', 21]].

?- analyze_student_grades([['Alice',49],['Bob',49],['Charles',49]],Average,Highest,Lowest,PassList,FailList).
Average = Highest, Highest = Lowest, Lowest = 49,
PassList = [],
FailList = [['Alice', 49], ['Bob', 49], ['Charles', 49]].

```

## Function Description:
`average_grade/2`  
**Inputs:** List of grades, variable to store Average  
**Outputs:** Calculates sum of grades in list, length of list, divides them and outputs average.  

`highest_grade/2`
**Inputs:** List of grades, variable to store Highest  
**Outputs:** Calculates maximum grade in list.  

`lowest_grade/2`  
**Inputs:** List of grades, variable to store Lowest  
**Outputs:** Calculates minimum grade in list.  

`pass_fail/2`  
**Inputs:** [Student, Grade] pair  
**Outputs:** pass= true if Grade>=60, fail= true if Grade<60.  

`find_passed/2`  
**Inputs:** List of [Student, Grade] pairs, list to store passed students  
**Outputs:** Finds all pairs in the given list, where student's grade>=60.    

`find_failed/2`
**Inputs:** List of [Student, Grade] pairs, list to store failed students  
**Outputs:** Finds all pairs in the given list, where student's grade<60.   

`analyze_student_grades/5`  
**Inputs:** List of [Student, Grade] pairs, variable to store Average, variable to store Highest, variable to store Lowest, list to store passed students, list to store failed students  
**Outputs:** Adds the grades into a sepaarate list. Finds average grade, lowest grade, highest grade using this list. Finds passed and failed students using original list of pairs.  