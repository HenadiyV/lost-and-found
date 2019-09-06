package com.example.advt.domain;

import javax.persistence.*;
import java.util.Objects;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *23.08.2019
 */
@Entity(name = "message")
@Table(name = "message")
public class Message {

    /**
     * Default constructor
     */
    public Message() {
    }

    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    /**
     *
     */
    @Column(name = "contact")
    public String contact;

    /**
     *
     */
    @Column(name = "text")
    public String text;

    /**
     *
     */
    @Column(name = "idToUser")
    public int idToUser;

    /**
     *
     */
    @Column(name = "idAdvt")
    public int idAdvt;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getIdToUser() {
        return idToUser;
    }

    public void setIdToUser(int idToUser) {
        this.idToUser = idToUser;
    }

    public int getIdAdvt() {
        return idAdvt;
    }

    public void setIdAdvt(int idAdvt) {
        this.idAdvt = idAdvt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message message = (Message) o;
        return Objects.equals(id, message.id) &&
                Objects.equals(contact, message.contact) &&
                Objects.equals(text, message.text) &&
                Objects.equals(idToUser, message.idToUser) &&
                Objects.equals(idAdvt, message.idAdvt);
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", contact='" + contact + '\'' +
                ", text='" + text + '\'' +
                ", idToUser=" + idToUser +
                ", idAdvt=" + idAdvt +
                '}';
    }
}
