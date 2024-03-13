# CS331 Lab-8
Ridhiman Dhindsa, 210101088    

## Problem Description:
Given a list `L` where each element is also a list, the objective is to sort the elements within `L` based on their lengths. Lists with shorter lengths are prioritized first in ascending order, followed by lists with longer lengths.

## Steps to Run:
1. Load the Prolog interpreter using `swipl` in the project directory.  
2. Load the file as a database using `[listsort].`  
3. Call the predicate `ssort/2` with the desired input list.

## Example Usage:
Suppose we have the following Prolog file named `listsort.pl` containing the predicate definition:

```prolog
?- [listsort].
true.

?- ssort([[1, 3, 2], [9], [1, 2], [99, 80, 76], [2, 3, 4, 5]], Result).
Result = [[9], [1, 2], [1, 3, 2], [99, 80, 76], [2, 3, 4, 5]] .

?- ssort([[o,p],[f],[q,w,e],[a],[l,k,j],[m,n],[o,f,g,h]], L).
L = [[f], [a], [o, p], [m, n], [q, w, e], [l, k, j], [o, f|...]] .
```

## Internal working:
```prolog
?- store_lengths([[1, 3, 2], [9], [1, 2], [99, 80, 76], [2, 3, 4, 5]], L),
count_frequency(L,M),
sort_pairs_by_first(M,N),
iterate_pairs(N,Q),
reverse_list(Q,T),
iterate_lengths([[9], [1, 2], [1, 3, 2], [99, 80, 76], [2, 3, 4, 5]], T, Res).

L = [3, 1, 2, 3, 4],
M = [[3, 2], [1, 1], [2, 1], [4, 1]],
N = [[4, 1], [3, 2], [2, 1], [1, 1]],
Q = [4, 3, 2, 1],
T = [1, 2, 3, 4],
Res = [[9], [1, 2], [1, 3, 2], [99, 80, 76], [2, 3, 4, 5]] .
```
