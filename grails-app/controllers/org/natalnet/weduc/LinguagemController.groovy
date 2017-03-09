package org.natalnet.weduc

import grails.plugin.springsecurity.annotation.Secured
import org.apache.commons.io.FileUtils;

class LinguagemController {

    def springSecurityService

    @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR'])
    def index() {
        redirect action: "listar"
    }

    @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR'])
    def nova() {}

    @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR'])
    def salvar() {

        // Início do cadastro de linguagem

        // Linguagem propriamente dita
        def linguagem = new Linguagem()
        linguagem.name = params.nome.toString()
        linguagem.description = params.descricao.toString()
        linguagem.robot = params.robo.toString()
        linguagem.extension = params.extensao.toString()
        linguagem.compileCode = params.codigoDeCompilacao.toString()
        linguagem.compilerFile = params.arquivo.toString()
        linguagem.sendCode = params.codigoDeEnvio.toString()
        linguagem.sentExtension = params.extensaoDoArquivo.toString()
        linguagem.header = params.cabecalho.toString()
        linguagem.footnote = params.rodape.toString()
        linguagem.mainFunction = params.funcaoPrincipal.toString()
        linguagem.otherFunctions = params.funcao.toString()
        linguagem.callFunction = params.chamadaDeFuncao.toString()
        linguagem.autor = springSecurityService.getCurrentUser()

        if(request.isUserInRole('ROLE_ADMIN')){
            linguagem.isSystemLanguage = true
        }

        // Tipos da linguagem
        linguagem.types = new Tipos()
        linguagem.types.name = ""
        linguagem.types.declareFalse = params.falso.toString()
        linguagem.types.declareTrue = params.verdadeiro.toString()
        linguagem.types.declareFloat = params.numero.toString()
        linguagem.types.declareString = params.texto.toString()
        linguagem.types.declareBoolean = params.booleano.toString()
        linguagem.types.save(flush: true, failOnError: true)

        // Operadores da linguagem
        linguagem.operators = new Operadores()
        linguagem.operators.equalTo = params.igual.toString()
        linguagem.operators.notEqualTo = params.diferente.toString()
        linguagem.operators.greaterThan = params.maior.toString()
        linguagem.operators.lessThan = params.menor.toString()
        linguagem.operators.greaterThanOrEqualTo = params.maiorOuIgual.toString()
        linguagem.operators.lessThanOrEqualTo = params.menorOuIgual.toString()
        linguagem.operators.logicalAnd = params.e.toString()
        linguagem.operators.logicalOr = params.ou.toString()
        linguagem.operators.logicalNot = params.nao.toString()
        linguagem.operators.name = params.nome.toString()
        linguagem.operators.save(flush: true, failOnError: true)

        // Controles de fluxo da linguagem
        linguagem.controlFlow = new ControleDeFluxo()
        linguagem.controlFlow.languageName = params.nome.toString()
        linguagem.controlFlow.breakCode = params.sair.toString()
        linguagem.controlFlow.doCode = params.farei.toString()
        linguagem.controlFlow.forCode = params.para.toString()
        linguagem.controlFlow.ifCode = params.se.toString()
        linguagem.controlFlow.repeatCode = params.repita.toString()
        linguagem.controlFlow.whileCode = params.enquanto.toString()
        linguagem.controlFlow.switchCode = params.teste.toString()
        linguagem.controlFlow.save(flush: true, failOnError: true)

        // Salvando a linguagem
        linguagem.save(flush: true, failOnError: true)

        // Definições
        def defines = new Definicao()
        defines.name = "a"
        defines.alreadyExists = true
        defines.text="OUT_A"
        defines.type="int"
        linguagem.addToDefines(defines).save(flush: true, failOnError: true)

        defines = new Definicao()
        defines.name = "b"
        defines.alreadyExists = true
        defines.text="OUT_B"
        defines.type="int"
        linguagem.addToDefines(defines).save(flush: true, failOnError: true)

        defines = new Definicao()
        defines.name = "c"
        defines.alreadyExists = true
        defines.text="OUT_C"
        defines.type="int"
        linguagem.addToDefines(defines).save(flush: true, failOnError: true)

        defines = new Definicao()
        defines.name = "ab"
        defines.alreadyExists = true
        defines.text="OUT_AB"
        defines.type="int"
        linguagem.addToDefines(defines).save(flush: true, failOnError: true)

        defines = new Definicao()
        defines.name = "ac"
        defines.alreadyExists = true
        defines.text="OUT_AC"
        defines.type="int"
        linguagem.addToDefines(defines).save(flush: true, failOnError: true)

        defines = new Definicao()
        defines.name = "bc"
        defines.alreadyExists = true
        defines.text="OUT_BC"
        defines.type="int"
        linguagem.addToDefines(defines).save(flush: true, failOnError: true)

        defines = new Definicao()
        defines.name = "abc"
        defines.alreadyExists = true
        defines.text="OUT_ABC"
        defines.type="int"
        linguagem.addToDefines(defines).save(flush: true, failOnError: true)

        defines = new Definicao()
        defines.name = "meio"
        defines.alreadyExists = true
        defines.text="BTNCENTER"
        defines.type="int"
        linguagem.addToDefines(defines).save(flush: true, failOnError: true)

        defines = new Definicao()
        defines.name = "baixo"
        defines.alreadyExists = true
        defines.text="BTNEXIT"
        defines.type="int"
        linguagem.addToDefines(defines).save(flush: true, failOnError: true)

        defines = new Definicao()
        defines.name = "setadireita"
        defines.alreadyExists = true
        defines.text="BTNRIGHT"
        defines.type="int"
        linguagem.addToDefines(defines).save(flush: true, failOnError: true)

        defines = new Definicao()
        defines.name = "setaesquerda"
        defines.alreadyExists = true
        defines.text="BTNLEFT"
        defines.type="int"
        linguagem.addToDefines(defines).save(flush: true, failOnError: true)

        defines = new Definicao()
        defines.name = "preto"
        defines.alreadyExists = false
        defines.text="1"
        defines.type="int"
        linguagem.addToDefines(defines).save(flush: true, failOnError: true)

        defines = new Definicao()
        defines.name = "azul"
        defines.alreadyExists = false
        defines.text="2"
        defines.type="int"
        linguagem.addToDefines(defines).save(flush: true, failOnError: true)

        defines = new Definicao()
        defines.name = "verde"
        defines.alreadyExists = false
        defines.text="3"
        defines.type="int"
        linguagem.addToDefines(defines).save(flush: true, failOnError: true)

        defines = new Definicao()
        defines.name = "amarelo"
        defines.alreadyExists = false
        defines.text="4"
        defines.type="int"
        linguagem.addToDefines(defines).save(flush: true, failOnError: true)

        defines = new Definicao()
        defines.name = "vermelho"
        defines.alreadyExists = false
        defines.text="5"
        defines.type="int"
        linguagem.addToDefines(defines).save(flush: true, failOnError: true)

        defines = new Definicao()
        defines.name = "branco"
        defines.alreadyExists = false
        defines.text="6"
        defines.type="int"
        linguagem.addToDefines(defines).save(flush: true, failOnError: true)

        println "Cadastro da linguagem " + linguagem.name + " concluído por " + springSecurityService.getCurrentUser()


        // Se a pasta de destino do arquivo não existir, cria	
        File pastadaLinguagem = new File("/data/sites/weduc/tmp/weduc/compilador/" + linguagem.id)

        if (!pastadaLinguagem.exists()) {
           pastadaLinguagem.mkdirs()
        }


        // Fim do cadastro de linguagem

        if(linguagem.id != null) {
                flash.message = "Linguagem " + linguagem.name + " cadastrada com sucesso."
                redirect action: "editar", id: linguagem.id
        } else {
                flash.message = "Erro ao cadastrar a linguagem " + linguagem.name + "."
                redirect action: "nova" //, params: params
        }

    }

