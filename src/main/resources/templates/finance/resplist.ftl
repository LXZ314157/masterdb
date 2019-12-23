<div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <a href="${base}">首页</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a href="javascript:;">财务管理</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <span>责任中心</span>
        </li>
    </ul>
</div>
<div class="row portlet light bordered" style=" margin: 10px;margin-bottom: 30px;height: 150px;margin-top: 10px;">
    <div class="col-md-12">
        <div class="portlet light portlet-fit ">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-table font-green"></i>
                    <span class="caption-subject font-green bold uppercase">查询条件</span>
                </div>
            </div>
            <div class="portlet-body" style="padding-bottom: 5px;">


                <div class="row col-md-6">
                    <div class="form-group ">
                        <label class="control-label col-md-4 "
                               style="text-align: right;line-height: 33px;">责任中心编号/名称:</label>
                        <div class="col-md-6" about="">
                            <div class="input-icon right">
                                <i class="fa"></i>
                                <input type="text" name="respcenterId" id="respcenterId" class="form-control" placeholder=""/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row col-md-6">
                    <div class="form-group  ">
                        <label class="control-label col-md-4" style="text-align: right;line-height: 33px;">语言类别:</label>
                        <div class="col-md-6" about="">
                            <div class="input-icon right">
                                <i class="fa"></i>
                                <select name="lanCode" id="lanCode" class="form-control">
                                    <option value="">---请选择---</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
<div class="row portlet light bordered" style="margin:5px">
    <div class="col-md-12">
        <div class="portlet light portlet-fit portlet-datatable">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-table font-green"></i>
                    <span class="caption-subject font-green bold uppercase">责任中心列表</span>
                </div>
            </div>
            <div class="portlet-body" style="padding-bottom: 5px;">
                <div class="table-container">
                    <table class="table table-striped table-bordered table-hover table-checkable" id="resplist">
                        <thead>
                        <tr>
                            <th style="min-width:80px;">责任中心编号</th>
                            <th>语言</th>
                            <#--<th>资源编号</th>-->
                            <th>责任中心名称</th>
                            <th>责任中心管理者</th>
                            <#--<th>所属部门</th>-->
                            <th>责任中心描述</th>
                            <th>生效时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <form id="frmMoedlDownload" name="frmMoedlDownload"  style="display:none;">
        <input type="hidden" id="respcenterIdOrName" name="respcenterIdOrName" value=""/>
        <input type="hidden" id="lanCodes" name="lanCodes" value=""/>
    </form>

</div>


