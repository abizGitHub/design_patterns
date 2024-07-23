package common

interface BrushStroke {
    fun putOnSurface(): String
}

class DryBrushStroke : BrushStroke {
    override fun putOnSurface() = "dry brush"
}

class WetBrushStroke : BrushStroke {
    override fun putOnSurface() = "wet brush"
}

class ScumblingBrushStroke : BrushStroke {
    override fun putOnSurface() = "scumbling brush"
}

class BrokenBrushStroke : BrushStroke {
    override fun putOnSurface() = "broken brush"
}
