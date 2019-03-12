import com.github.blacknblue.alohachat.base.message_parse.ChatMsg
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.InetAddress
import java.net.URL

fun findPublicIP() : String {
    var publicIP = ""
    try {
        var url_name : URL = URL("http://bot.whatismyipaddress.com")
        var sc = BufferedReader(InputStreamReader(url_name.openStream()))
        publicIP = sc.readLine().trim()
    } catch(e: Exception) {
        throw e
    }
    return publicIP
}

fun findLocalIP() : String {
    var localhost = InetAddress.getLocalHost()
    return localhost.hostAddress.trim()
}

fun segragateInput(input: String?) : MutableList<ChatMsg> {
    var tempList = MutableList<ChatMsg>(0)  { ChatMsg() }

    if(input != null) {
        for(msg in input.split('\n')) {
            tempList.add(ChatMsg(msg))
        }
    } else throw Exception("Error in Server module : Input string is null!!!")
    return tempList
}

