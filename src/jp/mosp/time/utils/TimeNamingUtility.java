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
package jp.mosp.time.utils;

import jp.mosp.framework.base.MospParams;

/**
 * 名称に関するユーティリティクラス。<br>
 * <br>
 * 勤怠管理システムにおいて作成される名称は、
 * 全てこのクラスを通じて作成される。<br>
 * <br>
 */
public class TimeNamingUtility {
	
	/**
	 * 他クラスからのインスタンス化を防止する。<br>
	 */
	private TimeNamingUtility() {
		// 処理無し
	}
	
	/**
	 * @param mospParams MosP処理情報
	 * @return 所定休日
	 */
	public static String prescribedHoliday(MospParams mospParams) {
		return mospParams.getName("PrescribedHoliday");
	}
	
	/**
	 * @param mospParams MosP処理情報
	 * @return 法定休日
	 */
	public static String legalHoliday(MospParams mospParams) {
		return mospParams.getName("LegalHoliday");
	}
	
	/**
	 * @param mospParams MosP処理情報
	 * @return 法定振替休日
	 */
	public static String legalTransferHoliday(MospParams mospParams) {
		return mospParams.getName("LegalTransferHoliday");
	}
	
	/**
	 * @param mospParams MosP処理情報
	 * @return 所定振替休日
	 */
	public static String prescribedTransferHoliday(MospParams mospParams) {
		return mospParams.getName("PrescribedTransferHoliday");
	}
	
	/**
	 * 遅名称を取得する。<br>
	 * @param mospParams MosP処理情報
	 * @return 遅
	 */
	public static String getLateAbbrNaming(MospParams mospParams) {
		return mospParams.getName("LateAbbr");
	}
	
	/**
	 * 早名称を取得する。<br>
	 * @param mospParams MosP処理情報
	 * @return 早
	 */
	public static String getEarlyAbbrNaming(MospParams mospParams) {
		return mospParams.getName("EarlyAbbr");
	}
	
	/**
	 * 未申請名称を取得する。<br>
	 * @param mospParams MosP処理情報
	 * @return 未申請
	 */
	public static String getNotApplied(MospParams mospParams) {
		return mospParams.getName("Ram", "Application");
	}
	
	/**
	 * 未承認名称を取得する。<br>
	 * @param mospParams MosP処理情報
	 * @return 未承認
	 */
	public static String getNotApproved(MospParams mospParams) {
		return mospParams.getName("Ram", "Approval");
	}
	
	/**
	 * 勤怠名称を取得する。<br>
	 * @param mospParams MosP処理情報
	 * @return 勤怠
	 */
	public static String getWorkManage(MospParams mospParams) {
		return mospParams.getName("WorkManage");
	}
	
	/**
	 * 残業名称を取得する。<br>
	 * @param mospParams MosP処理情報
	 * @return 残業
	 */
	public static String getOvertimeWork(MospParams mospParams) {
		return mospParams.getName("OvertimeWork");
	}
	
	/**
	 * 休暇名称を取得する。<br>
	 * @param mospParams MosP処理情報
	 * @return 休暇
	 */
	public static String getVacation(MospParams mospParams) {
		return mospParams.getName("Vacation");
	}
	
	/**
	 * 振出休出名称を取得する。<br>
	 * @param mospParams MosP処理情報
	 * @return 振出休出
	 */
	public static String getWorkOnHoliday(MospParams mospParams) {
		return mospParams.getName("SubstituteWorkAbbr", "WorkingHoliday");
	}
	
	/**
	 * 代休名称を取得する。<br>
	 * @param mospParams MosP処理情報
	 * @return 代休
	 */
	public static String getSubHoliday(MospParams mospParams) {
		return mospParams.getName("CompensatoryHoliday");
	}
	
	/**
	 * 勤務形態名称を取得する。<br>
	 * @param mospParams MosP処理情報
	 * @return 勤務形態
	 */
	public static String getWorkType(MospParams mospParams) {
		return mospParams.getName("Work", "Form");
	}
	
	/**
	 * 時差名称を取得する。<br>
	 * @param mospParams MosP処理情報
	 * @return 時差
	 */
	public static String getTimeDifference(MospParams mospParams) {
		return mospParams.getName("TimeDifference");
	}
	
}
