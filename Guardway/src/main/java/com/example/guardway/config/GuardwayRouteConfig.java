package com.example.guardway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GuardwayRouteConfig {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder){
    return builder.routes()
            .route("addCustomer", r -> r.path("/customers")
                    .filters(f -> f.rewritePath("/customers", "/api/customers"))
                    .uri("lb://customer-connect"))

            .route("getCustomer", r -> r.path("/customers/details")
                    .filters(f -> f.rewritePath("/customers/details", "/api/customers/details"))
                    .uri("lb://customer-connect"))

            .route("getAllCustomer", r -> r.path("/customers/all")
                    .filters(f -> f.rewritePath("/customers/all", "/api/customers/all"))
                    .uri("lb://customer-connect"))

            .route("customerUpdateDetails", r -> r.path("/customers/update")
                    .filters(f -> f.rewritePath("/customers/update", "/api/customers/update"))
                    .uri("lb://customer-connect"))

            .route("deleteCustomer", r -> r.path("/customers/delete")
                    .filters(f -> f.rewritePath("/customers/delete", "/api/customers/delete"))
                    .uri("lb://customer-connect"))

            .route("getCoupon", r -> r.path("/coupons/**")
                    .filters(f -> f.rewritePath("/coupons/(?<seg>.*)", "/api/coupons/${seg}"))
                    .uri("lb://coupon-hub"))

            .route("getAllCouponsOfCustomer", r -> r.path("/coupons/customer")
                    .filters(f -> f.rewritePath("/coupons/customer", "/api/coupons/customer"))
                    .uri("lb://coupon-hub"))

            .route("getAllCouponsOfCompany", r -> r.path("/coupons/company")
                    .filters(f -> f.rewritePath("/coupons/company", "/api/coupons/company"))
                    .uri("lb://coupon-hub"))

            .route("purchase", r -> r.path("/coupons/purchase/**")
                    .filters(f -> f.rewritePath("/coupons/purchase/(?<seg>.*)", "/api/coupons/purchase/${seg}"))
                    .uri("lb://coupon-hub"))

            .route("deleteCustomerCoupon", r -> r.path("/coupons/deleteCustomerCoupon/**")
                    .filters(f -> f.rewritePath("/coupons/deleteCustomerCoupon/(?<seg>.*)", "/api/coupons/deleteCustomerCoupon/${seg}"))
                    .uri("lb://coupon-hub"))


            .build();
    }

}
