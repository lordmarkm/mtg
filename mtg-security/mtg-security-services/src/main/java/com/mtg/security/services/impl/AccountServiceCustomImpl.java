package com.mtg.security.services.impl;

import javax.annotation.Resource;

import org.apache.commons.lang.Validate;
import org.joda.time.DateTime;
import org.springframework.transaction.annotation.Transactional;

import com.mtg.commons.models.Image;
import com.mtg.commons.models.magic.MagicPlayer;
import com.mtg.commons.services.ImageService;
import com.mtg.security.models.Account;
import com.mtg.security.models.AccountInfo;
import com.mtg.security.services.AccountService;
import com.mtg.security.services.AccountServiceCustom;

@Transactional
public class AccountServiceCustomImpl implements AccountServiceCustom {

	@Resource
	private AccountService service;
	
	@Resource
	private ImageService images;
	
	@Override
	public Account update(Account account) {
		Account old = service.findOne(account.getId());
		old.setUsername(account.getUsername());
		old.setPassword(account.getPassword());
		return old;
	}

	@Override
	public void updateLastLogin(String username) {
		Account account = service.findByUsername(username);
		Validate.notNull(account);
		
		AccountInfo info = account.getInfo();
		if(null == info) {
			info = new AccountInfo();
			info.setJoined(DateTime.now());
			account.setInfo(info);
		}
		
		info.setLastLogin(DateTime.now());
	}
	
	@Override
	public Image saveProfilePic(String name, byte[] bytes) {
		
		Account account = service.findByUsername(name);
		MagicPlayer player = account.getPlayer();
		Image image = player.getImage();
		
		if(null == image) {
			image = new Image();
			player.setImage(image);
		}
		
		return images.update(image, bytes, ImageService.DEFAULT_FORMAT);
	}

}
