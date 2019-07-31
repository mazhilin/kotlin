/*
 * KOTLIN PSI SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 0.1-106
 * PLACE: constant-literals, integer-literals, binary-integer-literals -> paragraph 1 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: Binary integer literals with underscore symbols in valid places.
 */

val value = 0b0_00_01_11_11_00_00
val value = 0B0_1_0_1_0_1_0_1_0_1_0_1
val value = 0b101_010_10101
val value = 0B00000000000________________________0
val value = 0b0_0
val value = 0B00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000_00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000
val value = 0B11111111111111111111111111_________1111111111111111111111111111111111111
val value = 0b1_______________________________________________________________________________________________________________________________________________________0
