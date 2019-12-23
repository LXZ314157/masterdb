<!-- BEGIN PAGE BAR -->
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li><a href="${base}">首页</a><i class="fa fa-angle-right"></i></li>
        <li><a href="javascript:;">店铺信息</a><i class="fa fa-angle-right"></i></li>
        <li><span>店铺级别&类型</span></li>
    </ul>
</div>

<div class="portlet light portlet-fit">

    <div class="portlet-body">
        <div class="row">
            <div class=" col-md-6">
                <div class="portlet light bordered">
                    <div class="portlet-title">
                        <div class="caption">
                            <i class="icon-bar-chart font-dark"></i>
                            <span class="caption-subject font-dark bold uppercase">店铺级别</span>
                        </div>
                        <div class="actions">
                            <a href="javascript:;" class="btn blue" id="addAttributeGroup"
                               onclick="addOrEditLevel(null)">
                                <i class="fa fa-plus"></i> 新增店铺级别 </a>
                        </div>
                    </div>
                    <div class="portlet-body">
                        <div class="table-scrollable">
                            <table class="table table-striped table-bordered table-hover dataTable" id="storeLevel">
                                <thead>
                                <tr>
                                    <th class="detail text-center">店铺级别
                                    </th>
                                    <th class="detail text-center">描述
                                    </th>
                                    <th class="detail text-center">状态
                                    </th>
                                    <th class="detail text-center">操作
                                    </th>
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
                            <span class="caption-subject font-dark bold uppercase">店铺类型</span>
                        </div>
                        <div class="actions">
                            <a href="javascript:;" class="btn blue btnAddArea" id="addStoreType"
                               onclick="addOrEditStoreType()">
                                <i class="fa fa-plus"></i> 新增店铺类型</a>
                        </div>
                    </div>
                    <div class="portlet-body">
                        <div class="table-scrollable">
                            <table class="table table-striped table-bordered table-hover dataTable" id="storeType">
                                <thead>
                                <tr>
                                    <th class="detail text-center">店铺类型
                                    </th>
                                    <th class="detail text-center">状态
                                    </th>
                                    <th class="detail text-center">操作
                                    </th>
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


<!--添加或编辑店铺级别-->
<div id="addOrEditLevel" class="modal fade" tabindex="-1" data-width="500">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
        <h4 class="modal-title" id="citylevel_title">新增店铺级别</h4>
    </div>
    <div class="modal-body form-horizontal">
        <div class="form-body">
            <div class="row">
                <input type="hidden" id="cityLevelId" name="cityLevelId" value="0"/>
                <div class="form-group col-md-12">
                    <label class="control-label col-md-3">
                        店铺级别名:
                        <span class="required">* </span>
                    </label>
                    <div class="col-md-8">
                        <div class="input-icon right">
                            <i class="fa"></i>
                            <input type="text" class="form-control" name="storeLevelName" id="storeLevelName"
                                   placeholder="店铺级别名"/>
                        </div>
                    </div>
                </div>
                <div class="form-group col-md-12">
                    <label class="control-label col-md-3">
                        级别描述:
                    </label>
                    <div class="col-md-8">
                        <div class="input-icon right">
                            <i class="fa"></i>
                            <textarea name="storeLevelDesc" id="storeLevelDesc" rows="3" class="form-control"
                                      placeholder="级别描述"></textarea>
                        </div>
                    </div>
                </div>
                <div class="form-group col-md-12" id="storelevelStatusArea">
                    <label class="control-label col-md-3">
                        状态:
                    </label>
                    <div class="col-md-8">
                        <div class="input-icon right">
                            <i class="fa"></i>
                            <div class="btn-group" data-toggle="buttons" id="storeLevelStatus">
                                <label class="btn default">
                                    <input type="radio" class="toggle" value="0"> 失效 </label>
                                <label class="btn default">
                                    <input type="radio" class="toggle active"  value="1"> 有效 </label>
                                <input type="hidden" id="hideStoreStatus" value="0"/>
                            </div>
                            <input type="hidden" id="storeLevelId" value="0"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button type="button" class="btn btn-outline green" id="saveStoreLevel">确认</button>
    </div>
</div>


<!--编辑或添加店铺类型-->
<div id="addOrEditStoreType" class="modal fade" tabindex="-1" data-width="500">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
        <h4 class="modal-title" id="storeTypeTile">新增店铺类型</h4>
    </div>
    <div class="modal-body form-horizontal">
        <div class="form-body">
            <div class="row">
                <input type="hidden" id="cityLevelId" name="cityLevelId" value="0"/>
                <div class="form-group col-md-12">
                    <label class="control-label col-md-3">
                        店铺类型名:
                        <span class="required">* </span>
                    </label>
                    <div class="col-md-8">
                        <div class="input-icon right">
                            <i class="fa"></i>
                            <input type="text" class="form-control" name="storeTypeName" id="storeTypeName"
                                   placeholder="店铺类型名"/>
                        </div>
                    </div>
                </div>

                <div class="form-group col-md-12" id="storeTypeArea" style="display: none;">
                    <label class="control-label col-md-3">
                        状态:
                    </label>
                    <div class="col-md-8">
                        <div class="input-icon right">
                            <i class="fa"></i>
                            <div class="btn-group" data-toggle="buttons" id="storeTypeStatus">
                                <label class="btn default">
                                    <input type="radio" class="toggle" value="0"> 失效 </label>
                                <label class="btn default">
                                    <input type="radio" class="toggle active" value="1"> 有效 </label>
                                <input type="hidden" id="hideLevelType" value="0"/>
                            </div>
                            <input type="hidden" id="storeTypeId" value="0"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button type="button" class="btn btn-outline green" id="saveStoreType">确认</button>
    </div>
</div>
