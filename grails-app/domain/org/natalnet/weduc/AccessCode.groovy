package org.natalnet.weduc

class AccessCode {
    //Professor professor
    Integer code
    
    //static belongsTo = Professor

    static constraints = {
        code size: 7, unique: true, nullable: true
    }
}
