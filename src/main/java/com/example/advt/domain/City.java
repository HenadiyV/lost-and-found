package com.example.advt.domain;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *23.08.2019
 */
@Entity(name = "city")
@Table(name = "city")
public class City {

    public City() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(name = "name", nullable = false)
    public String name;

    @OneToMany(mappedBy="city",targetEntity=Advt.class,cascade = CascadeType.ALL)
    private Set<Advt> advt;

    public Set<Advt> getAdvt() {
        return advt;
    }

    public void setAdvt(Set<Advt> advt) {
        this.advt = advt;
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
        if (!(o instanceof City)) return false;
        City city = (City) o;
        return id == city.id &&
                Objects.equals(name, city.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId());
    }
}
