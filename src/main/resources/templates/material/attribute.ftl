<!-- BEGIN PAGE BAR -->
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li><a href="${base}">${home ! "首页"}</a><i class="fa fa-angle-right"></i></li>
        <li><a href="javascript:;">${module ! "主数据维护"}</a><i class="fa fa-angle-right"></i></li>
        <li><a href="javascript:;">${title ! "物料列表"}</a><i class="fa fa-angle-right"></i></li>
        <li><span>${materialDetail ! "物料明细"}</span></li>
    </ul>
</div>

<div class="row">
    <div class="col-md-12">
        <div class="portlet light portlet-fit portlet-datatable">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-table font-green"></i>
                    <span class="caption-subject font-green bold uppercase">${basicMessage !"基本信息"}</span>
                </div>
            </div>
            <div class="portlet-body form" id="stepfirst">
                <div class="form-horizontal form_completejob">
                    <div class="form-body">
                        <div class="form-group col-md-6">
                            <label class="control-label col-md-4">
                            ${materialCode ! "物料编码"}
                            </label>
                            <div class="col-md-5" about="">
                                <div class="input-icon right">
                                    <i class="fa"></i>
                                    <input type="text" name="materialCode" id="materialCode" disabled
                                           class="form-control"
                                           placeholder=""/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group col-md-6">
                            <label class="control-label col-md-4">
                            ${materialName ! "物料名称"}
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon right">
                                    <i class="fa"></i>
                                    <input type="text" disabled name="materialName" id="materialName"
                                           class="form-control"
                                           placeholder=""/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group col-md-6">
                            <label class="control-label col-md-4">
                            ${dlName ! "所属大类"}
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon right">
                                    <i class="fa"></i>
                                    <input type="text" disabled name="bigClass" id="bigClass" class="form-control"
                                           placeholder=""/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group col-md-6">
                            <label class="control-label col-md-4">
                            ${uom ! "度量单位"}
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon right">
                                    <i class="fa"></i>
                                    <input type="text" disabled name="uom" id="uom" class="form-control"
                                           placeholder=""/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group col-md-6">
                            <label class="control-label col-md-4">
                            ${zlName ! "所属中类"}
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon right">
                                    <i class="fa"></i>
                                    <input type="text" disabled name="midClass" id="midClass" class="form-control"
                                           placeholder=""/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group col-md-6">
                            <label class="control-label col-md-4">
                            ${samplePrice ! "样品价格"}
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon right">
                                    <i class="fa"></i>
                                    <input type="text" name="vendorPrice" disabled id="vendorPrice" class="form-control"
                                           placeholder=""/>
                                </div>
                            </div>
                        </div>

                        <div class="form-group col-md-6">
                            <label class="control-label col-md-4">
                            ${xlName ! "所属小类"}
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon right">
                                    <i class="fa"></i>
                                    <input type="text" disabled name="smallClass" id="smallClass" class="form-control"
                                           placeholder=""/>
                                </div>
                            </div>
                        </div>

                        <div class="form-group col-md-6">
                            <label class="control-label col-md-4">
                            ${productPrice ! "大货价格"}
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon right">
                                    <i class="fa"></i>
                                    <input type="text" disabled name="bigProdectPrice" id="bigProdectPrice"
                                           class="form-control"
                                           placeholder=""/>
                                </div>
                            </div>
                        </div>

                        <div class="form-group col-md-6">
                            <label class="control-label col-md-4">
                            ${erpStatus ! "ERP状态"}
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon right">
                                    <i class="fa"></i>
                                    <input type="text" disabled name="erpStatus" id="erpStatus" class="form-control"
                                           placeholder=""/>
                                </div>
                            </div>
                        </div>

                        <div class="form-group col-md-6">
                            <label class="control-label col-md-4">
                            ${useStatus ! "可用状态"}
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon right">
                                    <i class="fa"></i>
                                    <input disabled type="text" name="enable" id="enable" class="form-control"
                                           placeholder=""/>
                                </div>
                            </div>
                        </div>

                        <div class="form-group col-md-12">
                            <label class="control-label col-md-2">
                            ${materialDesc ! "物料描述"}
                            </label>
                            <div class="col-md-8">
                                <div class="input-icon right">
                                    <i class="fa"></i>
                                    <textarea id="materialDesc" rows="4" disabled name="materialDesc"
                                              class="col-md-12 col-sm-12"></textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="portlet light portlet-fit portlet-datatable col-md-12">
        <div class="portlet-title">
            <div class="caption">
                <i class="fa fa-table font-green"></i>
                <span class="caption-subject font-green bold uppercase">${attrDetail ! "属性明细"}</span>
            </div>
        </div>

        <div class="portlet-body form ">
            <div action="#" class="form-horizontal form_completejob">
                <div class="form-body" id="attributeArea">
                    <div class="divider"></div>
                </div>
            </div>
            <div class="col-md-12">
                <div class="form-group col-md-6">
                    <div class="control-label col-md-4 text-right">
                        <button type="button" class="btn blue" id="save">${save !"保存"}</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>