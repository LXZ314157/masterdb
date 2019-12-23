<!-- BEGIN PAGE BAR -->
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li><a href="${base}">首页</a><i class="fa fa-angle-right"></i></li>
        <li><a href="javascript:;">产品信息管理</a><i class="fa fa-angle-right"></i></li>
        <li><span>基础选项列表</span></li>
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

                <div class="row col-md-5">
                    <div class="form-group ">
                        <label class="control-label col-md-4" style="text-align: right;line-height: 33px;">选项类别:</label>
                        <div class="col-md-8" about="">
                            <div class="input-icon right">
                                <i class="fa"></i>
                                <input type="text" name="itemCategory" id="itemCategory" class="form-control"/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row col-md-5">
                    <div class="form-group ">
                        <label class="control-label col-md-4" style="text-align: right;line-height: 33px;">选项值:</label>
                        <div class="col-md-8" about="">
                            <div class="input-icon right">
                                <i class="fa"></i>
                                <input type="text" name="itemSelect" id="itemSelect" class="form-control"/>
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
                    <span class="caption-subject font-green bold uppercase">基础选项列表</span>
                </div>
            </div>
            <div class="portlet-body" style="padding-bottom: 5px;">
                <div class="table-container">
                    <table class="table table-striped table-bordered table-hover table-checkable" id="colorCardList">
                        <thead>
                            <tr role="row" class="heading">
                                <th>
                                    <label class="mt-checkbox">
                                        <input id="chooseAll" name='checkbox' type="checkbox"
                                               onclick="selectAllCheckBox(this)"/>
                                        <span></span>
                                    </label>
                                </th>
                                <th>操作</th>
                                <th>选项类别</th>
                                <th>选项键</th>
                                <th>选项值</th>
                                <th>状态</th>
                            </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="editItem" class="modal fade" data-backdrop='static' tabindex="-1" data-width="800px">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
        <h4 class="modal-title" id="colorCardTile">编辑选项</h4>
    </div>
    <div class="modal-body form-horizontal">
        <input id="fabricId" type="hidden">
        <div class="form-body">
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="control-label col-md-3">
                        <span class="required">* </span>选项类别
                    </label>
                    <div class="col-md-8">
                        <div class="input-icon right">
                            <i class="fa"></i>
                            <select id="tableName" name="tableName" class="col-md-12 input-sm select2">
                                <option value="">--请选择-</option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="control-label col-md-3">
                        <span class="required">* </span> 选项键

                    </label>
                    <div class="col-md-8">
                        <div class="input-icon right">
                            <i class="fa"></i>
                            <input type="text" class="form-control" name="itemKey" id="itemKey"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="control-label col-md-3">
                        <span class="required">* </span> 选项值
                    </label>
                    <div class="col-md-8">
                        <div class="input-icon right">
                            <i class="fa"></i>
                            <input type="text" class="form-control" name="itemValue" id="itemValue"
                                   placeholder=""/>
                        </div>
                    </div>
                </div>
            </div>
            <input class="hidden" value="" id="id"/>
        </div>
    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button type="button" class="btn btn-outline green" id="saveItem" val="">确认</button>
    </div>

    <form id="frmMoedlDownload" name="frmMoedlDownload"  style="display:none;">
        <input type="hidden" id="itemCategorys" name="itemCategorys" value=""/>
        <input type="hidden" id="itemSelects" name="itemSelects" value=""/>
    </form>

    <div id="syncItemListDiv" class="modal fade" style="width:600px;height:268px">
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
            <button type="submit" class="btn btn-success" id="syncProductList" data-tid="" onclick="syncItemList()">同步</button>
            <a data-dismiss="modal" class="btn btn-inverse" href="#">取消</a>
        </div>
    </div>
    <input type="hidden" id="insertFlag" value=""/>
</div>