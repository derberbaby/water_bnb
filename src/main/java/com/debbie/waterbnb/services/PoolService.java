package com.debbie.waterbnb.services;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.debbie.waterbnb.models.Pool;
import com.debbie.waterbnb.models.User;
import com.debbie.waterbnb.repositories.PoolRepo;

@Transactional
@Service
public class PoolService {
	private PoolRepo poolRepo;
	
	public PoolService(PoolRepo poolRepo) {
		this.poolRepo = poolRepo;
	}
	
	public List<Pool> findUserPools(User user) {
		return (List<Pool>) poolRepo.findUserPools(user);
	}
	
	public void addPool(Pool pool) {
		poolRepo.save(pool);
	}
	
	public Pool findPoolById(Long id) {
		return poolRepo.findOnePool(id);
	}
	
	public void updatePool(Pool pool) {
		Date now = new Date();
		poolRepo.updatePool(pool.getDescription(), pool.getSize(), pool.getCost(), now, pool.getId());
	}
	
	public List<Pool> findByAddress(String search) {
		return poolRepo.findByAddressContaining(search);
	}
	
	public List<Pool> getAllPools() {
		return (List<Pool>) poolRepo.findAll();
	}
	
	public void updateRatings(Long id) {
		Pool pool = poolRepo.findOne(id);
		pool.setAvg_rating();
	}

}
