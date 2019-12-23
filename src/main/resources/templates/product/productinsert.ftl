<div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <a href="${base}">首页</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a href="javascript:;">产品管理</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <span>款式信息新增</span>
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
                                        <span class="required"> * </span>
                                    </label>
                                    <div class="col-md-4">
                                        <input type="text" name="productcode" class="form-control" id="productcode">
                                    </div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">产品类型
                                        <span class="required" aria-required="true"> * </span>
                                    </label>
                                    <div class="col-md-4">
                                        <select class="form-control" name="select" id="type">
                                            <option value="">请选择</option>
                                            <option value="1">衣服</option>
                                            <option value="2">书籍</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">价格
                                        <span class="required" aria-required="true"> * </span>
                                    </label>
                                    <div class="col-md-4">
                                        <input type="text" name="price" class="form-control" id="price"></div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">基本单位
                                        <span class="required" aria-required="true"> * </span>
                                    </label>
                                    <div class="col-md-4">
                                        <input type="text" name="union" class="form-control" id="union"></div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">品名
                                        <span class="required"> * </span>
                                    </label>
                                    <div class="col-md-4">
                                        <input type="text" name="pname" class="form-control" id="pname"></div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">成本
                                        <span class="required"> * </span>
                                    </label>
                                    <div class="col-md-4">
                                        <input type="text" name="cost" class="form-control" id="cost"></div>
                                </div>
                                <div class="form-group col-md-6 ">
                                    <label class="control-label col-md-3">品牌
                                        <span class="required"> * </span>
                                    </label>
                                    <div class="col-md-4">
                                        <input type="text" name="brand" class="form-control" id="brand"></div>
                                </div>
                                <div class="form-group col-md-6 ">
                                    <label class="control-label col-md-3">波
                                    </label>
                                    <div class="col-md-4">
                                        <select class="form-control" name="wave" id="wave">
                                            <option value="">请选择</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group col-md-6 ">
                                    <label class="control-label col-md-3">段
                                    </label>
                                    <div class="col-md-4">
                                        <select class="form-control" name="band" id="band">
                                            <option value="">请选择</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group col-md-6 ">
                                    <label class="control-label col-md-3">组
                                    </label>
                                    <div class="col-md-4">
                                        <select class="form-control" name="group" id="group">
                                            <option value="">请选择</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group col-md-6 ">
                                    <label class="control-label col-md-3">线
                                    </label>
                                    <div class="col-md-4">
                                        <select class="form-control" name="line" id="line">
                                            <option value="">请选择</option>
                                        </select>
                                    </div>
                                </div>


                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">详细描述
                                        <span class="required"> * </span>
                                    </label>
                                    <div class="col-md-4">
                                        <textarea class="form-control" rows="3" name="remarks" id="remarks"></textarea>
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
                    <div class="portlet-title">
                        <div class="caption">
                            <i class="icon-bar-chart font-dark"></i>
                            <span class="caption-subject font-dark bold uppercase">属性明细</span>
                        </div>
                    </div>
                    <div class="portlet-body ">
                        <form class="form-horizontal" novalidate="novalidate">
                            <div class="form-body " id="attributedetail">
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">商品码
                                        <span class="required"> * </span>
                                    </label>
                                    <div class="col-md-4">
                                        <input type="text" name="productno" class="form-control" id="productno"></div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">订单序号
                                    </label>
                                    <div class="col-md-4">
                                        <input type="text" name="productno" class="form-control" id="orderno"></div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">开发号
                                    </label>
                                    <div class="col-md-4">
                                        <input type="text" name="devno" class="form-control" id="devno"></div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">款型号
                                    </label>
                                    <div class="col-md-4">
                                        <input type="text" name="styleno" class="form-control" id="styleno"></div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">样板号
                                    </label>
                                    <div class="col-md-4">
                                        <input type="text" name="modelno" class="form-control" id="modelno"></div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">面料编号
                                    </label>
                                    <div class="col-md-4">
                                        <input type="text" name="materialno" class="form-control" id="materialno"></div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">面料名
                                    </label>
                                    <div class="col-md-4">
                                        <input type="text" name="materialname" class="form-control" id="materialname">
                                    </div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">花色
                                    </label>
                                    <div class="col-md-4">
                                        <input type="text" name="colorname" class="form-control" id="colorname"></div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">品种
                                    </label>
                                    <div class="col-md-4">
                                        <select class="form-control" name="productclass" id="productclass">

                                        </select>
                                    </div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">季节
                                    </label>
                                    <div class="col-md-4">
                                        <select class="form-control" name="natureseason" id="natureseason">
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">上市日期
                                    </label>
                                    <div class="col-md-4">
                                        <input type="text" name="salesdate" class="form-control" id="salesdate"></div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">色卡号
                                    </label>
                                    <div class="col-md-4">
                                        <input type="text" name="colorcardnumber" class="form-control"
                                               id="colorcardnumber"></div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">色卡名
                                    </label>
                                    <div class="col-md-4">
                                        <input type="text" name="colorcardname" class="form-control" id="colorcardname">
                                    </div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">颜色（色名）
                                    </label>
                                    <div class="col-md-4">
                                        <input type="text" name="colorname2" class="form-control" id="colorname2"></div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">工作组
                                    </label>
                                    <div class="col-md-4">
                                        <select class="form-control" name="workgroup" id="workgroup">

                                        </select>
                                    </div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">品牌
                                    </label>
                                    <div class="col-md-4">
                                        <input type="text" name="abrand" class="form-control" id="abrand"></div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">统一编码
                                    </label>
                                    <div class="col-md-4">
                                        <input type="text" name="code" class="form-control" id="code"></div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">规格
                                    </label>
                                    <div class="col-md-4">
                                        <input type="text" name="standard" class="form-control" id="standard"></div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">批次
                                    </label>
                                    <div class="col-md-4">
                                        <input type="text" name="code" class="form-control" id="batch"></div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">款号规则
                                    </label>
                                    <div class="col-md-4">
                                        <select class="form-control" name="stylerule" id="stylerule">
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">供应商编号
                                    </label>
                                    <div class="col-md-4">
                                        <input type="text" name="supplier" class="form-control" id="supplier"></div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">预估成本
                                    </label>
                                    <div class="col-md-4">
                                        <input type="text" name="estimatedcost" class="form-control" id="estimatedcost">
                                    </div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">预估倍率
                                    </label>
                                    <div class="col-md-4">
                                        <input type="text" name="estimatedrate" class="form-control" id="estimatedrate">
                                    </div>
                                </div>

                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="row" style="margin-top: 30px">
            <div class="col-md-offset-3 col-md-9">
                <button type="submit" class="btn green" id="submit">提交</button>
                <button type="button" class="btn grey-salsa btn-outline">取消</button>
            </div>
        </div>
    </div>
</div>
<#--</div>-->