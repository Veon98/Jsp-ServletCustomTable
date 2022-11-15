package com.example.demo1;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo1.User;
import com.example.demo1.HibernateUtil;


//controller del progetto, riceve i dati dalla view, li manipola e li invia al model (dao) che si mette in contatto col db

@WebServlet("/UserServlet")  //path del Servlet

public class UserServlet extends HttpServlet {
    //private static final long serialVersionUID = 1L;
    private UserDao userDao;

    public void init() throws ServletException {
        try {
            userDao = new UserDao();  //istanzio un oggetto della classe UserDao per poterne richiamare i metodi
        }
        catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    //lavoro con richeieste get
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            //recupero il parametro command e lo assegno a una variabile
            String cmd = request.getParameter("command");

            // in tale modo la tabella degli utenti viene visualizzata direttamente all'apertura del progetto (dato che in quel momento il valore di command è null
            if (cmd == null) {
                cmd = "LIST";
            }

            //route della scelta (al cambiare del parametro command varia il metodo che viene richiamato)
            switch (cmd) {

                case "LIST":
                    listUsers(request, response);
                    break;

                case "ADD":
                    addUser(request, response);
                    break;

                case "LOAD":
                    loadUser(request, response);
                    break;

                case "UPDATE":
                    updateUser(request, response);
                    break;

                case "DELETE":
                    deleteUser(request, response);
                    break;

                default:
                    listUsers(request, response);
            }

        }
        catch (Exception e) {
            throw new ServletException(e);
        }
    }

    //recupera tutti gli utenti per mostrarli
    private void listUsers(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List < User > listUser = userDao.getAllUser();  //all'arraylist listUser viene assegnato il valore di ritorno (tutti gli utenti della tabella del db) generato dal metodo getAllUser di UserDao
        request.setAttribute("listUser", listUser);  //in tale modo l'arraylist viene inserito nella richiesta verso la view, che userà la stringa (tra virgolette) per riconoscerla
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");  //viene creato il dispatcher per l'invio alla view specificata (index.jsp)
        dispatcher.forward(request, response);  //invia i dati (settati come attributo della request) alla view specificata
    }

    //recupera il singolo utente attraverso l'id (per pre-compilare il form)
    private void loadUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));  //lo prende dall'updLink
        User existingUser = userDao.getUser(id);  //attraverso il metodo getUser di UserDao viene recuperato l'utente specifico da modificare attraverso il suo id
        request.setAttribute("user", existingUser);  //procedimento già spiegato
        RequestDispatcher dispatcher = request.getRequestDispatcher("upd-user-form.jsp");
        dispatcher.forward(request, response);
    }

    //aggiungere gli utenti al db inviando i dati a UserDao
    private void addUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String name = request.getParameter("name");  //vengono recuperati i vari dati inseriti nel form
        String surname = request.getParameter("surname");
        String birthday = request.getParameter("birthday");

        User newUser = new User(name, surname, birthday);  //si istanzia un oggetto User con tali valori (inseriti nel costruttore)
        userDao.saveUser(newUser);  //si richiama il metodo di UserDao che effettuerà l'insert (session.save) usando l'oggetto creato, passato come argomento
        response.sendRedirect("UserServlet"); //ti rimanda alla pagina indicata (servlet o jsp che sia)
        //listUsers(request, response);   //richiama tale metodo del controller stesso, per visualizzare subito i dati aggiornati nella view
    }

    //modificare gli utenti inviando i dati a UserDao
    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));  //lo prende dall'input (hidden) del form di upd
        String name = request.getParameter("name");  //recupero dei nuovi dati (per effettuare l'update) inseriti nel form
        String surname = request.getParameter("surname");
        String birthday = request.getParameter("birthday");

        User user = new User(id, name, surname, birthday);  //si istanzia un oggetto User con tali valori (inseriti nel costruttore). Vi è anche l'id, fondamentale per modificare il giusto utente (la query effettiva si trova in UserDao)
        userDao.updateUser(user);  //si richiama il metodo di UserDao che effettuerà l'update (session.update) usando l'oggetto creato, passato come argomento
        response.sendRedirect("UserServlet");  //ti rimanda alla pagina indicata (servlet o jsp che sia)
        //listUsers(request, response);   //richiama tale metodo del controller stesso, per visualizzare subito i dati aggiornati nella view
    }

    //eliminare gli utenti inviando i dati a UserDao
    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));  //id recuperata dal jstl (c:param...) in index.jsp
        userDao.deleteUser(id);  //si richiama il metodo di UserDao che effettuerà la delete (session.delete) che sfrutterà l'id passata come argomento per recuperare l'istanza dell'User object corretto
        response.sendRedirect("UserServlet");  //ti rimanda alla pagina indicata (servlet o jsp che sia)
        //listUsers(request, response);   //richiama tale metodo del controller stesso, per visualizzare subito i dati aggiornati nella view
    }

}