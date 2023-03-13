package com.example.gatimetable.repository.empl;

import com.example.gatimetable.repository.dtos.GroupDto;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class GroupRepository {

    //load student groups and the modules they take
    public List<GroupDto> LoadGroups(){
        List<GroupDto> groups = new ArrayList<>();
        String query = "SELECT * FROM courses";
        ResultSet rs = DbConnection.getResultSet(query);
        try {
            while (rs.next()) {
                GroupDto groupDto = new GroupDto();
                groupDto.setGroupId(rs.getInt("id"));
                groupDto.setGroupSize(rs.getInt("group_size"));
                var id = rs.getInt("id");
                String sql ="SELECT module_id FROM group_modules WHERE group_id="+id;
                ResultSet rsql = DbConnection.getResultSet(sql);
                System.out.println("----group_modules");
                List<Integer> moduleIds = new ArrayList<>();
                while(rsql.next()){
                    moduleIds.add(rsql.getInt("module_id"));
                }
                int[] a =  moduleIds.stream().mapToInt(i->i).toArray();
                groupDto.setModuleIds( a);
                System.out.println(a);
                groups.add(groupDto);
            }
        }catch(Exception ex){System.out.println(ex.getMessage());}
        return groups;
    }
}
