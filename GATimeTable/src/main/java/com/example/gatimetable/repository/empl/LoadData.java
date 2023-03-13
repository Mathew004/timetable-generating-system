package com.example.gatimetable.repository.empl;

import com.example.gatimetable.services.Timetable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoadData {
    GroupRepository groupRepository;
    ModuleRepository moduleRepository;
    ProfessorRepository professorRepository;
    RoomRepository roomRepository;
    TimeslotRepository timeslotRepository;
    @Autowired
    public LoadData(GroupRepository groupRepository, ModuleRepository moduleRepository, ProfessorRepository professorRepository, RoomRepository roomRepository, TimeslotRepository timeslotRepository) {
        this.groupRepository = groupRepository;
        this.moduleRepository = moduleRepository;
        this.professorRepository = professorRepository;
        this.roomRepository = roomRepository;
        this.timeslotRepository = timeslotRepository;
    }
    public String LoadedData(){
        String mystr="";
        var rooms = roomRepository.LoadRooms();
        mystr += "Rooms available: "+rooms.size();
        var timeslots = timeslotRepository.LoadTimeslots();
        mystr += "\ntimeslots available: "+timeslots.size();
        var professors = professorRepository.LoadProfessors();
        mystr += "\nprofessors available: "+professors.size();
        var modules = moduleRepository.LoadModules();
        mystr += "\nmodules available: "+modules.size();
        var groups = groupRepository.LoadGroups();
        mystr += "\ngroups available: "+groups.size();
        return mystr;
    }
    public Timetable initializeTimetable(){
        Timetable timetable = new Timetable();
        // Set up rooms
        var rooms = roomRepository.LoadRooms();
        for (var room: rooms) {
            timetable.addRoom(room.getRoomId(), room.getRoomNumber(), room.getCapacity());
        }
        // Set up timeslots
        var timeslots = timeslotRepository.LoadTimeslots();
        for (var timeslot:timeslots) {
            timetable.addTimeslot(timeslot.getTimeslotId(), timeslot.getTimeslot());
        }
        // Set up professors
        var professors = professorRepository.LoadProfessors();
        for (var professor:professors) {
            timetable.addProfessor(professor.getProfessorId(), professor.getProfessorName());
        }
        // Set up modules and define the professors that teach them
        var modules = moduleRepository.LoadModules();
        for (var module:modules) {
            timetable.addModule(module.getModuleId(), module.getModuleCode(), module.getModuleName(),module.getProfessorIds());
        }
        // Set up student groups and the modules they take.
        var groups = groupRepository.LoadGroups();
        for (var group:groups) {
            timetable.addGroup(group.getGroupId(), group.getGroupSize(), group.getModuleIds());
        }
        return timetable;
    }
}
