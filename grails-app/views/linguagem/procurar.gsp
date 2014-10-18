<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="admin"/>
        <title>Procurar Linguagens - W-Educ</title>
    </head>
    <body>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Procurar Linguagens</h1>
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Nome da Linguagem</th>
                                <th>Robô</th>
                                <th>Autor</th>
                                <th>Opções</th>
                            </tr>
                        </thead>
                        <tbody>
                            <g:each in="${linguagens}" var="linguagem">
                            <tr>
                                <td>${linguagem?.name}</td>
                                <td>${linguagem?.robot}</td>
                                <td>${linguagem?.autor?.username}</td>
                                <td><g:link controller="ambiente" action="programar" id="${linguagem?.id}">Programar linguagem</g:link></td>
                            </tr>
                            </g:each>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
