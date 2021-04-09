package com.jamp.bt.rest.api.repositories;

import com.jamp.bt.rest.api.dto.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
