package com.tonitealive.app.domain.executor;

import rx.Scheduler;


public interface PostExecutionThread {

    Scheduler getScheduler();

}