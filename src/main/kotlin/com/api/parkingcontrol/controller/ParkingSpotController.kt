package com.api.parkingcontrol.controller

import com.api.parkingcontrol.dto.ParkingSpotDTO
import com.api.parkingcontrol.mapper.ParkingSpotMapper
import com.api.parkingcontrol.model.ParkingSpotModel
import com.api.parkingcontrol.service.ParkingSpotService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import javax.validation.Valid


@RestController
@CrossOrigin(origins = ["*"], maxAge = 3600)
@RequestMapping("api/v1/parking-spot")
class ParkingSpotController(val parkingSpotService: ParkingSpotService) {
    @GetMapping
    private fun getAllParkingSpot(
        @PageableDefault(
            page = 0,
            size = 10,
            sort = ["id"],
            direction = Sort.Direction.ASC
        ) pageable: Pageable
    ): ResponseEntity<Page<ParkingSpotModel>> = ResponseEntity.status(
        HttpStatus.OK
    ).body(this.parkingSpotService.findAll(pageable))

    @GetMapping("/{id}")
    private fun getOneParkingSpot(@PathVariable(value = "id") id: UUID): ResponseEntity<out Any> {
        val parkingSpotModalOptional: Optional<ParkingSpotModel> = parkingSpotService.findById(id)
        if (!parkingSpotModalOptional.isPresent) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot Not Found")
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(parkingSpotModalOptional.get())
        }
    }

    @DeleteMapping("/{id}")
    private fun deleteOneParkingSpot(@PathVariable(value = "id") id: UUID): ResponseEntity<out Any> {
        val parkingSpotModalOptional: Optional<ParkingSpotModel> = parkingSpotService.findById(id)
        if (!parkingSpotModalOptional.isPresent) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot Not Found")
        }
        parkingSpotService.delete(parkingSpotModalOptional.get())
        return ResponseEntity.status(HttpStatus.OK).body("Parking Spot $id deleted successfully.")
    }

    @PutMapping("/{id}")
    private fun updateParkingSpot(
        @PathVariable(value = "id") id: UUID,
        @RequestBody @Valid parkingSpotDTO: ParkingSpotDTO
    ): ResponseEntity<out Any> {
        val parkingSpotModelOptional: Optional<ParkingSpotModel> = parkingSpotService.findById(id)
        if (!parkingSpotModelOptional.isPresent) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot Not Found")
        }

        var parkingSpotModel: ParkingSpotModel = parkingSpotModelOptional.get()
        val mapper: ParkingSpotMapper<ParkingSpotDTO, ParkingSpotModel> =
            ParkingSpotMapper<ParkingSpotDTO, ParkingSpotModel>()
        val parkingSpot: ParkingSpotModel = mapper.toEntity(parkingSpotDTO)
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.save(parkingSpot))
    }

    @PostMapping
    fun save(@RequestBody parkingSpotDTO: @Valid ParkingSpotDTO?): ResponseEntity<Any?>? {
        if (parkingSpotService.existsByLicensePlateCar(parkingSpotDTO!!.licensePlateCar!!)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: License Plate Car is already in use!")
        }
        if (parkingSpotService.existsByParkingSpotNumber(parkingSpotDTO.parkingSpotNumber!!)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Spot is already in use!")
        }
        if (parkingSpotService.existsByApartmentAndBlock(parkingSpotDTO.apartment, parkingSpotDTO.block!!)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("Conflict: Parking Spot already registered for this apartment/block!")
        }
        val mapper: ParkingSpotMapper<ParkingSpotDTO, ParkingSpotModel> =
            ParkingSpotMapper<ParkingSpotDTO, ParkingSpotModel>()
        parkingSpotDTO.registrationDate = LocalDateTime.now(ZoneId.of("UTC"))
        val parkingSpot: ParkingSpotModel = mapper.toEntity(parkingSpotDTO)
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotService.save(parkingSpot))
    }
}