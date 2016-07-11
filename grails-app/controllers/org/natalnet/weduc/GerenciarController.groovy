package org.natalnet.weduc

import grails.plugin.springsecurity.annotation.Secured

class GerenciarController {

 	def springSecurityService
        
        @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR'])
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
            def mensagensEnviadas = Mensagem.findAll{autor == usuario}
            def mensagensRecebidas = Mensagem.findAll{destinatario == usuario}
            def logins = Login.findAll{usuario == usuario}
            def privilegio = UsuarioPrivilegio.find{usuario == usuario}
            def comentarios = Comment.find{commentBy == usuario}
            def erros = Erro.find{usuario == usuario}
            
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
         
            mensagensEnviadas.each{it -> it.autor = usuarioProf}
            mensagensEnviadas.each{it -> it.save(flush: true)}
            
            mensagensRecebidas.each{it -> it.destinatario = usuarioProf}
            mensagensRecebidas.each{it -> it.save(flush: true)}
            
            comentarios.each{it -> it.commentBy = usuarioProf}
            comentarios.each{it -> it.save(flush: true)}
            
            programas.each{it -> it.usuario = usuarioProf}
            programas.each{it -> it.save(flush: true)}
            
            logins.each{it -> it.usuario = usuarioProf}
            logins.each{it -> it.save(flush: true)}
            
            erro.each{it -> it.usuario = usuarioProf}
            erro.each{it -> it.save(flush: true)}

            
            usuarioProf.username = username + "Prof"
            usuarioProf.save flush: true, failOnError: true 

            def mensagem = new Mensagem()
            mensagem.destinatario = usuario
            mensagem.autor = springSecurityService.getCurrentUser()
            mensagem.data = new Date()
            mensagem.mensagem = "Olá, agora você faz parte do corpo de professores do W-Educ. Seja bem vindo! \n\
                                 Para acessar o sistema como professor e ter acesso a todos os seus dados, utilize o nome de usuário: " + usuario.username + "Prof, e a senha: " + usuario.username + 
                                 "PROF.   Recomendamos que seja realizada a troca de senha imediatamente."   
            mensagem.save(flush: true, failOnError: true)
        
            def mensagemProf = new Mensagem()
            mensagemProf.destinatario = usuarioProf
            mensagemProf.autor = springSecurityService.getCurrentUser()
            mensagemProf.data = new Date()
            mensagemProf.mensagem = "Olá, agora você faz parte do corpo de professores do W-Educ. Seja bem vindo!"
            mensagemProf.save(flush: true, failOnError: true)
        
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