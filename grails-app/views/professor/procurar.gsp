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
                <br>
                <div class="jumbotron">
                <div class="container">
                    <p>Você pode ser acompanhado por um <b>professor</b>, solicitar correção dos seus exercícios e receber atividades personalizadas.
                        Caso você ainda não tenha um professor e deseja ser acompanhado solicite um dos nossos professores na lista abaixo.</p>
                    
                </div>
                </div>
                <h2><b>Professores Cadastrados</b></h2>
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Nome</th>
                                <th>Username</th>
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <g:each in="${professores}" var="professor">
                            <tr>
                                <td>${professor?.name}</td>
                                <td>${professor?.username}</td>
                                <td><g:link controller="professor" action="selecionar" id="${professor?.id}"><b>Solicitar Acompanhamento</b></g:link></td>
                                <td><g:link controller="mensagem" action="escreverUsuario" id="${professor?.id}"><b>Enviar Mensagem</b></g:link></td>                              
                            </tr>
                            </g:each>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
