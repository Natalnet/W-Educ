package org.natalnet.weduc

class CadastroController {

	def index() {}

	def novo() {}

	def salvar() {
		// Faz o cadastro do usuário
		def usuario = new Aluno(params)
		println params
		/*usuario.username = params.username
        usuario.name = params.name
        usuario.dateofbirth = params.dateofbirth
        usuario.address = params.address
        usuario.gender = params.gender
        usuario.telefone = params.telefone
        usuario.institution = params.institution
		usuario.password = params.password
		usuario.email = params.email*/

		// Verifica se o cadastro foi salvo
		if(usuario.save(flush: true, failOnError: false)) {
			System.out.println("Passou do 2")
			// Fornece privilégios
			def privilegio = Privilegio.findWhere(authority: "ROLE_ALUNO")
			def usuarioPrivilegio = new UsuarioPrivilegio()
			usuarioPrivilegio.privilegio = privilegio
			usuarioPrivilegio.usuario = Usuario.get(usuario.id)
			usuarioPrivilegio.save(flush: true, failOnError: true)

			flash.message = "Cadastro efetuado com sucesso! Tente entrar no sistema."
			redirect controller: "login", action: "auth"

		} else {
			System.out.println("Passou do 3")
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
