package com.ociweb

class Book {

    String title
    String contents
    Date datePublished
    Author author
    String description
    Integer revisions = 0

    def beforeUpdate() {
        def dirtyProperties = this.dirtyPropertyNames
        def checkProperties = ['title', 'contents', 'author']

        def updatedProperties = dirtyProperties.findAll { checkProperties.contains it }
        if(updatedProperties) {
            println "Incrementing revisions for book:$id - ${updatedProperties} isDirty"
            revisions++
        }
    }

    static constraints = {

        title nullable: false
        contents nullable: false
        datePublished nullable: false
        author nullable: false
        description nullable: true
        revisions nullable: false

    }
}
