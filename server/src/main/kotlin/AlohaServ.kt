import com.github.blacknblue.alohachat.base.connection.BasicConnection
import com.github.blacknblue.alohachat.base.helpers.matchPrefix
import com.github.blacknblue.alohachat.base.message_parse.ChatMsg
import com.github.blacknblue.alohachat.base.others.Channel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking

object AlohaServ {

    fun init(servName: String, servIP: String, servPort: String) {
        conn = BasicConnection(servPort.toInt(),servIP)
        serverPort = servPort
        serverIP = servIP
        serverName = servName
        running = true
    }

    suspend fun passMsgToChannels(msgList: MutableList<ChatMsg>) = coroutineScope {
        // Loop through the message list
        val one = async {
            for(msg in msgList) {
                // Loop through the channel list
                for(channels in channelList)
                // If channel receiver prefix match msg receiver prefix, then enqueue in channel msg list
                    if(matchPrefix(msg.msgdata.receiverPrefix, (channels as Channel).senderPrefix)) {
                        channels.messageList.enqueue(msg)
                    }
            }
        }
        one.join()
    }


    fun run() = runBlocking {
        var tempInput: String? = ""
        var msgList = MutableList<ChatMsg>(0) { ChatMsg() }
        try {
            while(running) {
                tempInput = conn.receiveUTF8Data()
                if(tempInput == null)
                    throw Exception("Error in Server module : Received null string!!!")
                else {
                    msgList = segragateInput(tempInput)
                    passMsgToChannels(msgList)

                }
            }
        } catch(e : Exception) {
            e.printStackTrace()
        }
    }



    lateinit var conn : BasicConnection
    var channelList = MutableList<Any>(0) {}
    var serverPort = ""
    var serverIP = ""
    var serverName: String = ""
    var running = false
}