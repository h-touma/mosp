package jp.mosp.platform.system.action;

import jp.mosp.framework.base.BaseVo;
import jp.mosp.framework.base.MospException;
import jp.mosp.platform.comparator.base.EmploymentContractCodeComparator;
import jp.mosp.platform.system.base.PlatformSystemAction;
import jp.mosp.platform.system.vo.ManHourMasterVo;

public class ManHourMasterAction extends PlatformSystemAction {

	public static final String		CMD_SHOW			= "PF2700";
	public static final String		CMD_SEARCH			= "PF2702";
	public static final String		CMD_SORT			= "PF2704";
	public static final String		CMD_PAGE			= "PF2705";
	public static final String		CMD_REGIST			= "PF2707";
	public static final String		CMD_DELETE			= "PF2709";
	public static final String		CMD_INSERT_MODE		= "PF2711";
	public static final String		CMD_EDIT_MODE		= "PF2712";
	public static final String		CMD_ADD_MODE		= "PF2713";

	public ManHourMasterAction() {
		super();
	}

	@Override
	protected BaseVo getSpecificVo() {
		return new ManHourMasterVo();
	}

	@Override
	public void action() throws MospException {
		// コマンド毎の処理
		if (mospParams.getCommand().equals(CMD_SHOW)) {
			// 表示
			prepareVo(false, false);
			show();
		} else if (mospParams.getCommand().equals(CMD_SEARCH)) {
			// 検索処理
			prepareVo();
//			search();
		} else if (mospParams.getCommand().equals(CMD_SORT)) {
			// ソート
			prepareVo();
//			sort();
		} else if (mospParams.getCommand().equals(CMD_PAGE)) {
			// ページ繰り
			prepareVo();
//			page();
		} else if (mospParams.getCommand().equals(CMD_INSERT_MODE)) {
			// 新規登録モード切替
			prepareVo();
			insertMode();
		} else if (mospParams.getCommand().equals(CMD_EDIT_MODE)) {
			// 履歴編集モード切替
			prepareVo();
//			editMode();
		} else if (mospParams.getCommand().equals(CMD_ADD_MODE)) {
			// 履歴追加モード切替
			prepareVo();
//			addMode();
		} else if (mospParams.getCommand().equals(CMD_REGIST)) {
			// 登録
			prepareVo();
//			regist();
		} else if (mospParams.getCommand().equals(CMD_DELETE)) {
			// 削除
			prepareVo();
//			delete();
		}

	}

	protected void show() throws MospException {
		// VO取得
		ManHourMasterVo vo = (ManHourMasterVo)mospParams.getVo();
		// 基本設定共通VO初期値設定
		initPlatformSystemVoFields();
		// 新規登録モード設定
		insertMode();
		// ページ繰り設定
		setPageInfo(CMD_PAGE, getListLength());
		// ソートキー設定
		vo.setComparatorName(EmploymentContractCodeComparator.class.getName());
	}

	protected void insertMode() throws MospException {
		// 新規登録モード設定
		setEditInsertMode();
		// VO取得
		ManHourMasterVo vo = (ManHourMasterVo)mospParams.getVo();
		// 初期値設定
		vo.setTxtEditManHourCode("");
		vo.setTxtEditManHourName("");
//		vo.setTxtEditPositionAbbr("");
//		vo.setTxtEditPositionGrade("");
//		vo.setTxtEditPositionLevel(DEFAULT_LEVEL);
	}

}