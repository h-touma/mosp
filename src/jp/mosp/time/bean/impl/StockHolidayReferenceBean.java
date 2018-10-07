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
/**
 * 
 */
package jp.mosp.time.bean.impl;

import java.sql.Connection;
import java.util.Date;

import jp.mosp.framework.base.MospException;
import jp.mosp.framework.base.MospParams;
import jp.mosp.platform.base.PlatformBean;
import jp.mosp.time.bean.StockHolidayReferenceBeanInterface;
import jp.mosp.time.dao.settings.StockHolidayDaoInterface;
import jp.mosp.time.dto.settings.StockHolidayDtoInterface;

/**
 * ストック休暇設定参照クラス。
 */
public class StockHolidayReferenceBean extends PlatformBean implements StockHolidayReferenceBeanInterface {
	
	/**
	 * ストック休暇設定DAO。
	 */
	private StockHolidayDaoInterface	dao;
	
	
	/**
	 * {@link PlatformBean#PlatformBean()}を実行する。<br>
	 */
	public StockHolidayReferenceBean() {
		super();
	}
	
	/**
	 * {@link PlatformBean#PlatformBean(MospParams, Connection)}を実行する。<br>
	 * @param mospParams MosPパラメータクラス
	 * @param connection DBコネクション
	 */
	public StockHolidayReferenceBean(MospParams mospParams, Connection connection) {
		super(mospParams, connection);
	}
	
	@Override
	public void initBean() throws MospException {
		dao = (StockHolidayDaoInterface)createDao(StockHolidayDaoInterface.class);
	}
	
	@Override
	public StockHolidayDtoInterface getStockHolidayInfo(String paidHolidayCode, Date targetDate) throws MospException {
		return dao.findForInfo(paidHolidayCode, targetDate);
	}
	
	@Override
	public StockHolidayDtoInterface findForKey(String paidHolidayCode, Date activateDate) throws MospException {
		return dao.findForKey(paidHolidayCode, activateDate);
	}
	
}
