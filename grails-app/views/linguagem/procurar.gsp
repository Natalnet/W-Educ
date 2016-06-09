<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="admin"/>
        <title>Programar - W-Educ</title>
    </head>
    <body>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <br>
                <div class="jumbotron">
                <div class="container">
                    <p><b>Programe</b> em diversos robôs utilizando a linguagem <a href="https://github.com/Natalnet/W-Educ/wiki/Tutorial-da-Linguagem-R-Educ"><b>R-Educ</b></a> ou qualquer linguagem cadastrada. Selecione na lista abaixo seu robô com a linguagem correspondente.</p>
                </div>
                </div>
                <h2 class="page-header"><b>Robôs x Linguagens</b></h2>
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
                                <td>${linguagem?.robot}</td>
                                <td>${linguagem?.name}</td>
                                <td>${linguagem?.autor?.username}</td>
                                <td><g:link controller="ambiente" action="programar" id="${linguagem?.id}">Programar</g:link></td>
                            </tr>
                            </g:each>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
