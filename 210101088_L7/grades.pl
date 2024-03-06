%Ridhiman Dhindsa, 210101088

% Define facts for students and their grades
student_grade('Alice', 49).
student_grade('Bob', 60).
student_grade('Charles', 32).
student_grade('Dan', 77).
student_grade('Esgred', 90).
student_grade('Finn', 21).
student_grade('Gina', 88).
student_grade('Harry', 100).

% Predicate to calculate average grade
average_grade(Grades, Average) :-
    sum_list(Grades, Sum),
    length(Grades, Length),
    Average is Sum / Length.   %avg = sum of grads/length of list

% Predicate to find the highest grade
highest_grade(Grades, Highest) :-
    max_list(Grades, Highest).  %find max

% Predicate to find the lowest grade
lowest_grade(Grades, Lowest) :-
    min_list(Grades, Lowest).   %find min

% Predicate to categorize pass or fail
pass_fail([_|Grade], pass) :-
    Grade >= 60.             % pass=true if Grade>=60
pass_fail([_,Grade], fail) :-
    Grade < 60.              % fail=true if Grade<60
 
% iterate over list and find passed students
find_passed([],[]).
find_passed(ListOfPairs, Result) :-
    findall(Pair, (member(Pair, ListOfPairs), pass_fail(Pair, pass)), Result).

% iterate over list and find failed students
find_failed([],[]).
find_failed(ListOfPairs, Result) :-
    findall(Pair, (member(Pair, ListOfPairs), pass_fail(Pair, fail)), Result).

% Predicate to analyze student grades
analyze_student_grades(Students, Average, Highest, Lowest, PassList, FailList) :-
    findall(Grade, member([_, Grade], Students), Grades),  % extract grades from list of [student, grade] pairs
    average_grade(Grades, Average),
    highest_grade(Grades, Highest),
    lowest_grade(Grades, Lowest),
    find_passed(Students, PassList),
    find_failed(Students, FailList).
