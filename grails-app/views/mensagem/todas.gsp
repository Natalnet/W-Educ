<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="admin"/>
        <title>Mensagens - W-Educ</title>
    </head>
    <body>
    <script>
    </script>    
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Mensagens</h1>
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Remetente</th>
                                <th>Data</th>
                                <th>Mensagem</th>
                                <th>Excluir</th>
                            </tr>
                        </thead>
                        <tbody>
                            <g:each in="${mensagens}" var="mensagem">
                            <tr>
                                <td>${mensagem?.autor?.username}</td>
                                <td><g:formatDate format="dd/MM/yyyy" date="${mensagem?.data}"/></td>
                                <td>${mensagem?.mensagem}</td>
                                <td>             <g:link data-original-title="Exluir" action="excluirMensagem" data-toggle="tooltip" type="button" id="${mensagem?.id}" name="${mensagem?.id}" class="btn btn-sm btn-danger"><i class="glyphicon glyphicon-remove"></i></g:link></td>
                                        
                            
                            </tr>
                            </g:each>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
