package com.api.parkingcontrol.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

class ParkingSpotDTO(
    @NotBlank var parkingSpotNumber: String?,
    @NotBlank @Size(max = 7) val licensePlateCar: String,
    @NotBlank val brandCar: String,
    @NotBlank val modelCar: String,
    @NotBlank val colorCar: String,
    @NotBlank val responsibleName: String,
    @NotBlank val apartment: String,
    @NotBlank val block: String?
) {
}