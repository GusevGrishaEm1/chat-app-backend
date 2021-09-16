package ru.example.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.webapp.domain.Room;
import ru.example.webapp.domain.dto.RoomDto;
import ru.example.webapp.domain.dto.RoomDtoRequest;
import ru.example.webapp.mapper.RoomMapper;
import ru.example.webapp.repository.RoomRepo;
import java.util.List;

@Service
public class RoomService {

    @Autowired
    RoomRepo roomRepo;

    RoomMapper roomMapper;

    @Transactional
    public RoomDto addRoom(RoomDtoRequest roomDtoRequest) {
        Room roomEntity = roomMapper.toEntity(roomDtoRequest);
        roomRepo.save(roomEntity);
        return roomMapper.toDto(roomEntity);
    }

    public long deleteRoom(long id) {
        if (roomRepo.findById(id) == null)
            // add throw Exception
            return -1;
        else {
            roomRepo.deleteById(id);
            return id;
        }
    }

    @Transactional
    public RoomDto editRoom(RoomDto roomDto) {
        Room roomEntity = roomMapper.toEntity(roomDto);
        roomRepo.save(roomEntity);
        return roomMapper.toDto(roomEntity);
    }

    @Transactional(readOnly = true)
    public RoomDto getRoom(long id) {
        Room roomEntity = roomRepo.findById(id);
        if (roomEntity==null)
            // add throw Exception
            return null;
        else {
            return roomMapper.toDto(roomEntity);
        }
    }

    @Transactional(readOnly = true)
    public List<RoomDto> getRooms() {
        List<Room> rooms = roomRepo.findAll();
        if (rooms == null)
            // add throw Exception
            return null;
        else {
            return roomMapper.toDto(rooms);
        }
    }

}
