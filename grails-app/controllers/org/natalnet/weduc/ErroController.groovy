package org.natalnet.weduc

import grails.plugin.springsecurity.annotation.Secured
import java.text.SimpleDateFormat

class ErroController {

	def springSecurityService

	def index() {}

	@Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR'])
	def exibir() {

		def usuario = Usuario.findWhere(id:Long.parseLong(params.id.trim()))

		// Estatíscias de acesso - Início

		// Variáveis auxiliares
		def datas = []
		def totalDeErros = [:]

		// Testando cada um dos 8 tipos de erros
		(8..1).each { tipo, indice->
			def data = null
			def totalDeErros[indice] = 0
			def erros = Erro.findAllWhere(usuario:usuario, tipo:tipo.toString()).sort{ -it.data }
			// Para cada erro desse tipo encontrado
			erros.each { erro ->
				// A data do erro atual é a mesma anterior?
				if(erro.data.clearTime() == data.clearTime()) {
					// Sim, então vamos somar o número de erros
					totalDeErros[indice] += erro.quant
				} else if (datas.size() < 10) {
					// A data é diferente, mas há espaço na lista, vamos somar os erros
					totalDeErros[indice] += erro.quant
					// Adicionando a nova data na lista
					datas.add(data)
				}
				// Guarda a data do erro atual para comparar na próxima vez
				data = erro.data
			}
		}

		[
			aluno: Aluno.get(params.id),
			datas: datas,
			errosVariavel: totalDeErros[1],
			errosFuncao: totalDeErros[2],
			errosTarefa: totalDeErros[3],
			errosEstrutura: totalDeErros[4],
			errosCondicao: totalDeErros[5],
			errosRepeticao: totalDeErros[6],
			errosNome: totalDeErros[7],
			errosSintaxe: totalDeErros[8],
		]
	}
}
