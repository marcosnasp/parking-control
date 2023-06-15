package com.api.parkingcontrol.dto

import java.time.LocalDateTime
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

class ParkingSpotDTO(
    var id: UUID? = null,
    @NotBlank val parkingSpotNumber: String,
    @NotBlank @Size(max = 7) val licensePlateCar: String,
    @NotBlank val brandCar: String,
    @NotBlank val modelCar: String,
    @NotBlank val colorCar: String,
    @NotBlank var registrationDate: LocalDateTime?,
    @NotBlank val responsibleName: String,
    @NotBlank val apartment: String,
    @NotBlank val block: String
) {
}