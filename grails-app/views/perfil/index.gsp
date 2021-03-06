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
                <div class=" col-md-9 col-lg-9 "> 
                  <table class="table table-user-information">
                    <tbody>
                      <tr>
                        <td><b>Nome:</b></td>
                        <td>${usuario?.name}</td>
                      </tr>
                      <tr>
                      <tr>
                        <td><b>Instituição:</b></td>
                        <td>${usuario?.institution}</td>
                      </tr>
                      <tr>
                        <td><b>Data de Nascimento:</b></td>
                        <td>${usuario?.dateOfBirth}</td>
                      </tr>
                      <tr>
                        <td><b>Sexo:</b></td>
                        <td>${usuario?.gender}</td>
                      </tr>
                   
                         <tr>
                      </tr>
                        <tr>
                        <td><b>Endereço:</b></td>
                        <td>${usuario?.address}</td>
                      </tr>
                      <tr>
                        <td><b>E-mail:</b></td>
                        <td>${usuario?.email}</td>
                      </tr>
                        <td><b>Telefone:</b></td>
                        <td>${usuario?.telefone}
                        </td>
                           
                      </tr>
                     
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
                 <div class="panel-footer">
                       <g:link data-original-title="Enviar Mensagem" class="btn btn-info" controller="mensagem" action="escreverUsuario" id="1"><i class="fa fa-envelope fa-fw"></i></g:link>
                        <span class="pull-right">  

                            
                            <button type="button" class="btn btn-info" data-toggle="modal" data-target="#myModal">
                                Graduação <span class="fa fa-graduation-cap fa-fw"></span> 
                            </button>
                            
                            <g:link controller="perfil" action="editar" type="button" class="btn btn btn-warning">
                                <i class="fa fa-pencil-square-o fa-fw"></i>
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
