<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="admin"/>
        <title>Upload de Arquivos de Envio - W-Educ</title>
        <style type="text/css">
            .operadores > div > span {
                width: 5em;
            }
        </style>
    </head>
    <body>
        <form action="<g:createLink action="salvarEnvio" id="${linguagem?.id}"/>" method="post" enctype="multipart/form-data">
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Upload de Arquivos de Envio</h1>
                    <p>
                        [Espaço reservado a instruções para organização e upload dos arquivos]. 
                    </p>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <h2>Linguagem ${linguagem?.name}</h2>
                    <div class="well">
                        <input type="file" class="form-control" id="arquivo" name="arquivo" required />
                    </div>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <a href="javascript:window.close();" class="btn btn-default">Cancelar</a> 
                    <input type="submit" class="btn btn-default" value="Upload dos arquivos" />
                </div>
                <p>
                    &nbsp;
                </p>
            </div>
        </form>
    </body>
</html>
