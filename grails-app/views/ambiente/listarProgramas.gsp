<div class="table-responsive">
	<table class="table">
		<g:each in="${programas}" var="programa">
			<tr>
				<td>
					<a href="#" onclick="abrirPrograma(${programa.id})">
						${programa.nome}
					</a>
				</td>
			</tr>
		</g:each>
	</table>
</div>
