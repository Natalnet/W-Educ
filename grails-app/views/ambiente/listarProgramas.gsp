<div class="table-responsive">
	<table class="table">
		<thead>
			<tr>
				<th>Nome do programa</th>
				<th>Data de criação</th>
				<th>Data de modificação</th>
			</tr>
		</thead>
		<tbody>
			<g:each in="${programas}" var="programa">
				<tr>
					<td>
						<a href="#" onclick="abrirPrograma(${programa.id}, '${programa.nome}')">
							${programa.nome}
						</a>
					</td>
					<td><g:formatDate format="dd/MM/yyy" date="${programa.criadoEm}"/></td>
					<td><g:formatDate format="dd/MM/yyy" date="${programa.modificadoEm}"/></td>
				</tr>
			</g:each>
		</tbody>
	</table>
</div>
