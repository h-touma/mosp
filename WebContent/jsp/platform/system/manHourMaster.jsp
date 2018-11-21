<%@ page
language = "java"
pageEncoding = "UTF-8"
buffer = "256kb"
autoFlush = "false"
errorPage = "/jsp/common/error.jsp"
%><%@ page
import = "jp.mosp.framework.base.MospParams"
import = "jp.mosp.framework.constant.MospConst"
import = "jp.mosp.platform.system.constant.PlatformSystemConst"
import = "jp.mosp.platform.system.vo.ManHourMasterVo"
%>
<!--
import = "jp.mosp.framework.utils.HtmlUtility"
import = "jp.mosp.platform.comparator.base.ActivateDateComparator"
import = "jp.mosp.platform.comparator.base.InactivateComparator"
import = "jp.mosp.platform.comparator.base.PositionCodeComparator"
import = "jp.mosp.platform.comparator.system.PositionMasterGradeLevelComparator"
import = "jp.mosp.platform.comparator.system.PositionMasterPositionAbbrComparator"
import = "jp.mosp.platform.comparator.system.PositionMasterPositionNameComparator"
import = "jp.mosp.platform.constant.PlatformConst"
import = "jp.mosp.platform.system.action.ManHourMasterAction"
import = "jp.mosp.platform.utils.PlatformUtility"
-->
<%
MospParams params = (MospParams)request.getAttribute(MospConst.ATT_MOSP_PARAMS);
ManHourMasterVo vo = (ManHourMasterVo)params.getVo();
%>
<div class="List" id="divEdit">
	<table class="InputTable">
		<tr>
			<th class="EditTableTh" colspan="6">
				<jsp:include page="<%= PlatformSystemConst.PATH_SYSTEM_EDIT_HEADER_JSP %>" flush="false" />
			</th>
		</tr>
	</table>
</div>
