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

import jp.mosp.framework.base.MospException;
import jp.mosp.platform.dto.system.UserMasterDtoInterface;

/**
 * ユーザマスタ登録インターフェース
 */
public interface UserMasterRegistBeanInterface {
	
	/**
	 * 登録用DTOを取得する。<br>
	 * @return 初期DTO
	 */
	UserMasterDtoInterface getInitDto();
	
	/**
	 * 新規登録を行う。<br>
	 * @param dto 対象DTO
	 * @throws MospException インスタンスの取得、或いはSQL実行に失敗した場合
	 */
	void insert(UserMasterDtoInterface dto) throws MospException;
	
	/**
	 * 履歴追加を行う。<br>
	 * @param dto 対象DTO
	 * @throws MospException インスタンスの取得、或いはSQL実行に失敗した場合
	 */
	void add(UserMasterDtoInterface dto) throws MospException;
	
	/**
	 * 履歴更新を行う。<br>
	 * @param dto 対象DTO
	 * @throws MospException インスタンスの取得、或いはSQL実行に失敗した場合
	 */
	void update(UserMasterDtoInterface dto) throws MospException;
	
	/**
	 * 一括更新処理(有効/無効)を行う。<br>
	 * @param idArray        対象レコード識別ID配列
	 * @param activateDate   有効日
	 * @param inactivateFlag 無効フラグ
	 * @throws MospException インスタンスの取得、或いはSQL実行に失敗した場合
	 */
	void update(long[] idArray, Date activateDate, int inactivateFlag) throws MospException;
	
	/**
	 * 一括更新処理(ロール)を行う。<br>
	 * @param idArray      対象レコード識別ID配列
	 * @param activateDate 有効日
	 * @param roleCode     ロールコード
	 * @throws MospException インスタンスの取得、或いはSQL実行に失敗した場合
	 */
	void update(long[] idArray, Date activateDate, String roleCode) throws MospException;
	
	/**
	 * 論理削除(履歴)を行う。<br>
	 * レコード識別IDで取得される履歴に対して削除を行う。<br>
	 * @param idArray 対象レコード識別ID配列
	 * @throws MospException インスタンスの取得、或いはSQL実行に失敗した場合
	 */
	void delete(long[] idArray) throws MospException;
	
	/**
	 * レコード識別IDからユーザIDリストを取得する。<br>
	 * 同時に排他確認を行う。<br>
	 * @param recordIdArray レコード識別ID配列
	 * @return ユーザIDリスト
	 * @throws MospException SQLの作成に失敗した場合、或いはSQL例外が発生した場合
	 */
	List<String> getUserIdList(long[] recordIdArray) throws MospException;
	
	/**
	 * 登録情報の妥当性を確認確認する。
	 * @param dto 対象DTO
	 * @param row 行インデックス
	 * @throws MospException SQLの作成に失敗した場合、或いはSQL例外が発生した場合
	 */
	void validate(UserMasterDtoInterface dto, Integer row) throws MospException;
	
}
