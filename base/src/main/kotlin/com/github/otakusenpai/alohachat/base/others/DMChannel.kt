package com.github.blacknblue.alohachat.base.others

import com.github.blacknblue.alohachat.base.message_parse.Prefix

class DMChannel(ownPrefix: Prefix, sendPrefix: Prefix) : Channel(ownPrefix) {
    var receiverPrefix = sendPrefix
}