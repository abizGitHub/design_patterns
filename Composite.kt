interface FileSystemEntity {
    fun getName(): String
    fun getChildren(): MutableList<FileSystemEntity>? = null
    fun show(): String
}

open class File(private val name: String) : FileSystemEntity {
    override fun getName() = name
    override fun getChildren() = null
    override fun show() = "$name!"
}

open class Directory(private val name: String) : FileSystemEntity {
    override fun getName() = name
    private val subSet = mutableListOf<FileSystemEntity>()
    override fun getChildren() = subSet
    override fun show() = "$name{ ${getChildren().joinToString(", ") { it.show() }} }"

    fun add(fileSystemEntity: FileSystemEntity): Directory {
        subSet.add(fileSystemEntity)
        return this
    }
}

val homeDir = Directory("home")
    .add(
        Directory("movies")
            .add(File("Tetris.mkv"))
            .add(File("TakeShelter.mp4"))
            .add(File("Stalker.mp4"))
            .add(Directory("independent-cinema"))
    )
    .add(
        Directory("books")
            .add(File("Learn Rust Programming.pdf"))
            .add(File("The.Pragmatic.Programmer.pdf"))
            .add(File("Functional-Kotlin.pdf"))
            .add(File("Java-Concurrency-in-Practice.pdf"))
            .add(
                Directory("philosophy")
                    .add(Directory("hegel"))
                    .add(Directory("wittgenstein"))
            )
    )
    .add(
        Directory("Downloads")
    )
    .add(
        File("error.log")
    )

fun main() {
    println(homeDir.show())
    println(homeDir.getChildren().last().show())
    println(homeDir.getChildren().first().show())
    println(homeDir.getChildren().first().getChildren()!!.last().show())
}
