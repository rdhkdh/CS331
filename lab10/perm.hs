-- Ridhiman Dhindsa, 210101088
import Data.List (delete,nub)

-- Function to generate all permutations of a list
permutations :: [Int] -> [[Int]] --fn defn
permutations [] = [[]] -- Base case: Empty list has only one permutation, which is itself

permutations xs = nub $ concatMap (\x -> map (x :) (permutations (delete x xs))) xs
-- For each element x in the list, create permutations of the remaining elements (excluding x)
-- Append x to each of these permutations
--nub removes duplicates

-- Function to take user input of a list of elements
getUserList :: IO [Int] --fn defn
getUserList = do
    putStrLn "Enter elements of the list separated by spaces:"
    input <- getLine
    let numbers = map read $ words input :: [Int]
    return numbers

-- Main function
main :: IO () --fn defn
main = do
    list <- getUserList -- Prompt user to input list
    putStrLn "Original List:"
    print list
    
    -- Obtain the list of all permutations
    let permList = permutations list
    
    -- Print the resultant list
    putStrLn "List of permutations:"
    print permList