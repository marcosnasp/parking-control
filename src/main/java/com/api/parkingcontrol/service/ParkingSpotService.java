package com.api.parkingcontrol.service;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.parkingcontrol.model.ParkingSpotModel;
import com.api.parkingcontrol.repository.ParkingSpotModelRepository;

@Service
public class ParkingSpotService {

    private final ParkingSpotModelRepository parkingSpotModelRepository;

    public ParkingSpotService(ParkingSpotModelRepository parkingSpotModelRepository) {
        this.parkingSpotModelRepository = parkingSpotModelRepository;
    }
    
    public Optional<ParkingSpotModel> findById(UUID id) {
    	return this.parkingSpotModelRepository.findById(id);
    }

    public Page<ParkingSpotModel> findAll(Pageable pageable) {
        return this.parkingSpotModelRepository.findAll(pageable);
    }

    @Transactional
    public ParkingSpotModel save(ParkingSpotModel parkingSpot) {
        return this.parkingSpotModelRepository.save(parkingSpot);
    }

    public boolean existsByLicensePlateCar(String licensePlateCar) {
        return this.parkingSpotModelRepository.existsByLicensePlateCar(licensePlateCar);
    }

    public boolean existsByParkingSpotNumber(String parkingSpotNumber) {
        return this.parkingSpotModelRepository.existsByParkingSpotNumber(parkingSpotNumber);
    }

    public boolean existsByApartmentAndBlock(String apartment, String block) {
        return this.parkingSpotModelRepository.existsByApartmentAndBlock(apartment, block);
    }

    @Transactional
	public void delete(ParkingSpotModel parkingSpotModel) {
		this.parkingSpotModelRepository.delete(parkingSpotModel);
	}
}
