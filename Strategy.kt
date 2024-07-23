import common.*


class PaintingStrategy(var brush: BrushStroke, var fabric: Fabric) {

    fun changeBrush(brush: BrushStroke) {
        println("(${this.brush.putOnSurface()}) changed to (${brush.putOnSurface()})")
        this.brush = brush
    }

    fun changeFabric(fabric: Fabric) {
        println("(${this.fabric.getFiber()}) changed to (${fabric.getFiber()})")
        this.fabric = fabric
    }

    fun draw() = println("painting on ${fabric.getFiber()} using ${brush.putOnSurface()}")
}


fun main() {

    val strategy = PaintingStrategy(brush = BrokenBrushStroke(), fabric = Wool())
    strategy.draw()

    strategy.changeBrush(WetBrushStroke())
    strategy.changeFabric(Cotton())
    strategy.draw()

}