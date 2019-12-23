<!-- BEGIN PAGE BAR -->
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li><a href="${base}">${home ! "首页"}</a><i class="fa fa-angle-right"></i></li>
        <li><a href="javascript:;">${module ! "主数据维护"}</a><i class="fa fa-angle-right"></i></li>
        <li><span>币种&单位管理</span></li>
    </ul>
</div>

<div class="portlet light portlet-fit" id="currencyTest">
    <div class="portlet-body">
        <div class="row">
            <div class=" col-md-6">
                <div class="portlet light bordered">
                    <div class="portlet-title">
                        <div class="caption">
                            <i class="icon-bar-chart font-dark"></i>
                            <span class="caption-subject font-dark bold uppercase">${curencyList!"币种列表"}</span>
                        </div>
                        <div class="actions">
                            <a href="javascript:;" class="btn blue" id="btnAddCurrency">
                                <i class="fa fa-plus"></i>新增币种</a>
                        </div>
                    </div>
                    <div class="portlet-body">
                        <div class="table-scrollable" style="overflow-x:hidden">
                            <table class="table table-striped table-bordered table-hover dataTable" id="currency">
                                <thead>
                                <tr>
                                    <th>编号</th>
                                    <th>${curencyCode!"币种编码"}</th>
                                    <th>${curencyName!"币种名称"}</th>
                                    <th>${currencyDesc!"币种说明"}</th>
                                    <th>${currencyPrecision!"精确度"}</th>
                                    <th>${status!"状态"}</th>
                                    <th>${exec!"操作"}</th>
                                </tr>
                                </thead>
                                <tbody id="tbCityLevel">
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class=" col-md-6">
                <div class="portlet light bordered">
                    <div class="portlet-title">
                        <div class="caption">
                            <i class="icon-bar-chart font-dark"></i>
                            <span class="caption-subject font-dark bold uppercase">单位列表</span>
                        </div>
                        <div class="actions">
                            <a href="javascript:;" class="btn blue" id="btnAddMeasure">
                                <i class="fa fa-plus"></i> ${addUom!"新增单位"}</a>
                        </div>
                    </div>
                    <div class="portlet-body">
                        <div class="table-scrollable">
                            <table class="table table-striped table-bordered table-hover dataTable" id="measure">
                                <thead>
                                <tr>
                                    <th>编号</th>
                                    <th>${uomCode!"单位编码"}</th>
                                    <th>${uomMeasure!"度量单位"}</th>
                                    <th>${uomClass!"度量类别"}</th>
                                    <th>${uomDesc!"单位说明"}</th>
                                    <th>${status!"状态"}</th>
                                    <th>${exec!"操作"}</th>
                                </tr>
                                </thead>
                                <tbody id="tbArea">
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<div id="addArea" class="modal fade" tabindex="-1" data-width="500" data-height="340">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
        <h4 class="modal-title">新增币种</h4>
    </div>

    <div class="modal-body">
        <div class="form-group col-md-12">
            <label class="control-label col-md-3 text-right">
                <span class="required">* </span>${currencyCode!"币种编码"}:

            </label>
            <div class="col-md-8">
                <div class="input-icon right">
                    <i class="fa"></i>
                    <input type="hidden" id="id">
                    <input type="text" class="form-control" placeholder="请输入币种编码" id="currencyCode" maxlength="5"/>
                </div>
            </div>
        </div>
        <div class="form-group col-md-12">
            <label class="control-label col-md-3 text-right">
                <span class="required">* </span>${currencyName!"币种名称"}:

            </label>
            <div class="col-md-8">
                <div class="input-icon right">
                    <i class="fa"></i>
                    <input type="text" class="form-control" placeholder="请输入币种名称" id="currencyName" maxlength="30"/>
                    <input type="hidden"/>
                </div>
            </div>
        </div>

        <div class="form-group col-md-12">
            <label class="control-label col-md-3 text-right">
                <span class="required">* </span>${currencyPrecision!"精确度"}:

            </label>
            <div class="col-md-8">
                <div class="input-icon right">
                    <i class="fa"></i>
                    <input type="text" class="form-control" placeholder="请输入精确度" id="currencyPrecision" maxlength="5" />
                    <input type="hidden"/>
                </div>
            </div>

        </div>

        <div class="form-group col-md-12">
            <label class="control-label col-md-3 text-right">
            ${currencyDesc!"币种描述"}:
            </label>
            <div class="col-md-8">
                <textarea class="form-control" rows="3" name="currencyDesc" id="currencyDesc" maxlength="100"></textarea>
            </div>
        </div>

        <div class="form-group col-md-12" id="statusArea">
            <label class="control-label col-md-3 text-right">
                <span class="required">* </span> ${status!"状态"}:
            </label>
            <div class="col-md-8">
                <div class="input-icon right">
                    <i class="fa"></i>
                    <div class="btn-group" data-toggle="buttons" id="status">
                        <label class="btn default active" id="status_1">
                            <input type="radio" class="toggle" value="1">${valid!"有效"}  </label>
                        <label class="btn default" id="status_0">
                            <input type="radio" class="toggle" value="0"> ${unvalid!"无效"} </label>
                        <input type="hidden" id="hideStoreStatus" value="0"/>
                    </div>
                    <input type="hidden" id="storeLevelId" value="0"/>
                </div>
            </div>
        </div>
    </div>
    <div class="modal-footer">
        <button type="button" class="btn btn-success" onclick="saveOrEdit()" id="save" value="1">${save!"保存"}</button>
        <a data-dismiss="modal" class="btn btn-inverse" href="#">${cancel!"取消"}</a>
    </div>
