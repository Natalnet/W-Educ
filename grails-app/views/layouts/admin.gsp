<!DOCTYPE html>
<html lang="pt-br">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Ambiente de programação web para robótica educacional">
        <meta name="author" content="Victor Torres, Sarah Thomaz">

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

        <!-- Chosen jQuery Plugin -->
        <asset:stylesheet src="css/chosen.min.css"/>

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

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
        </g:if>

        <!-- Custom Theme JavaScript -->
        <asset:javascript src="js/sb-admin-2.js"/>

        <!-- Chosen jQuery UI -->
        <asset:javascript src="js/chosen.jquery.min.js"/>
        <asset:javascript src="js/chosen.proto.min.js"/>

        <!-- Bootbox.js -->
        <asset:javascript src="js/bootbox.min.js"/>

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
                   <a class="navbar-brand" href=""><b>W-Educ</b> - Ambiente de Desenvolviento Web para Robótica Educacional</a>
                </div>
                <!-- /.navbar-header -->

                <ul class="nav navbar-top-links navbar-right">
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="fa fa-envelope fa-fw"></i>  <i class="fa fa-caret-down"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-messages"><g:each in="${ultimasMensagens}" var="mensagem">
                            <li>
                                <a href="mensagem/todas">
                                    <div>
                                        <strong>${mensagem?.autor?.username}</strong>
                                        <span class="pull-right text-muted">
                                            <em><g:formatDate format="dd/MM/yyyy" date="${mensagem?.data}"/></em>
                                        </span>
                                    </div>
                                    <div>${mensagem?.mensagem}</div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            </g:each>
                            <li>
                                <g:link class="text-center" controller="mensagem" action="todas">
                                    <i class="fa fa-inbox"></i>
                                    <strong>&nbsp;&nbsp;Caixa de Entrada</strong>
                                </g:link>
                            </li>
                            <li>
                                <g:link class="text-center" controller="mensagem" action="escrever">
                                    <i class="fa fa-paper-plane-o"></i>
                                    <strong>&nbsp;&nbsp;Enviar Mensagem</strong>
                                </g:link>
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
                            <li><g:link controller="perfil" action="index"><i class="fa fa-user fa-fw"></i> Perfil</g:link>
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
                                <g:link controller="admin" action="index" class="active"><i class="fa fa-dashboard fa-fw"></i> Principal</g:link>
                            </li>
                            <sec:ifAnyGranted roles="ROLE_ADMIN">
                                <a href="#"><i class="fa fa-legal fa-fw"></i> Administrador<span class="fa arrow"></span></a>
                                    <li>
                                        <g:link controller="erro" action="sistema">Estatísticas do Sistema</g:link>
                                    </li>
                                    <li>
                                        <g:link controller="gerenciar" action="professores">Gerenciar Professores</g:link>
                                    </li>
                                    <li>
                                        <g:link controller="gerenciar" action="usuarios">Gerenciar Alunos</g:link>
                                    </li>
                                <!-- /.nav-second-level -->
                            </sec:ifAnyGranted>
                            <sec:ifAnyGranted roles="ROLE_ADMIN">
                            <a href="#"><i class="fa fa-male fa-fw"></i> Professor<span class="fa arrow"></span></a>
                            </sec:ifAnyGranted>
                            <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_PROFESSOR">
                                    <li>
                                        <g:link controller="erro" action="exibirProf">Estatítiscas</g:link>
                                    </li>
                                    <li>
                                        <g:link controller="linguagem" action="nova">Cadastrar Linguagem</g:link>
                                    </li>
                                    <li>
                                        <g:link controller="linguagem" action="listar">Gerenciar Linguagens</g:link>
                                    </li>
                                    <li>
                                        <g:link controller="linguagem" action="procurar">Programar</g:link>
                                    </li>
                                    <li>
                                        <g:link controller="professor" action="gerenciarAlunos">Gerenciar Alunos</g:link>
                                    </li>
                                    <li>
                                        <g:link controller="forum" action="home">Fórum</g:link>
                                    </li>
                                <!-- /.nav-second-level -->
                            
                            </sec:ifAnyGranted>
                            <sec:ifAnyGranted roles="ROLE_ADMIN">
                            <a href="#"><i class="fa fa-male fa-fw"></i> Aluno<span class="fa arrow"></span></a>
                            </sec:ifAnyGranted>
                            <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_ALUNO">
                            
                                
                                    <li>
                                        <g:link controller="erro" action="exibirAluno">Desempenho</g:link>
                                    </li>
                                    <li>
                                        <g:link controller="professor" action="procurar">Professores</g:link>
                                    </li>
                                    <li>
                                        <g:link controller="linguagem" action="procurar">Programar</g:link>
                                    </li>
                                    <li>
                                        <g:link controller="forum" action="home">Fórum</g:link>
                                    </li>
                                
                                <!-- /.nav-second-level -->
                            
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

    </body>

</html>
