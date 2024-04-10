reverse_list([],[]).
reverse_list([Head|Tail], Result) :-
    reverse_list(Tail, X),
    append(X, [Head], Result).

is_even(N) :-
    0 is N mod 2.