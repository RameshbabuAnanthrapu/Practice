package com.example.exchangeRateViewer.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.exchangeRateViewer.entity.RateDetails;

@Repository
public interface RateDetailsRepository extends JpaRepository<RateDetails, Long> {

}
