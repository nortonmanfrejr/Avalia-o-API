package com.api.BeneficiaryRegister.Controller;


import com.api.BeneficiaryRegister.Entities.BenefiDoc;
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
@RequestMapping(value = "/beneficiario")
@CrossOrigin(value = "*")
public class BeneficiarioCtrl {

    @Autowired
    private BeneficiarioServ serv;
    @Autowired
    private DocumentoServ documentoServ;

    // -------------------------------------------------------------------

    // region GET
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<Beneficiario> FindAll(){
        return serv.FindAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Beneficiario> FindById(@PathVariable Long id){
        return serv.FindById(id);
    }

    @GetMapping(value = "/docs/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Documento> FindByBeneficiarioId(@PathVariable Long id){
        return documentoServ.FindByBeneficiarioId(id);
    }
    // endregion GET

    // -------------------------------------------------------------------

    // region POST
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Beneficiario Create(@RequestBody Beneficiario obj){
        LocalDate datAtual = LocalDate.now();

        obj.dataInclusao = datAtual;
        obj.dataAtualizacao = datAtual;

        return serv.Create(obj);
    }

    // -------------------------------------------------------------------

    @PostMapping(value = "/docs")
    @ResponseStatus(HttpStatus.CREATED)
    public BenefiDoc CreateWithDoc(@RequestBody BenefiDoc obj){
        LocalDate datAtual = LocalDate.now();

        // -------------------------------------------------------------------
        // Create beneficiary

        Beneficiario beneficiary = obj.getBeneficiario();
        beneficiary.dataInclusao = datAtual;
        beneficiary.dataAtualizacao = datAtual;

        serv.Create(beneficiary);

        // -------------------------------------------------------------------
        // Create beneficiary docs
        List<Documento> docList = obj.getDocs();
        for (Documento d : docList) {
            d.setBeneficiario(beneficiary);
            d.dataInclusao = datAtual;
            d.dataAtualizacao = datAtual;

            documentoServ.Create(d);
        }
        return obj;
    }

    // endregion POST

    // -------------------------------------------------------------------

    // region PUT/DELETE
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void Delete(@PathVariable Long id){
        List<Documento> docList = documentoServ.FindByBeneficiarioId(id);
        if (!docList.isEmpty()) for (Documento d : docList) documentoServ.Delete(d.getId());

        serv.Delete(id);
    }

    // -------------------------------------------------------------------

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void Update(@RequestBody Beneficiario obj, @PathVariable Long id){

        Beneficiario origObj = serv.FindById(id).orElse(new Beneficiario());
        if (origObj.getId() == null) return;

        // ----------------------------------------------
        // Campos não alteráveis
        obj.setId(origObj.getId());
        obj.setDataInclusao(origObj.getDataInclusao());
        obj.dataAtualizacao = LocalDate.now();

        // ----------------------------------------------
        // Campos alteráveis
        if (obj.getDataNascimento() != origObj.getDataNascimento()) obj.setDataNascimento(origObj.getDataNascimento());
        if (!obj.getNome().equals(origObj.getNome())) obj.setNome(origObj.getNome());
        if (!obj.getTelefone().equals(origObj.getTelefone())) obj.setTelefone(origObj.getTelefone());

        // ----------------------------------------------
        serv.Update(obj);
    }
    // endregion PUT/DELETE

}
