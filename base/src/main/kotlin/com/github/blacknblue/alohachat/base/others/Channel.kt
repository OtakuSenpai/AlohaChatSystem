package com.github.blacknblue.alohachat.base.others

import com.github.blacknblue.alohachat.base.helpers.KtQueue
import com.github.blacknblue.alohachat.base.message_parse.Prefix

abstract class Channel(ownPrefix: Prefix) {
    var messageList = KtQueue()
    var senderPrefix = ownPrefix
}