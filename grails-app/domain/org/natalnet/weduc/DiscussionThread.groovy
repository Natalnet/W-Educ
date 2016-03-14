package org.natalnet.weduc

class DiscussionThread {
    static belongsTo = Topic
    static hasMany = [comments:Comment]

    Topic topic
    String subject
    Usuario opener
    Date createDate = new Date()

    public long getNumberOfReplies() {
        Comment.countByThread(this)
    }

    static constraints = {
    }
    
}