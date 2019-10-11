package org.fox.controller.font;

import org.fox.entity.Order;
import org.fox.entity.User;
import org.fox.service.OrderService;
import org.fox.util.STD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 获取当前登录用户的全部订单信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/orderlist",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> OrderList(HttpServletRequest request){
        Map<String,Object> model = new HashMap<String,Object>();
        //从session中获取当前用户登录信息
        User userinfo = (User)request.getSession().getAttribute("userinfo");
        if(userinfo != null){
            List<Order> orderList = orderService.queryUserOrder(userinfo.getUserId());
            if (!orderList.isEmpty()){
                model.put("success",true);
                model.put("orderlist",orderList);
            }else{
                model.put("success",false);
                model.put("message","当前没用可显示订单，请稍后刷新重试！");
            }
        }else{
            model.put("success",false);
            model.put("message","身份信息失效，请重新登录！");
        }
        return model;
    }
    /**
     * 根据orderNo获取订单详情
     * @param request
     * @return
     */
    @RequestMapping(value = "/orderdetails",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> OrderDetails(HttpServletRequest request){
        Map<String,Object> model = new HashMap<String,Object>();
        //从session中获取当前用户登录信息
        User userinfo = (User)request.getSession().getAttribute("userinfo");
        if(userinfo != null){
            String orderNo = request.getParameter("orderNo");
            Order details = orderService.queryOrder(orderNo);
            if (details != null){
                model.put("success",true);
                model.put("order",details);
            }else{
                model.put("success",false);
                model.put("message","当前没用可显示订单，请稍后刷新重试！");
            }
        }else{
            model.put("success",false);
            model.put("message","身份信息失效，请重新登录！");
        }
        return model;
    }

    /**
     * 获取用户输入，生成新的订单
     * @param request
     * @return
     */
    @RequestMapping(value = "/generateorder",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> GenerateOrder(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        //从session中获取当前用户登录的信息
        User userinfo = (User)request.getSession().getAttribute("userinfo");
        Order order = new Order();
        if(userinfo != null){
            order.setOrderUser(userinfo.getUserId());
            try{
                String userName = request.getParameter("UserName");
                String phone = request.getParameter("phone");
                String orderIdCard = request.getParameter("orderIdCard");
                String orderOther = request.getParameter("orderOther");
                Date liveTime = STD.StringTurnDate(request.getParameter("liveTime"));
                Date leaveTime = STD.StringTurnDate(request.getParameter("leaveTime"));
                int orderCount = Integer.decode(request.getParameter("personCount"));
                int orderPrice = Integer.decode(request.getParameter("orderPrice"));
                int roomCategroyId = Integer.decode(request.getParameter("roomCategoryId"));
                order.setUserName(userName);
                order.setPhone(phone);
                order.setOrderIdCard(orderIdCard);
                order.setOrderOther(orderOther);
                order.setLiveTime(liveTime);
                order.setLeaveTime(leaveTime);
                order.setPersonCount(orderCount);
                order.setOrderPrice(orderPrice);
                boolean od = orderService.addOrder(order,roomCategroyId);
                if (od){
                    modelMap.put("success",true);
                    modelMap.put("message","预约成功，请到我的>>我的订单 查看订单详情");
                }else{
                    modelMap.put("success",false);
                    modelMap.put("message","未知错误，订单提交失败请稍后重试！");
                }
            }catch (Exception e){
                e.getMessage();
                 modelMap.put("success",false);
                modelMap.put("message","信息填写有误！");
                return modelMap;
            }
        }else{
            modelMap.put("success",false);
            modelMap.put("message","请登录后重试！");
        }
        return modelMap;
    }

    /**
     * 删除指定订单号的订单
     * @param request
     * @return
     */
    @RequestMapping(value = "/deleteorder",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> DeleteOrder(HttpServletRequest request){
        Map<String,Object> model = new HashMap<String,Object>();
        //从session中获取当前用户登录信息
        User userinfo = (User)request.getSession().getAttribute("userinfo");
        if(userinfo != null){
            int orderNo = Integer.decode(request.getParameter("orderNo"));
            boolean ifdelete = orderService.deleteOrder(orderNo);
            if (ifdelete){
                model.put("success",true);
                model.put("message","删除成功！");
            }else{
                model.put("success",false);
                model.put("message","订单删除失败，请稍后刷新重试！");
            }
        }else{
            model.put("success",false);
            model.put("message","身份信息验证失败，请重新登录！");
        }
        return model;
    }
}
