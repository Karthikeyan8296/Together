package com.example.together.data.remote.dto

data class AllEventsDTO(
    val __v: Int,
    val _id: String,
    val capacity: Int,
    val category: String,
    val coverImage: String,
    val createdAt: String,
    val description: String,
    val endTime: String,
    val eventName: String,
    val hostId: HostId,
    val location: Location,
    val participants: List<Participant>,
    val startTime: String,
    val status: String
)

data class HostId(
    val _id: String,
    val name: String,
    val profileImage: Any
)

data class Location(
    val address: String
)

data class Participant(
    val _id: String,
    val checkedIn: Boolean,
    val qrCode: String,
    val registeredAt: String,
    val userId: String
)