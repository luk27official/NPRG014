class MyIndentingBuilder {

    def indent = 4

    def invokeMethod(String methodName, args) {
        def result = '';
        if (args.size() > 0) {
            Closure closure = args[0]
            indent += 4
            closure.delegate = this
            result = closure()
        }
        indent -= 4
        return "<$methodName>\n${' ' * indent}$result\n${' ' * (indent - 4)}</$methodName>"
    }
}

//TASK manipulate the value in "indent" so as the generated xml is nicely indented

def doc = new MyIndentingBuilder().html {
    body {
        div {
            "content"
        }
    }
}

println doc