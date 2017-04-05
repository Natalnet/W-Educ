import org.natalnet.weduc.Mensagem;

class MensagemFilters {

    def springSecurityService

    def filters = {
        ultimasMensagens(controller: '*', action: '*') {
            before = { model ->
                //def ultimasMensagens = Mensagem.findAllWhere(destinatario: springSecurityService.getCurrentUser())
                def usuario = springSecurityService.getCurrentUser()
                def ultimasMensagens = Mensagem.withCriteria {
                    eq('destinatario', usuario)
                    order('data', 'desc')
                    maxResults(3)
                }
                request.setAttribute("ultimasMensagens", ultimasMensagens)
            }
        }
    }
}