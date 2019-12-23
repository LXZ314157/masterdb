<div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <a href="${base}">首页</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li><a href="javascript:;">HR管理</a><i class="fa fa-angle-right"></i></li>
        <li>
            <span>人员信息详情</span>
        </li>
    </ul>
</div>
<#--<div class="row">-->
<div class="col-md-12">
    <div class="portlet light portlet-fit portlet-datatable">
        <div class="portlet-title">
            <div class="caption">
                <i class="fa fa-table font-green"></i>
                <span class="caption-subject font-green bold uppercase">人员信息</span>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="portlet light portlet-fit portlet-form bordered col-md-12">
                    <div class="portlet-title">
                        <div class="caption">
                            <i class="icon-bar-chart font-dark"></i>
                            <span class="caption-subject font-dark bold uppercase">基本信息</span>
                        </div>
                    </div>
                    <div class="portlet-body ">
                        <form class="form-horizontal" novalidate="novalidate">
                            <div class="form-body ">
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">员工编号
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="staffNum" class="form-control" id="staffNum"
                                               disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">中文名
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="staffNameLocal" class="form-control" id="staffNameLocal"
                                               disabled="disabled"></div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">成本中心
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="costCenterId" class="form-control" id="costCenterId"
                                               disabled="disabled"></div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">证件类型
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="credentialType" class="form-control" id="credentialType"
                                               disabled="disabled"></div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">证件编号
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="credentialNum" class="form-control" id="credentialNum"
                                               disabled="disabled"></div>
                                </div>
                                <div class="form-group col-md-6 ">
                                    <label class="control-label col-md-3">出生日期
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="birthday" class="form-control" id="birthday"
                                               disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group col-md-6 ">
                                    <label class="control-label col-md-3">手机号码
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="mobile" class="form-control" id="mobile"
                                               disabled="disabled">
                                    </div>
                                </div>

                                <div class="form-group col-md-6 ">
                                    <label class="control-label col-md-3">邮箱
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="email" class="form-control" id="email"
                                               disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group col-md-6 ">
                                    <label class="control-label col-md-3">电话
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="phone" class="form-control" id="phone"
                                               disabled="disabled">
                                    </div>
                                </div>

                                <div class="form-group col-md-6 ">
                                    <label class="control-label col-md-3">分机号
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="extnum" class="form-control" id="extnum"
                                               disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group col-md-6 ">
                                    <label class="control-label col-md-3">门禁卡号
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="doorcontrolnum" class="form-control" id="doorcontrolnum"
                                               disabled="disabled">
                                    </div>
                                </div>

                                <div class="form-group col-md-6 ">
                                    <label class="control-label col-md-3">员工状态
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="staffState" class="form-control" id="staffState"
                                               disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group col-md-6 ">
                                    <label class="control-label col-md-3">入职日期
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="joinDate" class="form-control" id="joinDate"
                                               disabled="disabled">
                                    </div>
                                </div>

                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">离职日期
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="demissionDate" class="form-control" id="demissionDate"
                                               disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">是否成本中心负责人
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="isDirector" class="form-control" id="isDirector"
                                               disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">岗位
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="postName" class="form-control" id="postName"
                                               disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">职级
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="staffLevel" class="form-control" id="staffLevel"
                                               disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">所属公司
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="company" class="form-control" id="company"
                                               disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">直接上级
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="superiorNum" class="form-control" id="superiorNum"
                                               disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">办公地点
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="officeLocation" class="form-control" id="officeLocation"
                                               disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">员工编制
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="staffType" class="form-control" id="staffType"
                                               disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">部门
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="departmentId" class="form-control" id="departmentId"
                                               disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">岗位序列
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="jobSequence" class="form-control" id="jobSequence"
                                               disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">人员类型
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="league" class="form-control" id="league"
                                               disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">国籍
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="nationality" class="form-control" id="nationality"
                                               disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">民族
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="ethnic" class="form-control" id="ethnic"
                                               disabled="disabled">
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<#--</div>-->