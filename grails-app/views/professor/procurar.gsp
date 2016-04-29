<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="admin"/>
        <title>Procurar Professores - W-Educ</title>
    </head>
    <body>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Procurar Professores</h1>
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Nome do professor</th>
                                <th>Nome de usuário</th>
                                <th>Opções</th>
                            </tr>
                        </thead>
                        <tbody>
                            <g:each in="${professores}" var="professor">
                            <tr>
                                <td>${professor?.name}</td>
                                <td>${professor?.username}</td>
                                <td><g:link controller="professor" action="selecionar" id="${professor?.id}">Selecionar professor</g:link></td>
                            </tr>
                            </g:each>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
