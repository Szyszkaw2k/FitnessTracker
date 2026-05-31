package pl.wsb.fitnesstracker.user.api;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter; // <-- Dodany import
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "Users") // Zgodnie z ERD (wielka litera)
@Getter
@Setter // <-- Dodana adnotacja!
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    // Dodano brakujące z konstruktora pola firstName i lastName
    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    // Zmiana z birthdate na birthday zgodnie ze schematem
    @Column(name = "birthday", nullable = false)
    private LocalDate birthdate;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    public User(
            final String firstName,
            final String lastName,
            final LocalDate birthdate, // Zmiana nazwy parametru
            final String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.email = email;
    }
}