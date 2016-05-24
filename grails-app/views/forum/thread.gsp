<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="admin">
		<title>Forum</title>
	</head>
	<body>
        

        <div class="section">
            <div class="sectionTitle">
                ${thread.subject}
            </div>
            <g:each in="${comments}" var="comment">
                <div class="comment">
                    <div>
                        <b>${comment.commentBy.username}</b>
                        <span class="topicDesc">
                            <g:formatDate date="${comment.createDate}" format="dd MMM yyyy hh:mma"/>
                        </span>
                    </div>

                    ${comment.body}
                </div>
            </g:each>
                <div class="comment">
                    <h2>Responder</h2>
                <div class="form-group">
                    <form action="<g:createLink action="postReply" id="enviar"/>" method="post">
                        <input type="text" class="form-control" id="destinatario" name="destinatario"/>
                        <textarea class="form-control" id="mensagem" name="mensagem" required placeholder="Escreva aqui sua mensagem..."></textarea><br/>
                        <input type="submit" class="btn btn-success" value="Enviar mensagem" />
                    </form>
                </div>
                        
                    
                </div>
        </div>

        
	</body>
</html>