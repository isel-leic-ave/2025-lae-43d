package pt.isel


fun getValuesEager(): Iterable<Int> {
    var res = mutableListOf<Int>()
    println("Adding 1")
    res.add(1)
    println("Adding 2")
    res.add(2)
    println("Adding 3")
    res.add(3)
    println("Adding 4")
    res.add(4)
    println("Adding 5")
    res.add(5)
    return res
}

fun genValuesLazy(): Sequence<Int> {
    return sequence<Int>
    {
        println("*Adding 1")
        yield(1)
//        println("*Adding 2")
//        yield(2)
//        println("*Adding 3")
//        yield(3)
//        println("*Adding 4")
//        yield(4)
//        println("*Adding 5")
//        yield(5)
    }
}

fun <T>showValues(seq: Iterator<T>, numElemsToShow: Int) {
    var elems = numElemsToShow

    val iter = seq.iterator()
    while (iter.hasNext()) {
        val i = iter.next()
        if(elems-- == 0)
            break
        println(i)
    }

//    for (i in seq) {
//        if(elems-- == 0)
//            break
//        println(i)
//    }
}


fun main() {
    val eagerValues = getValuesEager()
    val lazyValues = genValuesLazy()

    println("Eager values")
    showValues(eagerValues.iterator(), 2)
    println("*Lazy values")
    showValues(lazyValues.iterator(), 2)
}


// function* makeIterator() {
//     yield 1;
//     yield 2;
// }
//
// const iter = makeIterator();
//
// for (const itItem of iter) {
//     println(itItem);
// }