package com.forrest.server.web.entity.user;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Created by Bloo
 * @Date: 2021/07/11
 */


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail ( String email );
}
