import common.Gmail
import common.PostOffice
import common.TextMessage
import common.VoiceMessage

fun main() {
    VoiceMessage(by = Gmail()).sendMessage()
    VoiceMessage(by = PostOffice()).sendMessage()

    TextMessage(by = Gmail()).sendMessage()
    TextMessage(by = PostOffice()).sendMessage()
}
