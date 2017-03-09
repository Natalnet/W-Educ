package org.natalnet.weduc

class Privilegio {

    String authority

    static mapping = {
            cache true
    }

    static constraints = {
            authority blank: false, unique: true
    }
}
