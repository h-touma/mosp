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
package jp.mosp.platform.constant;

/**
 * メール送信で用いる定数を宣言する。<br>
 */
public class PlatformMailConst {
	
	/**
	 * 他クラスからのインスタンス化を防止する。<br>
	 */
	private PlatformMailConst() {
		// 処理無し
	}
	
	
	/**
	 * MosPアプリケーション設定キー(メール送信機能）。
	 */
	public static final String	APP_USE_MAIL				= "UseMail";
	
	/* メールサーバー関連 */
	public static final String	SMTP_HOST					= "mail.smtp.host";
	public static final String	MAIL_HOST					= "mail.host";
	public static final String	SMTP_AUTH					= "mail.smtp.auth";
	public static final String	SMTP_PORT					= "mail.smtp.port";
	public static final String	CONNETCTION_TIME_OUT		= "mail.smtp.connectiontimeout";
	public static final String	SMTP_TIME_OUT				= "mail.smtp.timeout";
	public static final String	MAIL_PROTOCOL				= "mail.transport.protocol";
	
	public static final String	SF_PORT						= "mail.smtp.socketFactory.port";
	public static final String	SF_FALLBACK					= "mail.smtp.socketFactory.fallback";
	public static final String	SF_CLASS					= "mail.smtp.socketFactory.class";
	
	public static final String	ISO_2022_JP					= "iso-2022-jp";
	public static final String	UTF_8						= "UTF-8";
	
	public static final String	SMTP						= "smtp";
	public static final String	PORT_25						= "25";
	public static final String	SSL_PORT_465				= "465";
	
	public static final String	TIME_OUT					= "60000";
	
	/**
	 * MosPアプリケーション設定キー(メールアドレス)。
	 */
	public static final String	APP_MAIL_ADDRESS			= "MailAddress";
	
	/**
	 * MosPアプリケーション設定キー(メール個人名)。
	 */
	public static final String	APP_MAIL_PERSONAL			= "MailPersonal";
	
	/**
	 * MosPアプリケーション設定キー(メールホスト)。
	 */
	public static final String	APP_MAIL_HOST				= "MailHost";
	
	/**
	 * MosPアプリケーション設定キー(メールユーザ名)。
	 */
	public static final String	APP_MAIL_USER_NAME			= "MailUserName";
	
	/**
	 * MosPアプリケーション設定キー(メールパスワード)。
	 */
	public static final String	APP_MAIL_PASSWORD			= "MailPassword";
	
	/**
	 * MosPアプリケーション設定キー(メールSMTP認証)。
	 */
	public static final String	APP_MAIL_SMTP_AUTH			= "MailSmtpAuth";
	
	/**
	 * MosPアプリケーション設定キー(メールSSL利用可否)。
	 */
	public static final String	APP_MAIL_SSL				= "MailSsl";
	
	public static final String	STR_UNDER_SEPARATOR			= ",";
	
	public static final String	MSG_MAIL_SEND_SUCCESS		= "へメールを送信しました。";
	public static final String	MSG_MAIL_SEND_FAILED		= "への送信を失敗しました。";
	
	/* テンプレート関連 */
	public static final String	RUNTIME_LOG_LOGSYSTEM_CLASS	= "runtime.log.logsystem.class";
	
	public static final String	FILE						= "file";
	public static final String	RESOURCE_LOADER				= "resource.loader";
	public static final String	RESOURCE_LOADER_DESCRIPTION	= FILE + ".resource.loader.description";
	public static final String	RESOURCE_LOADER_CLASS		= FILE + ".resource.loader.class";
	public static final String	RESOURCE_LOADER_PATH		= FILE + ".resource.loader.path";
	public static final String	RESOURCE_LOADER_CACHE		= FILE + ".resource.loader.cache";
	public static final String	RESOURCE_LOADER_INTERVAL	= FILE + ".resource.loader.modificationCheckInterval";
	public static final String	INPUT_ENCODING				= "input.encoding";
	public static final String	OUTPUT_ENCODING				= "output.encoding";
	public static final String	ENCODING					= "Windows-31J";
	public static final String	KEY_DTO						= "dto";
	
}
