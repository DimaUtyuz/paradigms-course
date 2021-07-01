composites(1).
push_step(N, Max, D) :- N < Max, assert(composites(N)), N1 is N + D, push_step(N1, Max, D).
fill_composites(N, Max) :- not composites(N), N1 is N * N, push_step(N1, Max, N).
fill_composites(N, Max) :- N * N < Max, N1 is N + 1, fill_composites(N1, Max).
init(MAX_N) :- Max is MAX_N + 1, fill_composites(2, Max).
prime(N) :- not composites(N).
composite(N) :- N > 0, composites(N).

take_divisor(N, A, R) :- prime(A), M is N mod A, M = 0, R is A, !.
take_divisor(N, A, R) :- A * A < N + 1, A1 is A + 1, take_divisor(N, A1, R), !.
take_divisor(N, A, N).

check_list([]) :- !.
check_list([A]) :- prime(A), !.
check_list([F, S | T]) :- F =< S, prime(F), check_list([S | T]). 

mul(1, []) :- !.
mul(N, [N]) :- !.
mul(N, [H | T]) :- mul(N1, T), N is N1 * H.

prime_divisors(1, []) :- !.
prime_divisors(N, [N]) :- prime(N), !.
prime_divisors(N, [H | T]) :- number(N), !, take_divisor(N, 2, H), N1 is N / H, prime_divisors(N1, T).
prime_divisors(N, [H | T]) :- check_list([H | T]), mul(N, [H | T]). 

prime_count(2, 1) :- !.
prime_count(P, N) :- prime(P), !, P1 is P - 1, prime_count(P1, N1), N is N1 + 1.
prime_count(P, N) :- P1 is P - 1, prime_count(P1, N).

prime_find(P, 0, A) :- P is A - 1, !.
prime_find(P, N, A) :- prime(A), A1 is A + 1, N1 is N - 1, prime_find(P, N1, A1), !.
prime_find(P, N, A) :- A1 is A + 1, prime_find(P, N, A1).

prime_index(P, N) :- number(P), !, prime(P), prime_count(P, N).
prime_index(P, N) :- prime_find(P, N, 2).