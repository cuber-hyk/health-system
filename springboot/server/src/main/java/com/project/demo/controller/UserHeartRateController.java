package com.project.demo.controller;

import com.project.demo.controller.base.BaseController;
import com.project.demo.entity.User;
import com.project.demo.entity.UserHeartRate;
import com.project.demo.service.UserHeartRateService;
import com.project.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@Slf4j
@RestController // 注解，表明这是一个controller,不会走视图解析器  而Controller会走视图解析器
@RequestMapping("user_heart_rate")
public class UserHeartRateController extends BaseController<UserHeartRate, UserHeartRateService> {
    /**
     * 服务对象
     */
    @Autowired
    public UserHeartRateController(UserHeartRateService service) {
        setService(service);
    }

    @Autowired
    private UserService userService;

    /**
     * 重写add
     * @return
     */
    // post心率接口
    @PostMapping("/addHeartRate")

    public Map<String, Object> addHeartRate(@RequestBody Map<String, String> data, HttpServletRequest request) throws IOException {
        log.info("[执行添加心率接口]");

        // 获取个字段
        String password = data.get("password");
        String username = data.get("username");
        String heartRate = data.get("heart_rate");

        // 根据用户名和密码查询用户
        Map<String, String> query = new HashMap<>();
        query.put("username", username);
        query.put("password", service.encryption(password));

        List resultList = userService.select(query,new HashMap<>()).getResultList();
        if (resultList.size() == 0) {
            return error(10000,"密码或账户错误, 添心率加失败!");
        }

        // 添加心率
        User user = (User) resultList.get(0);
        UserHeartRate userHeartRate = new UserHeartRate();
        userHeartRate.setUser_id(user.getUserId());
        userHeartRate.setHeart_rate(Integer.parseInt(heartRate));

        String additionalInfo;
        // 判断心率是否过快
        if(Integer.parseInt(heartRate) > 150){
            additionalInfo = "心率过快,请减少运动量";
            userHeartRate.setAdditional_info(additionalInfo);
        }else if(Integer.parseInt(heartRate) < 60){// 判断心率是否过慢
            additionalInfo = "心率过慢";
            userHeartRate.setAdditional_info(additionalInfo);
        }else{
            additionalInfo = "心率正常";
            userHeartRate.setAdditional_info(additionalInfo);
        }

        service.save(userHeartRate);

        return success(1);
    }
}
