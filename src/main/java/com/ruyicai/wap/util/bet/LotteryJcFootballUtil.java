package com.ruyicai.wap.util.bet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class LotteryJcFootballUtil {
	/**
	 * 计算竞彩足球注数(用于胜平负和总进球)
	 * @param play
	 * @param betCode
	 * 		20101004|1|301|3^20101005|2|201|3^
	 * @return
	 */
	public long getJczqSPFAndZJQZhuShu(String play,String betCode){
		long zhuShu = 0;
		if("500".equals(play)){
			return getJczqSPFAndZJQZhuShu500(betCode);
		}
		if("502".equals(play)){
			return getJczqSPFAndZJQZhuShu502(betCode);
		}
		if("503".equals(play)){
			return getJczqSPFAndZJQZhuShu503(betCode);
		}
		if("504".equals(play)){
			return getJczqSPFAndZJQZhuShu504(betCode);
		}
		if("505".equals(play)){
			return getJczqSPFAndZJQZhuShu505(betCode);
		}
		if("506".equals(play)){
			return getJczqSPFAndZJQZhuShu506(betCode);
		}
		if("507".equals(play)){
			return getJczqSPFAndZJQZhuShu507(betCode);
		}
		if("508".equals(play)){
			return getJczqSPFAndZJQZhuShu508(betCode);
		}
		if("526".equals(play)){
			return getJczqSPFAndZJQZhuShu526(betCode);
		}
		if("527".equals(play)){
			return getJczqSPFAndZJQZhuShu527(betCode);
		}
		if("528".equals(play)){
			return getJczqSPFAndZJQZhuShu528(betCode);
		}
		if("529".equals(play)){
			return getJczqSPFAndZJQZhuShu529(betCode);
		}
		if("530".equals(play)){
			return getJczqSPFAndZJQZhuShu530(betCode);
		}
		if("531".equals(play)){
			return getJczqSPFAndZJQZhuShu531(betCode);
		}
		if("532".equals(play)){
			return getJczqSPFAndZJQZhuShu532(betCode);
		}
		if("533".equals(play)){
			return getJczqSPFAndZJQZhuShu533(betCode);
		}
		if("534".equals(play)){
			return getJczqSPFAndZJQZhuShu534(betCode);
		}
		if("535".equals(play)){
			return getJczqSPFAndZJQZhuShu535(betCode);
		}
		if("536".equals(play)){
			return getJczqSPFAndZJQZhuShu536(betCode);
		}
		if("537".equals(play)){
			return getJczqSPFAndZJQZhuShu537(betCode);
		}
		if("538".equals(play)){
			return getJczqSPFAndZJQZhuShu538(betCode);
		}
		if("539".equals(play)){
			return getJczqSPFAndZJQZhuShu539(betCode);
		}
		if("540".equals(play)){
			return getJczqSPFAndZJQZhuShu540(betCode);
		}
		if("541".equals(play)){
			return getJczqSPFAndZJQZhuShu541(betCode);
		}
		if("542".equals(play)){
			return getJczqSPFAndZJQZhuShu542(betCode);
		}
		if("543".equals(play)){
			return getJczqSPFAndZJQZhuShu543(betCode);
		}
		if("544".equals(play)){
			return getJczqSPFAndZJQZhuShu544(betCode);
		}
		if("545".equals(play)){
			return getJczqSPFAndZJQZhuShu545(betCode);
		}
		if("546".equals(play)){
			return getJczqSPFAndZJQZhuShu546(betCode);
		}
		if("547".equals(play)){
			return getJczqSPFAndZJQZhuShu547(betCode);
		}
		if("548".equals(play)){
			return getJczqSPFAndZJQZhuShu548(betCode);
		}
		if("549".equals(play)){
			return getJczqSPFAndZJQZhuShu549(betCode);
		}
		if("550".equals(play)){
			return getJczqSPFAndZJQZhuShu550(betCode);
		}
		if("551".equals(play)){
			return getJczqSPFAndZJQZhuShu551(betCode);
		}
		if("552".equals(play)){
			return getJczqSPFAndZJQZhuShu552(betCode);
		}
		if("553".equals(play)){
			return getJczqSPFAndZJQZhuShu553(betCode);
		}
		if("554".equals(play)){
			return getJczqSPFAndZJQZhuShu554(betCode);
		}
		if("555".equals(play)){
			return getJczqSPFAndZJQZhuShu555(betCode);
		}
		if("556".equals(play)){
			return getJczqSPFAndZJQZhuShu556(betCode);
		}
		if("557".equals(play)){
			return getJczqSPFAndZJQZhuShu557(betCode);
		}
		return zhuShu;
	}
	/**
	 * 足球单关计算注数 
	 * 
	 * @param betCode
	 *            20101004|1|301|3^20101005|2|201|3^
	 * @return
	 */
	public long getJczqSPFAndZJQZhuShu500(String betCode) {

		String[] codes = getSelectCode(betCode);
		long zhushu = 0;
		for(String code:codes) {
			zhushu = zhushu + code.length();
		}
		return zhushu;
	}
	/**
	 * 竞彩足球2*1计算注数 
	 * 
	 * @param betCode
	 *            20101004|1|301|3^20101005|2|201|3^
	 * @return
	 */
	public long getJczqSPFAndZJQZhuShu502(String betCode) {

		List<List<String>> list = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 2);
		long zhushu = 0;
		for (List<String> codes : list) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeSPFAndZJQ(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球3*1计算注数 
	 * 
	 * @param betCode
	 *            20101004|1|301|3^20101004|2|201|3^20101006|3|401|0^ 以下类似
	 * @return
	 */
	public long getJczqSPFAndZJQZhuShu503(String betCode) {

		List<List<String>> list = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 3);
		long zhushu = 0;
		for (List<String> codes : list) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeSPFAndZJQ(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球4*1计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqSPFAndZJQZhuShu504(String betCode) {

		List<List<String>> list = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 4);
		long zhushu = 0;
		for (List<String> codes : list) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeSPFAndZJQ(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球5*1计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqSPFAndZJQZhuShu505(String betCode) {

		List<List<String>> list = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 5);
		long zhushu = 0;
		for (List<String> codes : list) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeSPFAndZJQ(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球6*1计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqSPFAndZJQZhuShu506(String betCode) {

		List<List<String>> list = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 6);
		long zhushu = 0;
		for (List<String> codes : list) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeSPFAndZJQ(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球7*1计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqSPFAndZJQZhuShu507(String betCode) {

		List<List<String>> list = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 7);
		long zhushu = 0;
		for (List<String> codes : list) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeSPFAndZJQ(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球8*1计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqSPFAndZJQZhuShu508(String betCode) {

		List<List<String>> list = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 8);
		long zhushu = 0;
		for (List<String> codes : list) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeSPFAndZJQ(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球3*3计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqSPFAndZJQZhuShu526(String betCode) {

		List<List<String>> list_cn3 = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 3);

		List<List<String>> list_all = new ArrayList<List<String>>();

		for (List<String> list : list_cn3) {
			list_all.addAll(getbetCodeCollection(list, 2));
		}
		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeSPFAndZJQ(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球3*4计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqSPFAndZJQZhuShu527(String betCode) {

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
			zhushu = zhushu + mulSelectCodeSPFAndZJQ(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球4*6计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqSPFAndZJQZhuShu528(String betCode) {

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
			zhushu = zhushu + mulSelectCodeSPFAndZJQ(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球4*11计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqSPFAndZJQZhuShu529(String betCode) {

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
			zhushu = zhushu + mulSelectCodeSPFAndZJQ(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球5*10计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqSPFAndZJQZhuShu530(String betCode) {

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
			zhushu = zhushu + mulSelectCodeSPFAndZJQ(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球5*20计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqSPFAndZJQZhuShu531(String betCode) {

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
			zhushu = zhushu + mulSelectCodeSPFAndZJQ(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球5*26计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqSPFAndZJQZhuShu532(String betCode) {
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
			zhushu = zhushu + mulSelectCodeSPFAndZJQ(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球6*15计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqSPFAndZJQZhuShu533(String betCode) {
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
			zhushu = zhushu + mulSelectCodeSPFAndZJQ(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球6*35计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqSPFAndZJQZhuShu534(String betCode) {
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
			zhushu = zhushu + mulSelectCodeSPFAndZJQ(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球6*50计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqSPFAndZJQZhuShu535(String betCode) {
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
			zhushu = zhushu + mulSelectCodeSPFAndZJQ(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球6*57计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqSPFAndZJQZhuShu536(String betCode) {
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
			zhushu = zhushu + mulSelectCodeSPFAndZJQ(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球7*120计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqSPFAndZJQZhuShu537(String betCode) {
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
			zhushu = zhushu + mulSelectCodeSPFAndZJQ(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球8*247计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqSPFAndZJQZhuShu538(String betCode) {
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
			zhushu = zhushu + mulSelectCodeSPFAndZJQ(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球4*4计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqSPFAndZJQZhuShu539(String betCode) {
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
			zhushu = zhushu + mulSelectCodeSPFAndZJQ(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球4*5计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqSPFAndZJQZhuShu540(String betCode) {
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
			zhushu = zhushu + mulSelectCodeSPFAndZJQ(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球5*16计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqSPFAndZJQZhuShu541(String betCode) {
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
			zhushu = zhushu + mulSelectCodeSPFAndZJQ(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球6*20计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqSPFAndZJQZhuShu542(String betCode) {
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
			zhushu = zhushu + mulSelectCodeSPFAndZJQ(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球6*42计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqSPFAndZJQZhuShu543(String betCode) {
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
			zhushu = zhushu + mulSelectCodeSPFAndZJQ(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球5*5计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqSPFAndZJQZhuShu544(String betCode) {
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
			zhushu = zhushu + mulSelectCodeSPFAndZJQ(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球5*6计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqSPFAndZJQZhuShu545(String betCode) {
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
			zhushu = zhushu + mulSelectCodeSPFAndZJQ(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球6*22计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqSPFAndZJQZhuShu546(String betCode) {
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
			zhushu = zhushu + mulSelectCodeSPFAndZJQ(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球7*35计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqSPFAndZJQZhuShu547(String betCode) {
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
			zhushu = zhushu + mulSelectCodeSPFAndZJQ(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球8*70计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqSPFAndZJQZhuShu548(String betCode) {
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
			zhushu = zhushu + mulSelectCodeSPFAndZJQ(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球6*6计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqSPFAndZJQZhuShu549(String betCode) {
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
			zhushu = zhushu + mulSelectCodeSPFAndZJQ(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球6*7计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqSPFAndZJQZhuShu550(String betCode) {
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
			zhushu = zhushu + mulSelectCodeSPFAndZJQ(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球7*21计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqSPFAndZJQZhuShu551(String betCode) {
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
			zhushu = zhushu + mulSelectCodeSPFAndZJQ(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球8*56计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqSPFAndZJQZhuShu552(String betCode) {
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
			zhushu = zhushu + mulSelectCodeSPFAndZJQ(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球7*7计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqSPFAndZJQZhuShu553(String betCode) {
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
			zhushu = zhushu + mulSelectCodeSPFAndZJQ(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球7*8计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqSPFAndZJQZhuShu554(String betCode) {
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
			zhushu = zhushu + mulSelectCodeSPFAndZJQ(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球8*28计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqSPFAndZJQZhuShu555(String betCode) {
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
			zhushu = zhushu + mulSelectCodeSPFAndZJQ(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球8*8计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqSPFAndZJQZhuShu556(String betCode) {
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
			zhushu = zhushu + mulSelectCodeSPFAndZJQ(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球8*9计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqSPFAndZJQZhuShu557(String betCode) {
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
			zhushu = zhushu + mulSelectCodeSPFAndZJQ(codelength);
		}
		return zhushu;
	}
	
	
	/**
	 * 计算竞彩足球注数(用于半全场和比分)
	 * @param play
	 * @param betCode
	 * 		20101004|1|301|3^20101005|2|201|3^
	 * @return
	 */
	public long getJczqBQCAndBFZhuShu(String play,String betCode){
		long zhushu = 0;
		if("500".equals(play)){
			return getJczqBQCAndBFZhuShu500(betCode);
		}
		if("502".equals(play)){
			return getJczqBQCAndBFZhuShu502(betCode);
		}
		if("503".equals(play)){
			return getJczqBQCAndBFZhuShu503(betCode);
		}
		if("504".equals(play)){
			return getJczqBQCAndBFZhuShu504(betCode);
		}
		if("505".equals(play)){
			return getJczqBQCAndBFZhuShu505(betCode);
		}
		if("506".equals(play)){
			return getJczqBQCAndBFZhuShu506(betCode);
		}
		if("507".equals(play)){
			return getJczqBQCAndBFZhuShu507(betCode);
		}
		if("508".equals(play)){
			return getJczqBQCAndBFZhuShu508(betCode);
		}
		if("526".equals(play)){
			return getJczqBQCAndBFZhuShu526(betCode);
		}
		if("527".equals(play)){
			return getJczqBQCAndBFZhuShu527(betCode);
		}
		if("528".equals(play)){
			return getJczqBQCAndBFZhuShu528(betCode);
		}
		if("529".equals(play)){
			return getJczqBQCAndBFZhuShu529(betCode);
		}
		if("530".equals(play)){
			return getJczqBQCAndBFZhuShu530(betCode);
		}
		if("531".equals(play)){
			return getJczqBQCAndBFZhuShu531(betCode);
		}
		if("532".equals(play)){
			return getJczqBQCAndBFZhuShu532(betCode);
		}
		if("533".equals(play)){
			return getJczqBQCAndBFZhuShu533(betCode);
		}
		if("534".equals(play)){
			return getJczqBQCAndBFZhuShu534(betCode);
		}
		if("535".equals(play)){
			return getJczqBQCAndBFZhuShu535(betCode);
		}
		if("536".equals(play)){
			return getJczqBQCAndBFZhuShu536(betCode);
		}
		if("537".equals(play)){
			return getJczqBQCAndBFZhuShu537(betCode);
		}
		if("538".equals(play)){
			return getJczqBQCAndBFZhuShu538(betCode);
		}
		if("539".equals(play)){
			return getJczqBQCAndBFZhuShu539(betCode);
		}
		if("540".equals(play)){
			return getJczqBQCAndBFZhuShu540(betCode);
		}
		if("541".equals(play)){
			return getJczqBQCAndBFZhuShu541(betCode);
		}
		if("542".equals(play)){
			return getJczqBQCAndBFZhuShu542(betCode);
		}
		if("543".equals(play)){
			return getJczqBQCAndBFZhuShu543(betCode);
		}
		if("544".equals(play)){
			return getJczqBQCAndBFZhuShu544(betCode);
		}
		if("545".equals(play)){
			return getJczqBQCAndBFZhuShu545(betCode);
		}
		if("546".equals(play)){
			return getJczqBQCAndBFZhuShu546(betCode);
		}
		if("547".equals(play)){
			return getJczqBQCAndBFZhuShu547(betCode);
		}
		if("548".equals(play)){
			return getJczqBQCAndBFZhuShu548(betCode);
		}
		if("549".equals(play)){
			return getJczqBQCAndBFZhuShu549(betCode);
		}
		if("550".equals(play)){
			return getJczqBQCAndBFZhuShu550(betCode);
		}
		if("551".equals(play)){
			return getJczqBQCAndBFZhuShu551(betCode);
		}
		if("552".equals(play)){
			return getJczqBQCAndBFZhuShu552(betCode);
		}
		if("553".equals(play)){
			return getJczqBQCAndBFZhuShu553(betCode);
		}
		if("554".equals(play)){
			return getJczqBQCAndBFZhuShu554(betCode);
		}
		if("555".equals(play)){
			return getJczqBQCAndBFZhuShu555(betCode);
		}
		if("556".equals(play)){
			return getJczqBQCAndBFZhuShu556(betCode);
		}
		if("557".equals(play)){
			return getJczqBQCAndBFZhuShu557(betCode);
		}
		return zhushu;
	}
	/**
	 * 足球单关计算注数 
	 * 
	 * @param betCode
	 *            20101004|1|301|3^20101005|2|201|3^
	 * @return
	 */
	public long getJczqBQCAndBFZhuShu500(String betCode) {

		String[] codes = getSelectCode(betCode);
		long zhushu = 0;
		for(String code:codes) {
			zhushu = zhushu + code.length()/2;
		}
		return zhushu;
	}
	/**
	 * 竞彩足球2*1计算注数 
	 * 
	 * @param betCode
	 *            20101004|1|301|3^20101005|2|201|3^
	 * @return
	 */
	public long getJczqBQCAndBFZhuShu502(String betCode) {

		List<List<String>> list = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 2);
		long zhushu = 0;
		for (List<String> codes : list) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeBQCAndBF(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球3*1计算注数 
	 * 
	 * @param betCode
	 *            20101004|1|301|3^20101004|2|201|3^20101006|3|401|0^ 以下类似
	 * @return
	 */
	public long getJczqBQCAndBFZhuShu503(String betCode) {

		List<List<String>> list = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 3);
		long zhushu = 0;
		for (List<String> codes : list) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeBQCAndBF(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球4*1计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqBQCAndBFZhuShu504(String betCode) {

		List<List<String>> list = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 4);
		long zhushu = 0;
		for (List<String> codes : list) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeBQCAndBF(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球5*1计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqBQCAndBFZhuShu505(String betCode) {

		List<List<String>> list = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 5);
		long zhushu = 0;
		for (List<String> codes : list) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeBQCAndBF(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球6*1计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqBQCAndBFZhuShu506(String betCode) {

		List<List<String>> list = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 6);
		long zhushu = 0;
		for (List<String> codes : list) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeBQCAndBF(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球7*1计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqBQCAndBFZhuShu507(String betCode) {

		List<List<String>> list = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 7);
		long zhushu = 0;
		for (List<String> codes : list) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeBQCAndBF(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球8*1计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqBQCAndBFZhuShu508(String betCode) {

		List<List<String>> list = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 8);
		long zhushu = 0;
		for (List<String> codes : list) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeBQCAndBF(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球3*3计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqBQCAndBFZhuShu526(String betCode) {

		List<List<String>> list_cn3 = getbetCodeCollection(
				Arrays.asList(betCode.split("\\^")), 3);

		List<List<String>> list_all = new ArrayList<List<String>>();

		for (List<String> list : list_cn3) {
			list_all.addAll(getbetCodeCollection(list, 2));
		}
		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeBQCAndBF(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球3*4计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqBQCAndBFZhuShu527(String betCode) {

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
			zhushu = zhushu + mulSelectCodeBQCAndBF(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球4*6计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqBQCAndBFZhuShu528(String betCode) {

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
			zhushu = zhushu + mulSelectCodeBQCAndBF(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球4*11计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqBQCAndBFZhuShu529(String betCode) {

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
			zhushu = zhushu + mulSelectCodeBQCAndBF(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球5*10计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqBQCAndBFZhuShu530(String betCode) {

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
			zhushu = zhushu + mulSelectCodeBQCAndBF(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球5*20计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqBQCAndBFZhuShu531(String betCode) {

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
			zhushu = zhushu + mulSelectCodeBQCAndBF(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球5*26计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqBQCAndBFZhuShu532(String betCode) {
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
			zhushu = zhushu + mulSelectCodeBQCAndBF(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球6*15计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqBQCAndBFZhuShu533(String betCode) {
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
			zhushu = zhushu + mulSelectCodeBQCAndBF(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球6*35计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqBQCAndBFZhuShu534(String betCode) {
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
			zhushu = zhushu + mulSelectCodeBQCAndBF(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球6*50计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqBQCAndBFZhuShu535(String betCode) {
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
			zhushu = zhushu + mulSelectCodeBQCAndBF(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球6*57计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqBQCAndBFZhuShu536(String betCode) {
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
			zhushu = zhushu + mulSelectCodeBQCAndBF(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球7*120计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqBQCAndBFZhuShu537(String betCode) {
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
			zhushu = zhushu + mulSelectCodeBQCAndBF(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球8*247计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqBQCAndBFZhuShu538(String betCode) {
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
			zhushu = zhushu + mulSelectCodeBQCAndBF(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球4*4计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqBQCAndBFZhuShu539(String betCode) {
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
			zhushu = zhushu + mulSelectCodeBQCAndBF(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球4*5计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqBQCAndBFZhuShu540(String betCode) {
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
			zhushu = zhushu + mulSelectCodeBQCAndBF(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球5*16计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqBQCAndBFZhuShu541(String betCode) {
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
			zhushu = zhushu + mulSelectCodeBQCAndBF(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球6*20计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqBQCAndBFZhuShu542(String betCode) {
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
			zhushu = zhushu + mulSelectCodeBQCAndBF(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球6*42计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqBQCAndBFZhuShu543(String betCode) {
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
			zhushu = zhushu + mulSelectCodeBQCAndBF(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球5*5计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqBQCAndBFZhuShu544(String betCode) {
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
			zhushu = zhushu + mulSelectCodeBQCAndBF(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球5*6计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqBQCAndBFZhuShu545(String betCode) {
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
			zhushu = zhushu + mulSelectCodeBQCAndBF(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球6*22计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqBQCAndBFZhuShu546(String betCode) {
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
			zhushu = zhushu + mulSelectCodeBQCAndBF(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球7*35计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqBQCAndBFZhuShu547(String betCode) {
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
			zhushu = zhushu + mulSelectCodeBQCAndBF(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球8*70计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqBQCAndBFZhuShu548(String betCode) {
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
			zhushu = zhushu + mulSelectCodeBQCAndBF(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球6*6计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqBQCAndBFZhuShu549(String betCode) {
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
			zhushu = zhushu + mulSelectCodeBQCAndBF(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球6*7计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqBQCAndBFZhuShu550(String betCode) {
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
			zhushu = zhushu + mulSelectCodeBQCAndBF(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球7*21计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqBQCAndBFZhuShu551(String betCode) {
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
			zhushu = zhushu + mulSelectCodeBQCAndBF(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球8*56计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqBQCAndBFZhuShu552(String betCode) {
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
			zhushu = zhushu + mulSelectCodeBQCAndBF(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球7*7计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqBQCAndBFZhuShu553(String betCode) {
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
			zhushu = zhushu + mulSelectCodeBQCAndBF(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球7*8计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqBQCAndBFZhuShu554(String betCode) {
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
			zhushu = zhushu + mulSelectCodeBQCAndBF(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球8*28计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqBQCAndBFZhuShu555(String betCode) {
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
			zhushu = zhushu + mulSelectCodeBQCAndBF(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球8*8计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqBQCAndBFZhuShu556(String betCode) {
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
			zhushu = zhushu + mulSelectCodeBQCAndBF(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球8*9计算注数 
	 * @param betCode
	 * @return
	 */
	public long getJczqBQCAndBFZhuShu557(String betCode) {
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
			zhushu = zhushu + mulSelectCodeBQCAndBF(codelength);
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

	public long mulSelectCodeSPFAndZJQ(String[] selects) {
		long total = 1;
		for (String select : selects) {
			total = total * select.length();
		}
		return total;
	}
	public long mulSelectCodeBQCAndBF(String[] selects) {
		long total = 1;
		for (String select : selects) {
			total = total * select.length()/2;
		}
		return total;
	}
}
