<!DOCTYPE html>

<html>
<head>

    <meta name="layout" content="admin"/>
    <title>Fórum</title>

</head>
<body>   
    <h1 class="page-header">
        Novo Post
    </h1>
    <g:if test="${flash?.error}">
        <div class="alert alert-danger">
            ${flash?.error}
        </div>
    </g:if>
    <div class="row">
        <div class="col-lg-12">
            <form action='<g:createLink controller="thread" action="save" />' method="post">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Preencha os campos abaixo:
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="form-group">
                                    <label>Título do post:</label>
                                    <input type="text" class="form-control" id="postTitle" name="postTitle" required>
                                </div>
                                <div class="form-group">
                                    <label>Texto do post:</label>
                                    <textarea class="form-control" id="postBody" name="postBody" rows="3" required></textarea>
                                </div>
                                <input type="hidden" id="topic" name ="topic" value ="${topic.id}">
                            </div>
                        </div>
                    </div>
                    <div class="panel-footer">
                        <button type="submit" class="btn btn-primary">Salvar</button>
                        <button type="reset" class="btn btn-default">Limpar</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</body>
        
</html>