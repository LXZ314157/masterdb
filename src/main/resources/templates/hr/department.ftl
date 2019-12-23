<!-- BEGIN PAGE BAR -->
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li><a href="${base}">首页</a><i class="fa fa-angle-right"></i></li>
        <li><a href="javascript:;">HR管理</a><i class="fa fa-angle-right"></i></li>
        <li><span>组织结构</span></li>
    </ul>
</div>
<!-- END PAGE BAR -->
<!-- BEGIN PAGE AREA-->
<div class="portlet light portlet-fit">
    <div class="portlet-body">
        <div class="row">
            <div class=" col-md-6">
                <div class="portlet light bordered">
                    <div class="portlet-title">
                        <div class="caption">
                            <i class="icon-bar-chart font-dark"></i>
                            <span class="caption-subject font-dark bold uppercase">组织结构列表</span>
                        </div>
                    </div>
                    <div class="portlet-body">
                        <div id="jstree_dept_div"></div>
                    </div>
                </div>
            </div>
            <div class=" col-md-6">
                <div class="portlet light bordered" id="bu_block" style="display: none;">
                    <div class="portlet-title">
                    </div>
                    <div class="portlet-body form-horizontal">
                        <div class="row">
                            <input type="hidden" id="buId" name="buId" value="0"/>
                            <div class="form-group col-md-12">
                                <label class="control-label col-md-3">
                                    事业部名:
                                    <span class="required">* </span>
                                </label>
                                <div class="col-md-8">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" class="form-control" name="buName" id="buName"
                                               placeholder="事业部名"/>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group col-md-12" id="buStatusArea" style="display: none;">
                                <label class="control-label col-md-3">
                                    事业部状态:
                                </label>
                                <div class="col-md-8">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <div class="btn-group btn-toggle-status" data-toggle="buttons" id="areaBuStatus">
                                            <label class="btn default" data-val="0">
                                                <input type="radio" class="toggle" value="0"> 关闭 </label>
                                            <label class="btn default" data-val="1">
                                                <input type="radio" class="toggle" value="1"> 打开 </label>
                                            <input type="hidden" id="hidBuState" name="hidBuState" value="0"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group col-md-12">
                                <label class="control-label col-md-3">
                                    事业部描述:
                                </label>
                                <div class="col-md-8">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <textarea name="buDesc" id="buDesc" rows="3" class="form-control"
                                                  placeholder="事业部描述"></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>


                <div class="portlet light bordered" id="dept_block" style="display: none;">
                    <div class="portlet-title">
                        <div class="caption">
                            <i class="fa fa-table font-green"></i>
                            <span class="caption-subject font-green bold uppercase" id="dept_title">部门角色</span>
                        </div>
                    </div>
                    <div class="portlet-body form-horizontal">
                        <div class="row">
                            <input type="hidden" id="deptId" name="deptId" value="0"/>



                            <div class="form-group col-md-12">
                                <label class="control-label col-md-3">
                                    组长/店长:
                                </label>
                                <div class="col-md-8">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" class="form-control" name="member01" id="member01"/>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group col-md-12">
                                <label class="control-label col-md-3">
                                    一级负责人（主管）:
                                </label>
                                <div class="col-md-8">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" class="form-control" name="principal_level1" id="principal_level1"/>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group col-md-12">
                                <label class="control-label col-md-3">
                                    二级负责人（经理）:
                                </label>
                                <div class="col-md-8">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" class="form-control" name="principal_level2" id="principal_level2"/>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group col-md-12">
                                <label class="control-label col-md-3">
                                    高级经理:
                                </label>
                                <div class="col-md-8">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" class="form-control" name="member02" id="member02"/>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group col-md-12">
                                <label class="control-label col-md-3">
                                    部门总监:
                                </label>
                                <div class="col-md-8">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" class="form-control" name="chief" id="chief"/>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group col-md-12">
                                <label class="control-label col-md-3">
                                    高级总监:
                                </label>
                                <div class="col-md-8">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" class="form-control" name="member03" id="member03"/>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group col-md-12">
                                <label class="control-label col-md-3">
                                    副总经理:
                                </label>
                                <div class="col-md-8">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" class="form-control" name="vice_president" id="vice_president"/>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group col-md-12">
                                <label class="control-label col-md-3">
                                    总经理:
                                </label>
                                <div class="col-md-8">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" class="form-control" name="president" id="president"/>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group col-md-12">
                                <label class="control-label col-md-3">
                                    CEO:
                                </label>
                                <div class="col-md-8">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" class="form-control" name="member04" id="member04"/>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group col-md-12">
                                <label class="control-label col-md-3">
                                    人事专员:
                                </label>
                                <div class="col-md-8">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" class="form-control" name="hr_bp" id="hr_bp"/>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group col-md-12">
                                <label class="control-label col-md-3">
                                    零售运营:
                                </label>
                                <div class="col-md-8">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" class="form-control" name="member05" id="member05"/>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group col-md-12">
                                <label class="control-label col-md-3">
                                    ITBP:
                                </label>
                                <div class="col-md-8">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" class="form-control" name="member06" id="member06"/>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group col-md-12">
                                <label class="control-label col-md-6">
                                </label>
                                <div class="col-md-1">
                                    <div class="input-icon right">
                                        <a  onclick="sync2OA()" class="input-group-addon btn blue fileinput-exists" data-dismiss="fileinput" > 同步到OA </a>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
                <div class="portlet light bordered" id="staff_block" style="display: none;">
                    <div class="portlet-title">
                        <div class="caption">
                            <i class="fa fa-table font-green"></i>
                            <span class="caption-subject font-green bold uppercase" id="zone_title">部门人员</span>
                        </div>
                    </div>
                    <div class="portlet-body form-horizontal">
                        <div class="row">
                            <input type="hidden" id="zoneId" name="zoneId" value="0"/>


                                <div class="table-container">
                                    <table class="table table-striped table-bordered table-hover table-checkable" id="deptStafflist">
                                        <thead>
                                        <tr>
                                            <th style="min-width:80px;">员工编号</th>
                                            <th>员工名</th>
                                            <th>性别</th>
                                            <th>手机号码</th>
                                            <th>直接上级</th>
                                            <th>岗位</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<input type="hidden" id="department_id"/>
</div>