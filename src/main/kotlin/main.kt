import kotlinx.cli.*
import java.lang.StringBuilder


fun main(args: Array<String>) {
    val parser = ArgParser("Brackets")

    // flags for center and edge brackets
    val middle by parser.option(
        ArgType.Boolean,
        shortName = "m",
        description = "Insert brackets in the middle of the string in case of even length"
    ).default(false)
    val edges by parser.option(
        ArgType.Boolean,
        shortName = "e",
        description = "Insert brackets around the edges of the string"
    ).default(false)

    //List of brackets separated by ';' in the order in which they will alternate in the resulting line
    val brackets by parser.option(
        ArgType.Choice(listOf("()", "[]", "{}"), { it }), shortName = "fb",
        description = "List of brackets"
    ).required().multiple().delimiter(";")

    parser.parse(args)
    val string = readLine()

    if (string == null) {
        println("Error: string is null")
        return
    }

    val len = string.length
    // Empty string
    if (len == 0) {
        if (edges) {
            println(brackets[0])
        } else {
            println("")
        }
        return
    }

    val module = brackets.size
    val answer = StringBuilder()
    var currentBracket = 0

    // edge bracket
    if (edges) {
        answer.append(brackets[currentBracket % module][0])
        currentBracket++
    }

    // first half of the string with brackets
    for (i in 0 until len / 2) {
        answer.append(string[i])
        answer.append(brackets[currentBracket % module][0])
        currentBracket++
    }

    // parsing cases for the middle of the string
    if (len % 2 == 1) {
        answer.append(string[len / 2])
    }
    currentBracket--

    // second half of the string with brackets
    for (i in (len + 1) / 2 until len) {
        answer.append(brackets[currentBracket % module][1])
        answer.append(string[i])
        currentBracket--
    }

    // edge bracket
    if (edges) {
        answer.append(brackets[currentBracket % module][1])
    }

    // remove unnecessary middle brackets in case of even string length
    if (len % 2 == 0 && !middle) {
        answer.deleteCharAt((answer.length - 1) / 2)
        answer.deleteCharAt(answer.length / 2)
    }

    println(answer.toString())
}