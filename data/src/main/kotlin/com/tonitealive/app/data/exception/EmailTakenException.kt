package com.tonitealive.app.data.exception


class EmailTakenException(email: String) : Exception(email) {
}