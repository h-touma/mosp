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
package jp.mosp.platform.bean.system;

import java.util.Date;
import java.util.List;
import java.util.Set;

import jp.mosp.framework.base.MospException;
import jp.mosp.platform.dto.human.HumanDtoInterface;
import jp.mosp.platform.dto.workflow.RouteApplicationDtoInterface;
import jp.mosp.platform.utils.PlatformUtility;

/**
 * プラットフォームマスタ参照インターフェース。<br>
 */
public interface PlatformMasterBeanInterface {
	
	/**
	 * 対象日時点における最新の有効な情報から、ルート適用情報を取得する。<br>
	 * <br>
	 * {@link PlatformUtility#getApplicationMaster(HumanDtoInterface, Set, Set)}
	 * 参照。<br>
	 * <br>
	 * @param humanDto     人事情報
	 * @param targetDate   対象日
	 * @param workflowType フロー区分
	 * @return 設定適用情報
	 * @throws MospException インスタンスの取得或いはSQL実行に失敗した場合
	 */
	RouteApplicationDtoInterface getRouteApplication(HumanDtoInterface humanDto, Date targetDate, int workflowType)
			throws MospException;
	
	/**
	 * 対象日時点における最新の有効な情報から、ルート適用情報を取得する。<br>
	 * <br>
	 * {@link PlatformMasterBeanInterface#getRouteApplication(HumanDtoInterface, Date, int)}
	 * 参照。<br>
	 * <br>
	 * @param personalId   個人ID
	 * @param targetDate   対象日
	 * @param workflowType フロー区分
	 * @return 設定適用情報
	 * @throws MospException インスタンスの取得或いはSQL実行に失敗した場合
	 */
	RouteApplicationDtoInterface getRouteApplication(String personalId, Date targetDate, int workflowType)
			throws MospException;
	
	/**
	 * 人事情報を取得する。<br>
	 * <br>
	 * 対象個人IDにつき、対象日以前で最新の人事情報を取得する。<br>
	 * <br>
	 * {@link PlatformMasterBeanInterface#getHumanHistory(String)}
	 * で人事情報履歴を取得する。<br>
	 * <br>
	 * @param personalId  個人ID
	 * @param targeteDate 対象日
	 * @return 人事情報
	 * @throws MospException インスタンスの取得或いはSQL実行に失敗した場合
	 */
	HumanDtoInterface getHuman(String personalId, Date targeteDate) throws MospException;
	
	/**
	 * 年月指定時の基準日で人事情報を取得する。<br>
	 * <br>
	 * {@link PlatformMasterBeanInterface#getHuman(String, Date)}
	 * 参照。<br>
	 * <br>
	 * @param personalId  個人ID
	 * @param targetYear  対象年
	 * @param targetMonth 対象月
	 * @return 人事情報
	 * @throws MospException インスタンスの取得或いはSQL実行に失敗した場合
	 */
	HumanDtoInterface getHuman(String personalId, int targetYear, int targetMonth) throws MospException;
	
	/**
	 * 人事情報履歴を取得する。<br>
	 * <br>
	 * 保持された人事情報履歴群に対象となる個人IDの履歴情報がある場合は、
	 * それを参照する。<br>
	 * 保持された人事情報履歴群に対象となる個人IDの履歴情報がない場合は、
	 * DBから情報を取得し、保持する。<br>
	 * <br>
	 * @param personalId 個人ID
	 * @return 人事情報履歴
	 * @throws MospException インスタンスの取得或いはSQL実行に失敗した場合
	 */
	List<HumanDtoInterface> getHumanHistory(String personalId) throws MospException;
	
}
