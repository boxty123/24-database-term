package com.example.databaseterm.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
public class ScheduleModel {
    private Date date;
    private String todo;
}
