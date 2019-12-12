<%@ page import="com.chinaunicom.devlab.support.group.big19.TaskProgress" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="big19main"/>
    <title>任务列表</title>
    <% nf = java.text.NumberFormat.getInstance(); nf.setGroupingUsed(false);nf.setMaximumFractionDigits(2) %>
</head>
<body>
    <!-- MAIN -->
    <div class="main">
        <div class="main-content panelLT">
            <div class="col-md-12">
                <div class="panel">
                    <div class="panel-heading">
                        <label for="">任务填报月份：</label>
                        <g:select onchange="window.location.href='${createLink(controller: 'worklog')}?month='+this.value" class="select" name="month"  from="${monthMap}" optionKey="key" optionValue="value" value="${params.month}"/>
                    </div>
                    <div class="panel-body">
                        <div class="row msg">
                            <div class="msgList">
                                <div class="metric">
                                    <span class="icon"><asset:image src="jt.png"/></span>
                                    <p>
                                        <%
                                            companyName = '集团技术部'
                                            TasksOfCompany = taskBasicList.findAll{item -> item.leadTeam.company.name == companyName}
                                            taskTotal = TasksOfCompany.size()
                                            taskFilled = progressList.findAll{item -> ((item.task in TasksOfCompany)&&(item.status!='未填报'))}.size()
                                        %>
                                        <span class="title">${companyName}</span>
                                        <span class="number">${nf.format(100*taskFilled/taskTotal)}% (${taskFilled}/${taskTotal})</span>
                                    </p>
                                </div>
                            </div>
                            <div class="msgList">
                                <div class="metric">
                                    <span class="icon"><asset:image src="yjy.png"/></span>
                                    <p>
                                        <%
                                            companyName = '研究院'
                                            TasksOfCompany = taskBasicList.findAll{item -> item.leadTeam.company.name == companyName}
                                            taskTotal = TasksOfCompany.size()
                                            taskFilled = progressList.findAll{item -> ((item.task in TasksOfCompany)&&(item.status!='未填报'))}.size()
                                        %>
                                        <span class="title">${companyName}</span>
                                        <span class="number">${nf.format(100*taskFilled/taskTotal)}% (${taskFilled}/${taskTotal})</span>
                                    </p>
                                </div>
                            </div>
                            <div class="msgList">
                                <div class="metric">
                                    <span class="icon"><asset:image src="ryy.png"/></span>
                                    <p>
                                        <%
                                            companyName = '软研院'
                                            TasksOfCompany = taskBasicList.findAll{item -> item.leadTeam.company.name == companyName}
                                            taskTotal = TasksOfCompany.size()
                                            taskFilled = progressList.findAll{item -> ((item.task in TasksOfCompany)&&(item.status!='未填报'))}.size()
                                        %>
                                        <span class="title">${companyName}</span>
                                        <span class="number">${nf.format(100*taskFilled/taskTotal)}% (${taskFilled}/${taskTotal})</span>
                                    </p>
                                </div>
                            </div>
                            <div class="msgList">
                                <div class="metric">
                                    <span class="icon"><asset:image src="wyy.png"/></span>
                                    <p>
                                        <%
                                            companyName = '网研院'
                                            TasksOfCompany = taskBasicList.findAll{item -> item.leadTeam.company.name == companyName}
                                            taskTotal = TasksOfCompany.size()
                                            taskFilled = progressList.findAll{item -> ((item.task in TasksOfCompany)&&(item.status!='未填报'))}.size()
                                        %>
                                        <span class="title">${companyName}</span>
                                        <span class="number">${nf.format(100*taskFilled/taskTotal)}% (${taskFilled}/${taskTotal})</span>
                                    </p>
                                </div>
                            </div>
                            <div class="msgList">
                                <div class="metric">
                                    <span class="icon"><asset:image src="wlwyjy.png"/></span>
                                    <p>
                                        <%
                                            companyName = '物联网研究院'
                                            TasksOfCompany = taskBasicList.findAll{item -> item.leadTeam.company.name == companyName}
                                            taskTotal = TasksOfCompany.size()
                                            taskFilled = progressList.findAll{item -> ((item.task in TasksOfCompany)&&(item.status!='未填报'))}.size()
                                        %>
                                        <span class="title">${companyName}</span>
                                        <span class="number">${nf.format(100*taskFilled/taskTotal)}% (${taskFilled}/${taskTotal})</span>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel-body">
                        <table class="table table-bordered" id="tb">
                            <thead>
                            <tr>
                                <th>网络强国战略</th><!--21条-->
                                <th>防范化解重大风险</th><!--网络安全和信息化-->
                                <th>十九大-党组41号</th><!--科技部和国资委意见-->
                                %{--<th>五新联通</th>--}%
                                <th width="150">分解任务编号</th>
                                <th>分解任务名称</th>
                                <th width="110">牵头单位</th>
                                <th>进度</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <g:each in="${taskBasicList}" status="i" var="task">
                            <tr>
                                <td width="20%">${task.big19Entry}</td>
                                <td width="18%">${task.itSecureEntry}</td>
                                <td width="18%">${task.omsAndSasacEntry}</td>
                                %{--<td width="14%">${task.newUnicom}</td>--}%
                                <td colspan="1">${task.code}</td>
                                <td colspan="1">${task.name}</td>
                                <td colspan="1">${task?.leadTeam?.company?.name}</td>
                                <%
                                    //status = TaskProgress.findByTaskAndMonthAndType(task, params.month, '牵头')?.status
                                    status = progressList.find{it -> it.task==task}?.status
                                    statusClass = ""
                                    if (status=='进行中') statusClass = 'label label-warning'
                                    else if (status=='已完成') statusClass = 'label label-success'
                                    else if (status=='滞后') statusClass = 'label label-danger'
                                %>
                                <td colspan="1" width="60"><span class="${statusClass}">${status?:'未填报'}</span></td>
                                <td colspan="1" width="50"><g:link controller="worklog" action="task" id="${task.id}" params="[month:params.month]">查看</g:link></td>
                            </tr>
                            </g:each>
                            </tbody>
                        </table>
                    </div>
                    <!--</div>-->
                </div>
            </div>
        </div>

        <div class="panel-body text-center bottom_p">
        <g:if test="${session._current_user?.company?.name != '非填报' }">
            <g:if test="${session._current_user?.name in (session._interface_person_list + '李晖')}">
                <g:link controller="worklog" action="exportSelf" params="[month:params.month]"><button type="button" class="btn btn-warning">本单位导出</button></g:link>
            </g:if>
            <g:if test="${session._current_user?.company?.name == '集团技术部' || session?._current_user?.username == 'lihui5'}">
                <g:link controller="worklog" action="exportAll" params="[month:params.month]"><button type="button" class="btn btn-success">导出全部</button></g:link>
                <button type="button" class="btn btn-danger" onclick="remindAll()">督办提醒</button>
            </g:if>
        </g:if>
        </div>
    </div>
<script language="JavaScript">
    autoRowSpan(tb,0,3);
    autoRowSpan(tb,0,2);
    autoRowSpan(tb,0,1);
    autoRowSpan(tb,0,0);

    function autoRowSpan(tb,row,col)
    {
        var lastValue="";
        var value="";
        var pos=1;
        for(var i=row;i<tb.rows.length;i++){
            value = tb.rows[i].cells[col].innerText;
            if(lastValue == value){
                tb.rows[i].deleteCell(col);
                tb.rows[i-pos].cells[col].rowSpan = tb.rows[i-pos].cells[col].rowSpan+1;
                pos++;
            }else{
                lastValue = value;
                pos=1;
            }
        }
    }

    function remindAll() {
        if (!confirm('系统只给未填报完整的联系人发送邮件。\r\n批量发送可能需要较长时间，邮件会在后台保证发送成功，如果您想收到回复消息，请不要离开此页。\r\n请注意不要频繁发送提醒邮件。\r\n\r\n是否现在发送？')) return;
        $.ajax({
            async: true,
            type: "get",
            url: "remind.json?month=${params.month}",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
                alert("已经给" + data.sendCount + "位未填报完整的联系人发送提醒邮件。");
            }
        });
    }
</script>
</body>
</html>
