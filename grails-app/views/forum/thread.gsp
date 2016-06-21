<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<!DOCTYPE html>
<html>
	<head>
            
		<meta name="layout" content="admin">
		<title>Fórum de Discussão</title>
	</head>
	<body>
            <div>
             <ul class="list-group">
        
        <li class="list-group-item list-group-item-info">
                 <font face ="verdana" size="5" color="black"> 
        <center><b>${thread.subject}</b></center>
                </font>
        </li>
        
        
            <g:each in="${comments}" var="comment">
                <li class="list-group-item list-group-item disabled">
                    <b>${comment.commentBy.username}</b> diz, em 
                        <span class="topicDesc">
                            <g:formatDate date="${comment.createDate}" format="dd MMM yyyy hh:mma"/>
                        </span>     
:
                </li>
                
                            <li class="list-group-item">
                                    ${comment.body}
                            </li>
            </g:each>
        </ul>   
        </div>
                    <div class="comment">
                        <h3>Responder</h3>
                    <div class="form-group">
                        <form action="<g:createLink controller="forum" action="postReply" id="enviar"/>" method="post">
                            <textarea class="form-control" id="mensagem" name="mensagem" required placeholder="Escreva aqui seu comentário..."></textarea><br/>
                            <input type="hidden" id="thread" name ="thread" value ="${thread.subject}">
                            <input type="submit" class="btn btn-info" value="Enviar mensagem" align="right" />
                        </form>
                    </div>
                </div>
	</body>
</html>