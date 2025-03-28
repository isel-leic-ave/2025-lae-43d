package lesson03

import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.KTypeProjection
import kotlin.reflect.full.createType
import kotlin.reflect.typeOf


class Stack<T> {
    fun push(obj: T) {

    }

    fun pop(): T? {
        return null;
    }
}

class GenericClass<A, B>

fun inspectGenericType(type: KType) {
    println("Type: ${type}")
    val arguments = type.arguments
    if (arguments.isNotEmpty()) {
        println("Type arguments:")
        arguments.forEach { arg ->
            println(" - ${arg.type}")
        }
    } else {
        println("No type arguments.")
    }
}


fun main() {
    val stackInt = Stack<Int>()
    val stackInt1 = Stack<Int>()

    stackInt.push(2)
    val pop: Int? = stackInt.pop()

    val stackString = Stack<String>()

    println("String type parameters: ${String::class.typeParameters}")

//    val stackGenericType = Stack::class


    println("Stack type parameters: ${Stack::class.typeParameters}")

    val kClassStackInt: KClass<out Stack<Int>> = Stack<Int>()::class
    println("Stack<Int> type parameters: ${kClassStackInt.typeParameters}")
    //println("Stack<Int> type parameters: ${kClassStackInt}")



    // Create an instance of a specific type
    val instanceType = //typeOf<List<String>>();
        GenericClass::class.createType(
            listOf(
                KTypeProjection.invariant(String::class.createType()),
                KTypeProjection.invariant(Int::class.createType())
            )
        )
    inspectGenericType(instanceType)
}
//
//
//fun inspectGenericType(type: KType) {
//    println("Type: ${type}")
//    val arguments = type.arguments
//    if (arguments.isNotEmpty()) {
//        println("Type arguments:")
//        arguments.forEach { arg ->
//            println(" - ${arg.type}")
//        }
//    } else {
//        println("No type arguments.")
//    }
//}
//
//fun main() {
//    // Example of a generic class
//    class GenericClass<A, B>
//
//    // Create an instance of a specific type
//    val instanceType = GenericClass::class.createType(listOf(String::class.createType(), Int::class.createType()))
//    inspectGenericType(instanceType)
//}
