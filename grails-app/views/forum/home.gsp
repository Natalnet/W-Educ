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
                <div class="list group"> 
                 <nav class="navbar navbar-default">
                    <div class="container-fluid">
                        <div class="navbar-header">
                                <g:link controller="forum" action="topic" id="${topicId?.title}">
                              <font face ="verdana" size="4" color="blue">  
                                  ${topicId?.title}
                              </font>
                            </g:link>
                        </div>
                    </div>
                </nav>                   
                </div>
                     <!--<g:each in="${threads}" var="thread">
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
                    </g:each>-->
              
                </div> 
                
               
       
         </g:each>
        
	</body>
        
</html>