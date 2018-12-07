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
import = "jp.mosp.framework.utils.HtmlUtility"
import = "jp.mosp.platform.constant.PlatformConst"
import = "jp.mosp.platform.system.action.ManHourMasterAction"
import = "jp.mosp.platform.utils.PlatformUtility"
%>
<!--

import = "jp.mosp.platform.comparator.base.ActivateDateComparator"
import = "jp.mosp.platform.comparator.base.InactivateComparator"
import = "jp.mosp.platform.comparator.base.PositionCodeComparator"
import = "jp.mosp.platform.comparator.system.PositionMasterGradeLevelComparator"
import = "jp.mosp.platform.comparator.system.PositionMasterPositionAbbrComparator"
import = "jp.mosp.platform.comparator.system.PositionMasterPositionNameComparator"



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
		<tr>
			<td class="TitleTd"><span class="RequiredLabel">*&nbsp;</span><span><%= params.getName("ActivateDate") %></span></td>
			<td class="InputTd">
				<div id="divSearchRate">
					<input type="text" class="Number4RequiredTextBox" id="txtEditActivateYear" name="txtEditActivateYear" value="<%= HtmlUtility.escapeHTML(vo.getTxtEditActivateYear()) %>" />&nbsp;<label for="txtEditActivateYear"><%= params.getName("Year") %></label>
					<input type="text" class="Number2RequiredTextBox" id="txtEditActivateMonth" name="txtEditActivateMonth" value="<%= HtmlUtility.escapeHTML(vo.getTxtEditActivateMonth()) %>" />&nbsp;<label for="txtEditActivateMonth"><%= params.getName("Month") %></label>
					<input type="text" class="Number2RequiredTextBox" id="txtEditActivateDay" name="txtEditActivateDay"  value="<%= HtmlUtility.escapeHTML(vo.getTxtEditActivateDay()) %>" />&nbsp;<label for="txtEditActivateDay"><%= params.getName("Day") %></label>
					<button type="button" class="Name2Button" id="btnEditActivateDate" onclick="submitForm(event, 'divSearchDate', null, '<%= ManHourMasterAction.CMD_SET_ACTIVATION_DATE %>')"><%= vo.getModeActivateDate().equals(PlatformConst.MODE_ACTIVATE_DATE_FIXED) ? params.getName("Change") : params.getName("Decision") %></button>
				</div>
			</td>
			<td class="TitleTd"><%= params.getName("ProjectNumber") %></td>
			<td class="BlankNumberTd">&nbsp;</td>
			<td class="TitleTd"><span class="RequiredLabel">*&nbsp;</span><span><%= params.getName("ProjectName") %></span></td>
			<td class="InputTd">
				<input type="text" class="Name15-40RequiredTextBox" id="txtEditProjectName" name="txtEditProjectName">
			</td>
		</tr>
		<tr>
			<td class="TitleTd"><span class="RequiredLabel">*&nbsp;</span><span><%= params.getName("Hierarchy") %></span></td>
			<td class="InputTd">

			</td>
		</tr>
	</table>
</div>
