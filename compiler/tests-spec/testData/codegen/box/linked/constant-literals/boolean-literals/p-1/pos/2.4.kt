// HELPERS: REFLECT

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 0.1-106
 * PLACE: constant-literals, boolean-literals -> paragraph 1 -> sentence 2
 * NUMBER: 4
 * DESCRIPTION: The use of Boolean literals as the identifier (with backtick) in the fileAnnotationSimple.
 * NOTE: this test data is generated by FeatureInteractionTestDataGenerator. DO NOT MODIFY CODE MANUALLY!
 */

@file:`true`
@file:`false`

@Target(AnnotationTarget.FILE)
annotation class `true`

@Target(AnnotationTarget.FILE)
annotation class `false`

fun box(): String? {
    if (!checkFileAnnotations("_2_4Kt", listOf("true", "false"))) return null
    if (!checkClassName(`true`::class, "true")) return null
    if (!checkClassName(`false`::class, "false")) return null

    return "OK"
}
