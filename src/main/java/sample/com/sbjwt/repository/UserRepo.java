package sample.com.sbjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.com.sbjwt.entities.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {
}
