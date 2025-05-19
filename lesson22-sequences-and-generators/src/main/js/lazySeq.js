

function getValuesEager() {
    var res = []
    console.log("Adding 1")
    res.push(1)
    console.log("Adding 2")
    res.push(2)
    console.log("Adding 3")
    res.push(3)
    console.log("Adding 4")
    res.push(4)
    console.log("Adding 5")
    res.push(5)
    return res
}

function* gerValuesLazy() {
    console.log("*Adding 1")
    yield 1
    console.log("*Adding 2")
    yield 2
    console.log("*Adding 3")
    yield 3
    console.log("*Adding 4")
    yield 4
    console.log("*Adding 5")
    yield 5
}

function showValues(iter, numElemsToShow) {
    for (const i of iter) {
        if(numElemsToShow-- == 0)
            break
        console.log(i)
    }
}

const eagerValues = getValuesEager()
const lazyValues = gerValuesLazy()

console.log("Eager values")
showValues(eagerValues, 2)
console.log("*Lazy values")
showValues(lazyValues, 2)


// function* makeIterator() {
//     yield 1;
//     yield 2;
// }
//
// const iter = makeIterator();
//
// for (const itItem of iter) {
//     console.log(itItem);
// }