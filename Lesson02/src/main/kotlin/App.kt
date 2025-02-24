import kotlin.reflect.KClass

class Person(var name: String, var age: Int, var country: String) {
    fun m1() { }
}


fun main() {
    var kClass: KClass<Person> = Person::class

    kClass.members

}