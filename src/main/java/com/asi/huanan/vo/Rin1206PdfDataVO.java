package com.asi.huanan.vo;

import java.util.List;

import com.asi.huanan.service.dao.mybatis.model.Rin1206Brokerdetail;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "Rin1206 Pdf列印資料")
public class Rin1206PdfDataVO {
	/**********************
	 * 			標頭		  *
	 * ********************
	 */
	
	private String rinComSname;//1.
	private String billNo;//帳單號碼
	private String treatyName;//4.
	private String rinComShare;//5.your Share
	private String treatyRate;//6.分出比率treatyRate + treatyMode

//	private String sPeriod;//週期區間
	
	
	/**********************
	 * 			保單		  *
	 * ********************
	 */
	private String pMonth1;//保批單_季_月1
	private String pMonth2;//保批單_季_月2
	private String pMonth3;//保批單_季_月3
	
	private String pTotalPrem1;//保費_季_月1
	private String pTotalPrem2;//保費_季_月2
	private String pTotalPrem3;//保費_季_月3
	private String pSharePrem1;//分出保費_季_月1
	private String pSharePrem2;//分出保費_季_月2
	private String pSharePrem3;//分出保費_季_月3
	
	private String pSum;//保加總負值加上"("__")"

	/**********************
	 * 			批單		  *
	 * ********************
	 */
	
	private String eTotalPrem1;//保費_季_月1
	private String eTotalPrem2;//保費_季_月2
	private String eTotalPrem3;//保費_季_月3
	private String eSharePrem1;//分出保費_季_月1
	private String eSharePrem2;//分出保費_季_月2
	private String eSharePrem3;//分出保費_季_月3
	
	private String eSum;//批加總負值加上"("__")"
	
	/**********************
	 * 			理賠		  *
	 * ********************
	 */
	//business tax
	private String businesstaxRate;
	private String busstax;
	
	//R/I Commission
	private String commRate;
	private String comm;
	//Loss Paid
	private String totalLoss;
	private String loss;
	
	private Long duetoYouLong;//pdf判斷使用
	private String duetoYou;
	private String duetoUs;
	
	
	/**********************
	 * 			其他		  *
	 * ********************
	 */
	//總和
	private String debitTotalSum;
	
	private String creditTotalSum;
	
	//再保人(可能多位?)
	public List<Rin1206Brokerdetail> brokerdetailList;
	private String bRinComId;
	private String bRinComSname;
	private String bRinComShare;
	
	public String getpMonth1() {
		return pMonth1;
	}
	public void setpMonth1(String pMonth1) {
		this.pMonth1 = pMonth1;
	}
	public String getpMonth2() {
		return pMonth2;
	}
	public void setpMonth2(String pMonth2) {
		this.pMonth2 = pMonth2;
	}
	public String getpMonth3() {
		return pMonth3;
	}
	public void setpMonth3(String pMonth3) {
		this.pMonth3 = pMonth3;
	}
	public String getpTotalPrem1() {
		return pTotalPrem1;
	}
	public void setpTotalPrem1(String pTotalPrem1) {
		this.pTotalPrem1 = pTotalPrem1;
	}
	public String getpTotalPrem2() {
		return pTotalPrem2;
	}
	public void setpTotalPrem2(String pTotalPrem2) {
		this.pTotalPrem2 = pTotalPrem2;
	}
	public String getpTotalPrem3() {
		return pTotalPrem3;
	}
	public void setpTotalPrem3(String pTotalPrem3) {
		this.pTotalPrem3 = pTotalPrem3;
	}
	public String getpSharePrem1() {
		return pSharePrem1;
	}
	public void setpSharePrem1(String pSharePrem1) {
		this.pSharePrem1 = pSharePrem1;
	}
	public String getpSharePrem2() {
		return pSharePrem2;
	}
	public void setpSharePrem2(String pSharePrem2) {
		this.pSharePrem2 = pSharePrem2;
	}
	public String getpSharePrem3() {
		return pSharePrem3;
	}
	public void setpSharePrem3(String pSharePrem3) {
		this.pSharePrem3 = pSharePrem3;
	}
	
