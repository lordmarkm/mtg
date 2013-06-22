package com.mtg.audit.service;

import com.mtg.audit.support.AuditableEvent;

public interface AuditLogger {

	public void log(AuditableEvent type, String logmsg);
	
}
