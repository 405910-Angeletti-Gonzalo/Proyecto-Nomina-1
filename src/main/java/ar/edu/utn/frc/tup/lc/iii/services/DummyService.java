package ar.edu.utn.frc.tup.lc.iii.services;


import ar.edu.utn.frc.tup.lc.iii.models.Dummy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DummyService {

    Dummy getDummy (Long id);
    List<Dummy> getDummyList();
    Dummy createDummy (Dummy dummy);
    Dummy updateDummy (Dummy dummy);
    void deleteDummy (Long id);
    Dummy getByAllDummy (Dummy dummy);
    List<Dummy> getDummyFiltered (Dummy dummy);
}
