package com.grp7.projectC.controller;

import com.grp7.projectC.customresponses.APIResponse;
import com.grp7.projectC.model.aggregates.CustomerId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.grp7.projectC.model.entities.Contact;
import com.grp7.projectC.service.ContactService;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.time.LocalDateTime;
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
    @GetMapping()
    public List<Contact> getAllContacts() {
        return contactService.getAllContacts();
    }

    // HTTP GET 요청에 대한 핸들러: 특정 연락처 가져오기
    @GetMapping("/{contactId}")
    public Contact getContactById(@PathVariable String contactId) {
        return contactService.getContactByContactId(contactId);
    }

    // HTTP POST 요청에 대한 핸들러: 새 연락처 생성
    @PostMapping()
    public ResponseEntity<APIResponse<Contact>> createContact(@Valid @RequestBody Contact contact, @RequestParam("customerId") CustomerId customerId, WebRequest request) {
        Contact newContact = contactService.createContact(contact, customerId);

        APIResponse<Contact> response = new APIResponse<>();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Contact created successfully");
        response.setDetails(newContact);
        response.setPath(request.getDescription(false));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // HTTP PUT 요청에 대한 핸들러: 연락처 업데이트

    @PutMapping("/update/{contactId}/")
    public ResponseEntity<APIResponse<Contact>> updateCustomerContact(@RequestParam("customerId") CustomerId customerId, @PathVariable String contactId,
                                                       @Valid @RequestBody Contact contact, WebRequest request) {

        Contact updatedContact = contactService.updateContact(customerId,contactId,contact);

        APIResponse<Contact> response = new APIResponse<>();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Contact updated successfully");
        response.setDetails(updatedContact);
        response.setPath(request.getDescription(false));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // HTTP DELETE 요청에 대한 핸들러: 연락처 삭제
    @DeleteMapping("/delete/{contactId}")
    public ResponseEntity<APIResponse<String>> deleteContact(@PathVariable String contactId, WebRequest request) {
        contactService.deleteContact(contactId);

        APIResponse<String> response = new APIResponse<>();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Contact deleted successfully");
        response.setDetails(contactId);
        response.setPath(request.getDescription(false));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
