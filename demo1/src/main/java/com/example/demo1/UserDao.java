package com.example.demo1;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.demo1.User;
import com.example.demo1.HibernateUtil;

import javax.management.Query;


//model del progetto, il pattern dao (data access object) va di pari passo con la jpa, in quanto contiene i metodi relativi all'entità. jpa è un framework per la gestione della persistenza dei dati di un DBMS relazionale nelle applicazioni che usano le piattaforme Java
public class UserDao {

    //recupera tutti gli utenti
    public List < User > getAllUser() {

        Transaction transaction = null;
        List < User > listOfUser = null;  //crea un arralist per inserire gli User objects
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {  //apertura della sessione
            //inizializza la transazione
            transaction = session.beginTransaction();

            //recupera gli oggetti relativi agli utenti (User objects)
            listOfUser = session.createQuery("from User order by id desc").getResultList(); //Effettua la query di select e inserisce i risultati nell'arraylist [User è la classe (entità) non la tabella del db]
            /*String sql = "SELECT id, name, surname, date_format(birthday, '%d-%m-%Y') FROM users order by id desc";  //date_format() non funziona
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(User.class);
            listOfUser = query.list();*/

            //fa il commit della transazione
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listOfUser;  //restituisce l'arraylist con gli User objects
    }



    //recupera il singolo utente attraverso l'id
    public User getUser(int id) {

        Transaction transaction = null;
        User user = null;  //crea un oggetto User
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            //recupera il singolo utente attraverso l'id che gli è stata passata dal controller e lo assegna a user
            user = session.get(User.class, id);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return user;  //restituisce l'oggetto user
    }



    //inserisce un utente nel db
    public void saveUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            //inserisce l'oggetto user passatogli dal controller (newUser) nel db
            session.save(user);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }



    //modifica un utente
    public void updateUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            //aggiorna l'utente (che è già stato scelto nel controller attraverso l'id passatagli dal form di upd) attraverso l'oggetto user passatogli dal controller
            session.update(user);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }



    //cancella un utente
    public void deleteUser(int id) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            User user = session.get(User.class, id);  //recupera l'oggetto user specifico attraverso l'id passatogli dal controller (al quale era stato passato attraverso c:url della view)
            if (user != null) {
                session.delete(user);  //se la get dà risultato allora l'utente viene eliminato dal db
                //System.out.println("utente eliminato");  //debug
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

}