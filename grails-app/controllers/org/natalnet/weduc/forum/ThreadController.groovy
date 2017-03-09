package org.natalnet.weduc.forum

import org.natalnet.weduc.Topic
import org.natalnet.weduc.DiscussionThread
import org.springframework.security.access.annotation.Secured

class ThreadController {
    
    def springSecurityService

    def index() { }
    
    def show() {
        def thread = DiscussionThread.get(params.id)
        def user = springSecurityService.getCurrentUser()
        
        render view: "/forum/thread/show", model: [thread: thread, canEdit: (thread.opener.id == user?.id)]
    }
    
    @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR', 'ROLE_ALUNO'])
    def edit(long id) {
        def thread = DiscussionThread.findWhere(id : params.id.toLong())
        def user = springSecurityService.getCurrentUser()
        
        if(thread.opener.id == user.id){
            render view: "/forum/thread/edit", model: [thread: thread]
        } else {
            flash.error = "Você não tem permissão para editar esta postagem."
            redirect controller: "thread", action: "show", id: thread.id
        }
    }
    
    @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR', 'ROLE_ALUNO'])
    def create(){
        def topic = Topic.findWhere(id: params.topicId.toLong())
        render view: "/forum/thread/create", model: [topic: topic]
    }
    
    @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR', 'ROLE_ALUNO'])
    def save() {
        def thread = new DiscussionThread()
        thread.topic = Topic.findWhere(id : params.topic.toLong())
        thread.title = params.postTitle
        thread.subject = params.postBody
        thread.opener = springSecurityService.getCurrentUser()
        thread.blocked = false

        if(thread.save()) {
            flash.success = "Postagem criada com sucesso."
            redirect controller: "forum", action: "topic", params: [topicId: thread.topic.id]
        } else {
            flash.error = "Erro ao criar uma nova postagem."
            redirect controller: "thread", action: "create", params: [topicId: thread.topic.id]
        }
    }
    
    @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR', 'ROLE_ALUNO'])
    def update(long id) {
        def thread = DiscussionThread.findWhere(id : params.id.toLong())
        def user = springSecurityService.getCurrentUser()
        
        
        if(thread.opener.id == user.id){
            thread.title = params.threadTitle
            thread.subject = params.threadBody
            
            if(thread.save(flush: true)) {
                flash.success = "Postagem atualizada com sucesso."
                redirect controller: "thread", action: "show", id: thread.id
            } else {
                flash.error = "Erro ao atualizar postagem."
                redirect controller: "thread", action: "edit", params: [id: thread.id]
            }
        
        } else {
            flash.error = "Você não tem permissão para editar esta postagem."
            redirect controller: "thread", action: "show", id: thread.id
        }
    }
    
    @Secured(['ROLE_ADMIN'])
    def block() {
        def thread = DiscussionThread.findWhere(id : params.id.toLong())

        render view: "/forum/thread/block", model: [thread: thread]
    }
    
    @Secured(['ROLE_ADMIN'])
    def updateBlock() {
        def thread = DiscussionThread.findWhere(id : params.id.toLong())
        thread.blocked = true
        
        if(thread.save(flush: true)) {
            flash.success = "Postagem bloqueada com sucesso."
            redirect controller: "forum", action: "home"
        } else {
            flash.error = "Erro ao bloquear postagem."
            redirect controller: "thread", action: "show", params: [id: thread.id]
        }
    }
}
