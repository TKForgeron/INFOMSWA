package com.example.demo.nfcReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.sql.DataSource;

@RestController
@RequestMapping(path = "api/v1/nfcreader")
public class NFCReaderController {

    @Autowired
    @Qualifier("eventStore")
    DataSource eventStore;

    @Autowired
    @Qualifier("bankCards")
    DataSource bankCards;

    private final NFCReaderService NFCReaderService;

    @Autowired
    public NFCReaderController(NFCReaderService NFCReaderService) {
        this.NFCReaderService = NFCReaderService;
    }

    @GetMapping
    public List<BankCard> getAccounts() {
        return NFCReaderService.getAccounts();
    }

    @PostMapping
    public void validateBankCard(@RequestBody BankCard BankCard) {
        bankCardIsValid = NFCReaderService.validateBankCard(BankCard);
        if (bankCardIsValid) {
            generateLocationRegistrationEvent(BankCard);
        } else {
            System.out.println("Your bank card unknown, please register at the service terminal.");
            throw new IllegalStateException("Bank card unknown");
        }
    }

    // @PostMapping(path="/registerLocation")
    public void generateLocationRegistrationEvent(BankCard BankCard) {
        NFCReaderService.registerLocation(BankCard);
    }

    /*
     * @PostMapping
     * public void registerNewStudent(@RequestBody BankCard BankCard) {
     * NFCReaderService.addNewStudent(BankCard);
     * }
     * 
     * @DeleteMapping(path = "{studentId}")
     * public void deleteStudent(@PathVariable("studentId") Long studentId) {
     * NFCReaderService.deleteStudent(studentId);
     * }
     * 
     * @PutMapping(path="{studentId}")
     * public void updateStudent(
     * 
     * @PathVariable("studentId") Long studentId,
     * 
     * @RequestParam(required=false) String name,
     * 
     * @RequestParam(required = false) String email) {
     * NFCReaderService.updateStudent(studentId, name, email);
     * }
     */
}
