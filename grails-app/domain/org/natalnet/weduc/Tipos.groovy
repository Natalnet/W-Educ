package org.natalnet.weduc

class Tipos {
    
    String name
    String declareFalse
    String declareTrue
    String declareFloat
    String declareString
    String declareBoolean
    
    static belongsTo = [linguagem: Linguagem]

    static constraints = {
    }
    
    def copy(Linguagem owner) {
        def newTypes = new Tipos(this.properties)
        newTypes.linguagem = owner
        
        return newTypes.save()
    }
}
