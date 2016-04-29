
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="admin"/>
        <title>Gerenciar Professores - W-Educ</title>
    </head>
    <body>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Gerenciar Professores</h1>
                <g:if test="${flash.message}">
                <g:if test="${flash.message.contains("recusado")}">
                <div class="alert alert-danger">
                </g:if>
                <g:else>
                <div class="alert alert-success">    
                </g:else>
                    ${flash.message}
                </div>
                </g:if>
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Nome de Usuário</th>
                                <th>Opções</th>
                            </tr>
                        </thead>
                        <tbody>
                            <g:each in="${professores}" var="professor">
                            <tr>
                                <td>${professor?.username}</td>
                                <td>
                                    <g:link class="btn btn-success" controller="gerenciar" action="alternarProf" id="${professor?.id}">Alternar para Aluno</g:link>
                                </td>
                                <td>
                                    <g:link class="btn btn-info" controller="gerenciar" action="estatisticas" id="${professor?.id}" params="[tipo: 'Professor']">Estatísticas</g:link>
                                </td>
                                <td>
                                    <g:link class="btn btn-danger" controller="gerenciar" action="excluir" id="${professor?.id}" params="[tipo: 'Professor']">Excluir</g:link>
                                </td>
                            </tr>
                            </g:each>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        
    </body>
</html>