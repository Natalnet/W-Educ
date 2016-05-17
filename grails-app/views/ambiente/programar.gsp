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
        <script>	
            

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
                
                var apelido = $("#nome-do-programa").val() ;
                var msg = "" ;
                if ( apelido.search( /\s/g ) != -1 )
                {
                        msg+= "Não é permitido espaços em branco.\n" ;
                        apelido = apelido.replace( /\s/g , "" ) ;
                }	
                if ( apelido.search( /[^a-z0-9]/i ) != -1 )
                {
                        msg += "Não é permitido caracteres especiais.\n" ;
                        apelido = apelido.replace( /[^a-z0-9]/gi , "" ) ;
                }
                if ( msg )
                {
                        $("#nome-do-programa").val(apelido) ;
                        msg += "Nome do programa substituido por: ";
                        msg += apelido;
                        msg += ".";
                        alert(msg);
                }
                
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
                        enviarBtn.disabled = false;
                    },
                    fail: function () {
                        alert("Erro ao tentar compilar o programa.");
                    }
                });
            };

            // Enviar o programa
            var enviarCliente = function () {
                var hiddenIFrameID = 'hiddenDownloader',
                    iframe = document.getElementById(hiddenIFrameID);
                if (iframe === null) {
                    iframe = document.createElement('iframe');
                    iframe.id = hiddenIFrameID;
                    iframe.style.display = 'none';
                    document.body.appendChild(iframe);
                }
                iframe.src = "<g:createLink action="enviarCliente"/>?linguagem=${linguagem?.id}&reduc=" + verificarLinguagem() + "&nome=" + $("#nome-do-programa").val();
                //enviarArquivoEnvio();    
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

            // Excluir programa
            var excluirPrograma = function (id) {
                // Inicia requisição assíncrona
                // para acessar o código do programa
                $.ajax({
                    url: "<g:createLink action="excluirPrograma"/>",
                    type: "post",
                    data: {
                        id: id
                    },
                    success: function () {
                        // Substitui o nome do programa
                         alert("Programa excluído com sucesso.");
                         window.location.reload();
                    },
                    fail: function () {
                        alert("Erro ao tentar acessar o código do programa no banco de dados.");
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
                            if (returnData == "NO"){
                                alert("É necessário salvar o programa antes de exportar.");
                            } 
                            else{
                                editor.setValue(returnData);
                                // Limpa o nome do programa
                                $("#nome-do-programa").val("");
                                // Seleciona a linguagem alvo
                                $("#radio2").click();
                                // Substitui o conteúdo do editor de texto
                            }
                        },
                        fail: function () {
                            alert("Erro ao tentar acessar o código do programa no banco de dados.");
                        }
                    });
                } else {
                    // A linguagem alvo está selecionada.
                    // Não há o que fazer.
                    bootbox.alert("Só é possível exportar um programa escrito em R-Educ.");
                }
            };
            
        </script>
        <!-- /.row -->  
            
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Programar em 
                <label class="radio-inline">
                        <input type="radio" name="linguagemSelecionada" id="radio1" value="reduc" checked>
                        R-Educ
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="linguagemSelecionada" id="radio2" value="alvo">
                        ${linguagem?.name}
                    </label>  
                </h1>
                <div class="form-group">
                    Você está programando um robô <label>${linguagem?.robot} </label>.
                    <br/>
                    <label>Nome do programa: </label>
                    <input class="form-control" type="text" id="nome-do-programa" style="display: inline; width: 200px;" />
                    <button type="button" class="btn btn-outline btn-default" onclick="salvarPrograma();">Salvar</button>
                    <button type="button" class="btn btn-outline btn-default" onclick="listarProgramas();">Abrir</button>
                </div>
                <div class="form-group">
                    <button class="btn btn-primary" id="btn-new">Novo</button>
                    <button class="btn btn-info" data-toggle="modal" id="dicionario1" data-target="#dicionario">Dicionário de Funções</button>
                    <button class="btn btn-warning" onclick="compilarPrograma();">Compilar</button>
                    <button class="btn btn-success" onclick="baixarPrograma();">Baixar</button>
                    <button class="btn btn-info" onclick="exportarPrograma();">Exportar</button>
                    <button id="enviarBtn" class="btn btn-success" disabled onclick="enviarCliente();">Enviar</button>
                    <button class="btn btn-danger">Apagar</button>	
                </div>

                 <div id="editor_container" style="height: 450px;">
                	<div id="editor">// Olá! Comece a programar aqui.</div> 
                </div>
            </div>
        </div>

        <!-- diálogo novo -->
                <div class="modal fade dialogo-novo" id="dialogo-novo" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
                  <div class="modal-dialog modal-sm">
                    <div class="modal-content" style="text-align: center; padding: 10px;">
                        <br/><span style="font-size: 18px;">Tem certeza de que deseja criar um novo programa?</span><br/>
                        Todo o conteúdo do editor será apagado e as alterações não salvas serão perdidas.<br /><br />
                       <div class="form-group">
                           <button class="btn  btn-success" onclick="novoPrograma();">Sim</button>
                           <button class="btn  btn-danger" onclick="$('#dialogo-novo').modal('hide');">Não</button>
                       </div><br/>
                    </div>
                  </div>
                </div>
        <!-- / diálogo novo -->  

<!-- Accordion -->
    <div class="modal fade" id="dicionario" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
    <div class="modal-content">
            <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                            <span aria-hidden="true">&times;</span>
                            <span class="sr-only">Fechar</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">Dicionário de Funções</h4>
            </div>
        <div class="modal-body">
        <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
            <g:each in="${funcoes}" var="funcao">
                <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="${'heading'+funcao.id}">
                                <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion" href="${'#'+funcao.id}" aria-expanded="true" aria-controls="${funcao.id}">
                                                 <strong>${funcao.name} </strong>
                                        </a>
                                </h4>
                        </div>
                    <div id="${funcao.id}" class="panel-collapse collapse" role="tabpanel" aria-labelledby="${'heading'+funcao.id}">
                        <div class="panel-body">
                            <div class="row">
                              <div class="col-md-8">
                              	${funcao.description}
                              	<br>
                              	<strong>
                                Parâmetros:  
                                </strong> ${funcao.qntParameters.toInteger()}
                              </div>
                            </div>
                        </div>
                    </div>
                </div>
                <br>
            </g:each>
        </div>
        </div>
    </div>
    </div>
    </div>
        
        <asset:javascript src="js/ace/ace.js"/>
        <script>
            var editor = ace.edit("editor");
            editor.setTheme("ace/theme/github");
            editor.getSession().setMode("ace/mode/c_cpp");
            var editor_height = $("#editor_container").height();
            $("#editor").height(editor_height);
            //var editor_width = $("#editor_container").width();
            //$("#editor").width(editor_width);
            
            
            $("#radio1").click(function() {
              
                dicionario1.disabled = false;
            });
            
            $("#radio2").click(function() {
              
                dicionario1.disabled = true;
              
            });
            
            // Apaga o conteúdo do editor de texto
            // e limpa o nome do programa
            $("#btn-new").click(function() {
              
                // Abre modal para informar usuário sobre
                // possíveis perdas de informação
                $('#dialogo-novo').modal('show');
              
            });
            
            var novoPrograma = function () {
                // Apaga o conteúdo do editor
                editor.setValue("// Olá! Comece a programar aqui.");
                // Limpa o nome do programa
                $("#nome-do-programa").val("");
                // Fecha modal
                $('#dialogo-novo').modal('hide');
            };
            
        </script>
    </body>
</html>
