<!DOCTYPE html>
<html>

    <head>

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
                                    &nbsp;&nbsp;&nbsp;&nbsp;O <b>W-Educ</b> é um ambiente de desenvolvimento web multiplataforma configurável para aplicações em robótica educacional.
                                </p>
				<p align="justify">
                                    &nbsp;&nbsp;&nbsp;&nbsp;Este projeto, desenvolvido por pesquisadores do laboratório NatalNet da Universidade Federal do Rio Grande do Norte, é um projeto aberto, que surgiu como uma extensão do software educacional RoboEduc, e que possibilita que a programação de diversos robôs programáveis possa ser realizada utilizando a linguagem R-Educ. Tornamos possível que o usuário programe na linguagem R-Educ e que seu código seja traduzido para uma linguagem cadastrada por um usuário professor, compilado e em seguida enviado e/ou executado pelo robô.
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
                                        <input type="submit" class="btn btn-lg btn-success btn-block" value="Entrar no Sistema"/>
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

		<script>

		<s2ui:initCheckboxes/>

		</script>

	</body>

</html>
