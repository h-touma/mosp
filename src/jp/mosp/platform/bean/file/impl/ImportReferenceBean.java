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
package jp.mosp.platform.bean.file.impl;

import java.sql.Connection;

import jp.mosp.framework.base.MospException;
import jp.mosp.framework.base.MospParams;
import jp.mosp.platform.base.PlatformBean;
import jp.mosp.platform.bean.file.ImportReferenceBeanInterface;
import jp.mosp.platform.dao.file.ImportDaoInterface;
import jp.mosp.platform.dto.file.ImportDtoInterface;

/**
 * インポートマスタ参照クラス。
 */
public class ImportReferenceBean extends PlatformBean implements ImportReferenceBeanInterface {
	
	/**
	 * インポートマスタDAO。
	 */
	protected ImportDaoInterface	dao;
	
	
	/**
	 * {@link PlatformBean#PlatformBean()}を実行する。<br>
	 */
	public ImportReferenceBean() {
		super();
	}
	
	/**
	 * {@link PlatformBean#PlatformBean(MospParams, Connection)}を実行する。<br>
	 * @param mospParams MosPパラメータクラス
	 * @param connection DBコネクション
	 */
	public ImportReferenceBean(MospParams mospParams, Connection connection) {
		super(mospParams, connection);
	}
	
	@Override
	public void initBean() throws MospException {
		dao = (ImportDaoInterface)createDao(ImportDaoInterface.class);
	}
	
	@Override
	public ImportDtoInterface findForKey(String importCode) throws MospException {
		return dao.findForKey(importCode);
	}
	
}
