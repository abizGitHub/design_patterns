package combine

import Directory
import File
import FileSystemEntity
import homeDir

interface DisplayStrategy {
    fun display(fileSystemEntity: FileSystemEntity)
}

class ListDisplayStrategy : DisplayStrategy {
    override fun display(fileSystemEntity: FileSystemEntity) {
        println("----------------ls ${fileSystemEntity.getName()}------------------------------".take(40))
        when (fileSystemEntity) {
            is File -> openFile(fileSystemEntity)
            is Directory -> showDirContents(fileSystemEntity)
        }
    }

    private fun openFile(file: File) {
        println(file.getName())
    }

    private fun showDirContents(dir: Directory) {
        for (child in dir.getChildren()) {
            when (child) {
                is File -> println("${child.getName()}!")
                is Directory -> println("${child.getName()}[]")
            }
        }
    }
}

class TreeDisplayStrategy : DisplayStrategy {

    override fun display(fileSystemEntity: FileSystemEntity) {
        println("----------------tree ${fileSystemEntity.getName()}------------------------------".take(40))
        display(fileSystemEntity, 0)
    }

    private fun display(fileSystemEntity: FileSystemEntity, level: Int) {
        val spaces = "───".repeat(level)
        when (fileSystemEntity) {
            is File -> println("$spaces${fileSystemEntity.getName()}")
            is Directory -> {
                println("$spaces${fileSystemEntity.getName()} ..")
                fileSystemEntity.getChildren().forEach { display(it, level + 1) }
            }
            else -> "can't open.."
        }
    }
}

object FileManager {

    private var displayStrategy: DisplayStrategy? = null

    fun changeStrategy(displayStrategy: DisplayStrategy) {
        this.displayStrategy = displayStrategy
    }

    fun display(fileSystemEntity: FileSystemEntity) {
        displayStrategy?.display(fileSystemEntity)
    }
}

fun main() {

    FileManager.changeStrategy(ListDisplayStrategy())
    FileManager.display(homeDir)
    FileManager.display(homeDir.getChildren().first())
    FileManager.display(homeDir.getChildren().last())

    FileManager.changeStrategy(TreeDisplayStrategy())
    FileManager.display(homeDir)

}