package com.example.advt.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

/*
*@autor Hennadiy Voroboiv 
27.05.2019
16:01
*/
@Entity
@Table(name = "usr")
public class User implements UserDetails,Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name", nullable = false)
  //  @NotEmpty(message= "Заповніть поле имя.")
    private String name;
    private String username;
    @Column(name="email", nullable = false)
  //  @NotEmpty(message="Заповніть поле email.")
    private String email;
    @Column(name="password", nullable = false)
  //  @NotEmpty(message="Заповніть поле пароль.")
    private String password;
    @Column(name="phohe")
    private String phone;
  private boolean active;
private  String idSocial;
private String nameSocial;
    private  Long idSocialUser;

//    @OneToMany(mappedBy="user",targetEntity=Advt.class,cascade = CascadeType.ALL)
//    private Set<Advt> advts;

//    public Set<Advt> getAdvts() {
//        return advts;
//    }
//
//    public void setAdvts(Set<Advt> advts) {
//        this.advts = advts;
//    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    //    @ManyToOne
//    private Role role;
    //===================================
    @ElementCollection(targetClass = Role.class,fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role",joinColumns =@JoinColumn(name="user_id") )
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    //================================
    /**
     * Default constructor
     */
    public User() {
    }

    // getter and setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getIdSocialUser() {
        return idSocialUser;
    }

    public void setIdSocialUser(Long idSocialUser) {
        this.idSocialUser = idSocialUser;
    }

    public String getIdSocial() {
        return idSocial;
    }

    public void setIdSocial(String idSocial) {
        this.idSocial = idSocial;
    }

    public String getNameSocial() {
        return nameSocial;
    }

    public void setNameSocial(String nameSocial) {
        this.nameSocial = nameSocial;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

//    public void setAdvers(Set<Adver> advers) {
//        this.advers = advers;
//    }
//
//    public void setRole(Role role) {
//        this.role = role;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Set<Adver> getAdvers() {
//        return advers;
//    }
//
//    public Role getRole() {
//        return role;
//    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", active=" + active +
                ", idSocial='" + idSocial + '\'' +
                ", nameSocial='" + nameSocial + '\'' +
                ", idSocialUser=" + idSocialUser +
                ", roles=" + roles +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId() == user.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
