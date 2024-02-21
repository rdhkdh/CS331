# Prolog Predicate: Frequency-based List Sorting
Ridhiman Dhindsa, 210101088  

## Problem Description:
Given a list `L` where each element is also a list, the objective is to sort the elements within `L` based on their length frequency. Lists with less common lengths are prioritized first in ascending order, followed by lists with more frequent lengths.

## Steps to Run:
1. Load the Prolog interpreter.
2. Load the file containing the predicate definition (`list.pl` in this example).
3. Call the predicate `final_output/2` with the desired input list.

## Example Usage:
Suppose we have the following Prolog file named `freq_sort.pl` containing the predicate definition:

```prolog
?- [list].
true.

?- freq_sort([[9],[33,44],[1,3,2],[9,70],[2,3,4,5],[99,80,76],[1,2]], Result).
Result = [[9], [2, 3, 4, 5], [1, 3, 2], [99, 80, 76], [33, 44], [9, 70], [1, 2]] .

?- freq_sort([[a,b,c],[d,e],[f,g,h],[d,e],[m,n,v,c],[o],[r,q]],L).
L = [[m, n, v, c], [o], [a, b, c], [f, g, h], [d, e], [d, e], [r, q]]
```

## Internal working:
```prolog
?- store_lengths([[9],[33,44],[1,3,2],[9,70],[2,3,4,5],[99,80,76],[1,2]],L),
count_frequency(L,M),
sort_pairs_by_second(M,N),
iterate_pairs(N,Q),
reverse_list(Q,T),
iterate_lengths([[9],[33,44],[1,3,2],[9,70],[2,3,4,5],[99,80,76],[1,2]],T,  Res).

L = [1, 2, 3, 2, 4, 3, 2],
M = [[1, 1], [2, 3], [3, 2], [4, 1]],
N = [[2, 3], [3, 2], [4, 1], [1, 1]],
Q = [2, 3, 4, 1],
T = [1, 4, 3, 2],
Res = [[9], [2, 3, 4, 5], [1, 3, 2], [99, 80, 76], [33, 44], [9, 70], [1, 2]] .
```