package com.moranta.accountmanagement.model;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "client", uniqueConstraints = {
        @UniqueConstraint(name = "unique_customer_id", columnNames = "customerId")
})
public class Client {

    @Id
    private Long id;

    private String name;

    private String customerId;

    private String surname;

    @OneToMany
    private Set<Account> accounts;

    public Client(String name, String customerId, String surname) {
        this.name = name;
        this.customerId = customerId;
        this.surname = surname;
    }

    public Client() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", customerId='" + customerId + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }


}
