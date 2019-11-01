package br.com.codebit.codemarket.repositories;


import br.com.codebit.codemarket.entitys.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Transactional(readOnly = true)
    Page<Product> findByNameContainingIgnoreCaseAndEnabledTrueAndExcludedFalse(String name, Pageable pageable);
}
