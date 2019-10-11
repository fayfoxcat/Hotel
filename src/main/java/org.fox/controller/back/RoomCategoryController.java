package org.fox.controller.back;

import org.fox.entity.Room;
import org.fox.service.RoomService;
import org.fox.util.ImageUtil;
import org.fox.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/roomcategory")
public class RoomCategoryController {
    @Autowired
    private RoomService roomService;

    @RequestMapping(value = "/roomlist",method = RequestMethod.POST)
    @ResponseBody
    //@RequestParam("file") MultipartFile file
    public Map<String,Object> RoomList(@RequestParam("file") MultipartFile file){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        System.out.println(file.isEmpty());
        System.out.println(file.getOriginalFilename());
        try {
            //若请求中存在文件流，则取出相关的文件
            if(!file.isEmpty()) {
                String dest = PathUtil.getCategoryImagePath(12);
                String thumbnailAddr = ImageUtil.ImagePath(file, dest);
                System.out.println(thumbnailAddr);
                modelMap.put("success",true);
                modelMap.put("message","上传成功!");
            }else{
                modelMap.put("success",false);
                modelMap.put("message","上传图片不能为空！");
            }
        }catch(Exception e) {
            modelMap.put("success",false);
            modelMap.put("message",e.toString());
            return modelMap;
        }
        return modelMap;
    }

    /**
     * 批量插入数据：临时
     * @param request
     * @return
     */
    @RequestMapping(value = "/createroom",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> CreateRoom(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        Room room = new Room();
            for(int i = 1001;i<=1010;i++){
                String roomNo =String.valueOf(i);
                room.setRoomNo(roomNo);
                room.setRoomCategoryId(4);
                room.setRoomStatus(1);
                boolean b = roomService.addRoom(room);
                if(b){
                    modelMap.put("success",true);
                    modelMap.put("message","成功!");
                }else{
                    modelMap.put("success",false);
                    modelMap.put("message","失败！");
                }
            }
        return modelMap;
    }

}

















