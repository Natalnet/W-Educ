<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta name="layout" content="admin"/>
        <title>Perfil</title>
    </head>
    <body>
        <br>
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad" >
   
          <form action="atualizar" method='POST' name="usuario" role="form">
          <fieldset> 
          <div class="panel panel-info">
            <div class="panel-heading">
              <h3 class="panel-title">${usuario?.username}</h3>
            </div>  
            <div class="panel-body">
              <div class="row">
                
                <!--<div class="col-xs-10 col-sm-10 hidden-md hidden-lg"> <br>
                  <dl>
                    <dt>DEPARTMENT:</dt>
                    <dd>Administrator</dd>
                    <dt>HIRE DATE</dt>
                    <dd>11/12/2013</dd>
                    <dt>DATE OF BIRTH</dt>
                       <dd>11/12/2013</dd>
                    <dt>GENDER</dt>
                    <dd>Male</dd>
                  </dl>
                </div>-->
                <div class=" col-md-9 col-lg-9 ">  
                    <table class="table table-user-information">
                    <tbody>
                      <tr>
                        <td>Nome:</td>
                        <td><input class="form-control"  name="name" value="${usuario?.name}" type="text" autofocus required></td>
                      </tr>
                      <tr>
                      <tr>
                        <td>Senha:</td>
                        <td><input class="form-control"  name="password" value="" type="text" autofocus required></td>
                      </tr>    
                      <tr>
                        <td>Instituição:</td>
                        <td><input class="form-control"  name="institution" value="${usuario?.institution}" type="text" autofocus required></td>
                      </tr>
                      <tr>
                        <td>Data de Nascimento:</td>
                        <td><input class="form-control"  name="dateofbirth" value="${usuario?.dateofbirth}" type="dateofbirth" required></td>
                      </tr>
                      <tr>
                        <td>Sexo</td>
                        <td><input class="form-control" name="gender" value="${usuario?.gender}" type="gender" required></td>
                      </tr>
                   
                      <tr>
                      </tr>
                        <tr>
                        <td>Endereço</td>
                        <td><input class="form-control" name="address" value="${usuario?.address}" type="address" required></td>
                      </tr>
                      <tr>
                        <td>Email</td>
                        <td><input class="form-control"  name="email" value="${usuario?.email}" type="email" required></td>
                      </tr>
                        <td>Telefone</td>
                        <td><input class="form-control" name="telefone" value="${usuario?.telefone}" type="telefone" required></td>
                           
                      </tr>
                     
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
                 <div class="panel-footer">
                        <a data-original-title="Broadcast Message" data-toggle="tooltip" type="button" class="btn btn-sm btn-primary"><i class="glyphicon glyphicon-envelope"></i></a>
                        <span class="pull-right">
                            <input type="submit" class="btn btn-sm btn-success" value="Atualizar cadastro" >
                            <g:link controller="perfil" action="index" type="button" data-original-title="Cancelar Edição" data-toggle="tooltip" class="btn btn-sm btn-danger">
                                <i class="glyphicon glyphicon-remove"></i>
                            </g:link>
                            
                        </span>
                    </div>
            
          </div>
          </fieldset>
          </form>
        </div>
      </div>
    </div>
    </body>
</html>

