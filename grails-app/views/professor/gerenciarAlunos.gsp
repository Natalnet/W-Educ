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
                    <div class="panel panel-primary">
                        <div class="panel-heading" role="tab" id="aceitos">
                            <h4 class="panel-title">
                                <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseAceitos" aria-expanded="true" aria-controls="ativos">
                                    Meus Alunos
                                </a>
                            </h4>
                        </div>
                        <div id="collapseAceitos" class="panel-collapse collapse" role="tabpanel" aria-labelledby="aceitos">
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <table class="table">
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
                                                    <g:link class="btn btn-primary btn-xs"  controller="erro" action="exibir" id="${aluno?.id}">Estatísticas</g:link>
                                                    <g:link class="btn btn-primary btn-xs"  controller="mensagem" action="escreverUsuario" id="${aluno?.id}">Enviar mensagem</g:link>
                                                </td>
                                            </tr>
                                            </g:each>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-warning">
                        <div class="panel-heading" role="tab" id="pendentes">
                            <h4 class="panel-title">
                                <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapsePendentes" aria-expanded="true" aria-controls="inativos">
                                    Alunos aguardando aceitação
                                </a>
                            </h4>
                        </div>
                        <div id="collapsePendentes" class="panel-collapse collapse" role="tabpanel" aria-labelledby="pendentes">
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th width="50%">Nome do aluno</th>
                                                <th>Opções:</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <g:each in="${requisicoes}" var="requisicao">
                                            <tr>
                                                <td>${requisicao?.aluno?.username}</td>
                                                <td>
                                                    <g:link class="btn btn-success btn-xs"  controller="professor" action="aceitar" id="${requisicao?.id}">Aceitar aluno</g:link> 
                                                    <g:link class="btn btn-danger btn-xs"  controller="professor" action="recusar" id="${requisicao?.id}">Recusar aluno</g:link>
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
