<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="admin"/>
        <title>Ambiente Textual - W-Educ</title>
        <style type="text/css" media="screen">
            #editor { 
                height: 600px;
                position: absolute;
                top: 22em;
                right: 0;
                bottom: 0;
                left: 0;
            }
        </style>
    </head>
    <body>
        <script type="text/javascript">
            // Apaga o conteúdo do editor de texto
            // e limpa o nome do programa
            var novoPrograma = function () {
                // Apaga o conteúdo do editor
                editor.setValue("// Olá! Comece a programar aqui.");
                // Limpa o nome do programa
                $("#nome-do-programa").val("");
            };

            // Verifica se a linguagem selecionada
            // é a R-Educ ou a Linguagem Alvo
            var verificarLinguagem = function () {
                if($("#radio1").is(":checked")) {
                    return "1"
                } else {
                    return "0"
                }
            };

            // Salva o programa no banco de dados
            var salvarPrograma = function () {
                // Inicia requisição assíncrona
                // para salvar o programa no banco
                $.ajax({
                    url: "<g:createLink action="salvarPrograma"/>",
                    type: "post",
                    data: {
                        linguagem: ${linguagem?.id},
                        reduc: verificarLinguagem(),
                        nome: $("#nome-do-programa").val(),
                        codigo: editor.getValue()
                    },
                    success: function () {
                        alert("Programa salvo no banco de dados com sucesso.");
                    },
                    fail: function () {
                        alert("Erro ao tentar gravar o programa no banco de dados.");
                    }
                });
            };

            // Compilar o programa
            var compilarPrograma = function () {

                // Inicia requisição assíncrona
                // para compilar o programa
                $.ajax({
                    url: "<g:createLink action="compilarPrograma"/>",
                    type: "post",
                    data: {
                        linguagem: ${linguagem?.id},
                        reduc: verificarLinguagem(),
                        nome: $("#nome-do-programa").val(),
                        codigo: editor.getValue()
                    },
                    success: function (returnData) {
                        alert(returnData);
                    },
                    fail: function () {
                        alert("Erro ao tentar gravar o programa no banco de dados.");
                    }
                });
            };

            // Baixar o programa
            var baixarPrograma = function () {
                var hiddenIFrameID = 'hiddenDownloader',
                    iframe = document.getElementById(hiddenIFrameID);
                if (iframe === null) {
                    iframe = document.createElement('iframe');
                    iframe.id = hiddenIFrameID;
                    iframe.style.display = 'none';
                    document.body.appendChild(iframe);
                }
                iframe.src = "<g:createLink action="baixarPrograma"/>?linguagem=${linguagem?.id}&reduc=" + verificarLinguagem() + "&nome=" + $("#nome-do-programa").val();
            };
        </script>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Programar em ${linguagem?.name}</h1>
                <div class="form-group">
                    <label>Selecione a linguagem: </label>
                    <label class="radio-inline">
                        <input type="radio" name="linguagemSelecionada" id="radio1" value="reduc" checked>
                        R-Educ
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="linguagemSelecionada" id="radio2" value="alvo">
                        ${linguagem?.name}
                    </label>
                    <br/>
                    <label>Nome do programa: </label>
                    <input class="form-control" type="text" id="nome-do-programa" style="display: inline; width: 200px;" />
                    <button type="button" class="btn btn-outline btn-default" onclick="salvarPrograma();">Salvar o programa</button>
                </div>
                <div class="form-group">
                    <button class="btn btn-info" onclick="novoPrograma();">Novo programa</button>
                    <button class="btn btn-success" onclick="compilarPrograma();">Compilar programa</button>
                    <button class="btn btn-warning" onclick="baixarPrograma();">Baixar programa</button>
                    <button class="btn btn-danger">Apagar programa</button>
                </div>
                <div id="editor">// Olá! Comece a programar aqui.</div>
            </div>
        </div>
            
        <asset:javascript src="js/ace/ace.js"/>
        <script>
            var editor = ace.edit("editor");
            editor.setTheme("ace/theme/github");
            editor.getSession().setMode("ace/mode/c_cpp");
            var editor_height = $("#editor_container").height();
            $("#editor").height(editor_height);
        </script>
    </body>
</html>
