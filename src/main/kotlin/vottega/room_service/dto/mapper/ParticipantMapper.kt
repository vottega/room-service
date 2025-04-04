package vottega.room_service.dto.mapper

import org.springframework.stereotype.Component
import vottega.avro.Action
import vottega.avro.ParticipantAvro
import vottega.room_service.domain.Participant
import vottega.room_service.dto.ParticipantResponseDTO
import java.time.ZoneId

@Component
class ParticipantMapper(
  private val participantRoleMapper: ParticipantRoleMapper,
) {
  fun toParticipantResponseDTO(participant: Participant): ParticipantResponseDTO {
    return ParticipantResponseDTO(
      id = participant.id ?: throw IllegalStateException("Participant ID is null"),
      name = participant.name,
      roomId = participant.room.id ?: throw IllegalStateException("Room ID is null"),
      position = participant.position,
      participantRole = participantRoleMapper.toParticipantRoleDTO(participantRole = participant.participantRole),
      isEntered = participant.isEntered,
      createdAt = participant.createdAt ?: throw IllegalStateException("createdAt is null"),
      enteredAt = participant.enteredAt,
      lastUpdatedAt = participant.lastUpdatedAt ?: throw IllegalStateException("lastUpdatedAt is null")
    )
  }

  fun toParticipantAvro(participantResponseDTO: ParticipantResponseDTO, action: Action): ParticipantAvro {
    return ParticipantAvro.newBuilder()
      .setId(participantResponseDTO.id)
      .setName(participantResponseDTO.name)
      .setRoomId(participantResponseDTO.roomId)
      .setPosition(participantResponseDTO.position)
      .setRole(participantRoleMapper.toParticipantRoleAvro(participantResponseDTO.participantRole))
      .setIsEntered(participantResponseDTO.isEntered)
      .setCreatedAt(participantResponseDTO.createdAt.atZone(ZoneId.systemDefault()).toInstant())
      .setEnteredAt(participantResponseDTO.enteredAt?.atZone(ZoneId.systemDefault())?.toInstant())
      .setLastUpdatedAt(participantResponseDTO.lastUpdatedAt.atZone(ZoneId.systemDefault()).toInstant())
      .setAction(action)
      .build()
  }
}