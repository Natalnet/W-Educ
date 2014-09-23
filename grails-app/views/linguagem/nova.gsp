<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="admin"/>
        <title>Cadastro de Linguagem - W-Educ</title>
    </head>
    <body>
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
                <h2>Descrição da Linguagem</h2>
                <textarea class="form-control" rows="3" id="descricao"></textarea>
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
                    Código de compilação:
                    <div class="well">
                        <input type="text" class="form-control" id="codigo-de-compilacao" />
                    </div>
                </p>
                <p>
                    Extensão dos arquivos na linguaem:
                    <div class="well">
                        <input type="text" class="form-control" id="extensao-do-arquivo" />
                    </div>
                </p>
                <p>
                    Upload do compilador:
                    <div class="well">
                        <button class="btn btn-default" id="extensao-do-arquivo">Faça o upload do compilador</button><br />
                        <label>
                            <input type="checkbox" id="extensao-do-arquivo"> Requer instalação</input> 
                        </label>
                    </div>
                </p>
                <textarea class="form-control" rows="3" id="descricao"></textarea>
            </div>
            <!-- /.col-lg-12 -->
        </div>
    </body>
</html>