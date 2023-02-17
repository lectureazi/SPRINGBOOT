package com.mc.bookmanager.rent;

import java.util.List;

import com.mc.bookmanager.rent.dto.RentDto;

public class RentController {

	private RentService rentService  = new RentService();
	
	public RentDto findRentByIdx(long rmIdx) {
		return rentService.findRentByIdx(rmIdx);
	}

	public boolean regisRent(String userId, List<Long> bkIdxs) {
		return rentService.createRent(userId, bkIdxs);
	}

	public boolean returnRentBook(long rbIdx) {
		return rentService.returnRentBook(rbIdx);
	}

	public List<RentDto> findRentByUserId(String userId) {
		return rentService.findRentByUserId(userId);
	}

	
	
	
	
	
	
	
	
	
	
}
