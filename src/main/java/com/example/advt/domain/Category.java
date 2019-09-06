package com.example.advt.domain;

import javax.persistence.*;
import java.util.Set;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *23.08.2019
 */
@Entity(name = "category")
@Table(name = "category")
public class Category {

    /**
     * Default constructor
     */

    public Category() {
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
    @Column(name = "name")
    public String name;

    /**
     *
     */
    @OneToMany(mappedBy="category",targetEntity=Advt.class,cascade = CascadeType.ALL)
    private Set<Advt> advt;

    public Set<Advt> getAdvt() {
        return advt;
    }

    public void setAdvt(Set<Advt> advt) {
        this.advt = advt;
    }

    @OneToMany(mappedBy="category",targetEntity=Piople.class,cascade = CascadeType.ALL)
    private Set<Piople> piople;

    @OneToMany(mappedBy="category",targetEntity=Animal.class,cascade = CascadeType.ALL)
    private Set<Animal> animal;

    @OneToMany(mappedBy="category",targetEntity=Thing.class,cascade = CascadeType.ALL)
    private Set<Thing> thing;



    public Set<Piople> getPiople() {
        return piople;
    }

    public void setPiople(Set<Piople> piople) {
        this.piople = piople;
    }

    public Set<Animal> getAnimal() {
        return animal;
    }

    public void setAnimal(Set<Animal> animal) {
        this.animal = animal;
    }

    public Set<Thing> getThing() {
        return thing;
    }

    public void setThing(Set<Thing> thing) {
        this.thing = thing;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
