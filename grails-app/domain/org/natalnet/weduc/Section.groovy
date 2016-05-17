package org.natalnet.weduc

class Section {
    static hasMany = [topics:Topic]
    String title
    
    static constraints = {
    }
    
}

    