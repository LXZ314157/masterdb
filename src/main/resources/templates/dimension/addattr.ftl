<!-- BEGIN PAGE BAR -->
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li><a href="${base}">首页</a><i class="fa fa-angle-right"></i></li>
        <li><a href="javascript:;">维度管理</a><i class="fa fa-angle-right"></i></li>
        <li><span>新增企划</span></li>
    </ul>
</div>

<div class="portlet light portlet-fit portlet-datatable">
    <div class="portlet-title">
        <div class="caption">
            <i class="fa fa-table font-green"></i>
            <span class="caption-subject font-green bold uppercase">新增属性</span>
        </div>
    </div>

    <form class="col-md-12 portlet-body" id="container">
        <div class="form-group col-md-6">
            <label class="control-label col-md-4 text-right">
                产品编号
                <span class="required">* </span>
            </label>
            <div class="col-md-6">
                <div class="input-icon right">
                    <i class="fa"></i>
                    <input type="text" id="productCode" class="form-control">
                </div>
            </div>

        </div>
    </form>
    <div class="col-md-12 text-center" style="margin-top: 40px">
        <button class="btn btn-primary " id="btnSaveAttr">提交</button>
        <button class="btn grey" id="return" style="margin-left: 20px">返回</button>
    </div>
</div>
