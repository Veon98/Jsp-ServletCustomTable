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
    <h3 style="font-size: 20px;">Modifica un utente</h3>

    <form action="UserServlet" method="GET">

        <input type="hidden" name="command" value="UPDATE" /> <!--imposta command a UPDATE-->

        <input type="hidden" name="id" value="${user.id}" />  <!--passa l'id dell'utente selezionato al controller-->

        <table>
            <tbody>
            <tr>
                <td><label>Nome:</label></td>
                <td><input type="text" name="name" value="${user.name}" required/></td>  <!--il value contiene il valore specifico dell'oggetto user passato dal controller-->
            </tr>

            <tr>
                <td><label>Cognome:</label></td>
                <td><input type="text" name="surname" value="${user.surname}" required/></td> <!--il value contiene il valore specifico dell'oggetto user passato dal controller-->
            </tr>

            <tr>
                <td><label>Data di nascita:</label></td>
                <td><input type="date" name="birthday" value="${user.birthday}" required/></td> <!--il value contiene il valore specifico dell'oggetto user passato dal controller-->
            </tr>

            <tr>
                <td><label></label></td>
                <td><input type="submit" value="Modifica"/></td>
            </tr>

            </tbody>
        </table>
    </form>
</div>
<br>

<p>
    <a class="homebtn" href="UserServlet">Home</a>
</p>
</body>

</html>











