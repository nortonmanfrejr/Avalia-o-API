package com.api.BeneficiaryRegister.Repository;

import com.api.BeneficiaryRegister.Entities.Beneficiario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficiarioRepo extends JpaRepository<Beneficiario, Long> {

}
