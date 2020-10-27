#!/usr/bin/env groovy

def colors = [red: '#FF0000', green: '#00FF00', blue: '#0000FF']

assert colors['red'] == '#FF0000'
assert colors.green == '#00FF00'

colors['pink'] = '#FF00FF'
colors.yellow = '#FFFF00'
assert colors.pink == '#FF00FF'
assert colors['yellow'] == '#FFFF00'

assert colors instanceof LinkedHashMap

assert colors.unknown == null // access a key not present will retrieve a null result

// use values of other types as keys
def numbers = [1: 'one', 2: 'two']
assert numbers[1] == 'one'

def key = 'name'
def person1 = [key: 'Guillaume'] // key will actually be the "key" string, not the value associated with the key variable
assert !person1.containsKey('name')
assert person1.containsKey('key')
def person2 = [(key): 'Guillaume'] // surround the key variable with parentheses, to instruct the parser we are passing a variable rather than defining a string key
assert person2.containsKey('name')
assert !person2.containsKey('key')
