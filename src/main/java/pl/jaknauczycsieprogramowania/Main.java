package pl.jaknauczycsieprogramowania;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Main implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) {
        List<User> users = userRepository.findAll();
        users.forEach(System.out::println);

        User someUser = getUser();

        userRepository.save(someUser);

        List<User> users2 = userRepository.findAll();
        users2.forEach(System.out::println);

        List<User> foundUsers = userRepository.findByUsername("kamil brzezinski");
        foundUsers.forEach(System.out::println);

        System.out.println();

        List<User> foundUsers2 = userRepository.findByUsernameContaining("kam");
        foundUsers2.forEach(System.out::println);

        System.out.println();

        List<User> foundUsers3 = userRepository.findByAgeGreaterThan(25);
        foundUsers3.forEach(System.out::println);

        System.out.println();

        List<String> cities = new ArrayList<>();
        cities.add("Kraków");
        cities.add("Kielce");
        List<User> foundUsers4 = userRepository.findByCityIn(cities);
        foundUsers4.forEach(System.out::println);
    }

    private User getUser() {
        return new User("kamil brzezinski", 31, "LDZ");
    }
}
