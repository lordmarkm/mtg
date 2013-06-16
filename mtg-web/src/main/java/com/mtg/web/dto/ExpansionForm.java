package com.mtg.web.dto;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.core.style.ToStringCreator;

import com.mtg.commons.models.Expansion;

public class ExpansionForm {

    @NotEmpty(message="Expansion must have a name")
    private String name;
    
    @NotEmpty(message="Expansion must have a description")
    private String description;
    
    @NotEmpty(message="Expansion must have a code")
    private String code;
    
    public Expansion toExpansion() {
        Expansion exp = new Expansion();
        exp.setName(name);
        exp.setCode(code);
        exp.setDescription(description);
        exp.setUrlFragment(code);
        return exp;
    }
    
    @Override
    public String toString() {
        return new ToStringCreator(this)
            .append("name", name)
            .append("description", description)
            .append("abbreviation", code)
            .toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
