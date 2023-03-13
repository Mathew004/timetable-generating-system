package com.example.gatimetable.repository.dtos;

import lombok.*;
@Getter
@Setter
public class GroupDto {
   private int groupId;
    private int groupSize;
    private int  moduleIds[];
}
