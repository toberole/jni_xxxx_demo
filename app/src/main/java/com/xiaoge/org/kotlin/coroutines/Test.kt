package com.xiaoge.org.kotlin.coroutines

import kotlinx.coroutines.*

fun test1() {
    GlobalScope.launch {
        // 在后台启动一个新的协程并继续
        delay(1000L) // 非阻塞的等待 1 秒钟（默认时间单位是毫秒）
        println("World!") // 在延迟后打印输出
    }

    println("Hello,") // 协程已在等待时主线程还在继续
    Thread.sleep(2000L) // 阻塞主线程 2 秒钟来保证 JVM 存活
}

fun test2() {
    GlobalScope.launch {
        // 在后台启动一个新的协程并继续
        delay(1000L) // 非阻塞的等待 1 秒钟（默认时间单位是毫秒）
        println("World!") // 在延迟后打印输出
    }

    println("Hello,") // 协程已在等待时主线程还在继续
    runBlocking {
        kotlinx.coroutines.delay(2000)
    }
}

fun test3() {
    runBlocking {
        GlobalScope.launch {
            // 在后台启动一个新的协程并继续
            delay(1000L) // 非阻塞的等待 1 秒钟（默认时间单位是毫秒）
            println("World!") // 在延迟后打印输出
        }
        println("Hello,") // 协程已在等待时主线程还在继续
        kotlinx.coroutines.delay(2000)
    }
}

fun test4() {
    runBlocking {
        val job = GlobalScope.launch {
            kotlinx.coroutines.delay(1000)
            println("==========")
        }

        println("main ---------")

        job.join()

        println("main -----0000----")
    }
}

fun main1() {

    test3()
}

// 使用 runBlocking 协程构建器将 main 函数转换为协程
// 外部协程直到在其作用域中启动的所有协程都执行完毕后才会结束。
fun main2() = runBlocking {
    // this: CoroutineScope
    launch {
        // 在 runBlocking 作用域中启动一个新协程
        delay(1000L)
        println("World!")
    }
    println("Hello,")
}

// runBlocking 与 coroutineScope 的主要区别在于后者在等待所有子协程执行完毕时不会阻塞当前线程。
fun main() = runBlocking {
    // this: CoroutineScope
    launch {
        delay(200L)
        println("Task from runBlocking")
    }
    println("000000000000000000")

    coroutineScope {
        // 创建一个协程作用域
        launch {
            delay(500L)
            println("Task from nested launch")
        }

        delay(100L)
        println("Task from coroutine scope") // 这一行会在内嵌 launch 之前输出
    }

    println("111111111111111111111")

    println("Coroutine scope is over") // 这一行在内嵌 launch 执行完毕后才输出
}