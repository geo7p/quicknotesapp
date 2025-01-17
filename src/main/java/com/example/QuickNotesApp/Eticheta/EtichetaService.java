package com.example.QuickNotesApp.Eticheta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class EtichetaService {

    @Autowired
    EtichetaRepository eRepo;

    public List<Eticheta> gasesteToateEtichetele() {
        return eRepo.findAll();
    }

    public void adaugaEticheta(Eticheta e) {
        int ok = 1;
        for(Eticheta et : eRepo.findAll()) {
            if(this.repetareEtichetaDupaDenumire(e.getDenumire())) {
                ok = 0;
            }
        }
        if(ok == 1) {
            eRepo.save(e);
        }
    }

    public Eticheta gasesteEtichetaDupaDenumire(String denumire) {
        Optional<Eticheta> etichetaOptional = eRepo.findByDenumire(denumire);
        if(etichetaOptional.isPresent()) {
            Eticheta e = etichetaOptional.get();
            return e;
        }
        else {
            return null;
        }
    }

    public boolean repetareEtichetaDupaDenumire(String denumire) {
        Optional<Eticheta> etichetaOptional = eRepo.findByDenumire(denumire);
        if(etichetaOptional.isPresent()) {
            return true;
        }
        return false;
    }

    public Eticheta gasesteEtichetaDupaId(int id) {
        Optional<Eticheta> etichetaOptional = eRepo.findById(id);
        Eticheta e = new Eticheta();
        if(etichetaOptional.isPresent()) {
            e = etichetaOptional.get();
        }
        return e;
    }

    public void modificaEtichetaDupaId(int id, String denumire) {
        Optional<Eticheta> etichetaOptional = eRepo.findById(id);
        if(etichetaOptional.isPresent()) {
            Eticheta e = etichetaOptional.get();
            e.setDenumire(denumire);
            eRepo.save(e);
        }
    }
}
