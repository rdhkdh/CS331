parent(adam, cain).
parent(eve, cain).
parent(adam, abel).
parent(eve, abel).

father(X, Y) :- parent(X, Y), male(X).
mother(X, Y) :- parent(X, Y), female(X).

male(abel).
male(adam).

female(cain).
female(eve).