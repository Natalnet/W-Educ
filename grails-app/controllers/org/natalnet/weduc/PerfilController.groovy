package org.natalnet.weduc

import grails.plugin.springsecurity.annotation.Secured

class PerfilController {

    def springSecurityService
     
    @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR', 'ROLE_ALUNO'])
    def index() { 
    	def usuario = springSecurityService.getCurrentUser()
        [usuario: usuario]
    }
    
    @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR', 'ROLE_ALUNO'])
    def editar() {
	def usuario = springSecurityService.getCurrentUser()
	[usuario: usuario]
	}
    @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR', 'ROLE_ALUNO'])    
    def atualizar(){
        def usuario = springSecurityService.getCurrentUser()    
        usuario.name = params.name.toString()
        usuario.dateofbirth = params.dateofbirth.toString()
        usuario.address = params.address.toString()
        usuario.gender = params.gender.toString()
        usuario.telefone = params.telefone.toString()
        usuario.institution = params.institution.toString()
        
        if(params.password != ""){
            usuario.password = params.password.toString()
        }
        
	usuario.email = params.email.toString()
        
        // Editando o usuário
        usuario.save(flush: true, failOnError: true)
        
        // Fim do cadastro de linguagem

        if(usuario.id != null) {
        	flash.message = "Cadastro do usuário " + usuario.name + " atualizado com sucesso."
        	redirect action: "index"
        } else {
        	flash.message = "Erro ao atualizar o cadastro do usuário  " + usuario.name + "."
        	redirect action: "index"
        }
        
      
    }
  @Secured(['ROLE_ALUNO'])
   def graduar(){
       
        def mensagem = new Mensagem()
        mensagem.destinatario = Usuario.findByUsername("admin")
        mensagem.autor = springSecurityService.getCurrentUser()
        mensagem.data = new Date()
        mensagem.mensagem = "O usuário " + springSecurityService.getCurrentUser().username + " solicitou graduação no sistema."
        mensagem.save(flush: true, failOnError: true)
        
        redirect controller: "admin", action: "index"
   }
       

}
