package com.mtg.web.support;

import java.text.MessageFormat;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.stereotype.Component;

import com.mtg.commons.models.Message;
import com.mtg.commons.services.MessageService;

@Component
public class DbMessageSource extends AbstractMessageSource {

	@Autowired
	private MessageService messages;
	
	@Override
	protected MessageFormat resolveCode(String code, Locale locale) {
		Message message = messages.findByKey(code);
		if(null == message) {
			return createMessageFormat("Message not found for code [" + code + "]", locale);
		}
		return createMessageFormat(message.getMessage(), locale);
	}

}
