package com.revature.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.Img;
import com.revature.repository.ImgRepo;

@Service
public class ImgService {
		@Autowired
		private ImgRepo imgRepo;

		public List<Img> findAll() {
			return imgRepo.findAll();
		}
		
		public Img findById(long id) {
			return imgRepo.getOne(id);
		}
		
		public Img save(Img i) {
			return imgRepo.save(i);
		}
}
