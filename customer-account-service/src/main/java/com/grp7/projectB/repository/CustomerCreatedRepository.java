package com.grp7.projectB.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.grp7.projectB.model.event.CustomerCreatedEvent;

@Repository
public interface CustomerCreatedRepository extends JpaRepository<CustomerCreatedEvent, Long> {
    // 사용자 정의 쿼리 메서드를 추가할 수 있습니다.
}