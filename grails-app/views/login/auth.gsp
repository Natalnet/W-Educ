<!DOCTYPE html>
<html>

    <head>
        <asset:javascript src="js/jquery.min.js"/>
    	<meta name="layout" content="login"/>

        <title><g:message code='spring.security.ui.login.title'/> - W-Educ</title>

    </head>

    <body>

	    <div class="container">
            <div class="row">
                <div class="col-md-12 col-md-offset-2">
                    <div class="col-md-4">
                        <div class="login-panel panel panel-default">
                            <div class="panel-heading" style="background-color:#FFEB99">
                                <h3 class="panel-title" ><b>Bem Vindo</b></h3>
                            </div>
                            <div class="panel-body">
                                <p>
                                    &nbsp;&nbsp;&nbsp;&nbsp;O <a href="https://github.com/Natalnet/W-Educ/wiki"><b>W-Educ</b></a> é um ambiente de desenvolvimento web multiplataforma configurável para aplicações em robótica educacional.
                                </p>
				<p align="justify">
                                    &nbsp;&nbsp;&nbsp;&nbsp;Este projeto, desenvolvido por pesquisadores do laboratório <a href="http://www.natalnet.br"><b>NatalNet</b></a> da <a href="http://www.ufrn.br"><b>Universidade Federal do Rio Grande do Norte</b></a>
                                    e do laboratório <a href="http://laica.ifrn.edu.br"><b>LAICA</b></a> do <a href="http://ifrn.edu.br"><b>Instituto Federal do Rio Grande do Norte</b></a>, 
                                    é um projeto aberto, que surgiu como uma extensão do software educacional RoboEduc, e que possibilita que a programação de qualqeur robô programável possa ser realizada utilizando a linguagem <b>R-Educ</b>. </p>
                                <p align="justify">  
                                    &nbsp;&nbsp;&nbsp;&nbsp;O sistema <a href="https://github.com/Natalnet/W-Educ/wiki"><b>W-Educ</b></a> é uma plataforma completa e gratuita para robótica educacional!
                                </p>
                                
                                
                                <p align="justify">  
                                    &nbsp;&nbsp;&nbsp;&nbsp;Não deixe de participar e conferir as últimas nóticias do nosso <a href="/weduc/forum/home"><b>Fórum de Discussão</b></a>
                                </p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="login-panel panel panel-default">
                            <div class="panel-heading" style="background-color:#FFEB99">
                                <h3 class="panel-title"><b>Login</b></h3>
                            </div>
                            <div class="panel-body">
                                <p>
			           <CENTER><g:img dir="images" file="capa.jpg" width="150" height="150"/></CENTER>
				</p>
                                <form action='${postUrl}' method='POST' id="loginForm" name="loginForm" autocomplete='off' role="form">
                                    <fieldset>
                                    <div class="form-group">
                                            <g:if test="${flash.message}">
                                            	<div class="alert alert-danger alert-dismissable">
    				                            	<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
    				                                <g:if test="${flash.message.equals('Sorry, we were not able to find a user with that username and password.')}">
                                                        Verifique se o usuário e a senha estão corretos.
                                                    </g:if>
                                                    <g:else>
                                                        ${flash.message}
                                                    </g:else>
    				                            </div>
                                            </g:if>
                                        </div>
                                        <div class="form-group">
                                            <input class="form-control" placeholder="Usuário" name="j_username" type="text" autofocus>
                                        </div>
                                        <div class="form-group">
                                            <input class="form-control" placeholder="Senha" name="j_password" type="password" value="">
                                        </div>
                                        <div class="checkbox">
                                            <label>
                                                <input name="${rememberMeParameter}" id="remember_me" type="checkbox" value="Lembrar-me">Lembrar-me
                                            </label>
                                        </div>
                                        <!-- Change this to a button or input when using this as a form -->
                                        <input type="submit" name="submit" class="btn btn-lg btn-success btn-block" value="Entrar no Sistema"/>
                                        <g:link controller="cadastro" action="novo" class="btn btn-lg btn-info btn-block">
                                            Novo Cadastro
                                        </g:link>
                                    </fieldset>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

		<!--script>

		<s2ui:initCheckboxes/>

		</script-->

	</body>

</html>
