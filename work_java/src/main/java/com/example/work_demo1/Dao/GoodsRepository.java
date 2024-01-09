package com.example.work_demo1.Dao;

import com.example.work_demo1.pojo.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GoodsRepository extends JpaRepository<Goods,Long> {

    Goods findGoodsBy_id(String _id);

    int deleteBy_id(String _id);
}
