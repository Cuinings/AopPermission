package com.cn.permission_kotlin.aop.annotation
/**
 * AnnotationTarget.CLASS：类，接口或对象，注解类也包括在内。
 * AnnotationTarget.ANNOTATION_CLASS：只有注解类。
 * AnnotationTarget.TYPE_PARAMETER：Generic type parameter (unsupported yet)通用类型参数（还不支持）。
 * AnnotationTarget.PROPERTY：属性。
 * AnnotationTarget.FIELD：字段，包括属性的支持字段。
 * AnnotationTarget.LOCAL_VARIABLE：局部变量。
 * AnnotationTarget.VALUE_PARAMETER：函数或构造函数的值参数。
 * AnnotationTarget.CONSTRUCTOR：仅构造函数（主函数或者第二函数）。
 * AnnotationTarget.FUNCTION：方法（不包括构造函数）。
 * AnnotationTarget.PROPERTY_GETTER：只有属性的 getter。
 * AnnotationTarget.PROPERTY_SETTER：只有属性的 setter。
 * AnnotationTarget.TYPE：类型使用。
 * AnnotationTarget.EXPRESSION：任何表达式。
 * AnnotationTarget.FILE：文件。
 * AnnotationTarget.TYPEALIAS：@SinceKotlin("1.1") 类型别名，Kotlin1.1已可用。
 */
/**
 * @Author:          崔宁 github: https://github.com/Cuinings
 * @Email:          1015597172@qq.com
 * @Date:           2020/6/4 2:33 PM
 * @Description:
 */
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
annotation class Permission(val value: Array<String>, val requestCode: Int = 0) {

}