package org.fox.controller.font;

import org.fox.entity.Category;
import org.fox.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/room")
public class RoomShowController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 前台房间类型展示
     * @param request
     * @return
     */
    @RequestMapping(value = "/roomshow",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> RoomShow(HttpServletRequest request){
        Map<String,Object> modelmap = new HashMap<String,Object>();
        List<Category> roomlist = categoryService.SerachAllCategory();
        if (!roomlist.isEmpty()){
            modelmap.put("roomlist",roomlist);
            modelmap.put("success",true);
        }else{
            modelmap.put("success",false);
            modelmap.put("message","拉取类别失败，请稍后重试");
        }
        return modelmap;
    }

    /**
     * 返回轮播图所需图片
     * @param request
     * @return
     */
    @RequestMapping(value = "/shiftimg",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> ShiftImg(HttpServletRequest request){
        Map<String,Object> modelmap = new HashMap<String,Object>();
        List<String> imglist = categoryService.queryAllImg();
        if (!imglist.isEmpty()){
            modelmap.put("imglist",imglist);
            modelmap.put("success",true);
        }else{
            modelmap.put("success",false);
        }
        return modelmap;
    }

    /**
     * 单个房间类型详情
     * @param request
     * @return
     */
    @RequestMapping(value = "/roomdetailsshow",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> RoomDetailsShow(HttpServletRequest request){
        Map<String,Object> modelmap = new HashMap<String,Object>();
        //获取用户选中客房类型，接收其roomCategoryId
        Object object = request.getParameter("roomCategoryId");
        if(object != null){
            int roomCategoryId = Integer.decode(request.getParameter("roomCategoryId"));
            System.out.println(roomCategoryId);
            Category room = categoryService.SerachCategory(roomCategoryId);
            if (room != null){
                modelmap.put("room",room);
                modelmap.put("success",true);
            }else{
                modelmap.put("success",false);
                modelmap.put("message","拉取类别失败，请稍后重试");
            }
        }else{
            modelmap.put("success",false);
            modelmap.put("message","类别获取不存在");
        }
        return modelmap;
    }
}
