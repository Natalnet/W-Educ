package org.natalnet.weduc

import grails.plugin.springsecurity.annotation.Secured

class CorrectionController {

    @Secured(httpMethod = 'POST')
    def store() {
        def correction = new Correction()
        
        correction.originalCode = params.codigo
        correction.requestedOn = new Date()
    
        def program = Programa.get(params.program)
        program.addToCorrections(correction)
        
        render (program.save(flush: true, failOnError: true))
    }
}
