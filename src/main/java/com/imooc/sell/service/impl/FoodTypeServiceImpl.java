package com.imooc.sell.service.impl;

import com.imooc.sell.dataoobject.FoodType;
import com.imooc.sell.exception.ErrException;
import com.imooc.sell.form.FoodAddForm;
import com.imooc.sell.form.FoodUpdateForm;
import com.imooc.sell.repository.FoodTypeRepository;
import com.imooc.sell.service.FoodTypeService;
import com.imooc.sell.utils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.imooc.sell.enums.ResultEnum.BANNER_IS_NOT_EXIT;
import static com.imooc.sell.enums.ResultEnum.BANNER_UPDATE_ERR;

@Service
public class FoodTypeServiceImpl implements FoodTypeService {
    @Autowired
    private FoodTypeRepository foodTypeRepository;

    @Override
    public List<FoodType> findAll() {
        Sort sort = new Sort(Sort.Direction.DESC, "sortFlag");
        return foodTypeRepository.findAll(sort);
    }

    @Override
    public FoodType addFoodType(FoodAddForm foodAddForm) {
        FoodType foodType = new FoodType();
        BeanUtils.copyNonNullProperties(foodAddForm, foodType);
        return foodTypeRepository.save(foodType);
    }

    @Override
    public FoodType updateFoodType(FoodUpdateForm foodUpdateForm) {
        FoodType foodType = foodTypeRepository.findOne(foodUpdateForm.getId());
        if (foodType == null) {
            throw new ErrException(BANNER_IS_NOT_EXIT.getCode(), BANNER_IS_NOT_EXIT.getMessage());
        }
        BeanUtils.copyNonNullProperties(foodUpdateForm, foodType);
        FoodType result = foodTypeRepository.save(foodType);
        if (result == null) {
            throw new ErrException(BANNER_UPDATE_ERR.getCode(), BANNER_UPDATE_ERR.getMessage());
        } else {
            return result;
        }
    }

    @Override
    public Boolean deleteFoodType(Integer id) {
        FoodType foodType = foodTypeRepository.findOne(id);
        if (foodType == null) {
            throw new ErrException(BANNER_IS_NOT_EXIT.getCode(), BANNER_IS_NOT_EXIT.getMessage());
        }
        foodTypeRepository.delete(foodType);
        FoodType result = foodTypeRepository.findOne(id);
        return result == null;
    }
}
