/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 0.1-106
 * PLACE: expressions, constant-literals, real-literals -> paragraph 3 -> sentence 1
 * NUMBER: 5
 * DESCRIPTION: Real literals suffixed by f/F (float suffix) with omitted a whole-number part and an exponent mark.
 */

// TESTCASE NUMBER: 1
val value_1 = .0e0f

// TESTCASE NUMBER: 2
val value_2 = .0e-00F

// TESTCASE NUMBER: 3
val value_3 = .0E000F

// TESTCASE NUMBER: 4
val value_4 = .0E+0000f

// TESTCASE NUMBER: 5
val value_5 = .0e+0f

// TESTCASE NUMBER: 6
val value_6 = .00e00f

// TESTCASE NUMBER: 7
val value_7 = .000E-000F

// TESTCASE NUMBER: 8
val value_8 = .0E+1F

// TESTCASE NUMBER: 9
val value_9 = .00e22F

// TESTCASE NUMBER: 10
val value_10 = .345678e00000000001F

// TESTCASE NUMBER: 11
val value_11 = .56e-0f

// TESTCASE NUMBER: 12
val value_12 = .65e000000000000F

// TESTCASE NUMBER: 13
val value_13 = .7654E+010f

// TESTCASE NUMBER: 14
val value_14 = .876543E1f

// TESTCASE NUMBER: 15
val value_15 = .98765432e-2f

// TESTCASE NUMBER: 16
val value_16 = .0987654321E-3f

// TESTCASE NUMBER: 17
val value_17 = .1111e4f

// TESTCASE NUMBER: 18
val value_18 = .22222E-5F

// TESTCASE NUMBER: 19
val value_19 = .33333e+6F

// TESTCASE NUMBER: 20
val value_20 = .444444E7F

// TESTCASE NUMBER: 21
val value_21 = .5555555e8f

// TESTCASE NUMBER: 22
val value_22 = <!FLOAT_LITERAL_CONFORMS_ZERO!>.777777777E-308f<!>

// TESTCASE NUMBER: 23
val value_23 = <!FLOAT_LITERAL_CONFORMS_ZERO!>.99999999999e-309F<!>

// TESTCASE NUMBER: 24
val value_24 = .000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000e0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000f

// TESTCASE NUMBER: 25
val value_25 = .000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000e-000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000F

// TESTCASE NUMBER: 26
val value_26 = .000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000e+000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000f
