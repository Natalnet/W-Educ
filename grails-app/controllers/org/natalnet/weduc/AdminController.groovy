package org.natalnet.weduc

import grails.plugin.springsecurity.annotation.Secured

class AdminController {

	def springSecurityService
    
	@Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR', 'ROLE_ALUNO'])
	def index() {

		def usuario = springSecurityService.getCurrentUser()

		// Estatíscias de acesso - Início

		// Mês e ano atuais
		def data = new Date()
		def mes = Integer.parseInt(data.format("MM"))
		def ano = Integer.parseInt(data.format("yyyy"))
		def datas = []
		def loginsUnicos = []
		def loginsTotais = []

		// Últimos cinco meses
		(1..5).each {

			// Define chaves primárias
			datas.add(ano + "-" + mes)

			// Define datas base
			def dataAnterior = new Date().parse("yyyy-MM-dd", ano + "-" + mes + "-01")
			def dataPosterior = new Date().parse("yyyy-MM-dd", ((mes == 12 ? ano + 1 : ano) + "-" + (mes == 12 ? 1 : mes + 1) + "-01")).minus(1)
			
			// Conta logins totais
			loginsTotais.add(Login.withCriteria {
				between("data", dataAnterior, dataPosterior)
				eq("usuario", usuario)
			}.size())

			// Conta logins únicos
			loginsUnicos.add(Login.withCriteria {
				between("data", dataAnterior, dataPosterior)
				eq("usuario", usuario)
				eq("primeiro", true)
			}.size())

			// Decrementa mês e ano
			mes = mes == 1 ? 12 : mes - 1
			ano = mes == 1 ? ano - 1 : ano

		}

		// Estatísticas de acesso - Fim

		// Minhas estatísticas - Início

		def programasCadastrados = usuario.programas

		def totalDeCompilacoes = 0
		def compilacoesBemSucedidas = 0
		def compilacoesMalSucedidas = 0
		def linguagensUtilizadas = 0
		def listaDeLinguagens = []

		programasCadastrados.each {
			totalDeCompilacoes += it.compilacoes
			compilacoesBemSucedidas += it.compilacoesBemSucedidas
			compilacoesMalSucedidas += it.compilacoesMalSucedidas
			listaDeLinguagens.add(it.linguagem)
		}

		linguagensUtilizadas = listaDeLinguagens.unique{}.size()

		// Minhas estatísticas - Fim

		[
			datas: 						datas, 
			loginsUnicos: 				loginsUnicos, 
			loginsTotais: 				loginsTotais,
			programasCadastrados: 		programasCadastrados.size(),
			totalDeCompilacoes: 		totalDeCompilacoes,
			compilacoesBemSucedidas: 	compilacoesBemSucedidas,
			compilacoesMalSucedidas: 	compilacoesMalSucedidas,
			linguagensUtilizadas: 		linguagensUtilizadas
		]
	}

}