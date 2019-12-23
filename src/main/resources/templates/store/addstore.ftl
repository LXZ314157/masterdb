<div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <a href="${base}">首页</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a href="javascript:;">店铺管理</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <span id="actionTitle">新增店铺</span>
        </li>
    </ul>
</div>
<div class="row">
    <div class="col-md-12">

        <div class="portlet light portlet-fit portlet-datatable">
            <div class="portlet-title" id="firstdiv" >
                <div class="caption">
                    <i class="fa fa-table font-green"></i>
                    <span class="caption-subject font-green bold uppercase">信息编辑</span>
                </div>
            </div>
            <div class="portlet  portlet-body form col-md-12" id="stepfirst">
                <form action="#" class="form-horizontal form_completejob form_complete_date">
                    <div class="form-body">
                        <div class="alert alert-danger display-hide" id="first-danger">
                            <button class="close" data-close="alert"></button>
                            红标字段输入错误, 请检查。
                        </div>
                        <div class="alert alert-success display-hide">
                            <button class="close" data-close="alert"></button>
                            验证通过
                        </div>
                        <input type="hidden" id="storeNo" name="storeNo" value="0">
                        <div class="row col-md-12">
                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    店铺ID
                                </label>
                                <div class="col-md-5" about="">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" name="storeId" id="storeId" class="form-control" onblur="verifysotreid(this.value)" placeholder="非必填，格式为5到10位数字或字母"/>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4 text-right">
                                    360对比店铺
                                </label>
                                <div class="col-md-5" about="">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <select id="compareStoreNo" name="" class="form-control select2-multiple" multiple>

                                        </select>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div class="row col-md-12">
                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    店铺名
                                </label>
                                <div class="col-md-5">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" name="storeName" id="storeName" class="form-control"
                                               placeholder=""/>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    店铺负责人
                                </label>
                                <div class="col-md-5" about="">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <select class="form-control input-sm select2" id="managerNum" name="managerNum" >
                                            <optgroup id="managerNumId">
                                                <option value="">-- 请选择 --</option>
                                            </optgroup>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row col-md-12">

                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    店铺简称
                                </label>
                                <div class="col-md-5" about="">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" name="storeShortName" id="storeShortName"
                                               class="form-control" placeholder=""/>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    拼音简写
                                </label>
                                <div class="col-md-5">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" name="storeSpell" id="storeSpell" class="form-control"
                                               placeholder=""/>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row col-md-12">

                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    联系电话
                                </label>
                                <div class="col-md-5" about="">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" name="tel" id="tel" class="form-control"
                                               placeholder=""/>
                                    </div>
                                </div>
                            </div>


                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    所在城市
                                </label>
                                <div class="col-md-5" about="">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <select id="cityId" name="cityId"
                                                class="form-control input-sm select2">
                                            <optgroup label="城市" id="city">
                                                <option value="">-- 请选择 --</option>
                                            </optgroup>
                                        </select>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div class="row col-md-12">
                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    所属代理商
                                </label>
                                <div class="col-md-5" about="">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <select class="form-control input-sm select2" id="buyerId"
                                                name="buyerId">
                                            <optgroup label="所属代理商" id="buyer">
                                                <option value="">-- 请选择 --</option>
                                            </optgroup>
                                        </select>
                                    </div>
                                </div>
                            </div>


                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    所属分区
                                </label>
                                <div class="col-md-5" about="">
                                    <div class="input-icon right">
                                        <select name="zone" id="zone" class="form-control select2-multiple" onchange="getManagecenter(this.value)">
                                            <option value="">-- 请选择 --</option>
                                        </select>
                                    </div>
                                </div>
                            </div>

                        </div>

                        <div class="row col-md-12">
                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    销售性质
                                </label>
                                <div class="col-md-5" about="">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <select name="storeType" id="storeType" class="form-control input-sm select2">
                                            <option value="">-- 请选择 --</option>
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    店铺级别
                                </label>
                                <div class="col-md-5" about="">
                                    <div class="input-icon right">
                                        <select id="storeLevel" name="storeLevel" class="form-control input-sm select2">
                                            <option value="">-- 请选择 --</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row col-md-12">

                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    手机
                                </label>
                                <div class="col-md-5" about="">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" name="mobile" id="mobile" class="form-control"
                                               placeholder=""/>
                                    </div>
                                </div>
                            </div>


                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    传真
                                </label>
                                <div class="col-md-5">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" name="fax" id="fax" class="form-control" placeholder=""/>
                                    </div>
                                </div>
                            </div>

                        </div>



                        <div class="row col-md-12">

                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    联系人
                                </label>
                                <div class="col-md-5">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" name="storeConnect" id="storeConnect" class="form-control"
                                               placeholder=""/>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    店铺状态
                                </label>
                                <div class="btn-group col-md-7" id="status" data-toggle="buttons">
                                    <label class="btn btn-default " id="state_2">
                                        <input type="radio" name="rstate" class="toggle" value="2"> 新店待开
                                    </label>
                                    <label class="btn btn-default statedefault active" id="state_1">
                                        <input type="radio" name="rstate" class="toggle" value="1" checked> 正常
                                    </label>
                                    <label class="btn btn-default statedefault " id="state_3">
                                        <input type="radio" name="rstate" class="toggle" value="3"> 闭店未关
                                    </label>
                                    <label class="btn btn-default statedefault " id="state_0">
                                        <input type="radio" name="rstate" class="toggle" value="0"> 关闭
                                    </label>
                                </div>

                            </div>
                        </div>

                        <div class="row col-md-12">

                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    Email
                                </label>
                                <div class="col-md-5">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" name="email" id="email" class="form-control" placeholder=""/>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    店铺面积
                                </label>
                                <div class="col-md-5" about="">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input onkeyup="checkNum(this)" type="text" name="area" id="area"
                                               class="form-control"
                                               placeholder=""/>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div class="row col-md-12">

                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    开业时间
                                </label>
                                <div class="col-md-5" about="">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" name="startTime" id="startTime" readonly="readonly"
                                               class="form-control form-control-inline start" placeholder="(点击选择时间)"/>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    结业时间
                                </label>
                                <div class="col-md-5">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" name="endTime"  id="endTime" onchange="setPeriod()" readonly="readonly"
                                               class="form-control form-control-inline end"
                                               placeholder="(点击选择时间)"/>
                                    </div>
                                </div>
                            </div>

                        </div>

                        <div class="row col-md-12">
                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    合约期
                                </label>
                                <div class="col-md-5" about="">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" name="contractPeriod" id="contractPeriod"
                                               class="form-control"
                                               placeholder=""/>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    备注
                                </label>
                                <div class="col-md-5" about="">
                                    <textarea class="form-control" id="extra"></textarea>
                                </div>
                            </div>

                        </div>
                        <div class="row col-md-12">
                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    销售渠道
                                </label>
                                <div class="col-md-5" about="">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <select name="storeOwnership" id="storeOwnership" class="form-control select2-multiple">
                                            <option value="" >---请选择---</option>
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    店铺类型
                                </label>
                                <div class="col-md-5" about="">
                                    <div class="input-icon right">
                                        <select id="storeCategory" name="storeCategory" class="form-control select2-multiple">
                                            <option value="">---请选择---</option>
                                        </select>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div class="row col-md-12">

                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    商场类型
                                </label>
                                <div class="col-md-5" about="">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <select name="mallType" id="mallType" class="form-control select2-multiple">
                                            <option value="">---请选择---</option>
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    专柜地址
                                </label>
                                <div class="col-md-5" about="">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input onkeyup="checkNum(this)" type="text" name="storeAddress" id="storeAddress"
                                               class="form-control"
                                               placeholder=""/>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div class="row col-md-12">

                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    所在楼层
                                </label>
                                <div class="col-md-5">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" name="floor" onkeyup="checkNum(this)" id="floor" class="form-control" placeholder=""/>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    押金
                                </label>
                                <div class="col-md-5" about="">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" name="deposit" id="deposit" onkeyup="clearNoNum(this)" class="form-control"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row col-md-12">

                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    租金
                                </label>
                                <div class="col-md-5">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" name="rental" id="rental" onkeyup="clearNoNum(this)" class="form-control" placeholder=""/>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    销售扣点
                                </label>
                                <div class="col-md-5" about="">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" name="salePoint" id="salePoint" onkeyup="clearNoNum(this)"
                                               class="form-control"
                                               placeholder=""/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row col-md-12">

                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    店铺类别
                                </label>
                                <div class="col-md-5" about="">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <select name="storeClass" id="storeClass" class="form-control select2-multiple">
                                            <option value="" id="">---请选择---</option>
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4 text-right">
                                    产品线
                                </label>
                                <div class="col-md-5" about="">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <select id="productLine" name="" class="form-control select2-multiple" multiple>

                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row col-md-12">
                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    最近重装日期
                                </label>
                                <div class="col-md-5">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" name="recentResetTime"  id="recentResetTime" onchange="setPeriod()" readonly="readonly"
                                               class="form-control form-control-inline end"
                                               placeholder="(点击选择时间)"/>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    装修标准
                                </label>
                                <div class="col-md-5" about="">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" name="decorationStandard" id="decorationStandard" class="form-control"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row col-md-12">

                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    装修年限
                                </label>
                                <div class="col-md-5">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" name="decorationLimitYears" maxlength="3" id="decorationLimitYears" class="form-control" placeholder=""/>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    店铺地址经度
                                </label>
                                <div class="col-md-5" about="">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" name="storeAddressLongitude" id="storeAddressLongitude" class="form-control"/>
                                    </div>
                                </div>
                            </div>

                        </div>

                        <div class="row col-md-12">
                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    店铺地址纬度
                                </label>
                                <div class="col-md-5">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" name="storeAddressLatitude" id="storeAddressLatitude" class="form-control"/>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    店铺形象类别
                                </label>
                                <div class="col-md-5" about="">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <select name="storeImageCategory" id="storeImageCategory" class="form-control select2-multiple">
                                            <option value="" >---请选择---</option>
                                        </select>
                                    </div>
                                </div>
                            </div>

                        </div>

                        <div class="row col-md-12">
                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    装修版本
                                </label>
                                <div class="col-md-5" about="">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <select name="installVersion" id="installVersion" class="form-control select2-multiple">
                                            <option value="" >---请选择---</option>
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    LOGO版本
                                </label>
                                <div class="col-md-5" about="">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <select name="logoVersion" id="logoVersion" class="form-control select2-multiple">
                                            <option value="" >---请选择---</option>
                                        </select>
                                    </div>
                                </div>
                            </div>

                        </div>
                </form>
            </div>
        </div>
        <div class="portlet  portlet-body form col-md-12" id="stepsecond">
            <div class="portlet-title" style="margin-bottom: 10px">
                <div class="caption">
                    <i class="fa fa-table font-green"></i>
                    <span class="caption-subject font-green bold uppercase" style="font-size: 16px;">属性明细</span>
                </div>
            </div>

            <form action="#" class="form-horizontal def_completejob" id="storeAttribDefArea">
                <div class="row col-md-12">

                    <div class="form-group col-md-6">
                        <label class="control-label col-md-4">
                            责任中心
                        </label>
                        <div class="col-md-5" about="">
                            <div class="input-icon right">
                                <i class="fa"></i>
                                <select class="form-control input-sm select2" id="respCenter" name="respCenter" >
                                    <optgroup id="respCenterId">
                                        <option value="">-- 请选择 --</option>
                                    </optgroup>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="form-group col-md-6">
                        <label class="control-label col-md-4">
                            成本中心
                        </label>
                        <div class="col-md-5" about="">
                            <div class="input-icon right">
                                <i class="fa"></i>
                                <select class="form-control input-sm select2" id="costCenter" name="costCenter" >
                                    <optgroup id="costCenterId">
                                        <option value="">-- 请选择 --</option>
                                    </optgroup>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="row col-md-12">
                    <div class="form-group col-md-6">
                        <label class="control-label col-md-4">
                            现场管理中心
                        </label>
                        <div class="col-md-5" about="">
                            <div class="input-icon right">
                                <i class="fa"></i>
                                <select class="form-control input-sm select2" id="manageCenter" name="manageCenter" >
                                    <optgroup id="manageCenterId">
                                        <option value="">-- 请选择 --</option>
                                    </optgroup>
                                </select>
                            </div>
                        </div>
                    </div>


                    <div class="form-group col-md-6">
                        <label class="control-label col-md-4">
                            公司别
                        </label>
                        <div class="col-md-5" about="">
                            <div class="input-icon right">
                                <i class="fa"></i>
                                <select class="form-control input-sm select2" id="company" name="company" >
                                    <optgroup id="companyId">
                                        <option value="">-- 请选择 --</option>
                                    </optgroup>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>



                <div class="alert alert-danger display-hide" id="def_danger">
                    <button class="close" data-close="alert"></button>
                    红标字段输入错误, 请检查。
                </div>
                <div class="alert alert-success display-hide" id="def_success">
                    <button class="close" data-close="alert"></button>
                    验证通过
                </div>
            </form>
            <div class="footer col-md-12">
                <button type="button" class="btn blue " id="next1">下一步</button>
            </div>
        </div>

        <div class="portlet  portlet-body form col-md-12" id="stepthird" style="display: none">

            <div class="form-group col-md-6">
                <label class="control-label col-md-4 text-right">
                    同步设定
                </label>
                <div class="col-md-6" about="">
                    <div class="input-icon right">
                        <i class="fa"></i>
                        <select id="synSetting" name="" class="form-control select2-multiple" multiple>
                            <#--<option selected value="emax">同步到博俊</option>-->
                            <option selected value="itransfer">同步到货品平台</option>
                            <option selected value="rfid">同步到RFID</option>
                            <option selected value="wms">同步到WMS</option>
                            <option selected value="burgeon">同步到新伯俊</option>
                                <#--暂时注释，等正式环境发布插件以后放开-->
                            <#--<option selected value="xt">同步到小钛</option>-->
                        </select>
                    </div>
                </div>
            </div>
            <div class="footer col-md-12">
                <button type="button" class="btn blue" id="before">上一步</button>
                <button type="button" class="btn blue" id="save">保存</button>
            </div>
        </div>

        <div class="portlet light portlet-fit portlet-datatable" id="addaddressdiv">
            <input type="hidden" id="hassaved" value="0"/>
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-table font-green"></i>
                    <span class="caption-subject font-green bold uppercase">新增地址</span>
                </div>
            </div>
            <div class="portlet  portltonget-body form col-md-12" id="stepfirst">
                <form action="#" class="form-horizontal form_address form_complete_date">
                    <div class="form-body">
                        <div class="alert alert-danger display-hide">
                            <button class="close" data-close="alert"></button>
                            红标字段输入错误, 请检查。
                        </div>
                        <div class="alert alert-success display-hide">
                            <button class="close" data-close="alert"></button>
                            验证通过
                        </div>
                        <input type="hidden" id="storeNo" name="storeNo" value="0">
                        <div class="row col-md-12">

                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    地址类型
                                </label>
                                <div class="col-md-5" about="">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <select id="addressType" class="form-control input-sm select2">
                                            <option value="" name="addressType">-- 请选择 --</option>
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    联系人
                                </label>
                                <div class="col-md-5" about="">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" name="contact" id="contact"
                                               class="form-control" placeholder=""/>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div class="row col-md-12">

                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    办公电话
                                </label>
                                <div class="col-md-5">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" name="phone" maxlength="50" id="phone" class="form-control"
                                               placeholder=""/>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    手机号码
                                </label>
                                <div class="col-md-5" about="">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" name="mobilePhone" maxlength="50" id="mobilePhone" class="form-control"
                                               placeholder=""/>
                                    </div>
                                </div>
                            </div>

                        </div>

                        <div class="row col-md-12">

                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    详细地址
                                </label>
                                <div class="col-md-5" about="">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" name="address" id="address" class="form-control" maxlength="200" placeholder=""/>
                                    </div>
                                </div>
                            </div>


                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    地址状态
                                </label>
                                <div class="col-md-5" about="">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <select class="form-control input-sm select2" id="addressState" name="addressState">
                                            <option value="">-- 请选择 --</option>
                                            <option value="1">有效</option>
                                            <option value="0">无效</option>
                                        </select>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div class="row col-md-12">
                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    是否默认
                                </label>
                                <div class="col-md-5" about="">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <select class="form-control input-sm select2" id="isDefault" >
                                            <option value="" name="isDefault">-- 请选择 --</option>
                                            <option value="1" name="isDefault">是</option>
                                            <option value="0" name="isDefault">否</option>
                                        </select>
                                    </div>
                                </div>
                            </div>

                        </div>


                        <div>
                            <button type="button" class="btn blue" id="addrsave" style="margin-left: 20px">保存</button>
                        </div>
                </form>
            </div>
        </div>

        <div class="portlet  portlet-body form col-md-12" >
            <div class="portlet-title" style="margin-bottom: 10px;margin-left: 20px;" id="savedaddressdiv" style="display: none">
                <div class="caption">
                    <i class="fa fa-table font-green"></i>
                    <span class="caption-subject font-green bold uppercase" style="font-size: 17px;">已保存地址</span>
                </div>
            </div>

            <div id="savedAddr">
            </div>


            <div class="footer col-md-12" id="buttion2">
                <button type="button" class="btn blue" id="before1">上一步</button>
                <button type="button" class="btn blue" id="next2" onclick="stepthird()">下一步</button>
            </div>
        </div>


    </div>
