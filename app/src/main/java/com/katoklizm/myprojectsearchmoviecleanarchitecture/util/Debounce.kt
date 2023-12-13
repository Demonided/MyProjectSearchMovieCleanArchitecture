package com.katoklizm.myprojectsearchmoviecleanarchitecture.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun <T> debounce(
    delayMillis: Long,
    coroutinesScope: CoroutineScope,
    useLastParam: Boolean,
    action: (T) -> Unit
): (T) -> Unit {
    var debounceJob: Job? = null
    return { param: T ->
        if (useLastParam) {
            debounceJob?.cancel()
        }

        if (debounceJob?.isCompleted != false || useLastParam) {
            debounceJob = coroutinesScope.launch {
                delay(delayMillis)
                action(param)
            }
        }
    }
}