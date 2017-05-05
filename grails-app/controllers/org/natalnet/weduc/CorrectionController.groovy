package org.natalnet.weduc

import grails.plugin.springsecurity.annotation.Secured

class CorrectionController {
    
    def springSecurityService

    @Secured(httpMethod = 'POST')
    def store() {
        def usuario = springSecurityService.getCurrentUser()
        var correction = new Correction()
        
        correction.originalCode = ""
        
    }
}
