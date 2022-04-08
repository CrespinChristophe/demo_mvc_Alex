package bstrom.akimts.demo_mvc.controllers;

import bstrom.akimts.demo_mvc.models.Produit;
import bstrom.akimts.demo_mvc.models.ProduitForm;
import bstrom.akimts.demo_mvc.service.ProduitService;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/produit")
public class ProduitController {

    private final ProduitService service;

    public ProduitController(ProduitService service) {
        this.service = service;
    }

    /*@ModelAttribute("get_all")
    public List<Produit> getAll(){
        return service.getAll();
    }à revoir et th:with qui permet de déclarer une variable dans html*/

    @GetMapping("/{id}")
    //@PreAuthorize("permitAll()")
    public String displayOne(@PathVariable int id, Model model){
        Produit p = service.getOne(id);
        model.addAttribute("produit", p);
        return "/pages/displayOne";
    }

    @GetMapping
    //@PreAuthorize("permitAll()")
    public String displayAll(Model model){
        List<Produit> list = service.getAll();
        model.addAttribute("liste_produit", list);
        return "/pages/displayAll";
    }

    @GetMapping("/marque")
    //@PreAuthorize("permitAll()")
    public String displayByMarque(@RequestParam String marque, Model model){
        //List<Produit> list = service.getByMarque(marque);
        model.addAttribute("liste_marque", service.getByMarque(marque));
        model.addAttribute( "marque", marque);//pas nécessaire fct sans mais permet de fct même si la liste est vide
        return "/pages/displayByMarque";
    }

    @GetMapping("/add")
    //@PreAuthorize("isAuthenticated()")
    public String displayInsertForm(@ModelAttribute("produit") ProduitForm form){
        return "forms/produitForm";
    }

    @PostMapping("/add")
   // @PreAuthorize("isAuthenticated()")
    public String processInsert(@Valid @ModelAttribute("produit") ProduitForm form, BindingResult binding){
        if(binding.hasErrors())
            return"forms/produitForm";

        Produit rslt = service.insert(form);
        return "redirect:/produit/" + rslt.getId();
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String handle(NoSuchElementException ex){
        return "/pages/404";
    }

}
