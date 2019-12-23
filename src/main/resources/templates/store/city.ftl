<!-- BEGIN PAGE BAR -->
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li><a href="${base}">首页</a><i class="fa fa-angle-right"></i></li>
        <li><a href="javascript:;">店铺管理</a><i class="fa fa-angle-right"></i></li>
        <li><span>城市维护</span></li>
    </ul>
</div>
<!-- END PAGE BAR -->
<!-- BEGIN PAGE AREA-->
<div class="portlet light portlet-fit">
    <div class="portlet-body">
        <div class="row">
            <div class=" col-md-6">
                <div class="portlet light bordered">
                    <div class="portlet-title">
                        <div class="caption">
                            <i class="icon-bar-chart font-dark"></i>
                            <span class="caption-subject font-dark bold uppercase">城市级别列表</span>
                        </div>
                        <div class="actions">
                            <a href="javascript:;" class="btn blue btnAddCityLevel">
                                <i class="fa fa-plus"></i> 新增城市级别 </a>
                        </div>
                    </div>
                    <div class="portlet-body">
                        <div class="table-scrollable">
                            <table class="table table-striped table-bordered table-hover dataTable">
                                <thead>
                                <tr>
                                    <th>城市级别</th>
                                    <th>描述</th>
                                    <th>状态</th>
                                    <th>操作</th>
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
                            <span class="caption-subject font-dark bold uppercase">地理区域列表</span>
                        </div>
                        <div class="actions">
                            <a href="javascript:;" class="btn blue btnAddArea">
                                <i class="fa fa-plus"></i> 新增地理区域 </a>
                        </div>
                    </div>
                    <div class="portlet-body">
                        <div class="table-scrollable">
                            <table class="table table-striped table-bordered table-hover dataTable">
                                <thead>
                                <tr>
                                    <th>区域编码</th>
                                    <th>区域名</th>
                                    <th>状态</th>
                                    <th>操作</th>
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
        <div class="row" style="margin-top: 25px;">
            <div class=" col-md-12">
                <div class="portlet light bordered">
                    <div class="portlet-title">
                        <div class="caption">
                            <i class="icon-bar-chart font-dark"></i>
                            <span class="caption-subject font-dark bold uppercase">城市列表</span>
                        </div>
                    </div>
                    <div class="portlet-body">
                        <div class="table-container">
                            <table class="table table-striped table-bordered table-hover table-checkable" id="datatable">
                                <thead>
                                    <tr role="row" class="heading">
                                        <th>城市ID</th>
                                        <th>城市名称</th>
                                        <th>城市编码</th>
                                        <th>省份</th>
                                        <th>城市级别</th>
                                        <th>所属地区</th>
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
</div>

<!-- 新增、编辑城市级别浮动层 -->
<div id="citylevel" class="modal fade" tabindex="-1" data-width="500">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
        <h4 class="modal-title" id="citylevel_title">新增城市级别</h4>
    </div>
    <div class="modal-body form-horizontal">
        <div class="form-body">
            <div class="row">
                <input type="hidden" id="cityLevelId" name="cityLevelId" value="0"/>
                <div class="form-group col-md-12">
                    <label class="control-label col-md-3">
                        城市级别名:
                        <span class="required">* </span>
                    </label>
                    <div class="col-md-8">
                        <div class="input-icon right">
                            <i class="fa"></i>
                            <input type="text" class="form-control" name="cityLevelName" id="cityLevelName"
                                   placeholder="城市级别名"/>
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
                            <textarea name="cityLevelDesc" id="cityLevelDesc" rows="3" class="form-control"
                                      placeholder="级别描述"></textarea>
                        </div>
                    </div>
                </div>
                <div class="form-group col-md-12" id="citylevelStatusArea" style="display: none;">
                    <label class="control-label col-md-3">
                        状态:
                    </label>
                    <div class="col-md-8">
                        <div class="input-icon right">
                            <i class="fa"></i>
                            <div class="btn-group btn-toggle-status" data-toggle="buttons" id="cityLevelStatus">
                                <label class="btn default" data-val="0">
                                    <input type="radio" class="toggle cityLevelStatus" value="0"> 失效 </label>
                                <label class="btn default" data-val="1">
                                    <input type="radio" class="toggle cityLevelStatus" value="1"> 有效 </label>
                                <input type="hidden" id="hidCityLevelState" name="hidCityLevelState" value="0"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button type="button" class="btn btn-outline green" id="btnSaveCityLevel">确认</button>
    </div>
</div>

<!-- 新增、编辑地理区域浮动层 -->
<div id="area" class="modal fade" tabindex="-1" data-width="500">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
        <h4 class="modal-title" id="area_title">新增地理区域</h4>
    </div>
    <div class="modal-body form-horizontal">
        <div class="form-body">
            <div class="row">
                <input type="hidden" id="areaId" name="areaId" value="0"/>
                <div class="form-group col-md-12">
                    <label class="control-label col-md-3">
                        区域编码:
                        <span class="required">* </span>
                    </label>
                    <div class="col-md-8">
                        <div class="input-icon right">
                            <i class="fa"></i>
                            <input type="text" class="form-control" name="areaCode" id="areaCode"
                                   placeholder="区域编码"/>
                        </div>
                    </div>
                </div>
                <div class="form-group col-md-12">
                    <label class="control-label col-md-3">
                        区域名:
                    </label>
                    <div class="col-md-8">
                        <div class="input-icon right">
                            <i class="fa"></i>
                            <textarea name="areaName" id="areaName" rows="3" class="form-control"
                                      placeholder="区域名"></textarea>
                        </div>
                    </div>
                </div>
                <div class="form-group col-md-12" id="areaStatusArea" style="display: none;">
                    <label class="control-label col-md-3">
                        状态:
                    </label>
                    <div class="col-md-8">
                        <div class="input-icon right">
                            <i class="fa"></i>
                            <div class="btn-group btn-toggle-status" data-toggle="buttons" id="areaStatus">
                                <label class="btn default" data-val="0">
                                    <input type="radio" class="toggle" value="0"> 失效 </label>
                                <label class="btn default" data-val="1">
                                    <input type="radio" class="toggle" value="1"> 有效 </label>
                                <input type="hidden" id="hidAreaState" name="hidAreaState" value="0"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button type="button" class="btn btn-outline green" id="btnSaveArea">确认</button>
    </div>
