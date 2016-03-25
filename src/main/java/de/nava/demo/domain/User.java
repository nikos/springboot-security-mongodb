package de.nava.demo.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
// TODO: @Immutable
public class User {

    @Id
    private String id;

    private String email;

    private String passwordHash;

    private Role role;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email.replaceFirst("@.*", "@***") +
                ", passwordHash='" + passwordHash.substring(0, 10) +
                ", role=" + role +
                '}';
    }
}
