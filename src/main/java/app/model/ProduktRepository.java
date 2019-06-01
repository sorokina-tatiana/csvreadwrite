package app.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProduktRepository extends JpaRepository<Produkt, String> {

    public Produkt save(Produkt produkt);
}
