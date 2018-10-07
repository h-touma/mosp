/*
 * MosP - Mind Open Source Project    http://www.mosp.jp/
 * Copyright (C) MIND Co., Ltd.       http://www.e-mind.co.jp/
 * 
 * This program is free software: you can redistribute it and/or
 * modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package jp.mosp.platform.utils;

import java.util.Date;
import java.util.List;

import jp.mosp.framework.base.MospParams;
import jp.mosp.framework.utils.DateUtility;
import jp.mosp.framework.utils.MessageUtility;
import jp.mosp.framework.utils.MospUtility;
import jp.mosp.platform.constant.PlatformMessageConst;

/**
 * メッセージに関するユーティリティクラス。<br>
 * <br>
 * プラットフォームにおいてサーバ側プログラムで作成されるメッセージは、
 * 全てこのクラスを通じて作成される(予定)。<br>
 * <br>
 */
public class PlatformMessageUtility {
	
	/**
	 * メッセージコード(妥当性確認(数値))。<br>
	 * %1%には数字を入力してください。<br>
	 */
	public static final String	MSG_W_CHECK_NUMERIC				= "PFW0113";
	
	/**
	 * メッセージコード(妥当性確認(日付))。<br>
	 * 正しい日付を入力してください。<br>
	 */
	public static final String	MSG_W_CHECK_DATE				= "PFW0116";
	
	/**
	 * メッセージコード(妥当性確認(小数))。<br>
	 * %1%は、整数部%2%桁以内、小数部%3%桁以内で入力してください。<br>
	 */
	public static final String	MSG_W_CHECK_DECIMAL				= "PFW0130";
	
	/**
	 * メッセージコード(社員の状態確認(肯定)時)。<br>
	 * 該当する社員は%1%しています。<br>
	 */
	public static final String	MSG_W_EMPLOYEE_IS				= "PFW0202";
	
	// TODO PFW0219 PlatformHumanBean.addEmployeeHistoryNotExistMessageを変更
	
	/**
	 * メッセージコード(ワークフロー処理失敗)。<br>
	 * ワークフロー処理に失敗しました。ワークフローを確認してください。<br>
	 */
	public static final String	MSG_W_WORKFLOW_PROCESS_FAILED	= "PFW0229";
	
	/**
	 * メッセージコード(異なる申請によるデータが存在)。<br>
	 * %1%は異なる申請により登録された%2%が存在するため、操作できません。<br>
	 */
	public static final String	MSG_W_OTHER_DATA_EXIST			= "PFW0236";
	
	/**
	 * メッセージコード(未登録)。<br>
	 * 有効日：%1%時点で社員コード：%2%の%3%は登録されていません。
	 */
	public static final String	MSG_W_UNREGISTERED				= "PFW0237";
	
	
	/**
	 * 他クラスからのインスタンス化を防止する。<br>
	 */
	private PlatformMessageUtility() {
		// 処理無し
	}
	
	/**
	 * %1%には数字を入力してください。(PFW0113)<br>
	 * <br>
	 * @param mospParams MosP処理情報
	 * @param fieldName  対象フィールド名
	 * @param row        対象行インデックス
	 */
	public static void addErrorCheckNumeric(MospParams mospParams, String fieldName, Integer row) {
		mospParams.addErrorMessage(MSG_W_CHECK_NUMERIC, getRowedFieldName(mospParams, fieldName, row));
	}
	
	/**
	 * 正しい日付を入力してください。(PFW0116)<br>
	 * <br>
	 * @param mospParams MosP処理情報
	 */
	public static void addErrorCheckDate(MospParams mospParams) {
		mospParams.addErrorMessage(MSG_W_CHECK_DATE);
	}
	
	/**
	 * %1%は、整数部%2%桁以内、小数部%3%桁以内で入力してください。(PFW0130)<br>
	 * <br>
	 * @param mospParams   MosP処理情報
	 * @param fieldName    対象フィールド名
	 * @param integerDigit 整数部桁数
	 * @param decimalDigit 小数部桁数
	 * @param row          対象行インデックス
	 */
	public static void addErrorCheckDecimal(MospParams mospParams, String fieldName, int integerDigit,
			int decimalDigit, Integer row) {
		mospParams.addErrorMessage(MSG_W_CHECK_DECIMAL, getRowedFieldName(mospParams, fieldName, row),
				String.valueOf(integerDigit), String.valueOf(decimalDigit));
	}
	
