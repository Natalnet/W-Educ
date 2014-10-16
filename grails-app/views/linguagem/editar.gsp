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
                    <h1 class="page-header">Cadastro de Linguagem</h1>
                    <p>
                        Para cadastrar uma linguagem você deverá preencher todos os campos seguindo os exemplos aprensentados. Observe que existem algumas palavras específicas que são utilizadas para identificar e tratar os dados fornecidos, como por exemplo: <strong>comandos</strong>, <strong>comandos1</strong>, <strong>comandos2</strong>, <strong>principal</strong>, <strong>nomedoprograma</strong>, <strong>funcao</strong>, <strong>variavel</strong>, <strong>valor</strong>. 
                    </p>
                    <p>
                        Os únicos dados que não são de preenchimento obrigatório são: <strong>descrição</strong>, <strong>cabeçalho</strong>, <strong>rodapé</strong>  e <strong>funções</strong>. Caso você professor deseje facilitar a manipulação da linguagem para os seus alunos é possível utilizar o cabeçalho para definir as funções que serão apenas manipuladas ao serem cadastradas no item <strong>funções</strong>. A palavra chave <strong>nomedoprograma</strong> ao ser encontrada nos itens do cadastro será substituída pelo nome do programa salvo pelo usuário.
                    </p> 
                    <p>
                        Ao término do preenchimento, você deve validar a linguagem clicando na imagem de validação. Caso você tenha deixado de utilizar as palavras reservadas em um dos itens o sistema o informará marcando de vermelho o item.
                    </p>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <h2>Nome da Linguagem</h2>
                    <div class="well">
                        <input type="text" class="form-control" id="nome" name="nome" required value="${linguagem?.name}" />
                    </div>
                    <h2>Nome do Robô</h2>
                    <div class="well">
                        <input type="text" class="form-control" id="robo" name="robo" required value="${linguagem?.robot}" />
                    </div>
                    <h2>Descrição da Linguagem</h2>
                    <div class="well">
                        <textarea class="form-control" rows="3" id="descricao" name="descricao" required >${linguagem?.description}</textarea>
                    </div>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <h2>Compilação e Envio</h2>
                    <h3>Compilação</h3>
                    <p>
                        Nesta seção você deve inserir a chamada de compilação em Windows, a extensão do arquivo gerado na sua linguagem e o compilador a ser utilizado. Os dados fornecidos serão utilizados para geração do código
                        intermediário na linguagem NXC e geração do código objeto para envio ao robô. 
                    </p>
                    <p>
                        O código de compilação deve ser inserido referenciando a localização de todos os arquivos necessários(códigos fonte e compilador) com a palavra chave <strong>diretorio</strong> para indicar o endereço dos arquivos. Já o nome do programa deve ser indicado com a palavra chave <strong>nomedoprograma</strong>. Por exemplo, caso o seu código de compilação seja <strong>nbc nomedoprograma.nxc -O=nomedoprograma.rxe </strong> ele deverá ser escrito da seguinte forma: <strong>diretorio/nbc diretorio/nomedoprograma.nxc -O=diretorio/nomedoprograma.rxe </strong>
                    </p>
                    <p>
                        Caso seja necessário descompactar os arquivos do compilador ou realizar algum tipo de instalação. Marque o item <strong>Instalação necessária</strong>.
                    </p>
                    <p>
                        <h4>Código de compilação:</h4>
                        <div class="well">
                            <input type="text" class="form-control" id="codigoDeCompilacao" name="codigoDeCompilacao" required value="${linguagem?.compileCode}" />
                        </div>
                    </p>
                    <p>
                        <h4>Extensão dos arquivos na linguaem:</h4>
                        <div class="well">
                            <input type="text" class="form-control" id="extensao" name="extensao" required value="${linguagem?.extension}" />
                        </div>
                    </p>
                    <p>
                        <h4>Upload do compilador:</h4>
                        <div class="well">
                            <button class="btn btn-default" id="fazer-upload">Faça o upload do compilador</button><br />
                            <label>
                                <input type="checkbox" id="requer-instalacao"> Requer instalação</input> 
                            </label>
                        </div>
                    </p>
                    <h3>Envio</h3>
                    <p>
                        Nesta seção você deve inserir a chamada de envio do programa em sua plataforma e a extensão do código a ser enviado ao computador local para posteriormente ser enviado ao robô através da chamada inserida.
                    </p>
                    <p>
                        O código de envio deve ser inserido referenciando a localização de todos os arquivos necessários(códigos fonte e programa de envio) com a palavra chave <strong>diretorio</strong> para indicar o endereço dos arquivos. Já o nome do programa deve ser indicado com a palavra chave <strong>nomedoprograma</strong>. Por exemplo, caso o seu código de envio seja <strong>nbc -d nomedoprograma.nxc </strong> ele deverá ser escrito da seguinte forma: <strong>diretorio/nbc -d diretorio/nomedoprograma.nxc</strong>. Esse endereço de diretório será tratado e substituído pelo endereço de sua pasta local de arquivos temporários.
                    </p>
                    <p>
                        <h4>Código de envio:</h4>
                        <div class="well">
                            <input type="text" class="form-control" id="codigoDeEnvio" name="codigoDeEnvio" required value="${linguagem?.sendCode}" />
                        </div>
                    </p>
                    <p>
                        <h4>Extensão do arquivo enviado:</h4>
                        <div class="well">
                            <input type="text" class="form-control" id="extensaoDoArquivo" name="extensaoDoArquivo" required value="${linguagem?.sentExtension}" />
                        </div>
                    </p>
                    <p>
                        <h4>Upload do arquivo a ser enviado ao computador local para permitir o envio: </h4>
                        <div class="well">
                            <button class="btn btn-default" id="fazer-upload-envio">Faça o upload do programa de envio</button><br />
                            <label>
                                <input type="checkbox" id="envio-necessario"> Envio necessário</input> 
                            </label>
                        </div>
                    </p>
                    <p>
                        <h4>Nome do arquivo:</h4>
                        <div class="well">
                            <input type="text" class="form-control" id="arquivo" value="${linguagem?.compilerFile}" />
                        </div>
                    </p>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <h2>Cabeçalho e Rodapé</h2>
                    <p>
                        Nesta seção você deve inserir o cabeçalho e rodapé necessários para gerar o seu código. Lembre-se de incluir todas as bibliotecas e fazer todas as definições necessárias para o funcionamento do seu programa. O cabeçalho e o rodapé inseridos serão 
                        adicionados a todos os códigos gerados para esta
                        linguagem.
                    </p>
                    <p>
                        Caso você deseje facilitar a manipulação da linguagem para os seus alunos é possível utilizar o cabeçalho para definir as funções que serão manipuladas ao serem cadastradas no item <strong>funções</strong>. Novas estruturas também podem ser definidas nesta seção.
                    </p>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-6">
                    <h3>Cabeçalho</h3>
                    <div class="well">
                        <textarea class="form-control" rows="3" id="cabecalho" name="cabecalho" required>${linguagem?.header}</textarea>
                    </div>
                </div>
                <div class="col-lg-6">
                    <h3>Rodapé</h3>
                    <div class="well">
                        <textarea class="form-control" rows="3" id="rodape" name="rodape" required>${linguagem?.footnote}</textarea>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <h2>Declaração de Funções</h2>
                    <h3>Função Principal</h3>
                    <p>
                        Nesta seção você deve declarar a função principal do seu programa.
                    </p>
                    <p>
                        Os exemplos abaixo mostram como é definida a função principal nas linguagens de programação R-Educ e C. Observe que a palavra reservada <strong>comandos</strong> deve ser utilizada.
                    </p>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8">
                    <h4>Função Principal na Linguagem Alvo</h4>
                    <div class="well">
                        <textarea class="form-control" rows="11" id="funcaoPrincipal" name="funcaoPrincipal" required>${linguagem?.mainFunction}</textarea>
                    </div>
                </div>
                <div class="col-lg-4">
                    <h4>Linguagem R-Educ</h4>
                    <div class="well">
                        tarefa principal {<br/>
                        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; comandos<br/>
                        }
                    </div>
                    <h4>Linguagem C</h4>
                    <div class="well">
                        int main() {<br/>
                        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; comandos<br/>
                        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; return 0;<br/>
                        }
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <h3>Funções</h3>
                    <p>
                        Nesta seção você deve identificar como é feita a declaração de funções sem retorno do seu programa.
                    </p>
                    <p>
                        Os exemplos abaixo mostram como são definidas as funções sem retorno nas linguagens de programação R-Educ e C. Observe que as palavras reservadas <strong>comandos</strong> e <strong>funcao</strong> devem ser utilizadas.
                    </p>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8">
                    <h4>Função na Linguagem Alvo</h4>
                    <div class="well">
                        <textarea class="form-control" rows="11" id="funcao">${linguagem?.otherFunctions}</textarea>
                    </div>
                </div>
                <div class="col-lg-4">
                    <h4>Linguagem R-Educ</h4>
                    <div class="well">
                        tarefa funcao {<br/>
                        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; comandos<br/>
                        }
                    </div>
                    <h4>Linguagem C</h4>
                    <div class="well">
                        void funcao() {<br/>
                        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; comandos<br/>
                        }
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <p>
                        Depois que a função foi declarada, ela pode ser chamada. Os exemplos abaixo mostram como são feitas chamadas de funções nas linguagens de programação R-Educ e C. Observe que a palavra reservada <strong>funcao</strong> deve ser utilizada.
                    </p>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8">
                    <h4>Chamada de Função na Linguagem Alvo</h4>
                    <div class="well">
                        <textarea class="form-control" rows="7" id="chamadaDeFuncao" name="chamadaDeFuncao" required>${linguagem?.callFunction}</textarea>
                    </div>
                </div>
                <div class="col-lg-4">
                    <h4>Linguagem R-Educ</h4>
                    <div class="well">
                        funcao
                    </div>
                    <h4>Linguagem C</h4>
                    <div class="well">
                        funcao();
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <h2>Tipos de Dados</h2>
                    <p>
                        Nesta seção você deve definir como são declaradas variáveis utilizando tipos específicos de dados na Linguagem Alvo.
                    </p>
                    <p>
                        A linguagem R-Educ possui três tipos de dados: <strong>texto</strong>, <strong>numero</strong> e <strong>booleano</strong>. Siga os exemplos abaixo para declarar as variáveis. Observe que as palavras reservadas <strong>variavel</strong> e <strong>valor</strong> devem ser utilizadas.
                    </p>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8">
                    <h4>Declaração do Tipo Texto</h4>
                    <div class="well">
                        <textarea class="form-control" rows="7" id="texto" name="texto" required>${linguagem?.types.declareString}</textarea>
                    </div>
                </div>
                <div class="col-lg-4">
                    <h4>Linguagem R-Educ</h4>
                    <div class="well">
                        texto variavel = "valor"
                    </div>
                    <h4>Linguagem C</h4>
                    <div class="well">
                        String variavel = "valor";
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8">
                    <h4>Declaração do Tipo Numero</h4>
                    <div class="well">
                        <textarea class="form-control" rows="7" id="numero" name="numero" required>${linguagem?.types.declareFloat}</textarea>
                    </div>
                    <p>
                        Variáveis do tipo <strong>numero</strong> recebem valores inteiros ou em ponto flutuante.
                    </p>
                </div>
                <div class="col-lg-4">
                    <h4>Linguagem R-Educ</h4>
                    <div class="well">
                        numero variavel = valor
                    </div>
                    <h4>Linguagem C</h4>
                    <div class="well">
                        float variavel = valor;
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8">
                    <h4>Declaração do Tipo Booleano</h4>
                    <div class="well">
                        <textarea class="form-control" rows="7" id="booleano" name="booleano" required>${linguagem?.types.declareBoolean}</textarea>
                    </div>
                    <p>
                        Variáveis do tipo <strong>booleano</strong> podem receber valores do tipo verdadeiro ou falso. Especifique como esses valores são chamados na linguagem cadastrada.
                    </p>
                </div>
                <div class="col-lg-4">
                    <h4>Linguagem R-Educ</h4>
                    <div class="well">
                        booleano variavel = valor
                    </div>
                    <h4>Linguagem C</h4>
                    <div class="well">
                        bool variavel = valor;
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-3">
                    <h4>Verdadeiro</h4>
                    <div class="well">
                        <textarea class="form-control" rows="7" id="verdadeiro" name="verdadeiro" required>${linguagem?.types.declareTrue}</textarea>
                    </div>
                </div>
                <div class="col-lg-3">
                    <h4>Linguagem R-Educ</h4>
                    <div class="well">
                        verdadeiro
                    </div>

                    <h4>Linguagem C</h4>
                    <div class="well">
                        true
                    </div>
                </div>
                <div class="col-lg-3">
                    <h4>Falso</h4>
                    <div class="well">
                        <textarea class="form-control" rows="7" id="falso"required>${linguagem?.types.declareFalse}</textarea>
                    </div>
                </div>
                <div class="col-lg-3">
                    <h4>Linguagem R-Educ</h4>
                    <div class="well">
                        falso
                    </div>

                    <h4>Linguagem C</h4>
                    <div class="well">
                        false
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <h2>Operadores</h2>
                    <p>
                        Nesta seção você deve definir os operadores lógicos e relacionais da Linguagem Alvo. Os exemplos abaixo mostram como são definidos estes operadores nas linguagens de programação R-Educ e C.
                    </p>
                </div>
            </div>
            
            <div class="row">
                <div class="col-lg-12">
                    <h2>Controle de Fluxo</h2>
                    <p>
                        Nesta sestao você deve definir as estruturas de controle de fluxo da Linguagem Alvo. A linguagem R-Educ possui as seguintes funções de controle de fluxo: <strong>se</strong>, <strong>enquanto</strong>, <strong>repita</strong>, <strong>teste</strong>, <strong>farei</strong>, <strong>para</strong> e <strong>sair</strong>.
                    </p>
                    <p>
                        Siga os exemplos abaixo para declarar as estruturas. Observe que cada definição possui um conjunto de palavras reservadas que devem ser utilizadas em cada caso, sendo elas: <strong>condicao</strong>, <strong>comandos1</strong>, <strong>comandos2</strong>, <strong>comandos</strong>, <strong>var</strong>, <strong>variavel</strong>, <strong>//teste1</strong>, <strong>//teste2</strong>, <strong>//fim</strong>, <strong>valor1</strong> e <strong>passo</strong>.
                    </p>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8">
                    <h4>Se</h4>
                    <div class="well">
                        <textarea class="form-control" rows="16" id="se" name="se" required>${linguagem?.controlFlow.ifCode}</textarea>
                    </div>
                </div>
                <div class="col-lg-4">
                    <h4>Linguagem R-Educ</h4>
                    <div class="well">
                        se (condicao) entao {<br/>
                        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; comandos1<br/>
                        }<br/>
                        senao {<br/>
                        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; comandos2<br/>
                        }
                    </div>
                    <h4>Linguagem C</h4>
                    <div class="well">
                        if (condicao) {<br/>
                        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; comandos1<br/>
                        } else {<br/>
                        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; comandos2<br/>
                        }
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8">
                    <h4>Enquanto</h4>
                    <div class="well">
                        <textarea class="form-control" rows="11" id="enquanto" name="enquanto" required>${linguagem?.controlFlow.whileCode}</textarea>
                    </div>
                </div>
                <div class="col-lg-4">
                    <h4>Linguagem R-Educ</h4>
                    <div class="well">
                        enquanto (condicao) farei {<br/>
                        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; comandos<br/>
                        }
                    </div>
                    <h4>Linguagem C</h4>
                    <div class="well">
                        while (condicao) {<br/>
                        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; comandos<br/>
                        }
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8">
                    <h4>Repita</h4>
                    <div class="well">
                        <textarea class="form-control" rows="11" id="repita" name="repita" required>${linguagem?.controlFlow.repeatCode}</textarea>
                    </div>
                </div>
                <div class="col-lg-4">
                    <h4>Linguagem R-Educ</h4>
                    <div class="well">
                        repita var vezes {<br/>
                        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; comandos<br/>
                        }
                    </div>
                    <h4>Linguagem C</h4>
                    <div class="well">
                        for (int k = 0; k &lt; var; k++) {<br/>
                        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; comandos<br/>
                        }
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8">
                    <h4>Teste</h4>
                    <div class="well">
                        <textarea class="form-control" rows="18" id="teste" name="teste" required>${linguagem?.controlFlow.switchCode}</textarea>
                    </div>
                </div>
                <div class="col-lg-4">
                    <h4>Linguagem R-Educ</h4>
                    <div class="well">
                        teste (variavel) {<br/>
                        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; se valor1: comandos1<br/>
                        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; senao: comandos2<br/>
                        }
                    </div>
                    <h4>Linguagem C</h4>
                    <div class="well">
                        switch (variavel) {<br/>
                        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; //teste1<br/>
                        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; case (valor1): comandos1<br/>
                        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; break;<br/>
                        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; //teste2<br/>
                        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; default: comandos2<br/>
                        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; break;<br/>
                        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; //fim<br/>
                        }
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8">
                    <h4>Para</h4>
                    <div class="well">
                        <textarea class="form-control" rows="17" id="para" name="para" required>${linguagem?.controlFlow.forCode}</textarea>
                    </div>
                </div>
                <div class="col-lg-4">
                    <h4>Linguagem R-Educ</h4>
                    <div class="well">
                        para variavel de valor1 ate valor2 passo X farei {<br/>
                        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; comandos<br/>
                        }
                    </div>
                    <h4>Linguagem C</h4>
                    <div class="well">
                        for (int variavel = valor1; variavel &lt;= valor2; variavel+= x) {<br/>
                        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; comandos<br/>
                        }
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8">
                    <h4>Farei</h4>
                    <div class="well">
                        <textarea class="form-control" rows="11" id="farei" name="farei" required>${linguagem?.controlFlow.doCode}</textarea>
                    </div>
                </div>
                <div class="col-lg-4">
                    <h4>Linguagem R-Educ</h4>
                    <div class="well">
                        farei {<br/>
                        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; comandos<br/>
                        } enquanto (condicao)
                    </div>
                    <h4>Linguagem C</h4>
                    <div class="well">
                        do {<br/>
                        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; comandos<br/>
                        } while (condicao);
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8">
                    <h4>Sair</h4>
                    <div class="well">
                        <textarea class="form-control" rows="7" id="sair" name="sair" required>${linguagem?.controlFlow.breakCode}</textarea>
                    </div>
                </div>
                <div class="col-lg-4">
                    <h4>Linguagem R-Educ</h4>
                    <div class="well">
                        sair
                    </div>
                    <h4>Linguagem C</h4>
                    <div class="well">
                        break;
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <input type="submit" class="btn btn-default" value="Atualizar linguagem" />
                </div>
                <p>
                    &nbsp;
                </p>
            </div>
        </form>
    </body>
</html>
