import java.io.File
import kotlin.math.sqrt

data class Quad(val title: String, val a: Double, val b: Double, val c: Double)

fun readFileName(): String {
    print("Enter the file name: ")
    val inputFileName = readLine()
    if (inputFileName != null) {
        return inputFileName
    }
    else {
        error("User didn't enter file name or entered file name is null")
    }
}

fun readFile(fileName: String): Array<Quad?> {
    val file = File(fileName)
    if (!file.exists()) {
        println("File not found: $fileName")
        return arrayOfNulls(0)
    }
    //try {
        val text = file.readLines()
        val quads = arrayOfNulls<Quad>(text.size)
        var i = 0
        while (i < text.size) {
            quads[i] = quadToString(text[i])
            i++
        }
        return quads
    //}
    //catch (ex: Exception) {
    //    println("failed to read file ${file} ")
    //    readFileName()
    //}
    //should never get here due to Exception
    return arrayOfNulls<Quad>(0)
}

fun quadToString(line: String): Quad {
    val parts = line.split(" ")
    try {
    if (parts.size == 4) {
        val title = parts[0]
        val a = parts[1].toDouble()
        val b = parts[2].toDouble()
        val c = parts[3].toDouble()
        return Quad(title, a, b, c)
    }
    }
    catch (ex: Exception) {
        val title = "BAD INPUT"
        val a = 0.0
        val b = 0.0
        val c = 0.0
        return Quad(title, a, b, c)
    }
    val title = "BAD INPUT"
    val a = 0.0
    val b = 0.0
    val c = 0.0
    return Quad(title, a, b, c)

}

fun ComputeAndPrint(quads: Array<Quad?>) {
    for (i in quads.indices) {
        var currentQuad = quads[i]
        try {
            var title = currentQuad?.title
            var a = currentQuad?.a
            var b = currentQuad?.b
            var c = currentQuad?.c
            if (title == "BAD INPUT") {
                println("BAD INPUT")
            }
            else if (title != "BAD INPUT") {
                val discriminant = b!! * b!! - 4 * a!! * c!!
                if (discriminant < 0) {
                    println("${title} (none,none)")
                }
                else {
                    var b2 = b.toDouble() * 2.0
                    var negb = b.toDouble() - b2
                    var root1 = (negb + sqrt(discriminant)) / (2 * a.toDouble())
                    val root2 = (negb - sqrt(discriminant)) / (2 * a.toDouble())
                    printResults(title!!, root1, root2)
                }
            }
        }
        catch (e: Exception) {
            println("Bad Input")
        }
    }
}


fun printResults(title: String, root1: Double, root2: Double) {
    print(title)
    print(" (${root1},${root2})\n")
}

fun main() {
    val fileName = readFileName()
    val quads = readFile(fileName)
    ComputeAndPrint(quads)
}