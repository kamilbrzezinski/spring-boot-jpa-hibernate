package pl.jaknauczycsieprogramowania;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private int age;
    private String city;

    public User(String username, int age, String city) {
        this.username = username;
        this.age = age;
        this.city = city;
    }
}
