package com.imooc.sell.service;

import com.imooc.sell.dataoobject.FoodType;
import com.imooc.sell.form.FoodAddForm;
import com.imooc.sell.form.FoodUpdateForm;

import java.util.List;

public interface FoodTypeService {

    List<FoodType> findAll();

    FoodType addFoodType(FoodAddForm foodAddForm);

    FoodType updateFoodType(FoodUpdateForm foodUpdateForm);

    Boolean deleteFoodType(Integer id);
}
