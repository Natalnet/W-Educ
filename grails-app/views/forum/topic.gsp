<!DOCTYPE html>

<html>
<head>

    <meta name="layout" content="admin"/>
    <title>Fórum</title>

</head>
<body>   
    <h1 class="page-header">
        ${topic?.title}
        <div class="btn-group pull-right">
            <g:link controller="forum" action="postCreate" type="button" class="btn btn-sm btn-primary" params='[topicId: "${topic.id}"]'>
                Nova Postagem
            </g:link>
        </div>
    </h1>
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-primary">
                <div class="panel-body">
                        <div class="table-responsive">
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>Post</th>
                                        <th>Respostas</th>
                                        <th>Data</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <g:each in="${topic.threads}" var="thread">
                                        <g:if test="${thread.blocked == false}">
                                        <tr>
                                            <td>
                                              <g:link controller="forum" action="thread" id="${thread?.id}">
                                                <b>${thread?.title}</b>
                                                <p>por ${thread?.opener.username}</p>
                                              </g:link>
                                            </td>
                                            <td>${thread?.getNumberOfReplies()}</td>
                                            <td><g:formatDate date="${thread.createDate}" format="dd/MM/yyyy HH:mm:ss"/></td>
                                        </tr>
                                        </g:if>
                                    </g:each>
                                </tbody>
                            </table>
                        </div>
                        <!-- /.table-responsive -->
                </div>
            </div>
        </div>
    </div>
        
  </body>
        
</html>