<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="admin"/>
        <title>Lista de Funções - W-Educ</title>
    </head>
    <body>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Lista de Funções</h1>
                <h2>Linguagem ${linguagem?.name} <g:link action="nova" params="${[linguagem: linguagem?.id]}" class="btn btn-default">Nova função</g:link></h2>
                <g:if test="${flash.message}">
                <g:if test="${flash.message.contains("removida")}">
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
                                <th>Nome da Função</th>
                                <th>Opções</th>
                            </tr>
                        </thead>
                        <tbody>
                            <g:each in="${funcoes}" var="funcao">
                            <tr>
                                <td><g:link controller="funcao" action="detalhes" id="${funcao?.id}">${funcao?.name}</g:link></td>
                                <td>
                                    <g:link controller="funcao" action="editar" id="${funcao?.id}">Editar função</g:link>&nbsp;
                                    <g:link controller="funcao" action="remover" id="${funcao?.id}">Remover função</g:link>
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
