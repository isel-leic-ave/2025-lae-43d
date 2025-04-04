package isel.lae.mapper

import kotlin.reflect.KClass
import kotlin.reflect.KParameter
import kotlin.reflect.KProperty1
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor


@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class PropName(val name: String)

fun mapTo(src: Any, dstRep: KClass<*>): Any? {
    var dstPrimaryConstructor = dstRep.primaryConstructor
    val srcProperties = src::class.memberProperties
    val arguments: Map<KParameter, Any?>?? = dstPrimaryConstructor?.parameters
        ?.filter { !it.isOptional }
        ?.associateWith { param ->
            val srcPropName = param.findAnnotation<PropName>()?.name ?: param.name
            val prop = srcProperties.find { it.name == srcPropName }
            getParamValue(src, prop, param)
        }

    return dstPrimaryConstructor?.callBy(arguments!!)
}

fun getParamValue(src: Any, prop: KProperty1<out Any, *>?, param: KParameter) : Any? {
    val propKClass: KClass<*>? = prop?.returnType?.classifier as? KClass<*>
    var value: Any? = prop?.call(src)

    return when (propKClass) {
        Int::class -> value
        String::class -> value
        else -> if (value != null) mapTo(value, param.type?.classifier as KClass<*>) else value
    }
}
