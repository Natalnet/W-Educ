package org.natalnet.weduc

class Operadores {
    
    String name
    String equalTo
    String notEqualTo
    String greaterThan
    String lessThan
    String greaterThanOrEqualTo
    String lessThanOrEqualTo
    String logicalAnd
    String logicalOr
    String logicalNot
    
    static belongsTo = [linguagem: Linguagem]

    static constraints = {
    }
    
    def copy(Linguagem owner) {
        def newOperators = new Operadores(this.properties)
        newOperators.linguagem = owner
        
        return newOperators.save()
    }
}
