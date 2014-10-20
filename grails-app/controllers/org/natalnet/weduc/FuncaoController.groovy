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
                funcao.description = params.descricao
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
                	redirect action: "nova" //, params: params
                }

	}

	@Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR'])
	def editar() {
		def linguagem = Linguagem.get(params.id)
		[linguagem: linguagem]
	}

	@Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR'])
	def atualizar() {

	
        
        println "Cadastro da linguagem " + linguagem.name + " atualizado por " + springSecurityService.getCurrentUser()

        // Fim do cadastro de linguagem

        if(linguagem.id != null) {
        	flash.message = "Linguagem " + linguagem.name + " atualizada com sucesso."
        	redirect action: "editar", id: linguagem.id
        } else {
        	flash.message = "Erro ao atualizar a linguagem " + linguagem.name + "."
        	redirect action: "editar" //, params: params
        }

	}

	@Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR'])
	def listar() {
		def usuario = springSecurityService.getCurrentUser()
                def linguagem = Linguagem.get(params.id)
		[funcoes: Funcao.findAllByLinguagem(linguagem), linguagem: linguagem]
	}

        @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR'])
        def remover() {
                
                // Define a função
                def funcao = Funcao.get(params.id)
                
                // Configura mensagem ao usuário
                flash.message = "Função " + funcao.name + " removida com sucesso."

                // Desfaz a relação com a linguagem
                funcao.linguagem.removeFromFunctions(function).save(flush: true)

                // Remove a função do banco
                funcao.delete(flush: true)

                redirect action: "listar"

        }
}