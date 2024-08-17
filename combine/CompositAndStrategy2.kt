package combine

import Directory
import File
import FileSystemEntity
import homeDir

interface Displayable : FileSystemEntity {
    var strategy: DisplayStrategy

    fun display() {
        strategy.display(this)
    }

    fun changeDisplayMode(strategy: DisplayStrategy) {
        this.strategy = strategy
        getChildren()?.forEach {
            if (it is Displayable)
                it.changeDisplayMode(strategy)
        }
    }

    companion object {
        fun of(legacy: FileSystemEntity): Displayable {
            return when (legacy) {
                is File -> XFile(legacy.getName())
                is Directory -> {
                    val xDir = XDirectory(legacy.getName())
                    legacy.getChildren().forEach {
                        xDir.add(of(it))
                    }
                    xDir
                }

                else -> legacy as Displayable
            }
        }
    }
}

class XDirectory(
    name: String,
    override var strategy: DisplayStrategy = ListDisplayStrategy()
) : Directory(name), Displayable

class XFile(name: String, override var strategy: DisplayStrategy = ListDisplayStrategy()) : File(name),
    Displayable


fun main() {

    val homeXDir = Displayable.of(homeDir)
    homeXDir.display()

    homeXDir.changeDisplayMode(TreeDisplayStrategy())
    homeXDir.display()

}



