package combine

import Request

interface RequestSplitter {
    fun chunk(request: Request): List<Request>
}

class SmallSplitter : RequestSplitter {
    override fun chunk(request: Request) = listOf(request)
}

class LargeSplitter : RequestSplitter {
    override fun chunk(request: Request) = request.content
        .lines().map { Request(type = request.type, content = it) }
}

abstract class RequestProcessor {
    protected abstract fun process(request: Request)
    private var splitter: RequestSplitter = SmallSplitter()
    private var next: RequestProcessor? = null

    fun changeSplitter(splitter: RequestSplitter) {
        this.splitter = splitter
        next?.changeSplitter(splitter)
    }

    fun withNext(next: RequestProcessor): RequestProcessor {
        this.next = next
        return this
    }

    fun handle(request: Request) {
        splitter.chunk(request).forEach {
            process(it)
            next?.handle(it)
        }
    }
}

class JsonProcessor : RequestProcessor() {
    override fun process(request: Request) {
        if (request.type == Type.JSON)
            println("serializing json with content: ${request.content}")
    }
}

class PdfProcessor : RequestProcessor() {
    override fun process(request: Request) {
        if (request.type == Type.PDF)
            println("reading pdf with content: ${request.content}")
    }
}

class StringProcessor : RequestProcessor() {
    override fun process(request: Request) {
        if (request.type == Type.STRING)
            println("reading string with content: ${request.content}")
    }
}

class ProcessorManager {
    private val smallSplitter = SmallSplitter()
    private val largeSplitter = LargeSplitter()
    private val processor = JsonProcessor()
        .withNext(
            StringProcessor()
                .withNext(
                    PdfProcessor()
                )
        )

    fun process(request: Request) {
        if (request.content.length > 500)
            processor.changeSplitter(largeSplitter)
        else
            processor.changeSplitter(smallSplitter)
        processor.handle(request)
    }
}

fun main() {
    ProcessorManager().process(Request(type = Type.PDF, content = "this is a small pdf!"))
    println("---------------------------------------------")
    ProcessorManager().process(Request(type = Type.PDF,
        content = "this is a large pdf!!".plus(
            (1..10).map { "### ... a very large line here >>> line number-$it \n" }
        )))
}


