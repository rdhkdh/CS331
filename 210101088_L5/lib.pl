% Ridhiman Dhindsa, 210101088

% Declare book/3 as dynamic predicate
:- dynamic(book/3).

%----------------------------------Facts-----------------------------------
book('Book1', 'Author1', available).
book('Book2', 'Author2', available).
book('Book3', 'Author3', checked_out).
book('Book4', 'Author4', available).
book('Book5', 'Author5', checked_out).
book('Inheritance', 'Christopher Paolini', available).
book('Divergent', 'Veronica Roth', available).
book('A Game of Thrones', 'George Martin', available).
book('A Midsummer Nights Dream', 'William Shakespeare', available).
book('The Puzzzle Ring', 'Kate Forsythe', available).
book('Concepts of Physics', 'HC Verma', available).


%--------------------------------Predicates-------------------------------------

% change status of book to checked_out
checkout_book(BookName) :-
    retract(book(BookName, _, available)),   % remove fact from knowledge base
    assertz(book(BookName, _, checked_out)), % insert fact at the end of list in knowledge base
    write('Book checked out successfully.'),
    nl.   % newline

% change status of book to available
return_book(BookName) :-
    retract(book(BookName, _, checked_out)),
    assertz(book(BookName, _, available)),
    write('Book returned successfully.'),
    nl.

% find all available books and store in list- AvailableBooks
list_available_books(AvailableBooks) :-
    findall(BookName, book(BookName, _, available), AvailableBooks). 

