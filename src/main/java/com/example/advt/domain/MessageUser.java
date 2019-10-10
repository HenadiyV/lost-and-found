package com.example.advt.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *23.08.2019
 */
@Entity(name = "message")
@Table(name = "message")
public class MessageUser implements Serializable {

    public MessageUser() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "contact")
    public String contact;

    @Column(name = "text")
    public String text;

    @Column(name = "idToUser")
    public Long idToUser;

    @Column(name = "idAdvt")
    public Long idAdvt;
    @Column(updatable = false)
     @Temporal(TemporalType.DATE)
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="MM/dd/yyyy")
    private Date dat;

    @Column(name="active")
    private boolean active;
    //
    public Long getIdAdvt() {
        return idAdvt;
    }

    public void setIdAdvt(Long idAdvt) {
        this.idAdvt = idAdvt;
    }
//    @ManyToOne
//    private Advt advt;
//    public Advt getAdvt() {
//        return advt;
//    }
//
//    public void setAdvt(Advt advt) {
//        this.advt = advt;
//    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDat() {
        return dat;
    }

    public void setDat(Date dat) {
        this.dat = dat;
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

    public Long getIdToUser() {
        return idToUser;
    }

    public void setIdToUser(Long idToUser) {
        this.idToUser = idToUser;
    }

//    public Long getIdAdvt() {
//        return idAdvt;
//    }
//
//    public void setIdAdvt(Long idAdvt) {
//        this.idAdvt = idAdvt;
//    }


    public MessageUser(String contact, String text, Long idToUser, Long idAdvt, Date dat, boolean active) {
        this.contact = contact;
        this.text = text;
        this.idToUser = idToUser;
        this.idAdvt = idAdvt;
        this.dat = dat;
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MessageUser)) return false;
        MessageUser that = (MessageUser) o;
        return active == that.active &&
                Objects.equals(id, that.id) &&
                Objects.equals(contact, that.contact) &&
                Objects.equals(text, that.text) &&
                Objects.equals(idToUser, that.idToUser) &&
                Objects.equals(idAdvt, that.idAdvt) &&
                Objects.equals(dat, that.dat);
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "MessageUser{" +
                "id=" + id +
                ", contact='" + contact + '\'' +
                ", text='" + text + '\'' +
                ", idToUser=" + idToUser +
                ", idAdvt=" + idAdvt +
                ", dat=" + dat +
                ", activ=" + active +
                '}';
    }
}
