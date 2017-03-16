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
    
    boolean isPrivate
    
    Professor autor
    
    static belongsTo = Professor
    
    static hasOne = [types: Tipos, operators: Operadores, controlFlow: ControleDeFluxo]
    
    static hasMany = [functions: Funcao, defines: Definicao, acessors: Professor]

    static constraints = {
        isPrivate defaultValue: false
        header nullable: true
        footnote nullable: true
    }
    
    static mapping = {
        sort 'name'
        description sqlType: 'longtext'
        header sqlType: 'longtext'
        footnote sqlType: 'longtext'
    }
    
    def copy(Usuario author){
        try {
            def novaLinguagem = new Linguagem(this.properties)

            novaLinguagem.name += " - Cópia"
            novaLinguagem.functions = null
            novaLinguagem.autor = author

            novaLinguagem = novaLinguagem.save()
            
            if (!novaLinguagem) {
                return null
            }

            this.functions.each {
                it.copy(novaLinguagem)
            }

            this.types.copy(novaLinguagem)
            this.operators.copy(novaLinguagem)
            this.controlFlow.copy(novaLinguagem)
        
            return novaLinguagem
        } catch (Exception e){
            return null
        }
    }
}
