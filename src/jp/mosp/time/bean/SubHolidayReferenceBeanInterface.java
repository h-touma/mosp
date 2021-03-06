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
package jp.mosp.time.bean;

import java.util.Date;
import java.util.List;

import jp.mosp.framework.base.MospException;
import jp.mosp.time.dto.settings.SubHolidayDtoInterface;
import jp.mosp.time.dto.settings.SubHolidayRequestDtoInterface;

/**
 * 代休データ参照インターフェース。
 */
public interface SubHolidayReferenceBeanInterface {
	
	/**
	 * プルダウン用配列取得。
	 * <p>
	 * 個人IDと開始年月日と終了年月日からプルダウン用配列を取得。
	 * </p>
	 * @param personalId 個人ID
	 * @param targetDate 対象年月日
	 * @param holidayRange 休暇範囲
	 * @param dto 代休申請DTO
	 * @return プルダウン用配列
	 * @throws MospException インスタンスの取得或いはSQL実行に失敗した場合
	 */
	String[][] getSelectArray(String personalId, Date targetDate, String holidayRange, SubHolidayRequestDtoInterface dto)
			throws MospException;
	
	/**
	 * 代休データリストを取得する。<br>
	 * @param personalId 個人ID
	 * @param startDate 開始日
	 * @param endDate 終了日
	 * @return 代休データリスト
	 * @throws MospException インスタンスの取得或いはSQL実行に失敗した場合
	 */
	List<SubHolidayDtoInterface> getSubHolidayList(String personalId, Date startDate, Date endDate)
			throws MospException;
	
	/**
	 * 代休データリストを取得する。<br>
	 * @param personalId 個人ID
	 * @param targetDate 対象日
	 * @return 代休データリスト
	 * @throws MospException インスタンスの取得或いはSQL実行に失敗した場合
	 */
	List<SubHolidayDtoInterface> getSubHolidayList(String personalId, Date targetDate) throws MospException;
	
	/**
	 * 申請可能日数を取得する。<br>
	 * @param personalId 個人ID
	 * @param targetDate 対象年月日
	 * @return 申請可能日数
	 * @throws MospException インスタンスの取得或いはSQL実行に失敗した場合
	 */
	double getPossibleRequestDays(String personalId, Date targetDate) throws MospException;
	
	/**
	 * 対象期間の代休発生合計日数を取得する。
	 * @param personalId 個人ID
	 * @param startDate 期間開始日
	 * @param endDate 期間終了日
	 * @return 合計代休発生日数(0：所定、1：法定、2：深夜)
	 * @throws MospException インスタンスの取得或いはSQL実行に失敗した場合
	 */
	Float[] getBirthSubHolidayTimes(String personalId, Date startDate, Date endDate) throws MospException;
	
}
