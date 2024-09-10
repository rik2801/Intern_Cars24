package com.cg.cars.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.cars.entities.Login;
import com.cg.cars.entities.User;


public interface ILoginRepository extends JpaRepository<Login, User> {


}
