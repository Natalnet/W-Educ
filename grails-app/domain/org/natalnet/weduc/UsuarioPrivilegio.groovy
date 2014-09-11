package org.natalnet.weduc

import org.apache.commons.lang.builder.HashCodeBuilder

class UsuarioPrivilegio implements Serializable {

	private static final long serialVersionUID = 1

	Usuario usuario
	Privilegio privilegio

	boolean equals(other) {
		if (!(other instanceof UsuarioPrivilegio)) {
			return false
		}

		other.usuario?.id == usuario?.id &&
		other.privilegio?.id == privilegio?.id
	}

	int hashCode() {
		def builder = new HashCodeBuilder()
		if (usuario) builder.append(usuario.id)
		if (privilegio) builder.append(privilegio.id)
		builder.toHashCode()
	}

	static UsuarioPrivilegio get(long usuarioId, long privilegioId) {
		UsuarioPrivilegio.where {
			usuario == Usuario.load(usuarioId) &&
			privilegio == Privilegio.load(privilegioId)
		}.get()
	}

	static boolean exists(long usuarioId, long privilegioId) {
		UsuarioPrivilegio.where {
			usuario == Usuario.load(usuarioId) &&
			privilegio == Privilegio.load(privilegioId)
		}.count() > 0
	}

	static UsuarioPrivilegio create(Usuario usuario, Privilegio privilegio, boolean flush = false) {
		def instance = new UsuarioPrivilegio(usuario: usuario, privilegio: privilegio)
		instance.save(flush: flush, insert: true)
		instance
	}

	static boolean remove(Usuario u, Privilegio r, boolean flush = false) {
		if (u == null || r == null) return false

		int rowCount = UsuarioPrivilegio.where {
			usuario == Usuario.load(u.id) &&
			privilegio == Privilegio.load(r.id)
		}.deleteAll()

		if (flush) { UsuarioPrivilegio.withSession { it.flush() } }

		rowCount > 0
	}

	static void removeAll(Usuario u, boolean flush = false) {
		if (u == null) return

		UsuarioPrivilegio.where {
			usuario == Usuario.load(u.id)
		}.deleteAll()

		if (flush) { UsuarioPrivilegio.withSession { it.flush() } }
	}

	static void removeAll(Privilegio r, boolean flush = false) {
		if (r == null) return

		UsuarioPrivilegio.where {
			privilegio == Privilegio.load(r.id)
		}.deleteAll()

		if (flush) { UsuarioPrivilegio.withSession { it.flush() } }
	}

	static constraints = {
		privilegio validator: { Privilegio r, UsuarioPrivilegio ur ->
			if (ur.usuario == null) return
			boolean existing = false
			UsuarioPrivilegio.withNewSession {
				existing = UsuarioPrivilegio.exists(ur.usuario.id, r.id)
			}
			if (existing) {
				return 'userRole.exists'
			}
		}
	}

	static mapping = {
		id composite: ['privilegio', 'usuario']
		version false
	}
}
