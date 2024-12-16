package com.wahenoor.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wahenoor.Entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {}
