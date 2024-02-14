% Binary Tree representation: tree(Value, LeftSubtree, RightSubtree)

% is_bst/1: Predicate to check if a binary tree is a BST
is_bst(nil).
is_bst(tree(_, Left, Right)) :-
    is_subtree_less(Left, Value),
    is_subtree_greater(Right, Value),
    is_bst(Left),
    is_bst(Right).

% is_subtree_less/2: Helper predicate to check if all nodes in a subtree are less than a given value
is_subtree_less(nil, _).
is_subtree_less(tree(Val, Left, Right), Value) :-
    Val < Value,
    is_subtree_less(Left, Value),
    is_subtree_less(Right, Value).

% is_subtree_greater/2: Helper predicate to check if all nodes in a subtree are greater than a given value
is_subtree_greater(nil, _).
is_subtree_greater(tree(Val, Left, Right), Value) :-
    Val > Value,
    is_subtree_greater(Left, Value),
    is_subtree_greater(Right, Value).

% Example usage:
% Define a binary tree
%           5
%          / \
%         3   7
%        / \ / \
%       1  4 6  8
example_tree(tree(5, tree(3, tree(1, nil, nil), tree(4, nil, nil)), tree(7, tree(6, nil, nil), tree(8, nil, nil)))).

% Test the tree
% ?- example_tree(Tree), is_bst(Tree).
% true.

% Test with a non-BST tree
% ?- example_tree(Tree), Tree = tree(_, Left, _), Left = tree(6, _, _), is_bst(Tree).
% false.
