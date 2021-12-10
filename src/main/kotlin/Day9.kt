import java.util.*

class Day9 {
    val testAnswer1 = "15"
    private val dx = arrayOf(-1,0,1,0)
    private val dy = arrayOf(0, -1,0,1)
    fun part1(input: List<String>): String {
        var ans = 0L
        val R = input.size
        val C = input[0].length
        val p = Array(R) { row ->
            IntArray(C){input[row][it]-'0'}
        }
        for(i in 0 until R) {
            for(j in 0 until C) {
                var ok = true
                for(d in 0 until 4) {
                    val x = j + dx[d]
                    val y = i + dy[d]
                    if(x < 0 || x >= C)continue
                    if(y < 0 || y >= R)continue
                    if(p[y][x] <= p[i][j])ok = false
                }
                if(ok){
                    ans += p[i][j] + 1
                }
            }
        }
        return "$ans"
    }


    val testAnswer2 = "1134"
    fun part2(input: List<String>): String {
        var ans = 0L
        val R = input.size
        val C = input[0].length
        val p = Array(R) { row ->
            IntArray(C){input[row][it]-'0'}
        }
        val b = Array(R) { row ->
            IntArray(C)
        }
        var c = 0
        val sizes = ArrayList<Int>()
        for(i in 0 until R) {
            for (j in 0 until C) {
                if(b[i][j] > 0)continue
                c++
                var now = 0
                val stack = Stack<Int>()
                stack.add(i)
                stack.add(j)
                while(stack.isNotEmpty()){
                    val xx = stack.pop()
                    val yy = stack.pop()

                    if(b[yy][xx] > 0)continue
                    if(p[yy][xx] == 9)break
                    b[yy][xx] = c
                    now++
                    for(d in 0 until 4) {
                        val x = xx + dx[d]
                        val y = yy + dy[d]
                        if(x < 0 || x >= C)continue
                        if(y < 0 || y >= R)continue
                        if(p[y][x] < 9 && b[y][x] == 0) {
                            stack.add(y)
                            stack.add(x)
                        }
                    }
                }
                sizes.add(now)
            }
        }
        sizes.sortDescending()
        ans = sizes[0].toLong() * sizes[1] * sizes[2]
        return "$ans"
    }


}
