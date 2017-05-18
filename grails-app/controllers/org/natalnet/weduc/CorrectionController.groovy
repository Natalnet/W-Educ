package org.natalnet.weduc

import grails.plugin.springsecurity.annotation.Secured

class CorrectionController {

    def springSecurityService
    
    @Secured(['ROLE_PROFESSOR'])
    def index() {
        def professor = springSecurityService.getCurrentUser()
        def students = Aluno.findAllByProfessor(professor)
        def programs = Programa.findAllByUsuarioInList(students)
        def corrections = Correction.findAllByProgramInListAndCorrectedOn(programs, null)
        
        [corrections: corrections]
    }

    @Secured(httpMethod = 'POST')
    def store() {
        def correction = new Correction()
        
        correction.originalCode = params.codigo
        correction.requestedOn = new Date()
    
        def program = Programa.get(params.program)
        program.addToCorrections(correction)
        
        render (program.save(flush: true, failOnError: true))
    }
    
    def correct() {
        def correction = Correction.get(params.id)
        def funcoes = Funcao.findAllWhere(linguagem: Linguagem.get(params.id))
            

        
        [correction: correction, funcoes: funcoes]
    }
}
