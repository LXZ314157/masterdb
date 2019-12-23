<div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <a href="${base}">首页</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li><a href="javascript:;">HR管理</a><i class="fa fa-angle-right"></i></li>
        <li>
            <span>岗位信息详情</span>
        </li>
    </ul>
</div>
<#--<div class="row">-->
<div class="col-md-12">
    <div class="portlet light portlet-fit portlet-datatable">
        <div class="portlet-title">
            <div class="caption">
                <i class="fa fa-table font-green"></i>
                <span class="caption-subject font-green bold uppercase">岗位信息</span>
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
                                    <label class="control-label col-md-3">岗位编号
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="postId" class="form-control" id="postId"
                                               disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">语言
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="lanCode" class="form-control" id="lanCode"
                                               disabled="disabled"></div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">源数据编号
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="sourceId" class="form-control" id="sourceId"
                                               disabled="disabled"></div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">岗位名称
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="postName" class="form-control" id="postName"
                                               disabled="disabled"></div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">部门
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="departmentId" class="form-control" id="departmentId"
                                               disabled="disabled"></div>
                                </div>
                                <div class="form-group col-md-6 ">
                                    <label class="control-label col-md-3">上级岗位
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="paPostId" class="form-control" id="paPostId"
                                               disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group col-md-6 ">
                                    <label class="control-label col-md-3">部门负责人
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="charger" class="form-control" id="charger"
                                               disabled="disabled">
                                    </div>
                                </div>

                                <div class="form-group col-md-6 ">
                                    <label class="control-label col-md-3">岗位状态
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="postState" class="form-control" id="postState"
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