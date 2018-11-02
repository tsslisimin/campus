package cn.csjava.campus.user.controller;

import cn.csjava.campus.common.base.AbstractController;
import cn.csjava.campus.common.results.Results;
import cn.csjava.campus.user.controller.vo.UserRequest;
import cn.csjava.campus.user.controller.vo.UserResponse;
import cn.csjava.campus.user.controller.vo.UserStudentResponse;
import cn.csjava.campus.user.services.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * 用户控制
 *
 * @author：yyl .
 * describe:
 * email:zxc7752948@qq.com
 * date: 2018/7/8 0008
 */
@Api(description = "用户")
@RestController
@RequestMapping("user")
public class UserController extends AbstractController {

    @Autowired
    private UserService userService;

    @PostMapping
    public Results createUser(@Valid  UserRequest userRequest, HttpServletRequest request, BindingResult result) {
        String header = request.getHeader("X-Forwarded-For");
        userRequest.setIp(Optional.ofNullable(header).orElse(request.getRemoteAddr()));
        Long id = userService.createUser(userRequest);
        if (null != id && id != 0) {
            return ok(UserResponse.builder().userId(id.toString()).build());
        }
        return fail(500, "登陆失败");
    }


    @GetMapping
    public Results getPatriarchChild(String userId) {
        List<UserStudentResponse> list = userService.getPatriarchChild(userId);
        if ( null != list && list.size() > 0 ) {
            return ok(list);
        }
        return ok(new ArrayList<>());
    }
}
