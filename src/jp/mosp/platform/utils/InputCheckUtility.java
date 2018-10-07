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

import jp.mosp.framework.base.MospParams;
import jp.mosp.framework.utils.ValidateUtility;
import jp.mosp.platform.constant.PlatformMessageConst;

/**
 * @author mind
 * 入力チェックに有効な共通機能を提供する。<br/><br/>
 */
public class InputCheckUtility {
	
	/**
	 * 他クラスからのインスタンス化を防止する。<br>
	 */
	/*private InputCheckUtility() {
		// 処理無し
	}*/
	
	/**
	 * 必須入力確認
	 * @param mospParams 共通引数
	 * @param targetValue 対象値
	 * @param targetRep メッセージ置換文字配列
	 */
	public static void checkRequired(MospParams mospParams, Object targetValue, String... targetRep) {
		
		if (!ValidateUtility.chkRequired(targetValue)) {
			// 必須メッセージ設定
			mospParams.addErrorMessage(PlatformMessageConst.MSG_REQUIRED, targetRep);
		}
	}
	
	/**
	 * 必須確認を行う。(インポート共用)<br>
	 * 妥当でない場合は、MosP処理情報にエラーメッセージが加えられる。<br>
	 * @param mospParams	共通設定
	 * @param value     	確認対象
	 * @param fieldName 	確認対象フィールド名
	 * @param row       	行インデックス
	 */
	public static void checkRequiredGeneral(MospParams mospParams, String value, String fieldName, Integer row) {
		// 文字列長(最大文字数)確認
		if (ValidateUtility.chkRequired(value) == false) {
			mospParams
				.addErrorMessage(PlatformMessageConst.MSG_REQUIRED, getRowedFieldName(mospParams, fieldName, row));
		}
	}
	
	/**
	 * 文字列長(最大文字数)を確認する。(インポート共用)<br>
	 * 妥当でない場合は、MosP処理情報にエラーメッセージが加えられる。<br>
	 * @param value     確認対象文字列
	 * @param maxLength 最大文字数
	 * @param fieldName 確認対象フィールド名
	 * @param mospParams 共通設定
	 * @param row       行インデックス
	 */
	public static void checkLengthGeneral(MospParams mospParams, String value, int maxLength, String fieldName,
			Integer row) {
		// 文字列長(最大文字数)確認
		if (ValidateUtility.chkLength(value, maxLength) == false) {
			String[] rep = { getRowedFieldName(mospParams, fieldName, row), String.valueOf(maxLength) };
			mospParams.addErrorMessage(PlatformMessageConst.MSG_MAX_LENGTH_ERR, rep);
		}
	}
	
	/**
	 * 対象文字列が半角英数字であることを確認する。<br>
	 * 妥当でない場合は、MosP処理情報にエラーメッセージが加えられる。<br>
	 * @param mospParams 共通設定
	 * @param value     確認対象文字列
	 * @param fieldName 確認対象フィールド名
	 * @param row       行インデックス
	 */
	public static void checkTypeCodeGeneral(MospParams mospParams, String value, String fieldName, Integer row) {
		// 文字列長(最大文字数)確認
		if (ValidateUtility.chkRegex("[A-Za-z0-9]*", value) == false) {
			// エラーメッセージ追加
			mospParams.addErrorMessage(PlatformMessageConst.MSG_ALP_NUM_CHECK_AMP,
					getRowedFieldName(mospParams, fieldName, row));
		}
	}
	
	/**
	 * 対象文字列が半角英数字であることを確認する。<br>
	 * 妥当でない場合は、MosP処理情報にエラーメッセージが加えられる。<br>
	 * @param mospParams 共通設定
	 * @param value     確認対象文字列
	 * @param fieldName 確認対象フィールド名
	 * @param row       行インデックス
	 */
	public static void checkTypeKanaGeneral(MospParams mospParams, String value, String fieldName, Integer row) {
		// 文字列長(最大文字数)確認
		if (ValidateUtility.chkRegex("[｡-ﾟ -~]*", value) == false) {
			// エラーメッセージ追加
			mospParams.addErrorMessage(PlatformMessageConst.MSG_INPUT_FORM_AMP,
					getRowedFieldName(mospParams, fieldName, row));
		}
	}
	
	/**
	 * 数値妥当性確認
	 * @param mospParams 共通引数
	 * @param value 対象値
	 * @param row 行インデックス
	 * @param targetRep メッセージ置換文字配列
	 */
	public static void checkTypeNumberGeneral(MospParams mospParams, String value, Integer row, String... targetRep) {
		String reqValue = value;
		if (reqValue == null) {
			return;
		}
		// メッセージ作成
		String[] message = new String[]{ getRowedFieldName(mospParams, targetRep[0], row), targetRep[1] };
		
		if (!chkNumber(reqValue)) {
			mospParams.addErrorMessage(PlatformMessageConst.MSG_CHR_TYPE, message);
		}
	}
	
