<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="admin"/>
        <title>Mensagens - W-Educ</title>
    </head>
    <body>
    <script>
    </script>
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Mensagens</h1>
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Assunto</th>
                                <th>Remetente</th>
                                <th>Data</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <g:each in="${mensagens}" var="mensagem">
                            <tr>
                                <td>${mensagem?.assunto ? mensagem.assunto : "Sem assunto"}</td>
                                <td>${mensagem?.autor?.username}</td>
                                <td><g:formatDate format="dd/MM/yyyy" date="${mensagem?.data}"/></td>
                                <td>
                                    <g:link data-original-title="Visualizar" class="btn btn-sm btn-primary" controller="mensagem" action="ver" id="${mensagem?.id}"><i class="fa fa-eye fa-fw"></i></g:link>
                                    <g:link data-original-title="Responder" class="btn btn-sm btn-primary" controller="mensagem" action="escreverUsuario" id="${mensagem?.autor.id}"><i class="fa fa-reply fa-fw"></i></g:link>
                                    <g:link data-original-title="Exluir" action="excluirMensagem" data-toggle="tooltip" type="button" id="${mensagem?.id}" name="${mensagem?.id}" class="btn btn-sm btn-danger"><i class="fa fa-trash fa-fw"></i></g:link>
                                </td>
                            </tr>
                            </g:each>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <!-- /.row -->
    </body>
</html>
