package vottega.room_service.dto

import java.time.LocalDateTime
import java.util.*

data class ParticipantResponseDTO(
  val id: UUID,
  val name: String,
  val roomId: Long,
  val position: String?,
  val participantRole: ParticipantRoleDTO,
  val isEntered: Boolean,
  val createdAt: LocalDateTime,
  val enteredAt: LocalDateTime?,
  val lastUpdatedAt: LocalDateTime,
)
