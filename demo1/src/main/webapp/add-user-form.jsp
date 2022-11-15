<!DOCTYPE html>
<html>

<head>
	<title>Prima task</title>

	<link rel="stylesheet" href="style.css">
</head>

<body>

<div class="title">
	<h1 style="color: ghostwhite; font-family: Arimo;">Users Manager</h1>
</div>
	
	<div class="container">
		<h3 style="font-size: 20px;">Aggiungi un utente</h3>
		
		<form action="UserServlet" method="GET">
		
			<input type="hidden" name="command" value="ADD" /> <!--imposta command a ADD-->
			
			<table>
				<tbody>
					<tr>
						<td><label>Nome:</label></td>
						<td><input type="text" name="name" required/></td>
					</tr>

					<tr>
						<td><label>Cognome:</label></td>
						<td><input type="text" name="surname" required/></td>
					</tr>

					<tr>
						<td><label>Data di nascita:</label></td>
						<td><input type="date" name="birthday" required/></td>
					</tr>
					
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Aggiungi"/></td>
					</tr>
					
				</tbody>
			</table>
		</form>
	</div>

<br>

<p>
	<a class="homebtn" href="UserServlet">Home</a> <!--richiama il servlet e non index.jsp per far caricare direttamente la tabella (ed anche perché è stata definita come pagina iniziale)-->
</p>

</body>

</html>











