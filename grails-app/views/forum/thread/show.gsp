<!DOCTYPE html>

<html>
<head>

    <meta name="layout" content="admin"/>
    <title>Fórum</title>

</head>
<body>
    <br />
    <g:if test="${flash?.error}">
        <div class="alert alert-danger">
            ${flash?.error}
        </div>
    </g:if>
    <g:elseif test="${flash?.success}">
        <div class="alert alert-success">
            ${flash?.success}
        </div>
    </g:elseif>
    <div class="row">
        <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <i class="fa fa-comments fa-fw"></i> ${thread?.title}
                
                <div class="btn-group pull-right">
                    <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">
                        <i class="fa fa-chevron-down"></i>
                    </button>
                    <ul class="dropdown-menu slidedown">
                        <g:if test="${canEdit}">
                            <li>
                                <g:link controller="thread" action="edit" type="button" id="${thread?.id}">
                                    <i class="fa fa-edit fa-fw"></i> Editar
                                </g:link>
                            </li>
                        </g:if>
                        <sec:ifAnyGranted roles="ROLE_ADMIN">
                            <li>
                                <g:link controller="thread" action="block" type="button" id="${thread?.id}">
                                    <i class="fa fa-minus-circle fa-fw"></i> Bloquear
                                </g:link>
                            </li>
                        </sec:ifAnyGranted>
                    </ul>
                </div>
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <div class="well">
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