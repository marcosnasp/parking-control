package com.api.parkingcontrol.mapper

import com.api.parkingcontrol.dto.ParkingSpotDTO
import com.api.parkingcontrol.model.ParkingSpotModel

class ParkingSpotMapper<D, E> : Mapper<ParkingSpotDTO, ParkingSpotModel> {
    override fun fromEntity(entity: ParkingSpotModel): ParkingSpotDTO = ParkingSpotDTO(
        entity.id,
        entity.parkingSpotNumber,
        entity.licensePlateCar,
        entity.brandCar,
        entity.modelCar,
        entity.colorCar,
        entity.registrationDate,
        entity.responsibleName,
        entity.apartment,
        entity.block
    )

    override fun toEntity(domain: ParkingSpotDTO): ParkingSpotModel = ParkingSpotModel(
        domain.id,
        domain.parkingSpotNumber,
        domain.licensePlateCar,
        domain.brandCar,
        domain.modelCar,
        domain.colorCar,
        domain.registrationDate,
        domain.responsibleName,
        domain.apartment,
        domain.block
    )

}