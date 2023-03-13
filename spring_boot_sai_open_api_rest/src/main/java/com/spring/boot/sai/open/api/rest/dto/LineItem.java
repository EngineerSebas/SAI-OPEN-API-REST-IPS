package com.spring.boot.sai.open.api.rest.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LineItem {
    private int account_id;
    private int account_pacific_id;
    private String account_pacific_code;
    private int partner_id;
    private int partner_pacific_id;
    private String partner_pacific_ident;
    private String name;
    private Integer analytic_account_id;
    private int currency_id;
    private double debit;
    private double credit;
    private List<Integer> tax_ids;
}
