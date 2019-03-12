package com.github.blacknblue.alohachat.base.others

import com.github.blacknblue.alohachat.base.helpers.KtQueue
import com.github.blacknblue.alohachat.base.message_parse.Prefix

abstract class Channel() {

    constructor(ownPrefix: Prefix) : this() {
        senderPrefix = ownPrefix
    }

    fun matchChannels(other: Channel): Boolean = (this.senderPrefix == other.senderPrefix)

    var messageList = KtQueue()
    var senderPrefix = Prefix()
}