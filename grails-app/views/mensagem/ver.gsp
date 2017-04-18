<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="admin"/>
        <title>Mensagem - W-Educ</title>
    
        <link rel="stylesheet" type="text/css" href='${assetPath(src: "prismjs/prism.css")}'>
        <script src='${assetPath(src: "prismjs/prism.js")}'></script>
    </head>
    <body>
    <script>
    </script>
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">${mensagem?.assunto ? mensagem.assunto : "Sem assunto"}</h1>
                <i>Enviado por <b>${mensagem?.autor?.username}</b> em <g:formatDate format="dd/MM/yyyy" date="${mensagem?.data}"/></i>
                <div class="pull-right">
                    <g:link title="Voltar" class="btn btn-sm btn-primary" controller="mensagem" action="todas">
                        <i class="fa fa-arrow-left fa-fw"></i>
                    </g:link>
                    <g:link title="Responder" class="btn btn-sm btn-primary" controller="mensagem" action="escreverUsuario" id="${mensagem?.autor.id}">
                        <i class="fa fa-reply fa-fw"></i>
                    </g:link>
                    <g:link title="Exluir" class="btn btn-sm btn-danger" controller="mensagem" action="excluirMensagem" id="${mensagem?.id}">
                        <i class="fa fa-trash fa-fw"></i>
                    </g:link>
                </div>
                <hr>
                <p>${raw(mensagem?.mensagem)}</p>
            </div>
        </div>
        <!-- /.row -->
    </body>
</html>
