#!/usr/bin/env groovy

// groovy list are plain java.util.List, implementation used when defining list literals are java.util.ArrayList by DEFAULT
def numbers = [1, 2, 3]
assert numbers instanceof List
assert numbers instanceof ArrayList
assert numbers.size() == 3

// create list containing values of heterogeneous type
def heterogeneous = [1, "a", true]
assert heterogeneous.size() == 3

// use a different backing type for list:
// 1. with the 'as' operator
def linkedList = [2, 3, 4] as LinkedList
assert linkedList instanceof LinkedList
// 2. with explicit type declaration
LinkedList otherLinked = [3, 4, 5]
assert otherLinked instanceof LinkedList

// reading and setting values of list
def letters = ['a', 'b', 'c', 'd']
assert letters[0] == 'a' // positive indices
assert letters[1] == 'b'
assert letters[-1] == 'd' // negative indices to access elements from the end of the list
assert letters[-2] == 'c'
letters[2] = 'C' // set value
assert letters[2] == 'C'
letters << 'e' // use << leftShift operator to append elements to a list
assert letters[4] == 'e'
assert letters[-1] == 'e'
assert letters[1, 3] == ['b', 'd'] // access two elements at once, return a new list containing two elements
assert letters[2..4] == ['C', 'd', 'e'] // use a range to access a range of values from the list, from a start to an end element position
// create multi-dimensional list
def multi = [[0, 1], [2, 3]]
assert multi[1][0] == 2
