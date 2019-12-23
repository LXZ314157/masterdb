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
            <span id="buyerTitle">新增代理商</span>
        </li>
    </ul>
</div>
<div class="row">
    <div class="col-md-12">
        <div class="portlet light portlet-fit portlet-datatable">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-table font-green"></i>
                    <span class="caption-subject font-green bold uppercase">信息编辑</span>
                </div>
            </div>
            <div class="portlet-body form portlet light bordered col-md-12" id="stepfirst">
                <form action="#" class="form-horizontal form_completejob">
                    <div class="form-body">
                        <div class="alert alert-danger display-hide">
                            <button class="close" data-close="alert"></button>
                            红标字段输入错误, 请检查。
                        </div>
                        <div class="alert alert-success display-hide">
                            <button class="close" data-close="alert"></button>
                            验证通过
                        </div>
                        <input type="hidden" id="buyerValue" name="buyerValue" value="1">
                        <div class="row col-md-12">
                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    客户ID
                                </label>
                                <div class="col-md-5" about="">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" name="buyerid" id="buyerid" class="form-control" onblur="checkBuyerId(this.value)" placeholder="非必填，格式为6位数字或字母"/>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    客户名
                                </label>
                                <div class="col-md-5">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" name="buyername" id="buyername" class="form-control"
                                               placeholder=""/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row col-md-12">
                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    客户简称
                                </label>
                                <div class="col-md-5" about="">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" name="buyshortname" id="buyershotname" maxlength="50" class="form-control"
                                               placeholder=""/>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    全名
                                </label>
                                <div class="col-md-5">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" name="fullname" id="fullname" maxlength="200" class="form-control"
                                               placeholder=""/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row col-md-12">
                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    拼音首字母简写
                                </label>
                                <div class="col-md-5" about="">

                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" name="spellfirst" id="spellfirst" maxlength="50" class="form-control"
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
                                        <select name="cityId" id="cityId" class="form-control input-sm select2">
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
                                    代理商级别
                                </label>
                                <div class="col-md-5" about="">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <select name="type" id="type" class="form-control input-sm select2">

                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    客户状态
                                </label>
                                <div class="col-md-5">
                                    <div class="btn-group" id="state" name="state" data-toggle="buttons">
                                        <label class="btn btn-default" id="state_1">
                                            <input type="radio" name="rstate" id="rights" class="toggle" value="1"> 正常
                                        </label>
                                        <label class="btn btn-default" id="state_0">
                                            <input type="radio" name="rstate" id="ends" class="toggle" value="0"> 结束合作
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row col-md-12">
                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    联系人
                                </label>
                                <div class="col-md-5" about="">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" name="connectpeople" id="connectpeople" class="form-control" maxlength="50"
                                               placeholder=""/>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    联系电话
                                </label>
                                <div class="col-md-5">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" name="phone" id="phone" class="form-control" placeholder="" maxlength="50"/>
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
                                        <input type="text" name="mobile" id="mobile" class="form-control" placeholder="" maxlength="50"/>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    Email
                                </label>
                                <div class="col-md-5">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" name="email" id="email" maxlength="100" class="form-control" placeholder=""/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row col-md-12">
                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    传真
                                </label>
                                <div class="col-md-5" about="">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" name="fax" id="fax" maxlength="50" class="form-control" placeholder=""/>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    加盟费
                                </label>
                                <div class="col-md-5" about="">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" name="joinfee" id="joinfee" class="form-control" placeholder=""/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row col-md-12">
                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    合约开始时间
                                </label>
                                <div class="col-md-5" about="">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" name="startime" id="startime" readonly="readonly"
                                               class="form-control form-control-inline start"
                                               placeholder="(点击选择时间)">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    合约结束时间
                                </label>
                                <div class="col-md-5">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" name="endtime" id="endtime" readonly="readonly"
                                               class="form-control form-control-inline end"
                                               placeholder="(点击选择时间)">
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row col-md-12">
                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    法人
                                </label>
                                <div class="col-md-5" about="">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" name="legalPerson" id="legalPerson" maxlength="20" class="form-control"
                                               placeholder=""/>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    公司名称
                                </label>
                                <div class="col-md-5">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" name="companyName" id="companyName" maxlength="100" class="form-control" placeholder=""/>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row col-md-12">
                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    开户行
                                </label>
                                <div class="col-md-5" about="">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" name="bank_name" id="bankName" maxlength="30" class="form-control"
                                               placeholder=""/>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    银行账号
                                </label>
                                <div class="col-md-5">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" name="bank_account" maxlength="30" id="bankAccount" class="form-control" placeholder=""/>
                                    </div>
                                </div>
                            </div>
                        </div>


                        <div class="row col-md-12">
                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    税号
                                </label>
                                <div class="col-md-5" about="">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" name="taxNumber" maxlength="50" id="taxNumber" class="form-control"
                                               placeholder=""/>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    地址
                                </label>
                                <div class="col-md-5">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" name="buyerAddress" maxlength="200" id="buyerAddress" class="form-control" placeholder=""/>
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
                                        <select name="manageCenter" id="manageCenter" class="form-control input-sm select2" onchange="getZoneList(this.value)"  >
                                            <optgroup id="manageCenterId">
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
                                        <select class="form-control input-sm select2" name="zone" id="zone" class="form-control" onchange="checkManageCenter()">
                                            <option value="">-- 请选择 --</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row col-md-12">
                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4">
                                    备注
                                </label>
                                <div class="col-md-5">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <textarea class="form-control" rows="3" maxlength="500" name="remarks" id="remarks"></textarea>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div class="portlet portlet-body form  portlet light bordered col-md-12" id="stepsecond">
            <div class="portlet-title" style="margin-bottom: 10px">
                <div class="caption" style="margin-left: 16px;">
                    <i class="fa fa-table font-green"></i>
                    <span class="caption-subject font-green bold uppercase">属性明细</span>
                </div>
            </div>

            <form action="#" class="form-horizontal def_completejob">
                <div class="form-body" id="attribute_add">

                    <div class="alert alert-danger display-hide" id="def_danger">
                        <button class="close" data-close="alert"></button>
                        红标字段输入错误, 请检查。
                    </div>
                    <div class="alert alert-success display-hide" id="def_success">
                        <button class="close" data-close="alert"></button>
                        验证通过
                    </div>



                </div>

            </form>

            <div class="footer col-md-12">
            <#--<button type="button" class="btn blue" id="again">上一步</button>-->
                <button type="button" class="btn blue " id="next" style="bottom: 80px;right: 80px;">下一步</button>
            </div>
        </div>
        <div class="portlet-body form  portlet light bordered col-md-12" id="stepthree">
            <form action="#" class="form-horizontal">
                <div class="form-body" id="synchronize">
                    <div class="row col-md-12">
                        <div class="form-group">
                            <h4 class="control-label col-md-4">
                                同步设定:
                            </h4>
                            <div class="col-md-7 input-icon right" about="">
                                <select id="sel_menu2" multiple="multiple" class="form-control"
                                        style="width:400px;height: 100px;">
                                    <option selected value="emax">同步到博俊</option>
                                    <option selected value="itransfer"><i class="fa fa-times">同步到货品平台</option>
                                    <option selected value="rfid"><i class="fa fa-times">同步到RFID</option>
                                    <option selected value="burgeon"><i class="fa fa-times">同步到新伯俊</option>
                                    <option selected value="yxt"><i class="fa fa-times">同步到云学堂</option>
                                    <#--等正式环境插件发布以后放开-->
                                    <#--<option selected value="xt"><i class="fa fa-times">同步到小钛</option>-->
                                </select>
                            </div>
                        </div>
                    </div>

            </form>
        </div>
    </div>
    <div class="footer" id="nextsyn" style="bottom: 80px;right: 80px;">
        <button type="button" class="btn blue" id="again">上一步</button>
        <button type="button" class="btn blue" id="btnsave"> 保存</button>
    </div>
</div>
</div>
</div>


