package ar.edu.utn.frc.tup.lc.iii.services.impl;

import ar.edu.utn.frc.tup.lc.iii.dtos.DummyDto;
import ar.edu.utn.frc.tup.lc.iii.entities.DummyEntitie;
import ar.edu.utn.frc.tup.lc.iii.models.Dummy;
import ar.edu.utn.frc.tup.lc.iii.repositories.DummyRepository;
import ar.edu.utn.frc.tup.lc.iii.services.DummyService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DummyServiceImpl implements DummyService {

@Autowired
private DummyRepository dummyRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Dummy getDummy(Long id) {
        DummyEntitie ent = dummyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dummy " + id + " no encontrado"));
        return modelMapper.map(ent, Dummy.class);
    }

    @Override
    public List<Dummy> getDummyList() {
        List<Dummy> dummyList = new ArrayList<>();
        List<DummyEntitie> dummyEntities = dummyRepository.findAll();
        for (int i = 0; i < dummyEntities.size(); i++) {
            dummyList.add(modelMapper.map(dummyEntities.get(i), Dummy.class));
        }
        return dummyList;
    }

    @Override
    public Dummy createDummy(Dummy dummy) {
        DummyEntitie dummyEntitie = new DummyEntitie();
        dummyEntitie = modelMapper.map((dummy), DummyEntitie.class);
        dummyRepository.save(dummyEntitie);
        Dummy dummy1 = modelMapper.map(dummyEntitie, Dummy.class);
        return dummy1;
    }

    @Override
    public Dummy updateDummy(Dummy dummy) {
        Dummy mDummy = new Dummy();
        DummyEntitie dummyEntitie = dummyRepository.save(modelMapper.map(dummy, DummyEntitie.class));
        dummyRepository.save(dummyEntitie);
        mDummy = modelMapper.map(dummyEntitie, Dummy.class);
        return mDummy;
    }

    @Override
    public void deleteDummy(Long id) {

    }
}
