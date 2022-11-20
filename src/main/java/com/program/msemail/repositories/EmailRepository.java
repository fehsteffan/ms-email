package com.program.msemail.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.program.msemail.models.EmailModel;

public interface EmailRepository extends JpaRepository<EmailModel, Long> {

}
