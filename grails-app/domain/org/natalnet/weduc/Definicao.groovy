package org.natalnet.weduc

class Definicao {
    
    String name
    boolean alreadyExists
    String text
    String type
    
    static belongsTo = [linguagem: Linguagem]

    static constraints = {
    }
}
