package com.api.BeneficiaryRegister.Entities;

import java.util.List;

public class BenefiDoc {

    public Beneficiario beneficiario;
    public List<Documento> docs;

    public Beneficiario getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(Beneficiario beneficiario) {
        this.beneficiario = beneficiario;
    }

    public List<Documento> getDocs() {
        return docs;
    }

    public void setDocs(List<Documento> docs) {
        this.docs = docs;
    }
}
