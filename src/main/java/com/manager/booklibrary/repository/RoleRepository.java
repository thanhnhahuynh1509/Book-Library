package com.manager.booklibrary.repository;

import java.util.List;

import com.manager.booklibrary.entity.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    
    @Query("select r.name from AppUser a inner join a.roles r where a.id = ?1")
    List<String> findAllName(long id);

}
