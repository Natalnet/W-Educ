<html>
<head>
    <meta name="layout" content="admin"/>
    <title>Fórum</title>
</head>
<body>
    <h3 class="page-header">Confirmação</h3>
    <div class="alert alert-warning">
        <div class="pull-right">
            <form action='<g:createLink controller="forum" action="confirmBlock" id="${thread?.id}"/>' method="post">
                <p><button type="submit" class="btn btn-danger">Confirmar</button></p>
            </form>
        </div>
        Tem certeza que deseja bloquear o post <b>${thread?.title}</b>?
        <br />Essa ação não pode ser revertida!
    </div>
</body>
</html>
