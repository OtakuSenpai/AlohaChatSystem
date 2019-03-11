package com.github.blacknblue.alohachat.base.others

import com.github.blacknblue.alohachat.base.helpers.matchPrefix
import com.github.blacknblue.alohachat.base.message_parse.Prefix

class GroupChannel(ownPrefix: Prefix) : Channel(ownPrefix) {

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

    // List of Prefixes in Group
    // Acts as a receiver Prefix list
    var receiverGroupPPL = MutableList(0) { Prefix() }
}