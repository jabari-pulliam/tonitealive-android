package com.tonitealive.app.domain.executor

import rx.Scheduler


interface PostExecutionThread {
    val scheduler: Scheduler
}