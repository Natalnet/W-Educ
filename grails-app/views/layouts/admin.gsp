<!DOCTYPE html>
<html lang="pt-br">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Ambiente de programação web para robótica educacional">
        <meta name="author" content="Victor Torres, Sarah Thomaz, Carla Fernandes">

        <title><g:layoutTitle default="W-Educ"/></title>

        <!-- Bootstrap Core CSS -->
        <asset:stylesheet src="css/bootstrap.min.css"/>

        <!-- MetisMenu CSS -->
        <asset:stylesheet src="css/plugins/metisMenu/metisMenu.min.css"/>

        <!-- Timeline CSS -->
        <asset:stylesheet src="css/plugins/timeline.css"/>

        <!-- Custom CSS -->
        <asset:stylesheet src="css/sb-admin-2.css"/>

        <!-- Morris Charts CSS -->
        <asset:stylesheet src="css/plugins/morris.css"/>

        <!-- Custom Fonts -->
        <asset:stylesheet src="font-awesome-4.1.0/css/font-awesome.min.css"/>

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

        <g:layoutHead/>

    </head>

    <body>

        <div id="wrapper">

            <!-- Navigation -->
            <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="index.html">W-Educ</a>
                </div>
                <!-- /.navbar-header -->

                <ul class="nav navbar-top-links navbar-right">
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="fa fa-envelope fa-fw"></i>  <i class="fa fa-caret-down"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-messages">
                            <li>
                                <a href="#">
                                    <div>
                                        <strong>John Smith</strong>
                                        <span class="pull-right text-muted">
                                            <em>Yesterday</em>
                                        </span>
                                    </div>
                                    <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="#">
                                    <div>
                                        <strong>John Smith</strong>
                                        <span class="pull-right text-muted">
                                            <em>Yesterday</em>
                                        </span>
                                    </div>
                                    <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="#">
                                    <div>
                                        <strong>John Smith</strong>
                                        <span class="pull-right text-muted">
                                            <em>Yesterday</em>
                                        </span>
                                    </div>
                                    <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a class="text-center" href="#">
                                    <strong>Ler Todas as Mensagens</strong>
                                    <i class="fa fa-angle-right"></i>
                                </a>
                            </li>
                        </ul>
                        <!-- /.dropdown-messages -->
                    </li>
                    <!-- /.dropdown -->
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                            <li><a href="#"><i class="fa fa-user fa-fw"></i> Perfil</a>
                            </li>
                            <li><a href="#"><i class="fa fa-gear fa-fw"></i> Configurações</a>
                            </li>
                            <li class="divider"></li>
                            <li><g:link controller="logout" action="index"><i class="fa fa-sign-out fa-fw"></i> Logout</g:link>
                            </li>
                        </ul>
                        <!-- /.dropdown-user -->
                    </li>
                    <!-- /.dropdown -->
                </ul>
                <!-- /.navbar-top-links -->

                <div class="navbar-default sidebar" role="navigation">
                    <div class="sidebar-nav navbar-collapse">
                        <ul class="nav" id="side-menu">
                            <!-- <li class="sidebar-search">
                                <div class="input-group custom-search-form">
                                    <input type="text" class="form-control" placeholder="Search...">
                                    <span class="input-group-btn">
                                    <button class="btn btn-default" type="button">
                                        <i class="fa fa-search"></i>
                                    </button>
                                </span>
                                </div>
                                <!-- /input-group -->
                            <!-- </li> -->
                            <li>
                                <g:link controller="admin" action="index" class="active"><i class="fa fa-dashboard fa-fw"></i> Dashboard</g:link>
                            </li>
                            <sec:ifAnyGranted roles="ROLE_ADMIN">
                            <li>
                                <a href="#"><i class="fa fa-legal fa-fw"></i> Administrador<span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li>
                                        <a href="flot.html">Estatísticas do sistema</a>
                                    </li>
                                    <li>
                                        <a href="morris.html">Gerenciar professores</a>
                                    </li>
                                </ul>
                                <!-- /.nav-second-level -->
                            </li>
                            </sec:ifAnyGranted>
                            <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_PROFESSOR">
                            <li>
                                <a href="#"><i class="fa fa-male fa-fw"></i> Professor<span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li>
                                        <a href="flot.html">Estatísticas do professor</a>
                                    </li>
                                    <li>
                                        <g:link controller="linguagem" action="nova">Cadastrar linguagem</g:link>
                                    </li>
                                    <li>
                                        <a href="morris.html">Gerenciar linguagens</a>
                                    </li>
                                    <li>
                                        <a href="morris.html">Procurar linguagens</a>
                                    </li>
                                    <li>
                                        <a href="morris.html">Gerenciar alunos</a>
                                    </li>
                                    <li>
                                        <a href="morris.html">Fórum de discussão</a>
                                    </li>
                                </ul>
                                <!-- /.nav-second-level -->
                            </li>
                            </sec:ifAnyGranted>
                            <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_ALUNO">
                            <li>
                                <a href="#"><i class="fa fa-pencil fa-fw"></i> Aluno<span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li>
                                        <a href="flot.html">Procurar professores</a>
                                    </li>
                                    <li>
                                        <a href="flot.html">Procurar linguagens</a>
                                    </li>
                                    <li>
                                        <a href="morris.html">Fórum de discussão</a>
                                    </li>
                                </ul>
                                <!-- /.nav-second-level -->
                            </li>
                            </sec:ifAnyGranted>
                        </ul>
                    </div>
                    <!-- /.sidebar-collapse -->
                </div>
                <!-- /.navbar-static-side -->
            </nav>

            <div id="page-wrapper">
                <g:layoutBody/>
            </div>
            <!-- /#page-wrapper -->

        </div>
        <!-- /#wrapper -->

        <!-- jQuery Version 1.11.0 -->
        <asset:javascript src="js/jquery-1.11.0.js"/>

        <!-- Bootstrap Core JavaScript -->
        <asset:javascript src="js/bootstrap.min.js"/>

        <!-- Metis Menu Plugin JavaScript -->
        <asset:javascript src="js/plugins/metisMenu/metisMenu.min.js"/>

        <g:if test='${"admin".equals(controllerName) && "index".equals(actionName)}'>
            <!-- Morris Charts JavaScript -->
            <asset:javascript src="js/plugins/morris/raphael.min.js"/>
            <asset:javascript src="js/plugins/morris/morris.min.js"/>
            <asset:javascript src="js/plugins/morris/morris-data.js"/>
        </g:if>

        <!-- Custom Theme JavaScript -->
        <asset:javascript src="js/sb-admin-2.js"/>

    </body>

</html>
