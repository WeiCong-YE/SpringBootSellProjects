package com.imooc.sell.repository;

import com.imooc.sell.dataoobject.FoodType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodTypeRepository extends JpaRepository<FoodType, Integer> {
}
