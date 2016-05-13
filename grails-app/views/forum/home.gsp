<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="admin">
		<title>Forum</title>
	</head>
	<body>
        <g:each in="${topic}" var="topicId">
        <div class="section">
            <div class="sectionTitle">
                ${topic.title}
                <span class="topicDesc">${topic.description}</span>
            </div>
            <g:each in="${threads}" var="thread">
                <div class="topic">
                    <g:link controller="forum" action="thread" params="[threadId:thread.id]" >
                        ${thread.subject}
                    </g:link>
                    <div class="rightInfo">
                        <b>replies</b>: ${thread.numberOfReplies}
                    </div>
                    <div>
                        Started by: ${thread.opener.username}
                        on: <g:formatDate date="${thread.createDate}" format="dd MMM yyyy"/>
                    </div>
                </div>
            </g:each>
        </div>
       
        </g:each>
        
	</body>
        
</html>