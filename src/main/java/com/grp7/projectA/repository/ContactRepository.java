package com.grp7.projectA.repository;

import com.grp7.projectA.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ContactRepository extends JpaRepository<Contact, Long> {
}
