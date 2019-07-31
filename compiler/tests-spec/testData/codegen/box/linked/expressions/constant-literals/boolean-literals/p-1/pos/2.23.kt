/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 0.1-106
 * PLACE: expressions, constant-literals, boolean-literals -> paragraph 1 -> sentence 2
 * NUMBER: 23
 * DESCRIPTION: The use of Boolean literals as the identifier (with backtick) in the atomicExpression.
 * NOTE: this test data is generated by FeatureInteractionTestDataGenerator. DO NOT MODIFY CODE MANUALLY!
 */

fun box(): String? {
    val `true` = 10
    val `false` = "."

    val value_1 = `true` - 100 % `true`
    val value_2 = `true`.dec()
    val value_3 = "$`false` 10"
    val value_4 = "${`false`}"
    val value_5 = `false` + " 11..." + `false` + "1"
    val value_6 = `true`

    if (value_1 != 10) return null
    if (value_2 != 9) return null
    if (value_3 != ". 10") return null
    if (value_4 != ".") return null
    if (value_5 != ". 11....1") return null
    if (value_6 != 10) return null

    return "OK"
}