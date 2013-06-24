package com.ruyicai.wap.util.bet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class LotteryJcBasketballUtil {
	/**
	 * 计算竞彩篮球胜负，让分胜负，大小分注数
	 * @param play
	 * @param betCode
	 * 		20101004|1|301|3^20101005|2|201|3^
	 * @return
	 */
	public long getJclqBetZhushu(String play,String betCode){
		long zhushu = 0;
		if("500".equals(play)){
			return getJclqBetZhushu500(betCode);
		}
		if("502".equals(play)){
			return getJclqBetZhushu502(betCode);
		}
		if("503".equals(play)){
			return getJclqBetZhushu503(betCode);
		}
		if("504".equals(play)){
			return getJclqBetZhushu504(betCode);
		}
		if("505".equals(play)){
			return getJclqBetZhushu505(betCode);
		}
		if("506".equals(play)){
			return getJclqBetZhushu506(betCode);
		}
		if("507".equals(play)){
			return getJclqBetZhushu507(betCode);
		}
		if("508".equals(play)){
			return getJclqBetZhushu508(betCode);
		}
		if("526".equals(play)){
			return getJclqBetZhushu526(betCode);
		}
		if("527".equals(play)){
			return getJclqBetZhushu527(betCode);
		}
		if("528".equals(play)){
			return getJclqBetZhushu528(betCode);
		}
		if("529".equals(play)){
			return getJclqBetZhushu529(betCode);
		}
		if("530".equals(play)){
			return getJclqBetZhushu530(betCode);
		}
		if("531".equals(play)){
			return getJclqBetZhushu531(betCode);
		}
		if("532".equals(play)){
			return getJclqBetZhushu532(betCode);
		}
		if("533".equals(play)){
			return getJclqBetZhushu533(betCode);
		}
		if("534".equals(play)){
			return getJclqBetZhushu534(betCode);
		}
		if("535".equals(play)){
			return getJclqBetZhushu535(betCode);
		}
		if("536".equals(play)){
			return getJclqBetZhushu536(betCode);
		}
		if("537".equals(play)){
			return getJclqBetZhushu537(betCode);
		}
		if("538".equals(play)){
			return getJclqBetZhushu538(betCode);
		}
		if("539".equals(play)){
			return getJclqBetZhushu539(betCode);
		}
		if("540".equals(play)){
			return getJclqBetZhushu540(betCode);
		}
		if("541".equals(play)){
			return getJclqBetZhushu541(betCode);
		}
		if("542".equals(play)){
			return getJclqBetZhushu542(betCode);
		}
		if("543".equals(play)){
			return getJclqBetZhushu543(betCode);
		}
		if("544".equals(play)){
			return getJclqBetZhushu544(betCode);
		}
		if("545".equals(play)){
			return getJclqBetZhushu545(betCode);
		}
		if("546".equals(play)){
			return getJclqBetZhushu546(betCode);
		}
		if("547".equals(play)){
			return getJclqBetZhushu547(betCode);
		}
		if("548".equals(play)){
			return getJclqBetZhushu548(betCode);
		}
		if("549".equals(play)){
			return getJclqBetZhushu549(betCode);
		}
		if("550".equals(play)){
			return getJclqBetZhushu550(betCode);
		}
		if("551".equals(play)){
			return getJclqBetZhushu551(betCode);
		}
		if("552".equals(play)){
			return getJclqBetZhushu552(betCode);
		}
		if("553".equals(play)){
			return getJclqBetZhushu553(betCode);
		}
		if("554".equals(play)){
			return getJclqBetZhushu554(betCode);
		}
		if("555".equals(play)){
			return getJclqBetZhushu555(betCode);
		}
		if("556".equals(play)){
			return getJclqBetZhushu556(betCode);
		}
		if("557".equals(play)){
			return getJclqBetZhushu557(betCode);
		}
		return zhushu;
	}
	/**
	 * 篮球单关计算注数 
	 * 
	 * @param betCode
	 *            20101004|1|301|3^20101005|2|201|3^
	 * @return
	 */
	public long getJclqBetZhushu500(String betCode) {

		String[] codes = getSelectCode(betCode);
		long zhushu = 0;
		for(String code:codes) {
			zhushu = zhushu + code.length();
		}
		return zhushu;
	}
	/**
	 * 篮球胜负2*1计算注数 
	 * 
	 * @param betCode
	 *            20101004|1|301|3^20101005|2|201|3^
	 * @return
	 */
	public long getJclqBetZhushu502(String betCode) {

		List<List<String>> list = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 2);
		long zhushu = 0;
		for (List<String> codes : list) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负3*1计算注数 
	 * 
	 * @param betCode
	 *            20101004|1|301|3^20101004|2|201|3^20101006|3|401|0^ 以下类似
	 * @return
	 */
	public long getJclqBetZhushu503(String betCode) {

		List<List<String>> list = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 3);
		long zhushu = 0;
		for (List<String> codes : list) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负4*1计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJclqBetZhushu504(String betCode) {

		List<List<String>> list = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 4);
		long zhushu = 0;
		for (List<String> codes : list) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负5*1计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJclqBetZhushu505(String betCode) {

		List<List<String>> list = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 5);
		long zhushu = 0;
		for (List<String> codes : list) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负6*1计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJclqBetZhushu506(String betCode) {

		List<List<String>> list = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 6);
		long zhushu = 0;
		for (List<String> codes : list) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负7*1计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJclqBetZhushu507(String betCode) {

		List<List<String>> list = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 7);
		long zhushu = 0;
		for (List<String> codes : list) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负8*1计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJclqBetZhushu508(String betCode) {

		List<List<String>> list = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 8);
		long zhushu = 0;
		for (List<String> codes : list) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负3*3计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJclqBetZhushu526(String betCode) {

		List<List<String>> list_cn3 = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 3);

		List<List<String>> list_all = new ArrayList<List<String>>();

		for (List<String> list : list_cn3) {
			list_all.addAll(getbetCodeCollection(list, 2));
		}
		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负3*4计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJclqBetZhushu527(String betCode) {

		// C(n,3)
		List<List<String>> list_cn3 = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 3);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(3,2)
		for (List<String> list : list_cn3) {
			list_all.addAll(getbetCodeCollection(list, 2));
		}

		// 3*4=C(3,2)+C(3,3)
		list_all.addAll(list_cn3);

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负4*6计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJclqBetZhushu528(String betCode) {

		// C(n,4)
		List<List<String>> list_cn4 = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 4);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(4,2)
		for (List<String> list : list_cn4) {
			list_all.addAll(getbetCodeCollection(list, 2));
		}
		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负4*11计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJclqBetZhushu529(String betCode) {

		// C(n,4)
		List<List<String>> list_cn4 = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 4);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(4,4)
		list_all.addAll(list_cn4);
		// C(4,3)
		for (List<String> list : list_cn4) {
			list_all.addAll(getbetCodeCollection(list, 3));
		}
		// C(4,2)
		for (List<String> list : list_cn4) {
			list_all.addAll(getbetCodeCollection(list, 2));
		}
		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负5*10计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJclqBetZhushu530(String betCode) {

		// C(n,5)
		List<List<String>> list_cn5 = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 5);

		// C(5,2)
		List<List<String>> list_all = new ArrayList<List<String>>();
		for (List<String> list : list_cn5) {
			list_all.addAll(getbetCodeCollection(list, 2));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负5*20计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJclqBetZhushu531(String betCode) {

		// C(n,5)
		List<List<String>> list_cn5 = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 5);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(5,3)
		for (List<String> list : list_cn5) {
			list_all.addAll(getbetCodeCollection(list, 3));
		}

		// C(5,2)
		for (List<String> list : list_cn5) {
			list_all.addAll(getbetCodeCollection(list, 2));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负5*26计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJclqBetZhushu532(String betCode) {
		// C(n,5)
		List<List<String>> list_cn5 = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 5);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(5,5)
		list_all.addAll(list_cn5);

		// C(5,4)
		for (List<String> list : list_cn5) {
			list_all.addAll(getbetCodeCollection(list, 4));
		}

		// C(5,3)
		for (List<String> list : list_cn5) {
			list_all.addAll(getbetCodeCollection(list, 3));
		}

		// C(5,2)
		for (List<String> list : list_cn5) {
			list_all.addAll(getbetCodeCollection(list, 2));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负6*15计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJclqBetZhushu533(String betCode) {
		// C(n,6)
		List<List<String>> list_cn6 = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 6);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(6,2)
		for (List<String> list : list_cn6) {
			list_all.addAll(getbetCodeCollection(list, 2));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负6*35计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJclqBetZhushu534(String betCode) {
		// C(n,6)
		List<List<String>> list_cn6 = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 6);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(6,2)
		for (List<String> list : list_cn6) {
			list_all.addAll(getbetCodeCollection(list, 2));
		}

		// C(6,3)
		for (List<String> list : list_cn6) {
			list_all.addAll(getbetCodeCollection(list, 3));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负6*50计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJclqBetZhushu535(String betCode) {
		// C(n,6)
		List<List<String>> list_cn6 = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 6);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(6,4)
		for (List<String> list : list_cn6) {
			list_all.addAll(getbetCodeCollection(list, 4));
		}
		// C(6,3)
		for (List<String> list : list_cn6) {
			list_all.addAll(getbetCodeCollection(list, 3));
		}
		// C(6,2)
		for (List<String> list : list_cn6) {
			list_all.addAll(getbetCodeCollection(list, 2));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负6*57计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJclqBetZhushu536(String betCode) {
		// C(n,6)
		List<List<String>> list_cn6 = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 6);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(6,6)
		list_all.addAll(list_cn6);

		// C(6,5)
		for (List<String> list : list_cn6) {
			list_all.addAll(getbetCodeCollection(list, 5));
		}

		// C(6,4)
		for (List<String> list : list_cn6) {
			list_all.addAll(getbetCodeCollection(list, 4));
		}
		// C(6,3)
		for (List<String> list : list_cn6) {
			list_all.addAll(getbetCodeCollection(list, 3));
		}
		// C(6,2)
		for (List<String> list : list_cn6) {
			list_all.addAll(getbetCodeCollection(list, 2));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负7*120计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJclqBetZhushu537(String betCode) {
		// C(n,7)
		List<List<String>> list_cn7 = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 7);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(7,7)
		list_all.addAll(list_cn7);
		// C(7,6)
		for (List<String> list : list_cn7) {
			list_all.addAll(getbetCodeCollection(list, 6));
		}
		// C(7,5)
		for (List<String> list : list_cn7) {
			list_all.addAll(getbetCodeCollection(list, 5));
		}
		// C(7,4)
		for (List<String> list : list_cn7) {
			list_all.addAll(getbetCodeCollection(list, 4));
		}
		// C(7,3)
		for (List<String> list : list_cn7) {
			list_all.addAll(getbetCodeCollection(list, 3));
		}
		// C(7,2)
		for (List<String> list : list_cn7) {
			list_all.addAll(getbetCodeCollection(list, 2));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负8*247计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJclqBetZhushu538(String betCode) {
		// C(n,8)
		List<List<String>> list_cn8 = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 8);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(8,8)
		list_all.addAll(list_cn8);
		// C(8,7)
		for (List<String> list : list_cn8) {
			list_all.addAll(getbetCodeCollection(list, 7));
		}
		// C(8,6)
		for (List<String> list : list_cn8) {
			list_all.addAll(getbetCodeCollection(list, 6));
		}
		// C(8,5)
		for (List<String> list : list_cn8) {
			list_all.addAll(getbetCodeCollection(list, 5));
		}
		// C(8,4)
		for (List<String> list : list_cn8) {
			list_all.addAll(getbetCodeCollection(list, 4));
		}
		// C(8,3)
		for (List<String> list : list_cn8) {
			list_all.addAll(getbetCodeCollection(list, 3));
		}
		// C(8,2)
		for (List<String> list : list_cn8) {
			list_all.addAll(getbetCodeCollection(list, 2));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负4*4计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJclqBetZhushu539(String betCode) {
		// C(n,4)
		List<List<String>> list_cn4 = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 4);

		// C(4,3)
		List<List<String>> list_all = new ArrayList<List<String>>();
		for (List<String> list : list_cn4) {
			list_all.addAll(getbetCodeCollection(list, 3));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负4*5计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJclqBetZhushu540(String betCode) {
		// C(n,4)
		List<List<String>> list_cn4 = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 4);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(4,4)
		list_all.addAll(list_cn4);
		// C(4,3)
		for (List<String> list : list_cn4) {
			list_all.addAll(getbetCodeCollection(list, 3));
		}
		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负5*16计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJclqBetZhushu541(String betCode) {
		// C(n,5)
		List<List<String>> list_cn5 = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 5);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(5,5)
		list_all.addAll(list_cn5);
		// C(5,4)
		for (List<String> list : list_cn5) {
			list_all.addAll(getbetCodeCollection(list, 4));
		}
		// C(5,3)
		for (List<String> list : list_cn5) {
			list_all.addAll(getbetCodeCollection(list, 3));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负6*20计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJclqBetZhushu542(String betCode) {
		// C(n,6)
		List<List<String>> list_cn6 = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 6);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(6,3)
		for (List<String> list : list_cn6) {
			list_all.addAll(getbetCodeCollection(list, 3));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负6*42计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJclqBetZhushu543(String betCode) {
		// C(n,6)
		List<List<String>> list_cn6 = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 6);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(6,6)
		list_all.addAll(list_cn6);
		// C(6,5)
		for (List<String> list : list_cn6) {
			list_all.addAll(getbetCodeCollection(list, 5));
		}
		// C(6,4)
		for (List<String> list : list_cn6) {
			list_all.addAll(getbetCodeCollection(list, 4));
		}
		// C(6,3)
		for (List<String> list : list_cn6) {
			list_all.addAll(getbetCodeCollection(list, 3));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负5*5计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJclqBetZhushu544(String betCode) {
		// C(n,5)
		List<List<String>> list_cn5 = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 5);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(5,4)
		for (List<String> list : list_cn5) {
			list_all.addAll(getbetCodeCollection(list, 4));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负5*6计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJclqBetZhushu545(String betCode) {
		// C(n,5)
		List<List<String>> list_cn5 = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 5);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(5,5)
		list_all.addAll(list_cn5);
		// C(5,4)
		for (List<String> list : list_cn5) {
			list_all.addAll(getbetCodeCollection(list, 4));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负6*22计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJclqBetZhushu546(String betCode) {
		// C(n,6)
		List<List<String>> list_cn6 = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 6);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(6,6)
		list_all.addAll(list_cn6);
		// C(6,5)
		for (List<String> list : list_cn6) {
			list_all.addAll(getbetCodeCollection(list, 5));
		}
		// C(6,4)
		for (List<String> list : list_cn6) {
			list_all.addAll(getbetCodeCollection(list, 4));
		}
		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负7*35计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJclqBetZhushu547(String betCode) {
		// C(n,7)
		List<List<String>> list_cn7 = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 7);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(7,4)
		for (List<String> list : list_cn7) {
			list_all.addAll(getbetCodeCollection(list, 4));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负8*70计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJclqBetZhushu548(String betCode) {
		// C(n,8)
		List<List<String>> list_cn8 = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 8);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(8,4)
		for (List<String> list : list_cn8) {
			list_all.addAll(getbetCodeCollection(list, 4));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负6*6计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJclqBetZhushu549(String betCode) {
		// C(n,6)
		List<List<String>> list_cn6 = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 6);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(6,5)
		for (List<String> list : list_cn6) {
			list_all.addAll(getbetCodeCollection(list, 5));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负6*7计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJclqBetZhushu550(String betCode) {
		// C(n,6)
		List<List<String>> list_cn6 = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 6);

		List<List<String>> list_all = new ArrayList<List<String>>();
		// C(6,6)
		list_all.addAll(list_cn6);

		// C(6,5)
		for (List<String> list : list_cn6) {
			list_all.addAll(getbetCodeCollection(list, 5));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负7*21计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJclqBetZhushu551(String betCode) {
		// C(n,7)
		List<List<String>> list_cn7 = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 7);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(7,5)
		for (List<String> list : list_cn7) {
			list_all.addAll(getbetCodeCollection(list, 5));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负8*56计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJclqBetZhushu552(String betCode) {
		// C(n,8)
		List<List<String>> list_cn8 = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 8);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(8,5)
		for (List<String> list : list_cn8) {
			list_all.addAll(getbetCodeCollection(list, 5));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负7*7计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJclqBetZhushu553(String betCode) {
		// C(n,7)
		List<List<String>> list_cn7 = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 7);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(7,6)
		for (List<String> list : list_cn7) {
			list_all.addAll(getbetCodeCollection(list, 6));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负7*8计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJclqBetZhushu554(String betCode) {
		// C(n,7)
		List<List<String>> list_cn7 = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 7);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(7,7)
		list_all.addAll(list_cn7);

		// C(7,6)
		for (List<String> list : list_cn7) {
			list_all.addAll(getbetCodeCollection(list, 6));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负8*28计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJclqBetZhushu555(String betCode) {
		// C(n,8)
		List<List<String>> list_cn8 = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 8);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(8,6)
		for (List<String> list : list_cn8) {
			list_all.addAll(getbetCodeCollection(list, 6));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负8*8计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJclqBetZhushu556(String betCode) {
		// C(n,8)
		List<List<String>> list_cn8 = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 8);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(8,7)
		for (List<String> list : list_cn8) {
			list_all.addAll(getbetCodeCollection(list, 7));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负8*9计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJclqBetZhushu557(String betCode) {
		// C(n,8)
		List<List<String>> list_cn8 = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 8);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(8,8)
		list_all.addAll(list_cn8);
		// C(8,7)
		for (List<String> list : list_cn8) {
			list_all.addAll(getbetCodeCollection(list, 7));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * @param betCodes
	 * @param select
	 * @return
	 */
	protected List<List<String>> getbetCodeCollection(
			List<String> betCodes, int select) {
		// 初始化原始数据
		int[] a = new int[betCodes.size()];
		for (int i = 0; i < a.length; i++) {
			a[i] = i;
		}
		// 接收数据
		int[] b = new int[select];

		List<int[]> list = new ArrayList<int[]>();

		// 进行组合
		combine(a, a.length, select, b, select, list);

		// 返回数据对象
		List<List<String>> reList = new ArrayList<List<String>>();
		for (int[] result : list) {
			List<String> codeList = new ArrayList<String>();
			for (int p : result) {
				codeList.add(betCodes.get(p));
			}
			reList.add(codeList);
		}

		return reList;
	}

	/**
	 * 组合的递归算法
	 * 
	 * @param a
	 *            原始数据
	 * @param n
	 *            原始数据个数
	 * @param m
	 *            选择数据个数
	 * @param b
	 *            存放被选择的数据
	 * @param M
	 *            常量，选择数据个数
	 * @param list
	 *            存放计算结果
	 */
	public void combine(int a[], int n, int m, int b[], final int M,
			List<int[]> list) {
		for (int i = n; i >= m; i--) {
			b[m - 1] = i - 1;
			if (m > 1)
				combine(a, i - 1, m - 1, b, M, list);
			else {
				int[] result = new int[M];
				for (int j = M - 1; j >= 0; j--) {
					result[j] = a[b[j]];
				}
				list.add(result);
			}
		}
	}

	/**
	 * 选出一串注码(多场次注码联排)中除去时间场次信息的投注信息
	 * 
	 * @param betCode
	 * @return
	 */
	public String[] getSelectCode(String betCode) {
		List<String> betCodes = Arrays.asList(betCode.split("\\^"));
		return getSelectCode(betCodes);
	}

	/**
	 * 选出注码List(每个list中的一个注码为一个场次的注码)中除去时间场次信息的投注信息
	 * 
	 * @param betCodes
	 * @return
	 */
	public String[] getSelectCode(List<String> betCodes) {
		String[] codes = new String[betCodes.size()];
		for (int i = 0; i < betCodes.size(); i++) {
			codes[i] = betCodes.get(i).split("\\|")[3];
		}
		return codes;
	}

	public long mulSelectCode(String[] selects) {
		long total = 1;
		for (String select : selects) {
			total = total * select.length();
		}
		return total;
	}
	public long mulSelectCodeJclqSFC(String[] selects) {
		long total = 1;
		for (String select : selects) {
			total = total * select.length()/2;
		}
		return total;
	}
	public void main(String[] args) {
		System.out.println(getJclqBetZhushu527("20101004|1|301|3^20101005|2|201|0^20101006|3|401|0^20101007|4|201|0^"));
	}
	
	/**
	 * 计算竞彩篮球胜分差注数
	 * @param play
	 * @param betCode
	 * 		20101004|1|301|3^20101005|2|201|3^
	 * @return
	 */
	public long getJclqSFCBetZhushu(String play,String betCode){
		long zhushu = 0;
		if("500".equals(play)){
			return getJclqSFCBetZhushu500(betCode);
		}
		if("502".equals(play)){
			return getJclqSFCBetZhushu502(betCode);
		}
		if("503".equals(play)){
			return getJclqSFCBetZhushu503(betCode);
		}
		if("504".equals(play)){
			return getJclqSFCBetZhushu504(betCode);
		}
		if("526".equals(play)){
			return getJclqSFCBetZhushu526(betCode);
		}
		if("527".equals(play)){
			return getJclqSFCBetZhushu527(betCode);
		}
		if("528".equals(play)){
			return getJclqSFCBetZhushu528(betCode);
		}
		if("529".equals(play)){
			return getJclqSFCBetZhushu529(betCode);
		}
		if("539".equals(play)){
			return getJclqSFCBetZhushu539(betCode);
		}
		if("540".equals(play)){
			return getJclqSFCBetZhushu540(betCode);
		}
		return zhushu;
	}
	
	/**
	 * 篮球单关计算注数 
	 * 
	 * @param betCode
	 *            20101004|1|301|3^20101005|2|201|3^
	 * @return
	 */
	public long getJclqSFCBetZhushu500(String betCode) {
		long zhushu = 0;
		String[] codes = getSelectCode(betCode);
		for(String code:codes) {
			zhushu = zhushu + code.length()/2;
		}
		return zhushu;
	}
	
	/**
	 * 篮球胜分差2*1计算注数 
	 * 
	 * @param betCode
	 *            20101004|1|301|3^20101005|2|201|3^
	 * @return
	 */
	public long getJclqSFCBetZhushu502(String betCode) {

		List<List<String>> list = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 2);
		long zhushu = 0;
		for (List<String> codes : list) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJclqSFC(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜分差3*1计算注数 
	 * 
	 * @param betCode
	 *            20101004|1|301|3^20101004|2|201|3^20101006|3|401|0^ 以下类似
	 * @return
	 */
	public long getJclqSFCBetZhushu503(String betCode) {

		List<List<String>> list = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 3);
		long zhushu = 0;
		for (List<String> codes : list) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJclqSFC(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜分差4*1计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJclqSFCBetZhushu504(String betCode) {

		List<List<String>> list = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 4);
		long zhushu = 0;
		for (List<String> codes : list) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJclqSFC(codelength);
		}
		return zhushu;
	}
	
	/**
	 * 篮球胜分差3*3计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJclqSFCBetZhushu526(String betCode) {

		List<List<String>> list_cn3 = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 3);

		List<List<String>> list_all = new ArrayList<List<String>>();

		for (List<String> list : list_cn3) {
			list_all.addAll(getbetCodeCollection(list, 2));
		}
		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJclqSFC(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜分差3*4计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJclqSFCBetZhushu527(String betCode) {

		// C(n,3)
		List<List<String>> list_cn3 = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 3);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(3,2)
		for (List<String> list : list_cn3) {
			list_all.addAll(getbetCodeCollection(list, 2));
		}

		// 3*4=C(3,2)+C(3,3)
		list_all.addAll(list_cn3);

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJclqSFC(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜分差4*6计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJclqSFCBetZhushu528(String betCode) {

		// C(n,4)
		List<List<String>> list_cn4 = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 4);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(4,2)
		for (List<String> list : list_cn4) {
			list_all.addAll(getbetCodeCollection(list, 2));
		}
		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJclqSFC(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜分差4*11计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJclqSFCBetZhushu529(String betCode) {

		// C(n,4)
		List<List<String>> list_cn4 = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 4);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(4,4)
		list_all.addAll(list_cn4);
		// C(4,3)
		for (List<String> list : list_cn4) {
			list_all.addAll(getbetCodeCollection(list, 3));
		}
		// C(4,2)
		for (List<String> list : list_cn4) {
			list_all.addAll(getbetCodeCollection(list, 2));
		}
		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJclqSFC(codelength);
		}
		return zhushu;
	}
	
	/**
	 * 篮球胜分差4*4计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJclqSFCBetZhushu539(String betCode) {
		// C(n,4)
		List<List<String>> list_cn4 = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 4);

		// C(4,3)
		List<List<String>> list_all = new ArrayList<List<String>>();
		for (List<String> list : list_cn4) {
			list_all.addAll(getbetCodeCollection(list, 3));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJclqSFC(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜分差4*5计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJclqSFCBetZhushu540(String betCode) {
		// C(n,4)
		List<List<String>> list_cn4 = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 4);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(4,4)
		list_all.addAll(list_cn4);
		// C(4,3)
		for (List<String> list : list_cn4) {
			list_all.addAll(getbetCodeCollection(list, 3));
		}
		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJclqSFC(codelength);
		}
		return zhushu;
	}
}
