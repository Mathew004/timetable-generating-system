package com.example.gatimetable.repository.empl;

import com.example.gatimetable.repository.dtos.ModuleDto;
import org.springframework.stereotype.Component;

import java.sql.Array;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ModuleRepository {


    //load modules and the professors that teach them
    public List<ModuleDto> LoadModules(){
        List<ModuleDto> modules = new ArrayList<>();
        String query = "SELECT * FROM units";
        ResultSet rs = DbConnection.getResultSet(query);
        try {
            while (rs.next()) {
                ModuleDto moduleDto = new ModuleDto();
                moduleDto.setModuleId(rs.getInt("id"));
                moduleDto.setModuleCode(rs.getString("unit_code"));
                moduleDto.setModuleName(rs.getString("unit_name"));
                var id = rs.getInt("id");

                String sql ="SELECT professor_id FROM module_professors WHERE module_id="+id;
                ResultSet rsql = DbConnection.getResultSet(sql);
                System.out.println("----module_professors");
                List<Integer> professorIds = new ArrayList<>();
                while(rsql.next()){
                    professorIds.add(rsql.getInt("professor_id"));
                }
                int[] a =  professorIds.stream().mapToInt(i->i).toArray();
                moduleDto.setProfessorIds( a);
                modules.add(moduleDto);
            }
        }catch(Exception ex){System.out.println(ex.getMessage());}
        return modules;
    }
}
