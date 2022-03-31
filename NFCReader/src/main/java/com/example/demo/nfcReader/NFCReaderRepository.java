package com.example.demo.nfcReader;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NFCReaderRepository
        extends JpaRepository<NFCReader, Long> {

    @Query("SELECT s FROM student s WHERE s.email = ?1")
    Optional<NFCReader> findStudentByEmail(String email);
}
