package com.grp7.projectB.repository;

import com.grp7.projectB.domain.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ContactRepository extends JpaRepository<Contact, Long> {
}
