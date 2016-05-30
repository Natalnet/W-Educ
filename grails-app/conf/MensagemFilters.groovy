import org.natalnet.weduc.Mensagem;

class MensagemFilters {

	def springSecurityService
        
	def filters = {
		ultimasMensagens(controller: '*', action: '*') {
			before = { model ->
				def ultimasMensagens = Mensagem.findAllWhere(destinatario: springSecurityService.getCurrentUser())
                                //def ultimasMensagens = Mensagem.list(max: 3, fetch: [destinatario: springSecurityService.getCurrentUser()])
				request.setAttribute("ultimasMensagens", ultimasMensagens)
			}
		}
	}
}