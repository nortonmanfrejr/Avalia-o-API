package com.api.BeneficiaryRegister.Services;

import com.api.BeneficiaryRegister.Entities.Beneficiario;
import com.api.BeneficiaryRegister.Entities.Documento;
import com.api.BeneficiaryRegister.Repository.DocumentoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentoServ {

    @Autowired
    public DocumentoRepo repo;

    public List<Documento> FindAll(){
        return repo.findAll();
    }

    public List<Documento> FindByBeneficiarioId(Long id){
        List<Documento> beneficiarioDocList =
                repo.findAll()
                        .stream()
                        .filter(x -> x.getBeneficiario().getId().equals(id))
                        .toList();

        return beneficiarioDocList;
    }
    public Optional<Documento> FindById(Long id){
        return repo.findById(id);
    }

    public Documento Create(Documento obj){
        return repo.save(obj);
    }

    public void Delete(Long id){
        repo.deleteById(id);
    }

    public void Update(Documento obj){
        if (!repo.existsById(obj.getId())) return;
        repo.save(obj);
    }
}
