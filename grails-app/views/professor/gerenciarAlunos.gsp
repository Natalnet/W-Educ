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
                <h2>Meus alunos</h2>
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Nome do aluno</th>
                                <th>Opções</th>
                            </tr>
                        </thead>
                        <tbody>
                            <g:if test="${flash.message}">
                            <g:if test="${flash.message.contains("recusado")}">
                            <div class="alert alert-danger">
                            </g:if><g:if test="${flash.message.contains("aceito")}">
                            <div class="alert alert-success">    
                            </g:if>
                                ${flash.message}
                            </div>
                            </g:if>
                            <g:each in="${alunos}" var="aluno">
                            <tr>
                                <td>${aluno?.username}</td>
                                <td>
                                    <g:link controller="mensagem" action="escrever" id="${aluno?.id}">Enviar mensagem</g:link>
                                </td>
                            </tr>
                            </g:each>
                        </tbody>
                    </table>
                </div>
                <h2>Alunos aguardando aceitação</h2>
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Nome do aluno</th>
                                <th>Opções</th>
                            </tr>
                        </thead>
                        <tbody>
                            <g:if test="${flash.message}">
                            <g:if test="${flash.message.contains("recusado")}">
                            <div class="alert alert-danger">
                            </g:if><g:if test="${flash.message.contains("aceito")}">
                            <div class="alert alert-success">    
                            </g:if>
                                ${flash.message}
                            </div>
                            </g:if>
                            <g:each in="${requisicoes}" var="requisicao">
                            <tr>
                                <td>${requisicao?.aluno?.username}</td>
                                <td>
                                    <g:link controller="professor" action="aceitar" id="${requisicao?.id}">Aceitar aluno</g:link> | 
                                    <g:link controller="professor" action="recusar" id="${requisicao?.id}">Recusar aluno</g:link>
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
