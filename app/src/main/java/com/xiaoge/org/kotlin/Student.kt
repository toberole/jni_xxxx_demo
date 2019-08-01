package com.xiaoge.org.kotlin

import com.tencent.bugly.proguard.s

/**
 * constructor主构造函数
 * 在主构造函数中不能有任何的代码 必须放在init代码块中
 * 如果主构造函数没有任何注解或者可见性修饰符.
 * 如果构造函数有注解或可见性修饰符，constructor 关键字是必需的，并且修饰符在它前面
 */
class Student public constructor(firstName: String) {
    public var firstName: String

    init {
        this.firstName = firstName;
        println("hello Student")
    }
}

// open表示该类可以被继承 默认类都是不可以继承的
// 不写open关键字的 类 方法 属性 是不能被继承和重写的
open class Student1 {
    public var firstName: String = ""
    public var age: Int = 0

    constructor(firstName: String) {
        this.firstName = firstName
    }

    constructor(age: Int) {
        this.age = age
    }

    constructor(firstName: String = "", age: Int = 0) {
        this.firstName = firstName;
        this.age = age
    }

    open fun printName() {
        println("Student1: " + firstName)
    }
}

class Student2 : Student1 {
    constructor(name: String) {
        super.firstName = name
    }

    override fun printName() {
        println("Student2: " + firstName)
    }

    fun sys() {
        // 如果一个函数不返回任何有用的值，它的返回类型是 Unit。Unit 是一种只有一个值——Unit 的类型。这个 值可写可不写
        println("sys ...")
        return Unit// 可以不写
    }

    // 表达式函数
    fun cal(x: Int): Int = x * x
}

fun main() {
    var student = Student("hello")
    println(student.firstName)

    var stu2 = Student2("2 name")
    stu2.printName()
    stu2.sys()
    var i = stu2.cal(2)
    println(i)
}