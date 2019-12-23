<div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <a href="${base}">首页</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li><a href="javascript:;">供应商管理</a><i class="fa fa-angle-right"></i></li>
        <li>
            <span>供应商信息详情</span>
        </li>
    </ul>
</div>
<#--<div class="row">-->
<div class="col-md-12">
    <div class="portlet light portlet-fit portlet-datatable">
        <div class="portlet-title">
            <div class="caption">
                <i class="fa fa-table font-green"></i>
                <span class="caption-subject font-green bold uppercase">供应商信息</span>
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
                                    <label class="control-label col-md-3">供应商编号
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="supplierCode" class="form-control" id="supplierCode"
                                               disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">名称
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="supplierName" class="form-control" id="supplierName"
                                               disabled="disabled"></div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">名称描述
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="supplierDescription" class="form-control" id="supplierDescription"
                                               disabled="disabled"></div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">供应商类别
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="supplierCSuppliertypeId" class="form-control" id="supplierCSuppliertypeId"
                                               disabled="disabled"></div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">法人
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="supplierLegalperson" class="form-control" id="supplierLegalperson"
                                               disabled="disabled"></div>
                                </div>
                                <div class="form-group col-md-6 ">
                                    <label class="control-label col-md-3">联系人
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="supplierContacter" class="form-control" id="supplierContacter"
                                               disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group col-md-6 ">
                                    <label class="control-label col-md-3">地址
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="supplierAddress" class="form-control" id="supplierAddress"
                                               disabled="disabled">
                                    </div>
                                </div>

                                <div class="form-group col-md-6 ">
                                    <label class="control-label col-md-3">邮编
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="supplierPostal" class="form-control" id="supplierPostal"
                                               disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group col-md-6 ">
                                    <label class="control-label col-md-3">电话
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="supplierPhone" class="form-control" id="supplierPhone"
                                               disabled="disabled">
                                    </div>
                                </div>

                                <div class="form-group col-md-6 ">
                                    <label class="control-label col-md-3">手机
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="supplierMobile" class="form-control" id="supplierMobile"
                                               disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group col-md-6 ">
                                    <label class="control-label col-md-3">国家
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="supplierCCountryId" class="form-control" id="supplierCCountryId"
                                               disabled="disabled">
                                    </div>
                                </div>

                                <div class="form-group col-md-6 ">
                                    <label class="control-label col-md-3">省份
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="supplierCProvinceId" class="form-control" id="supplierCProvinceId"
                                               disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group col-md-6 ">
                                    <label class="control-label col-md-3">城市
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="supplierCCityId" class="form-control" id="supplierCCityId"
                                               disabled="disabled">
                                    </div>
                                </div>

                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">电子邮件
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="supplierEmail" class="form-control" id="supplierEmail"
                                               disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">传真
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="supplierFax" class="form-control" id="supplierFax"
                                               disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">开户行
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="supplierDepositBank" class="form-control" id="supplierDepositBank"
                                               disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">银行账号
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="supplierAccount" class="form-control" id="supplierAccount"
                                               disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">税号
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="supplierTaxno" class="form-control" id="supplierTaxno"
                                               disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">公司名称
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="supplierCompanyname" class="form-control" id="supplierCompanyname"
                                               disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-3">状态
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="supplierDataStatusName" class="form-control" id="supplierDataStatusName"
                                               disabled="disabled">
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<#--</div>-->