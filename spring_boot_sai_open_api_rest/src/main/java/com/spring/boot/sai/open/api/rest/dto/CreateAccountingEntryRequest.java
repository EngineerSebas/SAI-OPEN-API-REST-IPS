package com.spring.boot.sai.open.api.rest.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class CreateAccountingEntryRequest {

    private List<LineItem> line_ids;
    private String name;
    private String date;
    private String move_type;
    private int journal_id;
    private int journal_pacific_id;
    private String journal_pacific_pre;
    private String ref;
    private String narration;
    private int idInterface;
}
