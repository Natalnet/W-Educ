<!DOCTYPE html>
<html>
    <head>
        <asset:stylesheet src="css/plugins/morris.css"/>
        <g:if test='${"erro".equals(controllerName) && "exibirProf".equals(actionName)}'>
            <!-- Morris Charts JavaScript -->
            <asset:javascript src="js/plugins/morris/raphael.min.js"/>
            <asset:javascript src="js/plugins/morris/morris.min.js"/>
        </g:if>
        <meta name="layout" content="admin"/>
        <title>Estatísticas</title>
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
        <div class="col-lg-14">
            <h1 class="page-header">Minhas Estatísticas</h1>
            <h2>${professor?.name}</h2>
                  <div class=" col-md-5 col-lg-9 "> 
                  <table class="table table-user-information">
                    <tbody>
                      <tr>
                        <td><b>Nome de Usuário:</b></td>
                        <td>${professor?.username}</td>
                      </tr>
                      <tr>
                      <tr>
                        <td><b>Instituição:</b></td>
                        <td>${professor?.institution}</td>
                      </tr>
                      <tr>
                        <td><b>Data de Nascimento:</b></td>
                        <td>${professor?.dateofbirth}</td>
                      </tr>
                      <tr>
                        <td><b>Sexo:</b></td>
                        <td>${professor?.gender}</td>
                      </tr>
                   
                         <tr>
                      </tr>
                        <tr>
                        <td><b>Endereço:</b></td>
                        <td>${professor?.address}</td>
                      </tr>
                      <tr>
                        <td><b>Email:</b></td>
                        <td>${professor?.email}</td>
                      </tr>
                      <tr>
                        <td><b>Telefone:</b></td>
                        <td>${professor?.telefone}
                        </td>    
                      </tr>
                      <tr>
                        <td><b>Quantidade de Alunos:</b></td>
                        <td>${numeroAlunos}
                        </td>
                           
                      </tr>
                     
                    </tbody>
                  </table>
                </div>
            <div class="panel panel-default">
            <div class="row">
                <div class="col-lg-4">
                    <div class="panel panel-default">
                    <div class="panel-heading">
                        <i class="fa fa-bell fa-fw"></i> <b>Estatísticas Gerais</b>
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="list-group">
                            <a href="#" class="list-group-item">
                                <i class="fa fa-comment fa-fw"></i> Publicações no fórum
                                <span class="pull-right text-muted small"><em>X</em>
                                </span>
                            </a>
                            <a href="#" class="list-group-item">
                                <i class="fa fa-book fa-fw"></i> Programas cadastrados
                                <span class="pull-right text-muted small"><em>${programasCadastrados}</em>
                                </span>
                            </a>
                            <a href="#" class="list-group-item">
                                <i class="fa fa-tasks fa-fw"></i> Total de compilações
                                <span class="pull-right text-muted small"><em>${totalDeCompilacoes}</em>
                                </span>
                            </a>
                            <a href="#" class="list-group-item">
                                <i class="fa fa-trophy fa-fw"></i> Compilações bem-sucedidas
                                <span class="pull-right text-muted small"><em>${compilacoesBemSucedidas}</em>
                                </span>
                            </a>
                            <a href="#" class="list-group-item">
                                <i class="fa fa-tag fa-fw"></i> Compilações mal-sucedidas
                                <span class="pull-right text-muted small"><em>${compilacoesMalSucedidas}</em>
                                </span>
                            </a>
                            <a href="#" class="list-group-item">
                                <i class="fa fa-bolt fa-fw"></i> Linguagens utilizadas
                                <span class="pull-right text-muted small"><em>${linguagensUtilizadas}</em>
                                </span>
                            </a>
                        </div>
                        <!-- /.list-group -->
                    </div>
                </div> 
                </div>    
                <div class="col-lg-7">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <i class="fa fa-bar-chart-o fa-fw"></i> <b>Estatísticas de Erros</b>
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div id="morris-line-chart"></div>
                    </div>
                    <!-- /.panel-body -->
                </div>
                </div>
                <!-- /.panel -->
            </div>
    </body>
</html>