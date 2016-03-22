<div class="table-responsive">
	<table class="table">
		<thead>
			<tr>
				<th>Nome do Usuário</th>
			</tr>
                            
		</thead>
                
		<tbody>
                    
			<g:each in="${destinatarios}" var="destinatario">
				<tr>
					<td>
						<a href="#" onclick="selecionarUsuario(${destinatario.id}, '${destinatario.username}');">
							${destinatario.username}
						</a>
					</td>
                                        
					<td></td>
					<td></td>
                                        <td>
                  </td>
				</tr>
                                
			</g:each>
		</tbody>
	</table>
</div>