package com.ruyicai.wap.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class Constants {
	/****************************彩种--lotno*******************************/
	/**
	 * 七乐彩LOTNO
	 */
	public static final String LOTNO_QLC = "F47102";
	/**
	 * 七乐彩LOTNAME
	 */
	public static final String LOTNAME_QLC = "七乐彩";
	/**
	 * 福彩3DLOTNO
	 */
	public static final String LOTNO_FC3D = "F47103";
	/**
	 * 福彩3DLOTNAME
	 */
	public static final String LOTNAME_FC3D = "福彩3D";
	/**
	 * 双色球LOTNO
	 */
	public static final String LOTNO_SSQ = "F47104";
	/**
	 * 双色球LOTNAME
	 */
	public static final String LOTNAME_SSQ = "双色球";
	/**
	 * 大乐透LOTNO
	 */
	public static final String LOTNO_DLT = "T01001";
	/**
	 * 大乐透LOTNAME
	 */
	public static final String LOTNAME_DLT = "大乐透";
	/**
	 * 排列三LOTNO
	 */
	public static final String LOTNO_PL3 = "T01002";
	/**
	 * 排列三LOTNAME
	 */
	public static final String LOTNAME_PL3 = "排列三";
	/**
	 * 足彩胜负彩14场LOTNO
	 */
	public static final String LOTNO_ZC_SFC_14C = "T01003";
	/**
	 * 足彩胜负彩14场LOTNAME
	 */
	public static final String LOTNAME_ZC_SFC_14C = "足彩胜负彩";
	/**
	 * 足彩任九场LOTNO
	 */
	public static final String LOTNO_ZC_R9C = "T01004";
	/**
	 * 足彩任九场LOTNAME
	 */
	public static final String LOTNAME_ZC_R9C = "足彩任九场";
	/**
	 * 足彩四场进球彩LOTNO
	 */
	public static final String LOTNO_ZC_4C_JQC = "T01005";
	/**
	 * 足彩四场进球彩LOTNAME
	 */
	public static final String LOTNAME_ZC_4C_JQC = "足彩四场进球";
	/**
	 * 足彩六场半全场LOTNO
	 */
	public static final String LOTNO_ZC_6C_BQC = "T01006";
	/**
	 * 足彩六场半全场LOTNAME
	 */
	public static final String LOTNAME_ZC_6C_BQC = "足彩六场半";
	/**
	 * 时时彩LOTNO
	 */
	public static final String LOTNO_SSC = "T01007";
	/**
	 * 时时彩LOTNAME
	 */
	public static final String LOTNAME_SSC = "时时彩";
	/**
	 * 七星彩LOTNO
	 */
	public static final String LOTNO_QXC = "T01009";
	/**
	 * 七星彩LOTNAME
	 */
	public static final String LOTNAME_QXC = "七星彩";
	/**
	 * 多乐彩——江西11选5LOTNO
	 */
	public static final String LOTNO_DLC_JX11X5 = "T01010";
	/**
	 * 多乐彩——江西11选5LOTNAME
	 */
	public static final String LOTNAME_DLC_JX11X5 = "江西11选5";
	/**
	 * 排列五LOTNO
	 */
	public static final String LOTNO_PL5 = "T01011";
	/**
	 * 排列五LOTNAME
	 */
	public static final String LOTNAME_PL5 = "排列五";
	/**
	 * 十一运夺金——山东11选5LOTNO
	 */
	public static final String LOTNO_11YDJ_SD11X5 = "T01012";
	/**
	 * 十一运夺金——山东11选5LOTNAME
	 */
	public static final String LOTNAME_11YDJ_SD11X5 = "十一运夺金";
	/**
	 * 22选5LOTNO
	 */
	public static final String LOTNO_22X5 = "T01013";
	/**
	 * 22选5LOTNAME
	 */
	public static final String LOTNAME_22X5 = "22选5";
	/**
	 * 广东11选5LOTNO
	 */
	public static final String LOTNO_GD11X5 = "T01014";
	/**
	 * 广东11选5LOTNAME
	 */
	public static final String LOTNAME_GD11X5 = "广东11选5";
	/**
	 * 广东快乐十分LOTNO
	 */
	public static final String LOTNO_GDKL10F = "T01015";
	/**
	 * 广东快乐十分LOTNAME
	 */
	public static final String LOTNAME_GDKL10F = "广东快乐十分";
	/**
	 * 竞彩足球胜平负LOTNO
	 */
	public static final String LOTNO_JC_ZQ_RQSPF = "J00013";
	/**
	 * 竞彩足球球胜平负LOTNAME
	 */
	public static final String LOTNAME_JC_ZQ_RQSPF = "竞彩足球让球胜平负";
	/**
	 * 竞彩足球球胜平负LOTNO
	 */
	public static final String LOTNO_JC_ZQ_SPF = "J00001";
	/**
	 * 竞彩足球胜平负LOTNAME
	 */
	public static final String LOTNAME_JC_ZQ_SPF = "竞彩足球胜平负";
	/**
	 * 竞彩足球比分LOTNO
	 */
	public static final String LOTNO_JC_ZQ_BF = "J00002";
	/**
	 * 竞彩足球比分LOTNAME
	 */
	public static final String LOTNAME_JC_ZQ_BF = "竞彩足球比分";
	/**
	 * 竞彩足球总进球LOTNO
	 */
	public static final String LOTNO_JC_ZQ_ZJQ = "J00003";
	/**
	 * 竞彩足球总进球LOTNAME
	 */
	public static final String LOTNAME_JC_ZQ_ZJQ = "竞彩足球总进球";
	/**
	 * 竞彩足球半场胜平负LOTNO
	 */
	public static final String LOTNO_JC_ZQ_BCSPF = "J00004";
	/**
	 * 竞彩足球半场胜平负LOTNAME
	 */
	public static final String LOTNAME_JC_ZQ_BCSPF = "竞彩足球半全场";
	/**
	 * 竞彩篮球胜负LOTNO
	 */
	public static final String LOTNO_JC_LQ_SF = "J00005";
	/**
	 * 竞彩篮球胜负LOTNAME
	 */
	public static final String LOTNAME_JC_LQ_SF = "竞彩篮球胜负";
	/**
	 * 竞彩篮球让分胜负LOTNO
	 */
	public static final String LOTNO_JC_LQ_RFSF = "J00006";
	/**
	 * 竞彩篮球让分胜负LOTNAME
	 */
	public static final String LOTNAME_JC_LQ_RFSF = "竞彩篮球让分胜负";
	/**
	 * 竞彩篮球胜分差LOTNO
	 */
	public static final String LOTNO_JC_LQ_SFC = "J00007";
	/**
	 * 竞彩篮球胜分差LOTNAME
	 */
	public static final String LOTNAME_JC_LQ_SFC = "竞彩篮球胜分差";
	/**
	 * 竞彩篮球大小分LOTNO
	 */
	public static final String LOTNO_JC_LQ_DXF = "J00008";
	/**
	 * 竞彩篮球大小分LOTNAME
	 */
	public static final String LOTNAME_JC_LQ_DXF = "竞彩篮球大小分";
	/**
	 * 竞彩篮球混合过关LOTNO
	 */
	public static final String LOTNO_JC_LQ_HHGG = "J00012";
	/**
	 * 竞彩篮球混合过关LOTNAME
	 */
	public static final String LOTNAME_JC_LQ_HHGG = "竞彩篮球混合过关";
	/**
	 * 竞彩足球混合过关LOTNO
	 */
	public static final String LOTNO_JC_ZQ_HHGG = "J00011";
	/**
	 * 竞彩足球混合过关LOTNAME
	 */
	public static final String LOTNAME_JC_ZQ_HHGG = "竞彩足球混合过关";
	/**
	 * 内蒙快三LOTNO
	 */
	public static final String LOTNO_NMK3 = "F47107";
	/**
	 * 内蒙快三LOTNAME
	 */
	public static final String LOTNAME_NMK3 = "内蒙快三";
	/**
	 * 北单胜平负LOTNO
	 */
	public static final String LOTNO_BD_SPF = "B00001";
	/**
	 * 北单胜平负LOTNAME
	 */
	public static final String LOTNAME_BD_SPF = "北单胜平负";
	/**
	 * 北单总进球LOTNO
	 */
	public static final String LOTNO_BD_ZJQ = "B00002";
	/**
	 * 北单总进球LOTNAME
	 */
	public static final String LOTNAME_BD_ZJQ = "北单总进球";
	/**
	 * 北单半全场LOTNO
	 */
	public static final String LOTNO_BD_BQC = "B00003";
	/**
	 * 北单半全场LOTNAME
	 */
	public static final String LOTNAME_BD_BQC = "北单半全场";
	/**
	 * 北单上下盘单双LOTNO
	 */
	public static final String LOTNO_BD_SXPDS = "B00004";
	/**
	 * 北单上下盘单双LOTNAME
	 */
	public static final String LOTNAME_BD_SXPDS = "北单上下盘单双";
	/**
	 * 北单单场比分LOTNO
	 */
	public static final String LOTNO_BD_DCBF = "B00005";
	/**
	 * 北单单场比分LOTNAME
	 */
	public static final String LOTNAME_BD_DCBF = "北单单场比分";

}
