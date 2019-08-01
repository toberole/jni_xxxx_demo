package com.xiaoge.org.kotlin

/*
object是Kotlin中的一个重要的关键字，object主要有以下三种使用场景：
对象声明（Object Declaration）
伴生对象（Companion Object）
对象表达式（Object Expression）

对比object declaration、Companion object以及object expression的初始化时机：

a. object declaration：当第一次访问它时才初始化，是一种懒初始化

b. Companion object：当它对应的类被加载后，它才初始化，类似Java中的静态代码块

c. object expression：一旦它被执行，立马初始化

*/

// 实现单例

object Singleton {
    fun closeFile() {
        println("closeFile")
    }
}


// 定义在内部类
class ObjectOuter {
    object Inner {
        fun method() {
            println("I'm in inner class")
        }
    }
}

// 伴生对象
// 在Kotlin中是没有static关键字的，也就是意味着没有了静态方法和静态成员。
// 那么在kotlin中如果要想表示这种概念，取而代之的是包级别函数（package-level function）
// 和我们这里提到的伴生对象。
// 语法形式
//class A{
//    companion object 伴生对象名(可以省略){
//        //define method and field here
//    }
//}
class Object_Companio {
    companion object MyObject {
        var a = 20
        @JvmStatic
        fun method() {
            println("I'm in companion object method")
        }

        // 加不加JvmStatic注解 效果一样
        // 可以通过反编译看源代码
        fun method1() {
            println("I'm in companion object method1")
        }
    }
}

// 对象表达式（Object Expression）
// 语法形式：object [ : 接口1,接口2,类型1, 类型2]{}
// 实现一个接口或者类
interface AA {
    fun a()
}

interface BB {
    fun b()
}

// 匿名对象
// 匿名对象只有定义成局部变量和private成员变量时，才能体现它的真实类型。
// 如果将匿名对象作为public函数的返回值或者是public属性时，只能将它看做是它的父类，
// 不指定任何类型时就当做Any，在匿名对象中添加的属性和方法是不能够被访问的。
class MyTest {

    private val foo = object {
        fun method() {
            println("private")
        }
    }

    val foo2 = object {
        fun method() {
            println("public")
        }
    }

    fun m() = object {
        fun method() {
            println("method")
        }
    }

    fun invoke() {

        val local = object {
            fun method() {
                println("local")
            }
        }

        local.method()  //编译通过
        foo.method()    //编译通过
        // foo2是public的
        // foo2.method()   //编译通不过
        // m()是public的
        // m().method()    //编译通不过
    }
}

fun main() {
    // 在kotlin中没有静态方法
    Singleton.closeFile()
    ObjectOuter.Inner.method()

    // 使用伴生对象
    // 方式一
    println(Object_Companio.MyObject.a)
    Object_Companio.MyObject.method()
    // 方式二 类似java static成员属性和方法的使用
    println(Object_Companio.a)
    Object_Companio.method()
    Object_Companio.method1()

    // onject 对象表达式
    var aa = object : AA {
        // 可以理解为匿名继承
        // 与此同时也声明它的一个对象
        override fun a() {
            println("object : AA")
        }
    }
    aa.a()
    var cc = object : AA, BB {
        override fun a() {
            println("AA:a")
        }

        override fun b() {
            println("BB:b")
        }
    }
    cc.a()
    cc.b()
}