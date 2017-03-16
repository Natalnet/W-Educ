package org.natalnet.weduc

class ControleDeFluxo {
    
    String languageName
    String breakCode
    String doCode
    String forCode
    String ifCode
    String repeatCode
    String switchCode
    String whileCode
    
    static belongsTo = [linguagem: Linguagem]

    static constraints = {
    }
    
    def copy(Linguagem owner) {
        def newControlFlow = new ControleDeFluxo(this.properties)
        newControlFlow.linguagem = owner
        
        return newControlFlow.save()
    }
}
