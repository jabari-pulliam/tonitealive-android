package com.tonitealive.app.domain


interface MessageProvider {
    fun getMessage(id: MessageId): String
}