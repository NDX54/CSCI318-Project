package com.grp7.projectB.repository;

import com.grp7.projectB.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
