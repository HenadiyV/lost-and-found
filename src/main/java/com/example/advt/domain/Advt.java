package com.example.advt.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

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
    private Long id;

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

    @Column(name="userId")
    private Long userId;

    @Temporal(TemporalType.DATE)

    private Date dat;
 private  boolean found;
    @ManyToOne
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

//    @OneToMany(mappedBy="advt",targetEntity=MessageUser.class,cascade = CascadeType.ALL)
//  private Set<MessageUser> mess;

//    public Set<MessageUser> getMess() {
//        return mess;
//    }
//
//    public void setMess(Set<MessageUser> mess) {
//        this.mess = mess;
//    }
//    @ManyToOne
//    private User user;
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }



    @ManyToOne
    private City city;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @ManyToOne
    private Subcategory subcategory;

    public Subcategory getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
/**
     *
     */
//    @ManyToOne
//    private People people;
//
//    public People getPeople() {
//        return people;
//    }
//
//    public void setPeople(People people) {
//        this.people = people;
//    }

//    @ManyToOne
//    private Animal animal;
//
//    public Animal getAnimal() {
//        return animal;
//    }
//
//    public void setAnimal(Animal animal) {
//        this.animal = animal;
//    }

//    @ManyToOne
//    private Thing thing;
//
//    public Thing getThing() {
//        return thing;
//    }

//    public void setThing(Thing thing) {
//        this.thing = thing;
//    }
    /**
     * Default constructor
     */
    public Advt() {
    }

    public Advt(String text, boolean activ, String photo, String status, String characters, Long userId, Date dat, boolean found, Category category, City city, Subcategory subcategory) {
        this.text = text;
        this.activ = activ;
        this.photo = photo;
        this.status = status;
        this.characters = characters;
        this.userId = userId;
        this.dat = dat;
        this.found = found;
        this.category = category;
        this.city = city;
        this.subcategory = subcategory;
    }

    public Long getId() {
        return  id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
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
    public boolean isActiv() {
        return activ;
    }

    public void setActiv(boolean activ) {
        this.activ = activ;
    }

    public String getCharacters() {
        return characters;
    }

    public void setCharacters(String characters) {
        this.characters = characters;
    }

    public Date getDat() {
        return dat;
    }

    public void setDat(Date dat) {
        this.dat = dat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Advt)) return false;
        Advt advt = (Advt) o;
        return activ == advt.activ &&
                found == advt.found &&
                Objects.equals(id, advt.id) &&
                Objects.equals(text, advt.text) &&
                Objects.equals(photo, advt.photo) &&
                Objects.equals(status, advt.status) &&
                Objects.equals(characters, advt.characters) &&
                Objects.equals(userId, advt.userId) &&
                Objects.equals(dat, advt.dat) &&
                Objects.equals(category, advt.category) &&
                Objects.equals(city, advt.city) &&
                Objects.equals(subcategory, advt.subcategory);
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
                ", activ=" + activ +
                ", photo='" + photo + '\'' +
                ", status='" + status + '\'' +
                ", characters='" + characters + '\'' +
                ", userId=" + userId +
                ", dat=" + dat +
                ", found=" + found +
                ", category=" + category +
                ", city=" + city +
                ", subcategory=" + subcategory +
                '}';
    }
}
