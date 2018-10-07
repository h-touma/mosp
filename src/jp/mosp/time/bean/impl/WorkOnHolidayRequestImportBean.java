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

import java.io.InputStream;
import java.sql.Connection;
import java.util.Date;
import java.util.List;

import jp.mosp.framework.base.MospException;
import jp.mosp.framework.base.MospParams;
import jp.mosp.platform.bean.file.ImportBeanInterface;
import jp.mosp.platform.bean.file.PlatformFileBean;
import jp.mosp.platform.bean.workflow.WorkflowIntegrateBeanInterface;
import jp.mosp.platform.bean.workflow.WorkflowRegistBeanInterface;
import jp.mosp.platform.constant.PlatformConst;
import jp.mosp.platform.dto.file.ImportDtoInterface;
import jp.mosp.platform.dto.file.ImportFieldDtoInterface;
import jp.mosp.platform.dto.workflow.WorkflowDtoInterface;
import jp.mosp.time.base.TimeBean;
import jp.mosp.time.bean.AttendanceTransactionRegistBeanInterface;
import jp.mosp.time.bean.SubstituteRegistBeanInterface;
import jp.mosp.time.bean.WorkOnHolidayRequestRegistBeanInterface;
import jp.mosp.time.constant.TimeConst;
import jp.mosp.time.dao.settings.impl.TmdSubstituteDao;
import jp.mosp.time.dao.settings.impl.TmdWorkOnHolidayRequestDao;
import jp.mosp.time.dto.settings.SubstituteDtoInterface;
import jp.mosp.time.dto.settings.WorkOnHolidayRequestDtoInterface;

/**
 * 振出・休出申請インポートクラス。<br>
 */
public class WorkOnHolidayRequestImportBean extends PlatformFileBean implements ImportBeanInterface {
	
	/**
	 * 振出・休出申請登録クラス。<br>
	 */
	protected WorkOnHolidayRequestRegistBeanInterface	workOnHolidayRequestRegist;
	
	/**
	 * 振替休日データ登録クラス。<br>
	 */
	protected SubstituteRegistBeanInterface				substituteRegist;
	
	/**
	 * 勤怠トランザクション登録クラス。<br>
	 */
	protected AttendanceTransactionRegistBeanInterface	attendanceTransactionRegist;
	
	/**
	 * ワークフロー統括クラス。<br>
	 */
	protected WorkflowIntegrateBeanInterface			workflowIntegrate;
	
	/**
	 * ワークフロー登録クラス。<br>
	 */
	protected WorkflowRegistBeanInterface				workflowRegist;
	
	
	/**
	 * {@link PlatformFileBean#PlatformFileBean()}を実行する。<br>
	 */
	public WorkOnHolidayRequestImportBean() {
		super();
	}
	
	/**
	 * {@link PlatformFileBean#PlatformFileBean(MospParams, Connection)}を実行する。<br>
	 * @param mospParams MosP処理情報
	 * @param connection DBコネクション
	 */
	public WorkOnHolidayRequestImportBean(MospParams mospParams, Connection connection) {
		super(mospParams, connection);
	}
	
	@Override
	public void initBean() throws MospException {
		workOnHolidayRequestRegist = (WorkOnHolidayRequestRegistBeanInterface)createBean(WorkOnHolidayRequestRegistBeanInterface.class);
		substituteRegist = (SubstituteRegistBeanInterface)createBean(SubstituteRegistBeanInterface.class);
		attendanceTransactionRegist = (AttendanceTransactionRegistBeanInterface)createBean(AttendanceTransactionRegistBeanInterface.class);
		workflowIntegrate = (WorkflowIntegrateBeanInterface)createBean(WorkflowIntegrateBeanInterface.class);
		workflowRegist = (WorkflowRegistBeanInterface)createBean(WorkflowRegistBeanInterface.class);
	}
	
	@Override
	public int importFile(ImportDtoInterface importDto, InputStream requestedFile) throws MospException {
		// アップロードファイルを登録情報リストに変換
		List<String[]> dataList = getDataList(importDto, requestedFile);
		if (mospParams.hasErrorMessage()) {
			return 0;
		}
		// インポートフィールド情報リストを取得
		List<ImportFieldDtoInterface> fieldList = getImportFieldList(importDto.getImportCode());
		if (mospParams.hasErrorMessage()) {
			return 0;
		}
		// 社員コードを個人IDに変換
		convertEmployeeCodeIntoPersonalId(fieldList, dataList, TmdWorkOnHolidayRequestDao.COL_REQUEST_DATE);
		if (mospParams.hasErrorMessage()) {
			return 0;
		}
		// インポート処理
		return importFile(fieldList, dataList);
	}
	
	/**
	 * インポート処理を行う。<br>
	 * 登録情報リストを、インポートフィールド情報リストに基づき
	 * 振出・休出情報、振替休日情報、ワークフロー情報に変換し、登録を行う。<br>
	 * @param fieldList インポートフィールド情報リスト
	 * @param dataList 登録情報リスト
	 * @return 登録件数
	 * @throws MospException インスタンスの取得、或いはSQL実行に失敗した場合
	 */
	protected int importFile(List<ImportFieldDtoInterface> fieldList, List<String[]> dataList) throws MospException {
		// 登録情報リスト内の各登録情報長を確認
		checkCsvLength(fieldList, dataList);
		if (mospParams.hasErrorMessage()) {
			return 0;
		}
		for (String[] data : dataList) {
			// インポート
			importData(fieldList, data);
		}
		return dataList.size();
	}
	
