package com.chdmc.spring.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chdmc.spring.demo.bean.User;
import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"},allowCredentials = "true",allowedHeaders = {"X-Custom-Header"},
        maxAge = 3600L, methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.HEAD})
public class APIController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @RequestMapping("/hello")
    public String jsonHello() {
        User user = new User();
        user.setPassWord("sdasf");
        user.setUserName("zhou");
        mongoTemplate.save(user);

        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < 10; i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("sfasdfasf", "sdafasdf");
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();

    }

    @RequestMapping("/login")
    public JSONObject login(String userName, String password) {
        JSONObject result = new JSONObject();

        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is(userName));
        User user = mongoTemplate.findOne(query, User.class);
        if (user == null) {
            result.put("success", false);
            result.put("message", "用户不存在");
        } else {
            if (password != null && password.equals(user.getPassWord())) {
                result.put("success", true);
                result.put("data", user);
            } else {
                result.put("success", false);
                result.put("message", "密码错误");
            }
        }
        return result;
    }

    @RequestMapping("/addUser")
    public JSONObject addUser(String userName, String password, String phoneNumber, String userType) {
        JSONObject result = new JSONObject();

        User user = new User();
        user.setUserName(userName);
        user.setPassWord(password);
        user.setPhoneNumber(phoneNumber);
        user.setUserType(User.USER_TYPE.getUserTyep(userType));
        mongoTemplate.insert(user);

        return result;
    }

    @RequestMapping("/deleteUser")
    public JSONObject deleteUser(String userName, String password) {
        JSONObject result = new JSONObject();

        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is(userName));
        query.addCriteria(Criteria.where("password").is(password));

        DeleteResult deleteResult = mongoTemplate.remove(query, User.class);
        long count = deleteResult.getDeletedCount();
        return result;
    }






}
