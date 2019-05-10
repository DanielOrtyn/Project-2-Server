package com.revature.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.revature.model.Img;
import com.revature.repository.ImgRepo;

@Service
public class ImgService {

		private ImgRepo imgRepo;

		public List<Img> findAll() {
			return imgRepo.findAll();
		}
		
		public Img findById(long id) {
			Optional<Img> item = imgRepo.findById(id);
			return item.isPresent() ? item.get() : null;
		}
		
		public Img save(Img img) {
			return imgRepo.save(img);
		}
}
