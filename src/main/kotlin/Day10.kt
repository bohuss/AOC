import java.util.Stack

class Day10 {
    val testAnswer1 = "26397"
    private val e = mapOf(')' to 3,']' to 57,'}' to 1197,'>' to 25137)
    private val o = "([{<"
    private val c = ")]}>"

fun part1(input: List<String>): String {
        var ans = 0L
        for(s in input) {
            val stack = Stack<Char>()
            for(x in s) {
                if(x in o) {
                    stack.add(x)
                } else {
                    if(o.indexOf(stack.peek()) != c.indexOf(x)){
                        ans += e[x]!!
                        break
                    }
                    stack.pop()
                }
            }
        }
        return "$ans"
    }


    val testAnswer2 = "288957"
    fun part2(input: List<String>): String {
        var ans = 0L
        val all = ArrayList<Long>()
        for(s in input) {
            val stack = Stack<Char>()
            var ok = true
            var now = 0L
            for(x in s) {
                if(x in o) {
                    stack.add(x)
                } else {

                    if(stack.isEmpty() || o.indexOf(stack.peek()) != c.indexOf(x)){
                        ok =false
                        break
                    }
                    stack.pop()
                }
            }
            if(ok) {
                while(stack.isNotEmpty()){
                    val x = stack.pop()
                    now *= 5
                    now += o.indexOf(x) + 1
                }
                all.add(now)
            }

        }
        all.sort()
        ans = all[all.size/2]
        return "$ans"
    }


}
