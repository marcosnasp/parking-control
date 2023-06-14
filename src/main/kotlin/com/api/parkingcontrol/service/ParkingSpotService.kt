package com.api.parkingcontrol.service

import com.api.parkingcontrol.model.ParkingSpotModel
import com.api.parkingcontrol.repository.ParkingSpotModelRepository
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.data.domain.Pageable

import java.util.*
import javax.transaction.Transactional

@Service
class ParkingSpotService(val parkingSpotModelRepository: ParkingSpotModelRepository) {

    fun findById(id: UUID): Optional<ParkingSpotModel> = parkingSpotModelRepository.findById(id)

    fun findAll(pageable: Pageable): Page<ParkingSpotModel> = parkingSpotModelRepository.findAll(pageable)

    @Transactional
    fun save(parkingSpot: ParkingSpotModel) = parkingSpotModelRepository.save(parkingSpot)

    fun existsByParkingSpotNumber(parkingSpotNumber: String) = parkingSpotModelRepository.existsByParkingSpotNumber(parkingSpotNumber)

    fun existsByApartmentAndBlock(apartment: String, block: String) = parkingSpotModelRepository.existsByApartmentAndBlock(apartment, block)

    @Transactional
    fun delete(parkingSpot: ParkingSpotModel) = parkingSpotModelRepository.delete(parkingSpot)
}