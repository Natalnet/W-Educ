<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="admin"/>
        <title>Edição do Cadastro de Linguagem - W-Educ</title>
        <style type="text/css">
            .operadores > div > span {
                width: 5em;
            }
        </style>
    
        <link rel="stylesheet" type="text/css" href='${assetPath(src: "prismjs/prism.css")}'>
        <script src='${assetPath(src: "prismjs/prism.js")}'></script>
        <script src='https://cloud.tinymce.com/stable/tinymce.min.js?apiKey=wnz75ojnvnqzlbjqt35g5uzzuvamglqdz3500580yoi7o7c2'></script>
        <script>
            tinymce.init({
                selector: '#descricao',
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
        <form action="<g:createLink action="atualizar" id="${funcao?.id}"/>" method="post">
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Edição do Cadastro de Funções</h1>
                    <h2>Linguagem ${funcao?.linguagem?.name}</h2>
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
                        <input type="text" name="nome" id="nome" value="${funcao?.name}" class="form-control"/>
                    </div>
                    <h2>Descrição</h2>
                    <div class="well">
                        <textarea class="form-control" rows="3" name="descricao" id="descricao">${funcao?.description}</textarea>
                    </div>
                    <h2>Tipo</h2>
                    <div class="well">
                        <g:select name="tipo" id="tipo" class="form-control" value="${funcao?.type}" from="${["Escrita", "Leitura", "Movimentação", "Outros"]}"/>
                    </div>
                    <h2>Retorno</h2>
                    <div class="well">
                        <g:select name="retorno" id="retorno" class="form-control" value="${funcao?.returnType}" from="${["boolean", "float", "String", "Void"]}"/>
                    </div>
                    <h2>Quantidade de parâmetros</h2>
                    <div class="well">
                        <g:select name="qntParametros" id="qntParametros" class="form-control" value="${funcao?.qntParameters}" from="${0..5}"/>
                    </div>
                    <h2>Código</h2>
                    <div class="well">
                        <textarea class="form-control" rows="3" name="codigo" id="codigo">${funcao?.code}</textarea>
                    </div>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <input type="submit" class="btn btn-default" value="Atualizar função" />
                </div>
                <p>
                    &nbsp;
                </p>
            </div>
        </form>
    </body>
</html>
