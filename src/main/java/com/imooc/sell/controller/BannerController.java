package com.imooc.sell.controller;


import com.imooc.sell.VO.ResultVO;
import com.imooc.sell.dataoobject.Banner;
import com.imooc.sell.exception.ErrException;
import com.imooc.sell.form.BannerAddForm;
import com.imooc.sell.form.BannerForm;
import com.imooc.sell.service.BannerService;
import com.imooc.sell.utils.BeanUtils;
import com.imooc.sell.utils.ResultVoUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.imooc.sell.enums.ResultEnum.*;

@RestController
@RequestMapping("/banner")
@Api("首页Banner图")
@Slf4j
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @GetMapping("findAll")
    @ApiOperation("查找所有Banner")
    public ResultVO findAll() {
        List<Banner> list = bannerService.findAllBanner();
        return ResultVoUtils.success(list);
    }


    @GetMapping("findById")
    @ApiOperation("根据id查找banner")
    public ResultVO findById(@ApiParam("BannerId") @RequestParam(required = false) Integer bannerId) {
        if (bannerId == null) {
            throw new ErrException(LACK_OF_PARAMETERS.getCode(), LACK_OF_PARAMETERS.getMessage());
        }
        Banner banner = bannerService.findBannerById(bannerId);
        if (banner == null) {
            return ResultVoUtils.error(THE_ONE_IS_NOT_EXIT.getCode(), THE_ONE_IS_NOT_EXIT.getMessage());
        }
        return ResultVoUtils.success(banner);
    }

    @PostMapping("addBanner")
    @ApiOperation("根据Id添加Banner")
    public ResultVO addBanner(@RequestBody(required = false) @Valid BannerAddForm bannerForm, BindingResult bindingResult) {
        if (bannerForm == null || bindingResult.hasErrors()) {
            throw new ErrException(LACK_OF_PARAMETERS.getCode(), LACK_OF_PARAMETERS.getMessage());
        }
        Banner result = bannerService.addBanner(bannerForm);
        if (result == null) {
            throw new ErrException(THE_ONE_IS_ADD_ERROR.getCode(), THE_ONE_IS_ADD_ERROR.getMessage());
        }
        return ResultVoUtils.success(result);
    }

    @DeleteMapping("deleteBanner")
    @ApiOperation("根据Id删除Banner")
    public ResultVO deleteBanner(@ApiParam("Banner 的ID") @RequestParam Integer bannerId) {
        if (bannerId == null) {
            throw new ErrException(LACK_OF_PARAMETERS.getCode(), LACK_OF_PARAMETERS.getMessage());
        }
        if (bannerService.findBannerById(bannerId) == null) {
            throw new ErrException(THE_ONE_IS_NOT_EXIT.getCode(), THE_ONE_IS_NOT_EXIT.getMessage());
        }
        if (bannerService.deleteBanner(bannerId)) {
            return ResultVoUtils.success();
        } else {
            return ResultVoUtils.error(THE_ONE_DELETE_ERROR.getCode(), THE_ONE_DELETE_ERROR.getMessage());
        }
    }

    @PutMapping("updateBanner")
    @ApiOperation("更新Banner")
    public ResultVO updateBanner(@RequestBody(required = false) @Valid BannerForm bannerForm,
                                 BindingResult bindingResult) {
        if (bannerForm == null || bindingResult.hasErrors()) {
            throw new ErrException(LACK_OF_PARAMETERS.getCode(), LACK_OF_PARAMETERS.getMessage());
        }
        Banner banner = new Banner();
        banner.setId(bannerForm.getBannerId());
        BeanUtils.copyNonNullProperties(bannerForm, banner);
        Banner result = bannerService.updateBanner(banner);
        if (result == null) {
            return ResultVoUtils.error(THE_ONE_UPDATE_ERR.getCode(), THE_ONE_UPDATE_ERR.getMessage());
        } else {
            return ResultVoUtils.success(result);
        }
    }
}
