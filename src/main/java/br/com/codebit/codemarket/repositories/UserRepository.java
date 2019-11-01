package br.com.codebit.codemarket.repositories;

import br.com.codebit.codemarket.entitys.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Transactional(readOnly = true)
    Page<User> findByNameContainingIgnoreCase(String name, Pageable pageable);

    User findByEmail(String email);
}
