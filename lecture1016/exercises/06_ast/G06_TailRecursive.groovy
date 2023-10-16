@groovy.transform.TailRecursive
private def factorial(BigDecimal n, BigDecimal result) {
    if (n == 1) return result
    else factorial(n - 1, n * result)
}

def fact(BigDecimal n) {
    factorial(n, 1)
}

println fact(5)

//TASK Make the function tail recursive so that it can pass the following line
println fact(10000)