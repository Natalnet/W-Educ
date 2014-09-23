<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="admin"/>
        <title>Cadastro de Linguagem - W-Educ</title>
    </head>
    <body>
        <!-- /.row -->
        <div class="row">
    <div class="span9">


                <!-- Overview
                ================================================== -->
                
                <section id="cadastro">
                    <br>
                    <br>
                    <div class="page-header">
                        <h1>Cadastro de Linguagem</h1>
                    </div>
                    <p>Para cadastrar uma linguagem você deverá preencher todos os campos seguindo os exemplos aprensentados.
                        Observe que existem algumas palavras específicas que são utilizadas para identificar e tratar os dados fornecidos,
                        como por exemplo: <b>comandos</b>, <b>comandos1</b>, <b>comandos2</b>, <b>principal</b>, <b>nomedoprograma</b>,
                        <b>funcao</b>, <b>variavel</b>, <b>valor</b>. 
                    </p>
                    <p>Os únicos dados que não são de preenchimento obrigatório são: <b>descrição</b>, <b>cabeçalho</b>, <b>rodapé</b>  e <b>funções</b>.
                       Caso você professor deseje facilitar a manipulação da linguagem para os seus alunos é possível utilizar o cabeçalho
                       para definir as funções que serão apenas manipuladas ao serem cadastradas no item <b>funções</b>. A palavra chave <b>nomedoprograma</b> 
                       ao ser encontrada nos itens do cadastro será substituída pelo nome do programa salvo pelo usuário.</p> 
                       <p>Ao término do preenchimento, você deve validar a linguagem clicando na imagem de validação. Caso você tenha deixado
                           de utilizar as palavras reservadas em um dos itens o sistema o informará marcando de vermelho o item.</p>
                       </section>

                       <hr class="bs-docs-separator">
                       <!--                Descrição-->
                       <section id="descricao">
                        <br>
                        <br>
                        <div class="page-header">
                            <h1>Descrição da linguagem</h1>
                        </div>
                        <div class="well well-small" id="console-log-description">
                            <textarea rows="10" cols="50" id="ta-description">LEGO</textarea>
                        </div>

                    </section>

                    <hr class="bs-docs-separator">
                    <!--Compilacao-->
                    <section id="compilacao">
                        <br>
                        <br>
                        <div class="page-header">
                          <h1>Compilação e Envio</h1>
                      </div>

                      <h3>Compilação</h3>
                      <p>Nesta seção você deve inserir a chamada de compilação em Windows, a extensão do arquivo
                        gerado na sua linguagem e o compilador a ser utilizado. Os dados fornecidos serão utilizados para geração do código
                        intermediário na linguagem NXC e geração do código objeto para envio ao robô. </p>
                        <p> O código de compilação deve ser inserido referenciando a localização de todos os arquivos necessários(códigos fonte 
                            e compilador) com a palavra chave <b>diretorio</b> para indicar o endereço dos arquivos. Já o nome do programa deve
                            ser indicado com a palavra chave <b>nomedoprograma</b>
                            Por exemplo, caso o seu código de compilação seja 
                            <b>nbc nomedoprograma.nxc -O=nomedoprograma.rxe </b> ele deverá ser escrito da seguinte forma: 
                            <b>diretorio/nbc diretorio/nomedoprograma.nxc -O=diretorio/nomedoprograma.rxe </b>
                            <p>Caso seja necessário descompactar os arquivos do compilador ou realizar algum tipo de instalação. Marque o item
                                <b>Instalação necessária</b>.</p>    

                                <p>Código de compilação:</p>
                                <div class="well well-small" id="console-log-compileCode">
                                    <input id="ip-compile-code" type="text" value="" size="">
                                </div>
                                <p>Extensão dos arquivos na linguagem NXC:</p>
                                <div class="well well-small" id="console-log-extension">
                                    <input id="ip-extension" type="text" value="" size="">
                                </div>
                                <p>Upload do compilador:</p>
                                <div class="well well-small" id="console-log-compilerFile">
                                    <a href="javascript:uploadCompiler();" class="btn">Faça o upload do compilador</a>
                                    <label id="lbl-compiler"></label>
                                    <label class="checkbox"><input type="checkbox" id="cb-install"> Instalação necessária.</label>
                                </div>

                                <h3>Envio</h3>
                                <p>Nesta seção você deve inserir a chamada de envio do programa em sua plataforma e a extensão do 
                                    código a ser enviado ao computador local para posteriormente ser enviado ao robô através da chamada inserida.</p>
                                    <p> O código de envio deve ser inserido referenciando a localização de todos os arquivos necessários(códigos fonte 
                                        e programa de envio) com a palavra chave <b>diretorio</b> para indicar o endereço dos arquivos. Já o nome do programa deve
                                        ser indicado com a palavra chave <b>nomedoprograma</b>
                                        Por exemplo, caso o seu código de envio seja 
                                        <b>nbc -d nomedoprograma.nxc </b> ele deverá ser escrito da seguinte forma: 
                                        <b>diretorio/nbc -d diretorio/nomedoprograma.nxc</b>. Esse endereço de diretório será tratado e substituído pelo
                                        endereço de sua pasta local de arquivos temporários.

                                        <p>Código de envio:</p>
                                        <div class="well well-small" id="console-log-sendCode">
                                            <input id="ip-send-code" type="text" value="" size="">
                                        </div>
                                        <p>Extensão do arquivo a ser enviado:</p>
                                        <div class="well well-small" id="console-log-sendExtension">
                                            <input id="ip-sendExtension" type="text" value="" size="">
                                        </div>
                                        <p>Upload do arquivo a ser enviado ao computador local para permitir o envio:</p>
                                        <div class="well well-small" id="console-log-sendFile">
                                            <a href="javascript:uploadSend();" class="btn">Faça o upload do programa de envio</a>
                                            <label id="lbl-send"></label>
                                            <label class="checkbox"><input type="checkbox" id="cb-send"> Envio necessário.</label>
                                            Nome do arquivo: <input class="span3" id="ip-fileName" type="text" value="" size="">
                                        </div>


                                    </section>

                                    <hr class="bs-docs-separator">
                                    <!--Cabecalho e rodape-->
                                    <section id="header-footnote">
                                        <br>
                                        <br>
                                        <div class="page-header">
                                            <h1>Cabeçalho e Rodapé</h1>
                                        </div>

                                        <p>Nesta seção você deve inserir o cabeçalho e rodapé necessários para gerar
                                            o seu código. Lembre-se de incluir todas as bibliotecas e fazer todas as definições
                                            necessárias para o funcionamento do seu programa. O cabeçalho e o rodapé inseridos serão 
                                            adicionados a todos os códigos gerados para esta
                                            linguagem.</p>
                                            <p>Caso você deseje facilitar a manipulação da linguagem para os seus alunos é possível utilizar o cabeçalho
                                             para definir as funções que serão manipuladas ao serem cadastradas no item <b>funções</b>. Novas estruturas
                                             também podem ser definidas nesta seção.
                                         </p>

                                         <table>
                                            <td>
                                                <h4 align="center">Cabeçalho</h4>
                                                <div class="well well-small span4" id="console-log-header">
                                                    <textarea wrap="off" rows="10" id="ta-header"></textarea>
                                                </div>
                                            </td>
                                            <td>&nbsp &nbsp &nbsp</td>
                                            <td>
                                                <h4 align="center">Rodapé</h4>
                                                <div class="well well-small span4" id="console-log-footnote">
                                                    <textarea wrap="off" rows="10" id="ta-footnote"></textarea>
                                                </div>
                                            </td>
                                        </table>

                                    </section>

                                    <hr class="bs-docs-separator">
                                    <!--Dec. de funcoes-->
                                    <section id="mainfunction">
                                        <br>
                                        <br>
                                        <div class="page-header">
                                            <h1>Declarações de Funções</h1>
                                        </div>

                                        <h3>Função principal</h3>

                                        <p>Nesta seção você deve declarar a função principal do seu programa.</p>
                                        <p>Os exemplos abaixo mostram como é definida a função principal nas linguagens de
                                            programação R-Educ e C. Observe que a palavra reservada <b>comandos</b> deve ser utilizada.
                                        </p>

                                        <div class="span8">
                                            <div class="span5">
                                                <h4 align="center">Função Principal</h4>
                                                <div class="well well-small" id="console-log-mainFunction">
                                                    <textarea wrap="off" rows="7" id="ta-mainFunction"></textarea>
                                                </div>
                                            </div>
                                            <div class="span2">
                                                <table>
                                                    <tr>
                                                        <h4 align="center">Linguagem R-Educ</h4>
                                                        <div class="alert" id="console-log">
                                                            tarefa principal {<br>
                                                            &nbsp &nbsp &nbsp &nbsp &nbsp comandos<br>
                                                        }
                                                    </div>
                                                </tr>
                                                <tr>
                                                    <h4 align="center">Linguagem C</h4>
                                                    <div class="alert" id="console-log">
                                                        int main() {<br>
                                                        &nbsp &nbsp &nbsp &nbsp &nbsp comandos<br>
                                                        &nbsp &nbsp &nbsp &nbsp &nbsp return 0;<br>
                                                    }
                                                </div>
                                            </tr>
