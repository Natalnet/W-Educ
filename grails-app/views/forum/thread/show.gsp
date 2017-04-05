<!DOCTYPE html>

<html>
<head>

    <meta name="layout" content="admin"/>
    <title>Fórum</title>
    
    <link rel="stylesheet" type="text/css" href='${assetPath(src: "prismjs/prism.css")}'>
    <script src='${assetPath(src: "prismjs/prism.js")}'></script>
    <script src='https://cloud.tinymce.com/stable/tinymce.min.js?apiKey=wnz75ojnvnqzlbjqt35g5uzzuvamglqdz3500580yoi7o7c2'></script>
    <script>
        tinymce.init({
            selector: '#mensagem',
            language_url : '${assetPath(src: "tinymce/langs/pt_BR.js")}',
            menubar: false,
            plugins: [
              'advlist autolink lists link image charmap print preview anchor',
              'searchreplace visualblocks code fullscreen',
              'insertdatetime media table contextmenu paste code codesample'
            ],
            codesample_languages: [
                {text: 'JavaScript', value: 'javascript'},
                {text: 'HTML/XML', value: 'markup'},
                {text: 'CSS', value: 'css'},
                {text: 'PHP', value: 'php'},
                {text: 'Ruby', value: 'ruby'},
                {text: 'Python', value: 'python'},
                {text: 'Java', value: 'java'},
                {text: 'C', value: 'c'},
                {text: 'C#', value: 'csharp'},
                {text: 'C++', value: 'cpp'}
            ],
            toolbar: 'undo redo | insert | removeformat | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link | codesample'
        });
    </script>

</head>
<body>
    <br />
    <g:if test="${flash?.error}">
        <div class="alert alert-danger">
            ${flash?.error}
        </div>
    </g:if>
    <g:elseif test="${flash?.success}">
        <div class="alert alert-success">
            ${flash?.success}
        </div>
    </g:elseif>
    <div class="row">
        <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <i class="fa fa-comments fa-fw"></i> ${thread?.title}
                
                <div class="btn-group pull-right">
                    <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">
                        <i class="fa fa-chevron-down"></i>
                    </button>
                    <ul class="dropdown-menu slidedown">
                        <g:if test="${canEdit}">
                            <li>
                                <g:link controller="thread" action="edit" type="button" id="${thread?.id}">
                                    <i class="fa fa-edit fa-fw"></i> Editar
                                </g:link>
                            </li>
                        </g:if>
                        <sec:ifAnyGranted roles="ROLE_ADMIN">
                            <li>
                                <g:link controller="thread" action="block" type="button" id="${thread?.id}">
                                    <i class="fa fa-minus-circle fa-fw"></i> Bloquear
                                </g:link>
                            </li>
                        </sec:ifAnyGranted>
                    </ul>
                </div>
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                ${raw(thread?.subject)}
                <hr>
                <div class="text-right">
                    <i>Publicado em <g:formatDate date="${thread?.createDate}" format="dd/MM/yyyy"/> por <b>${thread?.opener?.username}</b></i>
                </div>
            </div>
            <!-- /.panel-body -->
            <div class="panel-footer">
                <ul class="chat">
                    <g:each in="${thread.comments}" var="comment">
                    <li class="left clearfix">
                        <span class="chat-img pull-left">
                            <img src="http://placehold.it/50/55C1E7/fff" alt="User Avatar" class="img-circle">
                        </span>
                        <div class="chat-body clearfix">
                            <div class="header">
                                <strong class="primary-font">${comment?.commentBy.username}</strong>
                                <small class="pull-right text-muted">
                                    <i class="fa fa-clock-o fa-fw"></i> <g:formatDate date="${comment.createDate}" format="dd/MM/yyyy HH:mm:ss"/>
                                </small>
                            </div>
                            <p>${comment?.body}</p>
                        </div>
                    </li>
                    </g:each>
                </ul>
                <form action='<g:createLink controller="forum" action="postReply" id="enviar"/>' method="post">
                    <div class="form-group">
                        <textarea id="mensagem" name="mensagem" class="form-control" rows='10'></textarea>
                        <input type="hidden" id="thread" name ="thread" value ="${thread.id}">
                    </div>
                    <div class="form-group">
                        <button class="btn btn-warning btn-sm" id="btn-chat">
                            Responder
                        </button>
                    </div>
                </form>
            </div>
            <!-- /.panel-footer -->
        </div>
    </div>
    </div>
        
  </body>
        
</html>