	/**
	 * インポート処理を行う。<br>
	 * 登録情報を、インポートフィールド情報リストに基づき
	 * 振出・休出申請情報、振替休日情報、ワークフロー情報に変換し、登録を行う。<br>
	 * @param fieldList インポートフィールド情報リスト
	 * @param data 登録情報
	 * @throws MospException インスタンスの取得、或いはSQL実行に失敗した場合
	 */
	protected void importData(List<ImportFieldDtoInterface> fieldList, String[] data) throws MospException {
		WorkOnHolidayRequestDtoInterface dto = getWorkOnHolidayRequestDto(fieldList, data);
		SubstituteDtoInterface substituteDto = getSubstituteDto(fieldList, data);
		// 申請の相関チェック
		workOnHolidayRequestRegist.checkAppli(dto);
		if (mospParams.hasErrorMessage()) {
			return;
		}
		// 振替休日の相関チェック
		substituteRegist.checkImport(substituteDto);
		if (mospParams.hasErrorMessage()) {
			return;
		}
		WorkflowDtoInterface workflowDto = workflowIntegrate.getLatestWorkflowInfo(dto.getWorkflow());
		if (workflowDto == null) {
			workflowDto = workflowRegist.getInitDto();
			workflowDto.setFunctionCode(TimeConst.CODE_FUNCTION_WORK_HOLIDAY);
		}
		// 自己承認設定
		workflowRegist.setSelfApproval(workflowDto);
		// 登録後ワークフローの取得
		workflowDto = workflowRegist.appli(workflowDto, dto.getPersonalId(), dto.getRequestDate(),
				PlatformConst.WORKFLOW_TYPE_TIME, null);
		if (workflowDto != null) {
			long workflow = workflowDto.getWorkflow();
			// ワークフロー番号セット
			dto.setWorkflow(workflow);
			substituteDto.setWorkflow(workflow);
			// 振出・休出申請
			workOnHolidayRequestRegist.regist(dto);
			// 振替休日データ登録
			substituteRegist.insert(substituteDto);
			// 勤怠トランザクション登録
			attendanceTransactionRegist.regist(substituteDto);
		}
	}
	
	/**
	 * インポートフィールド情報リストに従い、
	 * 登録情報の内容を振出・休出申請情報(DTO)のフィールドに設定する。<br>
	 * @param fieldList インポートフィールド情報リスト
	 * @param data 登録情報
	 * @return 振出・休出申請情報(DTO)
	 * @throws MospException インスタンスの取得、或いはSQL実行に失敗した場合
	 */
	protected WorkOnHolidayRequestDtoInterface getWorkOnHolidayRequestDto(List<ImportFieldDtoInterface> fieldList,
			String[] data) throws MospException {
		// 休日出勤申請DTO準備
		WorkOnHolidayRequestDtoInterface dto = workOnHolidayRequestRegist.getInitDto();
		// 個人ID・出勤日取得
		String personalId = getFieldValue(TmdWorkOnHolidayRequestDao.COL_PERSONAL_ID, fieldList, data);
		Date requestDate = getDateFieldValue(TmdWorkOnHolidayRequestDao.COL_REQUEST_DATE, fieldList, data);
		// 振替申請取得
		int substitute = TimeConst.CODE_WORK_ON_HOLIDAY_SUBSTITUTE_ON;
		// 勤務形態コード取得
		String workTypeCode = getFieldValue(TmdWorkOnHolidayRequestDao.COL_WORK_TYPE_CODE, fieldList, data);
		// 勤務形態コードがある場合
		if (!workTypeCode.isEmpty()) {
			// 振替申請設定
			substitute = TimeConst.CODE_WORK_ON_HOLIDAY_SUBSTITUTE_ON_WORK_TYPE_CHANGE;
		}
		// 登録情報の内容を取得し設定
		dto.setPersonalId(personalId);
		dto.setRequestDate(requestDate);
		dto.setTimesWork(TimeBean.TIMES_WORK_DEFAULT);
		dto.setWorkOnHolidayType(workOnHolidayRequestRegist.getScheduledWorkTypeCode(personalId, requestDate));
		dto.setWorkTypeCode(workTypeCode);
		dto.setSubstitute(substitute);
		dto.setStartTime(null);
		dto.setEndTime(null);
		dto.setRequestReason(getFieldValue(TmdWorkOnHolidayRequestDao.COL_REQUEST_REASON, fieldList, data));
		return dto;
	}
	
	/**
	 * インポートフィールド情報リストに従い、
	 * 登録情報の内容を振替休日情報(DTO)のフィールドに設定する。<br>
	 * @param fieldList インポートフィールド情報リスト
	 * @param data 登録情報
	 * @return 振替休日情報(DTO)
	 * @throws MospException インスタンスの取得、或いはSQL実行に失敗した場合
	 */
	protected SubstituteDtoInterface getSubstituteDto(List<ImportFieldDtoInterface> fieldList, String[] data)
			throws MospException {
		String personalId = getFieldValue(TmdWorkOnHolidayRequestDao.COL_PERSONAL_ID, fieldList, data);
		Date workDate = getDateFieldValue(TmdWorkOnHolidayRequestDao.COL_REQUEST_DATE, fieldList, data);
		SubstituteDtoInterface dto = substituteRegist.getInitDto();
		// 登録情報の内容を取得し設定
		dto.setPersonalId(personalId);
		dto.setSubstituteDate(getDateFieldValue(TmdSubstituteDao.COL_SUBSTITUTE_DATE, fieldList, data));
		dto.setSubstituteType(workOnHolidayRequestRegist.getScheduledWorkTypeCode(personalId, workDate));
		dto.setSubstituteRange(1);
		dto.setWorkDate(workDate);
		dto.setTimesWork(TimeBean.TIMES_WORK_DEFAULT);
		return dto;
	}
	
}
