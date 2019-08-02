package com.xiaoge.org.kotlin.demo

// 区间表达式
fun test5_1(i: Int) {
    if (i in 1..10) { // 前闭后闭区间 [1,10]
        println("i: " + i)
    }
    println("倒序迭代")
    // 倒序迭代
    for (i in 4 downTo 1) println(i)

    for (i in 4 downTo 1 step 2) println(i)

    for (i in 1 until 10) {// [1,10)
        println(i)
    }

    for (i in 1 until 10) {// [1,10)
        println(i)
    }
}

// 类型检查与转换
// 类型的检查 is 操作符或其否定形式 !is 来检查对象是否符合给定类型，相当于java中的 instanceof
fun test5_2(obj: Any) {
    if (obj is String) {
        println("obj is String")
    }

    // 显示转换 不安全
    var x: String = obj as String
    println(x)
    // 显示转换 安全
    var x1: String? = obj as? String
    println(x1)
}

class E(message: String?) : Throwable(message) {

}

// 异常
// Kotlin 中所有异常类都是 Throwable 类的子孙类。 每个异常都有消息、堆栈回溯信息和原因。
// catch先匹配到就捕获了
fun test5_3() {
    try {
        throw E("hello")
    } catch (e: Throwable) {
        println("Throwable ----")
    } catch (ee: E) {// 被Throwable 先捕获了
        println("ee ----")
    } finally {
        println("finally")
    }
}

fun main() {
    test5_1(1)
    test5_1(10)

    test5_2("hello")
    test5_3()
}