<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="admin"/>
        <title>Ambiente Textual - W-Educ</title>
    </head>
    <body>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Programar em ${linguagem?.name}</h1>
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
                                <td><g:link controller="ambiente" action="programar" id="${linguagem?.id}">${linguagem?.name}</g:link></td>
                                <td><g:link controller="linguagem" action="editar" id="${linguagem?.id}">Editar linguagem</g:link></td>
                            </tr>
                            </g:each>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
