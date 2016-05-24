<!--
  To change this license header, choose License Headers in Project Properties.
    To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="admin">
		<title>Fórum</title>
	</head>
	<body>
            
        <div class="section">
            <div class="sectionTitle">
                ${topicName}
            </div>
            
            <g:each in="${threads}" var="thread">
                <div class="topic">
                    <g:link controller="forum" action="thread" id="${thread?.subject}">
                        ${thread.subject}
                    </g:link>
                    <div class="rightInfo">
                        <b>Comentários</b>: ${thread.numberOfReplies}
                    </div>
                    <div>
                        Criado por <b> ${thread.opener.username} </b>
                        em <g:formatDate date="${thread.createDate}" format="dd MMM yyyy"/>
                    </div>
                </div>
            </g:each>
        </div>

	</body>
</html>