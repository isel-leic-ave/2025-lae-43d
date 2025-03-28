import kotlin.reflect.KClass
import kotlin.reflect.KParameter
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor

fun mapper(src: Any, dstRep: KClass<*>): Any? {
    var dstPrimaryConstructor = dstRep.primaryConstructor
    val destProperties = src::class.memberProperties
    dstPrimaryConstructor?.parameters?.associateWith { param ->
        val prop = destProperties.find { it.name == param.name }
        var value: Any? = prop?.call(src)
        return value
    }

    val arguments: Map<KParameter, Any?> = emptyMap()

    return dstPrimaryConstructor?.callBy(arguments)
}