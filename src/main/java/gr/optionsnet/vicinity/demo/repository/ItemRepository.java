package gr.optionsnet.vicinity.demo.repository;

import gr.optionsnet.vicinity.demo.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {

    Optional<Item> findByOid(String s);
    Optional<Item> findByInfraId(String s);

    List<Item> findByOidNot(String s);
}
