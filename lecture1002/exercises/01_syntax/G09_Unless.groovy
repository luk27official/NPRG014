//TASK Define the unless (aka if not) method

def unless(condition, code) {
    if(!condition) code.call() //or just code()
}

unless(1 > 5) {
    println "Condition not satisfied!"
    def value = 10
    println "Value is $value"
}

println 'done'