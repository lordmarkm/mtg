package com.mtg.web.dto;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.core.style.ToStringCreator;

import com.mtg.commons.models.collections.Binder;

public class BinderForm {

    @NotEmpty(message = "Binder must have a name")
    private String name;
    
    @NotEmpty(message = "Binder must have a description")
    private String description;

    @Override
    public String toString() {
        return new ToStringCreator(this)
            .append("name", name)
            .append("description", description)
            .toString();
    }
    
    public Binder toBinder() {
        Binder binder = new Binder();
        binder.setName(name);
        binder.setDescription(description);
        return binder;
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
    
}
