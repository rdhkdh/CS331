% Ridhiman Dhindsa, 210101088

% Define a predicate to calculate the length of a list
list_length([], 0).
list_length([_|T], Length) :-
    list_length(T, PrevLength),
    Length is PrevLength + 1.

% Define a predicate to iterate over a list of lists and store the length of each list in a resultant list
store_lengths([], []).
store_lengths([List|Lists], [Length|Lengths]) :-
    list_length(List, Length),
    store_lengths(Lists, Lengths).

% Define a predicate to count the frequency of an element in a list
count_element_frequency(_, [], 0).
count_element_frequency(Element, [Element|T], Frequency) :-
    count_element_frequency(Element, T, PrevFrequency),
    Frequency is PrevFrequency + 1.
count_element_frequency(Element, [H|T], Frequency) :-
    Element \= H,
    count_element_frequency(Element, T, Frequency).

% Define a predicate to count the frequency of elements in a list and store them
count_frequency([], []).
count_frequency([H|T], [[H, Frequency]|Result]) :-
    count_element_frequency(H, [H|T], Frequency),
    % Prevent counting the same element again
    delete(T, H, NewT),
    count_frequency(NewT, Result).

%------------------------sorting a list of pairs by second element, in descending order---------------------------
% Define a predicate to swap two elements in a pair
swap([A,B], [B,A]).

% Define a predicate to compare pairs based on their second element
compare_pairs([_,X], [_,Y]) :-
    X =< Y.

% Define a predicate to sort a list of pairs by their second element
sort_pairs_by_second([], []).
sort_pairs_by_second([Pair|Pairs], Sorted) :-
    partition(compare_pairs(Pair), Pairs, Less, Greater),
    sort_pairs_by_second(Less, SortedLess),
    sort_pairs_by_second(Greater, SortedGreater),
    append(SortedLess, [Pair|SortedGreater], Sorted).

find_lists_with_length(ListOfLists, Length, Result) :-
    findall(List, (member(List, ListOfLists), length(List, Length)), Result).

%-----------------------------get all the first values of the pairs-----------------------------
iterate_pairs([], []).
iterate_pairs([[First|_]|Rest], [First|FirstValues]) :-
    % Add the first value of the current pair to the list
    iterate_pairs(Rest, FirstValues).

reverse_list([], []).
% Recursive case: to reverse a non-empty list,
% reverse the tail of the list and append the head at the end.
reverse_list([Head|Tail], Reversed) :-
    reverse_list(Tail, ReversedTail),
    append(ReversedTail, [Head], Reversed).

%----------------------------find all lists of a given length-----------------------------------------
iterate_lengths(_, [], []).
iterate_lengths(ListOfLists, [Length|Lengths], FinalResult) :-
    find_lists_with_length(ListOfLists, Length, Result), % Apply find_lists_with_length/3 to the current length
    iterate_lengths(ListOfLists, Lengths, RemainingResults), % Recursively process the rest of the lengths
    append(Result, RemainingResults, FinalResult). % Append the results together

%---------------------------final output predicate-----------------------------------
freq_sort(List, Result) :-
    store_lengths(List, L),
    count_frequency(L,M),
    sort_pairs_by_second(M,N),
    iterate_pairs(N,Q),
    reverse_list(Q,T),
    iterate_lengths(List, T, Result).
