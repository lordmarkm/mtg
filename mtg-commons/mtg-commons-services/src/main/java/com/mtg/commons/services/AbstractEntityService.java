package com.mtg.commons.services;

import org.apache.commons.lang.Validate;

public abstract class AbstractEntityService {

    protected String urlfragment(String source) {
        Validate.notNull(source);
        return source.toLowerCase().replaceAll("[^A-Za-z0-9 ]", "").replaceAll(" ", "-");
    }

}
