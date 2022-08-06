package com.konak.goodgames.repository;

import com.konak.goodgames.domain.enums.Role;
import com.konak.goodgames.domain.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    UserRole findUserRoleByRole(Role role);
}
