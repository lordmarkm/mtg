package com.mtg.web.dto;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.core.style.ToStringCreator;

import com.mtg.commons.models.Expansion;

public class ExpansionForm {

    @NotEmpty
    private String name;
    
    @NotEmpty
    private String description;
    
    @NotEmpty
    private String abbreviation;
    
    public Expansion toExpansion() {
        Expansion exp = new Expansion();
        exp.setName(name);
        exp.setDescription(description);
        exp.setUrlFragment(abbreviation);
        return exp;
    }
    
    @Override
    public String toString() {
        return new ToStringCreator(this)
            .append("name", name)
            .append("description", description)
            .append("abbreviation", abbreviation)
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

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }
}
