package com.example.advt.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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
public class User implements UserDetails, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    @NotBlank(message= "UserName cannot be empty")
    private String name;
    private String username;
    @Column(name = "email", nullable = false)
    @Email(message="Email not corect")
    @NotBlank(message= "Email cannot be empty")
    private String email;
    @Column(name = "password", nullable = false)
    @NotBlank(message= "Password cannot be empty")
    private String password;
    @Column(name = "phohe")
    private String phone;
    private boolean active;
    private String idSocial;
    private String nameSocial;
    private Long idSocialUser;
    private String activationCode;

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

    public String getactivationCode() {
        return activationCode;
    }

    public void setactivationCode(String activationCode) {
        this.activationCode = activationCode;
    }


    //===================================
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return active == user.active &&
                Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(username, user.username) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(phone, user.phone) &&
                Objects.equals(idSocial, user.idSocial) &&
                Objects.equals(nameSocial, user.nameSocial) &&
                Objects.equals(idSocialUser, user.idSocialUser) &&
                Objects.equals(activationCode, user.activationCode) &&
                Objects.equals(roles, user.roles);
    }

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
                ", activationCode='" + activationCode + '\'' +
                ", roles=" + roles +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
