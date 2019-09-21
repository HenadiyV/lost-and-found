package com.example.advt.domain;

import javax.persistence.*;
import java.util.Set;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *15.09.2019
 */
@Entity(name = "subCategory")
@Table(name = "subCategory")
public class Subcategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    @Column(name = "name")
    public String name;

    @ManyToOne
    private Category category;

    @OneToMany(mappedBy="subcategory",targetEntity=Advt.class,cascade = CascadeType.ALL)
    private Set<Advt> advt;

    public Set<Advt> getAdvt() {
        return advt;
    }

    public void setAdvt(Set<Advt> advt) {
        this.advt = advt;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
