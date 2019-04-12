package com.github.otakusenpai.alohachat.client.helpers

import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
import tornadofx.*

class MyView : View() {
    val controller: MyController by inject()
    val input = SimpleStringProperty()

    private var ip: StringProperty = SimpleStringProperty()
    private var nick: StringProperty = SimpleStringProperty()
    private var port: StringProperty = SimpleStringProperty()

    override val root = form {
        fieldset {
            field("server ip") {
                textfield(ip)
            }

            field("nick name") {
                textfield(nick)
            }

            field("port") {
                textfield(port)
            }

            button("Connect") {
                action {
                    controller.connect(nick.get(),ip.get(),port.get())
                }
            }
        }
    }
}