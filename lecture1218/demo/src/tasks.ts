// --------------------------------------------------------------------------------------
// TASK #1: Typing the createElement function
// --------------------------------------------------------------------------------------

let el = document.getElementById("out")
if (el == null && 1 == 1) throw "out element missing"
// 1 == 1 is not enough...
el = el!;

// TODO: Can you come up with a correct null check 
// that breaks the flow-sensitive type checking?

let tag: "input" = "input";
let input = document.createElement(tag)
input.value = "World"

let btn = document.createElement("button")
btn.innerText = "Greeet!"
btn.onclick = () => { alert("Hello " + input.value); }
el.appendChild(input)
el.appendChild(btn)


// --------------------------------------------------------------------------------------
// TASK #2: Implement bit increment
// --------------------------------------------------------------------------------------

type Bit = 0 | 1


type FourRange = 0 | 1 | 2 | 3
type MiniByte = [Bit,Bit,Bit,Bit]

function set(fourbit:MiniByte, i:FourRange, v:Bit) {
  fourbit[i] = v;
}

function get(fourbit:MiniByte, i:FourRange) : Bit {
  return fourbit[i];
}

function increment(fourbit:[Bit,Bit,Bit,Bit]) {
  // TODO: Write code that increments the number represented by 'fourbit'
  // (0,0,0,0 -> 0,0,0,1 -> 0,0,1,0 -> 0,0,1,1, -> etc.)
  // Can you do this in a way that makes the type checker happy?
  //
  // NOTE: If you index into fourbit using [i], the checker gives up.
  // You can try this using 'set' and 'get' which enforce range check.

  /*
  let i = 0;
  while(fourbit[i] === 1 && i < fourbit.length) {
    fourbit[i] = 0;
    i++;
  }
  if (i < fourbit.length) fourbit[i] = 1;
  */

  let fr: FourRange = 0;
  while(get(fourbit, fr) === 1) {
    set(fourbit, fr, 0);
    fr = fr + 1 as FourRange;
    if (fr > 3) fr = 0; // this has to be runtime-checked
    // fr = <FourRange>(fr + 1); // old syntax
  }
  set(fourbit, fr, 1);
}

let demo : MiniByte = [0,0,0,0]

let count = document.createElement("p")
el.appendChild(count);
count.innerText = demo.join(",")

let inc = document.createElement("button")
el.appendChild(inc);
inc.innerText = "Increment!"

inc.onclick = () => {
  increment(demo);
  count.innerText = demo.join(",")
}


// --------------------------------------------------------------------------------------
// TASK #3: Implement a create animal function
// --------------------------------------------------------------------------------------

type Fish = {
  name:string
}
type Cat = {
  name:string
  sound:string
}

// additional overloads
function createAnimal(species: "fish", name: string) : Fish
function createAnimal(species: "cat", name: string, sound: string) : Cat

function createAnimal(species: string, name: string, sound?: string): Fish | Cat {
  switch(species) {
    case "fish": return { "name": name };
    case "cat": return { "name": name, "sound": sound };
  }
  throw "unknown";
}

// TODO: Implement an overloaded function 'createAnimal' that returns
// the right type of animal, depending on the first parameter

let a1 = createAnimal("fish", "Moby")
let a2 = createAnimal("cat", "Cheshire cat", "We are all mad here")

let animals = document.createElement("p")
el.appendChild(animals)

// NOTE: Uncomment the following
animals.innerHTML += a1.name + "<br />";
// animals.innerHTML += a1.sound + "<br />"; // This should be an error
animals.innerHTML += a2.name + "<br />";
animals.innerHTML += a2.sound + "<br />";


// --------------------------------------------------------------------------------------
// TASK #4: Write a simple expression evaluator
// --------------------------------------------------------------------------------------

interface Constant {
  kind:"constant"
  value:number
}

interface Binary {
  kind:"binary"
  operator:"+" | "*"
  left:Expression
  right:Expression
} 

type Expression = Binary | Constant

function evaluate(expr:Expression) : number {
  switch(expr.kind) {
    case "constant": return expr.value
    case "binary":
      switch(expr.operator) {
        case "*": return evaluate(expr.left) * evaluate(expr.right)
        case "+": return evaluate(expr.left) + evaluate(expr.right)
      }
  }
}

let konst = (n:number) : Expression => 
  ({ kind:"constant", value:n })
let binary = (op:"+" | "*", l:Expression, r:Expression) : Expression => 
  ({ kind:"binary", operator:op, left:l, right:r })

let e = binary("*", binary("+", konst(20), konst(1)), konst(2))
let evalres = document.createElement("h3")
evalres.innerText = `Evaluating (20+1)*2: ${evaluate(e)}`
el.appendChild(evalres)


// --------------------------------------------------------------------------------------
// TASK #5: Implement well-typed join operation
// --------------------------------------------------------------------------------------

function join<T, P, K extends string>(a1: (T & Record<K, string>)[], a2: (P & Record<K, string>)[], key: K) : any[] {  
  let res = []
  for(var i = 0; i<a1.length; i++) {
    for(var j = 0; j<a2.length; j++) {
      let x: string = a1[i][key]
      let y: string = a2[j][key]
      if (x == y) {
        let r = { ...a1[i], ...a2[j] }
        res.push(r)
      }
    }        
  }  
  return res;
}

let r1 = 
  [ { name:"Tomas", bicycle:"Brompton" }, 
    { name:"Juliana", bicycle:"Beany" } ]
let r2 = 
  [ { name:"Tomas", language:"F#" },
    { name:"Juliana", language:"Scratch" } ]


let list = document.createElement("ul")
el.appendChild(list)

let r = join(r1, r2, "name")
r.forEach(el => 
  list.innerHTML += "<li>" + el.name + " rides " + 
    el.bicycle + " and likes " + el.language + "</li>")
