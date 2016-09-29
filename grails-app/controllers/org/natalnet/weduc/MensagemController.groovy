package org.natalnet.weduc

import grails.plugin.springsecurity.annotation.Secured

class MensagemController {

	def springSecurityService

	@Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR', 'ROLE_ALUNO'])
	def index() {
            def usuario = springSecurityService.getCurrentUser()
	    [usuario: usuario]
        } 

	@Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR', 'ROLE_ALUNO'])
	def escrever() {
                def usuario = springSecurityService.getCurrentUser()
		[usuario: usuario]
                def destinatarios = Usuario.findAllByEnabled(true)
                [destinatarios: destinatarios]
                
	}
        
    	@Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR', 'ROLE_ALUNO'])
	def escreverUsuario() {
                def usuario = springSecurityService.getCurrentUser()
		[usuario: usuario]
                [destinatario: Usuario.get(params.id)]
                
	}
        
        @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR', 'ROLE_ALUNO'])
        def selecionarDest() {
                def destinatarios = Usuario.get(params.id)
                render destinatarios.username
        }
        
         @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR', 'ROLE_ALUNO'])
        def excluirMensagem(){
            def mensagem = Mensagem.get(params.id) 
            mensagem.delete(flush:true)
            redirect controller: "mensagem", action: "todas"
        }
        
        
        @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR', 'ROLE_ALUNO'])
        def listarUsuarios() {
            [destinatarios: Usuario.findAllByEnabled(true)]
        }
    

	@Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR', 'ROLE_ALUNO'])
	def enviar() {                    
		def mensagem = new Mensagem()
		mensagem.destinatario = Usuario.findByUsername(params.destinatario)
		mensagem.autor = springSecurityService.getCurrentUser()
		mensagem.data = new Date()
                mensagem.mensagem = params.mensagem
		mensagem.save(flush: true, failOnError: true)

                if(mensagem.id != null) {
                	flash.message = "Mensagem a " + params.destinatario + " enviada com sucesso."
                	redirect controller: "mensagem", action: "todas"
                } else {
                	flash.message = "Erro ao enviar a mensagem."
                        redirect controller: "mensagem", action: "todas"

                }
		
	
    
        }

	@Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR', 'ROLE_ALUNO'])
	def todas() {
		def usuario = springSecurityService.getCurrentUser()
		def mensagens = Mensagem.findAllWhere(destinatario: usuario)
		[mensagens: mensagens]
	}

}