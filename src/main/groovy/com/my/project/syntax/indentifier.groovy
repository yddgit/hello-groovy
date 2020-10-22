#!/usr/bin/env groovy
def map = [:]

// quoted identifiers appear after the dot of a dotted expression
map."with space double quotes" = "ALLOWED"
map.'with-dash-single-quotes' = "ALLOWED"
assert map."with space double quotes" == "ALLOWED"
assert map.'with-dash-single-quotes' == "ALLOWED"

// different string literals
map.'single quote'
map."double quote"
map.'''triple single quote'''
map."""triple double quote"""
map./slashy string/
map.$/dollar slashy string/$

// GString

def firstname = "Homer"
map."Simpson-${firstname}" = "Homer Simpson"
assert map."Simpson-Homer" == "Homer Simpson"
