package com.tonitealive.app.data.exception


class UsernameTakenException(username: String) : Exception(username) {
}