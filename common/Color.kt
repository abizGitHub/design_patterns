package common

interface Color {
    fun shine(): String
}

class Blue : Color {
    override fun shine() = "blue is shining"
}

class Black : Color {
    override fun shine() = "black is shining"
}

class Red : Color {
    override fun shine() = "red is shining"
}

class Green : Color {
    override fun shine() = "green is shining"
}

class Yellow : Color {
    override fun shine() = "yellow is shining"
}