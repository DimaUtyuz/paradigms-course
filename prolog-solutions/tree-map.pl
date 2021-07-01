max(A, B, A) :- A > B, !.
max(_, B, B).

% node - [key, value, high, balance, size, left, right]

get_high([], 0).
get_balance([], 0).
get_size([], 0).
get_left([], []).
get_right([], []).

get_key([Key, _, _, _, _, _, _], Key).
get_value([_, Value, _, _, _, _, _], Value).
get_high([_, _, High, _, _, _, _], High).
get_balance([_, _, _, Balance, _, _, _], Balance).
get_size([_, _, _, _, Size, _, _], Size).
get_left([_, _, _, _, _, Left, _], Left).
get_right([_, _, _, _, _, _, Right], Right).

set_key([_, Value, High, Balance, Size, Left, Right], K, [K, Value, High, Balance, Size, Left, Right]).
set_value([Key, _, High, Balance, Size, Left, Right], V, [Key, V, High, Balance, Size, Left, Right]).
set_left([Key, Value, High, Balance, Size, _, Right], L, [Key, Value, High, Balance, Size, L, Right]).
set_right([Key, Value, High, Balance, Size, Left, _], R, [Key, Value, High, Balance, Size, Left, R]).

update([], []) :- !.
update([Key, Value, _, _, _, Left, Right], [Key, Value, NH, NB, NS, Left, Right]) :- 
				get_high(Left, HL), get_high(Right, HR), max(HL, HR, Tmp), get_size(Left, SL), get_size(Right, SR), NH is Tmp + 1, NB is HL - HR, NS is SL + SR + 1.

recalc(Tree, Result) :- update(Tree, Tmp), balance(Tmp, Result).

rotateLeft([], []) :- !.
rotateRight([], []) :- !.
rotateLeft(Tree, Result) :- get_right(Tree, B), get_left(B, AR), set_right(Tree, AR, Tmp1), update(Tmp1, NTree), set_left(B, NTree, Tmp2), update(Tmp2, Result), !. 
rotateRight(Tree, Result) :- get_left(Tree, B), get_right(B, AL), set_left(Tree, AL, Tmp1), update(Tmp1, NTree), set_right(B, NTree, Tmp2), update(Tmp2, Result), !. 

balance(Tree, Tree) :- get_balance(Tree, B), B > -2, B < 2, !.
balance(Tree, Result) :- get_balance(Tree, A), A = -2, get_right(Tree, Right), get_balance(Right, B), B \= 1, rotateLeft(Tree, Result), !.
balance(Tree, Result) :- get_balance(Tree, A), A = -2, get_right(Tree, Right), get_balance(Right, B), B = 1, rotateRight(Right, NRight), set_right(Tree, NRight, R), rotateLeft(R, Result), !.
balance(Tree, Result) :- get_balance(Tree, A), A = 2, get_left(Tree, Left), get_balance(Left, B), B \= -1, rotateRight(Tree, Result), !.
balance(Tree, Result) :- get_balance(Tree, A), A = 2, get_left(Tree, Left), get_balance(Left, B), B = -1, rotateLeft(Left, NLeft), set_left(Tree, NLeft, R), rotateRight(R, Result), !.

map_put([], K, V, [K, V, 1, 0, 1, [], []]) :- !.
map_put(Tree, K, V, Result) :- get_key(Tree, Kt), Kt is K, set_value(Tree, V, Result), !.
map_put(Tree, K, V, Result) :- get_key(Tree, Kt), K < Kt, get_left(Tree, Left), map_put(Left, K, V, NLeft), set_left(Tree, NLeft, R), recalc(R, Result), !.
map_put(Tree, K, V, Result) :- get_right(Tree, Right), map_put(Right, K, V, NRight), set_right(Tree, NRight, R), recalc(R, Result), !.

map_build([], []) :- !.
map_build([(K, V) | T], Result) :- map_build(T, R), map_put(R, K, V, Result).

map_min([K, V, _, _, _, [], _], (K, V)) :- !.
map_min(Tree, M) :- get_left(Tree, Left), map_min(Left, M), !.
map_max([K, V, _, _, _, _, []], (K, V)) :- !.
map_max(Tree, M) :- get_right(Tree, Right), map_max(Right, M), !.

map_remove([], _, []) :- !.
map_remove([K, _, _, _, _, [], []], K, []) :- !.
map_remove(Tree, K, Result) :- get_key(Tree, Kt), Kt is K, get_balance(Tree, B), B > -1, get_left(Tree, Left), map_max(Left, (K1, V1)), 
																set_key(Tree, K1, Tmp1), set_value(Tmp1, V1, Tmp2), map_remove(Left, K1, NL), set_left(Tmp2, NL, Tmp3), recalc(Tmp3, Result), !.
map_remove(Tree, K, Result) :- get_key(Tree, Kt), Kt is K, get_balance(Tree, B), B = -1, get_right(Tree, Right), map_min(Right, (K1, V1)), 
																set_key(Tree, K1, Tmp1), set_value(Tmp1, V1, Tmp2), map_remove(Right, K1, NL), set_right(Tmp2, NL, Tmp3), recalc(Tmp3, Result), !.
map_remove(Tree, K, Result) :- get_key(Tree, Kt), K < Kt, get_left(Tree, Left), map_remove(Left, K, NL), set_left(Tree, NL, Tmp), recalc(Tmp, Result), !.
map_remove(Tree, K, Result) :- get_right(Tree, Right), map_remove(Right, K, NR), set_right(Tree, NR, Tmp), recalc(Tmp, Result), !.

map_get(Tree, K, R) :- get_key(Tree, Kt), Kt is K, get_value(Tree, R), !.
map_get(Tree, K, R) :- get_key(Tree, Kt), K < Kt, get_left(Tree, Left), map_get(Left, K, R), !. 
map_get(Tree, K, R) :- get_key(Tree, _), get_right(Tree, Right), map_get(Right, K, R), !. 

map_headMapSize([], _, 0) :- !.
map_headMapSize(Tree, K, Sz) :- get_key(Tree, Kt), Kt is K, get_left(Tree, Left), get_size(Left, Sz), !.
map_headMapSize(Tree, K, Sz) :- get_key(Tree, Kt), K < Kt,  get_left(Tree, Left), map_headMapSize(Left, K, Sz), !.
map_headMapSize(Tree, K, Sz) :- get_left(Tree, Left), get_right(Tree, Right), get_size(Left, SzL), map_headMapSize(Right, K, SzR), Sz is SzL + SzR + 1, !.
map_tailMapSize(Tree, K, Sz) :- map_headMapSize(Tree, K, T), get_size(Tree, S), Sz is S - T.
