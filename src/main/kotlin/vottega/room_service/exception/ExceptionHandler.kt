package vottega.room_service.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(RoomNotFoundException::class, RoleNotFoundException::class, ParticipantNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleResourceNotFoundException(e: RoomNotFoundException) = e.message

    @ExceptionHandler(RoomStatusConflictException::class, RoleStatusConflictException::class)
    @ResponseStatus(HttpStatus.CONFLICT)
    fun handleRoomStatusConflictException(e: RoomStatusConflictException) = e.message
}