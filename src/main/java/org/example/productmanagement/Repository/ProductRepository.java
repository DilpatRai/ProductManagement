package org.example.productmanagement.Repository;


import org.example.productmanagement.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
