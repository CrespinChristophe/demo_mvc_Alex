package bstrom.akimts.demo_mvc.models;


import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ProduitForm {

    @NotBlank
    @Size(min = 2, max = 50)
    private String marque;
    @NotBlank
    @Size(min = 2, max = 50)
    private String modele;
    @NotNull
    @Min(0)
    private Double prix;

}
