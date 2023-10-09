String.metaClass.backToFront = {->
    delegate[-1..0]
}

println 'cimanyd si yvoorG'.backToFront()



//TASK define a starTrim() method to surround the original trimmed string with '*' 
//watch out that this might be dangerous 
String.metaClass.starTrim = {->
    return "*${delegate.trim()}*"
}

def s = '   core   '
assert '*core*' == s.starTrim()

println 'done'


















