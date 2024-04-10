# CS331 Lab10 - All permutations of a list
Ridhiman Dhindsa, 210101088  

## Steps to run:
1) Open terminal in the directory containing the Haskell file.  
2) Compile using: `ghc perm.hs`  
3) Run using: `./perm`  
4) Enter integer list elements as prompted.  

## Example Usage
```
$ ghc perm.hs
$ ./perm
Enter elements of the list separated by spaces:
1 2 3 4
Original List:
[1,2,3,4]
List of permutations:
[[1,2,3,4],[1,2,4,3],[1,3,2,4],[1,3,4,2],[1,4,2,3],[1,4,3,2],[2,1,3,4],[2,1,4,3],[2,3,1,4],[2,3,4,1],[2,4,1,3],[2,4,3,1],[3,1,2,4],[3,1,4,2],[3,2,1,4],[3,2,4,1],[3,4,1,2],[3,4,2,1],[4,1,2,3],[4,1,3,2],[4,2,1,3],[4,2,3,1],[4,3,1,2],[4,3,2,1]]

$ ./perm
Enter elements of the list separated by spaces:
1 1 3
Original List:
[1,1,3]
List of permutations:
[[1,1,3],[1,3,1],[3,1,1]]
```

## Description
For each element in the list, the rest of the elements are permuted in all possible ways, and the first element appended to them. The function **concatMap** iterates over each element in the original list, generates all permutations starting with it and concatenates it to the resultant list. The function **nub** is used to remove duplicates from the list of permutations, incase repeated integers are entered by the user.