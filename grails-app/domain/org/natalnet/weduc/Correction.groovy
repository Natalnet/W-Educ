package org.natalnet.weduc

class Correction {
    String originalCode
    String correctedCode
    String comments
    Date requestedOn
    Date correctedOn
    
    static belongsTo = [program: Programa]
    
    static constraints = {
        originalCode blank: false
        requestedOn blank: false
        correctedCode nullable: true
        comments nullable: true
        correctedOn nullable: true
    }
    
    static mapping = {
        originalCode sqlType: 'longtext'
        correctedCode sqlType: 'longtext'
    }
    
    def isCorrected(){
        return correctedOn != null
    }
}
