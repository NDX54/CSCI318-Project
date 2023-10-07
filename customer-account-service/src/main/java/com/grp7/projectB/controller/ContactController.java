package com.grp7.projectB.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.grp7.projectB.model.entities.Contact;
import com.grp7.projectB.service.ContactService;

import java.util.List;
@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    // HTTP GET 요청에 대한 핸들러: 모든 연락처 가져오기
    @GetMapping("/")
    public List<Contact> getAllContacts() {
        return contactService.getAllContacts();
    }

    // HTTP GET 요청에 대한 핸들러: 특정 연락처 가져오기
    @GetMapping("/{id}")
    public Contact getContactById(@PathVariable Long id) {
        return contactService.getContactById(id);
    }

    // HTTP POST 요청에 대한 핸들러: 새 연락처 생성
    @PostMapping("/")
    public void createContact(@RequestBody Contact contact, @RequestParam("customerId") Long customerId) {
        contactService.createContact(contact, customerId);
    }

    // HTTP PUT 요청에 대한 핸들러: 연락처 업데이트
    @PutMapping("/{id}")
    public void updateContact(@PathVariable Long id, @RequestBody Contact contact) {
        contactService.updateContact(id, contact);
    }

    // HTTP DELETE 요청에 대한 핸들러: 연락처 삭제
    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
    }
}
