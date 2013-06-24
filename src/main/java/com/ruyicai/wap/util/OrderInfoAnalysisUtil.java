package com.ruyicai.wap.util;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ruyicai.wap.util.bet.LotteryUtil;

@Component
public class OrderInfoAnalysisUtil {
	/**
	 * 解析orderInfo
	 * @param lotNo
	 * @param orderInfo
	 * @return
	 */
	public String orderInfoAnalysis(String lotNo,String orderInfo){
		String betCodeView = "";
		String betType = "";
		if(lotNo!=null&&!"".equals(lotNo)&&Constants.LOTNO_QLC.equals(lotNo)){//七乐彩
			//000101121418202329^_2_200_200!1001*040512131418192021222327282930^_2_200_1287000
			String[] orderInfos = orderInfo.split("!");
			for(String info : orderInfos) {
				String[] infos = info.split("_");
				String betCode = infos[0];
				String[] betCodes = betCode.split("\\^");
				for (String code : betCodes) {
					if (code.substring(0, 2).equals("00")) { //单式
						betCodeView += "玩法:单式<br/>"+LotteryUtil.getDouHaoStringFromStringList(LotteryUtil.getStringListFromZeroString(code.substring(4)))+"<br/>";
					} else if (code.substring(0, 2).equals("10")) { //复式
						betCodeView += "玩法:复式<br/>"+LotteryUtil.getDouHaoStringFromStringList(LotteryUtil.getStringListFromZeroString(code.substring(5)))+"<br/>";
					} else if (code.substring(0, 2).equals("20")) { //胆拖
						String danMa = LotteryUtil.getDouHaoStringFromStringList(LotteryUtil.getStringListFromZeroString(code.substring(4, code.indexOf("*"))));
						String tuoMa = LotteryUtil.getDouHaoStringFromStringList(LotteryUtil.getStringListFromZeroString(code.substring(code.indexOf("*")+1)));
						betCodeView += "玩法:胆拖<br/>"+"胆码:"+danMa+"<br/>拖码:"+tuoMa+"<br/>";
					} 
				}
			}
		}else if(lotNo!=null&&!"".equals(lotNo)&&Constants.LOTNO_FC3D.equals(lotNo)){//福彩3D
			//320109000102030405060708^_1_200_16800
			String[] orderInfos = orderInfo.split("!");
			for(String info : orderInfos) {
				String[] infos = info.split("_");
				String betCode = infos[0];
				if (betCode.substring(0, 2).equals("20")) { //直选复式 20020102^0106^0100^
					String[] codes = betCode.split("\\^");
					String bai = LotteryUtil.removeBetCodeZero(codes[0].substring(6));
					String shi = LotteryUtil.removeBetCodeZero(codes[1].substring(2));
					String ge = LotteryUtil.removeBetCodeZero(codes[2].substring(2));
					bai = LotteryUtil.getDouHaoStringFromStringList(LotteryUtil.getStringListFromString(bai));
					shi = LotteryUtil.getDouHaoStringFromStringList(LotteryUtil.getStringListFromString(shi));
					ge = LotteryUtil.getDouHaoStringFromStringList(LotteryUtil.getStringListFromString(ge));
					betCodeView +="玩法:直选复式<br/>"+ "百位:"+bai+"<br/>十位:"+shi+"<br/>个位:"+ge+"<br/>";
					continue;
				} else {
					String[] codes = betCode.split("\\^");
					for (String code : codes) {
						String play ="0"; //投注类型
						if (code.substring(0, 2).equals("00")) { //单选单式
							play = "DXD";
							betType = "单选单式";
						} else if (code.substring(0, 2).equals("01")) { //组3单式
							play = "Z3D";
							betType = "组3单式";
						} else if (code.substring(0, 2).equals("02")) { //组6单式
							play = "Z6D";
							betType = "组6单式";
						} else if (code.substring(0, 2).equals("31")) { //组3复式
							play = "Z3F";
							betType = "组3复式";
						} else if (code.substring(0, 2).equals("32")) { //组6复式
							play = "Z6F";
							betType = "组6复式";
						} else if (code.substring(0, 2).equals("34")) { //单选单复式
							play = "DXDF";
							betType = "单选单复式";
						}else if (code.substring(0, 2).equals("54")) { //胆拖
							play = "DT";
							betType = "胆拖";
						} else if (code.substring(0, 2).equals("10")) { //直选和值
							play = "ZXHZ";
							betType = "直选和值";
						} else if (code.substring(0, 2).equals("11")) { //组3和值
							play = "Z3HZ";
							betType = "组3和值";
						} else if (code.substring(0, 2).equals("12")) { //组6和值
							play = "Z6HZ";
							betType = "组6和值";
						}
						if (play.trim().equals("DXD") || play.trim().equals("Z3D") || play.trim().equals("Z6D")) { //单式
							betCodeView +="玩法:"+betType+"<br/>"+ LotteryUtil.getDouHaoStringFromStringList(LotteryUtil.getStringListFromString(LotteryUtil.removeBetCodeZero(code.substring(4, code.length()))))+"<br/>";
						} else if (play.trim().equals("Z3F") || play.trim().equals("Z6F") || play.trim().equals("DXDF")) { //复式
							betCodeView +="玩法:"+betType+"<br/>"+LotteryUtil.getDouHaoStringFromStringList(LotteryUtil.getStringListFromString(LotteryUtil.removeBetCodeZero(code.substring(6))))+"<br/>";
						} else if (play.trim().equals("DT")) {
							String danMa = LotteryUtil.getDouHaoStringFromStringList(LotteryUtil.getStringListFromString(LotteryUtil.removeBetCodeZero(code.substring(4, code.indexOf("*")))));
							String tuoMa = LotteryUtil.getDouHaoStringFromStringList(LotteryUtil.getStringListFromString(LotteryUtil.removeBetCodeZero(code.substring(code.indexOf("*") + 1))));
							betCodeView +="玩法:"+betType+"<br/>"+"胆码:"+danMa+"<br/>拖码:"+tuoMa+"<br/>";
						} else if (play.trim().equals("ZXHZ") || play.trim().equals("Z3HZ") || play.trim().equals("Z6HZ")) {
							betCodeView +="玩法:"+betType+"<br/>"+ LotteryUtil.getDouHaoStringFromStringList(LotteryUtil.getStringListFromString(LotteryUtil.removeBetCodeZero(code.substring(4, code.length()))))+"<br/>";
						} 
					}
				} 
			}
		}else if(lotNo!=null&&!"".equals(lotNo)&&Constants.LOTNO_SSQ.equals(lotNo)){//双色球
			//1001*06131521242533~14^_1_200_1400!1001*11131517253133~05^_1_200_1400
			String[] orderInfos = orderInfo.split("!");
			for(String info : orderInfos) {
				String[] infos = info.split("_");
				String betCode = infos[0];
				String[] codes = betCode.split("\\^");
				for (String code : codes) {
					if (code.length() > 4) {
						String play = "0"; //投注类型
						if (code.trim().substring(0, 2).equals("00")) {
							play = "DS"; //单式
							betType = "单式";
						} else if (code.trim().substring(0, 2).equals("10")||code.trim().substring(0, 2).equals("20")
								||code.trim().substring(0, 2).equals("30")) {
							play = "FS"; //复式
							betType = "复式";
						} else if (code.trim().substring(0, 2).equals("40")||code.trim().substring(0, 2).equals("50")) { //胆拖
							play = "DT";
							betType = "胆拖";
						}
						if (play.trim().equals("DS")) { // 单式
							String red = LotteryUtil.getDouHaoStringFromStringList(LotteryUtil.getStringListFromZeroString(code.substring(4, code.indexOf('~')))); 
							String blue = code.substring(code.indexOf('~')+1);
							betCodeView +="玩法:"+betType+"<br/>"+ "红球:"+red+"<br/>蓝球:"+blue+"<br/>";
						} else if(play.trim().equals("FS")) {
							String red = LotteryUtil.getDouHaoStringFromStringList(LotteryUtil.getStringListFromZeroString(code.substring(code.indexOf('*')+1, code.indexOf('~'))));
							String blue = LotteryUtil.getDouHaoStringFromStringList(LotteryUtil.getStringListFromZeroString(code.substring(code.indexOf('~')+1)));
							betCodeView +="玩法:"+betType+"<br/>"+ "红球:"+red+"<br/>蓝球:"+blue+"<br/>";
						} else if (play.trim().equals("DT")) { // 胆拖
							String redDanma = LotteryUtil.getDouHaoStringFromStringList(LotteryUtil.getStringListFromZeroString(code.substring(4, code.indexOf("*"))));
							String redTuoma =LotteryUtil.getDouHaoStringFromStringList(LotteryUtil.getStringListFromZeroString(code.substring(code.indexOf("*")+1, code.indexOf("~"))));
							String blue =LotteryUtil.getDouHaoStringFromStringList(LotteryUtil.getStringListFromZeroString(code.substring(code.indexOf("~")+1))); 
							betCodeView +="玩法:"+betType+"<br/>"+ "红球胆码:"+redDanma+"<br/>红球拖码:"+redTuoma+"<br/>蓝球:"+blue+"<br/>";
						}
					}
				}
			}
			
		}else if(lotNo!=null&&!"".equals(lotNo)&&Constants.LOTNO_DLT.equals(lotNo)){//大乐透
			//12 21 30$11 13 18 19 22-04$02 10 11_2_200_6000
			String[] orderInfos = orderInfo.split("!");
			for(String info : orderInfos) {
				String[] infos = info.split("_");
				String betCode = infos[0];
				String[] codes = betCode.split(";");
				for (String code : codes) {
					if (code.indexOf("-") > -1) { //单式或复式或胆拖
						if (code.indexOf("$") > -1) { //胆拖
							betType = "胆拖";
							String[] qianHou = code.split("-");
							String qianQuDanMa = ""; //前区胆码
							String qianQuTuoMa = ""; //前区拖码
							String houQuDanMa = ""; //后区胆码
							String houQuTuoMa = ""; //后区拖码
							//前区
							if (qianHou[0].indexOf("$") > -1) {
								String[] qianQu = qianHou[0].split("\\$");
								qianQuDanMa = qianQu[0].replaceAll(" ", ","); //前区胆码
								qianQuTuoMa = qianQu[1].replaceAll(" ", ","); //前区拖码
							} else {
								qianQuTuoMa = qianHou[0].replace(" ", ","); //前区拖码
							}
							//后区
							if (qianHou[1].indexOf("$") > -1) {
								String[] houQu = qianHou[1].split("\\$");
								houQuDanMa = houQu[0].replaceAll(" ", ","); //后区胆码
								houQuTuoMa = houQu[1].replaceAll(" ", ","); //后区拖码
							} else {
								houQuTuoMa = qianHou[1].replaceAll(" ", ","); //后区拖码
							}
							StringBuffer betCodeBuffer = new StringBuffer();
							if (qianQuDanMa!=null&&!"".equals(qianQuDanMa)) {
								betCodeBuffer.append("前区胆码:"+qianQuDanMa+"<br/>");
							}
							betCodeBuffer.append("前区拖码:"+qianQuTuoMa+"<br/>");
							if (houQuDanMa!=null&&!"".equals(houQuDanMa)) {
								betCodeBuffer.append("后区胆码:"+houQuDanMa+"<br/>");
							}
							betCodeBuffer.append("后区拖码:"+houQuTuoMa);
							betCodeView += "玩法:"+betType+"<br/>" + betCodeBuffer.toString()+"<br/>";
						} else { //单式或复式
							String[] qianHou = code.split("-");
							String[] qian = qianHou[0].split(" ");
							String[] hou = qianHou[1].split(" ");
							if (qian.length==5 && hou.length==2) {
								betType = "单式";
							} else {
								betType = "复式";
							}
							betCodeView += "玩法:"+betType+"<br/>" + qianHou[0].replaceAll(" ", ",")+"+"+qianHou[1].replaceAll(" ", ",")+"<br/>";
						}
					} else { //生肖乐
						String[] split3 = code.split(" ");
						if (split3.length == 2) {
							betType = "12选2单式";
						} else {
							betType = "12选2复式";
						}
						betCodeView +="玩法:"+betType+"<br/>" +code.replaceAll(" ", ",")+"<br/>";
					}
				}
			}
			
		}else if(lotNo!=null&&!"".equals(lotNo)&&Constants.LOTNO_PL3.equals(lotNo)){
			//S1|11_2_200_13800!1|4,16,5_2_200_400
			String[] orderInfos = orderInfo.split("!");
			for(String info : orderInfos) {
				String[] infos = info.split("_");
				String betCode = infos[0];
				String[] codes = betCode.split(";");
				
				for (String code : codes) {
					String[] split3 = code.split("\\|");
					if (split3[0].equals("1")) { //直选
						betType = "直选";
					} else if (split3[0].equals("6")) { //组选
						betType = "组选";
					} else if (split3[0].equals("S1")) { //直选和值
						betType = "直选和值";
					} else if (split3[0].equals("S9")) { //组选和值
						betType = "组选和值";
					} else if (split3[0].equals("S3")) { //组3和值
						betType = "组3和值";
					} else if (split3[0].equals("S6")) { //组6和值
						betType = "组6和值";
					} else if (split3[0].equals("F3")) { //组3包号
						betType = "组3包号";
					} else if (split3[0].equals("F6")) { //组6包号
						betType = "组6包号";
					} else if (split3[0].equals("Z3")) { //组3复式
						betType = "组3复式";
					} else if (split3[0].equals("Z6")) { //组6复式
						betType = "组6复式";
					}
					betCodeView += "玩法:"+betType+"<br/>" +split3[1]+"<br/>";
				}
				
			}
			
		}else if(lotNo!=null&&!"".equals(lotNo)&&Constants.LOTNO_PL5.equals(lotNo)){
			//4,5,9,3,4;_1_200_200!3,8,5,1,1;_1_200_200!8,9,1,6,4;_1_200_200!1,1,6,5,6;_1_200_200!0,6,7,1,8;_1_200_200
			String[] orderInfos = orderInfo.split("!");
			for(String info : orderInfos) {
				String[] infos = info.split("_");
				String betCode = infos[0];
				String[] codes = betCode.split(";");
				for (String code : codes) {
					if (code.indexOf(",") > -1) {
						boolean isD = true;
						String[] split3 = code.split(",");
						for (int i = 0; i < split3.length; i++) {
							if (split3[i].length() > 1) {
								isD = false;
								break;
							}
						}
						if (isD) {
							betType = "单式";
						} else {
							betType = "复式";
						}
					} else {
						betType = "单式";
					}
					betCodeView += "玩法:"+betType+"<br/>" + code+"<br/>";
				}
			}
			
		}else if(lotNo!=null&&!"".equals(lotNo)&&Constants.LOTNO_QXC.equals(lotNo)){
			//3,6,6,5,7,4,7;_1_200_200!9,3,1,9,2,5,9;_1_200_200!9,9,0,4,4,1,5;_1_200_200!7,1,3,5,8,9,1;_1_200_200!5,8,9,0,9,4,7;_1_200_200
			String[] orderInfos = orderInfo.split("!");
			for(String info : orderInfos) {
				String[] infos = info.split("_");
				String betCode = infos[0];
				String[] codes = betCode.split(";");
				for (String code : codes) {
					if (code.indexOf(",") > -1) {
						boolean isD = true;
						String[] split3 = code.split(",");
						for (int i = 0; i < split3.length; i++) {
							if (split3[i].length() > 1) {
								isD = false;
								break;
							}
						}
						if (isD) {
							betType = "单式";
						} else {
							betType = "复式";
						}
					} else {
						betType = "单式";
					}
					betCodeView += "玩法:"+betType+"<br/>" +code+"<br/>";
				}
			}
		}else if(lotNo!=null&&!"".equals(lotNo)&&Constants.LOTNO_22X5.equals(lotNo)){
			//502@20120508|2|424|1^20120508|2|425|2^_1_200_200
			String[] orderInfos = orderInfo.split("!");
			for(String info : orderInfos) {
				String[] infos = info.split("_");
				String betCode = infos[0];
				
				String[] codes = betCode.split("@");
				if (codes[0].equals("0")) { //单式
					betType = "单式";
					String codeStr = codes[1].substring(0, codes[1].length()-1);
					betCodeView +="玩法:"+betType+"<br/>"+ LotteryUtil.getDouHaoStringFromStringList(LotteryUtil.getStringListFromZeroString(codeStr))+"<br/>";
				} else if (codes[0].equals("1")) { //复式
					betType = "复式";
					String codeStr = codes[1].substring(0, codes[1].length()-1);
					betCodeView +="玩法:"+betType+"<br/>"+ LotteryUtil.getDouHaoStringFromStringList(LotteryUtil.getStringListFromZeroString(codeStr))+"<br/>";
				} else if (codes[0].equals("2")) { //胆拖
					betType = "胆拖";
					String danMa = codes[1].substring(0, codes[1].indexOf("*"));
					String tuoMa = codes[1].substring(codes[1].indexOf("*")+1, codes[1].length()-1);
					betCodeView +="玩法:"+betType+"<br/>"+ "胆码:"+LotteryUtil.getDouHaoStringFromStringList(LotteryUtil.getStringListFromZeroString(danMa))
							+"<br/>拖码:"+LotteryUtil.getDouHaoStringFromStringList(LotteryUtil.getStringListFromZeroString(tuoMa))+"<br/>";
				} 
			}
		}else if(lotNo!=null&&!"".equals(lotNo)&&Constants.LOTNO_SSC.equals(lotNo)){
			//5D|012345678,123456789,125679,01349,0126_2_200_1944000
			String[] orderInfos = orderInfo.split("!");
			for(String info : orderInfos) {
				String[] infos = info.split("_");
				String betCode = infos[0];
				String[] codes = betCode.split(";");
				for (String code : codes) {
					String[] split3 = code.split("\\|");
					if (split3[0].equals("5D")) { //五星
						betType = "五星";
					} else if (split3[0].equals("3D")) { //三星
						betType = "三星";
					} else if (split3[0].equals("2D")) { //二星
						betType = "二星";
					} else if (split3[0].equals("1D")) { //一星
						betType = "一星";
					} else if (split3[0].equals("5F")) { //五星复选
						betType = "五星复选";
					} else if (split3[0].equals("5T")) { //五星通选
						betType = "五星通选";
					} else if (split3[0].equals("3F")) { //三星复选
						betType = "三星复选";
					} else if (split3[0].equals("2F")) { //二星复选
						betType = "二星复选";
					} else if (split3[0].equals("H2")) { //二星和值
						betType = "二星和值";
					} else if (split3[0].equals("S2")) { //二星组选和值
						betType =  "二星组选和值";
					} else if (split3[0].equals("DD")) { //大小单双
						betType = "大小单双";
						String shi = split3[1].substring(0, 1); //大小
						String ge = split3[1].substring(1, 2); //单双
						String shiStr = "";
						if (shi.equals("2")) {
							shiStr = "大";
						} else if (shi.equals("1")) {
							shiStr = "小";
						} else if (shi.equals("5")) {
							shiStr = "单";
						} else if (shi.equals("4")) {
							shiStr = "双";
						}
						String geStr = "";
						if (ge.equals("2")) {
							geStr = "大";
						} else if (ge.equals("1")) {
							geStr = "小";
						} else if (ge.equals("5")) {
							geStr = "单";
						} else if (ge.equals("4")) {
							geStr = "双";
						}
						betCodeView+= "玩法:"+betType+"<br/>"+"十位:"+shiStr+"<br/>个位:"+geStr+"<br/>";
						continue;
					} else if (split3[0].equals("Z2")) { //二星组选
						betType = "二星组选";

					} else if (split3[0].equals("F2")) { //二星组选复式
						betType = "二星组选复式";
					}else if(split3[0].equals("3")){
						betType = "三星组三";
						betCodeView+= "玩法:"+betType+"<br/>"+LotteryUtil.getDouHaoStringFromStringList(LotteryUtil.getStringListFromString(split3[1]))+"<br/>";
						continue;
					}else if(split3[0].equals("6")){
						betType = "三星组六";
						betCodeView+= "玩法:"+betType+"<br/>"+LotteryUtil.getDouHaoStringFromStringList(LotteryUtil.getStringListFromString(split3[1]))+"<br/>";
						continue;
					}else if(split3[0].equals("Z3F")){
						betType = "三星组三";
						betCodeView+= "玩法:"+betType+"<br/>"+LotteryUtil.getDouHaoStringFromStringList(LotteryUtil.getStringListFromString(split3[1]))+"<br/>";
						continue;
					}else if(split3[0].equals("Z6F")){
						betType = "三星组六";
						betCodeView+= "玩法:"+betType+"<br/>"+LotteryUtil.getDouHaoStringFromStringList(LotteryUtil.getStringListFromString(split3[1]))+"<br/>";
						continue;
					}
					betCodeView+="玩法:"+betType+"<br/>"+split3[1]+"<br/>";
				}
			}
		}else if(lotNo!=null&&!"".equals(lotNo)&&Constants.LOTNO_DLC_JX11X5.equals(lotNo)){
			//R8|01 02 06 07 08 09 10 11_1_200_200!R8|02 04 05 06 07 08 10 11_1_200_200!R8|01 02 03 04 05 07 08 09_1_200_200
			String[] orderInfos = orderInfo.split("!");
			for(String info : orderInfos) {
				String[] infos = info.split("_");
				String betCode = infos[0];
				String[] codes = betCode.split(";");
				
				for (String code : codes) {
					if (betCode.indexOf("$") > -1) { //胆拖
						if (betCode.indexOf("|") > -1) {
							String[] split3 = code.split("\\|");
							if (split3[0].equals("R2")) { //任选二胆拖
								betType = "任选二胆拖";
							} else if (split3[0].equals("R3")) { //任选三胆拖
								betType = "任选三胆拖";
							} else if (split3[0].equals("R4")) { //任选四胆拖
								betType = "任选四胆拖";
							} else if (split3[0].equals("R5")) { //任选五胆拖
								betType = "任选五胆拖";
							} else if (split3[0].equals("R6")) { //任选六胆拖
								betType = "任选六胆拖";
							} else if (split3[0].equals("R7")) { //任选七胆拖
								betType = "任选七胆拖";
							} else if (split3[0].equals("R8")) { //任选八胆拖
								betType = "任选八胆拖";
							} else if (split3[0].equals("Q2")) { //选前二直选胆拖
								betType = "选前二直选胆拖";
							} else if (split3[0].equals("Q3")) { //选前三直选胆拖
								betType = "选前三直选胆拖";
							} else if (split3[0].equals("Z2")) { //选前二组选胆拖
								betType = "选前二组选胆拖";
							} else if (split3[0].equals("Z3")) { //选前三组选胆拖
								betType = "选前三组选胆拖";
							} 
							String[] danTuo = split3[1].split("\\$");
							betCodeView += "玩法:"+betType+"<br/>"+"胆码:"+danTuo[0].replaceAll(" ", ",")+" <br/>拖码:"+danTuo[1].replaceAll(" ", ",")+"<br/>";
						}
					} else {
						if (betCode.indexOf("|") > -1) {
							String[] split3 = code.split("\\|");
							if (split3[0].equals("R1")) { //任选一
								betType = "任选一";
							} else if (split3[0].equals("R2")) { //任选二
								betType = "任选二";
							} else if (split3[0].equals("R3")) { //任选三
								betType =  "任选三";
							} else if (split3[0].equals("R4")) { //任选四
								betType = "任选四";
							} else if (split3[0].equals("R5")) { //任选五
								betType = "任选五";
							} else if (split3[0].equals("R6")) { //任选六
								betType = "任选六";
							} else if (split3[0].equals("R7")) { //任选七
								betType = "任选七";
							} else if (split3[0].equals("R8")) { //任选八
								betType = "任选八";
							} else if (split3[0].equals("Q2")) { //选前二直选
								betType = "选前二直选";
								String[] split = split3[1].split("\\,");
								String first = split[0].replaceAll(" ", ",");
								String second = split[1].replaceAll(" ", ",");
								betCodeView+= "玩法:"+betType+"<br/>第一位:"+first+"<br/>第二位:"+second+"<br/>";
								continue;
							} else if (split3[0].equals("Q3")) { //选前三直选
								betType = "选前三直选";
								String[] split = split3[1].split("\\,");
								String first = split[0].replaceAll(" ", ",");
								String second = split[1].replaceAll(" ", ",");
								String third = split[2].replaceAll(" ", ",");
								betCodeView+= "玩法:"+betType+"<br/>第一位:"+first+"<br/>第二位:"+second+"<br/>第三位:"+third+"<br/>";
								continue;

							} else if (split3[0].equals("Z2")) { //选前二组选
								betType = "选前二组选";
							} else if (split3[0].equals("Z3")) { //选前三组选
								betType = "选前三组选";
							} 
							betCodeView+= "玩法:"+betType+"<br/>"+split3[1].replaceAll(" ", ",")+"<br/>";
						}
					}
				}
			}
		}else if(lotNo!=null&&!"".equals(lotNo)&&Constants.LOTNO_11YDJ_SD11X5.equals(lotNo)){
			//111@0211^_1_200_200!102@*020411^_1_200_600
			String[] orderInfos = orderInfo.split("!");
			for(String info : orderInfos) {
				String[] infos = info.split("_");
				String betCode = infos[0];
				
				String[] codes = betCode.split("@"); //102@*02040511^
				String wanfa = codes[0]; //玩法
				if (wanfa!=null && wanfa.trim().equals("101")) { //任选一复式
					betType = "任选一复式";
					betCodeView += "玩法:"+betType+"<br/>"+convertBetCodeFor11YDJ_FS(codes[1])+"<br/>";
				} else if (wanfa!=null && wanfa.trim().equals("102")) { //任选二复式
					betType =  "任选二复式";
					betCodeView += "玩法:"+betType+"<br/>"+convertBetCodeFor11YDJ_FS(codes[1])+"<br/>";
				} else if (wanfa!=null && wanfa.trim().equals("103")) { //任选三复式
					betType = "任选三复式";
					betCodeView+="玩法:"+betType+"<br/>"+convertBetCodeFor11YDJ_FS(codes[1])+"<br/>";
				} else if (wanfa!=null && wanfa.trim().equals("104")) { //任选四复式
					betType = "任选四复式";
					betCodeView += "玩法:"+betType+"<br/>"+convertBetCodeFor11YDJ_FS(codes[1])+"<br/>";
				} else if (wanfa!=null && wanfa.trim().equals("105")) { //任选五复式
					betType = "任选五复式";
					betCodeView+="玩法:"+betType+"<br/>"+convertBetCodeFor11YDJ_FS(codes[1])+"<br/>";
				} else if (wanfa!=null && wanfa.trim().equals("106")) { //任选六复式
					betType = "任选六复式";
					betCodeView +="玩法:"+betType+"<br/>"+convertBetCodeFor11YDJ_FS(codes[1])+"<br/>";
				} else if (wanfa!=null && wanfa.trim().equals("107")) { //任选七复式
					betType = "任选七复式";
					betCodeView += "玩法:"+betType+"<br/>"+convertBetCodeFor11YDJ_FS(codes[1])+"<br/>";
				}else if (wanfa!=null && wanfa.trim().equals("166")) { //任选七复式
					betType = "任选八复式";
					betCodeView += "玩法:"+betType+"<br/>"+convertBetCodeFor11YDJ_FS(codes[1])+"<br/>";
				}else if (wanfa!=null && wanfa.trim().equals("108")) { //选前二组选复式
					betType = "选前二组选复式";
					betCodeView+="玩法:"+betType+"<br/>"+convertBetCodeFor11YDJ_FS(codes[1])+"<br/>";
				} else if (wanfa!=null && wanfa.trim().equals("109")) { //选前三组选复式
					betType = "选前三组选复式";
					betCodeView+="玩法:"+betType+"<br/>"+convertBetCodeFor11YDJ_FS(codes[1])+"<br/>";
				} else if (wanfa!=null && wanfa.trim().equals("111")) { //任选二单式
					betType = "任选二单式";
					betCodeView +="玩法:"+betType+"<br/>"+convertBetCodeFor11YDJ_DS(codes[1])+"<br/>";
				} else if (wanfa!=null && wanfa.trim().equals("112")) { //任选三单式
					betType = "任选三单式";
					betCodeView +="玩法:"+betType+"<br/>"+convertBetCodeFor11YDJ_DS(codes[1])+"<br/>";
				} else if (wanfa!=null && wanfa.trim().equals("113")) { //任选四单式
					betType = "任选四单式";
					betCodeView +="玩法:"+betType+"<br/>"+convertBetCodeFor11YDJ_DS(codes[1])+"<br/>";
				} else if (wanfa!=null && wanfa.trim().equals("114")) { //任选五单式
					betType = "任选五单式";
					betCodeView +="玩法:"+betType+"<br/>"+convertBetCodeFor11YDJ_DS(codes[1])+"<br/>";
				} else if (wanfa!=null && wanfa.trim().equals("115")) { //任选六单式
					betType = "任选六单式";
					betCodeView +="玩法:"+betType+"<br/>"+convertBetCodeFor11YDJ_DS(codes[1])+"<br/>";
				} else if (wanfa!=null && wanfa.trim().equals("116")) { //任选七单式
					betType = "任选七单式";
					betCodeView +="玩法:"+betType+"<br/>"+convertBetCodeFor11YDJ_DS(codes[1])+"<br/>";
				} else if (wanfa!=null && wanfa.trim().equals("117")) { //任选八单式
					betType = "任选八单式";
					betCodeView +="玩法:"+betType+"<br/>"+convertBetCodeFor11YDJ_DS(codes[1])+"<br/>";
				} else if (wanfa!=null && wanfa.trim().equals("121")) { //任选二胆拖
					betType = "任选二胆拖";
					betCodeView +="玩法:"+betType+"<br/>"+convertBetCodeFor11YDJ_DT(codes[1])+"<br/>";
				} else if (wanfa!=null && wanfa.trim().equals("122")) { //任选三胆拖
					betType = "任选三胆拖";
					betCodeView +="玩法:"+betType+"<br/>"+convertBetCodeFor11YDJ_DT(codes[1])+"<br/>";
				} else if (wanfa!=null && wanfa.trim().equals("123")) { //任选四胆拖
					betType =  "任选四胆拖";
					betCodeView +="玩法:"+betType+"<br/>"+convertBetCodeFor11YDJ_DT(codes[1])+"<br/>";
				} else if (wanfa!=null && wanfa.trim().equals("124")) { //任选五胆拖
					betType = "任选五胆拖";
					betCodeView +="玩法:"+betType+"<br/>"+convertBetCodeFor11YDJ_DT(codes[1])+"<br/>";
				} else if (wanfa!=null && wanfa.trim().equals("125")) { //任选六胆拖
					betType = "任选六胆拖";
					betCodeView +="玩法:"+betType+"<br/>"+convertBetCodeFor11YDJ_DT(codes[1])+"<br/>";
				} else if (wanfa!=null && wanfa.trim().equals("126")) { //任选七胆拖
					betType = "任选七胆拖";
					betCodeView +="玩法:"+betType+"<br/>"+convertBetCodeFor11YDJ_DT(codes[1])+"<br/>";
				} else if (wanfa!=null && wanfa.trim().equals("131")) { //选前二组选单式
					betType = "选前二组选单式";
					betCodeView += "玩法:"+betType+"<br/>"+convertBetCodeFor11YDJ_DS(codes[1])+"<br/>";
				} else if (wanfa!=null && wanfa.trim().equals("133")) { //选前二组选胆拖
					betType = "选前二组选胆拖";
					betCodeView += "玩法:"+betType+"<br/>"+convertBetCodeFor11YDJ_DT(codes[1])+"<br/>";
				} else if (wanfa!=null && wanfa.trim().equals("141")) { //选前二直选单式
					betType = "选前二直选单式";
					betCodeView = "玩法:"+betType+"<br/>"+convertBetCodeFor11YDJ_DS(codes[1])+"<br/>";
				} else if (wanfa!=null && wanfa.trim().equals("142")) { //选前二直选定位复式
					betType = "选前二直选定位复式";
					betCodeView = "玩法:"+betType+"<br/>"+convertBetCodeFor11YDJ_DWFS(codes[1])+"<br/>";
				} else if (wanfa!=null && wanfa.trim().equals("151")) { //选前三组选单式
					betType = "选前三组选单式";
					betCodeView = "玩法:"+betType+"<br/>"+convertBetCodeFor11YDJ_DS(codes[1])+"<br/>";
				} else if (wanfa!=null && wanfa.trim().equals("153")) { //选前三组选胆拖
					betType = "选前三组选胆拖";
					betCodeView = "玩法:"+betType+"<br/>"+convertBetCodeFor11YDJ_DT(codes[1])+"<br/>";
				} else if (wanfa!=null && wanfa.trim().equals("161")) { //选前三直选单式
					betType = "选前三直选单式";
					betCodeView = "玩法:"+betType+"<br/>"+convertBetCodeFor11YDJ_DS(codes[1])+"<br/>";
				} else if (wanfa!=null && wanfa.trim().equals("162")) { //选前三直选定位复式
					betType = "选前三直选定位复式";
					betCodeView = "玩法:"+betType+"<br/>"+convertBetCodeFor11YDJ_DWFS(codes[1])+"<br/>";
				} 
			}
		}else if(lotNo!=null&&!"".equals(lotNo)&&Constants.LOTNO_GD11X5.equals(lotNo)){
			//S|R2|0507_1_200_200
			String[] orderInfos = orderInfo.split("!");
			for(String info : orderInfos) {
				String[] infos = info.split("_");
				String betCode = infos[0];
			
				String[] codes = betCode.split("\\|");
				String touZhuType = codes[0]; //投注方式
				String smallType = codes[1]; //子玩法
				String realCode = codes[2]; //实际注码
				if (touZhuType.equals("S")) { //单式投注
					String play = getT01014Play(smallType);
					betType = play+"单式";
					betCodeView += "玩法:"+betType+"<br/>"+LotteryUtil.getDouHaoStringFromStringList(LotteryUtil.getStringListFromZeroString(realCode))+"<br/>";
				} else if (touZhuType.equals("M")) { //复式投注
					String play = getT01014Play(smallType);
					betType = play+"复式";
					betCodeView = "玩法:"+betType+"<br/>"+LotteryUtil.getDouHaoStringFromStringList(LotteryUtil.getStringListFromZeroString(realCode))+"<br/>";
				} else if (touZhuType.equals("D")) { //胆拖投注
					String play = getT01014Play(smallType);
					String[] strings = realCode.split("-");
					String danMa = LotteryUtil.getDouHaoStringFromStringList(LotteryUtil.getStringListFromZeroString(strings[0]));
					String tuoMa = LotteryUtil.getDouHaoStringFromStringList(LotteryUtil.getStringListFromZeroString(strings[1]));
					betType = play+"胆拖";
					betCodeView += "玩法:"+betType+"<br/>"+"胆码:"+danMa+"<br/>拖码:"+tuoMa+"<br/>";
				} else if (touZhuType.equals("P")) { //定位投注
					String play = getT01014Play(smallType);
					betType = play+"定位复式";
					String[] strings = realCode.split("-");
					if(strings.length==2){
						betCodeView = "第一位:"+LotteryUtil.getDouHaoStringFromStringList(LotteryUtil.getStringListFromZeroString(strings[0]))
							+ "<br/>第二位:"+LotteryUtil.getDouHaoStringFromStringList(LotteryUtil.getStringListFromZeroString(strings[1]));
					}
					if(strings.length==3){
						betCodeView = "第一位:"+LotteryUtil.getDouHaoStringFromStringList(LotteryUtil.getStringListFromZeroString(strings[0]))
							+ "<br/>第二位:"+LotteryUtil.getDouHaoStringFromStringList(LotteryUtil.getStringListFromZeroString(strings[1]))
							+ "<br/>第三位:"+LotteryUtil.getDouHaoStringFromStringList(LotteryUtil.getStringListFromZeroString(strings[2]));
					}
					betCodeView = "玩法:"+betType+"<br/>"+ betCodeView+"<br/>";
				}
			}
		}else if(lotNo!=null&&!"".equals(lotNo)&&Constants.LOTNO_GDKL10F.equals(lotNo)){
			String[] orderInfos = orderInfo.split("!");
			for (String info : orderInfos) {
				String infos[] = info.split("\\|");
				String codes[] = infos[2].split("\\_");
				betType = getGuangDongHappyTenMinutesType(infos[0], infos[1]);
				if("S".equals(infos[0])||"M".equals(infos[0])){
					if("H1".equals(infos[1])){
						if(codes[0].indexOf("^")>-1){
							betCodeView +="玩法:"+betType+"<br/>"+ codes[0].replace("^", ",")+"<br/>";

						}else{
							List<String> zhuMaList = LotteryUtil.getStringListFromZeroString(codes[0]);
							betCodeView +="玩法:"+betType+"<br/>"+ LotteryUtil.getDouHaoStringFromStringList(zhuMaList)+"<br/>";
						}
					}else{
						List<String> zhuMaList = LotteryUtil.getStringListFromZeroString(codes[0]);
						betCodeView +="玩法:"+betType+"<br/>"+ LotteryUtil.getDouHaoStringFromStringList(zhuMaList)+"<br/>";
					}
				}else if("D".equals(infos[0])){
					String danStr[] = codes[0].split("\\-");
					List<String> danMaList = LotteryUtil.getStringListFromZeroString(danStr[0]);
					List<String> tuoMaList = LotteryUtil.getStringListFromZeroString(danStr[1]);

					String danMaBetCodeView = LotteryUtil.getDouHaoStringFromStringList(danMaList);
					String tuoMaBetCodeView = LotteryUtil.getDouHaoStringFromStringList(tuoMaList);
					betCodeView += "玩法:"+betType+"<br/>胆码:" + danMaBetCodeView + "<br/>拖码:"
							+ tuoMaBetCodeView+"<br/>";

				}else if("P".equals(infos[0])){
					String pStr[] = codes[0].split("\\-");
					if("Q2".equals(infos[1])){
						List<String> firstList = LotteryUtil.getStringListFromZeroString(pStr[0]);
						List<String> secondList = LotteryUtil.getStringListFromZeroString(pStr[1]);

						String firstBetCodeView = LotteryUtil.getDouHaoStringFromStringList(firstList);
						String secondBetCodeView = LotteryUtil.getDouHaoStringFromStringList(secondList);
						betCodeView +="玩法:"+betType+"<br/>"+ "第一位:"+firstBetCodeView+"<br/>第二位:"+secondBetCodeView+"<br/>";
					}else if("Q3".equals(infos[1])){
						List<String> firstList = LotteryUtil.getStringListFromZeroString(pStr[0]);
						List<String> secondList = LotteryUtil.getStringListFromZeroString(pStr[1]);
						List<String> threeList = LotteryUtil.getStringListFromZeroString(pStr[2]);
						
						String firstBetCodeView = LotteryUtil.getDouHaoStringFromStringList(firstList);
						String secondBetCodeView = LotteryUtil.getDouHaoStringFromStringList(secondList);
						String threeBetCodeView = LotteryUtil.getDouHaoStringFromStringList(threeList);
						betCodeView += "玩法:"+betType+"<br/>"+"第一位:"+firstBetCodeView+"<br/>第二位:"+secondBetCodeView+"<br/>第三位:"+threeBetCodeView+"<br/>";

					}
				}
			}
		}else if(lotNo!=null&&!"".equals(lotNo)&&Constants.LOTNO_ZC_SFC_14C.equals(lotNo)){
			//3,1,0,3,1,0,1,0,3,1,0,3,1,0_1_200_200
			String[] orderInfos = orderInfo.split("!");
			for(String info : orderInfos) {
				String[] infos = info.split("_");
				String betCode = infos[0];
				if (betCode.indexOf(",") > -1) {
					boolean isD = true;
					String[] codes = betCode.split(",");
					for (int i = 0; i < codes.length; i++) {
						if (codes[i].length() > 1) {
							isD = false;
							break;
						}
					}
					if (isD) {
						betType = "单式";
					} else {
						betType = "复式";
					}
				} else {
					betType = "单式";
				}
				betCodeView += "玩法:"+betType+"<br/>"+betCode+"<br/>";
			}
		}else if(lotNo!=null&&!"".equals(lotNo)&&Constants.LOTNO_ZC_R9C.equals(lotNo)){
			//3,1,3,1,0,1,3,0,3,#,#,#,#,#_1_200_200
			String[] orderInfos = orderInfo.split("!");
			for(String info : orderInfos) {
				String[] infos = info.split("_");
				String betCode = infos[0];
				
				if (betCode.indexOf("$") > -1) {
					betType = "胆拖";
					String[] codes = betCode.split("\\$");
					betCode = "胆码:"+codes[0]+"<br/>拖码:"+codes[1];
				} else {
					if (betCode.indexOf(",") > -1) {
						String[] codes = betCode.split(",");
						boolean isD = true;
						for (int i = 0; i < codes.length; i++) {
							if (codes[i].length() > 1) {
								isD = false;
								break;
							}
						}
						if (isD) {
							betType = "单式";
						} else {
							betType = "复式";
						}
					} else {
						betType = "单式";
					}
				}
				betCodeView += "玩法:"+betType+"<br/>"+betCode+"<br/>";
			}
		}else if(lotNo!=null&&!"".equals(lotNo)&&Constants.LOTNO_ZC_4C_JQC.equals(lotNo)){
			//0,1,2,1,2,1,1,2_1_200_200
			String[] orderInfos = orderInfo.split("!");
			for(String info : orderInfos) {
				String[] infos = info.split("_");
				String betCode = infos[0];
				
				if (betCode.indexOf(",") > -1) {
					String[] codes = betCode.split(",");
					boolean isD = true;
					for (int i = 0; i < codes.length; i++) {
						if (codes[i].length() > 1) {
							isD = false;
							break;
						}
					}
					if (isD) {
						betType = "单式";
					} else {
						betType = "复式";
					}
				} else {
					betType ="单式";
				}
				betCodeView += "玩法:"+betType+"<br/>"+betCode+"<br/>";
			}
		}else if(lotNo!=null&&!"".equals(lotNo)&&Constants.LOTNO_ZC_6C_BQC.equals(lotNo)){
			//3,1,3,1,0,3,1,1,3,0,1,3_2_200_200
			String[] orderInfos = orderInfo.split("!");
			for(String info : orderInfos) {
				String[] infos = info.split("_");
				String betCode = infos[0];
				
				if (betCode.indexOf(",") > -1) {
					String[] codes = betCode.split(",");
					boolean isD = true;
					for (int i = 0; i < codes.length; i++) {
						if (codes[i].length() > 1) {
							isD = false;
							break;
						}
					}
					if (isD) {
						betType = "单式";
					} else {
						betType = "复式";
					}
				} else {
					betType = "单式";
				}
				betCodeView += "玩法:"+betType+"<br/>"+betCode+"<br/>";
			}
		}else if(lotNo!=null&&!"".equals(lotNo)&&Constants.LOTNO_NMK3.equals(lotNo)){
			String[] orderInfos = orderInfo.split("!");
			for(String info : orderInfos) {
				String[] infos = info.split("_");
				String betCode = infos[0];
				String[] codes = betCode.split(";");
				
				for (String code : codes) {
					String type = code.substring(0, 2);
					betType = getNMK3Play(type);
					if(code.indexOf("*")>-1){
						String[] danTuo = betCode.substring(6, code.length()).split("\\*");
						betCodeView +="玩法:"+betType+"<br/>胆码:"
						+ LotteryUtil.getDouHaoStringFromStringList(LotteryUtil.getStringListFromString(LotteryUtil.removeBetCodeZero(danTuo[0])))+"<br/>"
						+"拖码:"+ LotteryUtil.getDouHaoStringFromStringList(LotteryUtil.getStringListFromString(LotteryUtil.removeBetCodeZero(danTuo[1])))+"<br/>";
					}else if("10".equals(type)){
						betCodeView +="玩法:"+betType+"<br/>"+ LotteryUtil.getDouHaoStringFromStringList(LotteryUtil.removeBetCodeZero2(code.substring(8, code.length())))+"<br/>";
					}else if("20".equals(type)||"21".equals(type)||"30".equals(type)||"63".equals(type)||"81".equals(type)){
						betCodeView +="玩法:"+betType+"<br/>"+ LotteryUtil.getDouHaoStringFromStringList(LotteryUtil.getStringListFromString(LotteryUtil.removeBetCodeZero(code.substring(8, code.length()))))+"<br/>";
					}else if("40".equals(type)){
						betCodeView +="玩法:"+betType+"<br/>1,1,1<br/>2,2,2<br/>3,3,3<br/>4,4,4<br/>5,5,5<br/>6,6,6<br/>";

					}else if("50".equals(type)){
						betCodeView +="玩法:"+betType+"<br/>1,2,3<br/>2,3,4<br/>3,4,5<br/>4,5,6";
					}else{
						betCodeView +="玩法:"+betType+"<br/>"+ LotteryUtil.getDouHaoStringFromStringList(LotteryUtil.getStringListFromString(LotteryUtil.removeBetCodeZero(code.substring(6, code.length()))))+"<br/>";
					}

				}
			}
		}
		return betCodeView;
	}
	/**
	 * 11运夺金注码转换(胆拖)
	 * @param betCode
	 * @return
	 */
	public static String convertBetCodeFor11YDJ_DT(String betCode) { 
		StringBuffer stringBuffer = new StringBuffer();
		String[] split = betCode.split("\\*");
		String danMa = split[0];
		String tuoMa = split[1].substring(0, split[1].length()-1);
		stringBuffer.append("胆码:"+LotteryUtil.getDouHaoStringFromStringList(LotteryUtil.getStringListFromZeroString(danMa))
				+"<br/>拖码:"+LotteryUtil.getDouHaoStringFromStringList(LotteryUtil.getStringListFromZeroString(tuoMa)));
//		String string = sBuffer.toString();
//		if (string.endsWith("<br/>")) {
//			return string.substring(0, string.length()-1);
//		} else {
//			return sBuffer.toString();
//		}
		return stringBuffer.toString();
	}
	
