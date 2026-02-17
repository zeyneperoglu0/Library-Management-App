package com.tpe.repository;

import com.tpe.domain.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner,Long> {

 boolean existsByEmail(String email);


}
