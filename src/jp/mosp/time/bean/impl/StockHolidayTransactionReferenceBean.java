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
package jp.mosp.time.bean.impl;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import jp.mosp.framework.base.MospException;
import jp.mosp.framework.base.MospParams;
import jp.mosp.platform.base.PlatformBean;
import jp.mosp.time.bean.StockHolidayTransactionReferenceBeanInterface;
import jp.mosp.time.dao.settings.StockHolidayTransactionDaoInterface;
import jp.mosp.time.dto.settings.StockHolidayTransactionDtoInterface;

/**
 * ストック休暇トランザクション参照クラス。
 */
public class StockHolidayTransactionReferenceBean extends PlatformBean implements
		StockHolidayTransactionReferenceBeanInterface {
	
	/**
	 * ストック休暇トランザクションDAO。
	 */
	private StockHolidayTransactionDaoInterface	dao;
	
	
	/**
	 * {@link PlatformBean#PlatformBean()}を実行する。<br>
	 */
	public StockHolidayTransactionReferenceBean() {
		super();
	}
	
	/**
	 * {@link PlatformBean#PlatformBean(MospParams, Connection)}を実行する。<br>
	 * @param mospParams MosPパラメータクラス
	 * @param connection DBコネクション
	 */
	public StockHolidayTransactionReferenceBean(MospParams mospParams, Connection connection) {
		super(mospParams, connection);
	}
	
	@Override
	public void initBean() throws MospException {
		dao = (StockHolidayTransactionDaoInterface)createDao(StockHolidayTransactionDaoInterface.class);
	}
	
	@Override
	public StockHolidayTransactionDtoInterface getStockHolidayTransactionInfo(String personalId, Date targetDate,
			Date acquisitionDate) throws MospException {
		return dao.findForInfo(personalId, targetDate, acquisitionDate);
	}
	
	@Override
	public StockHolidayTransactionDtoInterface findForKey(String personalId, Date activateDate, Date acquisitionDate)
			throws MospException {
		return dao.findForKey(personalId, activateDate, acquisitionDate);
	}
	
	@Override
	public List<StockHolidayTransactionDtoInterface> findForList(String personalId, Date acquisitionDate,
			Date startDate, Date endDate) throws MospException {
		return dao.findForList(personalId, acquisitionDate, startDate, endDate);
	}
	
}
