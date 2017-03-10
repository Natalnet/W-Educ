package org.natalnet.weduc

import grails.plugin.springsecurity.annotation.Secured

class CadastroController {

    @Secured("isAnonymous()")
    def novo() {}

    @Secured(value = ["isAnonymous()"], httpMethod = 'POST')
    def salvar() {

        // Faz o cadastro do usuário
        def usuario = new Aluno()
        usuario.username = params.username
        usuario.name = params.name
        usuario.dateOfBirth = params.dateOfBirth
        usuario.address = params.address
        usuario.gender = params.gender
        usuario.telefone = params.telefone
        usuario.institution = params.institution
        usuario.password = params.password
        usuario.email = params.email
        usuario.professor = Professor.findWhere(username: "admin")

        // Verifica se o cadastro foi salvo
        if(usuario.save(flush: true, failOnError: false)) {

                // Fornece privilégios
                def privilegio = Privilegio.findWhere(authority: "ROLE_ALUNO")
                def usuarioPrivilegio = new UsuarioPrivilegio()
                usuarioPrivilegio.privilegio = privilegio
                usuarioPrivilegio.usuario = Usuario.get(usuario.id)
                usuarioPrivilegio.save(flush: true, failOnError: true)

                flash.message = "Cadastro efetuado com sucesso! Tente entrar no sistema."
                redirect controller: "login", action: "auth"

        } else {

                // Verifica se o usuário já existe
                if(Usuario.findWhere(username: params.username)) {

                        flash.message = "O nome de usuário fornecido já existe."

                } else {

                        flash.message = "Verifique se as informações foram fornecidas corretamente."

                }

                redirect action: "novo"

        }

    }

    def editar() {}

    def atualizar() {}
	
}
