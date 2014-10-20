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
        <form action="<g:createLink action="salvar"/>" method="post">
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Cadastro de Funções</h1>
                    <h2>Linguagem ${linguagem?.name}</h2>
                    <p>
                        Nesta seção você deve definir as chamadas de funções que fazem parte da Linguagem Alvo. Estas funções devem possuir retornos do tipo <strong>float</strong>, <strong>String</strong>, <strong>void</strong> ou <strong>boolean</strong> que deve ser selecionado.
                    </p>
                    <p>
                        O nome definido para a função será o mesmo a ser chamado ao realizar a programação na linguagem R-Educ. O tipo de ação da função deve ser selecionado e o número de parâmetros recebidos por ela deve ser fornecido. Caso a função não receba parâmetros o valor inserido deve ser 0.
                    </p>
                    <p>
                        O código da função deve ser escrito com muito cuidado. Os parâmetros devem ser chamados de <strong>var</strong> seguido pelo número do parâmetro, acrescido do tipo do parâmetro entre parênteses. Os parâmetros recebidos podem ser do tipo: <strong>int</strong>, <strong>String</strong>, <strong>boolean</strong>, <strong>float</strong> ou <strong>double</strong>. Por exemplo: <strong>var1(int)</strong>, <strong>var2(String)</strong>, <strong>var3(boolean)</strong>, <strong>var4(float)</strong>, <strong>var5(double)</strong>.
                    </p>
                    <p>
                        No campo descrição insira uma breve descrição de como é feita a chamada a da sua função e o que cada parâmetro representa.
                    </p>
                    <p>
                        Os exemplos abaixo mostram funções nas linguagens R-Educ, NXC e Lejos que realizam a movimentação de robôs.
                    </p>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <h2>Nome</h2>
                    <div class="well">
                        <input type="text" name="haha" id="haha" class="form-control"/>
                    </div>
                    <h2>Tipo</h2>
                    <div class="well">
                        <g:select name="haha" id="haha" class="form-control" from="${["Escrita", "Leitura", "Movimentação", "Outros"]}"/>
                    </div>
                    <h2>Retorno</h2>
                    <div class="well">
                        <g:select name="haha" id="haha" class="form-control" from="${["boolean", "float", "String", "Void"]}"/>
                    </div>
                    <h2>Quantidade de parâmetros</h2>
                    <div class="well">
                        <g:select name="haha" id="haha" class="form-control" from="${0..5}"/>
                    </div>
                    <h2>Código</h2>
                    <div class="well">
                        <textarea class="form-control" rows="3" name="descricao" id="descricao"></textarea>
                    </div>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <input type="submit" class="btn btn-default" value="Salvar função" />
                </div>
                <p>
                    &nbsp;
                </p>
            </div>
        </form>
    </body>
</html>
