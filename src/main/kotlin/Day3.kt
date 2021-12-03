class Day3 {
    val testAnswer1 = "198"
    fun part1(input: List<String>): String {
        var ans = 0L

        val N = input[0].length
        val c1 = IntArray(N)
        val c0 = IntArray(N)
        for(s in input){
            for(i in 0 until N) {
                if(s[i] == '0')c0[i]++
                else c1[i]++
            }
        }

        var x = 0L
        var y = 0L
        for(i in 0 until N) {
            if(c1[i] >= c0[i]){
                x = x + (1L shl (N - i- 1))
            } else {
                y = y + (1L shl (N -i-1))
            }
        }
        ans = x * y
        return "$ans"
    }

    val testAnswer2 = "230"
    fun part2(input: List<String>): String {
        var ans = 0L
        var a = ArrayList<String>()
        var b = ArrayList<String>()

        for(s in input){
            a.add(s)
            b.add(s)
        }
        val N = input[0].length
        for(j in 0 until N) {
            val t = ArrayList<String>()
            var c1 = 0
            var c0 = 0
            for(s in a) {
                if(s[j] == '0')c0++
                else c1++
            }
            if(c1>=c0) {
                for(s in a) {
                    if(s[j]=='1')t.add(s)
                }
            } else {
                for(s in a) {
                    if(s[j]=='0')t.add(s)
                }
            }
            a = t
        }

        for(j in 0 until N) {
            val t = ArrayList<String>()
            var c1 = 0
            var c0 = 0
            for(s in b) {
                if(s[j] == '0')c0++
                else c1++
            }
            if(c0<=c1) {
                for(s in b) {
                    if(s[j]=='0')t.add(s)
                }
            } else {
                for(s in b) {
                    if(s[j]=='1')t.add(s)
                }
            }
            b = t
            if(b.size==1)break
        }

        var x = 0L
        var y = 0L
        for(i in 0 until N) {
            if(a.single()[i]=='1'){
                x = x + (1L shl (N - i- 1))
            }
            if(b.single()[i]=='1'){
                y = y + (1L shl (N -i-1))
            }
        }
        ans = x * y

        return "$ans"
    }

}
