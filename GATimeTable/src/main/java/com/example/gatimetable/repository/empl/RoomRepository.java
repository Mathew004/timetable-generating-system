package com.example.gatimetable.repository.empl;

import com.example.gatimetable.repository.dtos.RoomDto;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class RoomRepository {

    //load rooms
    public List<RoomDto> LoadRooms(){
        List<RoomDto> rooms = new ArrayList<>();
        String query = "SELECT * FROM rooms";
        ResultSet rs = DbConnection.getResultSet(query);
        try {
            while (rs.next()) {
                RoomDto roomDto = new RoomDto();
                roomDto.setRoomId(rs.getInt("id"));
                roomDto.setRoomNumber(rs.getString("number"));
                roomDto.setCapacity(rs.getInt("capacity"));

                rooms.add(roomDto);
            }
        }catch(Exception ex){System.out.println(ex.getMessage());}
        return rooms;
    }
}
