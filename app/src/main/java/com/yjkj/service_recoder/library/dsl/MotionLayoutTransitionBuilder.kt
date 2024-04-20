package com.yjkj.service_recoder.library.dsl

import androidx.constraintlayout.motion.widget.MotionLayout

/**
 * @author hxy
 * create at 2022/7/29 19:19
 * dsl简化motionLayout回调
 */

private typealias OnTransitionStartedCallback = (motionLayout: MotionLayout?, startId: Int, endId: Int) -> Unit
private typealias OnTransitionChangeCallback = (motionLayout: MotionLayout?, startId: Int, endId: Int, progress: Float) -> Unit
private typealias OnTransitionCompletedCallback = (motionLayout: MotionLayout?, currentId: Int) -> Unit
private typealias OnTransitionTriggerCallback = (motionLayout: MotionLayout?, triggerId: Int, positive: Boolean, progress: Float) -> Unit

class MotionLayoutTransitionBuilder : MotionLayout.TransitionListener {

    private var onTransitionStartedCallback : OnTransitionStartedCallback? = null
    private var onTransitionChangeCallback : OnTransitionChangeCallback? = null
    private var onTransitionCompletedCallback : OnTransitionCompletedCallback? = null
    private var onTransitionTriggerCallback : OnTransitionTriggerCallback? = null

    override fun onTransitionStarted(motionLayout: MotionLayout?, startId: Int, endId: Int) {
        onTransitionStartedCallback?.invoke(motionLayout, startId, endId)
    }

    override fun onTransitionChange(
        motionLayout: MotionLayout?,
        startId: Int,
        endId: Int,
        progress: Float
    ) {
        onTransitionChangeCallback?.invoke(motionLayout, startId, endId, progress)
    }

    override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
        onTransitionCompletedCallback?.invoke(motionLayout, currentId)
    }

    override fun onTransitionTrigger(
        motionLayout: MotionLayout?,
        triggerId: Int,
        positive: Boolean,
        progress: Float
    ) {
        onTransitionTriggerCallback?.invoke(motionLayout, triggerId, positive, progress)
    }

    fun transitionStarted(callback: OnTransitionStartedCallback){
        onTransitionStartedCallback = callback
    }

    fun transitionChange(callback: OnTransitionChangeCallback){
        onTransitionChangeCallback = callback
    }

    fun transitionCompleted(callback: OnTransitionCompletedCallback){
        onTransitionCompletedCallback = callback
    }

    fun transitionTrigger(callback: OnTransitionTriggerCallback){
        onTransitionTriggerCallback = callback
    }
}

fun registerTransitionListener(function : MotionLayoutTransitionBuilder.()->Unit) = MotionLayoutTransitionBuilder().also(function)