<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="admin"/>
        <title>Lista de Linguagens - W-Educ</title>
    </head>
    <body>
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Lista de Linguagens do Usuário</h1>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
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
            </div>
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Nome da Linguagem</th>
                                <th>Opções</th>
                            </tr>
                        </thead>
                        <tbody>
                            <g:each in="${linguagens}" var="linguagem">
                            <tr>
                                <td>
                                    ${linguagem?.name}
                                    <g:if test="${linguagem?.isPrivate}">
                                        <span class="glyphicon glyphicon-lock" aria-hidden="true"></span>
                                    </g:if>
                                </td>
                                <td>
                                    <g:link controller="linguagem" action="editar" id="${linguagem?.id}" class="btn btn-outline btn-primary btn-xs">
                                        Editar linguagem
                                    </g:link>
                                    <g:link controller="funcao" action="listar" id="${linguagem?.id}" class="btn btn-outline btn-primary btn-xs">
                                        Ver funções
                                    </g:link>
                                    <g:link controller="linguagem" action="copiar" id="${linguagem?.id}" class="btn btn-outline btn-primary btn-xs">
                                        Copiar linguagem
                                    </g:link>
                                    <g:link controller="linguagem" action="excluir" id="${linguagem?.id}" class="btn btn-outline btn-primary btn-xs">
                                        Excluir linguagem
                                    </g:link>
                                    <g:if test="${linguagem?.isPrivate}">
                                        <g:link controller="linguagem" action="makePublic" id="${linguagem?.id}" class="btn btn-primary btn-xs">
                                            Tornar Publica
                                        </g:link>
                                    </g:if>
                                    <g:else>
                                        <g:link controller="linguagem" action="makePrivate" id="${linguagem?.id}" class="btn btn-outline btn-default btn-xs">
                                            Tornar Privada
                                        </g:link>
                                    </g:else>
                                    <g:if test="${ linguagem?.autor in linguagem?.acessors }">
                                        <g:link controller="linguagem" action="disableLanguage" id="${linguagem?.id}" class="btn btn-outline btn-danger btn-xs">
                                            Desabilitar Linguagem
                                        </g:link>
                                    </g:if>
                                    <g:else>
                                        <g:link controller="linguagem" action="enableLanguage" id="${linguagem?.id}" class="btn btn-outline btn-success btn-xs">
                                            Habilitar linguagem
                                        </g:link>
                                    </g:else>
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
