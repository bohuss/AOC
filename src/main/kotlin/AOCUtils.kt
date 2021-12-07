import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream
import java.lang.StringBuilder
import java.net.URI
import java.net.URLEncoder
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.time.Instant
import java.util.zip.GZIPInputStream

class AOCUtils {

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
        if(cookie.isNullOrBlank()){
            println("AOC_COOKIE not found")
            return emptyList()
        }
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
        if(cookie.isNullOrBlank()){
            println("AOC_COOKIE not found, answer: $answer")
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
        val output = decompressGZIP(response.body().inputStream())
        saveOutput((output?:"--no-output--") + "\n------\n" + getResult(output), nDay, answer.level)
        println("SUBMITTED: $answer")
        println(getResult(output))
    }


    fun readInput() = File("src", "inputs/$nDay.txt").readLines()

    fun readTestInput() = File("src", "inputs/$nDay.test").readLines()

    fun saveInput(lines: List<String>, nDay: Int) = File("src", "inputs/$nDay.txt")
        .writeText(lines.joinToString("\n"))

    fun saveOutput(s: String, nDay: Int, level: String) = File("src", "outputs/$nYear-$nDay-$level-${Instant.now()}.txt")
        .writeText(s)

    fun getResult(s: String?): String {
        if(s == null) {
            return "--nothing--"
        }
        var p = s.indexOf("<main>")
        p = s.indexOf("<article>", p)
        p = s.indexOf("<p>", p)
        p += "<p>".length
        val start = p
        p = s.indexOf("</p>", p)
        val text = s.substring(start, p)
        return clean(text)
    }

    fun clean(text: String): String {
        var add = true
        val sb = StringBuilder()
        for(c in text) {
            when(c) {
                '<' -> {
                    add = false
                }
                '>' -> {
                    add = true
                }
                else -> {
                    if(add) {
                        sb.append(c)
                    }
                }
            }

        }
        return sb.toString()
    }


}
