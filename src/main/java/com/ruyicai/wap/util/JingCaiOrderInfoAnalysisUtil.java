package com.ruyicai.wap.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ruyicai.wap.util.bet.JingCaiUtil;
import com.ruyicai.wap.util.bet.LotteryUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Component
public class JingCaiOrderInfoAnalysisUtil {
	@Autowired
	SelectAllUtil selectAllUtil;
	@Autowired
	LotteryUtil lotteryUtil;
	@Autowired
	JingCaiUtil jingCaiUtil;
	@Autowired 
	JingCaiHhBetCodeParseUtil jingCaiHhBetCodeParseUtil;
	/**
	 * 竞彩解析orderInfo
	 * @param lotNo
	 * @param orderInfo
	 * @return
	 */
	public String orderInfoAnalysis(String lotNo,String orderId,String orderInfo){
//		JSONArray resultArray = new JSONArray();
//		String lineBreak = "<br/>";
		String betCodeView = "";
		JSONObject tlotsValueObject = selectAllUtil.selectJingCaiTlotsValueObject(orderId);
		if(tlotsValueObject!=null){
			JSONArray list = tlotsValueObject.getJSONArray("list");
			if(list!=null&&list.size()>0){
				if(Constants.LOTNO_JC_ZQ_HHGG.equals(lotNo)){
					JSONArray jingCaiHH = jingCaiHhBetCodeParseUtil.getHHParseBetCodeList(tlotsValueObject,orderInfo);
					String play = "";
					for(int a=0;a<jingCaiHH.size();a++){
						JSONObject betContentHtml = jingCaiHH.getJSONObject(a);
						String danMaString = "";
						if(!"".equals(betContentHtml.getString("isDanMa"))&&betContentHtml.getString("isDanMa")!=null){
							danMaString = "<p><span>"+betContentHtml.getString("isDanMa")+"</span></p>";
						}
						play = "<p><h4>玩法:"+betContentHtml.getString("play")+"</h4></p>";
						betCodeView+="<p>"+jingCaiUtil.getWeekByWeekId(betContentHtml.getString("weekId"))+"&nbsp;&nbsp;"+betContentHtml.getString("teamId")+"</p>"
								+"<p>"+betContentHtml.getString("homeTeam")+"(主)vs"+betContentHtml.getString("guestTeam")+"(客)</p>"
								+"<p>您的投注:</p>"+danMaString+"<p>"+betContentHtml.getString("betContentHtml")+"</p>"
								+(a<jingCaiHH.size()-1?"<div class='xuxian-line'>&nbsp;</div>":"");
					}
					return play+betCodeView;
				}else if(Constants.LOTNO_JC_LQ_HHGG.equals(lotNo)){
					JSONArray jingCaiHH = jingCaiHhBetCodeParseUtil.getHHParseBetCodeList(tlotsValueObject,orderInfo);
					String play = "";
					for(int a=0;a<jingCaiHH.size();a++){
						JSONObject betContentHtml = jingCaiHH.getJSONObject(a);
						String danMaString = "";
						if(!"".equals(betContentHtml.getString("isDanMa"))&&betContentHtml.getString("isDanMa")!=null){
							danMaString = "<p><span>"+betContentHtml.getString("isDanMa")+"</span></p>";
						}
						play = "<p><h4>玩法:"+betContentHtml.getString("play")+"</h4></p>";
						betCodeView+="<p>"+jingCaiUtil.getWeekByWeekId(betContentHtml.getString("weekId"))+"&nbsp;&nbsp;"+betContentHtml.getString("teamId")+"</p>"
								+"<p>"+betContentHtml.getString("guestTeam")+"(客)vs"+betContentHtml.getString("homeTeam")+"(主)</p>"
								+"<p>您的投注:</p>"+danMaString+"<p>"+betContentHtml.getString("betContentHtml")+"</p>"
								+(a<jingCaiHH.size()-1?"<div class='xuxian-line'>&nbsp;</div>":"");
					}
					return play+betCodeView;
				}else if(Constants.LOTNO_JC_ZQ_SPF.equals(lotNo)||Constants.LOTNO_JC_ZQ_RQSPF.equals(lotNo)||Constants.LOTNO_JC_ZQ_BCSPF.equals(lotNo)||Constants.LOTNO_JC_ZQ_BF.equals(lotNo)||Constants.LOTNO_JC_ZQ_ZJQ.equals(lotNo)){
					JSONArray jingCaiHH = jingCaiHhBetCodeParseUtil.getParseBetCodeList(tlotsValueObject,orderInfo,lotNo);
					String play = "";
					String vs ="";
					for(int a=0;a<jingCaiHH.size();a++){
						JSONObject betContentHtml = jingCaiHH.getJSONObject(a);
						String danMaString = "";
						if(!"".equals(betContentHtml.getString("isDanMa"))&&betContentHtml.getString("isDanMa")!=null){
							danMaString = "<p><span>"+betContentHtml.getString("isDanMa")+"</span></p>";
						}
						if(Constants.LOTNO_JC_ZQ_RQSPF.equals(lotNo)&&betContentHtml.containsKey("let")){
							vs = " "+betContentHtml.getString("let")+" ";
						}else{
							vs="vs";
						}
						play = "<p><h4>玩法:"+betContentHtml.getString("play")+"</h4></p>";
						betCodeView+="<p>"+jingCaiUtil.getWeekByWeekId(betContentHtml.getString("weekId"))+"&nbsp;&nbsp;"+betContentHtml.getString("teamId")+"</p>"
								+"<p>"+betContentHtml.getString("homeTeam")+"(主)"+vs+betContentHtml.getString("guestTeam")+"(客)</p>"
								+"<p>您的投注:</p>"+danMaString+"<p>"+betContentHtml.getString("betContentHtml")+"</p>"+betContentHtml.getString("result")
								+(a<jingCaiHH.size()-1?"<div class='xuxian-line'>&nbsp;</div>":"");
					}
					return play+betCodeView;
				}else if(Constants.LOTNO_JC_LQ_SF.equals(lotNo)||Constants.LOTNO_JC_LQ_SFC.equals(lotNo)){
					JSONArray jingCaiHH = jingCaiHhBetCodeParseUtil.getParseBetCodeList(tlotsValueObject,orderInfo,lotNo);
					String play = "";
					for(int a=0;a<jingCaiHH.size();a++){
						JSONObject betContentHtml = jingCaiHH.getJSONObject(a);
						String danMaString = "";
						if(!"".equals(betContentHtml.getString("isDanMa"))&&betContentHtml.getString("isDanMa")!=null){
							danMaString = "<p><span>"+betContentHtml.getString("isDanMa")+"</span></p>";
						}
						play = "<p><h4>玩法:"+betContentHtml.getString("play")+"</h4></p>";
						betCodeView+="<p>"+jingCaiUtil.getWeekByWeekId(betContentHtml.getString("weekId"))+"&nbsp;&nbsp;"+betContentHtml.getString("teamId")+"</p>"
								+"<p>"+betContentHtml.getString("guestTeam")+"(客)vs"+betContentHtml.getString("homeTeam")+"(主)</p>"
								+"<p>您的投注:</p>"+danMaString+"<p>"+betContentHtml.getString("betContentHtml")+"</p>"+betContentHtml.getString("result")
								+(a<jingCaiHH.size()-1?"<div class='xuxian-line'>&nbsp;</div>":"");
					}
					return play+betCodeView;
				}else if(Constants.LOTNO_JC_LQ_RFSF.equals(lotNo)){
					JSONArray jingCaiHH = jingCaiHhBetCodeParseUtil.getParseBetCodeList(tlotsValueObject,orderInfo,lotNo);
					String play = "";
					for(int a=0;a<jingCaiHH.size();a++){
						JSONObject betContentHtml = jingCaiHH.getJSONObject(a);
						String danMaString = "";
						if(!"".equals(betContentHtml.getString("isDanMa"))&&betContentHtml.getString("isDanMa")!=null){
							danMaString = "<p><span>"+betContentHtml.getString("isDanMa")+"</span></p>";
						}
						String letScore = "";
						if(!"".equals(betContentHtml.getString("letScore"))&&betContentHtml.getString("letScore")!=null){
							letScore = "("+betContentHtml.getString("letScore")+")";
						}
						play = "<p><h4>玩法:"+betContentHtml.getString("play")+"</h4></p>";
						betCodeView+="<p>"+jingCaiUtil.getWeekByWeekId(betContentHtml.getString("weekId"))+"&nbsp;&nbsp;"+betContentHtml.getString("teamId")+"</p>"
								+"<p>"+betContentHtml.getString("guestTeam")+"(客)"+(letScore==null||"".equals(letScore)?"vs":letScore)+betContentHtml.getString("homeTeam")+"(主)</p>"
								+"<p>您的投注:</p>"+danMaString+"<p>"+betContentHtml.getString("betContentHtml")+"</p>"+betContentHtml.getString("result")
								+(a<jingCaiHH.size()-1?"<div class='xuxian-line'>&nbsp;</div>":"");
					}
					return play+betCodeView;
				}else if(Constants.LOTNO_JC_LQ_DXF.equals(lotNo)){
					JSONArray jingCaiHH = jingCaiHhBetCodeParseUtil.getParseBetCodeList(tlotsValueObject,orderInfo,lotNo);
					String play = "";
					for(int a=0;a<jingCaiHH.size();a++){
						JSONObject betContentHtml = jingCaiHH.getJSONObject(a);
						String danMaString = "";
						if(!"".equals(betContentHtml.getString("isDanMa"))&&betContentHtml.getString("isDanMa")!=null){
							danMaString = "<p><span>"+betContentHtml.getString("isDanMa")+"</span></p>";
						}
						String totalScore = "";
						if(!"".equals(betContentHtml.getString("totalScore"))&&betContentHtml.getString("totalScore")!=null){
							totalScore = "("+betContentHtml.getString("totalScore")+")";
						}
						play = "<p><h4>玩法:"+betContentHtml.getString("play")+"</h4></p>";
						betCodeView+="<p>"+jingCaiUtil.getWeekByWeekId(betContentHtml.getString("weekId"))+"&nbsp;&nbsp;"+betContentHtml.getString("teamId")+"</p>"
								+"<p>"+betContentHtml.getString("guestTeam")+"(客)"+(totalScore==null||"".equals(totalScore)?"vs":totalScore)+betContentHtml.getString("homeTeam")+"(主)</p>"
								+"<p>您的投注:</p>"+danMaString+"<p>"+betContentHtml.getString("betContentHtml")+"</p>"+betContentHtml.getString("result")
								+(a<jingCaiHH.size()-1?"<div class='xuxian-line'>&nbsp;</div>":"");
					}
					return play+betCodeView;
				}
				for(int i=0;i<list.size();i++){
					JSONObject tlotObject = list.getJSONObject(i);
					String betCode = tlotObject.getString("betcode"); //注码
					String peiLvString = tlotObject.getString("peilu"); //赔率(20120520*7*001*|1:12.0|3:12.0|^20120520*7*002*|1:12.0|3:12.0|^)
					//502@20120409|1|001|3^20120409|1|002|10^
//					JSONObject object = new JSONObject();
//					object.put("lotName", lotteryUtil.getLotNameByLotNo(lotNo));
					String[] betCodes = betCode.split("@");
					String wanfa = betCodes[0]; //玩法
//					object.put("play", jingCaiUtil.getJingCaiPlay(wanfa));
					betCodeView +=  "<p><h4>玩法:"+jingCaiUtil.getJingCaiPlay(wanfa).getString("play")+"</h4></p>";
					StringBuilder builder = new StringBuilder();
					String[] codes = betCodes[1].split("\\^");
					int n = 0;
					for (String code : codes) {
						String[] split = code.split("\\|");
						String day = split[0];
						String weekid = split[1];
						String teamid = split[2];
						//判断是否是胆码
						String danMaString = jingCaiUtil.getDanMaString(orderInfo, code.substring(0, code.lastIndexOf("|"))).getString("result");
						//获取比赛信息
						JSONObject matchesValueObject = selectAllUtil.selectJingCaiMatchesValueObject(lotNo, day, weekid, teamid);
						if (matchesValueObject==null||matchesValueObject.getString("matches")==null
								||matchesValueObject.getString("matches").equals("null")) {
							continue;
						}
						JSONObject matchesObject = matchesValueObject.getJSONObject("matches");
						String team = matchesObject.getString("team");
						String zhu = ""; //主队
						String ke = ""; //客队
						if (team!=null&&!team.trim().equals("null")&&team.indexOf(":")>-1) {
							String[] teams = team.split(":");
							zhu = teams[0];
							ke = teams[1];
						}
						
						//比赛结果
						String score = "未开";
						String result = "未开";
						if (matchesValueObject!=null) {
							String resultString = matchesValueObject.getString("result");
							if (resultString!=null&&!resultString.equals("null")) {
								JSONObject resultObject = matchesValueObject.getJSONObject("result");
								String scoreString = resultObject.getString("result");
								if (scoreString!=null&&!scoreString.equals("null")) {
									if (Constants.LOTNO_JC_ZQ_BCSPF.equals(lotNo)||Constants.LOTNO_JC_ZQ_BF.equals(lotNo)||Constants.LOTNO_JC_ZQ_SPF.equals(lotNo)||Constants.LOTNO_JC_ZQ_RQSPF.equals(lotNo)||Constants.LOTNO_JC_ZQ_ZJQ.equals(lotNo)) { //竟彩足球
										score = scoreString;
									} else { //竟彩篮球
										if (scoreString!=null&&scoreString.indexOf(":")>-1) {
											String[] scores = scoreString.split(":");
											score = scores[1]+":"+scores[0];
										}
									}
								}
								result = jingCaiUtil.getJingCaiResultByScore(scoreString, true); //赛果
							}
						}
						
						String league = matchesObject.getString("league"); //联赛
						String id = day+"*"+weekid+"*"+teamid+"*"; //赛事信息
						
						builder.append("<p>"+jingCaiUtil.getWeekByWeekId(weekid)+"&nbsp;&nbsp;"+teamid+"&nbsp;&nbsp;"+league+"</p>");
						String betContent = ""; //投注内容
						if (lotNo!=null&&lotNo.equals("J00001")) { //竞彩足球胜平负
							betContent = jingCaiUtil.getBetContentForJ00001(id, peiLvString, split[3]);
							if("主胜".equals(result)){
								builder.append("<p><span>"+zhu+"(主)</span>vs"+ke+"(客)</p>");
							}else if("主负".equals(result)){
								builder.append("<p>"+zhu+"(主)vs<span>"+ke+"(客)</span></p>");
							}else{
								builder.append("<p>"+zhu+"(主)vs"+ke+"(客)</p>");
							}
							
						} else if (lotNo!=null&&lotNo.equals("J00002")) { //竞彩足球比分
							betContent = jingCaiUtil.getBetContentForJ00002(id, peiLvString, split[3]);
							if("主胜".equals(result)){
								builder.append("<p><span>"+zhu+"(主)</span>vs"+ke+"(客)</p>");
							}else if("主负".equals(result)){
								builder.append("<p>"+zhu+"(主)vs<span>"+ke+"(客)</span></p>");
							}else{
								builder.append("<p>"+zhu+"(主)vs"+ke+"(客)</p>");
							}
						} else if (lotNo!=null&&lotNo.equals("J00003")) { //竞彩足球总进球数
							betContent = jingCaiUtil.getBetContentForJ00003(id, peiLvString, split[3]);
							if("主胜".equals(result)){
								builder.append("<p><span>"+zhu+"(主)</span>vs"+ke+"(客)</p>");
							}else if("主负".equals(result)){
								builder.append("<p>"+zhu+"(主)vs<span>"+ke+"(客)</span></p>");
							}else{
								builder.append("<p>"+zhu+"(主)vs"+ke+"(客)</p>");
							}
						} else if (lotNo!=null&&lotNo.equals("J00004")) { //竞彩足球半全场
							betContent = jingCaiUtil.getBetContentForJ00004(id, peiLvString, split[3]);
							if("主胜".equals(result)){
								builder.append("<p><span>"+zhu+"(主)</span>vs"+ke+"(客)</p>");
							}else if("主负".equals(result)){
								builder.append("<p>"+zhu+"(主)vs<span>"+ke+"(客)</span></p>");
							}else{
								builder.append("<p>"+zhu+"(主)vs"+ke+"(客)</p>");
							}
						} else if (lotNo!=null&&lotNo.equals("J00005")) { //竞彩篮球胜负
							betContent = jingCaiUtil.getBetContentForJ00005(id, peiLvString, split[3]);
							if("主胜".equals(result)){
								builder.append("<p>"+ke+"(客)vs<span>"+zhu+"(主)</span></p>");
							}else if("主负".equals(result)){
								builder.append("<p><span>"+ke+"(客)</span>vs"+zhu+"(主)</p>");
							}else{
								builder.append("<p>"+ke+"(客)vs"+zhu+"(主)</p>");
							}
						} else if (lotNo!=null&&lotNo.equals("J00006")) { //竞彩篮球让分胜负
							JSONObject codeObject = jingCaiUtil.getBetContentForJ00006(id, peiLvString, split[3]);
							betContent = codeObject.getString("betContent");
							String letPoint = codeObject.getString("letPoint"); //让分
							
							if (letPoint!=null&&!"".equals(letPoint)) {
								if("主胜".equals(result)){
									builder.append("<p>"+ke+"(客)("+letPoint+")<span>"+zhu+"(主)</span></p>");
								}else if("主负".equals(result)){
									builder.append("<p><span>"+ke+"(客)</span>("+letPoint+")"+zhu+"(主)</p>");
								}else{
									builder.append("<p>"+ke+"(客)("+letPoint+")"+zhu+"(主)</p>");
								}
							} else {
								if("主胜".equals(result)){
									builder.append("<p>"+ke+"(客)vs<span>"+zhu+"(主)</span></p>");
								}else if("主负".equals(result)){
									builder.append("<p><span>"+ke+"(客)</span>vs"+zhu+"(主)</p>");
								}else{
									builder.append("<p>"+ke+"(客)vs"+zhu+"(主)</p>");
								}
							}
						} else if (lotNo!=null&&lotNo.equals("J00007")) { //竞彩篮球胜分差
							betContent = jingCaiUtil.getBetContentForJ00007(id, peiLvString, split[3]);
							if("主胜".equals(result)){
								builder.append("<p>"+ke+"(客)vs<span>"+zhu+"(主)</span></p>");
							}else if("主负".equals(result)){
								builder.append("<p><span>"+ke+"(客)</span>vs"+zhu+"(主)</p>");
							}else{
								builder.append("<p>"+ke+"(客)vs"+zhu+"(主)</p>");
							}
						} else if (lotNo!=null&&lotNo.equals("J00008")) { //竞彩篮球大小分
							JSONObject codeObject = jingCaiUtil.getBetContentForJ00008(id, peiLvString, split[3]);
							betContent = codeObject.getString("betContent");
							String basePoint = codeObject.getString("basePoint"); //基本分
							
							if (basePoint!=null&&!"".equals(basePoint)) {
								if("主胜".equals(result)){
									builder.append("<p>"+ke+"(客)("+basePoint+")<span>"+zhu+"(主)</span></p>");
								}else if("主负".equals(result)){
									builder.append("<p><span>"+ke+"(客)</span>("+basePoint+")"+zhu+"(主)</p>");
								}else{
									builder.append("<p>"+ke+"(客)("+basePoint+")"+zhu+"(主)</p>");
								}
							} else {
								if("主胜".equals(result)){
									builder.append("<p>"+ke+"(客)vs<span>"+zhu+"(主)</span></p>");
								}else if("主负".equals(result)){
									builder.append("<p><span>"+ke+"(客)</span>vs"+zhu+"(主)</p>");
								}else{
									builder.append("<p>"+ke+"(客)vs"+zhu+"(主)</p>");
								}
							}
						}
						String s = "";
						if(!"".equals(danMaString)&&danMaString!=null){
							s = "<p><span>"+danMaString+"</span></p>";
						}
						builder.append(s+"<p>您的投注:</p><p><span>"+betContent).append("</span></p><p>全场比分:<span>"+score+"</span></p>")
								.append("<p>赛果:<span>"+result+";</span></p>");
						n++;
						if(n<codes.length){
							builder.append("<div class='xuxian-line'>&nbsp;</div>");
						}
//						builder.substring(0, builder.indexOf("<div class='xuxian-line'>&nbsp;</div>"));

					}
//					if(i<list.size()-1){
//						builder.append("<div class='xuxian-line'>&nbsp;</div>");
//					}
					betCodeView += builder.toString();
				}
			}else{
				tlotsValueObject = selectAllUtil.selectJingCaiTlotsByTorderValueObject(orderId);
				if(Constants.LOTNO_JC_ZQ_HHGG.equals(lotNo)){
					JSONArray jingCaiHH = jingCaiHhBetCodeParseUtil.getHHParseBetCodeList(tlotsValueObject,orderInfo);
					String play = "";
					for(int a=0;a<jingCaiHH.size();a++){
						JSONObject betContentHtml = jingCaiHH.getJSONObject(a);
						String danMaString = "";
						if(!"".equals(betContentHtml.getString("isDanMa"))&&betContentHtml.getString("isDanMa")!=null){
							danMaString = "<p><span>"+betContentHtml.getString("isDanMa")+"</span></p>";
						}
						play = "<p><h4>玩法:"+betContentHtml.getString("play")+"</h4></p>";
						betCodeView+="<p>"+jingCaiUtil.getWeekByWeekId(betContentHtml.getString("weekId"))+"&nbsp;&nbsp;"+betContentHtml.getString("teamId")+"</p>"
								+"<p>"+betContentHtml.getString("homeTeam")+"(主)vs"+betContentHtml.getString("guestTeam")+"(客)</p>"
								+"<p>您的投注:</p>"+danMaString+"<p>"+betContentHtml.getString("betContentHtml")+"</p>"
								+(a<jingCaiHH.size()-1?"<div class='xuxian-line'>&nbsp;</div>":"");
					}
					return play+betCodeView;
				}else if(Constants.LOTNO_JC_LQ_HHGG.equals(lotNo)){
					JSONArray jingCaiHH = jingCaiHhBetCodeParseUtil.getHHParseBetCodeList(tlotsValueObject,orderInfo);
					String play = "";
					for(int a=0;a<jingCaiHH.size();a++){
						JSONObject betContentHtml = jingCaiHH.getJSONObject(a);
						String danMaString = "";
						if(!"".equals(betContentHtml.getString("isDanMa"))&&betContentHtml.getString("isDanMa")!=null){
							danMaString = "<p><span>"+betContentHtml.getString("isDanMa")+"</span></p>";
						}
						play = "<p><h4>玩法:"+betContentHtml.getString("play")+"</h4></p>";
						betCodeView+="<p>"+jingCaiUtil.getWeekByWeekId(betContentHtml.getString("weekId"))+"&nbsp;&nbsp;"+betContentHtml.getString("teamId")+"</p>"
								+"<p>"+betContentHtml.getString("guestTeam")+"(客)vs"+betContentHtml.getString("homeTeam")+"(主)</p>"
								+"<p>您的投注:</p>"+danMaString+"<p>"+betContentHtml.getString("betContentHtml")+"</p>"
								+(a<jingCaiHH.size()-1?"<div class='xuxian-line'>&nbsp;</div>":"");
					}
					return play+betCodeView;
				}else if(Constants.LOTNO_JC_ZQ_SPF.equals(lotNo)||Constants.LOTNO_JC_ZQ_RQSPF.equals(lotNo)||Constants.LOTNO_JC_ZQ_BCSPF.equals(lotNo)||Constants.LOTNO_JC_ZQ_BF.equals(lotNo)||Constants.LOTNO_JC_ZQ_ZJQ.equals(lotNo)){
					JSONArray jingCaiHH = jingCaiHhBetCodeParseUtil.getParseBetCodeList(tlotsValueObject,orderInfo,lotNo);
					String play = "";
					String vs="";
					for(int a=0;a<jingCaiHH.size();a++){
						JSONObject betContentHtml = jingCaiHH.getJSONObject(a);
						String danMaString = "";
						if(!"".equals(betContentHtml.getString("isDanMa"))&&betContentHtml.getString("isDanMa")!=null){
							danMaString = "<p><span>"+betContentHtml.getString("isDanMa")+"</span></p>";
						}
						if(!"".equals(betContentHtml.getString("isDanMa"))&&betContentHtml.getString("isDanMa")!=null){
							danMaString = "<p><span>"+betContentHtml.getString("isDanMa")+"</span></p>";
						}
						if(Constants.LOTNO_JC_ZQ_RQSPF.equals(lotNo)&&betContentHtml.containsKey("let")){
							vs = " "+betContentHtml.getString("let")+" ";
						}else{
							vs="vs";
						}
						play = "<p><h4>玩法:"+betContentHtml.getString("play")+"</h4></p>";
						betCodeView+="<p>"+jingCaiUtil.getWeekByWeekId(betContentHtml.getString("weekId"))+"&nbsp;&nbsp;"+betContentHtml.getString("teamId")+"</p>"
								+"<p>"+betContentHtml.getString("homeTeam")+"(主)"+vs+betContentHtml.getString("guestTeam")+"(客)</p>"
								+"<p>您的投注:</p>"+danMaString+"<p>"+betContentHtml.getString("betContentHtml")+"</p>"+betContentHtml.getString("result")
								+(a<jingCaiHH.size()-1?"<div class='xuxian-line'>&nbsp;</div>":"");
					}
					return play+betCodeView;
				}else if(Constants.LOTNO_JC_LQ_SF.equals(lotNo)||Constants.LOTNO_JC_LQ_SFC.equals(lotNo)){
					JSONArray jingCaiHH = jingCaiHhBetCodeParseUtil.getParseBetCodeList(tlotsValueObject,orderInfo,lotNo);
					String play = "";
					for(int a=0;a<jingCaiHH.size();a++){
						JSONObject betContentHtml = jingCaiHH.getJSONObject(a);
						String danMaString = "";
						if(!"".equals(betContentHtml.getString("isDanMa"))&&betContentHtml.getString("isDanMa")!=null){
							danMaString = "<p><span>"+betContentHtml.getString("isDanMa")+"</span></p>";
						}
						play = "<p><h4>玩法:"+betContentHtml.getString("play")+"</h4></p>";
						betCodeView+="<p>"+jingCaiUtil.getWeekByWeekId(betContentHtml.getString("weekId"))+"&nbsp;&nbsp;"+betContentHtml.getString("teamId")+"</p>"
								+"<p>"+betContentHtml.getString("guestTeam")+"(客)vs"+betContentHtml.getString("homeTeam")+"(主)</p>"
								+"<p>您的投注:</p>"+danMaString+"<p>"+betContentHtml.getString("betContentHtml")+"</p>"+betContentHtml.getString("result")
								+(a<jingCaiHH.size()-1?"<div class='xuxian-line'>&nbsp;</div>":"");
					}
					return play+betCodeView;
				}else if(Constants.LOTNO_JC_LQ_RFSF.equals(lotNo)){
					JSONArray jingCaiHH = jingCaiHhBetCodeParseUtil.getParseBetCodeList(tlotsValueObject,orderInfo,lotNo);
					String play = "";
					for(int a=0;a<jingCaiHH.size();a++){
						JSONObject betContentHtml = jingCaiHH.getJSONObject(a);
						String danMaString = "";
						if(!"".equals(betContentHtml.getString("isDanMa"))&&betContentHtml.getString("isDanMa")!=null){
							danMaString = "<p><span>"+betContentHtml.getString("isDanMa")+"</span></p>";
						}
						String letScore = "";
						if(!"".equals(betContentHtml.getString("letScore"))&&betContentHtml.getString("letScore")!=null){
							letScore = "("+betContentHtml.getString("letScore")+")";
						}
						play = "<p><h4>玩法:"+betContentHtml.getString("play")+"</h4></p>";
						betCodeView+="<p>"+jingCaiUtil.getWeekByWeekId(betContentHtml.getString("weekId"))+"&nbsp;&nbsp;"+betContentHtml.getString("teamId")+"</p>"
								+"<p>"+betContentHtml.getString("guestTeam")+"(客)"+(letScore==null||"".equals(letScore)?"vs":letScore)+betContentHtml.getString("homeTeam")+"(主)</p>"
								+"<p>您的投注:</p>"+danMaString+"<p>"+betContentHtml.getString("betContentHtml")+"</p>"+betContentHtml.getString("result")
								+(a<jingCaiHH.size()-1?"<div class='xuxian-line'>&nbsp;</div>":"");
					}
					return play+betCodeView;
				}else if(Constants.LOTNO_JC_LQ_DXF.equals(lotNo)){
					JSONArray jingCaiHH = jingCaiHhBetCodeParseUtil.getParseBetCodeList(tlotsValueObject,orderInfo,lotNo);
					String play = "";
					for(int a=0;a<jingCaiHH.size();a++){
						JSONObject betContentHtml = jingCaiHH.getJSONObject(a);
						String danMaString = "";
						if(!"".equals(betContentHtml.getString("isDanMa"))&&betContentHtml.getString("isDanMa")!=null){
							danMaString = "<p><span>"+betContentHtml.getString("isDanMa")+"</span></p>";
						}
						String totalScore = "";
						if(!"".equals(betContentHtml.getString("totalScore"))&&betContentHtml.getString("totalScore")!=null){
							totalScore = "("+betContentHtml.getString("totalScore")+")";
						}
						play = "<p><h4>玩法:"+betContentHtml.getString("play")+"</h4></p>";
						betCodeView+="<p>"+jingCaiUtil.getWeekByWeekId(betContentHtml.getString("weekId"))+"&nbsp;&nbsp;"+betContentHtml.getString("teamId")+"</p>"
								+"<p>"+betContentHtml.getString("guestTeam")+"(客)"+(totalScore==null||"".equals(totalScore)?"vs":totalScore)+betContentHtml.getString("homeTeam")+"(主)</p>"
								+"<p>您的投注:</p>"+danMaString+"<p>"+betContentHtml.getString("betContentHtml")+"</p>"+betContentHtml.getString("result")
								+(a<jingCaiHH.size()-1?"<div class='xuxian-line'>&nbsp;</div>":"");
					}
					return play+betCodeView;
				}
				if(tlotsValueObject!=null){
					list = tlotsValueObject.getJSONArray("list");
					if(list!=null&&list.size()>0){
						for(int i=0;i<list.size();i++){
							JSONObject tlotObject = list.getJSONObject(i);
							String betCode = tlotObject.getString("betcode"); //注码
							String peiLvString = tlotObject.getString("peilu"); //赔率(20120520*7*001*|1:12.0|3:12.0|^20120520*7*002*|1:12.0|3:12.0|^)
							//502@20120409|1|001|3^20120409|1|002|10^
//							JSONObject object = new JSONObject();
//							object.put("lotName", lotteryUtil.getLotNameByLotNo(lotNo));
							String[] betCodes = betCode.split("@");
							String wanfa = betCodes[0]; //玩法
//							object.put("play", jingCaiUtil.getJingCaiPlay(wanfa));
							betCodeView +=  "<p><h4>玩法:"+jingCaiUtil.getJingCaiPlay(wanfa).getString("play")+"</h4></p>";
							StringBuilder builder = new StringBuilder();
							String[] codes = betCodes[1].split("\\^");
							int n=0;
							for (String code : codes) {
								String[] split = code.split("\\|");
								String day = split[0];
								String weekid = split[1];
								String teamid = split[2];
								//判断是否是胆码
								String danMaString = jingCaiUtil.getDanMaString(orderInfo, code.substring(0, code.lastIndexOf("|"))).getString("result");
								//获取比赛信息
								JSONObject matchesValueObject = selectAllUtil.selectJingCaiMatchesValueObject(lotNo, day, weekid, teamid);
								if (matchesValueObject==null||matchesValueObject.getString("matches")==null
										||matchesValueObject.getString("matches").equals("null")) {
									continue;
								}
								JSONObject matchesObject = matchesValueObject.getJSONObject("matches");
								String team = matchesObject.getString("team");
								String zhu = ""; //主队
								String ke = ""; //客队
								if (team!=null&&!team.trim().equals("null")&&team.indexOf(":")>-1) {
									String[] teams = team.split(":");
									zhu = teams[0];
									ke = teams[1];
								}
								
								//比赛结果
								String score = "未开";
								String result = "未开";
								if (matchesValueObject!=null) {
									String resultString = matchesValueObject.getString("result");
									if (resultString!=null&&!resultString.equals("null")) {
										JSONObject resultObject = matchesValueObject.getJSONObject("result");
										String scoreString = resultObject.getString("result");
										if (scoreString!=null&&!scoreString.equals("null")) {
											if (Constants.LOTNO_JC_ZQ_BCSPF.equals(lotNo)||Constants.LOTNO_JC_ZQ_BF.equals(lotNo)||Constants.LOTNO_JC_ZQ_SPF.equals(lotNo)||Constants.LOTNO_JC_ZQ_RQSPF.equals(lotNo)||Constants.LOTNO_JC_ZQ_ZJQ.equals(lotNo)) { //竟彩足球
												score = scoreString;
											} else { //竟彩篮球
												if (scoreString!=null&&scoreString.indexOf(":")>-1) {
													String[] scores = scoreString.split(":");
													score = scores[1]+":"+scores[0];
												}
											}
										}
										result = jingCaiUtil.getJingCaiResultByScore(scoreString, true); //赛果
									}
								}
								
								String league = matchesObject.getString("league"); //联赛
								String id = day+"*"+weekid+"*"+teamid+"*"; //赛事信息
								
								builder.append("<p>"+jingCaiUtil.getWeekByWeekId(weekid)+"&nbsp;&nbsp;"+teamid+"&nbsp;&nbsp;"+league+"</p>");
								String betContent = ""; //投注内容
								if (lotNo!=null&&lotNo.equals("J00001")) { //竞彩足球胜平负
									betContent = jingCaiUtil.getBetContentForJ00001(id, peiLvString, split[3]);
									if("主胜".equals(result)){
										builder.append("<p><span>"+zhu+"(主)</span>vs"+ke+"(客)</p>");
									}else if("主负".equals(result)){
										builder.append("<p>"+zhu+"(主)vs<span>"+ke+"(客)</span></p>");
									}else{
										builder.append("<p>"+zhu+"(主)vs"+ke+"(客)</p>");
									}
									
								} else if (lotNo!=null&&lotNo.equals("J00002")) { //竞彩足球比分
									betContent = jingCaiUtil.getBetContentForJ00002(id, peiLvString, split[3]);
									if("主胜".equals(result)){
										builder.append("<p><span>"+zhu+"(主)</span>vs"+ke+"(客)</p>");
									}else if("主负".equals(result)){
										builder.append("<p>"+zhu+"(主)vs<span>"+ke+"(客)</span></p>");
									}else{
										builder.append("<p>"+zhu+"(主)vs"+ke+"(客)</p>");
									}
								} else if (lotNo!=null&&lotNo.equals("J00003")) { //竞彩足球总进球数
									betContent = jingCaiUtil.getBetContentForJ00003(id, peiLvString, split[3]);
									if("主胜".equals(result)){
										builder.append("<p><span>"+zhu+"(主)</span>vs"+ke+"(客)</p>");
									}else if("主负".equals(result)){
										builder.append("<p>"+zhu+"(主)vs<span>"+ke+"(客)</span></p>");
									}else{
										builder.append("<p>"+zhu+"(主)vs"+ke+"(客)</p>");
									}
								} else if (lotNo!=null&&lotNo.equals("J00004")) { //竞彩足球半全场
									betContent = jingCaiUtil.getBetContentForJ00004(id, peiLvString, split[3]);
									if("主胜".equals(result)){
										builder.append("<p><span>"+zhu+"(主)</span>vs"+ke+"(客)</p>");
									}else if("主负".equals(result)){
										builder.append("<p>"+zhu+"(主)vs<span>"+ke+"(客)</span></p>");
									}else{
										builder.append("<p>"+zhu+"(主)vs"+ke+"(客)</p>");
									}
								} else if (lotNo!=null&&lotNo.equals("J00005")) { //竞彩篮球胜负
									betContent = jingCaiUtil.getBetContentForJ00005(id, peiLvString, split[3]);
									if("主胜".equals(result)){
										builder.append("<p>"+ke+"(客)vs<span>"+zhu+"(主)</span></p>");
									}else if("主负".equals(result)){
										builder.append("<p><span>"+ke+"(客)</span>vs"+zhu+"(主)</p>");
									}else{
										builder.append("<p>"+ke+"(客)vs"+zhu+"(主)</p>");
									}
								} else if (lotNo!=null&&lotNo.equals("J00006")) { //竞彩篮球让分胜负
									JSONObject codeObject = jingCaiUtil.getBetContentForJ00006(id, peiLvString, split[3]);
									betContent = codeObject.getString("betContent");
									String letPoint = codeObject.getString("letPoint"); //让分
									
									if (letPoint!=null&&!"".equals(letPoint)) {
										if("主胜".equals(result)){
											builder.append("<p>"+ke+"(客)("+letPoint+")<span>"+zhu+"(主)</span></p>");
										}else if("主负".equals(result)){
											builder.append("<p><span>"+ke+"(客)</span>("+letPoint+")"+zhu+"(主)</p>");
										}else{
											builder.append("<p>"+ke+"(客)("+letPoint+")"+zhu+"(主)</p>");
										}
									} else {
										if("主胜".equals(result)){
											builder.append("<p>"+ke+"(客)vs<span>"+zhu+"(主)</span></p>");
										}else if("主负".equals(result)){
											builder.append("<p><span>"+ke+"(客)</span>vs"+zhu+"(主)</p>");
										}else{
											builder.append("<p>"+ke+"(客)vs"+zhu+"(主)</p>");
										}
									}
								} else if (lotNo!=null&&lotNo.equals("J00007")) { //竞彩篮球胜分差
									betContent = jingCaiUtil.getBetContentForJ00007(id, peiLvString, split[3]);
									if("主胜".equals(result)){
										builder.append("<p>"+ke+"(客)vs<span>"+zhu+"(主)</span></p>");
									}else if("主负".equals(result)){
										builder.append("<p><span>"+ke+"(客)</span>vs"+zhu+"(主)</p>");
									}else{
										builder.append("<p>"+ke+"(客)vs"+zhu+"(主)</p>");
									}
								} else if (lotNo!=null&&lotNo.equals("J00008")) { //竞彩篮球大小分
									JSONObject codeObject = jingCaiUtil.getBetContentForJ00008(id, peiLvString, split[3]);
									betContent = codeObject.getString("betContent");
									String basePoint = codeObject.getString("basePoint"); //基本分
									
									if (basePoint!=null&&!"".equals(basePoint)) {
										if("主胜".equals(result)){
											builder.append("<p>"+ke+"(客)("+basePoint+")<span>"+zhu+"(主)</span></p>");
										}else if("主负".equals(result)){
											builder.append("<p><span>"+ke+"(客)</span>("+basePoint+")"+zhu+"(主)</p>");
										}else{
											builder.append("<p>"+ke+"(客)("+basePoint+")"+zhu+"(主)</p>");
										}
									} else {
										if("主胜".equals(result)){
											builder.append("<p>"+ke+"(客)vs<span>"+zhu+"(主)</span></p>");
										}else if("主负".equals(result)){
											builder.append("<p><span>"+ke+"(客)</span>vs"+zhu+"(主)</p>");
										}else{
											builder.append("<p>"+ke+"(客)vs"+zhu+"(主)</p>");
										}
									}
								}
								String s = "";
								if(!"".equals(danMaString)&&danMaString!=null){
									s = "<p><span>"+danMaString+"</span></p>";
								}
								builder.append(s+"<p>您的投注:</p><p><span>"+betContent).append("</span></p><p>全场比分:<span>"+score+"</span></p>")
										.append("<p>赛果:<span>"+result+";</span></p>");
								n++;
								if(n<codes.length){
									builder.append("<div class='xuxian-line'>&nbsp;</div>");
								}
//								builder.substring(0, builder.indexOf("<div class='xuxian-line'>&nbsp;</div>"));
							}
//							if(i<list.size()-1){
//								builder.append("<div class='xuxian-line'>&nbsp;</div>");
//							}
							betCodeView += builder.toString();
						}
					}
				}
			}
		}
		return betCodeView;
	}
	/**
	 * 竞彩解析orderInfo
	 * @param lotNo
	 * @param orderInfo
	 * @return
	 */
	public String orderInfoAnalysisByOrder(String lotNo,String orderId,String orderInfo){
//		JSONArray resultArray = new JSONArray();
		String lineBreak = "<br/>";
		String betCodeView = "";
		JSONObject tlotsValueObject = selectAllUtil.selectJingCaiTlotsByTorderValueObject(orderId);
		if(tlotsValueObject!=null){
			JSONArray list = tlotsValueObject.getJSONArray("list");
			if(list!=null&&list.size()>0){
				for(int i=0;i<list.size();i++){
					JSONObject tlotObject = list.getJSONObject(i);
					String betCode = tlotObject.getString("betcode"); //注码
					String peiLvString = tlotObject.getString("peilu"); //赔率(20120520*7*001*|1:12.0|3:12.0|^20120520*7*002*|1:12.0|3:12.0|^)
					//502@20120409|1|001|3^20120409|1|002|10^
//					JSONObject object = new JSONObject();
//					object.put("lotName", lotteryUtil.getLotNameByLotNo(lotNo));
					String[] betCodes = betCode.split("@");
					String wanfa = betCodes[0]; //玩法
//					object.put("play", jingCaiUtil.getJingCaiPlay(wanfa));
					betCodeView +=  "玩法:"+jingCaiUtil.getJingCaiPlay(wanfa).getString("play")+lineBreak;
					StringBuilder builder = new StringBuilder();
					String[] codes = betCodes[1].split("\\^");
					for (String code : codes) {
						String[] split = code.split("\\|");
						String day = split[0];
						String weekid = split[1];
						String teamid = split[2];
						//判断是否是胆码
						String danMaString = jingCaiUtil.getDanMaString(orderInfo, code.substring(0, code.lastIndexOf("|"))).getString("result");
						//获取比赛信息
						JSONObject matchesValueObject = selectAllUtil.selectJingCaiMatchesValueObject(lotNo, day, weekid, teamid);
						if (matchesValueObject==null||matchesValueObject.getString("matches")==null
								||matchesValueObject.getString("matches").equals("null")) {
							continue;
						}
						JSONObject matchesObject = matchesValueObject.getJSONObject("matches");
						String team = matchesObject.getString("team");
						String zhu = ""; //主队
						String ke = ""; //客队
						if (team!=null&&!team.trim().equals("null")&&team.indexOf(":")>-1) {
							String[] teams = team.split(":");
							zhu = teams[0];
							ke = teams[1];
						}
						
						//比赛结果
						String score = "未开";
						String result = "未开";
						if (matchesValueObject!=null) {
							String resultString = matchesValueObject.getString("result");
							if (resultString!=null&&!resultString.equals("null")) {
								JSONObject resultObject = matchesValueObject.getJSONObject("result");
								String scoreString = resultObject.getString("result");
								if (scoreString!=null&&!scoreString.equals("null")) {
									if (Constants.LOTNO_JC_ZQ_BCSPF.equals(lotNo)||Constants.LOTNO_JC_ZQ_BF.equals(lotNo)||Constants.LOTNO_JC_ZQ_SPF.equals(lotNo)||Constants.LOTNO_JC_ZQ_RQSPF.equals(lotNo)||Constants.LOTNO_JC_ZQ_ZJQ.equals(lotNo)) { //竟彩足球
										score = scoreString;
									} else { //竟彩篮球
										if (scoreString!=null&&scoreString.indexOf(":")>-1) {
											String[] scores = scoreString.split(":");
											score = scores[1]+":"+scores[0];
										}
									}
								}
								result = jingCaiUtil.getJingCaiResultByScore(scoreString, true); //赛果
							}
						}
						
						String league = matchesObject.getString("league"); //联赛
						String id = day+"*"+weekid+"*"+teamid+"*"; //赛事信息
						
						builder.append(jingCaiUtil.getWeekByWeekId(weekid)+" "+teamid+" "+league+" ");
						String betContent = ""; //投注内容
						if (lotNo!=null&&lotNo.equals("J00001")) { //竞彩足球胜平负
							betContent = jingCaiUtil.getBetContentForJ00001(id, peiLvString, split[3]);
							builder.append(zhu+"vs"+ke);
						} else if (lotNo!=null&&lotNo.equals("J00002")) { //竞彩足球比分
							betContent = jingCaiUtil.getBetContentForJ00002(id, peiLvString, split[3]);
							builder.append(zhu+"vs"+ke);
						} else if (lotNo!=null&&lotNo.equals("J00003")) { //竞彩足球总进球数
							betContent = jingCaiUtil.getBetContentForJ00003(id, peiLvString, split[3]);
							builder.append(zhu+"vs"+ke);
						} else if (lotNo!=null&&lotNo.equals("J00004")) { //竞彩足球半全场
							betContent = jingCaiUtil.getBetContentForJ00004(id, peiLvString, split[3]);
							builder.append(zhu+"vs"+ke);
						} else if (lotNo!=null&&lotNo.equals("J00005")) { //竞彩篮球胜负
							betContent = jingCaiUtil.getBetContentForJ00005(id, peiLvString, split[3]);
							builder.append(ke+"vs"+zhu);
						} else if (lotNo!=null&&lotNo.equals("J00006")) { //竞彩篮球让分胜负
							JSONObject codeObject = jingCaiUtil.getBetContentForJ00006(id, peiLvString, split[3]);
							betContent = codeObject.getString("betContent");
							String letPoint = codeObject.getString("letPoint"); //让分
							
							if (letPoint!=null&&!"".equals(letPoint)) {
								builder.append(ke+"("+letPoint+")"+zhu);	
							} else {
								builder.append(ke+"vs"+zhu);
							}
						} else if (lotNo!=null&&lotNo.equals("J00007")) { //竞彩篮球胜分差
							betContent = jingCaiUtil.getBetContentForJ00007(id, peiLvString, split[3]);
							builder.append(ke+"vs"+zhu);
						} else if (lotNo!=null&&lotNo.equals("J00008")) { //竞彩篮球大小分
							JSONObject codeObject = jingCaiUtil.getBetContentForJ00008(id, peiLvString, split[3]);
							betContent = codeObject.getString("betContent");
							String basePoint = codeObject.getString("basePoint"); //基本分
							
							if (basePoint!=null&&!"".equals(basePoint)) {
								builder.append(ke+"("+basePoint+")"+zhu);
							} else {
								builder.append(ke+"vs"+zhu);
							}
						}
						builder.append(danMaString+lineBreak+"您的投注:"+betContent).append(lineBreak+"全场比分:"+score)
								.append(lineBreak+"赛果:"+result+";").append(lineBreak);
					}
//					if (builder.toString().endsWith(lineBreak)) {
//						builder = builder.delete(builder.length()-lineBreak.length(), builder.length());
//					}
//					object.put("betCode", builder.toString());
					betCodeView += builder.toString();
//					resultArray.add(object);
				}
			}
		}
		return betCodeView;
	}

}