	public String geteTotalPrem1() {
		return eTotalPrem1;
	}
	public void seteTotalPrem1(String eTotalPrem1) {
		this.eTotalPrem1 = eTotalPrem1;
	}
	public String geteTotalPrem2() {
		return eTotalPrem2;
	}
	public void seteTotalPrem2(String eTotalPrem2) {
		this.eTotalPrem2 = eTotalPrem2;
	}
	public String geteTotalPrem3() {
		return eTotalPrem3;
	}
	public void seteTotalPrem3(String eTotalPrem3) {
		this.eTotalPrem3 = eTotalPrem3;
	}
	public String geteSharePrem1() {
		return eSharePrem1;
	}
	public void seteSharePrem1(String eSharePrem1) {
		this.eSharePrem1 = eSharePrem1;
	}
	public String geteSharePrem2() {
		return eSharePrem2;
	}
	public void seteSharePrem2(String eSharePrem2) {
		this.eSharePrem2 = eSharePrem2;
	}
	public String geteSharePrem3() {
		return eSharePrem3;
	}
	public void seteSharePrem3(String eSharePrem3) {
		this.eSharePrem3 = eSharePrem3;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getRinComShare() {
		return rinComShare;
	}
	public void setRinComShare(String rinComShare) {
		this.rinComShare = rinComShare;
	}
	public String getBusinesstaxRate() {
		return businesstaxRate;
	}
	public void setBusinesstaxRate(String businesstaxRate) {
		this.businesstaxRate = businesstaxRate;
	}
	public String getDuetoYou() {
		return duetoYou;
	}
	public void setDuetoYou(String duetoYou) {
		this.duetoYou = duetoYou;
	}
	public String getDuetoUs() {
		return duetoUs;
	}
	public void setDuetoUs(String duetoUs) {
		this.duetoUs = duetoUs;
	}
	public String getRinComSname() {
		return rinComSname;
	}
	public void setRinComSname(String rinComSname) {
		this.rinComSname = rinComSname;
	}
	public String getTreatyName() {
		return treatyName;
	}
	public void setTreatyName(String treatyName) {
		this.treatyName = treatyName;
	}
	public String getTreatyRate() {
		return treatyRate;
	}
	public void setTreatyRate(String treatyRate) {
		this.treatyRate = treatyRate;
	}
	public String getpSum() {
		return pSum;
	}
	public void setpSum(String pSum) {
		this.pSum = pSum;
	}
	public String geteSum() {
		return eSum;
	}
	public void seteSum(String eSum) {
		this.eSum = eSum;
	}
	public String getBusstax() {
		return busstax;
	}
	public void setBusstax(String busstax) {
		this.busstax = busstax;
	}
	public String getCommRate() {
		return commRate;
	}
	public void setCommRate(String commRate) {
		this.commRate = commRate;
	}
	public String getComm() {
		return comm;
	}
	public void setComm(String comm) {
		this.comm = comm;
	}
	public String getTotalLoss() {
		return totalLoss;
	}
	public void setTotalLoss(String totalLoss) {
		this.totalLoss = totalLoss;
	}
	public String getLoss() {
		return loss;
	}
	public void setLoss(String loss) {
		this.loss = loss;
	}
	public String getDebitTotalSum() {
		return debitTotalSum;
	}
	public void setDebitTotalSum(String debitTotalSum) {
		this.debitTotalSum = debitTotalSum;
	}
	public String getCreditTotalSum() {
		return creditTotalSum;
	}
	public void setCreditTotalSum(String creditTotalSum) {
		this.creditTotalSum = creditTotalSum;
	}
	
	public String getbRinComId() {
		return bRinComId;
	}
	public void setbRinComId(String bRinComId) {
		this.bRinComId = bRinComId;
	}
	public String getbRinComSname() {
		return bRinComSname;
	}
	public void setbRinComSname(String bRinComSname) {
		this.bRinComSname = bRinComSname;
	}
	public String getbRinComShare() {
		return bRinComShare;
	}
	public void setbRinComShare(String bRinComShare) {
		this.bRinComShare = bRinComShare;
	}
	public Long getDuetoYouLong() {
		return duetoYouLong;
	}
	public void setDuetoYouLong(Long duetoYouLong) {
		this.duetoYouLong = duetoYouLong;
	}
	public List<Rin1206Brokerdetail> getBrokerdetailList() {
		return brokerdetailList;
	}
	public void setBrokerdetailList(List<Rin1206Brokerdetail> brokerdetailList) {
		this.brokerdetailList = brokerdetailList;
	}
	
}
