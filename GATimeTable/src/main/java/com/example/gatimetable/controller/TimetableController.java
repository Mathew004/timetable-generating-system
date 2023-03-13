package com.example.gatimetable.controller;

import com.example.gatimetable.repository.empl.LoadData;
import com.example.gatimetable.services.TimetableGA;
import com.example.gatimetable.services.TimetableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TimetableController {
    private final TimetableGA timetableGA;
    LoadData loadData;
    @Autowired
    public TimetableController(TimetableGA timetableGA,LoadData loadData){
        this.timetableGA = timetableGA;
        this.loadData =loadData;
    }
    @GetMapping("/")
    public String hello() {
        var str="hello welcome to timetable generation REST API";
        return str;
    }
    @GetMapping("/rm")
    public String rooms() {
        var str=loadData.LoadedData();
        return str.replaceAll("\n","<br/>");
    }
    @GetMapping("/timetable")
    public @ResponseBody
    List<TimetableResponse> timetable() {
        var output_info=timetableGA.TmGA();
        return  output_info;
    }
}