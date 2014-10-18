<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="admin"/>
        <title>Todas as Mensagens - W-Educ</title>
    </head>
    <body>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Todas as Mensagens</h1>
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Autor</th>
                                <th>Data</th>
                                <th>Mensagem</th>
                            </tr>
                        </thead>
                        <tbody>
                            <g:each in="${mensagens}" var="mensagem">
                            <tr>
                                <td>${mensagem?.autor?.username}</td>
                                <td><g:formatDate format="dd/MM/yyyy" date="${mensagem?.data}"/></td>
                                <td>${mensagem?.mensagem}</td>
                            </tr>
                            </g:each>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
