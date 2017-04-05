package org.natalnet.weduc

class DiscussionThread {

    static belongsTo = [topic: Topic]
    static hasMany = [comments: Comment]

    String title
    String subject
    Usuario opener
    boolean blocked
    Date createDate = new Date()

    public long getNumberOfReplies() {
        Comment.countByThread(this)
    }

    static constraints = {
        title(blank: false)
    }
    
    static mapping = {
        comments sort: 'createDate', order: 'asc'
        subject sqlType: 'longtext'
    }
}