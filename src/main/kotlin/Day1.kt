class Day1 {
    val testAnswer1 = "7"
    fun part1(input: List<String>): String {
        var ans = 0
        var prev = 10000000
        for(line in input){
//        println(line)
            val now = line.toInt()
            if(now>prev)ans++
            prev = now
        }
        println("ANS1:")
        println(ans)
        return "$ans"
    }

    val testAnswer2 = "5"
    fun part2(input: List<String>): String {
        var ans = 0
        var prev = 1000000000
        for(i in 0 until input.size - 2){
            val now = input[i].toInt() + input[i+1].toInt() + input[i+2].toInt()
            if(now>prev)ans++
            prev = now
        }

        println("ANS2:")
        println(ans)
        return "$ans"
    }

}
