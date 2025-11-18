package ar.edu.utn.frc.tup.lc.iii.services.impl;

import ar.edu.utn.frc.tup.lc.iii.entities.DummyEntitie;
import ar.edu.utn.frc.tup.lc.iii.models.Dummy;
import ar.edu.utn.frc.tup.lc.iii.repositories.DummyRepository;
import ar.edu.utn.frc.tup.lc.iii.services.DummyService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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
        for (DummyEntitie dummyEntity : dummyEntities) {
            dummyList.add(modelMapper.map(dummyEntity, Dummy.class));
        }
        return dummyList;
    }

    @Override
    public Dummy createDummy(Dummy dummy) {
        DummyEntitie dummyEntitie;
        dummyEntitie = modelMapper.map((dummy), DummyEntitie.class);
        dummyRepository.save(dummyEntitie);
        return modelMapper.map(dummyEntitie, Dummy.class);
    }

    @Override
    public Dummy updateDummy(Dummy dummy) {
        Dummy mDummy;
        DummyEntitie dummyEntitie = dummyRepository.save(modelMapper.map(dummy, DummyEntitie.class));
        dummyRepository.save(dummyEntitie);
        mDummy = modelMapper.map(dummyEntitie, Dummy.class);
        return mDummy;
    }

    @Override
    public void deleteDummy(Long id) {
        DummyEntitie dummy = dummyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El dummy no existe"));
        dummyRepository.delete(dummy);
    }

    @Override
    public Dummy getByAllDummy(Dummy dummy) {
        Long id =  dummy.getId();
        int aux = 0;
        if (id == null){
            List<DummyEntitie>  dummyEntities = dummyRepository.findAll();
            DummyEntitie dummyEntitie;
            for (int i = 0; i < dummyEntities.size(); i++) {
                dummyEntitie = dummyEntities.get(i);
                if (Objects.equals(dummyEntitie.getDummy(), dummy.getDummy())){
                    dummy.setId(dummyEntitie.getId());
                }
                else {
                    aux++;
                }
            }
            if (aux == dummyEntities.size()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El dummy con dummy '" + dummy.getDummy() + "' no existe");
            } else {
                return dummy;
            }
        }
        else {
           DummyEntitie dummyEntitie = dummyRepository.findById(id)
                   .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El dummy id " + id + " no existe"));
           dummy = modelMapper.map(dummyEntitie, Dummy.class);
        }
        return dummy;
    }

    @Override
    public List<Dummy> getDummyFiltered(Dummy dummy) {
        Long id =  dummy.getId();
        List<Dummy> dummyListResponse = new ArrayList<>();
        int aux = 0;
        if (id == null){
            List<DummyEntitie>  dummyEntities = dummyRepository.findAll();
            DummyEntitie dummyEntitie;
            for (int i = 0; i < dummyEntities.size(); i++) {
                dummyEntitie = dummyEntities.get(i);
                if (Objects.equals(dummyEntitie.getDummy(), dummy.getDummy())){
                    dummyListResponse.add(modelMapper.map(dummyEntitie, Dummy.class));
                }
                else {
                    aux++;
                }
            }
            if (aux == dummyEntities.size()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe un Dummy con dummy '" + dummy.getDummy() +"'");
            } else {
                return dummyListResponse;
            }
        }
        else {
            DummyEntitie dummyEntitie = dummyRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe un Dummy con id " + id));
            dummy = modelMapper.map(dummyEntitie, Dummy.class);
            return Collections.singletonList(dummy);
        }
    }
}
