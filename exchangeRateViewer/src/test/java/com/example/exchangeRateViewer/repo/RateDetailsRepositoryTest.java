package com.example.exchangeRateViewer.repo;



import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.exchangeRateViewer.entity.RateDetails;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RateDetailsRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private RateDetailsRepository detailsRepo;

	@Test
	public void testFindExchangeRatesByDate() {

		RateDetails details = new RateDetails();
		details.setBase("EUR");
		details.setCurrency("USD");
		details.setDate(Date.valueOf("2020-07-01"));

		entityManager.persist(details);
		entityManager.flush();

		List<RateDetails> response = detailsRepo.findExchangeRatesByDate(Date.valueOf("2020-07-01"));

		Assert.assertEquals("USD", response.get(0).getCurrency());

	}
	
	@Test
	public void testSaveAll() {

		RateDetails details = new RateDetails();
		details.setBase("EUR");
		details.setCurrency("USD");
		details.setDate(Date.valueOf("2020-07-01"));
		List<RateDetails> rateList =  new ArrayList<>();
		rateList.add(details);

		//entityManager.persist(details);
		//entityManager.flush();

		List<RateDetails> response = detailsRepo.saveAll(rateList);

		Assert.assertEquals("USD", response.get(0).getCurrency());

	}

}
