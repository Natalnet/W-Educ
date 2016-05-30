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
                        <td><b>Nome:</b></td>
                        <td><input class="form-control"  name="name" value="${usuario?.name}" type="text" autofocus required></td>
                      </tr>
                      <tr>
                      <tr>
                        <td><b>Senha:</b></td>
                        <td><input class="form-control"  name="password" value="" type="text" autofocus required></td>
                      </tr>    
                      <tr>
                        <td><b>Instituição:</b></td>
                        <td><input class="form-control"  name="institution" value="${usuario?.institution}" type="text" autofocus required></td>
                      </tr>
                      <tr>
                        <td><b>Data de Nascimento:</b></td>
                        <td><input class="form-control"  name="dateofbirth" value="${usuario?.dateofbirth}" type="dateofbirth" required></td>
                      </tr>
                      <tr>
                        <td><b>Sexo:</b></td>
                        <td><form>
                    <input placeholder="Sexo" type="radio" name="gender" value="M"> M

                    <input placeholder="Sexo" type="radio" name="gender" value="F"> F
                  </form>  
                  </td>
                      </tr>
                   
                      <tr>
                      </tr>
                        <tr>
                        <td><b>Endereço:</b></td>
                        <td><input class="form-control" name="address" value="${usuario?.address}" type="address" required></td>
                      </tr>
                      <tr>
                        <td><b>E-mail:</b></td>
                        <td><input class="form-control"  name="email" value="${usuario?.email}" type="email" required></td>
                      </tr>
                        <td><b>Telefone:</b></td>
                        <td><input class="form-control" name="telefone" value="${usuario?.telefone}" type="telefone" required></td>
                           
                      </tr>
                     
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
                 <div class="panel-footer">
                        <g:link data-original-title="Enviar Mensagem" class="btn btn-info" controller="mensagem" action="escreverUsuario" id="1"><i class="fa fa-envelope fa-fw"></i></g:link>
                        <span class="pull-right">
                             <button type="submit" class="btn btn-success" >
                                 <span class="fa fa-check fa-fw"></span> 
                            </button>
                            <g:link controller="perfil" action="index" type="button" data-original-title="Cancelar Edição" data-toggle="tooltip" class="btn btn-sm btn-danger">
                                <i class="fa fa-envelope fa-fw"></i>
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

