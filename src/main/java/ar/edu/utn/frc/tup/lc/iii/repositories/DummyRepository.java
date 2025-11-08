package ar.edu.utn.frc.tup.lc.iii.repositories;


import ar.edu.utn.frc.tup.lc.iii.entities.DummyEntitie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DummyRepository extends JpaRepository<DummyEntitie, Long> {
    @Override
    Optional<DummyEntitie> findById(Long Long);
}
