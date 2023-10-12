package com.grp7.projectC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.grp7.projectC.model.event.CustomerEvent;

@Repository
public interface CustomerEventRepository extends JpaRepository<CustomerEvent, Long> {
    // 사용자 정의 쿼리 메서드를 추가할 수 있습니다.
}