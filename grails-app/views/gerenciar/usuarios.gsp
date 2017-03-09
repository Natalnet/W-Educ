<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="admin"/>
        <title>Gerenciar Alunos - W-Educ</title>
    </head>
    <body>
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Gerenciar Alunos</h1>
            </div>
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
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
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                    <div class="panel panel-success">
                        <div class="panel-heading" role="tab" id="ativos">
                            <h4 class="panel-title">
                                <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseAtivos" aria-expanded="true" aria-controls="ativos">
                                    Alunos Ativos
                                </a>
                            </h4>
                        </div>
                        <div id="collapseAtivos" class="panel-collapse collapse" role="tabpanel" aria-labelledby="ativos">
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <table class="table table-hover">
                                        <thead>
                                            <tr>
                                                <th width="50%">Nome de Usuário</th>
                                                <th>Opções</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <g:each in="${alunos}" var="aluno">
                                            <tr>
                                                <td>${aluno?.username}</td>
                                                <td>
                                                    <g:link class="btn btn-success btn-xs" controller="gerenciar" action="alternar" id="${aluno?.id}">Alternar para Professor</g:link>
                                                    <g:link class="btn btn-info btn-xs" controller="erro" action="exibir" id="${aluno?.id}">Estatísticas</g:link>
                                                    <g:link class="btn btn-warning btn-xs" controller="mensagem" action="escreverUsuario" id="${aluno?.id}">Enviar Mensagem</g:link>
                                                    <g:link class="btn btn-danger btn-xs" controller="gerenciar" action="desativar" id="${aluno?.id}" params="[tipo: 'Aluno']">Desativar</g:link>
                                                </td>
                                            </tr>
                                            </g:each>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-danger">
                        <div class="panel-heading" role="tab" id="inativos">
                            <h4 class="panel-title">
                                <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseInativos" aria-expanded="true" aria-controls="inativos">
                                    Alunos Inativos
                                </a>
                            </h4>
                        </div>
                        <div id="collapseInativos" class="panel-collapse collapse" role="tabpanel" aria-labelledby="inativos">
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <table class="table table-hover">
                                        <thead>
                                            <tr>
                                                <th width="50%">Nome de Usuário</th>
                                                <th>Opções</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <g:each in="${alunosI}" var="aluno">
                                            <tr>
                                                <td>${aluno?.username}</td>
                                                <td>
                                                    <g:link class="btn btn-success btn-xs" controller="gerenciar" action="ativar" id="${aluno?.id}" params="[tipo: 'Aluno']">Ativar</g:link>
                                                    <g:link class="btn btn-info btn-xs" controller="erro" action="exibir" id="${aluno?.id}">Estatísticas</g:link>
                                                </td>
                                            </tr>
                                            </g:each>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>