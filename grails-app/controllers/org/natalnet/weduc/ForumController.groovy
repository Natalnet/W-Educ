package org.natalnet.weduc

import org.springframework.security.access.annotation.Secured

class ForumController {
    def springSecurityService

    def home() {
        def topic = Topic.findAll()
        def section =  Section.findAll()
        def thread = DiscussionThread.findAll()
        def numberOfThreads
        
        
        [sections: section, topic: topic, threads: thread]
    
    }
    
    def section(long sectionID){
        Section section
        
        params.max = 10
        params.order = 'desc'
        
        [topics: topic ]
    }
   
 

   def topic() {
        def topicName = params.id
        def topic = Topic.findAllByTitle(topicName)

        def threads = DiscussionThread.findAllByTopic(topic)     


         [threads:threads,
          topicName:topicName]
    }

    def thread(long threadId) {
        def thread = DiscussionThread.findBySubject(params.id)
        def comments = Comment.findAllByThread(thread)    

        [comments: comments,   
         thread:thread]
  
    }
 

    @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR', 'ROLE_ALUNO'])
    def postReply(long threadId, String body) {

        
        def comment = new Comment()
		comment.commentBy = springSecurityService.getCurrentUser()
		comment.thread = DiscussionThread.findBySubject(params.thread)
                comment.body = params.mensagem
		mensagem.save(flush: true, failOnError: true)

                if(mensagem.id != null) {
                	flash.message = "Mensagem a " + params.destinatario + " enviada com sucesso."
                	redirect controller: "mensagem", action: "todas"
                } else {
                	flash.message = "Erro ao enviar a mensagem."
                        redirect controller: "mensagem", action: "todas"

                }
        
        
    }
}