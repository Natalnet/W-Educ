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
   
 

   def topic(long topicId) {
    Topic topic
    
    params.max = 10
    params.sort = 'createDate'
    params.order = 'desc'

    [threads:DiscussionThread.findAllByTopic(topic, params),
     numberOfThreads:DiscussionThread.countByTopic(topic), topic:topic]
}

    def thread(long threadId) {
        DiscussionThread thread = DiscussionThread.get(threadId)

        params.max = 10
        params.sort = 'createDate'
        params.order = 'asc'

        [comments:Comment.findAllByThread(thread, params),
         numberOfComments:Comment.countByThread(thread), thread:thread]
  
    }
 

    @Secured(['ROLE_USER'])
    def postReply(long threadId, String body) {
        def offset = params.offset
        if (body != null && body.trim().length() > 0) {
            DiscussionThread thread = DiscussionThread.get(threadId)
            def commentBy = springSecurityService.currentUser
            new Comment(thread:thread, commentBy:commentBy, body:body).save()

            // go to last page so user can view his comment
            def numberOfComments = Comment.countByThread(thread)
            def lastPageCount = numberOfComments % 10 == 0 ? 10 : numberOfComments % 10
            offset = numberOfComments - lastPageCount
        }
        redirect(action:'thread', params:[threadId:threadId, offset:offset])
    }
}