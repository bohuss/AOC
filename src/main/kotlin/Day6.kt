class Day6 {
    val testAnswer1 = "5934"
    fun part1(input: List<String>): String {
        var ans = 0L
        val N = input.size
        var a = input[0].split(",").map { it.toInt() }.toIntArray()
        for(d in 0 until 80) {
            val x = ArrayList<Int>()
            for(y in a) {
                if(y == 0) {
                    x.add(6)
                    x.add(8)
                } else {
                    x.add(y - 1)
                }
            }
            a = x.toIntArray()
        }
        ans = a.size.toLong()

        return "$ans"
    }

    val testAnswer2 = "26984457539"
    fun part2(input: List<String>): String {
        var ans = 0L
        val N = input.size

        var b = input[0].split(",").map { it.toInt() }.toIntArray()
        var a = LongArray(10)
        for(x in b){
            a[x]++
        }
        for(d in 0 until 256) {
            val b = LongArray(10)


            b[6]+=a[0]
            b[8]+=a[0]
            for(y in 1 until b.size) {
                b[y - 1]+=a[y]
            }
            a = b
        }
        ans = a.sum().toLong()

        return "$ans"
    }

}
