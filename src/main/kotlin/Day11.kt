class Day11 {
    private val dx = arrayOf(-1,0,1,0,-1,-1,1,1)
    private val dy = arrayOf(0,-1,0,1,-1,1,-1,1)

    val testAnswer1 = "1656"
    fun part1(input: List<String>): String {
        var ans = 0L
        val R = input.size
        val C = input.size
        val p = Array(R) { y->
            IntArray(C) { x-> input[y][x] - '0'}
        }

        for(step in 1 .. 100) {
            val exploding = HashSet<String>()
            val exploded = HashSet<String>()
            for(i in 0 until R) {
                for (j in 0 until C) {
                    p[i][j]++
                    if(p[i][j]>=10) {
                        exploding.add("$i#$j")
                    }
                }
            }
            while(exploding.isNotEmpty()){
                val now = exploding.random()
                exploding.remove(now)
                if(now in exploded)continue
                exploded.add(now)
                val (ii, jj) = now.split("#").map { it.toInt() }
                for(d in 0 until 8) {
                    val x = jj + dx[d]
                    val y = ii + dy[d]
                    if(x<0 || x>=C)continue
                    if(y<0 || y>=R)continue
                    p[y][x]++
                    if(p[y][x]>=10) {
                        if("$y#$x" !in exploded) {
                            exploding.add("$y#$x")
                        }
                    }
                }
            }
            for ((i, j) in exploded.map{it.split("#").map { it.toInt() }}) {
                p[i][j] = 0
            }
            ans += exploded.size
        }

        return "$ans"
    }


    val testAnswer2 = "195"
    fun part2(input: List<String>): String {
        var ans = 0L
        val R = input.size
        val C = input.size
        val p = Array(R) { y->
            IntArray(C) { x-> input[y][x] - '0'}
        }

        for(step in 1 .. 1000000) {
            val exploding = HashSet<String>()
            val exploded = HashSet<String>()
            for(i in 0 until R) {
                for (j in 0 until C) {
                    p[i][j]++
                    if(p[i][j]>=10) {
                        exploding.add("$i#$j")
                    }
                }
            }
            while(exploding.isNotEmpty()){
                val now = exploding.random()
                exploding.remove(now)
                if(now in exploded)continue
                exploded.add(now)
                val (i, j) = now.split("#").map { it.toInt() }
                for(d in 0 until 8) {
                    val x = j + dx[d]
                    val y = i + dy[d]
                    if(x<0 || x>=C)continue
                    if(y<0 || y>=R)continue
                    p[y][x]++
                    if(p[y][x]>=10) {
                        if("$y#$x" !in exploded) {
                            exploding.add("$y#$x")
                        }
                    }
                }
            }
            exploded.map{it.split("#").map { it.toInt() }}.forEach {
                (i, j) -> p[i][j] = 0
            }
            if(exploded.size == R * C) {
                ans = step.toLong()
                break
            }
        }

        return "$ans"
    }


}
