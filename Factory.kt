import common.*

object ColorFactory {
    fun createColor(c: String): Color = when (c) {
        "blue" -> Blue()
        "black" -> Black()
        "red" -> Red()
        "green" -> Green()
        else -> error("color $c doesn't exist!")
    }
}

fun main() {
    ColorFactory.createColor("red")
        .shine()
        .also { println(it) }
}