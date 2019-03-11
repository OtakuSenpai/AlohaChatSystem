package com.github.blacknblue.alohachat.base.helpers

class KtQueue() {

    constructor(list: MutableList<Any>) : this() {
        data = list
    }

    fun isEmpty() = data.isEmpty()

    fun size() = data.size

    fun enqueue(element: Any) {
        data.add(element)
    }

    fun dequeue(): Any? {
        return if(isEmpty())
            null
        else {
            data.removeAt(0)
        }
    }

    var data = MutableList<Any>(0) {}
}