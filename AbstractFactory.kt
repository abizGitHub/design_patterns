import common.*

interface PaintingFactory {
    fun getColor(): Color
    fun getBrushStroke(): BrushStroke
    fun getFabric(): Fabric
    fun draw() = "${getColor().shine()} on ${getFabric().getFiber()} using ${getBrushStroke().putOnSurface()}"
}

object VanGoghPaintingFactory : PaintingFactory {
    override fun getColor() = Yellow()

    override fun getBrushStroke() = BrokenBrushStroke()

    override fun getFabric() = Silk()
}

object PicassoPaintingFactory : PaintingFactory {
    override fun getColor() = Blue()

    override fun getBrushStroke() = DryBrushStroke()

    override fun getFabric() = Wool()
}

object CustomPaintingFactory {
    enum class Style { MODERN, EXPRESSIONISM }

    fun withStyle(s: Style): PaintingFactory = when (s) {
        Style.MODERN -> PicassoPaintingFactory
        Style.EXPRESSIONISM -> VanGoghPaintingFactory
    }
}

fun main() {
    VanGoghPaintingFactory
        .draw()
        .also { println(it) }

    CustomPaintingFactory
        .withStyle(CustomPaintingFactory.Style.MODERN)
        .draw()
        .also { println(it) }
}

