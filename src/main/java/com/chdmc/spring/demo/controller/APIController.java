package com.chdmc.spring.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.chdmc.spring.demo.Config;
import com.chdmc.spring.demo.bean.BigThingsCollectionOrder;
import com.chdmc.spring.demo.bean.CollectionOrder;
import com.chdmc.spring.demo.bean.Complain;
import com.chdmc.spring.demo.bean.User;
import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*",allowCredentials = "true",allowedHeaders = {"X-Custom-Header"},
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
                result.put("result", user);
            } else {
                result.put("success", false);
                result.put("message", "密码错误");
            }
        }
        return result;
    }

    @RequestMapping("/addCollectionOrder")
    public JSONObject addOrder(String orderMessage, String orderCallNum, String imgUrls, String orderType, String userName) {
        JSONObject result = new JSONObject();

        if (!StringUtils.isEmpty(orderType)) {
            CollectionOrder collectionOrder = new CollectionOrder();
            collectionOrder.setType(orderType);
            collectionOrder.setOrderMessage(orderMessage);
            collectionOrder.setOrderCallNum(orderCallNum);
            collectionOrder.setOwnerId(userName);
            if (!StringUtils.isEmpty(imgUrls)) {
                collectionOrder.setImgUrlList(imgUrls.split(","));
            }
            CollectionOrder order = mongoTemplate.insert(collectionOrder);
            result.put("success", true);
            result.put("result", order);
        } else {
            result.put("success", false);
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


    @RequestMapping("/addComplain")
    public JSONObject addComplain(String type, String ownerId,
                            String imgCloseUrl, String imgRemoteUrl,
                            String imgMoreUrlList,
                            String message) {

        Complain complain = new Complain();
        complain.setType(type);
        complain.setOwnerId(ownerId);
        complain.setMessage(message);
        if (!StringUtils.isEmpty(imgCloseUrl)) {
            complain.setImgCloseUrl(imgCloseUrl);
        }
        if (!StringUtils.isEmpty(imgRemoteUrl)) {
            complain.setImgRemoteUlr(imgRemoteUrl);
        }
        if (!StringUtils.isEmpty(imgMoreUrlList)) {
            complain.setImgMoreUrlList(imgMoreUrlList.split(","));
        }
        complain.setState(0);

        Complain data = mongoTemplate.insert(complain);
        JSONObject result = new JSONObject();
        if (data != null) {
            result.put("success", true);
            result.put("result", data);
        } else {
            result.put("success", false);
        }
        return result;
    }

    @RequestMapping("/getComplain")
    public JSONObject getComplain(String username) {
        JSONObject result = new JSONObject();
        Query query = new Query();
        query.addCriteria(Criteria.where("ownerId").is(username));
        List<Complain> complains = mongoTemplate.find(query, Complain.class);

        if (complains != null && complains.size() > 0) {
            result.put("success", true);
            result.put("result", complains);
        } else {
            result.put("success", false);
        }
        return result;

    }

    @RequestMapping("/sendMessage")
    public void sendMessage(String phoneNumber) {
        DefaultProfile profile =
                DefaultProfile.getProfile("cn-hangzhou", Config.OSS_ACCESS_KEY_ID, Config.OSS_ACCESS_KEY_SECRET);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", "13486091957");
        request.putQueryParameter("SignName", "test");
        request.putQueryParameter("TemplateCode", "[我们是]");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}
