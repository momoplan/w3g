package com.ruyicai.wap.controller;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import com.ruyicai.wap.util.Constants;
import com.ruyicai.wap.util.SelectAllUtil;
import com.ruyicai.wap.vo.WinInfo;
import com.sun.image.codec.jpeg.*;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
@RequestMapping("/trendChart")
@Controller
public class TrendChartController {
	private static final Logger logger = Logger.getLogger(TrendChartController.class);
	@Value("${trendChartPath}")
	String trendChartPath;
	@Autowired
	SelectAllUtil selectAllUtil;
	@Value("${lottery}")
	String lottery;
	private BufferedImage bufferedImage;
	private Graphics2D graphics2d;
	private Color ballColor=Color.RED;
	private int row;//列数
	private int line;//行数
	private int width;//图片的X
	private int height;//图片的Y
	private int WIDTH = 19;
	private int LEFT_WIDTH = 90;
	private int FONT_WIDTH_Y = 14;//文字显示位置+Y坐标
	private int FONT_WIDTH_X = 3;//文字显示位置+X坐标
	private int BALL_WIDTH_X = 0;//球距离左侧位置X
	private int BALL_WIDTH_Y = 0;//球距离顶部位置Y
	private int BALL_WIDTH_XX = BALL_WIDTH_X*2;//球距离两侧位置和XX
	private int BALL_WIDTH_YY = BALL_WIDTH_Y*2;//球距离上下位置和YY
	@RequestMapping("/trendChartDetail")
	public ModelAndView trendChartDetailToGaoPin(
			@RequestParam(value="lotno",defaultValue="") String lotno,
			@RequestParam(value="type",defaultValue="1") String type,
			@RequestParam(value="imgName",defaultValue="1") String imgName
			) throws IOException{
		ModelAndView modelAndView = new ModelAndView();
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		list = getTrendChartDetail(lotno,"50", type);
		if(type.length()>1){
			ballColor = Color.BLUE;
		}else{
			ballColor = Color.RED;
		}
		if(list.size()!=0){
			if(Constants.LOTNO_GDKL10F.equals(lotno)&&"2".equals(type)){
				imgName = "kl10Red2";
			}
			getImage(list,imgName);
		}
		modelAndView.addObject("lotno", lotno);
		modelAndView.addObject("type", type);
		if(Constants.LOTNO_DLC_JX11X5.equals(lotno)){
			modelAndView.setViewName("trendChart_jx11x5");
			return modelAndView;
		}else if(Constants.LOTNO_11YDJ_SD11X5.equals(lotno)){
			modelAndView.setViewName("trendChart_11ydj");
			return modelAndView;
		}else if(Constants.LOTNO_GD11X5.equals(lotno)){
			modelAndView.setViewName("trendChart_gd11x5");
			return modelAndView;
		}else if(Constants.LOTNO_GDKL10F.equals(lotno)){
			modelAndView.setViewName("trendChart_gdkl10");
			return modelAndView;
		}else{
			modelAndView.setViewName("trendChart_index");
			return modelAndView;
		}
	}
	public void trendChartDetail(String lotno,String type,String imgName) throws IOException{
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		list = getTrendChartDetail(lotno,"50", type);
		if(type.length()>1){
			ballColor = Color.BLUE;
		}else{
			ballColor = Color.RED;
		}
		if(list.size()!=0){
			getImage(list,imgName);
		}
	}
	public List<Map<String, Object>> getTrendChartDetail(String lotno,String batchNumber,String type){
		List<Map<String, Object>> trendChartList = new ArrayList<Map<String,Object>>();
		if ("F47102".equals(lotno)) {//七乐彩
			trendChartList = getTrendChartList(lotno, batchNumber, type);
		}
		if ("F47103".equals(lotno)) {//福彩3D
			trendChartList = getTrendChartList(lotno, batchNumber, type);
		}
		if ("F47104".equals(lotno)) {//双色球
			trendChartList = getTrendChartList(lotno, batchNumber, type);
		}
		if ("T01001".equals(lotno)) {//大乐透
			trendChartList = getTrendChartList(lotno, batchNumber, type);
		}
		if ("T01002".equals(lotno)) {//排列三
			trendChartList = getTrendChartList(lotno, batchNumber, type);
		}
		if ("T01007".equals(lotno)) {//时时彩
			trendChartList = getTrendChartList(lotno, batchNumber, type);
		}
		if ("T01009".equals(lotno)) {//七星彩
			trendChartList = getTrendChartList(lotno, batchNumber, type);
		}
		if ("T01010".equals(lotno)) {//江西11选5/多乐彩
			trendChartList = getTrendChartList(lotno, batchNumber, type);
		}
		if ("T01011".equals(lotno)) {//排列五
			trendChartList = getTrendChartList(lotno, batchNumber, type);
		}
		if ("T01012".equals(lotno)) {//十一运夺金/山东11选五
			trendChartList = getTrendChartList(lotno, batchNumber, type);
		}
		if ("T01013".equals(lotno)) {//22选5
			trendChartList = getTrendChartList(lotno, batchNumber, type);
		}
		if ("T01014".equals(lotno)) {//广东11选5
			trendChartList = getTrendChartList(lotno, batchNumber, type);
		}
		if ("T01015".equals(lotno)) {//广东快乐10分
			trendChartList = getTrendChartList(lotno, batchNumber, type);
		}
		return trendChartList;
	}
	public List<Map<String, Object>> getTrendChartList(String lotno,String batchNumber,String type){
		List<Map<String, Object>> trendChartList = new ArrayList<Map<String,Object>>();
		List<WinInfo> winInfos = selectAllUtil.selectTwininfoByLotno(lotno, batchNumber);
		if(winInfos!=null){
			for(int i=0;i<winInfos.size();i++){
				WinInfo winInfo = winInfos.get(i);
				Map<String, Object> map = new HashMap<String, Object>();
				List<String> topList = new ArrayList<String>();
				if(i==0){
					map.put("topName", "期号");
					topList=getTopListByType(type, lotno);
					map.put("topList", topList);
				}else{
					topList=getTopListByType(type, lotno);
				}
				map.put("batchcode", winInfo.getBatchCode());
				String betCode = winInfo.getWinCode();
				String redBlue[] = betCode.split("\\|");
				String str[] = null;
				if(type.length()>1){
					str = redBlue[1].split(",");
				}else{
					str = redBlue[0].split(",");
				}
				List<String> betCodeList = new ArrayList<String>();
				if(str!=null){
					for(int x=0;x<topList.size();x++){
						if("T01009".equals(lotno)||"F47103".equals(lotno)||"T01002".equals(lotno)||"T01011".equals(lotno)){
							String newCode = "";
							int index = Integer.parseInt(type)-1;
							if(str[index]!=null&&str[index].length()==1){
								newCode = "0"+str[index];
							}else{
								newCode = str[index];
							}
							if(topList.get(x).equals(newCode)){
								betCodeList.add(newCode);
							}else{
								betCodeList.add(" ");
							}
						}else{
							int sum =0;
							for(int n=0;n<str.length;n++){
								String newCode = "";
								if(str[n]!=null&&str[n].length()==1){
									newCode = "0"+str[n];
								}else{
									newCode = str[n];
								}
								if(topList.get(x).equals(newCode)){
									betCodeList.add(newCode);
								}else{
									sum++;
								}
							}
							if(sum==str.length){
								betCodeList.add(" ");
							}
							sum=0;
						}
						
					}
				}
				
				map.put("betCodeList", betCodeList);
				trendChartList.add(map);
			}
		}
		return trendChartList;
	}
	public List<String> getTopListByType(String type,String lotno){
		List<String> topList = new ArrayList<String>();
		if ("F47102".equals(lotno)) {//七乐彩
			if("1".equals(type)){
				topList = getTopList(1, 11, true);
			}else if("2".equals(type)){
				topList = getTopList(11, 21, false);
			}else if("3".equals(type)){
				topList = getTopList(21, 31, false);
			}else if("11".equals(type)){
				topList = getTopList(1, 11, true);
			}else if("22".equals(type)){
				topList = getTopList(11, 21, true);
			}else if("33".equals(type)){
				topList = getTopList(21, 31, true);
			}
		}
		if ("F47103".equals(lotno)) {//福彩3D
			topList = getTopList(0, 10, true);
		}
		if ("F47104".equals(lotno)) {//双色球
				if("1".equals(type)){
					topList = getTopList(1, 12, true);
				}else if("2".equals(type)){
					topList = getTopList(12, 23, false);
				}else if("3".equals(type)){
					topList = getTopList(23, 34, false);
				}else if("11".equals(type)){
					topList = getTopList(1, 9, true);
				}else if("22".equals(type)){
					topList = getTopList(9, 17, true);
				}
			}

		if ("T01001".equals(lotno)) {//大乐透
				if("1".equals(type)){
					topList = getTopList(1, 13, true);
				}else if("2".equals(type)){
					topList = getTopList(13, 25, false);
				}else if("3".equals(type)){
					topList = getTopList(25, 36, false);
				}else if("11".equals(type)){
					topList = getTopList(1, 13, true);
				}
			}
		if ("T01002".equals(lotno)) {//排列三
			topList = getTopList(0, 10, true);
		}
		if ("T01007".equals(lotno)) {//时时彩
//			topList = getTopList(0, 10, true);
		}
		if ("T01009".equals(lotno)) {//七星彩
			topList = getTopList(0, 10, true);
		}
		if ("T01010".equals(lotno)) {//江西11选5/多乐彩
			topList = getTopList(1, 12, true);
		}
		if ("T01011".equals(lotno)) {//排列五
			topList = getTopList(0, 10, true);
		}
		if ("T01012".equals(lotno)) {//十一运夺金/山东11选五
			topList = getTopList(1, 12, true);
		}
		if ("T01013".equals(lotno)) {//22选5
			if("1".equals(type)){
				topList = getTopList(1, 12, true);
			}else if("2".equals(type)){
				topList = getTopList(12, 23, false);
			}
		}
		if ("T01014".equals(lotno)) {//广东11选5
			topList = getTopList(1, 12, true);
		}
		if ("T01015".equals(lotno)) {//广东快乐10分
			if("1".equals(type)){
				topList = getTopList(1, 11, true);
			}else if("2".equals(type)){
				topList = getTopList(11, 21, false);
			}
		}
		return topList;
	}
	public List<String> getTopList(int start,int num,boolean addZero){
		List<String> topList = new ArrayList<String>();
		for(int i=start;i<num;i++){
			
			if(addZero&&i>9){
				topList.add(i+"");
			}else if(addZero){
				topList.add("0"+i);
			}else{
				topList.add(i+"");
			}
		}
		return topList;
	}
	@SuppressWarnings({ "restriction", "unchecked" })
	public void getImage(List<Map<String, Object>> list,String imgName){
		if(list!=null){
			List<String> betCodeList = (List<String>)list.get(0).get("betCodeList");
			width = LEFT_WIDTH+WIDTH*(betCodeList.size());
			height = WIDTH+WIDTH*list.size();
			row=list.size();//列数
			line=betCodeList.size();//行数
		}
		bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		graphics2d = bufferedImage.createGraphics();
		graphics2d.setBackground(Color.WHITE);
		graphics2d.clearRect(0, 0, width, height);
		Font font = new Font("sansserif", Font.BOLD, 12);
		graphics2d.setFont(font);
		drawTop(list);
		drawLeft(list);
		drawMain(list);
		drawTable(list);
		graphics2d.dispose();
		bufferedImage.flush();
		//测试
		String filepath = trendChartPath+imgName+".jpeg";
		logger.info("图片存放地址filepath:"+filepath);
		File imgfile = new File(filepath);
		FileOutputStream fos;
		try{
		   fos = new FileOutputStream(imgfile);
		   BufferedOutputStream bos = new BufferedOutputStream(fos);
		   JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos);
		   encoder.encode(bufferedImage);
		   bos.close();
		   logger.info("目录"+filepath+"生成图片成功！");
		}catch (FileNotFoundException e){
		   e.printStackTrace();
		}catch (IOException e){
		   e.printStackTrace();
		} 
//		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(outputStream);
//		JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bufferedImage);
//		param.setQuality(1.0f, false);
//		encoder.setJPEGEncodeParam(param);
//		try {
//		encoder.encode(bufferedImage);
//		}catch(IOException ioe) {
//			ioe.printStackTrace();
//		}
	}
	
	/**
	 * 画头部
	 * @param list
	 */
	@SuppressWarnings("unchecked")
	public void drawTop(List<Map<String, Object>> list){
		List<String> topList = (List<String>) list.get(0).get("topList");
		
		String topName = (String) list.get(0).get("topName");
		graphics2d.setColor(Color.red);
		graphics2d.fillRect(0, 0, width, WIDTH);
		graphics2d.setColor(Color.white);
		graphics2d.drawString(topName, 0, FONT_WIDTH_Y);

		for(int i=0;i<topList.size();i++){
			graphics2d.drawString(topList.get(i), LEFT_WIDTH+i*WIDTH+FONT_WIDTH_X, FONT_WIDTH_Y);
		}
	}
	/**
	 * 画左侧
	 * @param list
	 */
	public void drawLeft(List<Map<String, Object>> list){
		for(int i=0;i<list.size();i++){
			if(i%2==0){
				graphics2d.setColor(Color.WHITE);
			}else{
				graphics2d.setColor(new Color(255, 193, 193));
			}
			
			graphics2d.fillRect(0, WIDTH+i*WIDTH, LEFT_WIDTH, WIDTH+(i+1)*WIDTH);
			graphics2d.setColor(Color.BLACK);
			graphics2d.drawString((String)list.get(i).get("batchcode"), 0,FONT_WIDTH_Y+(i+1)*WIDTH);

		}
	}
	/**
	 * 画主体
	 * @param list
	 */
	@SuppressWarnings("unchecked")
	public void drawMain(List<Map<String, Object>> list){
		for(int m=0;m<list.size();m++){
			List<String> betCodeList = (List<String>) list.get(m).get("betCodeList");

			for(int i=0;i<betCodeList.size();i++){
				if(m%2==0){
					if(i%2==0){
						graphics2d.setColor(Color.WHITE);
					}else{
						graphics2d.setColor(new Color(238, 233, 233));

					}
				}else{
					if(i%2==0){
						graphics2d.setColor(new Color(238, 233, 233));
					}else{
						graphics2d.setColor(Color.WHITE);
					}
				}
				
				graphics2d.fillRect(LEFT_WIDTH+i*WIDTH, WIDTH+m*WIDTH, LEFT_WIDTH+i*WIDTH, WIDTH+m*WIDTH);
				String betCode = betCodeList.get(i);
				if(!" ".equals(betCode)){
					drawBall(LEFT_WIDTH+i*WIDTH, WIDTH+m*WIDTH, WIDTH, WIDTH);
				}
				graphics2d.setColor(Color.WHITE);
				graphics2d.drawString(betCodeList.get(i), LEFT_WIDTH+i*WIDTH+FONT_WIDTH_X, WIDTH+m*WIDTH+FONT_WIDTH_Y);

			}
		}
		
	}
	/**
	 * 画表格
	 * @param list
	 */
	public void drawTable(List<Map<String, Object>> list){
		graphics2d.setColor(Color.GRAY);
		for(int i=0;i<line;i++){
			Line2D.Float line = new Line2D.Float(LEFT_WIDTH+i*WIDTH, WIDTH, LEFT_WIDTH+i*WIDTH, height);
			graphics2d.draw(line);
		}
		for(int i=0;i<row;i++){
			Line2D.Float line = new Line2D.Float(LEFT_WIDTH, WIDTH+i*WIDTH, width,WIDTH+i*WIDTH);
			graphics2d.draw(line);
		}

	}
	/**
	 * 画球
	 * @param x
	 * @param y
	 * @param xx
	 * @param yy
	 */
	public void drawBall(int x,int y,int xx,int yy){
		graphics2d.setColor(ballColor);
		graphics2d.fillOval(x+BALL_WIDTH_X, y+BALL_WIDTH_Y, xx-BALL_WIDTH_XX, yy-BALL_WIDTH_YY);
	}
	public void trendChartSSQ() throws IOException{
		trendChartDetail("F47104", "1","ssqRed1");
		trendChartDetail("F47104", "2","ssqRed2");
		trendChartDetail("F47104", "3","ssqRed3");
		trendChartDetail("F47104", "11","ssqBlue1");
		trendChartDetail("F47104", "22","ssqBlue2");
		logger.info("双色球走势图生成！");
	}
	public void trendChartQLC() throws IOException{
		trendChartDetail("F47102", "1","qlcRed1");
		trendChartDetail("F47102", "2","qlcRed2");
		trendChartDetail("F47102", "3","qlcRed3");
		trendChartDetail("F47102", "11","qlcBlue1");
		trendChartDetail("F47102", "22","qlcBlue2");
		trendChartDetail("F47102", "33","qlcBlue3");
		logger.info("七乐彩走势图生成！");
	}
	public void trendChartDLT() throws IOException{
		trendChartDetail("T01001", "1","dltRed1");
		trendChartDetail("T01001", "2","dltRed2");
		trendChartDetail("T01001", "3","dltRed3");
		trendChartDetail("T01001", "11","dltBlue1");
		trendChartDetail("T01001", "22","dltBlue2");
		logger.info("大乐透走势图生成！");
	}
	public void trendChart3D() throws IOException{
		trendChartDetail("F47103", "1","3dRed1");
		trendChartDetail("F47103", "2","3dRed2");
		trendChartDetail("F47103", "3","3dRed3");
		logger.info("福彩3D走势图生成！");
	}
	
	public void trendChartPLS() throws IOException{
		trendChartDetail("T01002", "1","plsRed1");
		trendChartDetail("T01002", "2","plsRed2");
		trendChartDetail("T01002", "3","plsRed3");
		logger.info("排列三走势图生成！");
	}
	
	public void trendChartPLW() throws IOException{
		trendChartDetail("T01011", "1","plwRed1");
		trendChartDetail("T01011", "2","plwRed2");
		trendChartDetail("T01011", "3","plwRed3");
		trendChartDetail("T01011", "4","plwRed4");
		trendChartDetail("T01011", "5","plwRed5");
		logger.info("排列五走势图生成！");
	}
	public void trendChartQXC() throws IOException{
		trendChartDetail("T01009", "1","qxcRed1");
		trendChartDetail("T01009", "2","qxcRed2");
		trendChartDetail("T01009", "3","qxcRed3");
		trendChartDetail("T01009", "4","qxcRed4");
		trendChartDetail("T01009", "5","qxcRed5");
		trendChartDetail("T01009", "6","qxcRed6");
		trendChartDetail("T01009", "7","qxcRed7");
		logger.info("七星彩走势图生成！");
	}
	public void trendChart22X5() throws IOException{
		trendChartDetail("T01013", "1","22x5Red1");
		trendChartDetail("T01013", "2","22x5Red2");
		logger.info("22选5走势图生成！");
	}
	public void trendChartDLC() throws IOException{
		trendChartDetail("T01010", "1","dlcRed1");
		logger.info("江西11选5/多乐彩走势图生成！");
	}
	public void trendChart11YDJ() throws IOException{
		trendChartDetail("T01012", "1","11ydjRed1");
		logger.info("十一运夺金/山东11选五走势图生成！");
	}
	public void trendChartGD11X5() throws IOException{
		trendChartDetail("T01014", "1","gd11x5Red1");
		logger.info("广东11选5走势图生成！");
	}
	@PostConstruct
	public void init() throws IOException{
		trendChartSSQ();
		trendChart22X5();
		trendChart3D();
		trendChartDLC();
		trendChartDLT();
		trendChartPLS();
		trendChartPLW();
		trendChartQLC();
		trendChartQXC();
		trendChartSSQ();
	}
	@RequestMapping("/trendChartIndex")
	public ModelAndView trendChartIndex(
			@RequestParam(value="lotno" ,defaultValue="") String lotno,
			@RequestParam(value="type" ,defaultValue="") String type,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("lotno", lotno);
		modelAndView.addObject("type", type);
		WinInfo winInfo = selectAllUtil.selectTwininfoByLotno(lotno, "1").get(0);
		modelAndView.addObject("winInfo", winInfo);
		if(Constants.LOTNO_SSQ.equals(lotno)) modelAndView.setViewName("trendChart_ssq");
		if(Constants.LOTNO_FC3D.equals(lotno)) modelAndView.setViewName("trendChart_fc3d");
		if(Constants.LOTNO_QLC.equals(lotno)) modelAndView.setViewName("trendChart_qlc");
		if(Constants.LOTNO_11YDJ_SD11X5.equals(lotno)) modelAndView.setViewName("trendChart_11ydj");
		if(Constants.LOTNO_22X5.equals(lotno)) modelAndView.setViewName("trendChart_22x5");
		if(Constants.LOTNO_DLC_JX11X5.equals(lotno)) modelAndView.setViewName("trendChart_jx11x5");
		if(Constants.LOTNO_DLT.equals(lotno)) modelAndView.setViewName("trendChart_dlt");
		if(Constants.LOTNO_GD11X5.equals(lotno)) modelAndView.setViewName("trendChart_gd11x5");
		if(Constants.LOTNO_GDKL10F.equals(lotno)) modelAndView.setViewName("trendChart_gdkl10");
		if(Constants.LOTNO_PL3.equals(lotno)) modelAndView.setViewName("trendChart_pl3");
		if(Constants.LOTNO_PL5.equals(lotno)) modelAndView.setViewName("trendChart_pl5");
		if(Constants.LOTNO_QXC.equals(lotno)) modelAndView.setViewName("trendChart_qxc");
		if(Constants.LOTNO_SSC.equals(lotno)) modelAndView.setViewName("trendChart_ssc");
		return modelAndView;
		
	}
}
