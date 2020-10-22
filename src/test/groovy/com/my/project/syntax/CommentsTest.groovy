package com.my.project.syntax

import org.junit.jupiter.api.Test

class CommentsTest {
    @Test
    void testGroovydocAtRuntime() {
        assert Comments.class.groovydoc.content.contains("A Classs Description")
        assert Comments.class.getMethod("bar", new Class[0]).groovydoc.content.contains("Method groovydoc for bar")
    }
}
