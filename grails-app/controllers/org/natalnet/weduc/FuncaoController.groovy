package org.natalnet.weduc

import grails.plugin.springsecurity.annotation.Secured

class FuncaoController {

        def springSecurityService

	@Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR'])
	def index() {
		redirect action: "listar"
	}

	@Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR'])
	def nova() {
                [linguagem: Linguagem.get(params.id)]
        }

	@Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR'])
	def salvar() {

                def linguagem = Linguagem.get(params.id)

                // Inicio do cadastro de função

                def funcao = new Funcao()

                funcao.name = params.nome
                funcao.type = params.tipo
                funcao.returnType = params.retorno
                funcao.qntParameters = params.qntParametros
                funcao.code = params.codigo
                funcao.description = params.descricao.toString()
                funcao.linguagem = linguagem
                funcao.typeAliases = ""
                funcao.imageURL = ""

                funcao.save(flush: true)

                linguagem.addToFunctions(funcao).save(flush: true)

                // Fim do cadastro de função

                if(funcao.id != null) {
                	flash.message = "Função " + funcao.name + " cadastrada com sucesso."
                	redirect action: "editar", id: funcao.id
                } else {
                	flash.message = "Erro ao cadastrar a função " + funcao.name + "."
                	redirect action: "nova", id: linguagem.id //, params: params
                }

	}

	@Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR'])
	def editar() {
		def funcao = Funcao.get(params.id)
		[funcao: funcao]
	}

	@Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR'])
	def atualizar() {

                // Inicio da atualização da função

                def funcao = Funcao.get(params.id)
                def linguagem = funcao.linguagem

                funcao.name = params.nome
                funcao.type = params.tipo
                funcao.returnType = params.retorno
                funcao.qntParameters = params.qntParametros
                funcao.code = params.codigo
                funcao.description = params.descricao
                funcao.linguagem = linguagem
                funcao.typeAliases = ""
                funcao.imageURL = ""

                funcao.save(flush: true)
        
                println "Cadastro da função " + funcao.name + " da linguagem " + linguagem.name + " atualizado por " + springSecurityService.getCurrentUser()

                // Fim da atualização da função

                if(linguagem.id != null) {
                	flash.message = "Linguagem " + linguagem.name + " atualizada com sucesso."
                	redirect action: "editar", id: funcao.id
                } else {
                	flash.message = "Erro ao atualizar a linguagem " + linguagem.name + "."
                	redirect action: "editar", id: funcao.id //, params: params
                }

	}

	@Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR'])
	def listar() {
		def usuario = springSecurityService.getCurrentUser()
                def linguagem = Linguagem.get(params.id)
                
                def canChange = (usuario == linguagem.autor) || request.isUserInRole('ROLE_ADMIN')
        
		[funcoes: Funcao.findAllByLinguagem(linguagem), linguagem: linguagem, canChange: canChange]
	}

        @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR'])
        def remover() {
                
                // Define a função
                def funcao = Funcao.get(params.id)

                // Define a linguagem
                def linguagem = Linguagem.get(funcao.linguagem.id)
                
                // Configura mensagem ao usuário
                flash.message = "Função " + funcao.name + " removida com sucesso."

                // Desfaz a relação com a linguagem
                // linguagem.removeFromFunctions(funcao).save(flush: true)

                // Remove a função do banco
                funcao.delete(flush: true)

                redirect action: "listar", id: linguagem.id

        }
}