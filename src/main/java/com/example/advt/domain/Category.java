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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(name = "name")
    public String name;

    @OneToMany(mappedBy="category",targetEntity=Advt.class,cascade = CascadeType.ALL)
    private Set<Advt> advt;

    public Set<Advt> getAdvt() {
        return advt;
    }

    public void setAdvt(Set<Advt> advt) {
        this.advt = advt;
    }

    @OneToMany(mappedBy="category",targetEntity=Subcategory.class,cascade = CascadeType.ALL)
    private Set<Subcategory> subcategory;

    public Set<Subcategory> getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(Set<Subcategory> subcategory) {
        this.subcategory = subcategory;
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
