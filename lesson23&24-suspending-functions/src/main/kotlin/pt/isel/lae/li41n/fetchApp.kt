package pt.isel.lae.li41n

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

val URL1 = "https://api.chucknorris.io/jokes/random"
fun main() {
    callFetchManyAsCps()
//    runBlocking {
//        callFetchManyAsSuspend()
//    }
}

suspend fun callFetchManyAsSuspend() {
    var fetchManyHandle =  ::fetchManyCps as (suspend (String, String, String)-> Iterable<String>)
    var result = fetchManyHandle(URL1, URL1, URL1)
    println("Result from fetchManyCps called as suspend ${result.joinToString("\n")}")
}

fun callFetchManyAsCps() {
    fetchManyCps(URL1, URL1, URL1,
        object : Continuation<Iterable<String>> {
            override val context: CoroutineContext = EmptyCoroutineContext
            override fun resumeWith(result: Result<Iterable<String>>) {
                val res = result.getOrThrow()
                val iter = res.iterator()
                val returnedValue = iter.next()
                println(returnedValue)

                println("Result from fetchManyCps ${result.getOrThrow().joinToString("\n")}")
            }
        })

    runBlocking {
        delay(5000)
    }
}
