package com.imooc.sell.service.impl;

import com.imooc.sell.dataoobject.FoodType;
import com.imooc.sell.exception.ErrException;
import com.imooc.sell.form.FoodAddForm;
import com.imooc.sell.form.FoodUpdateForm;
import com.imooc.sell.repository.FoodTypeRepository;
import com.imooc.sell.service.FoodTypeService;
import com.imooc.sell.utils.BeanUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import static com.imooc.sell.enums.ResultEnum.THE_ONE_IS_NOT_EXIT;
import static com.imooc.sell.enums.ResultEnum.THE_ONE_UPDATE_ERR;
import static com.imooc.sell.enums.ResultEnum.THE_ONE_IS_EXIST;

@Service
@Slf4j
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
        if (IsExistBySortFlag(foodAddForm.getSortFlag())) {
            throw new ErrException(THE_ONE_IS_EXIST.getCode(), THE_ONE_IS_EXIST.getMessage());
        }
        FoodType foodType = new FoodType();
        BeanUtils.copyNonNullProperties(foodAddForm, foodType);
        return foodTypeRepository.save(foodType);
    }

    @Override
    public FoodType updateFoodType(FoodUpdateForm foodUpdateForm) {
        FoodType foodTypeById = foodTypeRepository.findById(foodUpdateForm.getId());
        if (foodTypeById == null) {
            throw new ErrException(THE_ONE_IS_NOT_EXIT.getCode(), THE_ONE_IS_NOT_EXIT.getMessage());
        }
        if (!foodTypeById.getSortFlag().equals(foodUpdateForm.getSortFlag())) {
            if (IsExistBySortFlag(foodUpdateForm.getSortFlag())) {
                throw new ErrException(THE_ONE_IS_EXIST.getCode(), THE_ONE_IS_EXIST.getMessage());
            }
        }
        BeanUtils.copyNonNullProperties(foodUpdateForm, foodTypeById);
        FoodType result = foodTypeRepository.save(foodTypeById);
        if (result == null) {
            throw new ErrException(THE_ONE_UPDATE_ERR.getCode(), THE_ONE_UPDATE_ERR.getMessage());
        } else {
            return result;
        }
    }

    @Override
    public Boolean deleteFoodType(Integer id) {
        FoodType foodType = foodTypeRepository.findById(id);
        log.error("【找到的数据={}】", foodType);
        if (foodType == null) {
            throw new ErrException(THE_ONE_IS_NOT_EXIT.getCode(), THE_ONE_IS_NOT_EXIT.getMessage());
        }
        foodTypeRepository.delete(foodType);
        FoodType result = foodTypeRepository.findOne(id);
        return result == null;
    }

    @Override
    public Boolean IsExistBySortFlag(Integer sortFlag) {
        FoodType result = foodTypeRepository.findBySortFlag(sortFlag);
        return result != null;
    }
}
