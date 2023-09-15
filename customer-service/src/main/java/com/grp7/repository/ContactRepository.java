package com.grp7.repository;

import com.grp7.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ContactRepository extends JpaRepository<Contact, Long> {
}
