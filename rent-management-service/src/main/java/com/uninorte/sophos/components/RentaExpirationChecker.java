package com.uninorte.sophos.components;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.uninorte.sophos.RentaRepository;

@Component
public class RentaExpirationChecker {
	
	@Autowired
	private final RentaRepository repository;
	
	public RentaExpirationChecker(RentaRepository repository) {
        this.repository = repository;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void checkRentaExpirationAtBeginningOfDay() {
        LocalDate currentDate = LocalDate.now();
        repository.updateEstadoToVencido(currentDate);
    }

}
