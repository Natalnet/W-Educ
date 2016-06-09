<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="admin"/>
        <title>Home - W-Educ</title>
    </head>
    <body>
        <script type="text/javascript">
            $(function() {
                Morris.Area({
                    element: 'morris-area-chart',
                    data: [
                        <g:each in="${datas}" var="it" status="i">
                        {
                            month: "${it}",
                            loginsUnicos: ${loginsUnicos[i]},
                            loginsTotais: ${loginsTotais[i]}
                        }<g:if test="${(datas?.size() - 1) != i}">,</g:if>
                        </g:each>
                    ],
                    xkey: 'month',
                    ykeys: ['loginsUnicos', 'loginsTotais'],
                    labels: ['Logins únicos', 'Logins totais'],
                    pointSize: 2,
                    hideHover: 'auto',
                    resize: true
                });
            });
        </script>
        <!-- /.row -->
        <br>
                <div class="jumbotron">
                <div class="container">
                    <h2><b>Seja bem vindo ao W-Educ!</b></h2>
                    <p>Este é um ambiente completo para robótica educacional. Aqui você pode <a href="/weduc/linguagem/procurar">programar</a> diversos robôs, discutir sobre robótica no nosso <a href="/weduc/forum/home">fórum</a>, ser acompanhado por <a href="/weduc/professores/procurar">professores</a> de robótica de todo o mundo  e muito mais! </p>
                    <p><a class="btn btn-primary btn-lg" href="/weduc/linguagem/procurar" role="button">Começar a programar!</a></p>
                </div>
                </div>
         <div class="row">
            <div class="col-lg-3 col-md-6">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-xs-3">
                                <i class="fa fa-comments-o fa-5x"></i>
                            </div>
                            <div class="col-xs-9 text-right">
                                <div class="huge">Fórum</div>
                                <div>de discussão <br>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</div>
                            </div>
                        </div>
                    </div>
                    <a href="/weduc/forum/home">
                        <div class="panel-footer">
                            <span class="pull-left">Veja Mais</span>
                            <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                            <div class="clearfix"></div>
                        </div>
                    </a>
                </div>
            </div>
            <div class="col-lg-3 col-md-6">
                <div class="panel panel-green">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-xs-3">
                                <i class="fa fa-language fa-5x"></i>
                            </div>
                            <div class="col-xs-9 text-right">
                                <div class="huge">Robôs</div>
                                <div>e linguagens de programação</div>
                            </div>
                        </div>
                    </div>
                    <a href="/weduc/linguagem/procurar">

                        <div class="panel-footer">
                            <span class="pull-left">Veja Mais</span>
                            <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                            <div class="clearfix"></div>
                        </div>
                    </a>
                </div>
            </div>
            <div class="col-lg-3 col-md-6">
                <div class="panel panel-yellow">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-xs-3">
                                <i class="fa fa-sort-amount-asc fa-4x"></i>
                            </div>
                            <div class="col-xs-9 text-right">
                                <div class="huge">E-Aval</div>
                                <div>avaliação de softwares para robótica</div>
                            </div>
                        </div>
                    </div>
                    <a href="https://github.com/Natalnet/EducAval/wiki" target="_blank">
                        <div class="panel-footer">
                            <span class="pull-left">Veja Mais</span>
                            <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                            <div class="clearfix"></div>
                        </div>
                    </a>
                </div>
            </div>
            <div class="col-lg-3 col-md-6">
                <div class="panel panel-red">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-xs-3">
                                <i class="fa fa-book fa-5x"></i>
                            </div>
                            <div class="col-xs-9 text-right">
                                <div class="huge">Ajuda</div>
                                <div>do sistema e manuais &emsp;</div>
                            </div>
                        </div>
                    </div>
                    <a href="https://github.com/Natalnet/W-Educ/wiki" target="_blank">
                        <div class="panel-footer">
                            <span class="pull-left">Veja Mais</span>
                            <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                            <div class="clearfix"></div>
                        </div>
                    </a>
                </div>
            </div>
        </div>        

    </body>
</html>