    @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR'])
    def editar() {
        def linguagem = Linguagem.get(params.id)
        def currentUser = springSecurityService.getCurrentUser()

        if(linguagem.isSystemLanguage && request.isUserInRole('ROLE_ADMIN')){
            [linguagem: linguagem]
        }else if(!linguagem.isSystemLanguage && linguagem.autor == currentUser){
            [linguagem: linguagem]
        }else{
            render(status: 403, text: 'Você não tem permissão para isso!')
        }
    }

    @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR'])
    def copiar() {
        def linguagem = Linguagem.get(params.id)
        def novaLinguagem = linguagem.clonar(springSecurityService.getCurrentUser())

        if(novaLinguagem){
            linguagem.functions.each {
                it.clonar(novaLinguagem)
            }

            File sourceComp = new File("/data/sites/weduc/weduc/arquivos-de-compilacao/" + linguagem.id + "/")
            File destComp = new File("/data/sites/weduc/weduc/arquivos-de-compilacao/" + novaLinguagem.id + "/")

            FileUtils.copyDirectory(sourceComp, destComp);

            File sourceSend = new File("/data/sites/weduc/weduc/arquivos-de-envio/" + linguagem.id + "/")
            File destSend = new File("/data/sites/weduc/weduc/arquivos-de-envio/" + novaLinguagem.id + "/")

            FileUtils.copyDirectory(sourceSend, destSend);

            File sourceIncl = new File("/data/sites/weduc/weduc/arquivos-de-include/" + linguagem.id + "/")
            File destIncl = new File("/data/sites/weduc/weduc/arquivos-de-include/" + novaLinguagem.id + "/")

            FileUtils.copyDirectory(sourceIncl, destIncl);

            flash.success = "Cópia da linguagem " + linguagem.name + " realizada com sucesso."
            redirect action: "listarMinhas"
        }else{
            flash.error = "Erro ao realizar cópia da linguagem " + linguagem.name + "."
            redirect action: "listarMinhas"
        }
    }

