package com.example.demo.nfcReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/nfcreader")
public class NFCReaderController {

    private final NFCReaderService NFCReaderService;

    @Autowired
    public NFCReaderController(NFCReaderService NFCReaderService) {
        this.NFCReaderService = NFCReaderService;
    }

    @GetMapping
    public List<BankCard> getAccounts() {
        return NFCReaderService.getAccounts();
    }

/*    @PostMapping
    public void registerNewStudent(@RequestBody BankCard BankCard) {
        NFCReaderService.addNewStudent(BankCard);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        NFCReaderService.deleteStudent(studentId);
    }

    @PutMapping(path="{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required=false) String name,
            @RequestParam(required = false) String email) {
        NFCReaderService.updateStudent(studentId, name, email);
    }*/
}

