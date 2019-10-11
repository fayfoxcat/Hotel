package org.fox.util;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


public class ImageUtil {
	//获取项目根路径
	private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath().substring(1);
	//获取格式化时间对象
	private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	//获取随机数对象
	private static final Random r = new Random();

	/**
	 * 1.将图片存储到项目指定目录下
	 * 2.重命名图片名，并归类
	 * @param thumbnailFile
	 * @param targetAddr
	 * @return
	 */
	public static String ImagePath(MultipartFile thumbnailFile, String targetAddr) throws IOException {
		//使用随机数生成 图片名
		String realFileName = getRandomFileName();
		//获取文件图片扩展名
		String extension = getFileExtension(thumbnailFile.getOriginalFilename());
		//创建图片存储的绝对路径(相对于项目图片存储根路径)
		makeDirPath(targetAddr);
		//图片子路径
		String relativeAddr = targetAddr+realFileName+extension;
		//图片绝对路径（全路径）
		File dest = new File(PathUtil.getImgBasePath()+relativeAddr);
		InputStream  in = null;
		OutputStream out = null;
		try{
			//获取文件输入流
			in = thumbnailFile.getInputStream();
			//获取文件输出流并将文件输出到指定目录
			out = new FileOutputStream(dest);

			byte[] buffer = new byte[10000];
			int len = -1;
			while((len = in.read(buffer)) != -1) {
				out.write(buffer,0,len);
			}
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(in!=null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(out!=null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return '.'+relativeAddr;
	}

	/**
	 * 创建目标路径所涉及的目录，即D:/Users/ProjectDev/image/xxx.jpg,那么Users、ProjectDev、image、将自动创建
	 * @param targetAddr
	 */
	private static void makeDirPath(String targetAddr) {
		String realFileParentPath = PathUtil.getImgBasePath()+targetAddr;
		File dirPath = new File(realFileParentPath);
		//该路径不存在将被创建
		if(!dirPath.exists()) {
			dirPath.mkdirs();
		}
	}

	/**
	 * 获取文件输入流的扩展名
	 * @param fielName
	 * @return String
	 */
	private static String getFileExtension(String fielName) {
		return fielName.substring(fielName.lastIndexOf("."));
	}

	/**
	 * 生成随机文件名，当前的年/月/日/时/分/秒+五位随机数
	 * @return String
	 */
	public static String getRandomFileName() {
		//获取五位随机数
		int rannum = r.nextInt(89999)+10000;
		//将时间格式化为字符串
		String nowTimeStr = sDateFormat.format(new Date());
		//自动转换为String返回
		return nowTimeStr+rannum;
	}
	
	/**
	 * 判断storePath是文件路径还是目录路径
	 * 1.如果是文件路径则删除该文件
	 * 2.如果是目录路径则删除该目录下的所有文件
	 * @param storePath
	 */
	public static void deleteFileOrPath(String storePath) {
		//拼凑出图片的全路径
		File fileOrPath = new File(PathUtil.getImgBasePath()+storePath);
		if(fileOrPath.exists()) {
			//如果是目录
			if(fileOrPath.isDirectory()) {
				//将文件目录转换为数组，并遍历删除
				File files[] = fileOrPath.listFiles();
				for(int i=0;i<files.length;i++) {
					files[i].delete();
				}
			}
			fileOrPath.delete();
		}
	}
}














