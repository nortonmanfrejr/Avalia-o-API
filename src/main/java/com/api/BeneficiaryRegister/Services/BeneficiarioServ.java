package com.api.BeneficiaryRegister.Services;

import com.api.BeneficiaryRegister.Entities.Beneficiario;
import com.api.BeneficiaryRegister.Repository.BeneficiarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeneficiarioServ {

    @Autowired
    public BeneficiarioRepo repo;

    public List<Beneficiario> FindAll(){
        return repo.findAll();
    }

    public Optional<Beneficiario> FindById(Long id){
        return repo.findById(id);
    }

    public Beneficiario Create(Beneficiario obj){
        return repo.save(obj);
    }

    public void Delete(Long id){

        repo.deleteById(id);
    }

    public void Update(Beneficiario obj){
        if (!repo.existsById(obj.getId())) return;
        repo.save(obj);
    }
}
