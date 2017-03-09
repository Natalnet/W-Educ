package org.natalnet.weduc

import grails.plugin.springsecurity.annotation.Secured
import groovy.sql.Sql

class GerenciarController {

    def springSecurityService
    def dataSource

    @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR'])
    def index() {
        redirect action: "usuarios"
    }

    @Secured('ROLE_ADMIN')
    def usuarios() {
        render(view: "usuarios",  model: 
            [alunos: Aluno.findAllByEnabled(true),
            alunosI: Aluno.findAllByEnabled(false)]
        )
    }

    @Secured('ROLE_ADMIN')
    def professores() {
        [professores: Professor.findAllByEnabled(true),
         professoresI: Professor.findAllByEnabled(false)]
    }

    @Secured('ROLE_ADMIN')
    def alternar() {
        def usuario = Usuario.get(params.id)
        def to_class = "org.natalnet.weduc.Professor"

        changeRole(usuario, to_class, "ROLE_ALUNO", "ROLE_PROFESSOR")

        usuario.professor = null

        if(usuario.save(flush: true)){
            redirect action: "usuarios" 
        }
    }

    @Secured('ROLE_ADMIN')
    def alternarProf() {
        def usuario = Usuario.get(params.id)
        def to_class = "org.natalnet.weduc.Aluno"

        changeRole(usuario, to_class, "ROLE_PROFESSOR", "ROLE_ALUNO")

        //usuario.professor = null

        if(usuario.save(flush: true)){
            redirect action: "professores" 
        }
    }

    def changeRole(Usuario usuario, String to_class, String role_from, String role_to){
        def r_from = Privilegio.findWhere(authority: role_from)
        def r_to = Privilegio.findWhere(authority: role_to)

        UsuarioPrivilegio.remove(usuario, r_from)
        UsuarioPrivilegio.create(usuario, r_to)

        def sql = new Sql(dataSource)
        sql.executeUpdate('update usuario set class=? where id=?', [to_class, usuario.id])
    }
        
    @Secured('ROLE_ADMIN')
    def desativar() {
        def usuario = Usuario.get(params.id)

        if(usuario.username != "admin"){

            usuario.enabled = false
            usuario.save(flush: true, failOnError: true)


            if(params.tipo != "Professor")
                redirect action: "usuarios"
            else
                redirect action: "professores"  

        }
        else{
            render "Não é possível desativar o administrador do sistema."
        }
    }
        
    @Secured('ROLE_ADMIN')
    def ativar() {
        def usuario = Usuario.get(params.id)

        usuario.enabled = true
        usuario.save(flush: true, failOnError: true)

        if(params.tipo != "Professor")
            redirect action: "usuarios"
        else
            redirect action: "professores"  
    }    
}