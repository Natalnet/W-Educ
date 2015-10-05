<!DOCTYPE html>
<html>
    <head>
        <asset:stylesheet src="css/plugins/morris.css"/>
        <g:if test='${"erro".equals(controllerName) && "exibir".equals(actionName)}'>
            <!-- Morris Charts JavaScript -->
            <asset:javascript src="js/plugins/morris/raphael.min.js"/>
            <asset:javascript src="js/plugins/morris/morris.min.js"/>
        </g:if>

        <meta name="layout" content="admin"/>
        <title>Visualizar erros do aluno - W-Educ</title>
    </head>
    <body>
        <script type="text/javascript">
            $(function() {
                Morris.Line({
                    element: 'morris-line-chart',
                    data: [
                        <g:each in="${datas}" var="it" status="i">
                        {
                            days: "${it}",
                            <g:if test="${(errosVariavel?.size()) != 0 && errosVariavel[i] != -1 && (errosVariavel?.size()) > i}">errosVariavel: ${errosVariavel[i]},</g:if>
                            <g:if test="${(errosFuncao?.size()) != 0 && errosFuncao[i] != -1 && (errosFuncao?.size()) > i}">errosFuncao: ${errosFuncao[i]},</g:if>
                            <g:if test="${(errosTarefa?.size()) != 0 && errosTarefa[i] != -1 && (errosTarefa?.size()) > i}">errosTarefa: ${errosTarefa[i]},</g:if>
                            <g:if test="${(errosEstrutura?.size()) != 0 && errosEstrutura[i] != -1 && (errosEstrutura?.size()) > i}">errosEstrutura: ${errosEstrutura[i]},</g:if>
                            <g:if test="${(errosCondicao?.size()) != 0 && errosCondicao[i] != -1 && (errosCondicao?.size()) > i}">errosCondicao: ${errosCondicao[i]},</g:if>
                            <g:if test="${(errosRepeticao?.size()) != 0 && errosRepeticao[i] != -1 && (errosRepeticao?.size()) > i}">errosRepeticao: ${errosRepeticao[i]},</g:if>
                            errosNome: ${errosNome[i]},
                            errosSintaxe: ${errosSintaxe[i]},
                        }<g:if test="${(datas?.size() - 1) != i}">,</g:if>
                        </g:each>
                    ],
                    xkey: 'days',
                    ykeys: ['errosVariavel', 'errosFuncao', 'errosTarefa', 'errosEstrutura', 'errosCondicao', 'errosRepeticao', 'errosNome', 'errosSintaxe'],
                    labels: ['Variável', 'Função', 'Tarefas', 'Condições', 'Estrutura de Condição', 'Estruturas de Repetição', 'Escolha de nomes', 'Sintaxe'],
                    hideHover: 'auto',
                    resize: true
                });
            });
        </script>
        <div class="col-lg-8">
            <h1 class="page-header">Gráfico de erros por aluno</h1>
            <h2>Aluno: ${aluno?.name}</h2>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <i class="fa fa-bar-chart-o fa-fw"></i> <b>Estatísticas de erros</b>
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div id="morris-line-chart"></div>
                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->
            </div>
    </body>
</html>