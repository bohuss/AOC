class Day5 {
    val testAnswer1 = "5"
    fun part1(input: List<String>): String {
        var ans = 0L
        val N = input.size
        val p = Array(1000){IntArray(1000)}
        for(s in input) {
            val a =  s.split("[ ,>-]+".toRegex()).map { it.toInt() }
            if(a[0] != a[2] && a[1] != a[3])continue
            for(i in a[0] .. a[2]){
                for(j in a[1]..a[3])
                    p[i][j]++
            }
            for(i in a[0] downTo a[2]){
                for(j in a[1] downTo a[3])
                    p[i][j]++
            }
        }
        for(x in p){
            ans += x.count{it>=2}
        }


        return "$ans"
    }

    val testAnswer2 = "12"
    fun part2(input: List<String>): String {
        var ans = 0L
        val N = input.size
        val p = Array(1000){IntArray(1000)}
        for(s in input) {
            val a =  s.split("[ ,>-]+".toRegex()).map { it.toInt() }
            if(a[0] == a[2] || a[1] == a[3]) {

                for (i in a[0]..a[2]) {
                    for (j in a[1]..a[3])
                        p[j][i]++
                }
                for (i in a[0] downTo a[2]) {
                    for (j in a[1] downTo a[3])
                        p[j][i]++
                }
            }
            else {
                if(a[0] - a[2] == a[1] - a[3]) {
                    for(i in 0 .. kotlin.math.abs(a[0]- a[2])) {
                        val y = if(a[1] < a[3])a[1] + i else a[3]+i
                        val x = if(a[0] < a[2])a[0] + i else a[2]+i
                        if(x >= 0 && y >= 0 && x < p.size && y < p.size){
                            p[y][x]++
                        }
                    }
                }
                else if(a[0] - a[2] == a[3] - a[1]) {
                    if(a[1] > a[3]){
                        for(i in 0 .. kotlin.math.abs(a[0]- a[2])) {
                            val y = a[3]+i
                            val x = a[2]-i
                            if(x >= 0 && y >= 0 && x < p.size && y < p.size){
                                p[y][x]++
                            }
                        }
                    } else {
                        for(i in 0 .. kotlin.math.abs(a[0]- a[2])) {
                            val y = a[1] + i
                            val x = a[0] - i
                            if(x >= 0 && y >= 0 && x < p.size && y < p.size){
                                p[y][x]++
                            }
                        }
                    }
                }
            }
        }
        for(x in p){
            ans += x.count{it>=2}
        }
        return "$ans"
    }

}
