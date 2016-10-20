package org.natalnet.weduc

class Section {

    static hasMany = [topics:Topic]
    String title
    
    static constraints = {
    }
    
    static mapping = {
        topics sort: 'title', order: 'asc'
    }
}