package com.moranta.accountmanagement.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "client", uniqueConstraints = {
        @UniqueConstraint(name = "unique_customer_id", columnNames = "customerId")
})
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String customerId;

    private String surname;

    private String email;

    @OneToMany(mappedBy = "owner")
    private List<Account> accounts;

    public Client(String name, String customerId, String surname, String email) {
        this.name = name;
        this.customerId = customerId;
        this.surname = surname;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
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
