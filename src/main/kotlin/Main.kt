import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream
import java.net.URI
import java.net.URLEncoder
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.util.zip.GZIPInputStream

val year = 2021
val day = 3
val taskLink = "https://adventofcode.com/$year/day/$day"
val inputLink = "$taskLink/input"
val submitLink = "$taskLink/answer"
val submit = false

//Cookie: _ga=GA1.2.1310892297.1638293400; _gid=GA1.2.408434988.1638293400; session=53616c7465645f5f9aea5cfaa12feb4de663def3faf8a7c997ef0b6e7e93f57b3cac4703400b81a751ad989eea4571d5; _gat=1
//Cookie: _ga=GA1.2.895313618.1638307751; _gid=GA1.2.1644388470.1638307751; _gat=1; session=53616c7465645f5f51bd8029eb9466f6f1fd27f915c2f8962ebb7c57c99f655e156276666a9cd96423c565ec163cadd2
val cookie = "_ga=GA1.2.1310892297.1638293400; _gid=GA1.2.408434988.1638293400; session=53616c7465645f5f51bd8029eb9466f6f1fd27f915c2f8962ebb7c57c99f655e156276666a9cd96423c565ec163cadd2; _gat=1"

val dx = arrayOf(0, 1, 0, -1)
val dy = arrayOf(1, 0, -1, 0)

data class Answer (
    val level: String,
    val answer: String
)


fun String.utf8(): String = URLEncoder.encode(this, "UTF-8")

fun formData(data: Map<String, String>): HttpRequest.BodyPublisher? {

    val res = data.map {(k, v) -> "${(k.utf8())}=${v.utf8()}"}
        .joinToString("&")

    return HttpRequest.BodyPublishers.ofString(res)
}

//decompresser
fun decompressGZIP(inputStream: InputStream?): String? {
    val bodyStream: InputStream = GZIPInputStream(inputStream)
    val outStream = ByteArrayOutputStream()
    val buffer = ByteArray(4096)
    var length: Int
    while (bodyStream.read(buffer).also { length = it } > 0) {
        outStream.write(buffer, 0, length)
    }
    return String(outStream.toByteArray())
}

fun getInputLines(): List<String> {
    val client = HttpClient.newBuilder().build();
    val request = HttpRequest.newBuilder()
        .uri(URI.create(inputLink))
        .header("Cookie", cookie)
        .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8")
        .header("Accept-Encoding", "gzip, deflate, br")
        .header("Accept-Language", "en-US,en;q=0.5")
        .header("Cache-Control", "no-cache")
        .header("Origin", "https://adventofcode.com")
        .build()
    val response = client.send(request, HttpResponse.BodyHandlers.ofByteArray())
//    println(request)
    print("Input Status: ")
    println(response.statusCode())
//    println(response.headers())
//    println(decompressGZIP(response.body().inputStream()))
    return decompressGZIP(response.body().inputStream())!!.split("\n").filter { it.isNotBlank() }
}

fun submitAnswer(answer: Answer) {
    val values = mapOf("level" to answer.level, "answer" to answer.answer)
    if(!submit) {
        println("SUBMIT IS DISABLED: $answer")
        return
    }

    val client = HttpClient.newBuilder().build();
    val request = HttpRequest.newBuilder()
        .uri(URI.create(submitLink))
        .POST(formData(values))
        .header("Content-Type", "application/x-www-form-urlencoded")
        .header("Cookie", cookie)
        .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8")
        .header("Accept-Encoding", "gzip, deflate, br")
        .header("Accept-Language", "en-US,en;q=0.5")
        .header("Cache-Control", "no-cache")
        .header("Origin", "https://adventofcode.com")
        .build()
    val response = client.send(request, HttpResponse.BodyHandlers.ofByteArray())
//    println(request)
//    println("Submitted answer ${answer.answer} for level ${answer.level} ans the response is:")
    print("Submit Status: ")
    println(response.statusCode())
//    println(response.headers())
    println(decompressGZIP(response.body().inputStream()))
    println("SUBMITTED: $answer")
}


fun readInput() = File("src", "$day.txt").readLines()

fun readTestInput() = File("src", "inputs/$day.test").readLines()

fun main() {


    val testInput = readTestInput()

    val day = Day3()

    print("TEST ")
    val myTestAnswer1 = day.part1(testInput)
    val ans1 = day.part1(getInputLines())
//    val ans1 = part1(readInput())
    if( myTestAnswer1 == "${day.testAnswer1}") {
        submitAnswer(Answer("1","$ans1")) //
    } else {
        println("1 Returned $myTestAnswer1")
        println("1 Should be ${day.testAnswer1}")
    }

    print("TEST ")
    val myTestAnswer2 = day.part2(testInput)
    val ans2 = day.part2(getInputLines())
//    val ans2 = part2(readInput())
    if(myTestAnswer2 == "${day.testAnswer2}") {
        submitAnswer(Answer("2","$ans2")) //
    } else {
        println("2 Returned $myTestAnswer2")
        println("2 Should be ${day.testAnswer2}")
    }


}
