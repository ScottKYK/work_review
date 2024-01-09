package com.example.work_demo1.controller;

import com.alibaba.fastjson.JSON;
import com.example.work_demo1.pojo.Goods;
import com.example.work_demo1.pojo.SystemUser;
import com.example.work_demo1.service.GoodsService;
import com.example.work_demo1.service.SystemUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    GoodsService goodsService;
    @Autowired
    SystemUserService userService;

    @Operation(
            description = "新增商品",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "成功",
                            content = @Content(//Request內容
                                    mediaType = "application/json",
                                    examples = {@ExampleObject(
                                            value = "{\"_id\" : 商品代號, \"goods_name\" : \"商品名稱\"}"
                                    )}
                            )),
                    @ApiResponse(
                            responseCode = "403",
                            description = "權限不足"),
                    @ApiResponse(
                            responseCode = "500",
                            description = "系統錯誤")
            }
    )
    @PostMapping("add")
    public ResponseEntity<String> add(@RequestBody Map<String, Object> addGoods) {
        System.out.println(addGoods);
        String id = userService.Login((String) addGoods.get("account")).get_id();
        LocalDateTime now = LocalDateTime.now();
        Goods goods = new Goods(
                (String) addGoods.get("_id"), (String) addGoods.get("name"), id, now, id, now);
        Goods add = goodsService.add(goods);
        return ResponseEntity.ok().body(
                "{\"_id\":\"" + add.get_id() + "\", \"goods_name\":\"" + add.getName() + "\"}");
    }

    @Operation(
            description = "取得所有商品",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "成功",
                            content = @Content(//Request內容
                                    mediaType = "application/json",
                                    examples = {@ExampleObject(
                                            value = "{\"_id\" : 商品代號1, \"goods_name\" : \"商品名稱1\"}"
                                    )}
                            )),
                    @ApiResponse(
                            responseCode = "403",
                            description = "權限不足"),
                    @ApiResponse(
                            responseCode = "500",
                            description = "系統錯誤")
            }
    )
    @GetMapping
    public String getAllGoods() {
        List<Goods> allGoods = goodsService.getAllGoods();
        System.out.println("allGoods" + allGoods);
        String goods = "";
        for (int i = 0; i < allGoods.size(); i++) {
            goods += "{_id: " + allGoods.get(i).get_id() + ", goods_name:" + allGoods.get(i).getName() + ",";
        }
        return JSON.toJSONString(goods);
    }

    @Operation(
            description = "取得指定商品",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "成功",
                            content = @Content(//Request內容
                                    mediaType = "application/json",
                                    examples = {@ExampleObject(
                                            value = "{\"_id\" : 商品代號, \"goods_name\" : \"商品名稱\"}"
                                    )}
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "權限不足"),
                    @ApiResponse(
                            responseCode = "500",
                            description = "系統錯誤")
            }
    )
    @GetMapping("{id}")
    public ResponseEntity<String> getGoodsById(@PathVariable String id) {
        System.out.println(id);
        Goods goods = goodsService.getGoodsById(id);
        System.out.println(goods);
        return ResponseEntity.ok().body(
                "{\"_id\":\"" + goods.get_id() + "\", \"goods_name\":\"" + goods.getName() + "\"}");
    }

    @Operation(
            description = "刪除商品",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "成功"),
                    @ApiResponse(
                            responseCode = "403",
                            description = "權限不足"),
                    @ApiResponse(
                            responseCode = "500",
                            description = "系統錯誤")
            }
    )
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteGoodsById(@PathVariable String id) {
        System.out.println(id);
        int i = goodsService.deleteBy_id(id);
        if (i > 1) {
            return ResponseEntity.status(200).build();
        }else {
            return ResponseEntity.status(500).build();
        }
    }

}
