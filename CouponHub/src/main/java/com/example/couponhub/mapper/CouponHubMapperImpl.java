package com.example.couponhub.mapper;

import com.example.couponhub.dto.CouponHubDto;
import com.example.couponhub.entity.CouponHubEntity;
import org.springframework.stereotype.Component;

@Component
public class CouponHubMapperImpl implements CouponHubMapper {
    @Override
    public CouponHubEntity dtoToEntity(CouponHubDto dto) {
        return CouponHubEntity.builder()
                .uuid(dto.getUuid())
                .category(dto.getCategory())
                .title(dto.getTitle())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .amount(dto.getAmount())
                .details(dto.getDetails())
                .price(dto.getPrice())
                .image(dto.getImage())
                .build();    }

    @Override
    public CouponHubDto entityToDto(CouponHubEntity entity) {
        return CouponHubDto.builder()
                .uuid(entity.getUuid())
                .category(entity.getCategory())
                .title(entity.getTitle())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .amount(entity.getAmount())
                .details(entity.getDetails())
                .price(entity.getPrice())
                .image(entity.getImage())
                .build();     }
}
