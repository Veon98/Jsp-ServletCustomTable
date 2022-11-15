<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  <!--necessario per l'uso di jstl, libreria che attraverso l'uso di tag permette di non inserire business logic nella view-->

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

<br><br>

<div>

    <div>

        <input id="addbtn" type="button" value="Aggiungi utente" onclick="window.location.href='add-user-form.jsp'; return false;"/>

    <br><br>

        <table id="tab">

            <tr>
                <th style="text-align: center;">Nome</th>
                <th style="text-align: center;">Cognome</th>
                <th style="text-align: center;">Data di Nascita</th>
                <th style="text-align: center;">Azione</th>
            </tr>

            <c:forEach var="tmpUser" items="${listUser}">  <!--jstl che scorre l'arraylist fornita dal controller (UserServlet)-->

                <!--carica i valori per pre-compilare i campi del form di update-->
                <c:url var="updLink" value="UserServlet">  <!--specifica il path dell'url-->
                    <c:param name="command" value="LOAD" />  <!--imposta il parametro command a LOAD-->
                    <c:param name="id" value="${tmpUser.id}" /> <!--permette di passare l'id dell'utente cliccato-->
                </c:url>

                <!--logica uguale a quella di sopra, ma per la delete-->
                <c:url var="deleteLink" value="UserServlet">
                    <c:param name="command" value="DELETE" />
                    <c:param name="id" value="${tmpUser.id}" />
                </c:url>

                <tr>
                    <!--l'oggetto arriva con i campi della tabella (attributi dell'Entità User, proprietà della Classe User.java)-->
                    <td>${tmpUser.name}</td>
                    <td>${tmpUser.surname}</td>
                    <td>${tmpUser.birthday}</td>
                    <!--tasti per update e delete degli utenti-->
                    <td style="text-align: center;">
                        <a id="updbtn" href="${updLink}">Modifica</a>
                        |
                        <a id="dltbtn" href="${deleteLink}" onclick="if (!(confirm('Vuoi davvero eliminare questo utente?'))) return false"> Elimina</a>  <!--l'onlcick permette di avere l'alert di conferma innestando js-->
                    </td>

                </tr>

            </c:forEach>

        </table>

    </div>

</div>


<!--onload nel tag del body se si vuole usare questo script al posto del wlwcome-file in web.xml -->
<!--<script type="text/javascript">
    function redirect(){
        window.location = "UserServlet"
    }
</script>-->


</body>
</html>