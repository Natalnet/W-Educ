<!DOCTYPE html>

<html>
	<head>
		<meta name="layout" content="admin">
		<title>Fórum</title>
                
                <style>
                    #nav {
                        background-color: AliceBlue
                    }
                </style>
	</head>
	<body>   
                <div class="row">
            <div class="col-lg-12">
                <br>
                <div class="jumbotron">
                <div class="container">
                    <p>Este é o <b>Fórum de Discussão</b> do <b>W-Educ</b>. Compartilhe projetos, notícias, eventos, desafios e sinta-se a vontade para tirar dúvidas!</p>
                </div>
                </div>
        
        
        <g:each in="${topic}" var="topicId">
                
            <div class="section">
                <div class="list group"> 
                 <nav class="navbar navbar-default" id="nav">
                    <div class="container-fluid">
                        <div class="navbar-header">
                                <g:link controller="forum" action="topic" id="${topicId?.title}">
                              <font face ="times" size="5">  
                                  <b>${topicId?.title}</b>
                              </font>
                            </g:link>
                        <div class="navbar-footer">
                                <font face="verdana" color="#696969">
                                    <b>${topicId?.description}</b>  
                                </font>
                         </div>
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