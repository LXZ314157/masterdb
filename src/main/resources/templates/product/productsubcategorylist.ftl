<!-- BEGIN PAGE BAR -->
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li><a href="${base}">首页</a><i class="fa fa-angle-right"></i></li>
        <li><a href="javascript:;">产品信息管理</a><i class="fa fa-angle-right"></i></li>
        <li><span>款式信息列表</span></li>
    </ul>
</div>

<!--查询条件-->
<div class="row portlet light bordered" style=" margin: 10px;margin-bottom: 30px;height: 150px;margin-top: 10px;">
    <div class="col-md-12">
        <div class="portlet light portlet-fit ">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-table font-green"></i>
                    <span class="caption-subject font-green bold uppercase">查询条件</span>
                </div>
            </div>
            <div class="portlet-body" style="padding-bottom: 5px;">

                <div class="row col-md-3">
                    <div class="form-group ">
                        <label class="control-label col-md-4" style="text-align: right;line-height: 33px;">子类名称/编码:</label>
                        <div class="col-md-8" about="">
                            <div class="input-icon right">
                                <i class="fa"></i>
                                <input type="text" name="productSubCategoryCodeOrName" id="productSubCategoryCodeOrName" class="form-control"/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row col-md-3">
                    <div class="form-group  ">
                        <label class="control-label col-md-4" style="text-align: right;line-height: 33px;">产品类别:</label>
                        <div class="col-md-8" about="">
                            <div class="input-icon right">
                                <i class="fa"></i>
                                <select name="categoryCode" id="categoryCode" class="form-control">
                                    <option value="">---请选择---</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="row col-md-3">
                    <div class="form-group  ">
                        <label class="control-label col-md-4" style="text-align: right;line-height: 33px;">子类级别:</label>
                        <div class="col-md-8" about="">
                            <div class="input-icon right">
                                <i class="fa"></i>
                                <select name="subCategoryLevel" id="subCategoryLevel" class="form-control">
                                    <option value="">---请选择---</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row col-md-3">
                    <div class="form-group  ">
                        <label class="control-label col-md-4" style="text-align: right;line-height: 33px;">语言:</label>
                        <div class="col-md-8" about="">
                            <div class="input-icon right">
                                <i class="fa"></i>
                                <select name="lanCode" id="lanCode" class="form-control">
                                    <option value="">---请选择---</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>


            </div>
        </div>
    </div>
</div>

