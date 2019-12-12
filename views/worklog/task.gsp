<%@ page import="com.chinaunicom.devlab.support.group.big19.UserPerson; com.chinaunicom.devlab.support.group.big19.TaskProgress"%>
<!doctype html>
<html>
<head>
    <meta name="layout" content="big19main"/>
    <title>任务列表</title>
</head>
<body>
<!-- MAIN -->
    <div class="main">
        <div class="main-content panelLT">
            <div class="col-md-12">
                <div class="panel panelLb">
                    <div class="panel-heading panel-bottom">
                        <label for="">任务填报月份：</label>
                        <g:select onchange="window.location.href='${createLink(controller: 'worklog', action:'task')}/${params.id}?month='+this.value" class="select" name="month"  from="${monthMap}" optionKey="key" optionValue="value" value="${params.month}"/>
                    </div>
                    <div class="panel-body">
                        <table class="table table-bordered table-bottom">
                            <tbody>
                            <tr>
                                <td class="title-bold">任务编号</td>
                                <td>${taskBasic.code}</td>
                            </tr>
                            <tr>
                                <td class="title-bold">任务名称</td>
                                <td>${taskBasic.name}</td>
                            </tr>
                            <tr>
                                <td class="title-bold">任务描述</td>
                                <td>${taskBasic.memo}</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="col-md-12">
                <div class="panel status">
                    <div class="panel-body panel-bottom">
                        <div class="panel-heading col-md-6 heading-TB">
                            <%
                                statusClassL = ""
                                if (leadProgress?.status=='进行中') statusClassL = 'label label-warning'
                                else if (leadProgress?.status=='已完成') statusClassL = 'label label-success'
                                else if (leadProgress?.status=='滞后') statusClassL = 'label label-danger'
                            %>
                            <label>本月状态：</label><spna class="${statusClassL}">${leadProgress?.status}</spna>
                        </div>
                        %{--<div class="panel-heading col-md-6 heading-TB">--}%
                            %{--<label for="">上月进度状态：</label><span class="label label-warning">进行中</span>--}%
                        %{--</div>--}%
                    </div>
                </div>
            </div>
            <div class="col-md-12">
                <div class="panel panelLb">
                    <div class="panel-body">
                        <table class="table tableMN">
                            <thead>
                            <tr>
                                <th>职责</th>
                                <th>单位</th>
                                <th>责任人</th>
                                <th>联系人</th>
                                <th>参与人</th>
                                <th>配合事项</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td><span class="label label-success">牵头单位</span></td>
                                <td>${taskBasic?.leadTeam?.company?.name}</td>
                                <td>${taskBasic?.leadTeam?.principals*.name}</td>
                                <td>${taskBasic?.leadTeam?.contacts*.name}</td>
                                <td width="26%">${taskBasic?.leadTeam?.participants*.name}</td>
                                <td width="30%">${taskBasic?.leadTeam?.matter}</td>
                            </tr>
                            <g:each in="${teamList.sort {it.company.name}}" status="i" var="team">
                            <tr>
                                <td><span class="label label-warning">配合单位</span></td>
                                <td>${team?.company?.name}</td>
                                <td>${team?.principals*.name}</td>
                                <td>${team?.contacts*.name}</td>
                                <td width="26%">${team?.participants*.name}</td>
                                <td width="30%">${team?.matter}</td>
                            </tr>
                            </g:each>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="col-md-12 qiantou">
                <div class="panel panelLb">
                    <div class="panel-heading panel-bottom">
                        <label for="">牵头单位信息填报</label>
                    </div>
                    <div class="panel-body">
                        <table class="table table-bordered table-bottom mytable">
                            <tbody>
                            <tr>
                                <td class="title-bold">${leadProgress?.team?.company?.name}</td>
                                <td class="textLeft listOne-all"><spna class="${statusClassL}">${leadProgress?.status}</spna></td>
                            </tr>
                            <tr>
                                <td class="title-bold">承诺目标</td>
                                <td class="textLeft listTwo-all">${leadProgress?.promise}</td>
                            </tr>
                            <tr>
                                <td class="title-bold">承诺目标KR</td>
                                <td class="textLeft listTwo-all">${leadProgress?.promiseKr}</td>
                            </tr>
                            <tr>
                                <td class="title-bold">愿景目标</td>
                                <td class="textLeft listTwo-all">${leadProgress?.vision}</td>
                            </tr>
                            <tr>
                                <td class="title-bold">愿景目标KR</td>
                                <td class="textLeft listTwo-all">${leadProgress?.visionKr}</td>
                            </tr>
                            <tr>
                                <td class="title-bold">落实方案及举措</td>
                                <td class="textLeft listTwo-all">${leadProgress?.methord}</td>
                            </tr>
                            <tr>
                                <td class="title-bold">进展情况</td>
                                <td class="textLeft listThree-all">${leadProgress?.job}</td>
                            </tr>
                            <tr>
                                <td class="title-bold">后续安排</td>
                                <td class="textLeft listFive-all">${leadProgress?.plan}</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <p class="demo-button text-center">
                        <!--牵头单位-->
                        <g:if test="${session._current_user in (taskBasic?.leadTeam?.contacts + taskBasic?.leadTeam?.principals + taskBasic?.leadTeam?.participants + com.chinaunicom.devlab.support.group.big19.UserPerson.findAllByUsername('lihui5'))}">
                            <button onclick="prepareUpForm(${leadProgress.id},'${leadProgress.status}',
                                '${leadProgress.methord?.replaceAll('\r\n','\\\\r\\\\n')?.replaceAll("'","\\\\\'")}',
                                '${leadProgress.job?.replaceAll('\r\n','\\\\r\\\\n')?.replaceAll("'","\\\\\'")}',
                                '${leadProgress.plan?.replaceAll('\r\n','\\\\r\\\\n')?.replaceAll("'","\\\\\'")}',
                                '${leadProgress.promise?.replaceAll('\r\n','\\\\r\\\\n')?.replaceAll("'","\\\\\'")}',
                                '${leadProgress.vision?.replaceAll('\r\n','\\\\r\\\\n')?.replaceAll("'","\\\\\'")}',
                                '${leadProgress.promiseKr?.replaceAll('\r\n','\\\\r\\\\n')?.replaceAll("'","\\\\\'")}',
                                '${leadProgress.visionKr?.replaceAll('\r\n','\\\\r\\\\n')?.replaceAll("'","\\\\\'")}',
                                true)"
                                    type="button" class="btn btn-success Allbutton" data-toggle="modal" data-target="#headAll">牵头单位汇总</button>
                        </g:if>
                        <g:if test="${session._current_user?.name in session._interface_person_list}">
                            <button type="button" class="btn btn-danger" onclick="remindTask()">督办提醒</button>
                        </g:if>
                    </p>
                </div>
            </div>
            <g:if test="${taskBasic.cooperateTeams}">
            <div class="col-md-12">
                <div class="panel panelLb">
                    <div class="panel-heading panel-bottom">
                        <label for="">配合单位信息填报</label>
                    </div>
                    <g:each in="${TaskProgress.findAllByTeamInListAndMonth(taskBasic.cooperateTeams, params.month)}" status="i" var="cooperateProgress">
                    <div class="panel-body peihe">
                        <table class="table table-bordered table-bottom">
                            <tbody>
                            <tr>
                                <td class="title-bold">${cooperateProgress?.team?.company?.name}</td>
                                <td class="textLeft listOne-all"></td>
                            </tr>
                            <tr>
                                <td class="title-bold">落实方案及举措</td>
                                <td class="textLeft listTwo-all">${cooperateProgress?.methord}</td>
                            </tr>
                            <tr>
                                <td class="title-bold">进展情况</td>
                                <td class="textLeft listThree-all">${cooperateProgress?.job}</td>
                            </tr>
                            <tr>
                                <td class="title-bold">后续安排</td>
                                <td class="textLeft listFive-all">${cooperateProgress?.plan}</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <g:if test="${session._current_user in (cooperateProgress?.team?.contacts + cooperateProgress?.team?.principals + cooperateProgress?.team?.participants + com.chinaunicom.devlab.support.group.big19.UserPerson.findAllByUsername('lihui5'))}">
                    <p class="demo-button text-center">
                        <button onclick="prepareUpForm(${cooperateProgress.id},'${cooperateProgress.status}',
                            '${cooperateProgress.methord?.replaceAll('\r\n','\\\\r\\\\n')?.replaceAll("'","\\\\\'")}',
                            '${cooperateProgress.job?.replaceAll('\r\n','\\\\r\\\\n')?.replaceAll("'","\\\\\'")}',
                            '${cooperateProgress.plan?.replaceAll('\r\n','\\\\r\\\\n')?.replaceAll("'","\\\\\'")}',
                            '${leadProgress.promise?.replaceAll('\r\n','\\\\r\\\\n')?.replaceAll("'","\\\\\'")}',
                            '${leadProgress.vision?.replaceAll('\r\n','\\\\r\\\\n')?.replaceAll("'","\\\\\'")}',
                            '${leadProgress.promiseKr?.replaceAll('\r\n','\\\\r\\\\n')?.replaceAll("'","\\\\\'")}',
                            '${leadProgress.visionKr?.replaceAll('\r\n','\\\\r\\\\n')?.replaceAll("'","\\\\\'")}',
                            false)"
                                type="button" class="btn btn-warning myModal PHbutton" data-toggle="modal" data-target="#headAll">配合单位填报</button>
                    </p>
                    </g:if>
                    </g:each>
                </div>
            </div>
            </g:if>
            <!--信息填报模态窗口-->
            <g:form name="upForm" action="update">
            <div class="modal fade" id="headAll" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog tanchu" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">牵头单位信息填报</h4>
                        </div>
                        <div class="modal-body">
                            <div class="panel">
                                <div class="panel-body">
                                    <textarea name="promise" class="form-control" placeholder="承诺目标" rows="4"></textarea>

                                    <textarea name="promiseKr" class="form-control" placeholder="承诺目标（O）对应关键结果（KR）" rows="4"></textarea>

                                    <textarea name="vision" class="form-control" placeholder="愿景目标" rows="4"></textarea>

                                    <textarea name="visionKr" class="form-control" placeholder="愿景目标（O）对应关键结果（KR）" rows="4"></textarea>

                                    <textarea name="methord" class="form-control" placeholder="落实方案及举措" rows="4"></textarea>

                                    <textarea name="job" class="form-control" placeholder="进展情况" rows="4"></textarea>

                                    <textarea name="plan" class="form-control" placeholder="后续安排" rows="4"></textarea>

                                    <g:select class="form-control sle_add" name="status"  from="${TaskProgress.constrainedProperties.status.inList}" value=""/>
                                    %{--任务完成预判、存在问题以及解决举措--}%
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="submit" onclick="upForm.submit();" class="btn btn-primary sure" data-dismiss="modal">提交</button>
                            %{--<button type="button" class="btn btn-warning" data-dismiss="modal">保存</button>--}%
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        </div>
                    </div>
                </div>
            </div>
                <input type="hidden" name="month" value="${params.month}"/>
                <input type="hidden" name="id" value=""/>
                <input type="hidden" name="leader" value=""/>
            </g:form>
        </div>
    </div>
