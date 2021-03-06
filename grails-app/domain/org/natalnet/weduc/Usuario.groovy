package org.natalnet.weduc

class Usuario {

    transient springSecurityService

    String username
    String name
    String password
    String institution
    String dateOfBirth
    String gender
    String address
    String email
    String telefone
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    static transients = ['springSecurityService']

    static hasMany = [programas: Programa]

    static constraints = {
        username blank: false, unique: true
        password blank: false
        email blank: false
        programas blank: true, nullable: true
        name blank: true, nullable:true
        institution blank: true, nullable:true
        dateOfBirth blank: true, nullable:true
        gender blank: true, nullable:true
        address blank: true, nullable:true
        telefone blank: true, nullable:true
    }

    static mapping = {
        password column: '`password`'
        sort "username"
    }

    Set<Privilegio> getAuthorities() {
        UsuarioPrivilegio.findAllByUsuario(this).collect { it.privilegio }
    }

    def beforeInsert() {
        encodePassword()
    }

    def beforeUpdate() {
        if (isDirty('password')) {
            encodePassword()
        }
    }

    protected void encodePassword() {
        password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
    }
        
}
