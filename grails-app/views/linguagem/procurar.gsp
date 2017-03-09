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
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Robô</th>
                                <th>Nome da Linguagem</th>
                                <th>Autor</th>
                                <th>Opções</th>
                            </tr>
                        </thead>
                        <tbody>
                            <g:each in="${linguagens}" var="linguagem">
                            <tr>
                                <td><b>${linguagem?.robot}</b></td>
                                <td>
                                    <b>${linguagem?.name}</b>
                                    <p class="help-block">${linguagem?.description}</p>
                                </td>
                                <td><b>${linguagem?.autor?.username}</b></td>
                                <td>
                                    <g:link class="btn btn-outline btn-primary" controller="ambiente" action="programar" id="${linguagem?.id}">
                                        Programar
                                    </g:link>
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
