package ar.edu.utn.frc.tup.lc.iii.controlers;


import ar.edu.utn.frc.tup.lc.iii.dtos.DummyDto;
import ar.edu.utn.frc.tup.lc.iii.models.Dummy;
import ar.edu.utn.frc.tup.lc.iii.services.DummyService;
import org.springframework.web.bind.annotation.RequestBody;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/dummy")
public class DummyController {

    @Autowired
    private DummyService dummyService;
    @Autowired
    private ModelMapper modelMapper;


    @GetMapping("/dummy")
    public ResponseEntity <List<DummyDto>> getDummy(){
        List<DummyDto> dummyDto = new ArrayList<>();
         List<Dummy> dummyModels = dummyService.getDummyList();

        for (Dummy dummyModel : dummyModels) {
            dummyDto.add(modelMapper.map(dummyModel, DummyDto.class));
        }
        return ResponseEntity.ok(dummyDto);
    }
    @GetMapping("")
    public ResponseEntity<DummyDto> getDummyAll(@RequestBody DummyDto dummyDto){
        Dummy dummy = dummyService.getByAllDummy(modelMapper.map(dummyDto, Dummy.class));
        DummyDto dummyResponse = modelMapper.map(dummy, DummyDto.class);
        return ResponseEntity.ok(dummyResponse);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Dummy> getDummyList(@PathVariable Long id){
        Dummy dummy = dummyService.getDummy(id);
        return ResponseEntity.ok(dummy);
    }

    @GetMapping("/list")
    public ResponseEntity<DummyDto> getDummyFiltered(@RequestBody DummyDto dummyDto){
        Dummy dummy = dummyService.getByAllDummy(modelMapper.map(dummyDto, Dummy.class));
        DummyDto dummyResponse = modelMapper.map(dummy, DummyDto.class);
        return ResponseEntity.ok(dummyResponse);
    }


    @PostMapping("")
    public ResponseEntity<Dummy> createDummy(@RequestBody DummyDto dummyDto){
        Dummy dummy = modelMapper.map(dummyDto, Dummy.class);
        Dummy saved = dummyService.createDummy(dummy);
        return ResponseEntity.ok(saved);
        }


    @PutMapping("")
    public ResponseEntity<DummyDto> updateDummy(@RequestBody DummyDto dummyDto){

        Dummy dummy = modelMapper.map(dummyDto, Dummy.class);
        Dummy tDummy = dummyService.updateDummy(dummy);
        DummyDto dummyDto1 = modelMapper.map(tDummy, DummyDto.class);
        return ResponseEntity.ok(dummyDto1);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDummy(@PathVariable Long id){
        dummyService.deleteDummy(id);
        return ResponseEntity.ok("El dummy id " + id + " a sido eliminado con exito");
    }

}
