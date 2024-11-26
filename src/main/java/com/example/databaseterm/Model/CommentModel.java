package com.example.databaseterm.Model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter

public class CommentModel {

    private String comment;
    private Date date;
    private int nid;
    private String author;
}