</div>

<!--修改地址信息窗口-->
<div id="storeAddrUpdateDiv" class="modal fade" tabindex="-1" data-width="600">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
        <h4 class="modal-title" id="groupTitle">修改地址信息</h4>
    </div>
    <div class="modal-body">
        <div class="form-horizontal" style="height: 335px">

            <div class="form-group">
                <label class="control-label col-md-3">
                    地址类型：
                </label>
                <div class="col-md-7" about="">
                    <div class="input-icon right">
                        <i class="fa"></i>
                        <select name="addrAdressType" id="addrAdressType" class="form-control">
                            <option value="">-- 请选择 --</option>
                        </select>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-3">联系人：</label>
                <div class="controls col-md-7">
                    <input type="text" placeholder="请输入联系人" maxlength="50" id="addrContact" class="form-control"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-md-3">办公电话：</label>
                <div class="controls col-md-7">
                    <input type="text" placeholder="请输入办公电话" maxlength="50" id="addrPhone" class="form-control"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-md-3">手机号码：</label>
                <div class="controls col-md-7">
                    <input type="text" placeholder="请输入手机号码" maxlength="50" id="addrMobilePhone" class="form-control"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-3">详细地址：</label>
                <div class="controls col-md-7">
                    <input type="text" placeholder="请输入详细地址" maxlength="200" id="addrAddress" class="form-control"/>
                </div>
            </div>

            <div class="form-group col-md-12">
                <label class="control-label col-md-3">
                    地址状态:
                </label>
                <div class="col-md-8">
                    <div class="input-icon right">
                        <i class="fa"></i>
                        <div class="btn-group btn-toggle-status" data-toggle="buttons" id="addressStateDiv">
                            <label class="btn default" data-val="0">
                                <input type="radio" name="addressState" class="toggle" value="0"> 无效 </label>
                            <label class="btn default" data-val="1">
                                <input type="radio" name="addressState"  class="toggle" value="1"> 有效 </label>
                        </div>
                    </div>
                </div>
            </div>

            <div class="form-group col-md-12">
                <label class="control-label col-md-3">
                    是否默认:
                </label>
                <div class="col-md-8">
                    <div class="input-icon right">
                        <i class="fa"></i>
                        <div class="btn-group btn-toggle-status" data-toggle="buttons" id="addrIsDefaultDiv">
                            <label class="btn default" data-val="0">
                                <input type="radio" name="addrIsDefault" class="toggle" value="0"> 否 </label>
                            <label class="btn default" data-val="1">
                                <input type="radio" name="addrIsDefault"  class="toggle" value="1"> 是 </label>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
    <div class="modal-footer">
        <button class="btn btn-success" id="saveStoreAddr" data-tid="0" value="0">保存</button>
        <a data-dismiss="modal" class="btn btn-inverse" href="#">取消</a>
    </div>
    <input type="hidden" id="storeAddrId"/>
</div>


