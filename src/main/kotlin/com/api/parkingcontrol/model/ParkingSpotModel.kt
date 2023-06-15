package com.api.parkingcontrol.model

import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "TB_PARKING_SPOT")
data class ParkingSpotModel(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: UUID? = null,
    @Column(nullable = false, unique = true, length = 10) var parkingSpotNumber: String,
    @Column(nullable = false, unique = true, length = 7) var licensePlateCar: String,
    @Column(nullable = false, length = 70) var brandCar: String,
    @Column(nullable = false, length = 70) var modelCar: String,
    @Column(nullable = false, length = 70) var colorCar: String,
    @Column(nullable = false) var registrationDate: LocalDateTime?,
    @Column(nullable = false, length = 130) var responsibleName: String,
    @Column(nullable = false, length = 30) var apartment: String,
    @Column(nullable = false, length = 30) var block: String
)