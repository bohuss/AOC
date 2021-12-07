import kotlin.math.absoluteValue

class Day7 {
    val testAnswer1 = "37"
    fun part1(input: List<String>): String {
        val a = input[0].split(",").map{it.toInt()}.toIntArray()
        a.sort()
        val med = a[a.size / 2]
        val ans = a.sumOf { (it - med).absoluteValue }
        return "$ans"
    }


    val testAnswer2 = "168"
    fun part2(input: List<String>): String {
        val f = LongArray(10000)
        for(i in 1 until f.size){
            f[i] = f[i - 1] + i
        }
        var ans = 0L
        val a = input[0].split(",").map{it.toInt()}.toIntArray()
        ans = Int.MAX_VALUE.toLong()
        (0 until (a.maxOrNull()?:0)).forEach { x ->
            ans = ans.coerceAtMost(a.sumOf { f[(it - x).absoluteValue] })
        }

        return "$ans"
    }

}