	/**
	 * 11运夺金注码转换(单式)
	 * @param code
	 * @return
	 */
	public static String convertBetCodeFor11YDJ_DS(String betCode) {
		StringBuffer sBuffer = new StringBuffer();
		String[] split = betCode.split("\\^");
		for (String string : split) {
			sBuffer.append(LotteryUtil.getDouHaoStringFromStringList(LotteryUtil.getStringListFromZeroString(string))).append("<br/>");
		}
		String string = sBuffer.toString();
		if (string.endsWith("<br/>")) {
			return string.substring(0, string.length()-5);
		} else {
			return sBuffer.toString();
		}
	}
	
	/**
	 * 11运夺金注码转换(定位复式)
	 * @param betCode
	 * @return
	 */
	public static String convertBetCodeFor11YDJ_DWFS(String betCode) { 
		StringBuffer sBuffer = new StringBuffer();
		String[] split = betCode.split("\\*");
		if (split.length==2) { //142@04*050607^
			String wan = split[0];
			String qian = split[1].substring(0, split[1].length()-1);
			sBuffer.append("第一位:"+LotteryUtil.getDouHaoStringFromStringList(LotteryUtil.getStringListFromZeroString(wan))
					+"<br/>第二位:"+LotteryUtil.getDouHaoStringFromStringList(LotteryUtil.getStringListFromZeroString(qian)));
		} else if (split.length==3) { //162@0305*04*08^
			String wan = split[0];
			String qian = split[1];
			String bai = split[2].substring(0, split[2].length()-1);
			sBuffer.append("第一位:"+LotteryUtil.getDouHaoStringFromStringList(LotteryUtil.getStringListFromZeroString(wan))
					+"<br/>第二位:"+LotteryUtil.getDouHaoStringFromStringList(LotteryUtil.getStringListFromZeroString(qian))
					+"<br/>第三位:"+LotteryUtil.getDouHaoStringFromStringList(LotteryUtil.getStringListFromZeroString(bai)));
		}
//		String string = sBuffer.toString();
//		if (string.endsWith("\n")) {
//			return string.substring(0, string.length()-1);
//		} else {
//			return sBuffer.toString();
//		}
		return sBuffer.toString();
	}
	
