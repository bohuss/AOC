data class Ticket (
    var id: Int,
    var lines: Array<IntArray> = Array(5){IntArray(5)},
    var seen: Array<BooleanArray> = Array(5){BooleanArray(5)},
    var r: IntArray = IntArray(5),
    var c: IntArray = IntArray(5),
    var isWinning: Boolean = false
    ) {
    fun unmarkedSum(): Int {
        var ans = 0
        for(i in 0 until 5) {
            for (j in 0 until 5) {
                if (seen[i][j] == false) {
                    ans += lines[i][j]
                }
            }
        }
        return ans
    }

    fun mark(x: Int) {
        for(i in 0 until 5){
            for(j in 0 until 5) {
                if(lines[i][j] == x) {
                    seen[i][j] = true
                    r[i]++
                    c[j]++
                    if(r[i] == 5) isWinning = true
                    if(c[j] == 5) isWinning = true
                }
            }
        }
    }
}
class Day4 {
    val testAnswer1 = "4512"
    fun part1(input: List<String>): String {
        var ans = 0L
        val nums = input[0].split(",").map { it.toInt() }

        val b = input.drop(1).filter{it.isNotBlank()}
        val n = b.size / 5
        val t = Array(n){Ticket(it)}
        for(i in 0 until n){
            t[i].id = i
            for(j in 0 until 5) {
                t[i].lines[j] = b[i*5 + j].split("[ ]+".toRegex()).filter{it.isNotBlank()}.map { it.toInt() }.toIntArray()
            }

        }
        var lastNumber = -1
        var winner: Ticket? = null
        for(x in nums) {
            lastNumber = x

            for(tt in t) {
                tt.mark(x)
                if(tt.isWinning){
                    winner = tt
                    break
                }
            }
            if(winner != null) {
                break
            }
        }

        ans = (winner!!.unmarkedSum() * lastNumber).toLong()


        return "$ans"
    }

    val testAnswer2 = "1924"
    fun part2(input: List<String>): String {
        var ans = 0L
        val nums = input[0].split(",").map { it.toInt() }

        val b = input.drop(1).filter{it.isNotBlank()}
        val n = b.size / 5
        val t = Array(n){Ticket(it)}
        for(i in 0 until n){
            t[i].id = i
            for(j in 0 until 5) {
                t[i].lines[j] = b[i*5 + j].split("[ ]+".toRegex()).filter{it.isNotBlank()}.map { it.toInt() }.toIntArray()
            }

        }
        var lastNumber = -1
        var winner: Ticket? = null
        var wins = 0
        for(x in nums) {
            lastNumber = x

            for(tt in t) {
                if(tt.isWinning)continue
                tt.mark(x)
                if(tt.isWinning){
                    wins++
                    if(wins == n) {
                        winner = tt
                        break
                    }
                }
            }
            if(winner != null) {
                break
            }
        }

        ans = (winner!!.unmarkedSum() * lastNumber).toLong()


        return "$ans"
    }

}
