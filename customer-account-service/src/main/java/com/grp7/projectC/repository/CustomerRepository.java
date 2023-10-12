
package com.grp7.projectC.repository;

import com.grp7.projectC.model.aggregates.CustomerId;
import org.springframework.data.jpa.repository.JpaRepository;
import com.grp7.projectC.model.aggregates.CustomerAggregate;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerAggregate, CustomerId> {
    // 여기에 사용자 정의 쿼리 메서드를 추가할 수 있습니다.
    // Spring Data JPA가 자동으로 해당 메서드를 구현합니다.

    Optional<CustomerAggregate> findByCustomerId(CustomerId customerId);
}