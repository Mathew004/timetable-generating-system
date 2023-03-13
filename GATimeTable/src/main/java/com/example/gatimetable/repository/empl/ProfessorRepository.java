package com.example.gatimetable.repository.empl;

import com.example.gatimetable.repository.dtos.ProfessorDto;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
@Component
public class ProfessorRepository {

    //load professors
    public List<ProfessorDto> LoadProfessors(){
        List<ProfessorDto> professors = new ArrayList<>();
        String query = "SELECT * FROM professors";
        ResultSet rs = DbConnection.getResultSet(query);
        try {
            while (rs.next()) {
                ProfessorDto professorDto = new ProfessorDto();
                professorDto.setProfessorId(rs.getInt("id"));
                professorDto.setProfessorName(rs.getString("name"));

                professors.add(professorDto);
            }
        }catch(Exception ex){System.out.println(ex.getMessage());}
        return professors;
    }
}
