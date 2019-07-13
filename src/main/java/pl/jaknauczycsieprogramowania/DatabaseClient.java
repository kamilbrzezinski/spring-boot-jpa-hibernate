package pl.jaknauczycsieprogramowania;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class DatabaseClient {
    @Autowired
    UserRepository userRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/users")
    public ResponseEntity getUsers() throws JsonProcessingException {
        List<User> allUsers = userRepository.findAll();
        return ResponseEntity.ok(objectMapper.writeValueAsString(allUsers));
    }

    @PostMapping("/users")
    public ResponseEntity addUser(@RequestBody User user) throws JsonProcessingException {
        User addedUser = userRepository.save(user);
        return ResponseEntity.ok(objectMapper.writeValueAsString(addedUser));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users/{username}")
    public ResponseEntity getUserByUsername(@PathVariable("username") String username) throws JsonProcessingException {
        List<User> users = userRepository.findByUsername(username);
        return ResponseEntity.ok(objectMapper.writeValueAsString(users));
    }

    @GetMapping("/users/containing/{username}")
    public ResponseEntity getUsersByUsernameContaining(@PathVariable("username") String username) throws JsonProcessingException {
        List<User> users = userRepository.findByUsernameContaining(username);
        return ResponseEntity.ok(objectMapper.writeValueAsString(users));
    }

    @GetMapping("/users/olderThan/{age}")
    public ResponseEntity getUsersOlderThan(@PathVariable("age") int age) throws JsonProcessingException {
        List<User> users = userRepository.findByAgeGreaterThan(age);
        return ResponseEntity.ok(objectMapper.writeValueAsString(users));
    }

    @GetMapping("/users/fromCities/{cities}")
    public ResponseEntity getUsersFromCities(@PathVariable("cities") String cities) throws JsonProcessingException {
        String[] citiesArray = cities.split(",");
        List<String> citiesArrayList = Arrays.asList(citiesArray);
        List<User> users = userRepository.findByCityIn(citiesArrayList);
        return ResponseEntity.ok(objectMapper.writeValueAsString(users));
    }
}
