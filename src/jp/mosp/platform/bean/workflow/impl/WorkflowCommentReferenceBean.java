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
package jp.mosp.platform.bean.workflow.impl;

import java.sql.Connection;
import java.util.List;

import jp.mosp.framework.base.MospException;
import jp.mosp.framework.base.MospParams;
import jp.mosp.platform.base.PlatformBean;
import jp.mosp.platform.bean.workflow.WorkflowCommentReferenceBeanInterface;
import jp.mosp.platform.dao.workflow.WorkflowCommentDaoInterface;
import jp.mosp.platform.dto.workflow.WorkflowCommentDtoInterface;

/**
 * ワークフローコメント参照クラス。
 */
public class WorkflowCommentReferenceBean extends PlatformBean implements WorkflowCommentReferenceBeanInterface {
	
	private WorkflowCommentDaoInterface	dao;
	
	
	/**
	 * {@link PlatformBean#PlatformBean()}を実行する。<br>
	 */
	public WorkflowCommentReferenceBean() {
		super();
	}
	
	/**
	 * {@link PlatformBean#PlatformBean(MospParams, Connection)}を実行する。<br>
	 * @param mospParams MosPパラメータクラス
	 * @param connection DBコネクション
	 */
	public WorkflowCommentReferenceBean(MospParams mospParams, Connection connection) {
		super(mospParams, connection);
	}
	
	@Override
	public void initBean() throws MospException {
		dao = (WorkflowCommentDaoInterface)createDao(WorkflowCommentDaoInterface.class);
	}
	
	@Override
	public WorkflowCommentDtoInterface getLatestWorkflowCommentInfo(long workflow) throws MospException {
		return dao.findForLatestCommentInfo(workflow);
	}
	
	@Override
	public List<WorkflowCommentDtoInterface> getWorkflowCommentHistory(long workflow) throws MospException {
		return dao.findForHistory(workflow);
	}
	
	@Override
	public List<WorkflowCommentDtoInterface> getWorkflowCommentList(long workflow) throws MospException {
		return dao.findForList(workflow);
	}
	
}
