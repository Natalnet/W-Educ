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
                
            <style>
                table#t01 thead {
                    background-color: teal;
                    color: White;
                }
                table#t01 tbody {
                    background-color: AliceBlue ;
                    color: Black;
                }
                button#b01{
                    position: relative;
                    right: -600px;
                    color: white;
                    background-color: royalblue;
                    border-radius: 8px;
                    padding: 12px 28px;
                    text-align: center;
                    border: 2px solid white;
                }
            </style>
	</head>
	<body>
            
        <div class="section">
            <div class="sectionTitle">
                <font face ="verdana" size="6" color="black"> 
                ${topicName}
                </font>
                    <g:link controller="forum" action="postthread" type="button" 
                        data-toggle="tooltip" class="btn btn-sm btn-danger" id="topic" params="[topic: "${topicName}"]">
                        Novo Tópico
                    </g:link>
             
            </div>
            <g:each in="${threads}" var="thread">
                <div class="topic">
                    <table class = "table" id="t01">
                            <thead>
                                <tr>
                                    <th>Assunto</th>
                                    <th>Comentários</th>
                                    <th>Criado por/Data de Criação</th>
                                </tr>
                            </thead> 
                            <tbody>
                    
                                <tr>
                                    <th>
                                 <g:link controller="forum" action="thread" id="${thread?.subject}">
                                 ${thread.subject}
                                 </g:link>
                                    </th>
                                    <th>
                                        <div class="rightInfo">
                                            <b>Comentários</b>: ${thread.numberOfReplies}
                                        </div>
                                    </th>
                                    <th>
                                        <div>
                                            Criado por <b> ${thread.opener.username} </b> <br>
                                            em <g:formatDate date="${thread.createDate}" format="dd MMM yyyy"/>
                                        </div>
                                    </th>
                                </tr>
                            </tbody>
                        </table>
                    </div>
            </g:each>
                </div>
        

	</body>
</html>