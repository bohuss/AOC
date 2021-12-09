val nYear = 2021
val nDay = 9
val day = Day9()
val taskLink = "https://adventofcode.com/$nYear/day/$nDay"
val inputLink = "$taskLink/input"
val submitLink = "$taskLink/answer"
val submit = true

val cookie = System.getenv("AOC_COOKIE")

val aocUtils = AOCUtils()

fun main() {

    val testInput = aocUtils.readTestInput()

    val myTestAnswer1 = if(testInput.isNullOrEmpty())day.testAnswer1 else {
        println("TEST 1")
        day.part1(testInput)
    }

    val ans1 = aocUtils.readInput().let {
        if(it.isEmpty()){
            aocUtils.getInputLines()
                .also {
                    aocUtils.saveInput(it, nDay)
                }
        }
        else {
            it
        }
    }.let {
        println("REAL 1")
        day.part1(it)
    }

    if( myTestAnswer1 == "${day.testAnswer1}") {
        aocUtils.submitAnswer(AOCUtils.Answer("1", "$ans1")) //
    } else {
        println("1 Returned $myTestAnswer1")
        println("1 Should be ${day.testAnswer1}")
        println("1 NOT SUBMITTING: $ans1")
    }


    val myTestAnswer2 = if(testInput.isNullOrEmpty())day.testAnswer2 else {
        println("TEST 2")
        day.part2(testInput)
    }


    val ans2 = aocUtils.readInput().let {
        if(it.isEmpty()){
            aocUtils.getInputLines()
                .also {
                    aocUtils.saveInput(it, nDay)
                }
        }
        else {
            it
        }
    }.let {
        println("REAL 2")
        day.part2(it)
    }

    if(myTestAnswer2 == "${day.testAnswer2}") {
        aocUtils.submitAnswer(AOCUtils.Answer("2", "$ans2")) //
    } else {
        println("2 Returned $myTestAnswer2")
        println("2 Should be ${day.testAnswer2}")
        println("2 NOT SUBMITTING: $ans2")
    }

}



