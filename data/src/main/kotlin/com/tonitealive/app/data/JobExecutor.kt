package com.tonitealive.app.data

import com.tonitealive.app.domain.executor.ThreadExecutor
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadFactory
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit


class JobExecutor : ThreadExecutor {

    companion object {
        private const val INITIAL_POOL_SIZE = 3
        private const val MAX_POOL_SIZE = 5
        private const val KEEP_ALIVE_TIME = 10L
        private val KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS
        private const val THREAD_NAME = "android_"
    }

    private val workQueue = LinkedBlockingQueue<Runnable>()
    private val threadFactory = JobThreadFactory()
    private val threadPoolExecutor = ThreadPoolExecutor(INITIAL_POOL_SIZE,
            MAX_POOL_SIZE,
            KEEP_ALIVE_TIME,
            KEEP_ALIVE_TIME_UNIT,
            workQueue,
            threadFactory)

    override fun execute(command: Runnable) {
        if (command != null)
            threadPoolExecutor.execute(command)
        else
            throw IllegalAccessException("Runnable cannot be null")
    }

    private class JobThreadFactory : ThreadFactory {
        private var counter = 0

        override fun newThread(runnable: Runnable?): Thread {
            return Thread(runnable, THREAD_NAME + counter++)
        }
    }
}