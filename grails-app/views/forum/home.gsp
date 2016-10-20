<!DOCTYPE html>

<html>
<head>

    <meta name="layout" content="admin"/>
    <title>Fórum</title>

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
        </div>
        </div>
        <div class="row">
        <g:each in="${sections}" var="section">
        <div class="col-lg-12">
        <div class="panel panel-primary">
            <div class="panel-heading">
                ${section?.title}
                <sec:ifAnyGranted roles="ROLE_ADMIN">
                <div class="btn-group pull-right">
                    <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">
                        <i class="fa fa-chevron-down"></i>
                    </button>
                    <ul class="dropdown-menu slidedown">
                        <li>
                            <g:link controller="forum" action="topicCreate" type="button" params='[sectionId: "${section.id}"]'>
                                <i class="fa fa-plus fa-fw"></i> Adicionar
                            </g:link>
                        </li>
                    </ul>
                </div>
                </sec:ifAnyGranted>
            </div>
            <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>Tópico</th>
                                    <th>Posts</th>
                                    <th>Respostas</th>
                                </tr>
                            </thead>
                            <tbody>
                              <g:each in="${section.topics}" var="topic">
                                <tr>
                                    <td>
                                      <g:link controller="forum" action="topic" id="${topic?.title}">
                                        <b>${topic?.title}</b>
                                      </g:link>
                                      <p>${topic?.description}</p>
                                    </td>
                                    <td>${topic?.getNumberOfThreads()}</td>
                                    <td>${topic?.getNumberOfReplies()}</td>
                                </tr>
                              </g:each>
                            </tbody>
                        </table>
                    </div>
                    <!-- /.table-responsive -->
            </div>
        </div>
        </div>
        </g:each>
        </div>
        
  </body>
        
</html>