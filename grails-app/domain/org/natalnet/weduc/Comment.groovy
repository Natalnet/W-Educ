package org.natalnet.weduc

class Comment {
    
    static belongsTo = DiscussionThread
    DiscussionThread thread
    Usuario commentBy
    String body
    Date createDate = new Date()
    
    static constraints = {
        body( maxSize: 8000)
    }
    
}