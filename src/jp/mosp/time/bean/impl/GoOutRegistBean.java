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
import java.util.List;

import jp.mosp.framework.base.MospException;
import jp.mosp.framework.base.MospParams;
import jp.mosp.platform.base.PlatformBean;
import jp.mosp.time.bean.AttendanceCalcBeanInterface;
import jp.mosp.time.bean.GoOutRegistBeanInterface;
import jp.mosp.time.dao.settings.GoOutDaoInterface;
import jp.mosp.time.dto.settings.GoOutDtoInterface;
import jp.mosp.time.dto.settings.TimeSettingDtoInterface;
import jp.mosp.time.dto.settings.impl.TmdGoOutDto;

/**
 * 勤怠データ外出情報登録クラス。
 */
public class GoOutRegistBean extends PlatformBean implements GoOutRegistBeanInterface {
	
	/**
	 * 勤怠データ自動計算クラス。
	 */
	protected AttendanceCalcBeanInterface	attendanceCalc;
	
	/**
	 * 外出マスタDAOクラス。<br>
	 */
	GoOutDaoInterface						dao;
	
	
	/**
	 * {@link PlatformBean#PlatformBean()}を実行する。<br>
	 */
	public GoOutRegistBean() {
		super();
	}
	
	/**
	 * {@link PlatformBean#PlatformBean(MospParams, Connection)}を実行する。<br>
	 * @param mospParams MosPパラメータクラス
	 * @param connection DBコネクション
	 */
	public GoOutRegistBean(MospParams mospParams, Connection connection) {
		super(mospParams, connection);
	}
	
	@Override
	public void initBean() throws MospException {
		attendanceCalc = (AttendanceCalcBeanInterface)createBean(AttendanceCalcBeanInterface.class);
		dao = (GoOutDaoInterface)createDao(GoOutDaoInterface.class);
	}
	
	@Override
	public GoOutDtoInterface getInitDto() {
		return new TmdGoOutDto();
	}
	
	@Override
	public void regist(GoOutDtoInterface dto) throws MospException {
		if (dao.findForKey(dto.getPersonalId(), dto.getWorkDate(), dto.getTimesWork(), dto.getGoOutType(),
				dto.getTimesGoOut()) == null) {
			// 新規登録
			insert(dto);
		} else {
			// 履歴追加
			update(dto);
		}
	}
	
	@Override
	public void insert(GoOutDtoInterface dto) throws MospException {
		// DTOの妥当性確認
		validate(dto);
		if (mospParams.hasErrorMessage()) {
			return;
		}
		// 新規登録情報の検証
		checkInsert(dto);
		if (mospParams.hasErrorMessage()) {
			return;
		}
		// レコード識別ID最大値をインクリメントしてDTOに設定
		dto.setTmdGoOutId(dao.nextRecordId());
		// 登録処理
		dao.insert(dto);
	}
	
	@Override
	public void update(GoOutDtoInterface dto) throws MospException {
		// DTOの妥当性確認
		validate(dto);
		if (mospParams.hasErrorMessage()) {
			return;
		}
		// 履歴更新情報の検証
		checkUpdate(dto);
		if (mospParams.hasErrorMessage()) {
			return;
		}
		// 論理削除
		logicalDelete(dao, dto.getTmdGoOutId());
		// レコード識別ID最大値をインクリメントしてDTOに設定
		dto.setTmdGoOutId(dao.nextRecordId());
		// 登録処理
		dao.insert(dto);
	}
	
	/**
	 * 新規登録時の確認処理を行う。<br>
	 * @param dto 対象DTO
	 * @throws MospException SQLの作成に失敗した場合、或いはSQL例外が発生した場合
	 */
	protected void checkInsert(GoOutDtoInterface dto) throws MospException {
		// 対象レコードの有効日が重複していないかを確認
		checkDuplicateInsert(dao.findForHistory(dto.getPersonalId(), dto.getWorkDate(), dto.getGoOutType(),
				dto.getTimesGoOut(), dto.getGoOutStart(), dto.getGoOutEnd()));
	}
	
	/**
	 * 履歴更新時の確認処理を行う。<br>
	 * @param dto 対象DTO
	 * @throws MospException SQLの作成に失敗した場合、或いはSQL例外が発生した場合
	 */
	protected void checkUpdate(GoOutDtoInterface dto) throws MospException {
		// 対象レコード識別IDのデータが削除されていないかを確認
		checkExclusive(dao, dto.getTmdGoOutId());
	}
	
	/**
	 * 登録情報の妥当性を確認する。
	 * @param dto 対象DTO
	 */
	protected void validate(GoOutDtoInterface dto) {
		// TODO 妥当性確認
	}
	