<!--信息列表-->
<div class="row portlet light bordered" style="margin:5px">
    <div class="col-md-12">
        <div class="portlet light portlet-fit portlet-datatable">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-table font-green"></i>
                    <span class="caption-subject font-green bold uppercase">产品子类列表</span>
                </div>
            </div>
            <div class="portlet-body" style="padding-bottom: 5px;">
                <div class="table-container">
                    <table class="table table-striped table-bordered table-hover table-checkable" id="productsubcategorylist">
                        <thead>
                        <tr>
                            <th>
                                <label class="mt-checkbox">
                                    <input id="chooseAll" name='checkbox' type="checkbox"
                                           onclick="selectAllCheckBox(this)"/>
                                    <span></span>
                                </label>
                            </th>
                            <th>操作</th>
                            <th>子类编号</th>
                            <th>子类名称</th>
                            <th>产品类别</th>
                            <th>子类级别</th>
                            <th>上级名称</th>
                            <th>产品税率</th>
                            <th>物料键</th>
                            <th>状态</th>
                            <th>语言</th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <div id="syncSubCategoryListDiv" class="modal fade" style="width:600px;height:300px">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
            <h4 class="modal-title">批量同步</h4>
        </div>
        <div class="modal-body">
            <div class="form-horizontal">
                <div class="form-group">
                    <label class="control-label col-md-4" style="line-height: 30px;margin-left: 138px;font-size: 15px;font-weight: bold">您一共选择了<span id="total"></span>项</label>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-5" style="line-height: 30px;margin-left: -82px;">同步到:</label>
                    <div class="controls col-md-7">
                        <select id="synSetting" name="" class="form-control select2-multiple" style="height: 64px;" multiple>
                            <option selected value="burgeon">同步到伯俊</option>
                        </select>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <button type="submit" class="btn btn-success" data-tid="" onclick="syncProductSubCategoryList()">同步</button>
            <a data-dismiss="modal" class="btn btn-inverse" href="#">取消</a>
        </div>
    </div>


    <div id="editItem" class="modal fade" data-backdrop='static' tabindex="-1" data-width="850px">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
            <h4 class="modal-title" id="colorCardTile">编辑选项</h4>
        </div>
        <div class="modal-body form-horizontal">
            <input id="fabricId" type="hidden">
            <div class="form-body">
                <div class="row">
                    <div class="form-group col-md-6">
                        <label class="control-label col-md-3">
                            <span class="required">* </span>子类编号
                        </label>
                        <div class="col-md-8">
                            <div class="input-icon right">
                                <i class="fa"></i>
                                <input type="text" class="form-control" name="productSubCategoryCodes" id="productSubCategoryCodes" maxlength="30"/>
                            </div>
                        </div>
                    </div>

                    <div class="form-group col-md-6">
                        <label class="control-label col-md-3">
                            <span class="required">* </span>子类名称
                        </label>
                        <div class="col-md-8">
                            <div class="input-icon right">
                                <i class="fa"></i>
                                <input type="text" class="form-control" name="productSubCategoryNames" id="productSubCategoryNames" maxlength="50"/>
                            </div>
                        </div>
                    </div>

                </div>

                <div class="row">
                    <div class="form-group col-md-6">
                        <label class="control-label col-md-3">
                            <span class="required">* </span>产品类别
                        </label>
                        <div class="col-md-8">
                            <div class="input-icon right">
                                <select id="categoryCodes" name="categoryCodes" class="input-sm select2">
                                    <option value="">--请选择-</option>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="form-group col-md-6">
                        <label class="control-label col-md-3">
                            <span class="required">* </span>子类级别
                        </label>
                        <div class="col-md-8">
                            <div class="input-icon right">
                                <select id="subCategoryLevels" name="subCategoryLevels" class="input-sm select2">
                                    <option value="">--请选择-</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-6">
                        <label class="control-label col-md-3">
                            上级编号
                        </label>
                        <div class="col-md-8">
                            <div class="input-icon right">
                                <i class="fa"></i>
                                <input type="text" class="form-control" name="paSubCategoryCodes" id="paSubCategoryCodes" maxlength="30"/>
                            </div>
                        </div>
                    </div>

                    <div class="form-group col-md-6">
                        <label class="control-label col-md-3">
                            <span class="required">* </span>产品税率
                        </label>
                        <div class="col-md-8">
                            <div class="input-icon right">
                                <i class="fa"></i>
                                <input type="text" class="form-control" name="saleTaxRates" onkeyup="clearNoNum(this)" id="saleTaxRates" maxlength="10"/>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="row">
                    <div class="form-group col-md-6">
                        <label class="control-label col-md-3">
                            物料键
                        </label>
                        <div class="col-md-8">
                            <div class="input-icon right">
                                <i class="fa"></i>
                                <input type="text" class="form-control" name="subCategoryKeys" id="subCategoryKeys" maxlength="10"/>
                            </div>
                        </div>
                    </div>

                    <div class="form-group col-md-6">
                        <label class="control-label col-md-3">
                            <span class="required">* </span>语言
                        </label>
                        <div class="col-md-8">
                            <div class="input-icon right">
                                <select id="lanCodes" name="lanCodes" class="col-md-8 input-sm select2">
                                    <option value="">--请选择-</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>

                <input class="hidden" value="" id="id"/>
            </div>
        </div>
        <div class="modal-footer">
            <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
            <button type="button" class="btn btn-outline green" onclick="saveProductSubCategory()"val="">确认</button>
        </div>
        <input type="hidden" id="insertFlag" value=""/>
    </div>

    <input type="hidden" id="productSubCategoryId">
</div>