	/**
	 * 該当する社員が存在しません。(PFW0201)<br>
	 * <br>
	 * @param mospParams MosP処理情報
	 */
	public static void addErrorEmployeeNotExist(MospParams mospParams) {
		mospParams.addErrorMessage(PlatformMessageConst.MSG_NO_ITEM, getNameEmployee(mospParams));
	}
	
	/**
	 * ユーザID：userIdは存在しないため、登録できません。(PFW0214)<br>
	 * <br>
	 * @param mospParams MosP処理情報
	 * @param userId ユーザID
	 * @param row    行インデックス
	 */
	public static void addErrorSelectedUserIdNotExist(MospParams mospParams, String userId, Integer row) {
		String rep = getRowedFieldName(mospParams, getNameUserId(mospParams), row);
		mospParams.addErrorMessage(PlatformMessageConst.MSG_SELECTED_CODE_NOT_EXIST, rep, userId);
	}
	
	/**
	 * ワークフロー処理に失敗しました。ワークフローを確認してください。(PFW0229)<br>
	 * <br>
	 * @param mospParams MosP処理情報
	 */
	public static void addErrorWorkflowProcessFailed(MospParams mospParams) {
		mospParams.addErrorMessage(MSG_W_WORKFLOW_PROCESS_FAILED);
	}
	
	/**
	 * 該当する社員は退職しています。(PFW0202)<br>
	 * @param mospParams MosP処理情報
	 * @param row        行インデックス
	 */
	public static void addErrorEmployeeRetired(MospParams mospParams, Integer row) {
		mospParams.addErrorMessage(MSG_W_EMPLOYEE_IS, getRowedFieldName(mospParams, "", row),
				getNameRetirement(mospParams));
	}
	
	/**
	 * 該当する社員は休職しています。(PFW0202)<br>
	 * @param mospParams MosP処理情報
	 * @param row        行インデックス
	 */
	public static void addErrorEmployeeSuspended(MospParams mospParams, Integer row) {
		mospParams.addErrorMessage(MSG_W_EMPLOYEE_IS, getRowedFieldName(mospParams, "", row),
				getNameSuspension(mospParams));
	}
	
	/**
	 * YYYY/MM/DDは異なる申請により登録された住所情報が存在するため、操作できません。(PFW0236)<br>
	 * @param mospParams   MosP処理情報
	 * @param activateDate 有効日
	 * @param row          行インデックス
	 */
	public static void addErrorOtherAddressExist(MospParams mospParams, Date activateDate, Integer row) {
		mospParams.addErrorMessage(MSG_W_OTHER_DATA_EXIST,
				getRowedFieldName(mospParams, DateUtility.getStringDate(activateDate), row),
				PlatformNamingUtility.addressInfo(mospParams));
	}
	
	/**
	 * YYYY/MM/DDは異なる申請により登録された電話情報が存在するため、操作できません。(PFW0236)<br>
	 * @param mospParams   MosP処理情報
	 * @param activateDate 有効日
	 * @param row          行インデックス
	 */
	public static void addErrorOtherPhoneExist(MospParams mospParams, Date activateDate, Integer row) {
		mospParams.addErrorMessage(MSG_W_OTHER_DATA_EXIST,
				getRowedFieldName(mospParams, DateUtility.getStringDate(activateDate), row),
				PlatformNamingUtility.phoneInfo(mospParams));
	}
	
	/**
	 * 有効日：%1%時点で社員コード：%2%の%3%は登録されていません。(PFW0237)<br>
	 * @param mospParams   MosP処理情報
	 * @param activateDate 有効日
	 * @param employeeCode 社員コード
	 */
	public static void addErrorUnregisteredMailAddress(MospParams mospParams, Date activateDate, String employeeCode) {
		mospParams.addErrorMessage(MSG_W_UNREGISTERED, DateUtility.getStringDate(activateDate), employeeCode,
				getNameMailAddress(mospParams));
	}
	
