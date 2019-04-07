package com.github.otakusenpai.alohachat.base.channel

import com.github.otakusenpai.alohachat.base.helpers.matchPrefix
import com.github.otakusenpai.alohachat.base.message_parse.Prefix

class GroupChannel(channelName: String, port: Int) : Channel(channelName, port) {

    init {
        this.dmORgroup = false
    }

    fun addPPL(prefix: Prefix) {
        receiverGroupPPL.add(prefix)
    }

    fun ifPplIsThere(prefix: Prefix): Boolean {
        var found = false
        for(i in receiverGroupPPL) {
            if(matchPrefix(i,prefix)) found = true
        }
        return found
    }

    fun removePPL(prefix: Prefix) {
        receiverGroupPPL.remove(prefix)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false

        other as GroupChannel

        if (receiverGroupPPL != other.receiverGroupPPL) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + receiverGroupPPL.hashCode()
        return result
    }

    var receiverGroupPPL = MutableList(0) { Prefix() }
}