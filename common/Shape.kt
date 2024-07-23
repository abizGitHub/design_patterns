package common

interface Shape {
    fun drawShape(): String
}

class Circle : Shape {
    override fun drawShape() = "circle"
}

class Square : Shape {
    override fun drawShape() = "square"
}

class Rectangle : Shape {
    override fun drawShape() = "rectangle"
}


