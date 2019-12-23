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
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-table font-green"></i>
                    <span class="caption-subject font-green bold uppercase">新增地址</span>
                </div>
            </div>
            <div class="portlet  portlet-body form col-md-12" id="stepfirst">
                <form action="#" class="form-horizontal form_completejob form_complete_date">
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
                                        <select id="addressType" name="addressType" class="form-control input-sm select2">
                                            <option value="">-- 请选择 --</option>
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
                                        <input type="text" name="contact" id="contact" maxlength="50"
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
                                        <input type="text" name="phone" id="phone" class="form-control" maxlength="50"
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
                                        <input type="text" name="mobilePhone" id="mobilePhone" class="form-control" maxlength="50"
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
                                        <select class="form-control input-sm select2" id="isDefault" name="isDefault">
                                            <option value="">-- 请选择 --</option>
                                            <option value="1">是</option>
                                            <option value="0">否</option>
                                        </select>
                                    </div>
                                </div>
                            </div>

                        </div>


                        <div>
                            <button type="button" class="btn blue" id="addrsave">保存</button>
                        </div>

                </form>
            </div>
        </div>
        <div class="portlet  portlet-body form col-md-12" id="stepsecond">
            <div class="portlet-title" style="margin-bottom: 10px" >
                <div class="caption">
                    <i class="fa fa-table font-green"></i>
                    <span class="caption-subject font-green bold uppercase">已保存地址</span>
                </div>
            </div>
            <div id="savedAddr">
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





