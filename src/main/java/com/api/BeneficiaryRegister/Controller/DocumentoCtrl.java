package com.api.BeneficiaryRegister.Controller;


import com.api.BeneficiaryRegister.Entities.Beneficiario;
import com.api.BeneficiaryRegister.Entities.Documento;
import com.api.BeneficiaryRegister.Services.BeneficiarioServ;
import com.api.BeneficiaryRegister.Services.DocumentoServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RestController
@RequestMapping(value = "/documento")
@CrossOrigin(value = "*")
public class DocumentoCtrl {

    @Autowired
    public DocumentoServ serv;

    @Autowired
    public BeneficiarioServ serv2;

    // -------------------------------------------------------------------

    // region GET
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<Documento> FindAll(){
        return serv.FindAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Documento> FindById(@PathVariable Long id){
        return serv.FindById(id);
    }

    @GetMapping(value = "/beneficiario={id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Documento> FindByBeneficiarioId(@PathVariable Long id){
        return serv.FindByBeneficiarioId(id);
    }

    // endregion GET

    // -------------------------------------------------------------------

    @PostMapping(value = "/beneficiario={id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void Create(@RequestBody Documento obj,@PathVariable Long id){
        LocalDate datAtual = LocalDate.now();

        Beneficiario tempBeneficiario = serv2.FindById(id).orElse(new Beneficiario());

        if(tempBeneficiario.getId() == null) return;

        obj.setBeneficiario(tempBeneficiario);
        obj.dataInclusao = datAtual;
        obj.dataAtualizacao = datAtual;

        serv.Create(obj);
    }

    // -------------------------------------------------------------------

    // region DELETE
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void Delete(@PathVariable Long id){
        serv.Delete(id);
    }
    // endregion DELETE

}
