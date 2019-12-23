<div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <a href="${base}">首页</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a href="javascript:;">代理商管理</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <span>代理商列表</span>
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
                               style="text-align: right;line-height: 33px;">客户名/客户ID:</label>
                        <div class="col-md-6" about="">
                            <div class="input-icon right">
                                <i class="fa"></i>
                                <input type="text" name="buyername" id="buyername" class="form-control" placeholder=""/>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="row col-md-6">
                    <div class="form-group  ">
                        <label class="control-label col-md-4" style="text-align: right;line-height: 33px;">客户状态:</label>
                        <div class="col-md-6" about="">
                            <div class="input-icon right">
                                <i class="fa"></i>
                                <select name="way" id="way" class="form-control">
                                    <option value="">-- 请选择 --</option>
                                    <option value="1">正常</option>
                                    <option value="0">结束合作</option>
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
                    <span class="caption-subject font-green bold uppercase">代理商列表</span>
                </div>

                <div class="dt-buttons" style="text-align: right;">
                    <#--<a class="dt-button buttons-collection buttons-colvis btn blue btn-outline" tabindex="0"
                       aria-controls="datatable" href="${base}/buyer/buyerinfo" id="newtype"><span>新增代理商</span></a>-->
                </div>

            </div>
            <div class="portlet-body" style="padding-bottom: 5px;">
                <div class="table-container">
                    <table class="table table-striped table-bordered table-hover table-checkable" id="buyerlist">
                        <thead>
                        <tr>
                            <th style="min-width:80px;">客户ID</th>
                            <th>客户名</th>

                            <th>联系人</th>
                            <th>联系电话</th>
                            <th>保证金</th>
                            <th>代理商平台标志</th>
                            <th>定金占比</th>
                            <th>状态</th>
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
        <input type="hidden" id="buyerNameOrId" name="buyerNameOrId" value=""/>
        <input type="hidden" id="buyerState" name="buyerState" value=""/>
    </form>

</div>


