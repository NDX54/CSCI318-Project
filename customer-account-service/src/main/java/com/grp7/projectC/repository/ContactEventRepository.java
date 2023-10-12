
package com.grp7.projectC.repository;

import com.grp7.projectC.model.event.ContactEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactEventRepository extends JpaRepository<ContactEvent, Long> {
    // 이 인터페이스에 사용자 정의 쿼리 메서드를 추가할 수 있습니다.
    // Spring Data JPA가 자동으로 해당 메서드를 구현합니다.
}