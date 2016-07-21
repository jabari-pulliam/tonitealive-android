package com.tonitealive.app.data

import android.content.Context
import com.tonitealive.app.domain.MessageId
import com.tonitealive.app.domain.MessageProvider
import java.util.*
import javax.inject.Singleton

@Singleton
class DefaultMessageProvider(private val context: Context) : MessageProvider {

    private val messageCodes: HashMap<MessageId, Int> = HashMap()

    init {
        messageCodes.put(MessageId.SIGN_IN_SUCCESS, R.string.signin_succeeded)
    }

    override fun getMessage(id: MessageId): String {
        val code = messageCodes[id]
        if (code != null)
            return context.getString(code)
        throw IllegalArgumentException("No message found for id $id")
    }
}