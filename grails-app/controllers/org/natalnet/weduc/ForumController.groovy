package org.natalnet.weduc

import org.springframework.security.access.annotation.Secured

class ForumController {
    def springSecurityService

    def index() { }

    def home() {
        def section =  Section.findAll()
        
        [sections: section]
    }

    def topic() {
        def topic
        if(Topic.findByTitle(params.id)){
            topic = Topic.findByTitle(params.id)
        } else {
            topic = Topic.findWhere( id : params.topicId.toLong())
        }

        [topic: topic]
    }

    def thread(long threadId) {
        def thread = DiscussionThread.findWhere( id : params.id.toLong())
        
        [thread: thread]
    }

    @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR', 'ROLE_ALUNO'])
    def topicCreate(){
        def section = Section.findWhere(id: params.sectionId.toLong())
        [section: section]
    }

    @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR'])
    def topicNew() {
        def topic = new Topic()
        topic.section = Section.findWhere( id : params.section.toLong())
        topic.title = params.topicTitle
        topic.description = params.topicDescription
        topic.save(flush: true, failOnError: true)

        if(params.section != null) {
            flash.message = "Tópico criado com sucesso."
            redirect controller: "forum", action: "home"
        } else {
            flash.message = "Erro ao criar um novo tópico."
            redirect controller: "forum", action: "home"
        }
    }

    @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR', 'ROLE_ALUNO'])
    def postCreate(){
        def topic = Topic.findWhere(id: params.topicId.toLong())
        [topic: topic]
    }

    @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR', 'ROLE_ALUNO'])
    def postNew() {
        def thread = new DiscussionThread()
        thread.topic = Topic.findWhere( id : params.topic.toLong())
        thread.title = params.postTitle
        thread.subject = params.postBody
        thread.opener = springSecurityService.getCurrentUser()
        thread.blocked = false
        thread.save(flush: true, failOnError: true)

        if(params.topic != null) {
            flash.message = "Post criado com sucesso."
            redirect controller: "forum", action: "topic", params: [topicId: thread.topic.id]
        } else {
            flash.message = "Erro ao criar um novo tópico."
            redirect controller: "forum", action: "topic", params: [topicId: thread.topic.id]
        }
    }

    @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR', 'ROLE_ALUNO'])
    def postReply() {

        def comment = new Comment()
        comment.commentBy = springSecurityService.getCurrentUser()
        comment.thread = DiscussionThread.findWhere( id : params.thread.toLong())
        comment.body = params.mensagem
        comment.save(flush: true, failOnError: true)

        if(comment.body != null) {
            flash.message = "Mensagem a " + comment.thread + " enviada com sucesso."
            redirect controller: "forum", action: "thread", params: [id: comment.thread.id]
        } else {
            flash.message = "Erro ao enviar a mensagem."
            redirect controller: "forum", action: "thread", params: [id: comment.thread.id]
        }
        
        
    }
    
    @Secured(['ROLE_ADMIN'])
    def block() {
        def thread = DiscussionThread.findWhere( id : params.id.toLong())

        [thread: thread]
    }
    
    @Secured(['ROLE_ADMIN'])
    def confirmBlock() {
        def thread = DiscussionThread.findWhere( id : params.id.toLong())
        thread.blocked = true
        thread.save(flush: true, failOnError: true)

        redirect controller: "forum", action: "home"
    }
}
