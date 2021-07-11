package com.forrest.server.web.entity.user;

import javax.jws.soap.SOAPBinding.Use;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Created by Bloo
 * @Date: 2021/07/11
 */


public interface UserRepository extends JpaRepository<Use, Long> {

}
