package com.revature.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.model.Img;

public interface ImgRepo extends JpaRepository<Img, Long>{
	
	Optional<Img> findById(long imgId);
	
}
