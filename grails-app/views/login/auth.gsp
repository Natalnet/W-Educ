<!DOCTYPE html>
<html>

    <head>

    	<meta name="layout" content="login"/>

        <title><g:message code='spring.security.ui.login.title'/> - W-Educ</title>

    </head>

    <body>

	    <div class="container">
            <div class="row">
                <div class="col-md-4 col-md-offset-4">
                    <div class="login-panel panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">Login</h3>
                        </div>
                        <div class="panel-body">
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

		<script>

		<s2ui:initCheckboxes/>

		</script>

	</body>

</html>
