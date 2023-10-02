def joe = [name : 'Joe', age : 83]
def jeff = [name : 'Jeff', age : 38]
def jess = [name : 'Jess', age : 33]

def process(person, code) {
    code.delegate = person    
    code.resolveStrategy = Closure.DELEGATE_FIRST //without this it would print "Noname" in the first process call
    //code.resolveStrategy = Closure.OWNER_FIRST //with this it will print "Noname" in both cases
    code.call()
    
    //person.with(code) //this is a shorthand for the above
}

name = "Noname"
process(joe, {println name})
process(jeff, {println "$name, $age"})


/*
class Person {
    final name = "Whatever"
    final greet = {println name}
}
process(joe, new Person().greet)
*/


//TASK Experiment with owner, delegate as well as with different resolution strategies