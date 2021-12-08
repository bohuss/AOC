class Day8 {
    val testAnswer1 = "26"
    fun part1(input: List<String>): String {
        var ans = 0L
        for(s in input) {
            val(_, n) = s.split("|")
            val num = n.split("[ ]+".toRegex()).filter { it.isNotBlank() }
            ans += num.count{it.length in listOf(2,3,4,7)}
        }
        return "$ans"
    }


    val testAnswer2 = "61229"
    fun part2(input: List<String>): String {

        val dig = mapOf<String, Int>(
            "abcefg" to 0,
            "cf" to 1,
            "acdeg" to 2,
            "acdfg" to 3,
            "bcdf" to 4,
            "abdfg" to 5,
            "abdefg" to 6,
            "acf" to 7,
            "abcdefg" to 8,
            "abcdfg" to 9,
        )

        var ans = 0L
        for(s in input) {
            val(d, n) = s.split("|")
            val digits = d.split("[ ]+".toRegex()).filter { it.isNotBlank() }
            val num = n.split("[ ]+".toRegex()).filter { it.isNotBlank() }

            var all = "abcdefg"

            var mapping: HashMap<Char, Char>?

            do {
                all = all.shuffle()
                mapping = checkMapping(all, digits, dig)
            } while(mapping == null)

            var now = 0
            for(x in num) {
                now *= 10
                val y = trans(x, mapping).toCharArray().sorted().joinToString("")
                now += dig[y]!!
            }
            ans += now
        }

        return "$ans"
    }

    private fun checkMapping(all: String, digits: List<String>, dg: Map<String, Int>): HashMap<Char, Char>? {
        val mapping = HashMap<Char, Char>()
        for(i in all.indices) {
            mapping['a'+i] = all[i]
        }
        return if( digits.all {dg.containsKey(trans(it, mapping))}) mapping else null
    }

    private fun trans(s: String, mapping: java.util.HashMap<Char, Char>): String {
        val a = ArrayList<Char>()
        for(c in s) {
            a.add(mapping[c]!!)
        }
        a.sort()
        return a.joinToString("")
    }
}

fun String.shuffle(): String {
    val t = this.toCharArray()
    t.shuffle()
    return t.joinToString("")
}
