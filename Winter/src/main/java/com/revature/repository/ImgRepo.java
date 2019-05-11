package com.revature.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.model.Img;
public interface ImgRepo extends JpaRepository<Img, Long>{
	
}