</div>
<#--新增单位-->
<div id="addUom" class="modal fade" tabindex="-1" data-width="560" data-height="320">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
        <h4 class="modal-title">编辑单位</h4>
    </div>

    <div class="modal-body">
        <div class="form-group col-md-12">
            <label class="control-label col-md-3 text-right">
                <span class="required">* </span>${uomCode!"单位编码"}:

            </label>
            <div class="col-md-8">
                <div class="input-icon right">
                    <i class="fa"></i>
                    <input type="text" class="form-control" placeholder="" id="uomCode" maxlength="25"/>
                    <input type="hidden"/>
                </div>
            </div>
        </div>
        <div class="form-group col-md-12">
            <label class="control-label col-md-3 text-right">
                <span class="required">* </span>${uomMeasure!"度量单位"}:
            </label>
            <div class="col-md-8">
                <div class="input-icon right">
                    <i class="fa"></i>
                    <input type="text" class="form-control" placeholder="" id="uomName" maxlength="25"/>
                    <input type="hidden"/>
                </div>
            </div>
        </div>
        <div class="form-group col-md-12">
            <label class="control-label col-md-3 text-right">
                ${uomClass!"度量类别"}:
            </label>
            <div class="col-md-8">
                <div class="input-icon right">
                    <i class="fa"></i>
                    <input type="text" class="form-control" placeholder="" id="measurement" maxlength="10"/>
                    <input type="hidden"/>
                </div>
            </div>
        </div>
        <div class="form-group col-md-12">
            <label class="control-label col-md-3 text-right">
                单位说明:
            </label>
            <div class="col-md-8">
                <div class="input-icon right">
                    <i class="fa"></i>
                    <textarea class="form-control" rows="3" name="uomDesc" id="uomDesc" maxlength="50"></textarea>
                    <input type="hidden"/>
                </div>
            </div>
        </div>
        <div class="form-group col-md-12" id="measureStatus">
            <label class="control-label col-md-3 text-right">
                <span class="required">* </span>${status!"状态"}:
            </label>
            <div class="col-md-8">
                <div class="input-icon right">
                    <i class="fa"></i>
                    <div class="btn-group" data-toggle="buttons" id="uomStatus">
                        <label class="btn default " id="hasStatus">
                            <input type="radio" class="toggle" value="1">${valid!"有效"}  </label>
                        <label class="btn default" id="noStatus">
                            <input type="radio" class="toggle" value="0"> ${unvalid!"无效"} </label>
                    </div>
                    <input type="hidden" id="uomId" value="0"/>
                </div>
            </div>
        </div>
    </div>
    <div class="modal-footer">
        <button type="button" class="btn btn-success" onclick="uomSave()" id="uomSave" value="1">${save!"保存"}</button>
        <a data-dismiss="modal" class="btn btn-inverse" href="#">${cancel!"取消"}</a>
    </div>
</div>
