package com.api.BeneficiaryRegister.Repository;


import com.api.BeneficiaryRegister.Entities.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentoRepo extends JpaRepository<Documento, Long> {
}
