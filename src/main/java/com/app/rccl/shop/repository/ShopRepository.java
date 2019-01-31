package com.app.rccl.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ShopRepository<T> extends JpaRepository<T, Long> {

}
