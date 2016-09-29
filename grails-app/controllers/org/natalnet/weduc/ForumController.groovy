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
        def topicName
        def topic
        if(Topic.findByTitle(params.id)){
            topic = Topic.findByTitle(params.id)
            topicName = params.id
            
        }    
        else{
            topic = Topic.findWhere( id : params.topicId.toLong())
            topicName = topic.title
        }
            def threads = DiscussionThread.findAllByTopic(topic)     


         [threads:threads,
          topicName:topicName,
          topicId: topic.id  ]
    }
    def thread(long threadId) {
        def thread = DiscussionThread.findWhere( id : params.id.toLong())
        def comments = Comment.findAllByThread(thread)    
        
        [comments: comments,   
         thread:thread]
  
    }
    @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR', 'ROLE_ALUNO'])
    def postthread(){
        def topic = Topic.findWhere(id: params.topicId.toLong())
        [topic: topic.id]
    }
 
        @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR', 'ROLE_ALUNO'])
    def newTopic() {

        
        def thread = new DiscussionThread()
        System.out.println("Estou aqui new Topic")
        thread.topic = Topic.findWhere( id : params.topic.toLong())
        thread.subject = params.threadSubject
        thread.opener = springSecurityService.getCurrentUser()
        thread.save(flush: true, failOnError: true)

        if(params.topic != null) {
            flash.message = "Tópico criado com sucesso."
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
}