<script language="JavaScript">
    function prepareUpForm(id, status, methord, job, plan, promise, vision, promiseKr, visionKr, leader) {
        upForm.id.value = id;
        upForm.status.value = status;
        upForm.methord.value = methord;
        upForm.job.value = job;
        upForm.plan.value = plan;
        upForm.promise.value = promise;
        upForm.vision.value = vision;
        upForm.promiseKr.value = promiseKr;
        upForm.visionKr.value = visionKr;
        upForm.leader.value = leader;
        if (leader) {
            upForm.status.style.display = 'inline';
            upForm.promise.style.display = 'inline';
            upForm.vision.style.display = 'inline';
            upForm.promiseKr.style.display = 'inline';
            upForm.visionKr.style.display = 'inline';
            myModalLabel.innerText = '牵头单位信息汇总';
        }
        else {
            myModalLabel.innerText = '配合单位信息填报';
            upForm.status.style.display = 'none';
            upForm.promise.style.display = 'none';
            upForm.vision.style.display = 'none';
            upForm.promiseKr.style.display = 'none';
            upForm.visionKr.style.display = 'none';
        }
    }

    function remindTask() {
        if (!confirm('系统只给未填报完整的联系人发送邮件。\r\n批量发送可能需要较长时间，邮件会在后台保证发送成功，如果您想收到回复消息，请不要离开此页。\r\n请注意不要频繁发送提醒邮件。\r\n\r\n是否现在发送？')) return;
        $.ajax({
            async: true,
            type: "get",
            url: "../remind/${taskBasic.id}.json?month=${params.month}",
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