<!--                                <tr>
                                    <h4 align="center">Linguagem Java</h4>
                                    <div class="alert" id="console-log">
                                        class principal {<br>
                                        &nbsp &nbsp &nbsp &nbsp &nbsp comandos<br>
                                        }
                                    </div>
                                </tr>-->
                            </table>
                        </div>
                    </div>
                    
                    <h3>Funções</h3>

                    <p>Nesta seção você deve identificar como é feita a declaração de funções sem retorno do seu programa.</p>
                    <p>Os exemplos abaixo mostram como são definidas as funções sem retorno nas linguagens de
                        programação R-Educ e C. Observe que as palavras reservadas <b>comandos</b> e <b>funcao</b>
                        devem ser utilizadas.
                    </p>
                    
                    <div class="col-lg-9">
                        <div class="col-lg-5">
                            <h4 align="center">Funções</h4>
                            <div class="well well-small" id="console-log-otherFunctions">
                                <textarea wrap="off" rows="7" id="ta-otherFunctions"></textarea>
                            </div>
                        </div>
                        <div class="col-lg-3" style="max-width:200px;">
                            <table>
                                <tr>
                                    <h4 align="center">Linguagem R-Educ</h4>
                                    <div class="well" id="console-log">
                                        tarefa funcao {<br>
                                        &nbsp &nbsp &nbsp &nbsp &nbsp comandos<br>
                                    }
                                    </div>
                                </tr>
                                <tr>
                                    <h4 align="center">Linguagem C</h4>
                                    <div class="well" id="console-log">
                                        void funcao() {<br>
                                        &nbsp &nbsp &nbsp &nbsp &nbsp comandos<br>
                                    }
                                    </div>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>

            <p>Depois que a função foi declarada, ela pode ser chamada.
                Os exemplos abaixo mostram como são feitas chamadas de funções nas linguagens de
                programação R-Educ e C. Observe que a palavra reservada <b>funcao</b>
                deve ser utilizada.</p>

                <div class="span9">
                    <div class="span1">
                    </div>
                    <div class="span3">
                        <h4 align="center">Chamada de Funções</h4>
                        <div class="well well-small" id="console-log-callFunction">
                            <textarea wrap="off" rows="5" id="ta-callFunction"></textarea>
                        </div>
                    </div>
                    <div class="span3" style="max-width:200px;">
                        <table>
                            <tr>
                                <h4 align="center">Linguagem R-Educ</h4>
                                <div class="alert" id="console-log">
                                    funcao
                                </div>
                            </tr>
                            <tr>
                                <h4 align="center">Linguagem C</h4>
                                <div class="alert" id="console-log">
                                    funcao();
                                </div>
                            </tr>
                        </table>
                    </div>
                </div>

                <br><br><br><br><br><br><br><br><br>

            </section>

            <hr class="bs-docs-separator">
            <hr class="bs-docs-separator">
            <!--tipos-->
            <section id="types">
                <br>
                <br>
                <div class="page-header">
                    <h1>Tipos de Dados</h1>
                </div>

                <p>Nesta seção você deve definir como são declaradas variáveis 
                    utilizando tipos específicos de dados na linguagem
                    NXC.</p>
                    <p>A linguagem R-Educ possui três tipos de dados: <b>texto</b>, <b>numero</b>
                        e <b>booleano</b>. Siga os exemplos abaixo para declarar as variáveis. Observe que as palavras 
                        reservadas <b>variavel</b> e <b>valor</b> devem ser utilizadas.</p> 

                        <p>Caso deseje selecionar um dos tipos já criados,
                            <a href="javascript:chooseTypes();">clique aqui</a>.
                        </p>

                        <div class="span9">
                            <div class="span1">
                            </div>
                            <div class="span3">
                                <h4 align="center">Declaração do tipo texto</h4>
                                <div class="well well-small" id="console-log-dec-str">
                                    <textarea wrap="off" rows="3" id="ta-dec-str"></textarea>
                                </div>
                            </div>
                            <div class="span3">
                                <table>
                                    <tr>
                                        <h4 align="center">Linguagem R-Educ</h4>
                                        <div class="alert" id="console-log">
                                            texto variavel = "valor"
                                        </div>
                                    </tr>
                                    <tr>
                                        <h4 align="center">Linguagem C</h4>
                                        <div class="alert" id="console-log">
                                            String variavel = "valor";
                                        </div>
                                    </tr>
                                </table>
                            </div>
                        </div>

                        <p>Variáveis do tipo <b>numero</b> recebem valores inteiros ou em ponto flutuante.</p>

                        <div class="span9">
                            <div class="span1">
                            </div>
                            <div class="span3">
                                <h4 align="center">Declaração do tipo numero</h4>
                                <div class="well well-small" id="console-log-dec-float">
                                    <textarea wrap="off" rows="3" id="ta-dec-float"></textarea>
                                </div>
                            </div>
                            <div class="span3">
                                <table>
                                    <tr>
                                        <h4 align="center">Linguagem R-Educ</h4>
                                        <div class="alert" id="console-log">
                                            numero variavel = valor
                                        </div>
                                    </tr>
                                    <tr>
                                        <h4 align="center">Linguagem C</h4>
                                        <div class="alert" id="console-log">
                                            float variavel = valor;
                                        </div>
                                    </tr>
                                </table>
                            </div>
                        </div>

                        <div class="span9">
                            <div class="span1">
                            </div>
                            <div class="span3">
                                <h4 align="center">Declaração do tipo booleano</h4>
                                <div class="well well-small" id="console-log-dec-bool">
                                    <textarea wrap="off" rows="3" id="ta-dec-bool"></textarea>
                                </div>
                            </div>
                            <div class="span3">
                                <table>
                                    <tr>
                                        <h4 align="center">Linguagem R-Educ</h4>
                                        <div class="alert" id="console-log">
                                            booleano variavel = valor
                                        </div>
                                    </tr>
                                    <tr>
                                        <h4 align="center">Linguagem C</h4>
                                        <div class="alert" id="console-log">
                                            bool variavel = valor;
                                        </div>
                                    </tr>
                                </table>
                            </div>
                        </div>
                        <p>Variáveis do tipo <b>booleano</b> podem receber valores do tipo verdadeiro ou falso.
                            Especifique como esses valores são chamados na linguagem cadastrada.</p>
                            <div class="span9">
                                <div class="span2">
                                    <h4 align="center">Verdadeiro</h4>
                                    <div class="well well-small" id="console-log-dec-true">
                                        <textarea wrap="off" rows="3" id="ta-dec-true"></textarea>
                                    </div>
                                </div>
                                <div class="span2">
                                    <table>
                                        <tr>
                                            <h4 align="center">Linguagem R-Educ</h4>
                                            <div class="alert" id="console-log">
                                                verdadeiro
                                            </div>
                                        </tr>
                                        <tr>
                                            <h4 align="center">Linguagem C</h4>
                                            <div class="alert" id="console-log">
                                                true
                                            </div>
                                        </tr>
                                    </table>
                                </div>
                                <div class="span2">
                                    <h4 align="center">Falso</h4>
                                    <div class="well well-small" id="console-log-dec-false">
                                        <textarea wrap="off" rows="3" id="ta-dec-false"></textarea>
                                    </div>
                                </div>
                                <div class="span2">
                                    <table>
                                        <tr>
                                            <h4 align="center">Linguagem R-Educ</h4>
                                            <div class="alert" id="console-log">
                                                falso
                                            </div>
                                        </tr>
                                        <tr>
                                            <h4 align="center">Linguagem C</h4>
                                            <div class="alert" id="console-log">
                                                false
                                            </div>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                            
                            <br><br><br><br><br><br><br><br><br>

                        </section>
                        
                        <hr class="bs-docs-separator">
                        <!--operadores-->
                        <section id="operators">
                            <br>
                            <br>
                            <div class="page-header">
                                <h1>Operadores</h1>
                            </div>

                            <p>Nesta seção você deve definir os operadores lógicos e relacionais da linguagem
                                NXC. Os exemplos abaixo mostram como são definidos
                                estes operadores nas linguagens de programação R-Educ e C.</p>

                                <p>Caso deseje selecionar operadores já criados,
                                    <a href="javascript:chooseOperators();">clique aqui</a>.
                                </p>

                                <div class="span9">
                                    <div class="span4">
                                        <h4 align="center">Operadores</h4>
                                        <div class="well well-small" id="console-log-operators">
                                            <table>
                                                <tr>
                                                    <td>
                                                        <div class="input-prepend">
                                                            <div class="span1" id="console-log" style="max-width:30px;">
                                                                <span class="add-on">e</span><input style="max-width:30px;" id="ip-op-and" type="text" value="">
                                                            </div>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <div class="input-prepend">
                                                            <div class="span1" id="console-log" style="max-width:30px;">
                                                                <span class="add-on">ou</span><input style="max-width:30px;" id="ip-op-or" type="text" value="">
                                                            </div>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <div class="input-prepend">
                                                            <div class="span1" id="console-log" style="max-width:30px;">
                                                                <span class="add-on">!</span><input style="max-width:30px;" id="ip-op-not" type="text" value="">
                                                            </div>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <div class="input-prepend">
                                                            <div class="span1" id="console-log" style="max-width:30px;">
                                                                <span class="add-on">=</span><input style="max-width:30px;" id="ip-op-e" type="text" value="">
                                                            </div>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <div class="input-prepend">
                                                            <div class="span1" id="console-log" style="max-width:30px;">
                                                                <span class="add-on">!=</span><input style="max-width:30px;" id="ip-op-ne" type="text" value="">
                                                            </div>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <div class="input-prepend">
                                                            <div class="span1" id="console-log" style="max-width:30px;">
                                                                <span class="add-on">></span><input style="max-width:30px;" id="ip-op-g" type="text" value="">
                                                            </div>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <div class="input-prepend">
                                                            <div class="span1" id="console-log" style="max-width:30px;">
                                                                <span class="add-on">>=</span><input style="max-width:30px;" id="ip-op-ge" type="text" value="">
                                                            </div>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <div class="input-prepend">
                                                            <div class="span1" id="console-log" style="max-width:30px;">
                                                                <span class="add-on"><</span><input style="max-width:30px;" id="ip-op-l" type="text" value="">
                                                            </div>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <div class="input-prepend">
                                                            <div class="span1" id="console-log" style="max-width:30px;">
                                                                <span class="add-on"><=</span><input style="max-width:30px;" id="ip-op-le" type="text" value="">
                                                            </div>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                    </div>
                                    <div class="span4">
                                        <h4 align="center">Linguagem R-Educ e Linguagem C</h4>
                                        <div class="alert" id="console-log">
                                            <table>
                                                <tr>
                                                    <td>
                                                        <div class="input-prepend">
                                                            <div class="span1" id="console-log" style="max-width:20px;">
                                                                <span class="add-on">e</span><input style="max-width:30px;" type="text" value="&&" readonly>
                                                            </div>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <div class="input-prepend">
                                                            <div class="span1" id="console-log" style="max-width:20px;">
                                                                <span class="add-on">ou</span><input style="max-width:30px;" type="text" value="||" readonly>
                                                            </div>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <div class="input-prepend">
                                                            <div class="span1" id="console-log" style="max-width:20px;">
                                                                <span class="add-on">!</span><input style="max-width:30px;" type="text" value="!" readonly>
                                                            </div>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <div class="input-prepend">
                                                            <div class="span1" id="console-log" style="max-width:20px;">
                                                                <span class="add-on">=</span><input style="max-width:30px;" type="text" value="==" readonly>
                                                            </div>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <div class="input-prepend">
                                                            <div class="span1" id="console-log" style="max-width:20px;">
                                                                <span class="add-on">!=</span><input style="max-width:30px;" type="text" value="!=" readonly>
                                                            </div>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <div class="input-prepend">
                                                            <div class="span1" id="console-log" style="max-width:20px;">
                                                                <span class="add-on">></span><input style="max-width:30px;" type="text" value=">" readonly>
                                                            </div>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <div class="input-prepend">
                                                            <div class="span1" id="console-log" style="max-width:20px;">
                                                                <span class="add-on">>=</span><input style="max-width:30px;" type="text" value=">=" readonly>
                                                            </div>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <div class="input-prepend">
                                                            <div class="span1" id="console-log" style="max-width:20px;">
                                                                <span class="add-on"><</span><input style="max-width:30px;" type="text" value="<" readonly>
                                                            </div>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <div class="input-prepend">
                                                            <div class="span1" id="console-log" style="max-width:20px;">
                                                                <span class="add-on"><=</span><input style="max-width:30px;" type="text" value="<=" readonly>
                                                            </div>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                                <br><br><br><br><br><br><br><br><br><br>

                            </section>

                            <hr class="bs-docs-separator">
                            <!--controle de fluxo-->
                            <section id="flowcontrol">
                                <br>
                                <br>
                                <div class="page-header">
                                    <h1>Controle de Fluxo</h1>
                                </div>

                                <p>Nesta sestao você deve definir as estruturas de controle de fluxo da linguagem
                                    NXC. A linguagem R-Educ possui as seguintes funções de controle de fluxo:
                                    <b>se</b>, <b>enquanto</b>, <b>repita</b>, <b>teste</b>,
                                    <b>farei</b>, <b>para</b> e <b>sair</b>.</p>

                                    <p>Siga os exemplos abaixo para declarar as estruturas. Observe que cada definição possui 
                                        um conjunto de palavras reservadas que devem ser utilizadas em cada caso, sendo elas: 
                                        <b>condicao</b>, <b>comandos1</b>, <b>comandos2</b>, <b>comandos</b>, <b>var</b>,
                                        <b>variavel</b>, <b>//teste1</b>, <b>//teste2</b>, <b>//fim</b>, <b>valor1</b> e 
                                        <b>passo</b>.</p>

                                        <div class="span9">
                                            <div class="span3">
                                                <h4 align="center">Se</h4>
                                                <div class="well well-small" id="console-log-if">
                                                    <textarea wrap="off" rows="7" id="ta-if"></textarea>
                                                </div>
                                            </div>
                                            <div class="span3" style="max-width:230px;">
                                                <h4 align="center">Linguagem R-Educ</h4>
                                                <div class="alert" id="console-log">
                                                    se (condicao) entao {<br>
                                                    &nbsp;&nbsp;comandos1<br>
                                                }<br>
                                                senao {<br>
                                                &nbsp;&nbsp;comandos2<br>
                                            }
                                        </div>
                                    </div>
                                    <div class="span3" style="max-width:230px;">
                                        <h4 align="center">Linguagem C</h4>
                                        <div class="alert" id="console-log">
                                            if (condicao) {<br>
                                            &nbsp;&nbsp;comandos1<br>
                                        }<br>
                                        else {<br>
                                        &nbsp;&nbsp;comandos2<br>
                                    }
                                </div>
                            </div>
                        </div>

                        <div class="span9">
                            <div class="span3">
                                <h4 align="center">Enquanto</h4>
                                <div class="well well-small" id="console-log-while">
                                    <textarea wrap="off" rows="7" id="ta-while"></textarea>
                                </div>
                            </div>
                            <div class="span3" style="max-width:230px;">
                                <h4 align="center">Linguagem R-Educ</h4>
                                <div class="alert" id="console-log">
                                    enquanto (condicao) farei {<br>
                                    &nbsp;&nbsp;comandos<br>
                                }
                            </div>
                        </div>
                        <div class="span3" style="max-width:230px;">
                            <h4 align="center">Linguagem C</h4>
                            <div class="alert" id="console-log">
                                while (condicao) {<br>
                                &nbsp;&nbsp;comandos<br>
                            }
                        </div>
                    </div>
                </div>

                <div class="span9">
                    <div class="span3">
                        <h4 align="center">Repita</h4>
                        <div class="well well-small" id="console-log-repeat">
                            <textarea wrap="off" rows="7" id="ta-repeat"></textarea>
                        </div>
                    </div>
                    <div class="span3" style="max-width:230px;">
                        <h4 align="center">Linguagem R-Educ</h4>
                        <div class="alert" id="console-log">
                            repita var vezes {<br>
                            &nbsp;&nbsp;comandos<br>
                        }
                    </div>
                </div>
                <div class="span3" style="max-width:230px;">
                    <h4 align="center">Linguagem C</h4>
                    <div class="alert" id="console-log">
                        for (int k = 0; k < var; k++) {<br>
                        &nbsp;&nbsp;comandos<br>
                    }
                </div>
            </div>
        </div>

        <div class="span9">
            <div class="span3">
                <h4 align="center">Teste</h4>
                <div class="well well-small" id="console-log-switch">
                    <textarea wrap="off" rows="9" id="ta-switch"></textarea>
                </div>
            </div>
            <div class="span3" style="max-width:230px;">
                <h4 align="center">Linguagem R-Educ</h4>
                <div class="alert" id="console-log">
                    teste (variavel) {<br>
                    &nbsp;&nbsp;se valor1: comandos1<br>
                    &nbsp;&nbsp;senao: comandos2<br>
                    }
                </div>
            </div>
        </div>
        <div class="span3" style="max-width:230px;">
            <h4 align="center">Linguagem C</h4>
            <div class="alert" id="console-log">
                switch (variavel) {<br>
                &nbsp;&nbsp;//teste1<br>
                &nbsp;&nbsp;case (valor1): comandos1<br>
                &nbsp;&nbsp;&nbsp;&nbsp; break;<br>
                &nbsp;&nbsp;//teste2<br>
                &nbsp;&nbsp;default: comandos2<br>
                &nbsp;&nbsp;&nbsp;&nbsp; break;<br>
                &nbsp;&nbsp;//fim<br>
            }
            </div>
        </div>
    </div>
