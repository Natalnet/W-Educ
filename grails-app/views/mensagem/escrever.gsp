<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="admin"/>
        <title>Enviar Mensagem - W-Educ</title>
    </head>
    <body>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Enviar Mensagem</h1>
                <div class="form-group">
                    <form action="enviar" method="post">
                        <h2>Mensagem para ${destinatario}</h2>
                        <textarea class="form-control" id="mensagem" name="mensagem" required placeholder="Escreva aqui..."></textarea><br/>
                        <input type="submit" class="btn btn-default" value="Enviar mensagem" />
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
