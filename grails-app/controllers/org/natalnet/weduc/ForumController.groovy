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

        if(topic.save()) {
            flash.success = "Tópico criado com sucesso."
            redirect controller: "forum", action: "home"
        } else {
            flash.error = "Erro ao criar um novo tópico."
            redirect controller: "forum", action: "topicCreate", params: [sectionId: topic.section.id]
        }
    }

    @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR', 'ROLE_ALUNO'])
    def postReply() {

        def comment = new Comment()
        comment.commentBy = springSecurityService.getCurrentUser()
        comment.thread = DiscussionThread.findWhere( id : params.thread.toLong())
        comment.body = params.mensagem

        if(comment.save()) {
            flash.success = "Resposta a " + comment.thread.title + " enviada com sucesso."
            redirect controller: "thread", action: "show", params: [id: comment.thread.id]
        } else {
            flash.error = "Erro ao enviar a resposta."
            redirect controller: "thread", action: "show", params: [id: comment.thread.id]
        }
        
        
    }
}
