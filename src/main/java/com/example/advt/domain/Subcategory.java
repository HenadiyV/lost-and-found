package com.example.advt.domain;

import javax.persistence.*;
import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subcategory)) return false;
        Subcategory that = (Subcategory) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(category, that.category) &&
                Objects.equals(advt, that.advt);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, category, advt);
    }

    @Override
    public String toString() {
        return "Subcategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", advt=" + advt +
                '}';
    }
}
