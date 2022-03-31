package com.nfcReaderApp.nfcReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class NFCReaderService {

    private final NFCReaderRepository NFCReaderRepository;

    @Autowired
    public NFCReaderService(NFCReaderRepository NFCReaderRepository) {
        this.NFCReaderRepository = NFCReaderRepository;
    }

    public Boolean isValid(Integer nfcId) {
        Optional<NFCReader> accountOptional = NFCReaderRepository
                .findAccountByNfcId(nfcId);
        if (accountOptional.isPresent()) {
            return true;
        }
        return false;
    }

    // public List<NFCReader> getStudents() {
    // return NFCReaderRepository.findAll();
    // }

    // public void addNewStudent(NFCReader NFCReader) {
    // Optional<NFCReader> studentOptional = NFCReaderRepository
    // .findStudentByEmail(NFCReader.getEmail());
    // if (studentOptional.isPresent()) {
    // throw new IllegalStateException("email taken");
    // }
    // NFCReaderRepository.save(NFCReader);
    // }

    // public void deleteStudent(Long studentId) {
    // boolean exists = NFCReaderRepository.existsById(studentId);
    // if (!exists) {
    // throw new IllegalStateException(
    // "student with id: " + studentId + "doesn't exist");
    // }
    // NFCReaderRepository.deleteById(studentId);
    // }

    // @Transactional
    // public void updateStudent(Long studentId,
    // String name,
    // String email) {
    // NFCReader NFCReader = NFCReaderRepository.findById(studentId)
    // .orElseThrow(() -> new IllegalStateException("studentID doesn't exist"));

    // if (name != null && name.length() > 0 && NFCReader.getName() != name) {
    // NFCReader.setName(name);
    // }
    // if (email != null && email.length() > 0 && NFCReader.getEmail() != email) {
    // NFCReader.setEmail(email);
    // }
    // }
}
