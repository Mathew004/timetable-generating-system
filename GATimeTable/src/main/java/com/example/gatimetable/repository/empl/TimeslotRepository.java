package com.example.gatimetable.repository.empl;

import com.example.gatimetable.repository.dtos.RoomDto;
import com.example.gatimetable.repository.dtos.TimeslotDto;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class TimeslotRepository {

    //load timeslots
    public List<TimeslotDto> LoadTimeslots(){
        List<TimeslotDto> timeslots = new ArrayList<>();
        String query = "SELECT * FROM timeslots";
        ResultSet rs = DbConnection.getResultSet(query);
        try {
            while (rs.next()) {
                TimeslotDto timeslotDto = new TimeslotDto();
                timeslotDto.setTimeslotId(rs.getInt("id"));
                timeslotDto.setTimeslot(rs.getString("timeslot"));

                timeslots.add(timeslotDto);
            }
        }catch(Exception ex){System.out.println(ex.getMessage());}
        return timeslots;
    }
}
