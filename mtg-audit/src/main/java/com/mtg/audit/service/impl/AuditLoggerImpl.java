package com.mtg.audit.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mtg.audit.service.AuditLogger;
import com.mtg.audit.support.AuditableEvent;

@Service
public class AuditLoggerImpl implements AuditLogger {

	private static Logger log = LoggerFactory.getLogger("audit");

	@Override
	public void log(AuditableEvent type, String logmsg) {
		log.info("[{}] {}", type, logmsg);
	}
	
}
