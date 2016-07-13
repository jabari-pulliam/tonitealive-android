package com.tonitealive.app.domain.interactors

import com.tonitealive.app.domain.executor.PostExecutionThread
import com.tonitealive.app.domain.executor.ThreadExecutor


abstract class UseCase protected constructor(val executor: ThreadExecutor,
                                             val postExecutionThread: PostExecutionThread) {

}