</div>

<!-- 城市搜索浮动层 -->
<div id="city_search" class="modal fade" tabindex="-1" data-width="500">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
        <h4 class="modal-title">筛选城市</h4>
    </div>
    <div class="modal-body form-horizontal">
        <div class="form-body">
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="control-label col-md-3">
                        城市名称:
                    </label>
                    <div class="col-md-8">
                        <div class="input-icon right">
                            <i class="fa"></i>
                            <input type="text" class="form-control" name="cityName" id="cityName"
                                   placeholder="城市名称(模糊查询)"/>
                        </div>
                    </div>
                </div>
                <div class="form-group col-md-12">
                    <label class="control-label col-md-3">
                        城市级别:
                    </label>
                    <div class="col-md-8">
                        <div class="input-icon right">
                            <i class="fa"></i>
                            <select id="city_level_id" name="city_level_id" class="form-control city_level">
                                <option value="0">--选择城市级别--</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="form-group col-md-12">
                    <label class="control-label col-md-3">
                        地市:
                    </label>
                    <div class="col-md-8" about="">
                        <div class="input-icon right">
                            <i class="fa"></i>
                            <select class="form-control input-sm select2" id="area_city" name="area_city" >
                                <optgroup id="area_city_id">
                                    <option value="">-- 请选择 --</option>
                                </optgroup>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="form-group col-md-12">
                    <label class="control-label col-md-3">
                        区县:
                    </label>
                    <div class="col-md-8" about="">
                        <div class="input-icon right">
                            <i class="fa"></i>
                            <select class="form-control input-sm select2" id="county" name="county" >
                                <optgroup id="county_id">
                                    <option value="">-- 请选择 --</option>
                                </optgroup>
                            </select>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button type="button" class="btn btn-outline green" id="btnSearchCity">查询</button>
    </div>
</div>

<!-- 城市编辑浮动层 -->
<div id="city_edit" class="modal fade" tabindex="-1" data-width="500">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
        <h4 class="modal-title">编辑城市</h4>
    </div>
    <div class="modal-body form-horizontal">
        <div class="form-body">
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="control-label col-md-3">
                        城市编号:
                    </label>
                    <div class="col-md-8">
                        <div class="input-icon right">
                            <i class="fa"></i>
                            <input type="text" class="form-control" name="city_id" id="city_id" readonly="readonly"
                                   placeholder="城市编号"/>
                        </div>
                    </div>
                </div>

                <div class="form-group col-md-12">
                    <label class="control-label col-md-3">
                        城市名称:
                    </label>
                    <div class="col-md-8">
                        <div class="input-icon right">
                            <i class="fa"></i>
                            <input type="text" class="form-control" name="city_name" id="city_name" readonly="readonly"
                                   placeholder="城市名称"/>
                        </div>
                    </div>
                </div>
                <div class="form-group col-md-12">
                    <label class="control-label col-md-3">
                        上级城市:
                    </label>
                    <div class="col-md-8">
                        <div class="input-icon right">
                            <i class="fa"></i>
                            <input type="text" class="form-control" name="pa_city_name" id="pa_city_name" readonly="readonly"/>
                        </div>
                    </div>
                </div>
                <div class="form-group col-md-12">
                    <label class="control-label col-md-3">
                        所属地区:
                    </label>
                    <div class="col-md-8">
                        <div class="input-icon right">
                            <i class="fa"></i>
                            <select id="area_id" name="area_id" class="form-control area_info">
                                <option value="0">--选择地区--</option>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="form-group col-md-12">
                    <label class="control-label col-md-3">
                        所属省份:
                    </label>
                    <div class="col-md-8">
                        <div class="input-icon right">
                            <i class="fa"></i>
                            <select id="province_id" name="province_id" class="form-control province_info">
                                <option value="0">--选择省份--</option>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="form-group col-md-12">
                    <label class="control-label col-md-3">
                        城市编码:
                    </label>
                    <div class="col-md-8">
                        <div class="input-icon right">
                            <i class="fa"></i>
                            <input type="text" class="form-control" name="city_code" id="city_code"
                                   placeholder="城市编码"/>
                        </div>
                    </div>
                </div>
                <div class="form-group col-md-12">
                    <label class="control-label col-md-3">
                        城市级别:
                    </label>
                    <div class="col-md-8">
                        <div class="input-icon right">
                            <i class="fa"></i>
                            <select id="level_id" name="level_id" class="form-control city_level">
                                <option value="0">--选择城市级别--</option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button type="button" class="btn btn-outline green" id="btnEditCity">确定</button>
    </div>
</div>