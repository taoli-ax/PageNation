package com.coh.service.impl;

import com.coh.dao.CupDao;
import com.coh.pojo.Cup;
import com.coh.pojo.CupExample;
import com.coh.pojo.PageInfo;
import com.coh.service.CupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.annotation.WebServlet;
import java.util.List;
@Service
public class CupServiceImpl implements CupService {
    @Autowired
    private CupDao cupDao;
    public CupServiceImpl(){

    }

    public CupServiceImpl(CupDao cupDao) {
        this.cupDao = cupDao;
    }

    public void setCupDao(CupDao cupDao) {
        this.cupDao = cupDao;
    }

    @Override
    public List<Cup> findAll() {
        return cupDao.selectByExample(null);
    }

    @Override
    public List<Cup> findByPage(PageInfo pageInfo) {
        CupExample cupExample=new CupExample();
        // offset位移 =（当前页-1） * 每页条数
        cupExample.setOffset((long) ((long) (pageInfo.getPageNumber() - 1) *pageInfo.getPageSize()));
        cupExample.setLimit(pageInfo.getPageSize());
        // 获取总行数
        long lines=cupDao.countByExample(null);
        pageInfo.setCountRow((int) lines);
        //总页数
        Integer pages= pageInfo.getCountRow() % pageInfo.getPageSize()==0?pageInfo.getCountRow() / pageInfo.getPageSize():pageInfo.getCountRow() / pageInfo.getPageSize()+1;
        pageInfo.setCountPage(pages);
        //查询example
        List<Cup> cups=cupDao.selectByExample(cupExample);
        return cups;
    }

    @Override
    public List<Cup> queryByPage(PageInfo pageInfo, String cupBrand, String cupName) {

        CupExample cupExample=new CupExample();
        CupExample.Criteria criteria=cupExample.createCriteria();
        if(cupName!=null && !"".equals(cupName)){
            criteria.andNameLike("%"+cupName+"%");
        }
        if(cupBrand!=null && !"".equals(cupBrand)){
            criteria.andBrandLike("%"+cupBrand+"%");
        }
        // 获取总行数
        long lines=cupDao.countByExample(cupExample);
        pageInfo.setCountRow((int) lines);
        //总页数
        Integer pages= pageInfo.getCountRow() % pageInfo.getPageSize()==0?pageInfo.getCountRow() / pageInfo.getPageSize():pageInfo.getCountRow() / pageInfo.getPageSize()+1;
        pageInfo.setCountPage(pages);

        // offset位移 =（当前页-1） * 每页条数
        cupExample.setOffset((long) ((long) (pageInfo.getPageNumber() - 1) *pageInfo.getPageSize()));
        cupExample.setLimit(pageInfo.getPageSize());


        //查询example
        return cupDao.selectByExample(cupExample);

    }
}
