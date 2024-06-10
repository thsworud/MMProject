package com.shop.demo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MMMemberRepository extends JpaRepository<MMMember, String> {
    Optional<MMMember> findById(String id);
    
    @Query(value = "select * from MMMember a where a.id = ?", nativeQuery = true)
    public MMMember findById1(String id);
}
