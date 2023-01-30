package com.spring.boot.sai.open.api.rest.context;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserContext {
    private String lang;
    private boolean tz;
    private int uid;

}
