function openWindow(target) {
	window.open(target);
}
/**
 * 画面読込時追加処理
 * @param 無し
 * @return 無し
 * @throws 実行時例外
 */
function onLoadExtra() {
	// setToDayTableColor関数が存在する場合
	if (typeof setToDayTableColor == "function") {
		// 勤怠一覧用当日背景色設定
		setToDayTableColor("list");
	}
}
