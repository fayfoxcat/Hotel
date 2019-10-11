package org.fox.controller.font;

import org.fox.entity.User;
import org.fox.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    //注册用户
    @RequestMapping(value = "/register",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> register(HttpServletRequest request){
        Map<String,Object> model = new HashMap<String,Object>();

        String userName = (String) request.getParameter("username");
        String password = (String) request.getParameter("password");
        String sex = (String) request.getParameter("sex");
        int age = Integer.decode(request.getParameter("age"));
        String eamil = (String) request.getParameter("email");

        User user = new User();
        user.setUsername(userName);
        user.setPassword(password);
        user.setSex(sex);
        user.setAge(age);
        user.setEmail(eamil);

        int result = userService.insertUser(user);
        if(result == 1){
            model.put("message","注册成功，请登录");
            model.put("success",true);
        }else if (result == 0){
            model.put("message","用户名已存在，请更换");
            model.put("success",false);
        }else{
            model.put("message","注册失败，请稍后重试");
            model.put("success",false);
        }
        return model;
    }

    //校验登录
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> login(HttpServletRequest request){
        Map<String,Object> model = new HashMap<String,Object>();
        //验证码检查
        //和数据库值比对
        String userName = (String) request.getParameter("username");
        String password = (String) request.getParameter("password");
        User userinfo = userService.loginUser(userName);
        if (userinfo!=null){
            if(userinfo.getPassword().equals(password)){
                model.put("success",true);
                model.put("message","登录成功");
                //登录成功将用户信息存放至session中
                request.getSession().setAttribute("userinfo",userinfo);
                model.put("userinfo",userinfo);
            }else{
                model.put("success",false);
                model.put("message","密码错误，请重新输入！");
            }
        }else{
            model.put("success",false);
            model.put("message","账号不存在！");
        }
        return model;
    }

    //返回用户信息
    @RequestMapping(value = "/queryuserinfo",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> queryUserInfo(HttpServletRequest request){
        Map<String,Object> model = new HashMap<String,Object>();
        //验证码检查
        //从session中获取当前用户登录信息
        User userinfo = (User)request.getSession().getAttribute("userinfo");
        if(userinfo != null){
            model.put("success",true);
            model.put("userinfo",userinfo);
        }else{
            model.put("success",false);
            model.put("message","登录信息失效，请重新登录！");
        }
        return model;
    }

    /**
     * 完善用户信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/alertuserinfo",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> alertUserInfo(HttpServletRequest request){
        Map<String,Object> model = new HashMap<String,Object>();
        //从session中获取当前用户登录信息
        User userinfo = (User)request.getSession().getAttribute("userinfo");
        if(userinfo != null){
            try{
                String userName = (String) request.getParameter("username");
                int age = Integer.decode(request.getParameter("age"));
                String phone = (String) request.getParameter("phone");
                String email = (String) request.getParameter("email");
                userinfo.setUsername(userName);
                userinfo.setAge(age);
                userinfo.setPhone(phone);
                userinfo.setEmail(email);
                if(userService.updateUser(userinfo)){
                    model.put("success",true);
                }else{
                    model.put("success",false);
                    model.put("message","保存失败");
                }
            }catch(Exception e){
                model.put("success",false);
                return model;
            }
        }else{
            model.put("success",false);
            model.put("message","登录信息失效，请重新登录！");
        }
        return model;
    }

    /**
     * 修改密码
     * @param request
     * @return
     */
    @RequestMapping(value = "/updatepassword",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> updatePassword(HttpServletRequest request){
        Map<String,Object> model = new HashMap<String,Object>();
        //从session中获取当前用户登录信息
        User userinfo = (User)request.getSession().getAttribute("userinfo");
        if(userinfo != null){
            try{
                String oldPassword = (String) request.getParameter("oldPassword");
                if( oldPassword.equals(userinfo.getPassword())){
                    String newPassword = (String) request.getParameter("newPassword");
                    userinfo.setPassword(newPassword);
                    if(userService.updateUser(userinfo)){
                        model.put("success",true);
                        model.put("message","修改成功，请重新登录");
                    }else{
                        model.put("success",false);
                        model.put("message","未知错误，请重试");
                    }
                }else{
                    model.put("success",false);
                    model.put("message","输入的原密码不正确");
                }
            }catch(Exception e){
                model.put("success",false);
                return model;
            }
        }else{
            model.put("success",false);
            model.put("message","登录信息失效，请重新登录！");
        }
        return model;
    }

    //安全注销
    @RequestMapping(value = "/exit",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> exit(HttpServletRequest request){
        Map<String,Object> model = new HashMap<String,Object>();
        //将登录用户session置空
        request.getSession().setAttribute("userinfo",null);
        if(request.getSession().getAttribute("userinfo")==null){
            model.put("success",true);
            model.put("message","已安全退出");
        }else{
            model.put("success",false);
            model.put("message","未知错误，请稍后刷新重试！");
        }
        return model;
    }
}
