package org.natalnet.weduc

import grails.plugin.springsecurity.annotation.Secured

class GerenciarController {

 	def springSecurityService

	def index() {
		redirect action: "usuarios"
	}

	@Secured('ROLE_ADMIN')
	def usuarios() {
		[alunos: Aluno.findAll()]
	}
        
    	@Secured('ROLE_ADMIN')
	def professores() {
		[professores: Professor.findAll()]
	}
        
        @Secured('ROLE_ADMIN')
	def alternar() {
            
            def usuario = Usuario.get(params.id)
            def programas = Programa.findAll{usuario == usuario}
            def logins = Login.findAll{usuario == usuario}
            def privilegio = UsuarioPrivilegio.find{usuario == usuario}
            
            def username = usuario.username.toString()
            
            def  usuarioProf = new Professor()
    		usuarioProf.username = usuario.username + "PROFTEMP"
    		usuarioProf.password = usuario.username + "PROF"
    		usuarioProf.email = usuario.email
                usuarioProf.name = usuario.name
                usuarioProf.institution = usuario.institution
                usuarioProf.dateofbirth = usuario.dateofbirth
                usuarioProf.gender = usuario.gender
                usuarioProf.address = usuario.address
                usuarioProf.telefone = usuario.telefone
    		
            def privilegioProf = Privilegio.findWhere(authority: "ROLE_PROFESSOR")
            def usuarioPrivilegio = new UsuarioPrivilegio()
    		usuarioPrivilegio.usuario = usuarioProf
    		usuarioPrivilegio.privilegio = privilegioProf    
        
            usuarioProf.save flush: true, failOnError: true       
            usuarioPrivilegio.save flush: true, failOnError: true
         
            programas.each{it -> it.usuario = usuarioProf}
            programas.each{it -> it.save(flush: true)}
        	
            //não deu certo a senha e a troca dos programas.    

            if(privilegio != null)
               privilegio.delete(flush:true, failOnError: true)
               
            
            Programa.findAll{usuario == usuario}.each{it.delete(flush:true)}
            Login.findAll{usuario == usuario}.each{it.delete(flush:true)}

            usuario.delete(flush:true, failOnError: true)
            
            usuarioProf.username = username
            usuarioProf.save flush: true, failOnError: true 
        
            def mensagem = new Mensagem()
            mensagem.destinatario = springSecurityService.getCurrentUser()
            mensagem.autor = usuarioProf
            mensagem.data = new Date()
            mensagem.mensagem = "Olá, agora você faz parte do corpo de professores do W-Educ. Seja bem vindo!"
            mensagem.save(flush: true, failOnError: true)
        
            redirect action: "usuarios"         
        
	}
        
     
        
    	@Secured('ROLE_ADMIN')
	def excluir() {
            
            def usuario = Usuario.get(params.id)
            
            if(usuario.username != "admin"){
                def privilegio = UsuarioPrivilegio.find{usuario == usuario}

                if(privilegio != null)
                   privilegio.delete(flush:true, failOnError: true)


                Programa.findAll{usuario == usuario}.each{it.delete(flush:true)}
                Login.findAll{usuario == usuario}.each{it.delete(flush:true)}
                Mensagem.findAll{autor == usuario}.each{it.delete(flush:true)}
                Mensagem.findAll{destinatario == usuario}.each{it.delete(flush:true)}

                if(params.tipo != "Professor")
                    RequisicaoDeAluno.findAll{aluno == usuario}.each{it.delete(flush:true)}
                else
                    RequisicaoDeAluno.findAll{professor == usuario}.each{it.delete(flush:true)}

                usuario.delete(flush:true, failOnError: true)

                if(params.tipo != "Professor")
                    redirect action: "usuarios"
                else
                    redirect action: "professores"  

            }
            else{
                render "Não é possível excluir o administrador do sistema."
            }
        }
        
        
    
        
}