// HELPERS: REFLECT

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 0.1-106
 * PLACE: constant-literals, boolean-literals -> paragraph 1 -> sentence 2
 * NUMBER: 18
 * DESCRIPTION: The use of Boolean literals as the identifier (with backtick) in the typeAlias.
 * NOTE: this test data is generated by FeatureInteractionTestDataGenerator. DO NOT MODIFY CODE MANUALLY!
 */

typealias `true` = Boolean

internal typealias `false`<`true`> = Map<`true`, List<`true`>?>

fun box(): String? {
    val x1: `false`<Boolean> = mapOf(true to listOf(false, false), false to null)
    val x2: `true` = false

    if (!x1[true]!!.containsAll(listOf(false, false)) || x1[false] != null) return null
    if (x2) return null

    if (!checkClassName(`true`::class, "kotlin.Boolean")) return null

    return "OK"
}