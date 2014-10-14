package org.natalnet.weduc

class Funcao {
    
    String name
    String type
    String returnType
    String qntParameters
    String code
    String description
    String typeAliases
    String imageURL
    
    static belongsTo = [linguagem: Linguagem]
    
    static constraints = {
    }
}
