package com.mtg.admin.services;

import com.mtg.admin.services.support.BannableType;
import com.mtg.commons.service.support.OpResult;

public interface AdminService {

	OpResult ban(BannableType type, Long id);
	
}
