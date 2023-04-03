package com.coh.service;

import com.coh.pojo.Cup;
import com.coh.pojo.PageInfo;

import java.util.List;

public interface CupService {
     List<Cup> findAll();
     /***
      * 分页查询
      * */
     List<Cup> findByPage(PageInfo pageInfo);

     /**
      * 按条件查询
      * @param pageInfo pageInfo对象
      * @param cupBrand 查询条件
      * @param cupName 查询条件
      * @return Cup的集合
      */
     List<Cup> queryByPage(PageInfo pageInfo, String cupBrand, String cupName);
}
