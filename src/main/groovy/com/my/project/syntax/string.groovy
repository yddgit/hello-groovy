#!/usr/bin/env groovy
// java.lang.String
// groovy.lang.GString also called interpolated string

def name = "Ace"

println 'a single-quoted string are plain java.lang.String and don\'t support interpolation ${name}'

assert 'string can be concatenated with the + operator' == 'string can be ' + 'concatenated' + ' with the + operator'

println '''a triple-single-quoted string are plain java.lang.String and don\'t support interpolation ${name}'''

def multiline = '''line one
line two
line three'''
println multiline

def startAndEndWithNewLine = '''
line one
line two
line three
'''
assert startAndEndWithNewLine.startsWith('\n')
assert startAndEndWithNewLine.endsWith('\n')

def stripFirstNewLine = '''\
line one
line two
line three
'''
assert !stripFirstNewLine.startsWith('\n')

// escape special characters
println "backspace:a\b"
println "formfeed: \f"
println "newline: \n"
println "carriage return: \r"
//println "single space: \s"
println "tabulation: \t"
println "backslash: \\"
println 'single quote within a single-quoted string: \''
println "double quote within a double-quoted string: \""

// unicode escape sequence
assert '中文￥' == '\u4E2D\u6587\uFFE5'

assert "a double-quoted strings are plain java.lang.String if there's no interpolated expression," instanceof String
assert "but are groovy.lang.GString instances if interpolation is present: ${name}" instanceof GString

assert "Hello ${name}".toString() == 'Hello Ace'
assert "The sum of 2 and 3 equals ${2 + 3}".toString() == 'The sum of 2 and 3 equals 5'

def person = [name: 'Guillaume', age: 36]
// use a lone $ sign prefixing a dotted expression. Expressions containing parentheses would be invalid
assert "$person.name is $person.age years old" == 'Guillaume is 36 years old'

def number = 3.14
groovy.test.GroovyAssert.shouldFail(MissingPropertyException) {
    println "$number.toString()"
    // being interpreted by the parser as "${number.toString}()"
}

// if the expression is ambiguous, you need to keep the curly braces
String thing = 'treasure'
groovy.test.GroovyAssert.shouldFail(MissingPropertyException) {
    println 'x of treasure is represented by treasure.x' == "x of $thing is represented by $thing.x"
    // 'x of treasure is represented by treasure.x' == "x of $thing is represented by ${thing.x}"
}
assert 'x of treasure is represented by treasure.x' == "x of $thing is represented by ${thing}.x"

// espace $ ${} in a GString
assert '$5' == "\$5"
assert '${name}' == "\${name}"

// interpolating closure expressions: only closures with zero or one parameters are allowed
assert "1 + 2 = ${-> 3}" == '1 + 2 = 3'
// Overloads the leftShift operator for Writer to allow an object to be written using Groovy's default representation for the object.
assert "1 + 2 = ${w -> w << 3}" == '1 + 2 = 3'
// closures have an interesting advantage over mere expressions: lazy evaluation
number = 1
def eagerGString = "value == ${number}"
def lazyGString = "value == ${->number}"
assert eagerGString == "value == 1"
assert lazyGString == "value == 1"
number = 2
assert eagerGString == "value == 1"
assert lazyGString == "value == 2"

// when a method expects a java.lang.String, the toString() of GString is automatically and transparently called
String takeString(String message) {
    assert message instanceof String
    return message
}
def message = "The message is ${'hello'}"
assert message instanceof GString
def result = takeString(message)
assert result instanceof String
assert result == 'The message is hello'

// String and GString hashCodes are different
assert "one: ${1}".hashCode() != "one: 1".hashCode()

// using GString as map keys should be avoided
def key = "a"
def m = ["${key}": "letter ${key}"]
assert m["a"] == null // because hashCode is different

// triple-double-quoted string behave like double-quoted string
// neither double quotes nor single quotes need be escaped in triple-double-quoted strings
name = 'Groovy'
def template = """
    Dear Mr ${name},

    You're the winner of the lottery!

    Yours sincerly,

    Dave
"""
assert template.toString().contains("Groovy")

// slashy string for defining regular expressions, no need to escape backslashes
def fooPattern = /.*foo.*/
assert fooPattern == '.*foo.*'
def escapeSlash = /The character \/ is a forward slash/
assert escapeSlash == 'The character / is a forward slash'
def multilineSlashy = /one
two
three/
assert multilineSlashy.contains('\n')
def color = 'blue'
def interpolatedSlashy = /a ${color} car/
assert interpolatedSlashy == 'a blue car'
def noEscaping = /\t/
assert noEscaping == '\\t'
// special cases:
// an empty slashy string cannot be represented: //
// slashy string can't end with a backslash: /end with backslash \/
// you can instead use a special trick
def endWithBackslash = /ends with backslash ${'\\'}/
assert endWithBackslash == 'ends with backslash \\'

// dollar slashy string, the escaping character is the dollar sign, and it can escape another dollar, or a forward slash
name = "Guillaume"
def date = "April, 1st"
def dollarSlashy = $/
    Hello $name,
    today we're ${date}

    $ dollar sign
    $$ escaped dollar sign
    \ backslash
    / forward slash
    $/ escaped forward slash
    $$$/ escaped opening dollar slashy
    $/$$ escaped closing dollar slashy
/$
assert [
    'Guillaume',
    'April, 1st',
    '$ dollar sign',
    '$ escaped dollar sign',
    '\\ backslash',
    '/ forward slash',
    '/ escaped forward slash',
    '$/ escaped opening dollar slashy',
    '/$ escaped closing dollar slashy'
].every { dollarSlashy.contains(it)}

// characters
char c1 = 'A' // a char variable
assert c1 instanceof Character
def c2 = 'B' as char // when a char value must be passed as argument of a method call
assert c2 instanceof Character
def c3 = (char)'C' // when a char value must be passed as argument of a method call
assert c3 instanceof Character
