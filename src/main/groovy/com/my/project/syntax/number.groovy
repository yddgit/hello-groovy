#!/usr/bin/env groovy

// the integral literal types are the same as in java
byte b = 1
char c = 2
short s = 3
int i = 4
long l = 5
BigInteger bi = 6
assert b + c + s + i + l + bi == 21

// def keyword
def a1 = 1
assert a1 instanceof Integer
def a2 = 2147483647 // Integer.MAX_VALUE
assert a2 instanceof Integer
def a3 = 2147483648 // Integer.MAX_VALUE + 1
assert a3 instanceof Long
def a4 = 9223372036854775807 // Long.MAX_VALUE
assert a4 instanceof Long
def a5 = 9223372036854775808 // Long.MAX_VALUE + 1
assert a5 instanceof BigInteger
def na1 = -1
assert na1 instanceof Integer
def na2 = -2147483648 // Integer.MIN_VALUE
assert na2 instanceof Integer
def na3 = -2147483649 // Integer.MIN_VALUE - 1
assert na3 instanceof Long
def na4 = -9223372036854775808 // Long.MIN_VALUE
assert na4 instanceof Long
def na5 = -9223372036854775809 // Long.MIN_VALUE - 1
assert na5 instanceof BigInteger

// binary
int xInt = 0b110
assert xInt == 6
short xShort = 0b111
assert xShort == 7 as short
byte xByte = 0b11
assert xByte == 3 as byte
long xLong = 0b10000000000
assert xLong == 1024
BigInteger xBigInteger = 0b1111111111
assert xBigInteger == 1023g
int xNegativeInt = -0b11110
assert xNegativeInt == -30

// octal
xInt = 077
assert xInt == 63
xShort = 011
assert xShort == 9 as short
xByte = 032
assert xByte == 26 as byte
xLong = 0246
assert xLong == 166l
xBigInteger = 01111
assert xBigInteger == 585g
xNegativeInt = -077
assert xNegativeInt -63

// hex
xInt = 0x77
assert xInt == 119
xShort = 0xaa
assert xShort == 170 as short
xByte = 0x3a
assert xByte == 58 as byte
xLong = 0xffff
assert xLong == 65535l
xBigInteger = 0xaaaa
assert xBigInteger == 43690g
Double xDouble = new Double('0x1.0p0')
assert xDouble == 1.0d
xNegativeInt = -0x77
assert xNegativeInt == -119

// the decimal literal types are the same as in java
float f = 1.234
double d = 2.345
BigDecimal bd = 3.456
assert new java.text.DecimalFormat("#.###").format(f + d + bd) == "7.035"

assert 1e3 == 1_000.0
assert 2E4 == 20_000.0
assert 3e+1 == 30.0
assert 4E-2 == 0.04
assert 5e-1 == 0.5

// underscore
long creditCardNumber = 1234_5678_9012_3456L
long socialSecurityNumber = 999_99_9999L
double monetaryAmount = 12_345_132.12
long hexByte = 0xFF_EC_DE_5e
long hexWord = 0xFFEC_DE5E
long maxLong = 0x7fff_ffff_ffff_ffffL
long alsoMaxLong = 9_223_372_036_854_775_807L
long bytes = 0b11010010_01101001_10010100_10010010
println creditCardNumber + socialSecurityNumber + monetaryAmount + hexByte + hexWord + maxLong + alsoMaxLong + bytes

// number type suffix
assert 42I == new Integer('42')
assert 42i == new Integer('42') // more readable
assert 123L == new Long("123") // more readable
assert 2147483648 == new Long('2147483648')
assert 456G == new BigInteger('456')
assert 456g == new BigInteger('456')
assert 123.45 == new BigDecimal('123.45') // default BigDecimal type used
assert 1.200065D == new Double('1.200065')
assert 1.234F == new Float('1.234')
assert 1.23E23D == new Double('1.23E23')
assert 0b1111L.class == Long
assert 0xFFi.class == Integer
assert 034G.class == BigInteger

// https://docs.groovy-lang.org/latest/html/documentation/#_math_operations
// binary operations between float, double and BigDecimal result in double

// the power operator

// base and exponent are ints and the result can be represented by an Integer
assert 2 ** 3 instanceof Integer //  8
assert 10 ** 9 instanceof Integer //  1_000_000_000
// the base is a long, so fit the result in a Long
// (although it could have fit in an Integer)
assert 5L ** 2 instanceof Long //  25
// the result can't be represented as an Integer or Long, so return a BigInteger
assert 100 ** 10 instanceof BigInteger //  10e20
assert 1234 ** 123 instanceof BigInteger //  170515806212727042875...
// the base is a BigDecimal and the exponent a negative int
// but the result can be represented as an Integer
assert 0.5 ** -2 instanceof Integer //  4
// the base is an int, and the exponent a negative float
// but again, the result can be represented as an Integer
assert 1 ** -0.3f instanceof Integer //  1
// the base is an int, and the exponent a negative int
// but the result will be calculated as a Double
// (both base and exponent are actually converted to doubles)
assert 10 ** -1 instanceof Double //  0.1
// the base is a BigDecimal, and the exponent is an int, so return a BigDecimal
assert 1.2 ** 10 instanceof BigDecimal //  6.1917364224
// the base is a float or double, and the exponent is an int
// but the result can only be represented as a Double value
assert 3.4f ** 5 instanceof Double //  454.35430372146965
assert 5.6d ** 2 instanceof Double //  31.359999999999996
// the exponent is a decimal value
// and the result can only be represented as a Double value
assert 7.8 ** 1.9 instanceof Double //  49.542708423868476
assert 2 ** 0.1f instanceof Double //  1.0717734636432956
