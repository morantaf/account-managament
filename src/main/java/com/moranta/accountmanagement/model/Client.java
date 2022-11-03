package com.moranta.accountmanagement.model;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "client", uniqueConstraints = {
        @UniqueConstraint(name = "unique_customer_id", columnNames = "customerId")
})
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String customerId;

    @NonNull
    private String surname;

    @NonNull
    private String email;

    @OneToMany(mappedBy = "owner")
    private List<Account> accounts;

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
