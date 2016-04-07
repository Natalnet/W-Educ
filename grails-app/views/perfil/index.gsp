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
                        <td>${usuario?.name}</td>
                      </tr>
                      <tr>
                      <tr>
                        <td>Instituição:</td>
                        <td>${usuario?.institution}</td>
                      </tr>
                      <tr>
                        <td>Data de Nascimento:</td>
                        <td>${usuario?.dateofbirth}</td>
                      </tr>
                      <tr>
                        <td>Sexo</td>
                        <td>${usuario?.gender}</td>
                      </tr>
                   
                         <tr>
                      </tr>
                        <tr>
                        <td>Endereço</td>
                        <td>${usuario?.address}</td>
                      </tr>
                      <tr>
                        <td>Email</td>
                        <td>${usuario?.email}</td>
                      </tr>
                        <td>Telefone</td>
                        <td>${usuario?.telefone}
                        </td>
                           
                      </tr>
                     
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
                 <div class="panel-footer">
                       <a data-original-title="Broadcast Message" data-toggle="tooltip" type="button" class="btn btn btn-primary"><i class="glyphicon glyphicon-envelope"></i></a>
                        <span class="pull-right">  

                            
                            <button type="button" class="btn btn-info" data-toggle="modal" data-target="#myModal">
                                Graduação <span class="glyphicon glyphicon-star-empty"></span> 
                            </button>
                            
                            <g:link controller="perfil" action="editar" type="button" class="btn btn btn-warning">
                                <i class="glyphicon glyphicon-edit"></i>
                            </g:link>
                            </span>
                            </div>
                            
                            <!--<div class="panel-footer">
                            <a data-original-title="Remover Usuário" action="excluir" data-toggle="tooltip" type="button" class="btn btn-sm btn-danger"><i class="glyphicon glyphicon-remove"></i></a>
                        <span class="pull-right">     
                        <g:link  action="excluir" type="button" class="btn btn-sm btn-warning">
                            <i class="glyphicon glyphicon-remove"> </i>
                             </g:link>
                        </span>
                       
                    </div>-->
                 <!-- Modal -->
                    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                      <div class="modal-dialog" role="document">
                        <div class="modal-content">
                          <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">Graduação</h4>
                          </div>
                          <div class="modal-body">
                            Você deseja realmente solicitar graduação no sistema? Dessa forma você passará a ter acesso de professor, sendo possível
                            cadastrar linguagens e orientar usuários em atividades.
                            <br>
                            Caso sua solicitação seja aceita você será informado por e-mail dos procedimentos necessários.
                          </div>
                          <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                            <g:link controller="perfil" action="graduar" type="button" class="btn btn-success">
                                Solicitar Graduação <span class="glyphicon glyphicon-star-empty"></span>
                            </g:link>
                          </div>
                        </div>
                      </div>
                    </div>               
              
          </div>
        </div>
      </div>
    </div>
    </body>
</html>
