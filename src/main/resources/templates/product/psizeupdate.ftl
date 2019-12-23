<div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <a href="${base}">首页</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li><a href="javascript:;">产品管理</a><i class="fa fa-angle-right"></i></li>
        <li>
            <span>款式条码编辑</span>
        </li>
    </ul>
</div>

<div class="col-md-12">
    <div class="portlet light portlet-fit portlet-datatable">
        <div class="portlet-title">
            <div class="caption">
                <i class="fa fa-table font-green"></i>
                <span class="caption-subject font-green bold uppercase">款式条码新增</span>
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
                    <div class="portlet-body " style="min-height: 562px">
                        <form class="form-horizontal" novalidate="novalidate">
                            <div class="form-body ">
                                <div class="row col-md-12">
                                    <div class="form-group col-md-6">
                                        <label class="control-label col-md-3">产品编码
                                            <span class="required"> * </span>
                                        </label>
                                        <div class="col-md-5">
                                            <input type="text" name="productcode" class="form-control" id="productcode"
                                                   disabled="disabled">
                                        </div>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label class="control-label col-md-3">颜色
                                        </label>
                                        <div class="col-md-5">
                                            <input type="text" name="color" class="form-control" id="color" maxlength="50">
                                        </div>
                                    </div>
                                </div>

                                <div class="row col-md-12">
                                    <div class="form-group col-md-6">
                                        <label class="control-label col-md-3">尺码
                                            <span class="required" aria-required="true"> * </span>
                                        </label>
                                        <div class="col-md-5">
                                            <select id="size" name="size" class="form-control input-sm select2">
                                                <option value="">--请选择-</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label class="control-label col-md-3">条码
                                            <span class="required"> * </span>
                                        </label>
                                        <div class="col-md-5">
                                            <input type="text" name="sku" class="form-control" id="sku" maxlength="30"></div>
                                    </div>
                                </div>

                                <div class="row col-md-12">
                                    <div class="form-group col-md-6">
                                        <label class="control-label col-md-3">批次</label>
                                        <div class="col-md-5">
                                            <select id="batch" name="batch" class="form-control input-sm select2">
                                                <option value="">--请选择-</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label class="control-label col-md-3">标准单价
                                        </label>
                                        <div class="col-md-5">
                                            <input type="text" name="standardPrice" maxlength="18" class="form-control" id="standardPrice" onkeyup="clearNoNum(this)">
                                        </div>
                                    </div>
                                </div>

                                <div class="row col-md-12">
                                    <div class="form-group col-md-6">
                                        <label class="control-label col-md-3">标准成本
                                        </label>
                                        <div class="col-md-5">
                                            <input type="text" name="standardCost" maxlength="18" class="form-control" id="standardCost" onkeyup="clearNoNum(this)"></div>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label class="control-label col-md-3">销售成本
                                        </label>
                                        <div class="col-md-5">
                                            <input type="text" name="saleCost" maxlength="18" class="form-control" id="saleCost" onkeyup="clearNoNum(this)">
                                        </div>
                                    </div>
                                </div>

                                <div class="row col-md-12">
                                    <div class="form-group col-md-6">
                                        <label class="control-label col-md-3">安全码
                                        </label>
                                        <div class="col-md-5">
                                            <input type="text" name="securityCode" maxlength="30" class="form-control" id="securityCode">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                        <div class="row">
                            <div class="col-md-offset-5 col-md-12" style="margin-top:40px">
                                <button type="submit" class="btn green" id="submit">提交</button>
                                <button type="button" class="btn grey-salsa btn-outline" style="margin-left: 10px;" id="cancel">取消</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

