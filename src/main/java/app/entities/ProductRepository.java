package app.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    public Product findByHauptartikelnr(String hauptartikelnr);
    public Product save(Product product);
}
