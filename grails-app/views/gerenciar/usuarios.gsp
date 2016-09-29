<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="admin"/>
        <title>Gerenciar Alunos - W-Educ</title>
    </head>
    <body>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Gerenciar Alunos</h1>
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
                <h2 class="page-header">Alunos Ativos</h2>
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Nome de Usuário</th>
                                <th>Opções</th>
                            </tr>
                        </thead>
                        <tbody>
                            <g:each in="${alunos}" var="aluno">
                            <tr>
                                <td>${aluno?.username}</td>
                                <td>
                                    <g:link class="btn btn-success" controller="gerenciar" action="alternar" id="${aluno?.id}">Alternar para Professor</g:link>
                                </td>
                                <td>
                                    <g:link class="btn btn-info" controller="erro" action="exibir" id="${aluno?.id}">Estatísticas</g:link>
                                </td>
                                <td>
                                    <g:link class="btn btn-warning" controller="mensagem" action="escreverUsuario" id="${aluno?.id}">Enviar Mensagem</g:link>
                                </td>
                                <td>
                                    <g:link class="btn btn-danger" controller="gerenciar" action="desativar" id="${aluno?.id}" params="[tipo: 'Aluno']">Desativar</g:link>
                                </td>
                            </tr>
                            </g:each>
                        </tbody>
                    </table>
                </div>
                <h2 class="page-header">Alunos Inativos</h2>
                                <div class="table-responsive">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Nome de Usuário</th>
                                <th>Opções</th>
                            </tr>
                        </thead>
                        <tbody>
                            <g:each in="${alunosI}" var="aluno">
                            <tr>
                                <td>${aluno?.username}</td>
                                <td>
                                    <g:link class="btn btn-success" controller="gerenciar" action="alternar" id="${aluno?.id}">Alternar para Professor</g:link>
                                </td>
                                <td>
                                    <g:link class="btn btn-info" controller="erro" action="exibir" id="${aluno?.id}">Estatísticas</g:link>
                                </td>
                                <td>
                                    <g:link class="btn btn-warning" controller="mensagem" action="escreverUsuario" id="${aluno?.id}">Enviar Mensagem</g:link>
                                </td>
                                <td>
                                    <g:link class="btn btn-danger" controller="gerenciar" action="ativar" id="${aluno?.id}" params="[tipo: 'Aluno']">Ativar</g:link>
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