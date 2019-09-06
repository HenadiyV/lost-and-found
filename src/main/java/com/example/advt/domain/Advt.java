package com.example.advt.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *23.08.2019
 */
@Entity
@Table(name = "advt")
public class Advt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @Column(name="name")
//    private String name;

    @Column(name="text")
    private String text;

    @Column(name="active")
    private boolean activ;

    @Column(name="photo")
    private String photo;

    @Column(name="status")
    private String status;

    @Column(name="characters")
    private String characters;

    @Temporal(TemporalType.DATE)

    private Date dat;

    @ManyToOne
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    /**
     *
     */
    @ManyToOne
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
//    @ManyToOne
//    private UserSocial userO;

    /**
     *
     */
    @ManyToOne
    private City city;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
    /**
     *
     */
    @ManyToOne
    private Piople piople;

    public Piople getPiople() {
        return piople;
    }

    public void setPiople(Piople piople) {
        this.piople = piople;
    }

    @ManyToOne
    private Animal animal;

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    @ManyToOne
    private Thing thing;

    public Thing getThing() {
        return thing;
    }

    public void setThing(Thing thing) {
        this.thing = thing;
    }
    /**
     * Default constructor
     */
    public Advt() {
    }

    public int getId() {
        return  id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getCharacter() {
        return characters;
    }

    public void setCharacter(String characters) {
        this.characters = characters;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isActive() {
        return activ;
    }

    public void setActive(boolean active) {
        this.activ = active;
    }

    public Date getData() {
        return dat;
    }

    public void setData(Date data) {
        this.dat = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Advt)) return false;
        Advt advt = (Advt) o;
        return activ == advt.activ &&

                Objects.equals(text, advt.text) &&
                Objects.equals(photo, advt.photo) &&
                Objects.equals(status, advt.status) &&
               Objects.equals(category, advt.category) &&
                Objects.equals(characters, advt.characters) &&
                Objects.equals(dat, advt.dat)&&
               Objects.equals(user, advt.user) &&
               Objects.equals(city, advt.city) ;

    }

    @Override
    public int hashCode() {

        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Advt{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", active=" + activ +
                ", photo='" + photo + '\'' +
                ", status=" + status +
               ", category=" + category +
                ", character=" + characters +
                ", data=" + dat +
                ", user=" + user +
                ", city=" + city +

                '}';
    }
}