    @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR'])
    def atualizar() {

        // Início do cadastro de linguagem

        // Linguagem propriamente dita
        def linguagem = Linguagem.get(params.id)
        linguagem.name = params.nome.toString()
        linguagem.description = params.descricao.toString()
        linguagem.robot = params.robo.toString()
        linguagem.extension = params.extensao.toString()
        linguagem.compileCode = params.codigoDeCompilacao.toString()
        linguagem.compilerFile = params.arquivo.toString()
        linguagem.sendCode = params.codigoDeEnvio.toString()
        linguagem.sentExtension = params.extensaoDoArquivo.toString()
        linguagem.header = params.cabecalho.toString()
        linguagem.footnote = params.rodape.toString()
        linguagem.mainFunction = params.funcaoPrincipal.toString()
        linguagem.otherFunctions = params.funcao.toString()
        linguagem.callFunction = params.chamadaDeFuncao.toString()
        linguagem.autor = springSecurityService.getCurrentUser()

        // Tipos da linguagem
        linguagem.types.name = ""
        linguagem.types.declareFalse = params.falso.toString()
        linguagem.types.declareTrue = params.verdadeiro.toString()
        linguagem.types.declareFloat = params.numero.toString()
        linguagem.types.declareString = params.texto.toString()
        linguagem.types.declareBoolean = params.booleano.toString()
        linguagem.types.save(flush: true, failOnError: true)

        // Operadores da linguagem
        linguagem.operators.equalTo = params.igual.toString()
        linguagem.operators.notEqualTo = params.diferente.toString()
        linguagem.operators.greaterThan = params.maior.toString()
        linguagem.operators.lessThan = params.menor.toString()
        linguagem.operators.greaterThanOrEqualTo = params.maiorOuIgual.toString()
        linguagem.operators.lessThanOrEqualTo = params.menorOuIgual.toString()
        linguagem.operators.logicalAnd = params.e.toString()
        linguagem.operators.logicalOr = params.ou.toString()
        linguagem.operators.logicalNot = params.nao.toString()
        linguagem.operators.name = params.nome.toString()
        linguagem.operators.save(flush: true, failOnError: true)

        // Controles de fluxo da linguagem
        linguagem.controlFlow.languageName = params.nome.toString()
        linguagem.controlFlow.breakCode = params.sair.toString()
        linguagem.controlFlow.doCode = params.farei.toString()
        linguagem.controlFlow.forCode = params.para.toString()
        linguagem.controlFlow.ifCode = params.se.toString()
        linguagem.controlFlow.repeatCode = params.repita.toString()
        linguagem.controlFlow.whileCode = params.enquanto.toString()
        linguagem.controlFlow.switchCode = params.teste.toString()
        linguagem.controlFlow.save(flush: true, failOnError: true)

        // Salvando a linguagem
        linguagem.save(flush: true, failOnError: true)

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
        def linguagens = Linguagem.findAllByIsPrivate(false)
        [linguagens: linguagens, professor: springSecurityService.getCurrentUser()]
    }

    @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR'])
    def listarMinhas() {
        def usuario = springSecurityService.getCurrentUser()
        [linguagens: Linguagem.findAllByAutor(usuario)]
    }

    @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR'])
    def enableLanguage(){
        def professor = Professor.get(springSecurityService.getCurrentUser().id)
        println Linguagem.load(params.id)
        
        if(professor.addToEnabledLanguages(Linguagem.load(params.id)).save(flush: true))
        
            redirect(controller: "linguagem", action: "listar")
            
        professor.errors.each {
            println it
        }
    }

    @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR'])
    def disableLanguage(){
        def professor = Professor.get(springSecurityService.getCurrentUser().id)
        
        professor.removeFromEnabledLanguages(Linguagem.load(params.id)).save(flush: true)
        
        redirect(controller: "linguagem", action: "listar")
    }

    @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR'])
    def makePublic(){
        def language = Linguagem.get(params.id)
        
        language.isPrivate = false
        language.save(flush: true)
        
        redirect(controller: "linguagem", action: "listarMinhas")
    }

    @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR'])
    def makePrivate(){
        def language = Linguagem.get(params.id)
        
        language.isPrivate = true
        language.save(flush: true)
        
        redirect(controller: "linguagem", action: "listarMinhas")
    }

