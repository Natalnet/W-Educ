package org.natalnet.weduc

class Comment {

    static belongsTo = DiscussionThread
    DiscussionThread thread
    Usuario commentBy
    String body
    Date createDate = new Date()
    
    static constraints = {
        body(blank: false, maxSize: 8000)
        body sqlType: 'longtext'
    }
}