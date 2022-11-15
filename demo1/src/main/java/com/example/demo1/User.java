package com.example.demo1;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


//Entity, table, column ecc... fanno parte di javax.persistance e servono per mantenere la logica jpa (java persistance api) che serve per la gestione dei dbms relazionali nel contesto di java
@Entity
@Table(name="users")  //nome della tabella alla quale appartiene l'entità User

public class User {
    //creazione degli attributi (colonne) dell'entità
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)  //generazione chiave primaria automatica
    @Column(name="id")
    protected int id;

    @Column(name="name")
    protected String name;

    @Column(name="surname")
    protected String surname;

    @Column(name="birthday")
    protected String birthday;

    //chiesto esplicitamente da Java
    public User() {
    }

    //costruttore senza id (utile ad esempio per l'add, dato che l'id viene assegnata automaticamente dal db)
    public User(String name, String surname, String birthday) {
        //super();
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
    }

    //costruttore con id (utile ad esempio per l'update dato che il controller deve passare l'id al dao)
    public User(int id, String name, String surname, String birthday) {
        //super();
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
    }

    //getters e setters
    public int getId() {return id; }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getBirthday() {
        return birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}