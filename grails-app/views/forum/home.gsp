<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="admin">
		<title>Fórum</title>
	</head>
	<body>    
            <h1 class="page-header">Fórum</h1>
        <g:each in="${topic}" var="topicId">
        
            <div class="section">
                <div class="panel panel-primary">
                 <div class="panel-heading">
                  ${topic.title}
                </div>
                <div class="panel-footer">&emsp; ${topic.description}</div>
                
                     <g:each in="${threads}" var="thread">
                        <div class="topic">
                          <div class="list-group">
                              <a href="#" class="list-group-item">
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
                              </a>
                           </div>
                        </div>
                    </g:each>
              
                </div> 
            </div>
       
         </g:each>
        
	</body>
        
</html>