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

import jp.mosp.framework.base.MospParams;

/**
 * 名称に関するユーティリティクラス。<br>
 * 
 * プラットフォームにおいて作成される名称は、
 * 全てこのクラスを通じて作成される(予定)。<br>
 * <br>
 */
public class PlatformNamingUtility {
	
	/**
	 * 他クラスからのインスタンス化を防止する。<br>
	 */
	private PlatformNamingUtility() {
		// 処理無し
	}
	
	/**
	 * @param mospParams MosP処理情報
	 * @return 住所情報
	 */
	public static String addressInfo(MospParams mospParams) {
		return mospParams.getName("Address", "Information");
	}
	
	/**
	 * @param mospParams MosP処理情報
	 * @return 電話情報
	 */
	public static String phoneInfo(MospParams mospParams) {
		return mospParams.getName("Phone", "Information");
	}
	
	/**
	 * @param mospParams MosP処理情報
	 * @return 社員コード
	 */
	public static String employeeCode(MospParams mospParams) {
		return mospParams.getName("EmployeeCode");
	}
	
	/**
	 * @param mospParams MosP処理情報
	 * @return 承認者職位等級範囲
	 */
	public static String approverPositionGrade(MospParams mospParams) {
		return mospParams.getName("ApproverPositionGrade");
	}
	
	/**
	 * @param mospParams MosP処理情報
	 * @return 、
	 */
	public static String touten(MospParams mospParams) {
		return mospParams.getName("Touten");
	}
	
	/**
	 * @param mospParams MosP処理情報
	 * @return 空白
	 */
	public static String blank(MospParams mospParams) {
		return mospParams.getName("Blank");
	}
	
	/**
	 * @param mospParams MosP処理情報
	 * @return 自動設定
	 */
	public static String autoNumbering(MospParams mospParams) {
		return mospParams.getName("AutoNumbering");
	}
	
	/**
	 * @param mospParams MosP処理情報
	 * @return 有
	 */
	public static String exsistAbbr(MospParams mospParams) {
		return mospParams.getName("EffectivenessExistence");
	}
	
	/**
	 * @param mospParams MosP処理情報
	 * @return 無
	 */
	public static String notExsistAbbr(MospParams mospParams) {
		return mospParams.getName("InactivateExistence");
	}
	
}
