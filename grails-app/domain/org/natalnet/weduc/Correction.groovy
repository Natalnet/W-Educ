package org.natalnet.weduc

class Correction {
    Programa program
    String originalCode
    String correctedCode
    String comments
    Date correctedOn
    
    static belongsTo = [student: Aluno]
    
    static constraints = {
        originalCode blank: false
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
