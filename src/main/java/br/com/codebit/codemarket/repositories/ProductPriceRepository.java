package br.com.codebit.codemarket.repositories;

import br.com.codebit.codemarket.entitys.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPriceRepository extends JpaRepository<ProductPrice, Integer> {
}
