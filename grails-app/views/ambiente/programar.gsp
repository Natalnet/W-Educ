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

            // Listar programas
            var listarProgramas = function () {
                
                // Inicia requisição assíncrona
                // para listar os programas
                $.ajax({
                    url: "<g:createLink action="listarProgramas"/>",
                    type: "post",
                    data: {
                        linguagem: ${linguagem?.id},
                        reduc: verificarLinguagem(),
                    },
                    success: function (returnData) {
                        bootbox.alert(returnData);
                    },
                    fail: function () {
                        alert("Erro ao tentar listar os programa no banco de dados.");
                    }
                });
            };

            // Abrir programa
            var abrirPrograma = function (id, nome) {
                
                // Inicia requisição assíncrona
                // para acessar o código do programa
                $.ajax({
                    url: "<g:createLink action="abrirPrograma"/>",
                    type: "post",
                    data: {
                        id: id
                    },
                    success: function (returnData) {
                        // Substitui o nome do programa
                        $("#nome-do-programa").val(nome);
                        // Substitui o conteúdo do editor de texto
                        editor.setValue(returnData);
                    },
                    fail: function () {
                        alert("Erro ao tentar acessar o código do programa no banco de dados.");
                    }
                });
            };

            // Exportar programa
            var exportarPrograma = function () {
                // Verifica se a linguagem selecionada
                // é a R-Educ ou a Linguagem Alvo
                if($("#radio1").is(":checked")) {
                    // A linguagem seleciona é a R-Educ.
                    // Inicia requisição assíncrona
                    // para acessar o código do programa.
                    $.ajax({
                        url: "<g:createLink action="exportarPrograma"/>",
                        type: "post",
                        data: {
                            linguagem: ${linguagem?.id},
                            reduc: verificarLinguagem(),
                            nome: $("#nome-do-programa").val()
                        },
                        success: function (returnData) {
                            // Limpa o nome do programa
                            $("#nome-do-programa").val("");
                            // Seleciona a linguagem alvo
                            $("#radio2").click();
                            // Substitui o conteúdo do editor de texto
                            editor.setValue(returnData);
                        },
                        fail: function () {
                            alert("Erro ao tentar acessar o código do programa no banco de dados.");
                        }
                    });
                } else {
                    // A linguagem alvo está selecionada.
                    // Não há o que fazer.
                    bootbox.alert("Não é possível exportar um programa que já está escrito na Linguagem Alvo.");
                }
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
                    <button type="button" class="btn btn-outline btn-default" onclick="salvarPrograma();">Salvar</button>
                    <button type="button" class="btn btn-outline btn-default" onclick="listarProgramas();">Abrir</button>
                </div>
                <div class="form-group">
                    <button class="btn btn-primary" onclick="novoPrograma();">Novo</button>
                    <button class="btn btn-warning" onclick="compilarPrograma();">Compilar</button>
                    <button class="btn btn-success" onclick="baixarPrograma();">Baixar</button>
                    <button class="btn btn-info" onclick="exportarPrograma();">Exportar</button>
                    <button class="btn btn-danger">Apagar</button>
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