	/**
	 * 行番号が付加されたフィールド名を取得する。<br>
	 * 行インデックスがnullでない場合、エラーメッセージに行番号が加えられる。<br>
	 * @param mospParams	共通設定
	 * @param fieldName 対象フィールド名
	 * @param row       対象行インデックス
	 * @return 行番号が付加されたフィールド名
	 */
	protected static String getRowedFieldName(MospParams mospParams, String fieldName, Integer row) {
		// 対象行インデックス確認
		if (row == null) {
			return fieldName;
		}
		return mospParams.getName("Row") + String.valueOf(row + 1) + mospParams.getName("Colon") + fieldName;
	}
	
	/**
	 * 数値妥当性確認
	 * @param mospParams 共通引数
	 * @param targetValue 対象値
	 * @param targetRep メッセージ置換文字配列
	 */
	public static void checkNumber(MospParams mospParams, String targetValue, String... targetRep) {
		String reqValue = targetValue;
		if (reqValue == null) {
			reqValue = "0";
		}
		if (!chkNumber(reqValue)) {
			mospParams.addErrorMessage(PlatformMessageConst.MSG_CHR_TYPE, targetRep);
		}
	}
	
	/**
	 * コード妥当性確認
	 * @param mospParams 共通引数
	 * @param targetValue 対象値
	 * @param targetRep メッセージ置換文字配列
	 */
	public static void checkCode(MospParams mospParams, String targetValue, String... targetRep) {
		String reqValue = targetValue;
		if (reqValue == null) {
			reqValue = "";
		}
		if (!chkCode(reqValue)) {
			mospParams.addErrorMessage(PlatformMessageConst.MSG_CHR_TYPE, targetRep);
		}
	}
	
	/**
	 * 日付妥当性確認
	 * @param mospParams 共通引数
	 * @param year 年
	 * @param month 月
	 * @param day 日
	 * @param targetRep メッセージ置換文字配列
	 */
	public static void checkDate(MospParams mospParams, int year, int month, int day, String... targetRep) {
		if (!ValidateUtility.chkDate(year, month - 1, day)) {
			// 日付の妥当性チェックメッセージ設定
			mospParams.addErrorMessage(PlatformMessageConst.MSG_INPUT_DATE, targetRep);
		}
	}
	
	/**
	 * 日付妥当性確認
	 * @param mospParams 共通引数
	 * @param year 年
	 * @param month 月
	 * @param day 日
	 * @param targetRep メッセージ置換文字配列
	 */
	public static void checkDate(MospParams mospParams, String year, String month, String day, String... targetRep) {
		try {
			checkDate(mospParams, Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), targetRep);
		} catch (NumberFormatException e) {
			// 日付の妥当性チェックメッセージ設定
			mospParams.addErrorMessage(PlatformMessageConst.MSG_INPUT_DATE, targetRep);
		}
	}
	
	/**
	 * 日付妥当性確認
	 * @param mospParams 共通引数
	 * @param year 年
	 * @param month 月
	 * @param day 日
	 * @param targetRep メッセージ置換文字配列
	 */
	public static void checkDateGeneral(MospParams mospParams, String year, String month, String day,
			String... targetRep) {
		if (year.isEmpty() && month.isEmpty() && day.isEmpty()) {
			return;
		}
		checkDate(mospParams, year, month, day, targetRep);
	}
	
	/**
	 * 時間妥当性確認。<br>
	 * @param mospParams 共通引数
	 * @param hour   時
	 * @param minute 分
	 * @param second 秒
	 * @param targetRep メッセージ置換文字配列
	 */
	public static void checkTime(MospParams mospParams, int hour, int minute, int second, String... targetRep) {
		if (!ValidateUtility.chkTime(hour, minute, second)) {
			// 時間妥当性エラー
			mospParams.addErrorMessage(PlatformMessageConst.MSG_INPUT_DATE, targetRep);
		}
	}
	
	/**
	 * 期間妥当性確認。<br>
	 * @param mospParams 共通引数
	 * @param date      確認対象日
	 * @param startDate 開始日
	 * @param endDate   終了日
	 * @param targetRep メッセージ置換文字配列
	 */
	public static void checkTerm(MospParams mospParams, Date date, Date startDate, Date endDate, String... targetRep) {
		if (date == null || startDate == null || endDate == null) {
			return;
		}
		if (!ValidateUtility.chkTerm(date, startDate, endDate)) {
			// 期間妥当性エラー
			mospParams.addErrorMessage(PlatformMessageConst.MSG_INPUT_DATE, targetRep);
		}
	}
	
	/**
	 * 文字列長確認(最大文字数)。<br>
	 * @param mospParams 共通引数
	 * @param value     確認対象文字列
	 * @param maxLength 最大文字数
	 * @param targetName 対象項目名
	 */
	public static void checkLength(MospParams mospParams, String value, int maxLength, String targetName) {
		if (value == null) {
			return;
		}
		if (!ValidateUtility.chkLength(value, maxLength)) {
			String[] rep = { targetName, String.valueOf(maxLength) };
			// 桁数エラー
			mospParams.addErrorMessage(PlatformMessageConst.MSG_DIGIT_NUMBER, rep);
		}
	}
	
	private static boolean chkCode(String value) {
		return ValidateUtility.chkRegex("\\w*", value);
	}
	
	private static boolean chkNumber(String value) {
		return ValidateUtility.chkRegex("\\d*", value);
	}
	
}