</div>

<div class="span9">
    <div class="span3">
        <h4 align="center">Para</h4>
        <div class="well well-small" id="console-log-for">
            <textarea wrap="off" rows="7" id="ta-for"></textarea>
        </div>
    </div>
    <div class="span3" style="max-width:230px;">
        <h4 align="center">Linguagem R-Educ</h4>
        <div class="alert" id="console-log">
            para variavel de valor1 ate valor2 passo X farei {<br>
            &nbsp;&nbsp;comandos<br>
        }
    </div>
</div>
<div class="span3" style="max-width:230px;">
    <h4 align="center">Linguagem C</h4>
    <div class="alert" id="console-log">
        for (int variavel = valor1; variavel < valor2; variavel+=passo) {<br>
        &nbsp;&nbsp;comandos<br>
    }
</div>
</div>
</div>

<div class="span9">
    <div class="span3">
        <h4 align="center">Farei</h4>
        <div class="well well-small" id="console-log-do">
            <textarea wrap="off" rows="7" id="ta-do"></textarea>
        </div>
    </div>
    <div class="span3" style="max-width:230px;">
        <h4 align="center">Linguagem R-Educ</h4>
        <div class="alert" id="console-log">
            farei {<br>
            &nbsp;&nbsp;comandos<br>
        } enquanto (condicao)
    </div>
