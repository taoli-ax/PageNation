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
}
