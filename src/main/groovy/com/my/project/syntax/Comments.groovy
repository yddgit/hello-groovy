package com.my.project.syntax

/**@
 * A Classs Description, this doc can be retained at RUNTIME
 */
class Comments {
    /** name of person */
    String name
    /**
     * Say hello to the other
     * @param other other person
     * @return a greeting message
     */
    String greet(String other) {
        "Hello ${other}"
    }
    /**@
     * Method groovydoc for bar, this doc can be retained at RUNTIME
     */
    String bar() {
        println "This method doc can be retained at runtime"
    }
}