	@Override
	public void delete(String personalId, Date workDate, int timesWork) throws MospException {
		List<GoOutDtoInterface> list = dao.findForList(personalId, workDate, timesWork);
		for (GoOutDtoInterface dto : list) {
			checkDelete((GoOutDtoInterface)dao.findForKey(dto.getTmdGoOutId(), true));
			if (mospParams.hasErrorMessage()) {
				// エラーが存在したら履歴削除処理をしない
				continue;
			}
			// 論理削除
			logicalDelete(dao, dto.getTmdGoOutId());
		}
	}
	
	/**
	 * 削除時の確認処理を行う。<br>
	 * 削除対象雇用契約を設定している社員がいないかの確認を行う。<br>
	 * @param dto 対象DTO
	 * @throws MospException SQLの作成に失敗した場合、或いはSQL例外が発生した場合
	 */
	protected void checkDelete(GoOutDtoInterface dto) throws MospException {
		// 対象レコード識別IDのデータが削除されていないかを確認
		checkExclusive(dao, dto.getTmdGoOutId());
	}
	
	@Override
	public void delete(String personalId, Date workDate, int timesWork, int goOutType, int timesGoOut)
			throws MospException {
		GoOutDtoInterface dto = dao.findForKey(personalId, workDate, timesWork, goOutType, timesGoOut);
		checkDelete((GoOutDtoInterface)dao.findForKey(dto.getTmdGoOutId(), true));
		if (mospParams.hasErrorMessage()) {
			// エラーが存在したら履歴削除処理をしない
			return;
		}
		// 論理削除
		logicalDelete(dao, dto.getTmdGoOutId());
	}
	
	@Override
	public int getCalcPrivateGoOutTime(Date goOutStart, Date goOutEnd, TimeSettingDtoInterface timeSettingDto) {
		// 外出開始時刻、外出終了時刻、勤怠設定情報がない場合
		if (goOutStart == null || goOutEnd == null || timeSettingDto == null) {
			return 0;
		}
		// 外出開始丸め時刻取得
		Date roundGoOutStart = attendanceCalc.getRoundMinute(goOutStart, timeSettingDto.getRoundDailyPrivateStart(),
				timeSettingDto.getRoundDailyPrivateStartUnit());
		// 外出終了丸め時刻取得
		Date roundGoOutEnd = attendanceCalc.getRoundMinute(goOutEnd, timeSettingDto.getRoundDailyPrivateEnd(),
				timeSettingDto.getRoundDailyPrivateEndUnit());
		// 外出時間。
		return attendanceCalc.getDefferenceMinutes(roundGoOutStart, roundGoOutEnd);
	}
	
	@Override
	public int getCalcPublicGoOutTime(Date goOutStart, Date goOutEnd, TimeSettingDtoInterface timeSettingDto) {
		// 外出開始時刻、外出終了時刻、勤怠設定情報がない場合
		if (goOutStart == null || goOutEnd == null || timeSettingDto == null) {
			return 0;
		}
		// 外出開始丸め時刻取得
		Date roundGoOutStart = attendanceCalc.getRoundMinute(goOutStart, timeSettingDto.getRoundDailyPublicStart(),
				timeSettingDto.getRoundDailyPublicStartUnit());
		// 外出終了丸め時刻取得
		Date roundGoOutEnd = attendanceCalc.getRoundMinute(goOutEnd, timeSettingDto.getRoundDailyPublicEnd(),
				timeSettingDto.getRoundDailyPublicEndUnit());
		// 外出時間。
		return attendanceCalc.getDefferenceMinutes(roundGoOutStart, roundGoOutEnd);
	}
	
	@Override
	public int getCalcMinutelyHolidayAGoOutTime(Date goOutStart, Date goOutEnd, TimeSettingDtoInterface timeSettingDto) {
		// 外出開始時刻、外出終了時刻、勤怠設定情報がない場合
		if (goOutStart == null || goOutEnd == null || timeSettingDto == null) {
			return 0;
		}
		// TODO 日付オブジェクトの差を時間(分)で取得
		return attendanceCalc.getDefferenceMinutes(goOutStart, goOutEnd);
	}
	
	@Override
	public int getCalcMinutelyHolidayBGoOutTime(Date goOutStart, Date goOutEnd, TimeSettingDtoInterface timeSettingDto) {
		// 外出開始時刻、外出終了時刻、勤怠設定情報がない場合
		if (goOutStart == null || goOutEnd == null || timeSettingDto == null) {
			return 0;
		}
		// TODO 日付オブジェクトの差を時間(分)で取得
		return attendanceCalc.getDefferenceMinutes(goOutStart, goOutEnd);
	}
}
