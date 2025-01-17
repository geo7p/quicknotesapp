package com.example.QuickNotesApp.Notita;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NotitaService {

    private final NotitaRepository nRepo;

    public NotitaService(NotitaRepository nRepo) {
        this.nRepo = nRepo;
    }

    public List<Notita> gasesteToateNotitele() {
        return nRepo.findAll();
    }

    public Notita gasesteNotitaDupaId(int id) {
        Optional<Notita> notitaOptional = nRepo.findById(id);
        if(notitaOptional.isPresent()) {
            Notita notita = notitaOptional.get();
            return notita;
        }
        return null;
    }

    public void adaugaNotita(Notita n) {
        nRepo.save(n);
    }

    public void stergeNotitaDupaId(int id) {
        nRepo.deleteById(id);
    }

    public void schimbaArhivare(int id) {
        Optional<Notita> optionalNotita = nRepo.findById(id);
        if (optionalNotita.isPresent()) {
            Notita notita = optionalNotita.get();
            if(notita.getArchived() == true) {
                notita.setArchived(false);
                notita.setTextArhivat("");
                notita.setButonArhivat("ARHIVARE");
            }
            else {
                notita.setArchived(true);
                notita.setTextArhivat("ARHIVATA");
                notita.setButonArhivat("DEZARHIVARE");
            }
            nRepo.save(notita);
        }
    }

    public List<Notita> gasesteNotiteArhivate() {
        List<Notita> notiteArhivate = new ArrayList<>();
        for (Notita n : nRepo.findAll()) {
            if (n.getArchived()) {
                notiteArhivate.add(n);
            }
        }
        return notiteArhivate;
    }

    public void schimbaCuloare(int id, String culoare) {
        Optional<Notita> notitaOptional = nRepo.findById(id);
        if(notitaOptional.isPresent()) {
            Notita notita = notitaOptional.get();
            notita.setCuloare(culoare);
            nRepo.save(notita);
        }
    }

    public void schimbaPin(int id) {
        Optional<Notita> notitaOptional = nRepo.findById(id);
        if(notitaOptional.isPresent()) {
            Notita notita = notitaOptional.get();
            if(notita.getIsPinned() == true) {
                notita.setIsPinned(false);
                notita.setButonPin("PIN");
                notita.setTextPin("");
            }
            else {
                notita.setIsPinned(true);
                notita.setButonPin("UNPIN");
                notita.setTextPin("!");
            }
            nRepo.save(notita);
        }
    }

    public List<Notita> gasesteNotitePinned() {
        List<Notita> notitePinned = new ArrayList<Notita>();
        for(Notita notita : nRepo.findAll()) {
            if(notita.getIsPinned() == true && notita.getArchived() == false){
                notitePinned.add(notita);
            }
        }
        return notitePinned;
    }

    public List<Notita> gasesteNotiteSimple() {
        List<Notita> notiteSimple = new ArrayList<Notita>();
        for(Notita notita : nRepo.findAll()) {
            if(notita.getIsPinned() == false && notita.getArchived() == false){
                notiteSimple.add(notita);
            }
        }
        return notiteSimple;
    }

    public void modificaNotita(int id, String titlu, String subtitlu, String continut, String culoare) {
        Optional<Notita> notitaOptional = nRepo.findById(id);
        if(notitaOptional.isPresent()) {
            Notita notita = notitaOptional.get();
            notita.setTitlu(titlu);
            notita.setSubtitlu(subtitlu);
            notita.setContinut(continut);
            notita.setCuloare(culoare);
            nRepo.save(notita);
        }
    }
}
