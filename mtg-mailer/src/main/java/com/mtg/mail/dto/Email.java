package com.mtg.mail.dto;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.style.ToStringCreator;

public class Email {

    private String sender;
    private String recipient;
    private String message;
    private String subject;
    private String template;
    private Map<String, Object> model = new HashMap<String, Object>();
    
    @Override
    public String toString() {
        return new ToStringCreator(this)
            .append("sender", sender)
            .append("recipient", recipient)
            .append("subject", subject)
            .append("template", template)
            .append("body", message != null && message.length() > 0 ? "[not empty]" : "[empty]")
            .toString();
    }
    
    public String getSender() {
        return sender;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }
    public String getRecipient() {
        return recipient;
    }
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }

    
}
