package org.natalnet.weduc

import grails.plugin.springsecurity.annotation.Secured

class PerfilController {

    def springSecurityService
     
    def index() { 
    	def usuario = springSecurityService.getCurrentUser()
        [usuario: usuario]
    }
    
    def editar() {
	def usuario = springSecurityService.getCurrentUser()
	[usuario: usuario]
	}
        
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
 

    
   def excluir(){
       def usuario = springSecurityService.getCurrentUser()    
       def privilegio = UsuarioPrivilegio.find{usuario == usuario}
       
        if(privilegio != null)
           privilegio.delete(flush:true, failOnError: true)
           
        Login.findAll{usuario == usuario}.each{it.delete(flush:true)}

        usuario.delete(flush:true, failOnError: true)
        redirect controller:"logout", action:"index"
        
    }
       

}
