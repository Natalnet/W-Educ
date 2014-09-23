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
                <h2>Nome da Linguagem</h2>
                <div class="well">
                    <input type="text" class="form-control" id="nome-da-linguagem" />
                </div>
                <h2>Nome do Robô</h2>
                <div class="well">
                    <input type="text" class="form-control" id="nome-do-robo" />
                </div>
                <h2>Descrição da Linguagem</h2>
                <div class="well">
                    <textarea class="form-control" rows="3" id="descricao"></textarea>
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
                        <input type="text" class="form-control" id="codigo-de-compilacao" />
                    </div>
                </p>
                <p>
                    <h4>Extensão dos arquivos na linguaem:</h4>
                    <div class="well">
                        <input type="text" class="form-control" id="extensao-do-arquivo" />
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
                        <input type="text" class="form-control" id="codigo-de-envio" />
                    </div>
                </p>
                <p>
                    <h4>Extensão do arquivo enviado:</h4>
                    <div class="well">
                        <input type="text" class="form-control" id="extensao-do-arquivo-enviado" />
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
                        <input type="text" class="form-control" id="nome-do-arquivo" />
                    </div>
                </p>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <div class="row">
            <div class="col-lg-12">
                <h2>Cabeçalho e Rodapé</h2>
                <div class="well">
                    <input type="text" class="form-control" id="nome-da-linguagem" />
                </div>
                <h2>Nome do Robô</h2>
                <div class="well">
                    <input type="text" class="form-control" id="nome-do-robo" />
                </div>
                <h2>Descrição da Linguagem</h2>
                <div class="well">
                    <textarea class="form-control" rows="3" id="descricao"></textarea>
                </div>
            </div>
            <!-- /.col-lg-12 -->
        </div>
    </body>
</html>