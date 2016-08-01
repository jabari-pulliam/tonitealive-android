package com.tonitealive.app.domain.interactors;

import com.tonitealive.app.domain.executor.PostExecutionThread;
import com.tonitealive.app.domain.executor.ThreadExecutor;


public abstract class UseCase {

    protected final ThreadExecutor executor;
    protected final PostExecutionThread postExecutionThread;

    protected UseCase(ThreadExecutor executor, PostExecutionThread postExecutionThread) {
        this.executor = executor;
        this.postExecutionThread = postExecutionThread;
    }

}