	/**
	 * 11运夺金注码转换(复式)
	 * @param code
	 * @return
	 */
	public static String convertBetCodeFor11YDJ_FS(String betCode) {
		String string = betCode.substring(betCode.indexOf("*")+1, betCode.indexOf("^"));
		String code = LotteryUtil.getDouHaoStringFromStringList(LotteryUtil.getStringListFromZeroString(string));
		return code;
	}
	/**
	 * 获取广东十一选五的玩法
	 * @param smallType
	 * @return
	 */
	public static String getT01014Play(String smallType) {
		String play = "";
		if (smallType!=null&&smallType.equals("R1")) {
			play = "任选一";
		} else if (smallType!=null&&smallType.equals("R2")) {
			play = "任选二";
		} else if (smallType!=null&&smallType.equals("R3")) {
			play = "任选三";
		} else if (smallType!=null&&smallType.equals("R4")) {
			play = "任选四";
		} else if (smallType!=null&&smallType.equals("R5")) {
			play = "任选五";
		} else if (smallType!=null&&smallType.equals("R6")) {
			play = "任选六";
		} else if (smallType!=null&&smallType.equals("R7")) {
			play = "任选七";
		} else if (smallType!=null&&smallType.equals("R8")) {
			play = "任选八";
		} else if (smallType!=null&&smallType.equals("Q2")) {
			play = "直选前二";
		} else if (smallType!=null&&smallType.equals("Q3")) {
			play = "直选前三";
		} else if (smallType!=null&&smallType.equals("Z2")) {
			play = "组选前二";
		} else if (smallType!=null&&smallType.equals("Z3")) {
			play = "组选前三";
		}
		return play;
	}
	public static String getGuangDongElevenSelectFiveBetType(String str0,String str1){
		String betType ="";
		if("S".equals(str0)){
			betType = "单式";
		}else if("M".equals(str0)){
			betType = "复式";
		}else if("P".equals(str0)){
			betType = "定位复式";
		}else if("D".equals(str0)){
			betType = "胆拖";
		}
		if("R1".equals(str1)){
			betType = "任选一"+betType;
		}else if("R2".equals(str1)){
			betType = "任选二"+betType;
		}else if("R3".equals(str1)){
			betType = "任选三"+betType;
		}else if("R4".equals(str1)){
			betType = "任选四"+betType;
		}else if("R5".equals(str1)){
			betType = "任选五"+betType;
		}else if("R6".equals(str1)){
			betType = "任选六"+betType;
		}else if("R7".equals(str1)){
			betType = "任选七"+betType;
		}else if("R8".equals(str1)){
			betType = "任选八"+betType;
		}else if("Q2".equals(str1)){
			betType = "直选前二"+betType;
		}else if("Q3".equals(str1)){
			betType = "直选前三"+betType;
		}else if("Z2".equals(str1)){
			betType = "组选前二"+betType;
		}else if("Z3".equals(str1)){
			betType = "组选前三"+betType;
		}
		return betType;
	}
	public static String getGuangDongHappyTenMinutesType(String str0,String str1){
		String betType ="";
		if("S".equals(str0)){
			betType = "单式";
		}else if("M".equals(str0)){
			betType = "复式";
		}else if("P".equals(str0)){
			betType = "定位复式";
		}else if("D".equals(str0)){
			betType = "胆拖";
		}
		if("S1".equals(str1)){
			betType = "选一数投"+betType;
		}if("H1".equals(str1)){
			betType = "选一红投"+betType;
		}else if("R2".equals(str1)){
			betType = "任选二"+betType;
		}else if("R3".equals(str1)){
			betType = "任选三"+betType;
		}else if("R4".equals(str1)){
			betType = "任选四"+betType;
		}else if("R5".equals(str1)){
			betType = "任选五"+betType;
		}else if("Q2".equals(str1)){
			betType = "选二连直"+betType;
		}else if("Q3".equals(str1)){
			betType = "直选前三"+betType;
		}else if("Z2".equals(str1)){
			betType = "选二连组"+betType;
		}else if("Z3".equals(str1)){
			betType = "组选前三"+betType;
		}
		return betType;
	}
	public String getNMK3Play(String param){
		if("00".equals(param)) return "三不同单式";
		if("01".equals(param)) return "二同号单式";
		if("02".equals(param)) return "三同号单式";
		if("10".equals(param)) return "和值";
		if("20".equals(param)) return "二不同复选";
		if("21".equals(param)) return "二不同号组合";
		if("22".equals(param)) return "二不同号胆拖";
		if("30".equals(param)) return "二同号复选";
		if("40".equals(param)) return "三同号通选";
		if("50".equals(param)) return "三连号通选";
		if("63".equals(param)) return "三不同组合";
		if("64".equals(param)) return "三不同胆拖";
		if("71".equals(param)) return "二同号单选组合";
		if("81".equals(param)) return "三同号复式";
		return "";
	}
}
