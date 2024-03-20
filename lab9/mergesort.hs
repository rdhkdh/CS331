-- Ridhiman Dhindsa, 210101088
import Data.List (sort)

-- function to merge 2 lists in ascending order
merge :: Ord a => [a] -> [a] -> [a] --function definition
merge [] ys = ys --base case: if x is empty
merge xs [] = xs --base case: if y is empty

merge (x:xs) (y:ys)
    | x <= y    = x : merge xs (y:ys) --if x is smaller, place at beginning of resultant list
    | otherwise = y : merge (x:xs) ys --if y is smaller, place at beginning of resultant list

-- function to recursively divide a list into 2 halves, sort them, merge them
mergeSort :: Ord a => [a] -> [a]  --function definition
mergeSort [] = []  --base case: if input list is empty
mergeSort [x] = [x] --base case: if list has only 1 element

mergeSort xs = merge (mergeSort firstHalf) (mergeSort secondHalf)  --recursively apply mergesort on both halves
    where
        (firstHalf, secondHalf) = splitAt (length xs `div` 2) xs


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
    
    -- Sort the list using mergeSort function
    let sortedList = mergeSort list
    
    -- Print the sorted list
    putStrLn "Sorted List:"
    print sortedList
