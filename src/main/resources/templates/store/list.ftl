<!-- BEGIN PAGE BAR -->
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li><a href="${base}">首页</a><i class="fa fa-angle-right"></i></li>
        <li><a href="javascript:;">店铺管理</a><i class="fa fa-angle-right"></i></li>
        <li><span>店铺列表</span></li>
    </ul>
</div>

<div class="portlet light portlet-fit">

    <div class="row" style="margin-top: 25px;">
        <div class=" col-md-12">
            <div class="portlet light bordered">
                <div class="portlet-title">
                    <div class="caption">
                        <i class="icon-bar-chart font-dark"></i>
                        <span class="caption-subject font-dark bold uppercase">店铺列表</span>
                    </div>
                </div>
                <div class="portlet-body">
                    <div class="table-container">
                        <table class="table table-striped table-bordered table-hover table-checkable" id="datatable">
                            <thead>
                            <tr role="row" class="heading">
                                <th style="min-width: 100px">店铺编号</th>
                                <th>店铺名</th>
                                <th>代理商</th>
                                <th>所属分区</th>
                                <th>联系人</th>
                                <th>联系电话</th>
                                <th>店铺级别</th>
                                <th>类型</th>
                                <th>状态</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody></tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<!--高级搜索的浮动层-->
<div id="conditionArea" class="modal fade" tabindex="-1" data-width="500">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
        <h4 class="modal-title" id="storeTypeTile">高级搜索</h4>
    </div>
    <div class="modal-body form-horizontal">
        <div class="form-body">
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="control-label col-md-3">
                        关键字 </label>
                    <div class="col-md-8">
                        <div class="input-icon right">
                            <i class="fa"></i>
                            <input type="text" class="form-control" name="key"
                                   id="key"
                                   placeholder="关键字"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="control-label col-md-3">
                        所属客户:
                    </label>
                    <div class="col-md-8">
                        <select id="selectedBuyer" class="form-control input-sm select2-multiple">
                            <option value="">请选择</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="control-label col-md-3">
                        店铺级别:
                    </label>
                    <div class="col-md-8">
                        <select id="selectedStoreLevel" class="form-control input-sm select2-multiple">
                            <option value="">请选择</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="control-label col-md-3">
                        店铺类型:
                    </label>
                    <div class="col-md-8">
                        <select id="selectedStoreType" class="form-control input-sm select2-multiple">
                            <option value="">请选择</option>
                        </select>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button type="button" class="btn btn-outline green" id="btnCheck">搜索</button>
    </div>

    <form id="frmMoedlDownload" name="frmMoedlDownload"  style="display:none;">
        <input type="hidden" id="sSearch" name="sSearch" value=""/>
        <input type="hidden" id="buyerId" name="buyerId" value=""/>
        <input type="hidden" id="storeLevelId" name="storeLevelId" value=""/>
        <input type="hidden" id="storeTypeId" name="storeTypeId" value=""/>
        <input type="hidden" id="anywords" name="anywords" value=""/>
    </form>

</div>


