package com.example.demo.nfcReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class NFCReaderService {

    private final NFCReaderRepository NFCReaderRepository;

    @Autowired
    public NFCReaderService(NFCReaderRepository NFCReaderRepository) {
        this.NFCReaderRepository = NFCReaderRepository;
    }

    public List<BankCard> getAccounts() {
        return NFCReaderRepository.findAll();
    }

    public Boolean validateBankCard(BankCard BankCard) {
        Optional<NFCReaderRepository> bankCardOptional= NFCReaderRepository
                .findBankCardByNfcId(BankCard.getNfcId());

        Date today = Calendar.getInstance().getTime();
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date expiryDate = Date.from(BankCard.getExpiryDate().atStartOfDay(defaultZoneId).toInstant());
        if (today.after(expiryDate)) {
            System.out.println("Your bank card has expired, please go to the service terminal.");
            throw new IllegalStateException("Bank card expired");
        }

        if (bankCardOptional.isPresent()) {
            return true;
        }
        return false;
    }

}

//    public void addNewStudent(BankCard BankCard) {
//        Optional<BankCard> studentOptional= NFCReaderRepository
//                .findStudentByEmail(BankCard.getEmail());
//        if (studentOptional.isPresent()) {
//            throw new IllegalStateException("email taken");
//        }
//        NFCReaderRepository.save(BankCard);
//    }
//
//    public void deleteStudent(Long studentId) {
//        boolean exists = NFCReaderRepository.existsById(studentId);
//        if (!exists) {
//            throw new IllegalStateException(
//                    "student with id: " + studentId + "doesn't exist");
//        }
//        NFCReaderRepository.deleteById(studentId);
//    }
//
//    @Transactional
//    public void updateStudent(Long studentId,
//                              String name,
//                              String email) {
//        BankCard BankCard = NFCReaderRepository.findById(studentId)
//                .orElseThrow(() -> new IllegalStateException("studentID doesn't exist"));
//
//        if (name != null && name.length() > 0 && BankCard.getName() != name) {
//        BankCard.setName(name); }
//        if (email != null && email.length() > 0 && BankCard.getEmail() != email) {
//            BankCard.setEmail(email); }
//    }