    @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR', 'ROLE_ALUNO'])
    def procurar() {
        def usuario = springSecurityService.getCurrentUser()
        
        def queryId

        if(usuario.instanceOf(Aluno)){
            queryId = usuario.professor.id
        }else{
            queryId = usuario.id
        }

        def linguagens = Linguagem.withCriteria {
            acessors {
                eq('id', queryId)
            }
            or {
                and {
                    autor {
                        ne('id', queryId)
                    }
                    eq('isPrivate', false)
                }
                autor {
                    eq('id', queryId)
                }
            }
            order('name', 'asc')
        }

        [linguagens: linguagens]
    }

    @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR'])
    def uploadCompilacao() {
        [linguagem: Linguagem.get(params.id)]
    }

    @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR'])
    def uploadIncludes() {
        [linguagem: Linguagem.get(params.id)]
    }


    @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR'])
    def uploadEnvio() {
        [linguagem: Linguagem.get(params.id)]
    }
	
    @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR'])
    def excluir(){
        def linguagem = Linguagem.get(params.id)   
        Programa.findAll{linguagem == linguagem}.each{it.delete(flush:true)}
        linguagem.delete(flush:true)
        redirect controller:"admin", action:"index"
    }
       
    //Salva compilador
    @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR'])
    def salvarCompilacao() {
        // Captura o arquivo enviado através do formulário
        def arquivo = request.getFile("arquivo")

        //Apaga os arquivos enviados anteriormente
        File fDell = new File("/data/sites/weduc/weduc/arquivos-de-compilacao/" + params.id + "/")
        fDell.deleteDir()


        // Se a pasta de destino do arquivo não existir, cria
        File pastaDeDestino = new File("/data/sites/weduc/weduc/arquivos-de-compilacao/" + params.id + "/")
        if (!pastaDeDestino.exists()) {
                pastaDeDestino.mkdirs()
        }

        // Transfere o arquivo enviado para a a pasta de destino
        // e fornece um nome específico a ele
        File destinoDoArquivo = new File("/data/sites/weduc/weduc/arquivos-de-compilacao/" + params.id + "/arquivo")
        arquivo.transferTo(destinoDoArquivo)

        //Copia os arquivos de include e extrai na pasta	
        def origem = "/data/sites/weduc/weduc/arquivos-de-compilacao/" + params.id + "/arquivo"	
        def destino = "/data/sites/weduc/weduc/arquivos-de-compilacao/" + params.id + "/" 
        CommandShellToString.execute("unzip "+origem+" -d "+destino);

        [linguagem: Linguagem.get(params.id)]
    }

    //Salva os arquivos necessários para compilar
    @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR'])
    def salvarIncludes() {
        // Captura o arquivo enviado através do formulário
        def arquivo = request.getFile("arquivo")

        //Apaga os arquivos enviados anteriormente
        File fDell = new File("/data/sites/weduc/weduc/arquivos-de-include/" + params.id + "/")
        fDell.deleteDir()


        // Se a pasta de destino do arquivo não existir, cria
        File pastaDeDestino = new File("/data/sites/weduc/weduc/arquivos-de-include/" + params.id + "/")
        if (!pastaDeDestino.exists()) {
                pastaDeDestino.mkdirs()
        }

        // Transfere o arquivo enviado para a a pasta de destino
        // e fornece um nome específico a ele
        File destinoDoArquivo = new File("/data/sites/weduc/weduc/arquivos-de-include/" + params.id + "/arquivo")
        arquivo.transferTo(destinoDoArquivo)

        [linguagem: Linguagem.get(params.id)]
    }

    @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR'])
    def salvarEnvio() {
        // Captura o arquivo enviado através do formulário
        def arquivo = request.getFile("arquivo")

        // Se a pasta de destino do arquivo não existir, cria
        File pastaDeDestino = new File("/data/sites/weduc/weduc/arquivos-de-envio/" + params.id + "/")
        if (!pastaDeDestino.exists()) {
                pastaDeDestino.mkdirs()
        }


        // Transfere o arquivo enviado para a a pasta de destino
        // e fornece um nome específico a ele
        File destinoDoArquivo = new File("/data/sites/weduc/weduc/arquivos-de-envio/" + params.id + "/arquivo")
        arquivo.transferTo(destinoDoArquivo)     

        //Copia os arquivos de include e extrai na pasta	
        def origem = "/data/sites/weduc/weduc/arquivos-de-envio/" + params.id + "/arquivo"	
        def destino = "/data/sites/weduc/weduc/arquivos-de-envio/" + params.id + "/" 
        CommandShellToString.execute("unzip "+origem+" -d "+destino);

        [linguagem: Linguagem.get(params.id)]  
    }
}
