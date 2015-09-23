package org.natalnet.weduc

class Linguagem {
    
    String name
    String description
    String robot
    String extension
    String compileCode
    String compilerFile
    String sendCode
    String sentExtension
    String header
    String footnote
    String mainFunction
    String otherFunctions
    String callFunction
    
    Tipos types
    Operadores operators
    ControleDeFluxo controlFlow
    
    static hasMany = [functions: Funcao, defines: Definicao]

    static belongsTo = [autor: Professor]

    static constraints = {
        description sqlType: 'longtext'
        header sqlType: 'longtext'
        footnote sqlType: 'longtext'
    }
    
}
