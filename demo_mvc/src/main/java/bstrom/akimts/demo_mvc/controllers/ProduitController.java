package bstrom.akimts.demo_mvc.controllers;

import bstrom.akimts.demo_mvc.models.Produit;
import bstrom.akimts.demo_mvc.service.ProduitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/produit")
public class ProduitController {

    private final ProduitService service;

    public ProduitController(ProduitService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public String displayOne(@PathVariable int id, Model model){
        Produit p = service.getOne(id);
        model.addAttribute("produit", p);
        return "displayOne";
    }

    @GetMapping
    public String displayAll(Model model){
        List<Produit> list = service.getAll();
        model.addAttribute("liste_produit", list);
        return "displayAll";
    }

    @GetMapping("/marque")
    public String displayByMarque(@RequestParam String marque, Model model){
        List<Produit> list = service.getByMarque(marque);
        model.addAttribute("liste_marque", list);
        return "displayByMarque";
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String handle(NoSuchElementException ex){
        return "404";
    }

}
