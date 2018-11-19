package jp.mosp.platform.system.vo;

import jp.mosp.platform.system.base.PlatformSystemVo;

/**
 * 工数マスタ画面の情報を格納する。
 */

public class ManHourMasterVo extends PlatformSystemVo{

	private static final long serialVersionUID = 161302234958581089L ;

	private String			txtEditManHourCode;
	private String			txtEditManHourName;


	/**
	 * @return
	 */
	public String getTxtEditManHourCode() {
		return txtEditManHourCode;
	}
	/**
	 * @param txtEditManHourCode
	 */
	public void setTxtEditManHourCode(String txtEditManHourCode) {
		this.txtEditManHourCode = txtEditManHourCode;
	}
	/**
	 * @return
	 */
	public String getTxtEditManHourName() {
		return txtEditManHourName;
	}
	/**
	 * @param txtEditManHourName
	 */
	public void setTxtEditManHourName(String txtEditManHourName) {
		this.txtEditManHourName = txtEditManHourName;
	}

}