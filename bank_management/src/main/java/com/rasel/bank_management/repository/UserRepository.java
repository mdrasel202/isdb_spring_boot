package com.rasel.bank_management.repository;

import com.rasel.bank_management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
