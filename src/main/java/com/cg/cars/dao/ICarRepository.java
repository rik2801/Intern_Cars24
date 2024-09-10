package com.cg.cars.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.cars.entities.Car;

public interface ICarRepository extends JpaRepository<Car, Long> {

	@Query("select c from Car c where c.model LIKE :model")
	public List<Car> findByModel(@Param("model") String model);

	@Query("select c from Car c where c.brand LIKE :brand")
	public List<Car> findByBrand(@Param("brand")String brand);
	
	@Query("select c from Car c where c.registrationState LIKE :registrationState")
	public List<Car> findByLocation(@Param("registrationState")String registrationState);

}
