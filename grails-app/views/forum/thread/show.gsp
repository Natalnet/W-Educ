<!DOCTYPE html>

<html>
<head>

    <meta name="layout" content="admin"/>
    <title>Fórum</title>

</head>
<body>
    <h1>${topic?.title}</h1>
    <div class="row">
        <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <i class="fa fa-comments fa-fw"></i> Post
                <sec:ifAnyGranted roles="ROLE_ADMIN">
                <div class="btn-group pull-right">
                    <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">
                        <i class="fa fa-chevron-down"></i>
                    </button>
                    <ul class="dropdown-menu slidedown">
                        <li>
                            <a href="#">
                                <i class="fa fa-edit fa-fw"></i> Editar
                            </a>
                        </li>
                        <li>
                            <g:link controller="forum" action="block" type="button" id="${thread?.id}">
                                <i class="fa fa-minus-circle fa-fw"></i> Bloquear
                            </g:link>
                        </li>
                    </ul>
                </div>
                </sec:ifAnyGranted>
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <div class="well">
                    <h4>${thread?.title}</h4>
                    <p>${thread?.subject}</p>
                </div>
                <ul class="chat">
                    <g:each in="${thread.comments}" var="comment">
                    <li class="left clearfix">
                        <span class="chat-img pull-left">
                            <img src="http://placehold.it/50/55C1E7/fff" alt="User Avatar" class="img-circle">
                        </span>
                        <div class="chat-body clearfix">
                            <div class="header">
                                <strong class="primary-font">${comment?.commentBy.username}</strong>
                                <small class="pull-right text-muted">
                                    <i class="fa fa-clock-o fa-fw"></i> <g:formatDate date="${comment.createDate}" format="dd/MM/yyyy HH:mm:ss"/>
                                </small>
                            </div>
                            <p>${comment?.body}</p>
                        </div>
                    </li>
                    </g:each>
                </ul>
            </div>
            <!-- /.panel-body -->
            <div class="panel-footer">
                <form action='<g:createLink controller="forum" action="postReply" id="enviar"/>' method="post">
                    <div class="input-group">
                        <input id="mensagem" name="mensagem" type="text" class="form-control input-sm" placeholder="Deixe sua resposta...">
                        <input type="hidden" id="thread" name ="thread" value ="${thread.id}">
                        <span class="input-group-btn">
                            <button class="btn btn-warning btn-sm" id="btn-chat">
                                Responder
                            </button>
                        </span>
                    </div>
                </form>
            </div>
            <!-- /.panel-footer -->
        </div>
    </div>
    </div>
        
  </body>
        
</html>