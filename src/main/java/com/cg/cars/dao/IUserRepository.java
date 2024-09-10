package com.cg.cars.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


import com.cg.cars.entities.User;


@Repository

public interface IUserRepository extends JpaRepository<User, Long> {





}
