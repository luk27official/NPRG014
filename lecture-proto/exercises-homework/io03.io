Vect := Object clone do (
	x := 0
	y := 0
	length := method( (x squared + y squared) sqrt )
	asString := method ( "v(" .. (x asString) .. "," .. (y asString) .. ")" )
)

v1 := Vect clone
v1 x = 3
v1 y = 4

v1 println
"Length of v1 is #{v1 length}" interpolate println


"" println


/* ASSIGNMENT:
 * Override the asString method such that println prints out the vector in form: "v(3,4)"
 */


v1 println // prints "v(3,4)" 

"" println


/* ASSIGNMENT:
 * Create a function that will allow creating new vectors in the following way:
 */

Vect with (7, 9) println

Vect with := method(x, y,
	res := Vect clone
	res x = x
	res y = y
	res
)


"" println

/* ASSIGNMENT:
 * Create function length2 that will return a number, which however when printed out 
 * it returns a string like: "d(3,4) = 5"
 * 
 * d1 := v1 length2
 * d1 println // prints "d(3,4) = 5"
 * (d1 + 2) println // since d1 is a number, this prints out 7
 */
Vect length2 := method(
	res := length clone
	res asString := method( "d(" .. (x asString) .. "," .. (y asString) .. ") = " .. (res call) )
	res
)

d1 := v1 length2
d1 println // prints "d(3,4) = 5"
(d1 + 2) println // since d1 is a number, this prints out 7


