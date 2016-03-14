<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="admin">
		<title>Grails Forum</title>
	</head>
	<body>
        <g:each in="${sections}" var="section">
            <div class="section">
                <div class="sectionTitle">${section.title}</div>
                <g:each in="${section.topics.sort{it.title}}" var="topic">
                    <div class="topic">
                        <g:link controller="forum" action="topic" params="[topicId:topic.id]" >
                            ${topic.title}
                        </g:link>
                        <span class="topicDesc">${topic.description}</span>
                        <div class="rightInfo">
                            <b>threads</b>: ${topic.numberOfThreads}
                            <b>replies</b>: ${topic.numberOfReplies}
                        </div>
                    </div>
                </g:each>
            </div>
        </g:each>
	</body>
</html>