</div>
<div class="span3" style="max-width:230px;">
    <h4 align="center">Linguagem C</h4>
    <div class="alert" id="console-log">
        do {<br>
        &nbsp;&nbsp;comandos<br>
    } while (condicao)
</div>
</div>
</div>

<div class="span9">
    <div class="span3">
        <h4 align="center">Sair</h4>
        <div class="well well-small" id="console-log-break">
            <textarea wrap="off" rows="2" id="ta-break"></textarea>
        </div>
    </div>
    <div class="span3" style="max-width:230px;">
        <h4 align="center">Linguagem R-Educ</h4>
        <div class="alert" id="console-log">
            sair
        </div>
    </div>
    <div class="span3" style="max-width:230px;">
        <h4 align="center">Linguagem C</h4>
        <div class="alert" id="console-log">
            break;
        </div>
    </div>
</div>

<br><br>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br><br><br><br>

</section>

<hr class="bs-docs-separator">
<!--funcoes-->
<section id="lfunctions">
    <br>
    <br>
    <div class="page-header">
        <h1>Funções</h1>
    </div>

    <p>Nesta seção você deve definir as chamadas de funções que fazem parte da linguagem
        NXC. Estas funções devem possuir retornos do tipo
        <b>float</b>, <b>String</b>, <b>Void</b> ou <b>boolean</b> que deve ser selecionado.</p>
        <p>O nome definido para a função será o mesmo a ser chamado ao realizar a programação na
            linguagem R-Educ. O tipo de ação da função deve ser selecionado e o número de parâmetros recebidos
            por ela deve ser fornecido. Caso a função não receba parâmetros o valor inserido deve ser 0.</p>
            <p>O código da função deve ser escrito com muito cuidado. Os parâmetros devem ser chamados de
                <b>var</b> seguido pelo número do parâmetro, acrescido do tipo do parâmetro entre parênteses.
                Os parâmetros recebidos podem ser do tipo: <b>int</b>, <b>String</b>, <b>boolean</b>, <b>float</b> ou <b>double</b>.
                Por exemplo: <b>var1(int)</b>, <b>var2(String)</b>, <b>var3(boolean)</b>, <b>var4(float)</b>, <b>var5(double)</b>.</p>
                <p>No campo descrição insira uma breve descrição de como é feita a chamada a da sua função e o que cada
                    parâmetro representa.</p>
                    <p>Os exemplos abaixo mostram funções nas linguagens R-Educ, NXC e Lejos
                        que realizam a movimentação de robôs.</p>

                        <ul class="thumbnails">
                            <li class="span2 alert">
                              <strong>Linguagem R-Educ<br></strong>
                              Frente (var1(int), var2(int))
                          </li>
                          <li class="span3 alert">
                              <strong>Linguagem NXC<br></strong> 
                              RotateMotor(OUT_A, var1(int), var2(int));
                          </li>
                          <li class="span2 alert">
                              <strong>Linguagem Lejos<br></strong>
                              Motor.A.setSpeed(var1(int));<br>
                              Motor.A.rotate(var2(int));
                          </li>
                      </ul>

                      <div class="row-fluid">
                        <div class="span4">
                            <select id="functionlist" onchange="functionClick();">

                            </select>
                        </div>
                        <div class="span8 form-search">
                            <div class="input-append">
                                <input type="text" id="searchbox"  class="span8 search-query">
                                <button type="button" class="btn" onclick="highlightOption2('#FFFF00');">Buscar</button>
                            </div>
                        </div>
                    </div>

                    <div class="well menu">
                        <table style="vertical-align: top;">
                            <tr>
                                <td><h4>Nome</h4></td>
                                <td><h4>Tipo</h4></td>
                                <td rowspan="8" style="vertical-align: top;">
                                    <h4>Código</h4>
                                    <textarea rows="6" cols="40" id="ta-fc-code"></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td><input id="ip-fc-name" type="text" value=""></td>
                                <td>
                                    <select id="ip-fc-type">
                                        <option>Escrita</option>
                                        <option>Leitura</option>
                                        <option>Movimentação</option>
                                        <option>Outros</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td><h4>Retorno</h4></td>
                                <td><h4>Nº de parâmetros</h4></td>
                            </tr>
                            <tr>
                                <td>
                                    <select id="ip-fc-return">
                                        <option>boolean</option>
                                        <option>float</option>
                                        <option>String</option>
                                        <option>Void</option>
                                    </select>
                                </td>
                                <td><input id="ip-fc-np" type="text" value=""></td>
                            </tr>
                            <!-- TYPE ALIASES, VICTOR TORRES 26.11.2013 -->
                            <tr style="vertical-align: top;">
                                <td><h4>Apelidos de tipo</h4></td>
                                <td><h4>Imagem</h4></td>
                            </tr>
                            <tr style="vertical-align: top;">
                                <td><input id="ip-fc-ta" type="text" value=""></td>
                                <td>
                                    <img id="function-image" src="functionImages/140x140.png" class="img-polaroid" width="140px" height="140px"><br>
                                    <a href="javascript:uploadDialog();" id="btn-trocar" style="display: none;">Trocar</a>
                                    <input id="ip-fc-iurl" type="hidden" value="">
                                </td>
                            </tr>
                            <!-- END -->
                        </table>
                        <label id='lbl-functionC'></label>
                        <br>
                        <button title="Adicionar" id="btn-fc-ok"class="btn btn-success pull-right" value="AddFc">
                          <i class="icon-ok  icon-white"></i>
                      </button>

                      <button title="Novo" id="btn-fc-new"class="btn btn-info pull-right" value="NewFc">
                          <i class="icon-file  icon-white"></i>
                      </button>

                      <button title="Deletar" id="btn-fc-delete"class="btn btn-danger pull-right" value="DeleteFc">
                          <i class="icon-trash  icon-white"></i>
                      </button>
                      <br>
                  </div>


              </section>

          </div>
      </div>

      <button id="btn-ok"class="btn btn-success pull-right" value="OK">Validar
        <i class="icon-ok  icon-white"></i>
    </button>
    </body>
</html>