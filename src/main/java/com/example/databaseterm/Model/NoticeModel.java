package com.example.databaseterm.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
public class NoticeModel {
    private String title;
    private String content;
    private Date date;
    private int sn;
    private String cn;

}
