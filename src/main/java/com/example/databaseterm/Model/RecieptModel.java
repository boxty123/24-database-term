package com.example.databaseterm.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
public class RecieptModel {

    private int spend;

    private Date date;

    private int amount;
}
