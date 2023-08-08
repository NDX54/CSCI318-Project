package csci318.demo.repository;

import csci318.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // 기존 메서드들 ...

    // 여기에 Product에 관련된 커스텀 메서드를 추가할 수 있습니다.
}
