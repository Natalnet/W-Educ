<!DOCTYPE html>
<html>
    
    <head>
        <meta name="layout" content="admin"/>
        <title>Correio - W-Educ</title>
    </head>
    <body>    
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Correio Eletrônico</h1>
                <div class="form-group">
                    <form action="<g:createLink action="enviar" id="enviar"/>" method="post">
                        
                                <div class="input-group">
                                 <span class="input-group-addon" id="basic-addon2"><b>Para:</b></span> 
                                 <input type="text" class="form-control" id="destinatario" name="destinatario" value="${destinatario?.username}"/>
                                </div><!-- /input-group -->
                        <br>
                        <textarea class="form-control" id="mensagem" name="mensagem" required placeholder="Escreva aqui sua mensagem..."></textarea><br/>
                        <input type="submit" class="btn btn-success" value="Enviar mensagem" />
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
