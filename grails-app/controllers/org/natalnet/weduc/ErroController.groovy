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

		
		//Testa para cada um dos tipos de erros
		for (int j = 8; j > 0; j--) {
			def dia = new Date()
			def quantidadeErros = 0
			def errosTotais = []
			def erros1 = Erro.findAllWhere(usuario:usuario, tipo:j.toString()).sort{it.data}.reverse()
			dia++
			indiceAtual=0
			
			//Analiza se há erros deste tipo
			if (erros1.size() > 0) {
				dia = erros1.get(0).data

				//Percorre todos os erros
				for (int i = 0; i < erros1.size(); i++) {
					//Vê se mudou de data
					if (erros1.get(i).data.clearTime() < dia.clearTime()) {
						//Se for uma data nova e já houverem 10 na tabela, não colocar mais!
						if (!datas.contains(dia.format('yyyy-MM-dd')) && datas.size() >= 10) {
							break;
						}
						if (j == 8) {
							datas.add(dia.format('yyyy-MM-dd'))
							errosTotais.add(quantidadeErros)
						}
						else {
							while (dia.format('yyyy-MM-dd') != datas.get(indiceAtual)) {
								errosTotais.add(-1)
								indiceAtual++
							}
							indiceAtual++;
							errosTotais.add(quantidadeErros)
						}
						quantidadeErros = erros1.get(i).quant
						dia = erros1.get(i).data
					}
					//Senão, soma os erros
					else if (erros1.get(i).data.clearTime() == dia.clearTime()) {
						quantidadeErros+=erros1.get(i).quant
					}

				}
				if (datas.contains(dia.format('yyyy-MM-dd')) || datas.size() < 10) {
					datas.add(dia.format('yyyy-MM-dd'))
					errosTotais.add(quantidadeErros)
				}

				datas = datas.unique()
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

		[
			aluno:                      Aluno.get(params.id),
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