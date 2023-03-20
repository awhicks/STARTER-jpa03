package edu.vt.cs3704.example.repositories;

import edu.vt.cs3704.example.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
  Optional<User> findByLogin(String login);
}
