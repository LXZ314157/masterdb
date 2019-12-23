<!-- BEGIN PAGE BAR -->
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li><a href="${base}">首页</a><i class="fa fa-angle-right"></i></li>
        <li><a href="javascript:;">后台管理</a><i class="fa fa-angle-right"></i></li>
        <li><span>图片检索系统帐号列表</span></li>
    </ul>
</div>
<!-- END PAGE BAR -->
<!-- BEGIN PAGE AREA-->
<div class="portlet light portlet-fit">
    <div class="portlet-body">
        <div class="row">
            <div class=" col-md-12">
                <div class="portlet light bordered">
                    <div class="portlet-title">
                        <div class="caption">
                            <i class="icon-bar-chart font-dark"></i>
                            <span class="caption-subject font-dark bold">图片检索系统帐号列表</span>
                        </div>
                    </div>
                    <div class="portlet-body">
                        <div class="table-container">
                            <table class="table table-striped table-bordered table-hover table-checkable"
                                   id="datatable">
                                <thead>
                                <tr role="row" class="heading">
                                    <th>操作</th>
                                    <th>帐号id</th>
                                    <th>登录名</th>
                                    <th>显示名</th>
                                    <th>域</th>
                                    <th>权限组</th>
                                </tr>
                                </thead>
                                <tbody></tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 新增、编辑图片检索系统帐号浮动层 -->
<div id="detail" class="modal fade" tabindex="-1">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
        <h4 class="modal-title" id="detail_title">新增图片检索系统帐号</h4>
    </div>
    <div class="modal-body">
        <form action="#" id="form_detail" class="form-horizontal">
            <div class="form-body">
                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="control-label col-md-2">
                            用户id
                        </label>
                        <div class="col-md-3">
                            <div class="input-icon right">
                                <i class="fa"></i>
                                <input type="text" class="form-control" name="accountId" id="accountId"
                                       readonly="readonly" placeholder="用户id"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group col-md-12">
                        <label class="control-label col-md-2">
                            登录名
                            <span class="required">* </span>
                        </label>
                        <div class="col-md-10">
                            <div class="input-icon right">
                                <i class="fa"></i>
                                <input type="text" class="form-control" name="loginname" id="loginname"
                                       placeholder="登录名"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group col-md-12">
                        <label class="control-label col-md-2">
                            显示名
                            <span class="required">* </span>
                        </label>
                        <div class="col-md-10">
                            <div class="input-icon right">
                                <i class="fa"></i>
                                <input type="text" class="form-control" name="truename" id="truename"
                                       placeholder="显示名"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group col-md-12">
                        <label class="control-label col-md-2">
                            域
                            <span class="required">* </span>
                        </label>
                        <div class="col-md-10">
                            <div class="input-icon right">
                                <i class="fa"></i>
                                <select name="domain" id="domain" class="form-control">
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="form-group col-md-12">
                        <label class="control-label col-md-2">
                            权限组
                            <span class="required">* </span>
                        </label>
                        <div class="col-md-10">
                            <div class="input-icon right">
                                <i class="fa"></i>
                                <select name="groupName" id="groupName" class="form-control">
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <div class="modal-footer">
        <input type="hidden" id="type" value="0"/>
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button type="button" class="btn btn-outline green" id="btnSave">确认</button>
    </div>
</div>