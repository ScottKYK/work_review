package com.example.work_demo1.controller;

import com.example.work_demo1.pojo.SystemUser;
import com.example.work_demo1.request.Request;
import com.example.work_demo1.service.SystemUserService;
import com.example.work_demo1.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class LoginController {
    @Autowired
    TokenService tokenService;
    @Autowired
    SystemUserService systemUserService;

    @PostMapping("/login")
    @Operation(
            description = "Login",
            responses = {
                    @ApiResponse(responseCode = "401",
                            description = "帳號密碼錯誤",
                            content = @Content(//Request內容
                                    mediaType = "application/json",
                                    examples = {@ExampleObject(
                                            value = "{\"code\" : 401, \"Status\" : \"帳號密碼錯誤\"}"
                                    )}
                            )),
                    @ApiResponse(
                            responseCode = "500",
                            description = "系統錯誤",
                            content = @Content(//Request內容
                                    mediaType = "application/json",
                                    examples = {@ExampleObject(
                                            value = "{\"code\" : 500, \"Status\" : \"系統錯誤\"}"
                                    )}
                            )
                    ),
                    @ApiResponse(
                            responseCode = "200",
                            description = "成功",
                            content = @Content(//Request內容
                                    mediaType = "application/json",
                                    examples = {@ExampleObject(
                                            value = "{\"code\" : 200, \"Status\" : \"成功\"}"
                                    )}
                            )
                    )
            }

    )
    public ResponseEntity<String> login(@RequestBody Request request) {
        SystemUser user = systemUserService.Login(request.getAccount());
        if (user == null || !user.getPassword().equals(request.getPassword())) { //帳號密碼錯誤
            return ResponseEntity.status(401).build();
        } else if (user.getPassword().equals(request.getPassword()) && user.getAccount().equals(request.getAccount())) {
            String token = tokenService.createToken(request);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.AUTHORIZATION, token);
            System.out.println(headers);
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(
                            "{\"account\":\"" + user.getAccount()
                                    + "\", \"name\":\"" + user.getName() +
                                    "\", \"token\":\"" + token + "\"}");
        } else { // 其他錯誤
            System.out.println("500");
            return ResponseEntity.status(500).build();
        }
    }

    //驗證token是否過期
    @PostMapping("parse_token")
    @ResponseBody
    public ResponseEntity<String> parseToken(@RequestBody String authorization) {
        try{
            Map<String, Object> jwtPayload = tokenService.parseToken(authorization);
            Claims claims = (Claims) jwtPayload.get("claims");
            if (claims.get("sub").equals("Access Token")) {
                return ResponseEntity.ok(claims.get("account").toString());
            } else {
                return ResponseEntity.status(500).build();
            }
        }catch (ExpiredJwtException e){
            //token 超過時間回傳403
            return ResponseEntity.status(403).build();
        }

    }
}
