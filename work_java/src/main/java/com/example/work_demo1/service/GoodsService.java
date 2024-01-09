package com.example.work_demo1.service;

import com.example.work_demo1.Dao.GoodsRepository;
import com.example.work_demo1.pojo.Goods;
import com.example.work_demo1.pojo.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@Transactional
public class GoodsService {
    @Autowired
    GoodsRepository goodsRepository;

    // add goods
    public Goods add(Goods goods){
        return goodsRepository.save(goods);
    }

    // Get all goods
    public List<Goods> getAllGoods() {
        return goodsRepository.findAll();
    }

    //Get goods by id
    public Goods getGoodsById(String id) {
        return goodsRepository.findGoodsBy_id(id);
    }

    //delete goods by id
    public int deleteBy_id(String id) {
        return goodsRepository.deleteBy_id(id);
    }
}
