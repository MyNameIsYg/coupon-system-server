package com.example.couponhub.mapper;

import com.example.couponhub.dto.CouponHubDto;
import com.example.couponhub.entity.CouponHubEntity;

public interface CouponHubMapper {

    CouponHubEntity dtoToEntity (CouponHubDto dto);
    CouponHubDto entityToDto (CouponHubEntity entity);
}
