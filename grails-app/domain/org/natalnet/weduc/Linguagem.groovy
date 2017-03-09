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
    
    Professor autor
    
    boolean isPrivate
    
    static hasMany = [functions: Funcao, defines: Definicao, acessors: Professor]
    
    static belongsTo = Professor

    static constraints = {
        isPrivate defaultValue: false
    }
    
    static mapping = {
        sort 'name'
        description sqlType: 'longtext'
        header sqlType: 'longtext'
        footnote sqlType: 'longtext'
    }
    
    def clonar(Usuario author){
        def novaLinguagem = new Linguagem(this.properties)

        novaLinguagem.name += " - Cópia"
        novaLinguagem.functions = null
        novaLinguagem.autor = author
        novaLinguagem.footnote = ""
        
        return novaLinguagem.save()
    }
}