	/**
	 * %1%には%2%を入力してください。(PFW0237)<br>
	 * <br>
	 * 利用可能文字列を%2%に入れる。<br>
	 * <br>
	 * @param mospParams MosP処理情報
	 * @param fieldName  対象フィールド名
	 * @param availables 利用可能文字列リスト
	 * @param row        行インデックス
	 */
	public static void addErrorAvailableChars(MospParams mospParams, String fieldName, List<String> availables,
			Integer row) {
		mospParams.addErrorMessage(PlatformMessageConst.MSG_CHR_TYPE, getRowedFieldName(mospParams, fieldName, row),
				getAvailableCharString(mospParams, availables));
	}
	
	/**
	 * {@link MessageUtility#getRowedFieldName(MospParams, String, Integer)}
	 * を実行する。<br>
	 * @param mospParams MosP処理情報
	 * @param fieldName 対象フィールド名
	 * @param row       対象行インデックス
	 * @return 行番号が付加されたフィールド名
	 */
	protected static String getRowedFieldName(MospParams mospParams, String fieldName, Integer row) {
		// 行番号が付加されたフィールド名を取得
		return MessageUtility.getRowedFieldName(mospParams, fieldName, row);
	}
	
	/**
	 * 下書しました。<br>
	 * <br>
	 * @param mospParams MosP処理情報
	 * @return メッセージ
	 */
	public static String getDraftSucceed(MospParams mospParams) {
		return mospParams.getMessage(PlatformMessageConst.MSG_PROCESS_SUCCEED, getNameDraft(mospParams));
	}
	
	/**
	 * 社員コード名称を取得する。<br>
	 * @param mospParams MosP処理情報
	 * @return 社員コード名称
	 */
	public static String getNameEmployeeCode(MospParams mospParams) {
		return PlatformNamingUtility.employeeCode(mospParams);
	}
	
	/**
	 * 有効日名称を取得する。<br>
	 * @param mospParams MosP処理情報
	 * @return 有効日名称
	 */
	public static String getNameActivateDate(MospParams mospParams) {
		return mospParams.getName("ActivateDate");
	}
	
	/**
	 * 社員名称を取得する。<br>
	 * @param mospParams MosP処理情報
	 * @return 社員名称
	 */
	public static String getNameEmployee(MospParams mospParams) {
		return mospParams.getName("Employee");
	}
	
	/**
	 * ユーザID名称を取得する。<br>
	 * @param mospParams MosP処理情報
	 * @return ユーザID名称
	 */
	public static String getNameUserId(MospParams mospParams) {
		return mospParams.getName("User", "Id");
	}
	
	/**
	 * 下書名称を取得する。<br>
	 * @param mospParams MosP処理情報
	 * @return 下書名称
	 */
	public static String getNameDraft(MospParams mospParams) {
		return mospParams.getName("WorkPaper");
	}
	
	/**
	 * 退職名称を取得する。<br>
	 * @param mospParams MosP処理情報
	 * @return 退職名称
	 */
	public static String getNameRetirement(MospParams mospParams) {
		return mospParams.getName("RetirementOn");
	}
	
	/**
	 * 休職名称を取得する。<br>
	 * @param mospParams MosP処理情報
	 * @return 休職名称
	 */
	public static String getNameSuspension(MospParams mospParams) {
		return mospParams.getName("RetirementLeave");
	}
	
	/**
	 * メールアドレス名称を取得する。<br>
	 * @param mospParams MosP処理情報
	 * @return メールアドレス名称
	 */
	public static String getNameMailAddress(MospParams mospParams) {
		return mospParams.getName("MailAddress");
	}
	
	/**
	 * 利用可能文字列リストをメッセージ用文字列に変換する。<br>
	 * <br>
	 * ""の場合は、空白と出力する。<br>
	 * <br>
	 * @param mospParams MosP処理情報
	 * @param availables 利用可能文字列リスト
	 * @return メッセージ用文字列
	 */
	public static String getAvailableCharString(MospParams mospParams, List<String> availables) {
		// 配列を準備
		String[] array = new String[availables.size()];
		// インデックス準備
		int i = 0;
		// 利用可能文字毎に処理
		for (String available : availables) {
			// ""の場合は空白に変換
			array[i++] = available.equals("") ? PlatformNamingUtility.blank(mospParams) : available;
		}
		return MospUtility.toSeparatedString(array, PlatformNamingUtility.touten(mospParams));
	}
	
}
