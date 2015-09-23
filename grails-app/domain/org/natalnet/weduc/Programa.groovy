package org.natalnet.weduc

class Programa {
    
    String nome
    Date criadoEm
    Date modificadoEm
    Long compilacoes
    Long compilacoesBemSucedidas
    Long compilacoesMalSucedidas
    String extensao
    String local
    boolean reduc
    Linguagem linguagem
    
    String codigo
    
    static belongsTo = [usuario: Usuario]

    static constraints = {
        local nullable: true
    }
    static mapping = {
        codigo sqlType: 'longtext'
    }        
        
}

