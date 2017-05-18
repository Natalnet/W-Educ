<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="admin"/>
        <title>Correções Pendentes - W-Educ</title>
    </head>
    <body>
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Correções Pendentes</h1>
            </div>
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <g:if test="${corrections.size() > 0}">
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Aluno</th>
                                    <th>Programa</th>
                                    <th>Solicitado em</th>
                                    <th>Opções</th>
                                </tr>
                            </thead>
                            <tbody>
                                <g:each in="${corrections}" var="correction">
                                        <tr>
                                            <td>
                                                ${correction?.program?.usuario?.username}
                                            </td>
                                            <td>
                                                ${correction?.program?.nome}
                                            </td>
                                            <td>
                                                <g:formatDate date="${correction?.requestedOn}" format="dd/MM/yyyy"/>
                                            </td>
                                            <td>
                                                <g:link controller="correction" action="correct" id="${correction?.id}" class="btn btn-outline btn-primary btn-xs">
                                                    Corrigir
                                                </g:link>
                                            </td>
                                        </tr>
                                </g:each>
                            </tbody>
                        </table>
                    </div>
                </g:if>
                <g:else>
                    <div class="alert alert-success">    
                        Não há correções pendentes.
                    </div>
                </g:else>
            </div>
        </div>
    </body>
</html>