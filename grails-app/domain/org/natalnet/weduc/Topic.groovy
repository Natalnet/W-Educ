package org.natalnet.weduc

class Topic {

    static belongsTo = [section: Section]
    static hasMany = [threads: DiscussionThread]

    String title
    String description
    
    public long getNumberOfThreads() {
        DiscussionThread.countByTopicAndBlocked(this, false)
    }

    public long getNumberOfReplies() {
        Topic.executeQuery("select count(*) from Topic t join t.threads thr join thr.comments c where t.id = :topicId and thr.blocked = false", [topicId:id])[0]
    }

    static constraints = {
    }
}
