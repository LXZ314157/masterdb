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
                        <label class="control-label col-md-4 "
                               style="text-align: right;line-height: 33px;">批量条码:</label>
                        <div class="col-md-8" about="">
                            <div class="input-icon right">
                                <i class="fa"></i>
                                <input type="text" name="batchSku" id="batchSku" class="form-control" placeholder=""/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row col-md-3">
                    <div class="form-group ">
                        <label class="control-label col-md-4" style="text-align: right;line-height: 33px;">产品编码/条码:</label>
                        <div class="col-md-8" about="">
                            <div class="input-icon right">
                                <i class="fa"></i>
                                <input type="text" name="productCodeOrSku" id="productCodeOrSku" class="form-control"/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row col-md-3">
                    <div class="form-group ">
                        <label class="control-label col-md-4 "
                               style="text-align: right;line-height: 33px;">起始更新时间:</label>
                        <div class="col-md-8" about="">
                            <div class="input-icon right">
                                <i class="fa"></i>
                                <input type="text" name="startTime" style="background-color: transparent" id="startTime" readonly="readonly" class="form-control form-control-inline startTime"/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row col-md-3">
                    <div class="form-group ">
                        <label class="control-label col-md-4 "
                               style="text-align: right;line-height: 33px;">结束更新时间:</label>
                        <div class="col-md-8" about="">
                            <div class="input-icon right">
                                <i class="fa"></i>
                                <input type="text" name="endTime" id="endTime" readonly="readonly" style="background-color: transparent" class="form-control form-control-inline endTime"/>
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
                    <span class="caption-subject font-green bold uppercase">条码信息列表</span>
                </div>
            </div>
            <div class="portlet-body" style="padding-bottom: 5px;">
                <div class="table-container">
                    <table class="table table-striped table-bordered table-hover table-checkable" id="skulist">
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
                            <th>产品编码</th>
                            <th>条码</th>
                            <th>颜色</th>
                            <th>批次</th>
                            <th>尺码</th>
                            <th>标准单价</th>
                            <th>标准成本</th>
                            <th>销售成本</th>
                            <th>安全码</th>
                            <th>状态</th>
                            <th>更新时间</th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <div id="syncSkuListDiv" class="modal fade" style="width:600px;height:300px">
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
                            <option selected value="burgeon-rfid-wms">同步到伯俊-RFID-WMS</option>
                            <#--先注释掉，等正式环境发布OA插件以后再放开-->
                            <#--<option selected value="oa">同步到OA</option>-->
                        </select>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <button type="submit" class="btn btn-success" data-tid="" onclick="syncSkuList()">同步</button>
            <a data-dismiss="modal" class="btn btn-inverse" href="#">取消</a>
        </div>
    </div>

</div>


