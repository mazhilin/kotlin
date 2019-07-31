// HELPERS: REFLECT

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 0.1-106
 * PLACE: constant-literals, boolean-literals -> paragraph 1 -> sentence 2
 * NUMBER: 12
 * DESCRIPTION: The use of Boolean literals as the identifier (with backtick) in the setter.
 * NOTE: this test data is generated by FeatureInteractionTestDataGenerator. DO NOT MODIFY CODE MANUALLY!
 */

class A {
    var x1: String = "100"
        set(`true`) {
            field = "$`true` 10"
        }
}

object B {
    var x2: String = "101"
        set(`false`) = kotlin.Unit
}

var x3: String = "102"
    set(`true`) {
        field = "${`true`} 11"
    }

fun box(): String? {
    val a = A()
    a.x1 = "0"
    B.x2 = "1"
    x3 = "2"

    if (a.x1 != "0 10") return null
    if (B.x2 != "101") return null
    if (x3 != "2 11") return null

    if (!checkSetterParameterName(A::x1, "true")) return null
    if (!checkSetterParameterName(B::x2, "false")) return null
    if (!checkSetterParameterName(::x3, "true")) return null

    return "OK"
}
