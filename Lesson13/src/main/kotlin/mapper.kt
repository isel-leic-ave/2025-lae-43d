package isel.lae.li43d.mapper

import kotlin.reflect.*
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.primaryConstructor


@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class PropName(val n: String)

var typeNamePropMap: MutableMap<
        Pair<KClass<*>, KClass<*>>,
        Pair<KFunction<Any>, Map<KParameter, KProperty1<Any, Any?>>>> = mutableMapOf()

fun mapTo(src: Any, dstRep: KClass<*>): Any {
    val srcRep: KClass<*> = src::class

    val srcDestMapping: Pair<KFunction<Any>, Map<KParameter, KProperty1<Any, Any?>>> =
        typeNamePropMap.getOrElse(Pair(srcRep, dstRep))
    {
        println("### Obtaining map for $srcRep, $dstRep ")
        val primaryConstructor: KFunction<Any> = dstRep.primaryConstructor!!


        val mapKParamProp: Map<KParameter, KProperty1<Any, Any?>> =
            primaryConstructor.parameters
                .filter { !it.isOptional }
                .associateWith { param ->
                    val propName = param.findAnnotation<PropName>()?.n ?: param.name
                    val prop: KProperty1<Any, Any?> = srcRep.declaredMemberProperties.find { it.name == propName } as KProperty1<Any, Any?>
                    prop
                }
        val pair = Pair(primaryConstructor, mapKParamProp)
        typeNamePropMap[Pair(srcRep, dstRep)] = pair
        pair
    }

    val (primaryConstructor, mapKParamProp) = srcDestMapping

    val arguments: Map<KParameter, Any?> = mapKParamProp.mapValues { (param, prop) ->
        getParamValue(src, prop, param)
        //prop.call(src)
    }
    return primaryConstructor.callBy(arguments)
}

inline fun getParamValue(src: Any, prop: KProperty1<Any, Any?>,param: KParameter) : Any? {
    val value: Any? = prop.call(src)

    //return when (param.type.classifier) {
    return when (prop.returnType.classifier) {
        Int::class -> value
        String::class -> value
        else -> if (value != null) mapTo(value, param.type.classifier as KClass<*>) else null
    }
}
