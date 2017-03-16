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
    static mapping = {
        description sqlType: 'longtext'
        code sqlType: 'longtext'
    }
    
    def copy(Linguagem owner = null) {
        def novaFuncao = new Funcao(this.properties)
        novaFuncao.typeAliases = ""
        novaFuncao.imageURL = ""
        
        if(owner != null){
            novaFuncao.linguagem = owner
        }else{
            novaFuncao.name += " - Cópia"
        }
        
        return novaFuncao.save()
    }
}
