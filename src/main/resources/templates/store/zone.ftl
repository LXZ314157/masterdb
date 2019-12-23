<!-- BEGIN PAGE BAR -->
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li><a href="${base}">首页</a><i class="fa fa-angle-right"></i></li>
        <li><a href="javascript:;">店铺管理</a><i class="fa fa-angle-right"></i></li>
        <li><span>业务分区列表</span></li>
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
                            <span class="caption-subject font-dark bold uppercase">业务分区列表</span>
                        </div>
                        <div class="actions">
                            <a href="javascript:;" class="btn blue btnAddBU">
                                <i class="fa fa-plus"></i> 新增现场管理中心 </a>
                        </div>
                    </div>
                    <div class="portlet-body">
                        <div id="jstree_demo_div"></div>
                    </div>
                </div>
            </div>
            <div class=" col-md-6">
                <div class="portlet light bordered" id="mng_block" style="display: none;">
                    <div class="portlet-title">
                        <div class="caption">
                            <i class="fa fa-table font-green"></i>
                            <span class="caption-subject font-green bold uppercase" id="mng_title">新增现场管理中心</span>
                        </div>
                    </div>
                    <div class="portlet-body form-horizontal">
                        <div class="row">
                            <input type="hidden" id="mngId" name="mngId" value="0"/>
                            <input type="hidden" id="managerCenterId" name="managerCenterId"/>
                            <div class="form-group col-md-12">
                                <label class="control-label col-md-3">
                                    管理中心名称:
                                    <span class="required">* </span>
                                </label>
                                <div class="col-md-8">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" class="form-control" name="manageCenterName" id="manageCenterName" placeholder="管理中心名称"/>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group col-md-12">

                                <label class="control-label col-md-3">
                                    负责人:
                                </label>
                                <div class="col-md-8">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <select id="manager" name="manager" class="form-control select2-multiple">
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group col-md-12" id="mngStatusArea" style="display: none;">
                                <label class="control-label col-md-3">
                                    管理中心状态:
                                    <span class="required">* </span>
                                </label>
                                <div class="col-md-8">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <div class="btn-group btn-toggle-status" data-toggle="buttons" id="areaBuStatus">
                                            <label class="btn default" data-val="0">
                                                <input type="radio" name="manageCenterState" class="toggle" value="0"> 无效 </label>
                                            <label class="btn default" data-val="1">
                                                <input type="radio" name="manageCenterState"  class="toggle" value="1"> 有效 </label>
                                            <input type="hidden" id="hidBuState" name="hidBuState" value="0"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="portlet-footer">
                        <div class="col-md-4 col-md-offset-4">
                            <button type="button" class="btn blue btn-block" id="btnSaveMng">保存</button>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </div>



                <div class="portlet light bordered" id="zone_block" style="display: none;">
                    <div class="portlet-title">
                        <div class="caption">
                            <i class="fa fa-table font-green"></i>
                            <span class="caption-subject font-green bold uppercase" id="zone_title">新增区域</span>
                        </div>
                    </div>
                    <div class="portlet-body form-horizontal">
                        <div class="row">
                            <input type="hidden" id="zoneId" name="zoneId" value="0"/>
                            <div class="form-group col-md-12">
                                <label class="control-label col-md-3">
                                    现场管理中心:
                                </label>
                                <div class="col-md-8">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <select id="zone_mng_id" name="zone_mng_id" class="form-control mng_info">
                                            <option value="0">--选择现场管理中心--</option>
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group col-md-12">
                                <label class="control-label col-md-3">
                                    负责人:
                                </label>
                                <div class="col-md-8">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <select id="manager1" name="manager1" class="form-control select2-multiple">
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group col-md-12" id="zoneTypeArea">
                                <label class="control-label col-md-3">
                                    分区性质:
                                </label>
                                <div class="col-md-8">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <div class="btn-group btn-toggle-status" data-toggle="buttons" id="zoneper">
                                            <label class="btn default" data-val="1">
                                                <input type="radio" class="toggle" value="1"> 零售分区 </label>
                                            <label class="btn default" data-val="2">
                                                <input type="radio" class="toggle" value="2"> 业务分区 </label>
                                            <input type="hidden" id="hidZoneper" name="hidZoneper" value="0"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group col-md-12">
                                <label class="control-label col-md-3">
                                    上级分区:
                                </label>
                                <div class="col-md-8">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <select id="paZone_id" name="paZone_id" class="form-control pazone_info">
                                            <option value="0">--选择上级分区--</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group col-md-12">
                                <label class="control-label col-md-3">
                                    分区名:
                                    <span class="required">* </span>
                                </label>
                                <div class="col-md-8">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" class="form-control" name="zoneName" id="zoneName"
                                               placeholder="分区名"/>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group col-md-12">
                                <label class="control-label col-md-3">
                                    分区描述:
                                </label>
                                <div class="col-md-8">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <textarea name="zoneDesc" id="zoneDesc" rows="3" class="form-control"
                                                  placeholder="分区描述"></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="portlet-footer">
                        <div class="col-md-4 col-md-offset-4">
                            <button type="button" class="btn blue btn-block" id="btnSaveZone">保存</button>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>