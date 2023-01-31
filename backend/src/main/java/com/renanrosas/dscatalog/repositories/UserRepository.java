package com.renanrosas.dscatalog.repositories;

import com.renanrosas.dscatalog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
