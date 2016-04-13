package com.ociweb

import grails.test.mixin.TestMixin
import grails.test.mixin.gorm.Domain
import grails.test.mixin.hibernate.HibernateTestMixin
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestMixin(HibernateTestMixin)
@Domain([Book, Author])
class BookSpec extends Specification {

    @Unroll
    def "test that a Book's revision is incremented when checked properties are changed"() {

        when: 'a book is created'
        def jeff = new Author(name: 'Jeff Brown').save()
        def book = new Book(title: 'Grails - The Definitive Guide',
                author: jeff,
                description: 'The definitive guide to the Grails Framework',
                contents: 'asdasdasdsadas',
                datePublished: new Date())
        book.save()

        then: 'the revisions has the expected value'
        book.revisions == 0

        when: 'the properties are changed'
        book.title = title
        book.description = description
        book.contents = contents
        book.datePublished = datePublished
        book.save()

        then: 'the actionStep counter should be 0'
        book.revisions == revisions

        where: 'properties changed'
        title                             |contents         |description                                    | datePublished | revisions
        'Grails 2 - The Definitive Guide' |'asdasdasdsadas' |'The definitive guide to the Grails Framework' | new Date()    | 1
    }

}
