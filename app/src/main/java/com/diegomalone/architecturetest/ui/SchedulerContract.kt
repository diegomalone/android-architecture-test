package br.com.keycar.architecturetest.ui

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.ImmediateThinScheduler
import io.reactivex.schedulers.Schedulers

/**
 * Created by Diego Malone on 29/11/17.
 */

interface SchedulerContract {
    val io: Scheduler
    val ui: Scheduler
}

object DefaultScheduler : SchedulerContract {
    override val io: Scheduler = Schedulers.io()
    override val ui: Scheduler = AndroidSchedulers.mainThread()
}

object ImmediateScheduler : SchedulerContract {
    val scheduler: Scheduler = ImmediateThinScheduler.INSTANCE

    override val io: Scheduler = scheduler
    override val ui: Scheduler = scheduler
}