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

		// Mês e ano atuais
		def datas = []
		def errosVariavel = []
		def errosFuncao = []
		def errosTarefa = []
		def errosEstrutura = []
		def errosCondicao = []
		def errosRepeticao = []
		def errosNome = []
		def errosSintaxe = []

		def indiceAtual=0;

		

		for (int j = 8; j > 0; j--) {
			def dia = new Date()
			def quantidadeErros = 0
			def errosTotais = []
			def erros1 = Erro.findAllWhere(usuario:usuario, tipo:j.toString()).sort{it.data}.reverse()
			dia++
			indiceAtual=0
			
			if (erros1.size() > 0) {
				dia = erros1.get(0).data

				for (int i = 0; i < erros1.size(); i++) {
					if (erros1.get(i).data.clearTime() < dia.clearTime()) {
						if (j == 8) {
							datas.add(dia.format('yyyy-MM-dd'))
							errosTotais.add(quantidadeErros)
						}
						else {
							println("Nao é 8! " + j)
							println(dia.format('yyyy-MM-dd') + " " + datas.get(indiceAtual))
							while (dia.format('yyyy-MM-dd') != datas.get(indiceAtual)) {
								println(j + " -> add -1")
								errosTotais.add(-1)
								indiceAtual++
							}
							indiceAtual++;
							errosTotais.add(quantidadeErros)
						}
						quantidadeErros = erros1.get(i).quant
						dia = erros1.get(i).data
					}
					else if (erros1.get(i).data.clearTime() == dia.clearTime()) {
						quantidadeErros+=erros1.get(i).quant
					}

				}
				datas.add(dia.format('yyyy-MM-dd'))
				errosTotais.add(quantidadeErros)
			}

			switch(j) {
				case 1: errosVariavel = errosTotais
						break
				case 2: errosFuncao = errosTotais
						break
				case 3: errosTarefa = errosTotais
						break
				case 4: errosEstrutura = errosTotais
						break
				case 5: errosCondicao = errosTotais
						break
				case 6: errosRepeticao = errosTotais
						break
				case 7: errosNome = errosTotais
						break
				case 8: errosSintaxe = errosTotais
						break
			}
		}

		datas = datas.unique()

		for (int i = 0; i < datas.size(); i++) {
			println(datas.get(i))
		}

		println(errosSintaxe)
		println(errosNome)
		println(errosRepeticao)
		println(errosCondicao)
		println(errosEstrutura)

		[
			datas: 						datas, 
			errosVariavel:              errosVariavel,
			errosFuncao:                errosFuncao,
			errosTarefa:                errosTarefa,
			errosEstrutura:             errosEstrutura,
			errosCondicao:              errosCondicao,
			errosRepeticao:             errosRepeticao,
			errosNome:                  errosNome,
			errosSintaxe:               errosSintaxe
		]
	}

}