class Day2 {
    val testAnswer1 = "150"
    fun part1(input: List<String>): String {
        var ans = 0

        var d = 0
        var h = 0
        for(s in input){
            val x = s.split(" ")
            when(x[0][0]){
                'f' -> h += x[1].toInt()
                'd' -> d += x[1].toInt()
                'u' -> d -= x[1].toInt()
            }
        }
        ans = d * h
        return "$ans"
    }

    val testAnswer2 = "900"
    fun part2(input: List<String>): String {
        var ans = 0

        var d = 0
        var h = 0
        var a = 0

        for(s in input){
            val x = s.split(" ")
            when(x[0][0]){
                'f' -> {
                    h += x[1].toInt()
                    d += a * x[1].toInt()
                }
                'd' -> a += x[1].toInt()
                'u' -> a -= x[1].toInt()
            }
        }
        ans = d * h
        return "$ans"
    }

}
