package com.example.exchangeRateViewer.repo;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.exchangeRateViewer.entity.RateDetails;

@Repository
public interface RateDetailsRepository extends JpaRepository<RateDetails, Long> {

	@Query("select r from RateDetails r where r.date >= :date")
	List<RateDetails> findExchangeRatesByDate(@Param("date") Date date);

}
