package org.natalnet.weduc;

class Mensagem {

    Usuario autor
    Usuario destinatario
    String assunto
    String mensagem
    Date data

    static constraints = {
        autor nullable: false
        destinatario nullable: false
        mensagem blank: false
    }
    
    static mapping = {
        mensagem sqlType: 'longtext'
    }
}
