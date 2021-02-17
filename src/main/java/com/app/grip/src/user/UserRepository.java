package com.app.grip.src.user;

import com.app.grip.src.user.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository // => JPA => Hibernate => ORM => Database 객체지향으로 접근하게 해주는 도구이다
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByStatus(String status);
    List<User> findByStatusAndPhoneNumberIsContaining(String y, String phoneNumber);
    List<User> findByIdAndSnsDivAndStatus(String id, String div, String status);
    Optional<User> findByNoAndStatus(Long userNo, String status);
}