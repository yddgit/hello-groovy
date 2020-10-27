#!/usr/bin/env groovy

// We need to explicitely define the type of the array through coercion or type declaration
String[] arrStr = ['Ananas', 'Banana', 'Kiwi']
assert arrStr instanceof String[]
assert !(arrStr instanceof List)

def numArr = [1, 2, 3] as int[]
assert numArr instanceof int[]
assert numArr.size() == 3

// multi-dimensional arrays
def matrix3 = new Integer[3][3] // define the bounds of a new array
assert matrix3.size() == 3
Integer[][] matrix2 // declare an array without specifying its bounds
matrix2 = [[1, 2], [3, 4]]
assert matrix2 instanceof Integer[][]

// access the elements of an array
String[] names = ['Cédric', 'Guillaume', 'Jochen', 'Paul']
assert names[0] == 'Cédric'
names[2] = 'Blackdrag'
assert names[2] == 'Blackdrag'

// Groovy3 and above support that variant of the Java array initialization
def primes = new int[] {2, 3, 5, 7, 11}
assert primes.size() == 5 && primes.sum() == 28
assert primes.class.name == '[I'

def pets = new String[] {'cat', 'dog'}
assert pets.size() == 2 && pets.sum() == 'catdog'
assert pets.class.name == '[Ljava.lang.String;'

// traditional Groovy alternative still supported
String [] groovyBooks = ['Groovy in Action', 'Making Java Groovy']
assert groovyBooks.every{it.contains('Groovy')}
