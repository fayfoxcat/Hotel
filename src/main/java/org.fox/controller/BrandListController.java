package org.fox.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.fox.entity.Car;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/fox",method=RequestMethod.GET)
public class BrandListController {
	//测试git
	@RequestMapping(value="/carlist",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> carList(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		Car car = new Car();
		car.setId(3);
		car.setName("布加迪");
		car.setCtime(new Date());
		
		List<Car> list = new ArrayList<Car>();
		list.add(car);
		
		modelMap.put("list",list);
		modelMap.put("status", 0);
		return modelMap;
	}
	
	@RequestMapping(value="/addcar",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> addCar(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		String name =(String)request.getParameter("name");
		if(name !=null) {
			modelMap.put("status", 0);
		}else {
			modelMap.put("status", 1);
		}
		System.out.println(name);
		return modelMap;
	}
	
	@RequestMapping(value="/deletecar",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> deleteCar(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		int id = Integer.decode(request.getParameter("id"));
		
		if(id > 0) {
			modelMap.put("status", 0);
		}else {
			modelMap.put("status", 1);
		}
		System.out.println(id);
		return modelMap;
	}
	
}




