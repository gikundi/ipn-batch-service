package io.jenga.batch.repositories;

import io.jenga.batch.entities.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customers, Long> {

    @Query("SELECT c FROM Customers c WHERE c.status = :status")
    public List<Customers> findByStatus(@Param("status") Integer status);


}
