package com.example.gatimetable.services;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class TimetableResponse {
    String module;
    int group;
    String room;
    String professor;
    String time;
}
