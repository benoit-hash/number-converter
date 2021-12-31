package converter

import java.math.BigDecimal
import java.math.BigInteger
import kotlin.system.exitProcess

var baseFrom = BigInteger.valueOf(10)
var baseTo = BigInteger.valueOf(10)

fun menu() {
    println("Enter two numbers in format: {source base} {target base} (To quit type /exit)")
    val commande = readLine()!!.toString()
    when (commande) {
        "/exit" -> exitProcess(1)
        else -> {
            baseFrom = BigInteger(commande.split(" ")[0])
            baseTo = BigInteger(commande.split(" ")[1])
            subMenu()
        }
    }
}
fun subMenu() {
    println("Enter number in base $baseFrom to convert to base $baseTo (To go back type /back)")
    val commande = readLine()!!.toString()
    when (commande) {
        "/back" -> menu()
        else -> {
            if(commande.contains('.')) {
                if (baseFrom == BigInteger.valueOf(10)) {
                    println("Conversion result: ${convFrom(commande.split('.')[0])}${convFromFract("0.${commande.split('.')[1]}")}")
                } else if (baseTo == BigInteger.valueOf(10)) {
                    println("Conversion result: ${convTo(commande.split('.')[0])}${convToFract(commande.split('.')[1])}")
                } else {
                    println("Conversion result: ${convFrom(convTo(commande.split('.')[0]))}.${convFromFract("0." + convToFract(commande.split('.')[1]))}")
                }
            } else {
                if (baseFrom == BigInteger.valueOf(10)) {
                    println("Conversion result: ${convFrom(commande)}")
                } else if (baseTo == BigInteger.valueOf(10)) {
                    println("Conversion result: ${convTo(commande)}")
                } else {
                    println("Conversion result: ${convFrom(convTo(commande))}")
                }
            }
            subMenu()
        }
    }
}

fun convTo(nombre: String): String {
    var res = BigInteger.valueOf(0)
    var power = BigInteger.valueOf(1)
    for (i in nombre.length downTo 1) {
        when (nombre[i - 1]) {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'-> res += BigInteger(nombre[i - 1].toString()) * power
            'A', 'a' -> res += BigInteger.valueOf(10) * power
            'B', 'b' -> res += BigInteger.valueOf(11) * power
            'C', 'c' -> res += BigInteger.valueOf(12) * power
            'D', 'd' -> res += BigInteger.valueOf(13) * power
            'E', 'e' -> res += BigInteger.valueOf(14) * power
            'F', 'f' -> res += BigInteger.valueOf(15) * power
            'G', 'g' -> res += BigInteger.valueOf(16) * power
            'H', 'h' -> res += BigInteger.valueOf(17) * power
            'I', 'i' -> res += BigInteger.valueOf(18) * power
            'J', 'j' -> res += BigInteger.valueOf(19) * power
            'K', 'k' -> res += BigInteger.valueOf(20) * power
            'L', 'l' -> res += BigInteger.valueOf(21) * power
            'M', 'm' -> res += BigInteger.valueOf(22) * power
            'N', 'n' -> res += BigInteger.valueOf(23) * power
            'O', 'o' -> res += BigInteger.valueOf(24) * power
            'P', 'p' -> res += BigInteger.valueOf(25) * power
            'Q', 'q' -> res += BigInteger.valueOf(26) * power
            'R', 'r' -> res += BigInteger.valueOf(27) * power
            'S', 's' -> res += BigInteger.valueOf(28) * power
            'T', 't' -> res += BigInteger.valueOf(29) * power
            'U', 'u' -> res += BigInteger.valueOf(30) * power
            'V', 'v' -> res += BigInteger.valueOf(31) * power
            'W', 'w' -> res += BigInteger.valueOf(32) * power
            'X', 'x' -> res += BigInteger.valueOf(33) * power
            'Y', 'y' -> res += BigInteger.valueOf(34) * power
            'Z', 'z' -> res += BigInteger.valueOf(35) * power
        }
        power *= baseFrom
    }
    return res.toString()
}

fun convToFract(nombre: String): String {
    var res = BigDecimal.valueOf(0)
    var power: BigDecimal = BigDecimal.valueOf(1).setScale(5) / BigDecimal(baseFrom)
    for (i in 1..nombre.length) {
        when (nombre[i - 1]) {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'-> res += BigDecimal(nombre[i - 1].toString()) * power
            'A', 'a' -> res += BigDecimal.valueOf(10) * power
            'B', 'b' -> res += BigDecimal.valueOf(11) * power
            'C', 'c' -> res += BigDecimal.valueOf(12) * power
            'D', 'd' -> res += BigDecimal.valueOf(13) * power
            'E', 'e' -> res += BigDecimal.valueOf(14) * power
            'F', 'f' -> res += BigDecimal.valueOf(15) * power
            'G', 'g' -> res += BigDecimal.valueOf(16) * power
            'H', 'h' -> res += BigDecimal.valueOf(17) * power
            'I', 'i' -> res += BigDecimal.valueOf(18) * power
            'J', 'j' -> res += BigDecimal.valueOf(19) * power
            'K', 'k' -> res += BigDecimal.valueOf(20) * power
            'L', 'l' -> res += BigDecimal.valueOf(21) * power
            'M', 'm' -> res += BigDecimal.valueOf(22) * power
            'N', 'n' -> res += BigDecimal.valueOf(23) * power
            'O', 'o' -> res += BigDecimal.valueOf(24) * power
            'P', 'p' -> res += BigDecimal.valueOf(25) * power
            'Q', 'q' -> res += BigDecimal.valueOf(26) * power
            'R', 'r' -> res += BigDecimal.valueOf(27) * power
            'S', 's' -> res += BigDecimal.valueOf(28) * power
            'T', 't' -> res += BigDecimal.valueOf(29) * power
            'U', 'u' -> res += BigDecimal.valueOf(30) * power
            'V', 'v' -> res += BigDecimal.valueOf(31) * power
            'W', 'w' -> res += BigDecimal.valueOf(32) * power
            'X', 'x' -> res += BigDecimal.valueOf(33) * power
            'Y', 'y' -> res += BigDecimal.valueOf(34) * power
            'Z', 'z' -> res += BigDecimal.valueOf(35) * power
        }
        power /= BigDecimal(baseFrom)
    }
    return res.toString().substringAfter('.')
}

fun convFrom(nombre: String): String {
    val a = BigInteger(nombre)
    var res = ""
    var quot = a / baseTo
    var rest = a % baseTo
    when (rest.toInt()) {
        in 0..9 -> res = rest.toString() + res
        else -> res = Char('a'.code.toByte() - 10 + rest.toInt()) + res
    }
    while (quot > BigInteger.valueOf(0)) {
        rest = quot % baseTo
        quot /= baseTo
        when (rest.toInt()) {
            in 0..9 -> res = rest.toString() + res
            else -> res = Char('a'.code.toByte() - 10 + rest.toInt()) + res
        }
    }
    return res
}

fun convFromFract(nombre: String): String {
    val a = BigDecimal(nombre)
    var res = ""
    var count = 1
    var quot = a * BigDecimal(baseTo)
    var rest = quot % BigDecimal(baseTo)
    while (count <= 5) {
        when (rest.toInt()) {
            in 0..9 -> res += rest.toInt()
            else -> res += Char('a'.code.toByte() - 10 + rest.toInt())
        }
        //print("rest: $rest")
        quot *= BigDecimal(baseTo)
        rest = quot % BigDecimal(baseTo)
        count ++
    }
    return res.substringAfter('.')
}

fun main() {
    menu()
}
