import org.natalnet.weduc.Usuario
import org.natalnet.weduc.Privilegio
import org.natalnet.weduc.UsuarioPrivilegio

class BootStrap {

    def init = { servletContext ->
    	// Registra privilégios, caso não existam
    	def privilegios = ["ADMIN", "PROFESSOR", "ALUNO"]
    	privilegios.each {
    		Privilegio.findOrSaveWhere(authority: it)
    	}

    	// Registra usuário padrão
    	def privilegio = Privilegio.findWhere(authority: "ADMIN")
    	def usuario = Usuario.findWhere(username: "admin")
    	if(!usuario) {
    		usuario = new Usuario()
    		usuario.username = "admin"
    		usuario.password = "admin"
    		usuario.email = "admin@email.com"
    		usuario.save flush: true, failOnError: true

    		def usuarioPrivilegio = new UsuarioPrivilegio()
    		usuarioPrivilegio.usuario = usuario
    		usuarioPrivilegio.privilegio = privilegio
    		usuarioPrivilegio.save flush: true, failOnError: true
    	}
    }
    def destroy = {
    }
}
