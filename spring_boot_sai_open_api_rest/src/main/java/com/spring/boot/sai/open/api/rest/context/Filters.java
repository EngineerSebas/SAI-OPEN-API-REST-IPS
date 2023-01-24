package com.spring.boot.sai.open.api.rest.context;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Filters {
    private List<List<String>> filters;

}
