package com.example.gatimetable.repository.dtos;

import lombok.*;
@Getter
@Setter
public class ModuleDto {
    int moduleId;
    String moduleCode;
    String moduleName;
    int professorIds[];
}
