<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="admin"/>
        <title>Cadastro de Linguagem - W-Educ</title>
        <style type="text/css">
            .operadores > div > span {
                width: 5em;
            }
        </style>
    </head>
    <body>
        <form action="<g:createLink action="atualizar" id="${linguagem?.id}"/>" method="post">
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Upload de Arquivos de Compilação</h1>
                    <p>
                        [Espaço reservado a instruções para organização e upload dos arquivos]. 
                    </p>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <h2>Linguagem ${linguagem?.name}</h2>
                    <div class="well">
                        <input type="text" class="form-control" id="nome" name="nome" required value="${linguagem?.name}" />
                    </div>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <input type="submit" class="btn btn-default" value="Upload dos arquivos" />
                </div>
                <p>
                    &nbsp;
                </p>
            </div>
        </form>
    </body>
</html>
