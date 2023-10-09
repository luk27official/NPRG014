class MyMath {
    static int factorial(Integer value) {
        value > 1 ? value * factorial(value - 1) : 1
    }
}

assert 479001600 == MyMath.factorial(12)

use(MyMath) {
    //this works like extension methods
    println 1.factorial()
    println 10.factorial()
}

println 'done'