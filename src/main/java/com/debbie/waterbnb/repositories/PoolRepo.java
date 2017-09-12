package com.debbie.waterbnb.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.debbie.waterbnb.models.Pool;
import com.debbie.waterbnb.models.User;

@Repository
public interface PoolRepo extends CrudRepository<Pool, Long> {
	
	@Query("SELECT p FROM Pool p")
	List<Pool> findAllPools();
	
	@Query("SELECT p FROM Pool p JOIN p.host h WHERE h = ?1")
	List<Pool> findUserPools(User user);
	
	@Query("SELECT p FROM Pool p WHERE p.id = ?1")
	Pool findOnePool(Long id);
	
	@Modifying
	@Query("update Pool p set p.description = ?1, p.size = ?2, p.cost = ?3, p.updatedAt = ?4 WHERE p.id = ?5")
	int updatePool(String description, String size, Float cost, Date updatedAt, Long id);
	
	List<Pool> findByAddressContaining(String search);
	
}
