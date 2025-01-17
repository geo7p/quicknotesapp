package com.example.QuickNotesApp;



import com.example.QuickNotesApp.Eticheta.Eticheta;
import com.example.QuickNotesApp.Eticheta.EtichetaService;
import com.example.QuickNotesApp.Notita.Notita;
import com.example.QuickNotesApp.Notita.NotitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private NotitaService nServ;

    @Autowired
    private EtichetaService eServ;

    @GetMapping("/")
    public String index(Model model) {
        String mesaj = "";
        List<Notita> notiteSimple = nServ.gasesteNotiteSimple();
        List<Notita> notitePinned = nServ.gasesteNotitePinned();
        if (notiteSimple.isEmpty() && notitePinned.isEmpty()) {
            mesaj = "Nu exista nicio notita.";
        }
        model.addAttribute("notiteSimple", notiteSimple);
        model.addAttribute("notitePinned", notitePinned);
        model.addAttribute("mesaj", mesaj);
        return "index";
    }

    @GetMapping("/adauga")
    public String FormularNotita(Model model) {
        Notita n = new Notita();
        List<Eticheta> e = eServ.gasesteToateEtichetele();
        model.addAttribute("notita", n);
        model.addAttribute("etichete", e);
        return "adauga";
    }

    @PostMapping("/adauga")
    public String adauga(@RequestParam("titlu") String titlu,
                         @RequestParam("subtitlu") String subtitlu,
                         @RequestParam("continut") String continut,
                         @RequestParam("culoare") String culoare,
                         Model model) {
        Notita n = new Notita(titlu, subtitlu, continut, culoare);
        String mesaj = "";
        if (titlu == "" && subtitlu == "" && continut == "") {
            mesaj = "Eroare! Notita nu are nici titlu nici subtitlu nici continut!";
            model.addAttribute("mesaj", mesaj);
            model.addAttribute("notita", new Notita());
            return "adauga";
        } else if (titlu == "") {
            if (subtitlu == "") {
                mesaj = "Eroare! Notita nu are nici titlu nici subtitlu!";
                model.addAttribute("mesaj", mesaj);
                model.addAttribute("notita", new Notita());
                return "adauga";
            } else if (continut == "") {
                mesaj = "Eroare! Notita nu are nici titlu nici continut!";
                model.addAttribute("mesaj", mesaj);
                model.addAttribute("notita", new Notita());
                return "adauga";
            } else {
                mesaj = "Eroare! Notita nu are titlu!";
                model.addAttribute("mesaj", mesaj);
                model.addAttribute("notita", new Notita());
                return "adauga";
            }
        } else if (subtitlu == "") {
            if (continut == "") {
                mesaj = "Eroare! Notita nu are nici subtitlu nici continut!";
                model.addAttribute("mesaj", mesaj);
                model.addAttribute("notita", new Notita());
                return "adauga";
            } else {
                mesaj = "Eroare! Notita nu are subtitlu!";
                model.addAttribute("mesaj", mesaj);
                model.addAttribute("notita", new Notita());
                return "adauga";
            }
        } else if (continut == "") {
            mesaj = "Eroare! Notita nu are continut!";
            model.addAttribute("mesaj", mesaj);
            model.addAttribute("notita", new Notita());
            return "adauga";
        } else {
            mesaj = "Notita a fost adaugata cu succes!";
            nServ.adaugaNotita(n);
        }
        List<Notita> notiteSimple = nServ.gasesteNotiteSimple();
        List<Notita> notitePinned = nServ.gasesteNotitePinned();
        model.addAttribute("notiteSimple", notiteSimple);
        model.addAttribute("notitePinned", notitePinned);
        model.addAttribute("mesaj", mesaj);
        return "index";
    }

    @PostMapping("/sterge/{id}")
    public String sterge(@PathVariable int id,
                         Model model) {
        Notita notita = nServ.gasesteNotitaDupaId(id);
        model.addAttribute("notita", notita);
        return "sterge";
    }

    @PostMapping("/confirmaStergere/{id}")
    public String confirmaStergere(@PathVariable int id,
                                   Model model) {
        nServ.stergeNotitaDupaId(id);
        List<Notita> notiteSimple = nServ.gasesteNotiteSimple();
        List<Notita> notitePinned = nServ.gasesteNotitePinned();
        model.addAttribute("notiteSimple", notiteSimple);
        model.addAttribute("notitePinned", notitePinned);
        return "redirect:/";
    }

    @PostMapping("/arhiveaza/{id}")
    public String arhiveaza(@PathVariable int id, Model model) {
        nServ.schimbaArhivare(id);
        List<Notita> notiteSimple = nServ.gasesteNotiteSimple();
        List<Notita> notitePinned = nServ.gasesteNotitePinned();
        model.addAttribute("notiteSimple", notiteSimple);
        model.addAttribute("notitePinned", notitePinned);
        return "index";
    }

    @PostMapping("/pin/{id}")
    public String pin(@PathVariable int id, Model model) {
        nServ.schimbaPin(id);
        List<Notita> notiteSimple = nServ.gasesteNotiteSimple();
        List<Notita> notitePinned = nServ.gasesteNotitePinned();
        model.addAttribute("notiteSimple", notiteSimple);
        model.addAttribute("notitePinned", notitePinned);
        return "index";
    }

    @GetMapping("/arhivate")
    public String arhivate(Model model) {
        String mesaj = "";
        List<Notita> n = nServ.gasesteNotiteArhivate();
        if (n.isEmpty()) {
            mesaj = "Nu exista notite arhivate.";
        }
        model.addAttribute("notite", n);
        model.addAttribute("mesaj", mesaj);
        return "arhivate";
    }

    @PostMapping("/dezarhiveaza/{id}")
    public String unarchive(@PathVariable int id, Model model) {
        nServ.schimbaArhivare(id);
        List<Notita> n = nServ.gasesteNotiteArhivate();
        model.addAttribute("notite", n);
        return "arhivate";
    }

    @GetMapping("/adaugaEticheta")
    public String formEticheta(Model model) {
        Eticheta e = new Eticheta();
        List<Eticheta> etichete = eServ.gasesteToateEtichetele();
        model.addAttribute("eticheta", e);
        model.addAttribute("etichete", etichete);
        return "adauga_eticheta";
    }

    @PostMapping("/adaugaEticheta")
    public String adaugaEticheta(@RequestParam("denumire") String denumire,
                                 Model model) {
        String mesaj = "";
        if (eServ.repetareEtichetaDupaDenumire(denumire) == true) {
            mesaj = "Eroare! Eticheta se repeta! Mai incercati.";
            List<Eticheta> e = eServ.gasesteToateEtichetele();
            model.addAttribute("mesaj", mesaj);
            model.addAttribute("etichete", e);
            model.addAttribute("eticheta", new Eticheta());
            return "adauga_eticheta";
        } else if (denumire == "") {
            mesaj = "Eroare! Nu ati dat o denumire etichetei.";
            List<Eticheta> e = eServ.gasesteToateEtichetele();
            model.addAttribute("mesaj", mesaj);
            model.addAttribute("etichete", e);
            model.addAttribute("eticheta", new Eticheta());
            return "adauga_eticheta";
        } else {
            mesaj = "Eticheta a fost adaugata cu succes!";
            Eticheta e = new Eticheta(denumire);
            eServ.adaugaEticheta(e);
            List<Notita> notiteSimple = nServ.gasesteNotiteSimple();
            List<Notita> notitePinned = nServ.gasesteNotitePinned();
            model.addAttribute("mesaj", mesaj);
            model.addAttribute("notiteSimple", notiteSimple);
            model.addAttribute("notitePinned", notitePinned);
            return "index";
        }
    }

    @GetMapping("/formModifica/{id}")
    public String formModifica(@PathVariable int id, Model model) {
        Notita n = nServ.gasesteNotitaDupaId(id);
        model.addAttribute("notita", n);
        return "modifica";
    }

    @PostMapping("/modifica/{id}")
    public String modifica(@PathVariable int id,
                           @RequestParam("titlu") String titlu,
                           @RequestParam("subtitlu") String subtitlu,
                           @RequestParam("continut") String continut,
                           @RequestParam("culoare") String culoare,
                           Model model) {
        String mesaj = "";
        Notita notitaCurenta = nServ.gasesteNotitaDupaId(id);
        if (titlu == "" && subtitlu == "" && continut == "") {
            mesaj = "Eroare! Notita nu are nici titlu nici subtitlu nici continut!";
            model.addAttribute("mesaj", mesaj);
            model.addAttribute("notita", notitaCurenta);
            return "modifica";
        } else if (titlu == "") {
            if (subtitlu == "") {
                mesaj = "Eroare! Notita nu are nici titlu nici subtitlu!";
                model.addAttribute("mesaj", mesaj);
                model.addAttribute("notita", notitaCurenta);
                return "modifica";
            } else if (continut == "") {
                mesaj = "Eroare! Notita nu are nici titlu nici continut!";
                model.addAttribute("mesaj", mesaj);
                model.addAttribute("notita", notitaCurenta);
                return "modifica";
            } else {
                mesaj = "Eroare! Notita nu are titlu!";
                model.addAttribute("mesaj", mesaj);
                model.addAttribute("notita", notitaCurenta);
                return "modifica";
            }
        } else if (subtitlu == "") {
            if (continut == "") {
                mesaj = "Eroare! Notita nu are nici subtitlu nici continut!";
                model.addAttribute("mesaj", mesaj);
                model.addAttribute("notita", notitaCurenta);
                return "modifica";
            } else {
                mesaj = "Eroare! Notita nu are subtitlu!";
                model.addAttribute("mesaj", mesaj);
                model.addAttribute("notita", notitaCurenta);
                return "modifica";
            }
        } else if (continut == "") {
            mesaj = "Eroare! Notita nu are continut!";
            model.addAttribute("mesaj", mesaj);
            model.addAttribute("notita", notitaCurenta);
            return "modifica";
        }
        else {
            mesaj = "Notita a fost modificata cu succes.";
            nServ.modificaNotita(id, titlu, subtitlu, continut, culoare);
            List<Notita> notiteSimple = nServ.gasesteNotiteSimple();
            List<Notita> notitePinned = nServ.gasesteNotitePinned();
            model.addAttribute("notiteSimple", notiteSimple);
            model.addAttribute("notitePinned", notitePinned);
            model.addAttribute("mesaj", mesaj);
            return "index";
        }
    }
 }
