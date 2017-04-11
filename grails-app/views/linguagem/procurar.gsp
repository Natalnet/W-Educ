<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="admin"/>
        <title>Programar - W-Educ</title>
    </head>
    <body>
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Linguagens Habilitadas</h1>
            </div>
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <div class="well">
                    <p><b>Programe</b> em diversos robôs utilizando a linguagem <a href="https://github.com/Natalnet/W-Educ/wiki/Tutorial-da-Linguagem-R-Educ"><b>R-Educ</b></a> ou qualquer linguagem cadastrada. Selecione na lista abaixo seu robô com a linguagem correspondente.</p>
                </div>
            </div>
            <div class="col-lg-12">
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>Linguagem</th>
                                <th>Robô</th>
                                <th>Descrição</th>
                                <th>Autor</th>
                            </tr>
                        </thead>
                        <tbody>
                            <g:each in="${linguagens}" var="linguagem">
                            <tr>
                                <td>
                                    <g:link controller="ambiente" action="programar" id="${linguagem?.id}">
                                        <b>${linguagem?.name}</b>
                                    </g:link>
                                </td>
                                <td><b>${linguagem?.robot}</b></td>
                                <td>${linguagem?.description}</td>
                                <td><b>${linguagem?.autor?.username}</b></td>
                            </tr>
                            </g:each>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
