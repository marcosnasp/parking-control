package com.api.parkingcontrol.repository

import com.api.parkingcontrol.model.ParkingSpotModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ParkingSpotModelRepository : JpaRepository<ParkingSpotModel, UUID> {
    fun existsByLicensePlateCar(licensePlateCar: String?): Boolean

    fun existsByParkingSpotNumber(parkingSpotNumber: String?): Boolean

    fun existsByApartmentAndBlock(apartment: String?, block: String?): Boolean
}