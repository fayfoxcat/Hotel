package org.fox.util;

public class PathUtil {
	/**
	 * 返回项目图片的根路径
	 * @return String
	 */
	public static String getImgBasePath() {
		//获取当前运行环境的系统类型(mac/windows/lunix)
		String os = System.getProperty("os.name");
		String basePath = "";
		//针对不同类型的系统设置不同的存储路径
		if(os.toLowerCase().startsWith("win")) {
			basePath="D:/Users/fox/IdeaProjects/HotelManagement/hotel-web/src/";
		}else {
			basePath="/home/fox/IdeaProjects/HotelManagement/hotel-web/src/";
		}
		//替换路径符号
		return basePath;
	}
	
	/**
	 * 依据不同需求，返回项目图片的子路径
	 * @param CategoryId
	 * @return String
	 */
	public static String getCategoryImagePath(long CategoryId) {
		String imagePath = "/assets/Image/RoomCategory/"+CategoryId+"/";
		return imagePath;
	}
	
}
