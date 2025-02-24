function reflect(o) {
    Object.keys(o.constructor.prototype).forEach(key => {
        console.log(key)
        if(o[key] instanceof Function) {
            o[key].call(this)
        }
    })
}


function Person(name) {

}
Person.prototype.sayHello = function() {
    console.log("Hello")
}

Person.prototype.slb = "Glorioso"
var s =  new Person("Hello");
