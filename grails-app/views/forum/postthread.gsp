<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<html>
	<head>
		<meta name="layout" content="admin">
        <title>Postar Comentário</title>
    </head>
    <body>
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Novo Tópico</h1>
                <div class="form-group">
                    <form action="<g:createLink controller="forum" action="newTopic" id="enviar"/>" method="post">
                        
                                <div class="input-group">
                                 <span class="input-group-addon" id="basic-addon2"><b>Nome do tópico:</b></span> 
                                 <input type="text" class="form-control" id="threadSubject" name="threadSubject" required placeholder="Escreva aqui o assunto do seu tópico!"/>
                                 <input type="hidden" id="topic" name ="topic" value ="${topic}">
                                </div><!-- /input-group -->
                        <input type="submit" class="btn btn-success" value="Criar tópico" />
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>