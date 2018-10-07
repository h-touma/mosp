/**
 *
 */
package jp.mosp.jasperreport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * JasperPrintで出力する中間パラメータクラス。
 */
public class JasperReportIntermediate implements Serializable {
	
	private static final long	serialVersionUID	= -3719638470694011714L;
	
	/**
	 * MosPアプリケーション設定キー(テンプレートファイルパス)。<br>
	 * 出力ファイル名としても用いられる。<br>
	 */
	private final String		appReport;
	
	/**
	 * データソース。<br>
	 */
	private final List<?>		list;
	
	
	/**
	 * コンストラクタ。
	 * @param appReport MosPアプリケーション設定キー(テンプレートファイルパス)
	 * @param list 出力対象リスト
	 */
	public JasperReportIntermediate(String appReport, List<?> list) {
		this.appReport = appReport;
		this.list = list == null ? new ArrayList<Object>() : list;
	}
	
	/**
	 * @return MosPアプリケーション設定キー(テンプレートファイルパス)
	 */
	public String getAppReport() {
		return appReport;
	}
	
	/**
	 * @return list 出力対象リスト
	 */
	public List<?> getList() {
		return list;
	}
	
}
