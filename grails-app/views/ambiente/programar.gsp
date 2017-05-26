<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="admin"/>
        <title>Ambiente Textual - W-Educ</title>
    
        <link rel="stylesheet" type="text/css" href='${assetPath(src: "prismjs/prism.css")}'>
        <script src='${assetPath(src: "prismjs/prism.js")}'></script>
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
            
            function waitSeconds(iMilliSeconds) {
                var counter= 0
                    , start = new Date().getTime()
                    , end = 0;
                while (counter < iMilliSeconds) {
                    end = new Date().getTime();
                    counter = end - start;
                }
            }

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
                if ( apelido == "" ){
                        msg += "É necessário nomear o programa para salvar. \n"
                }
                if ( msg )
                {       
                        if (apelido == "")
                            alert(msg);
                        else{    
                            $("#nome-do-programa").val(apelido) ;
                            msg += "Nome do programa substituido por: ";
                            msg += apelido;
                            msg += ".";
                            alert(msg);
                        }    
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
                
                $('#comp-result').html('Compilando o programa...');

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
                        $('#comp-result').html(returnData);
                        enviarBtn.disabled = false;
                    },
                    fail: function () {
                        alert("Erro ao tentar compilar o programa.");
                    }
                });
            };
            
            // Solicitar correção ao professor
            var solicitarCorrecao = function () {
                
                // Inicia requisição assíncrona
                // para solicitar correção
                $.ajax({
                    url: "<g:createLink controller="correction" action="store"/>",
                    type: "post",
                    data: {
                        program: $("#program_id").val(),
                        linguagem: ${linguagem?.id},
                        reduc: verificarLinguagem(),
                        codigo: editor.getValue()
                    },
                    success: function (returnData) {
                        alert("Correção solicitada com sucesso.");
                    },
                    fail: function () {
                        alert("Erro ao tentar solicitar correção.");
                    }
                });
            };
            
            // Compilar o programa
            var compilarProgramaEnvio = function () {

                // Inicia requisição assíncrona
                // para compilar o programa
                $.ajax({
                    url: "<g:createLink action="compilarProgramaEnvio"/>",
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
                compilarProgramaEnvio();
                waitSeconds(1000);
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
                        $('#prog-'+id).remove();
                        $('#prog-item-'+id).remove();
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
                        $("#program_id").val(id);
                        // Substitui o conteúdo do editor de texto
                        editor.setValue(returnData);
                    },
                    fail: function () {
                        alert("Erro ao tentar acessar o código do programa no banco de dados.");
                    }
                });
            };
            
            var novoPrograma = function () {
                // Apaga o conteúdo do editor
                editor.setValue("// Olá! Comece a programar aqui.");
                // Limpa o nome do programa
                $("#nome-do-programa").val("");
                // Fecha modal
                $('#dialogo-novo').modal('hide');
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
        
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">
                    Ambiente de Programação
                </h1>
            </div>
        </div>
        <!-- /.row -->  
            
        <div class="row">
            <div class="col-lg-12">
                <div class="row">
                    <div class="col-lg-3">
                        <div class="form-group">
                            <label>Robô:</label>
                        <p class="form-control-static">${linguagem?.robot}</p>
                        </div>
                    </div>
                    <div class="col-lg-3">
                        <div class="form-group">
                            <label>Programar em:</label>
                            <div class="radio">
                                <label class="radio-inline">
                                    <input type="radio" name="linguagemSelecionada" id="radio1" value="reduc" checked>
                                    R-Educ
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="linguagemSelecionada" id="radio2" value="alvo">
                                    ${linguagem?.name}
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="form-group">
                            <label>Nome do programa:</label>
                            <div class="input-group">
                                <input class="form-control" type="text" id="nome-do-programa" value="${programa?.nome}"/>
                                <span class="input-group-btn">
                                    <button type="button" class="btn btn-outline btn-default" onclick="salvarPrograma();">Salvar</button>
                                </span>
                            </div>
                            <input type="hidden" id="program_id" />
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-12">
                <div class="row">
                    <div class="col-lg-3">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        Funções R-Educ
                                    </div>

                                    <!-- List group -->
                                    <ul class="list-group" style="max-height: 200px; overflow-y: scroll;">
                                        <g:each in="${funcoes}" var="funcao">
                                            <li class="list-group-item">
                                                <a href="#" data-toggle="modal" data-target="#func-${funcao.id}">${funcao.name}</a>
                                            </li>
                                            <!-- Modal -->
                                            <div class="modal fade" id="func-${funcao.id}" tabindex="-1" role="dialog" aria-labelledby="func-${funcao.id}">
                                                <div class="modal-dialog" role="document">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                            ${funcao.name}
                                                        </div>
                                                        <div class="modal-body">
                                                            ${raw(funcao.description)}
                                                            <p><b>Parâmetros:</b> ${funcao.qntParameters.toInteger()}</p>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </g:each>
                                    </ul>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        Programas
                                    </div>

                                    <!-- List group -->
                                    <ul class="list-group" style="max-height: 200px; overflow-y: scroll;">
                                        <g:if test="${programa}">
                                            <li class="list-group-item active">
                                                ${programa.nome}
                                            </li>
                                        </g:if>
                                        <g:each in="${programas}" var="item">
                                            <li class="list-group-item" id="prog-item-${item.id}">
                                                <a href="#" data-toggle="modal" data-target="#prog-${item.id}">
                                                    ${item.nome}
                                                </a>
                                            </li>
                                            <!-- Modal -->
                                            <div class="modal fade" id="prog-${item.id}" tabindex="-1" role="dialog" aria-labelledby="prog-${item.id}">
                                                <div class="modal-dialog" role="document">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                            ${item.nome} (Modificado em <g:formatDate date="${item.modificadoEm}" format="dd/MM/yyyy"/>)
                                                        </div>
                                                        <div class="modal-body">
                                                            <pre class=" language-cpp">${item.codigo}</pre>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                                                            <button type="button" class="btn btn-danger" data-dismiss="modal" onclick="excluirPrograma(${item.id});">Excluir</button>
                                                            <g:link controller="ambiente" action="programar" id="${item.id}" class="btn btn-primary">
                                                                Abrir
                                                            </g:link>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </g:each>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-9">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <div class="btn-group" role="group" aria-label="...">
                                    <button class="btn btn-default" id="btn-new" onclick="$('#dialogo-novo').modal('show');">Novo</button>
                                    <button class="btn btn-default" onclick="compilarPrograma();">Compilar</button>
                                    <button class="btn btn-default" onclick="baixarPrograma();">Baixar</button>
                                    <button class="btn btn-default" id="converter" onclick="exportarPrograma();">Converter para ${linguagem?.name}</button>
                                    <button id="enviarBtn" class="btn btn-default" disabled onclick="enviarCliente();">Enviar</button>
                                    <button class="btn btn-default" onclick="solicitarCorrecao();">Correção</button>
                                    <!--<button class="btn btn-danger">Apagar</button>	-->
                                </div>
                            </div>
                            <div id="editor" style="height: 400px">${(programa?.codigo) ?: "// Olá! Comece a programar aqui."}</div>
                            <div class="panel-footer">
                                <div id="comp-result"></div>
                            </div>
                        </div>
                    </div>
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
        
        <asset:javascript src="js/ace/ace.js"/>
        <script>
            var editor = ace.edit("editor");
            editor.setTheme("ace/theme/github");
            editor.getSession().setMode("ace/mode/c_cpp");
            
            
            $("#radio1").click(function() {
                converter.disabled = false;
            });
            
            $("#radio2").click(function() {
                converter.disabled = true;
            });
            
            // Apaga o conteúdo do editor de texto
            // e limpa o nome do programa
            $("#btn-new").click(function() {
              
                // Abre modal para informar usuário sobre
                // possíveis perdas de informação
                $('#dialogo-novo').modal('show');
              
            });
            
            $(document).ready(function(){
                var original_txt = $('.descricao').html();
                var display_txt = original_txt.replace(/\n/g, '<br />');
                $('.descricao').html(display_txt);
            });
            
        </script>
    </body>
</html>
