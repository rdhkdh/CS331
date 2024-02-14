# Library System Prolog Program
CS331 Assignment 5  
Ridhiman Dhindsa, 210101088  
Date: 14 Feb 2024  

## Description
This Prolog program simulates a basic library system where users can check out and return books. It maintains a database of books with their titles, authors, and availability status. Users can perform operations such as checking out a book, returning a book, and listing available books.

## Function Input/Output Specification
1. `checkout_book/1`
   - Input: `BookName` (a string representing the title of the book to be checked out).
   - Output: Prints a message indicating whether the book was checked out successfully or not.

2. `return_book/1`
   - Input: `BookName` (a string representing the title of the book to be returned).
   - Output: Prints a message indicating whether the book was returned successfully or not.

3. `list_available_books/1`
   - Input: None
   - Output: Returns a list of available books (titles) as a Prolog list.

## Steps to Run
1. Open a terminal in the directory containing the `lib.pl` file.
2. Start the SWI Prolog interpreter by typing `swipl` followed by pressing Enter.
3. Load the program into the Prolog interpreter by typing `[lib].` followed by pressing Enter. If the file is named differently, use the corresponding file name.
4. Use the provided predicates `checkout_book/1`, `return_book/1`, and `list_available_books/1` to interact with the library system as described in the Function Input/Output Specification.
5. To exit the Prolog interpreter, type `halt.` followed by pressing Enter.

## Example Usage
```prolog
?- checkout_book('Book1').
Book checked out successfully.

?- return_book('Book1').
Book returned successfully.

?- list_available_books(X).
X = ['Book1', 'Book2', 'Book3', 'Book4', 'Book5']
```

> If the book is not present in knowledge base or user tries to return an `available` book, or check out an already `checked_out` book, then prolog outputs `false`.