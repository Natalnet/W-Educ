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
            selector: '#threadBody',
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
    <h1 class="page-header">
        Editar
    </h1>
    <g:if test="${flash?.error}">
        <div class="alert alert-danger">
            ${flash?.error}
        </div>
    </g:if>
    <div class="row">
        <div class="col-lg-12">
            <form action='<g:createLink controller="thread" action="update" id="${thread.id}"/>' method="post">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Preencha os campos abaixo:
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="form-group">
                                    <label>Título do post:</label>
                                    <input type="text" class="form-control" id="threadTitle" name="threadTitle" value="${thread.title}" required>
                                </div>
                                <div class="form-group">
                                    <label>Texto do post:</label>
                                    <textarea class="form-control" id="threadBody" name="threadBody" rows="15">${thread.subject}</textarea>
                                </div>
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