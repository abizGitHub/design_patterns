data class Request(val content: String, val type: Type)

data class Envelope(
    val request: Request,
    val checkedBy: MutableList<RequestHandler> = mutableListOf()
)

enum class Type {
    JSON, STRING, PDF, MEDIA
}

abstract class RequestHandler {
    var next: RequestHandler? = null
    fun withNext(next: RequestHandler): RequestHandler {
        this.next = next
        return this
    }

    protected abstract fun handleReq(request: Request)

    fun handle(envelope: Envelope) {
        envelope.checkedBy.add(this)
        handleReq(envelope.request)
        next?.handle(envelope)
    }
}

class JsonHandler : RequestHandler() {
    override fun handleReq(request: Request) {
        if (request.type == Type.JSON)
            println("serializing json with content: ${request.content}")
    }
}

class PdfHandler : RequestHandler() {
    override fun handleReq(request: Request) {
        if (request.type == Type.PDF)
            println("reading pdf with content: ${request.content}")
    }
}

class StringHandler : RequestHandler() {
    override fun handleReq(request: Request) {
        if (request.type == Type.STRING)
            println("reading string with content: ${request.content}")
    }
}

class MediaHandler : RequestHandler() {
    override fun handleReq(request: Request) {
        if (request.type == Type.MEDIA)
            println("playing media with content: ${request.content}")
    }
}

class RequestManager {
    private val firstHandler =
        StringHandler()
            .withNext(
                JsonHandler()
                    .withNext(
                        PdfHandler()
                            .withNext(MediaHandler())
                    )
            )

    fun readRequest(envelope: Envelope) {
        firstHandler.handle(envelope)
    }
}

fun main() {
    val envelope = Envelope(request = Request("""{"isJson":true}""", type = Type.JSON))
    RequestManager().readRequest(envelope)
    envelope.checkedBy.forEach {
        println("checkedBy: ${it::class}")
    }
    RequestManager().readRequest(Envelope(Request("playing a music ", type = Type.MEDIA)))
}
