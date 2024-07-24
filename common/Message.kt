package common

interface Sender {
    fun sendIt(msg: String)
}

class PostOffice : Sender {
    override fun sendIt(msg: String) = println("sending $msg by car...")
}

class Gmail : Sender {
    override fun sendIt(msg: String) = println("emailing $msg!")
}

//-------------------------------------------------------------------------
abstract class Message(open val sender: Sender) {
    abstract fun getContent(): String
    fun sendMessage() {
        sender.sendIt(getContent())
    }
}

class TextMessage(by: Sender) : Message(by) {
    override fun getContent(): String {
        println("writing text..")
        return "[some text]"
    }
}

class VoiceMessage(by: Sender) : Message(by) {
    override fun getContent(): String {
        println("recording voice..")
        return "[hello? is any..]"
    }
}

