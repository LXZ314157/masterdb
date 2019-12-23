<div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <a href="${base}">首页</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li><a href="javascript:;">产品管理</a><i class="fa fa-angle-right"></i></li>
        <li>
            <span>款式信息编辑</span>
        </li>
    </ul>
</div>
<#--<div class="row">-->
<div class="col-md-12">
    <div class="portlet light portlet-fit portlet-datatable">
        <div class="portlet-title">
            <div class="caption">
                <i class="fa fa-table font-green"></i>
                <span class="caption-subject font-green bold uppercase">款式信息编辑</span>
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
                                    <label class="control-label col-md-3">产品编码
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="productcode" class="form-control" id="productcode" disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">品牌
                                    </label>
                                    <div class="col-md-6">
                                        <select id="brand" name="brand" class="form-control select2-multiple">
                                                <option value="">-- 请选择 --</option>
                                            </optgroup>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">价格
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="price" maxlength="18" class="form-control" id="price" ></div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">品名
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="pname" maxlength="100" class="form-control" id="pname" ></div>
                                </div>
                                <#--<div class="form-group col-md-6">-->
                                    <#--<label class="control-label col-md-3">成本-->
                                    <#--</label>-->
                                    <#--<div class="col-md-6">-->
                                        <#--<input type="text" name="cost" class="form-control" id="cost" maxlength="18"></div>-->
                                <#--</div>-->
                                <div class="form-group col-md-6 ">
                                    <label class="control-label col-md-3">大类
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="catedl" class="form-control" id="catedl" disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group col-md-6 ">
                                    <label class="control-label col-md-3">当前状态
                                    </label>
                                    <div class="col-md-6">
                                        <select name="syncstatus" id="syncstatus" class="form-control input-sm select2">
                                            <option value="">-- 请选择 --</option>
                                            <option value="0">已更新</option>
                                            <option value="1">有更新</option>
                                            <option value="2">新增</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group col-md-6 ">
                                    <label class="control-label col-md-3">中类
                                    </label>
                                    <div class="col-md-6">
                                        <select id="catezl" name="catezl" class="form-control select2-multiple" onchange="getCategoryLevle2(this.value)">
                                            <option value="">-- 请选择 --</option>
                                            </optgroup>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group col-md-6 ">
                                    <label class="control-label col-md-3">可用状态
                                    </label>
                                    <div class="col-md-6">
                                        <select name="status" id="status" class="form-control input-sm select2">
                                            <option value="">-- 请选择 --</option>
                                            <option value="1">可用</option>
                                            <option value="0">不可用</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group col-md-6 " id="catexlDiv">
                                    <label class="control-label col-md-3">小类
                                    </label>
                                    <div class="col-md-6">
                                        <select id="catexl" name="catexl" class="form-control select2-multiple">
                                            <option value="">-- 请选择 --</option>
                                            </optgroup>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group col-md-6 ">
                                    <label class="control-label col-md-3">创建时间
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="createtime" class="form-control" id="createtime"
                                               disabled="disabled">
                                    </div>
                                </div>

                                <div class="form-group col-md-6 ">
                                    <label class="control-label col-md-3">上次更新时间
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="updatetime" class="form-control" id="updatetime"
                                               disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group col-md-6 ">
                                    <label class="control-label col-md-3">单位
                                    </label>
                                    <div class="col-md-6">
                                        <select class="form-control input-sm select2" id="uom" name="uom">
                                            <option value="">-- 请选择 --</option>
                                            </optgroup>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">详细描述
                                    </label>
                                    <div class="col-md-6">
                                        <textarea class="form-control" rows="3" name="remarks" id="remarks" disabled="disabled"></textarea>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="row" style="margin-top: 30px">
            <div class="col-md-12">
                <div class="portlet light portlet-fit portlet-form bordered col-md-12">

                    <div class="portlet-title" id="attrdetailDiv">
                        <div class="caption">
                            <i class="icon-bar-chart font-dark"></i>
                            <span class="caption-subject font-dark bold uppercase">属性明细</span>
                        </div>
                    </div>

                    <#--扩展属性内容-->
                    <form action="#" class="form-horizontal def_completejob">
                        <div class="form-body" id="attribute_add">
                        </div>
                    </form>

                </div>
            </div>
        </div>
        <div class="row" style="margin-top: 40px" id="smt">
            <div class="col-md-offset-3 col-md-9">
                <button class="btn btn-primary green" id="submit">提交</button>
            </div>
        </div>
    </div>
</div>
<#--</div>-->