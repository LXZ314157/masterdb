<!-- BEGIN PAGE BAR -->
<style type="text/css">
    div.dt-button-collection > a.dt-button {
        background: #c1e0e8;
    }

    div.dt-button-collection > a.dt-button:hover {
        background: #c1e0e8;
    }
</style>
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li><a href="${base}">首页</a><i class="fa fa-angle-right"></i></li>
        <li><a href="javascript:;">产品信息管理</a><i class="fa fa-angle-right"></i></li>
        <li><span>款式信息维护</span></li>
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
                               style="text-align: right;line-height: 33px;">批量编码:</label>
                        <div class="col-md-8" about="">
                            <div class="input-icon right">
                                <i class="fa"></i>
                                <input type="text" name="batchProductCode" id="batchProductCode" class="form-control" placeholder=""/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row col-md-3">
                    <div class="form-group ">
                        <label class="control-label col-md-4 "
                               style="text-align: right;line-height: 33px;">产品编码/名称:</label>
                        <div class="col-md-8" about="">
                            <div class="input-icon right">
                                <i class="fa"></i>
                                <input type="text" name="productCodeOrName" id="productCodeOrName" class="form-control" placeholder=""/>
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
                                <input type="text" name="startTime" style="background-color: transparent" id="startTime" readonly="readonly" class="form-control form-control-inline startTime" placeholder=""/>
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
                                <input type="text" name="endTime" id="endTime" readonly="readonly" style="background-color: transparent" class="form-control form-control-inline endTime" placeholder=""/>
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
                    <span class="caption-subject font-green bold uppercase">款式信息维护</span>
                </div>
            </div>
            <div class="portlet-body" style="padding-bottom: 5px;">
                <div class="table-container">
                    <table class="table table-striped table-bordered table-hover table-checkable" id="productList">
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
                            <th>产品名称</th>
                            <th>价格</th>
                            <th>单位</th>
                            <th>商品大类</th>
                            <th>商品中类</th>
                            <th>商品小类</th>
                            <th>面料编号</th>
                            <th>面料名称</th>
                            <th>商品描述</th>
                            <th>品牌</th>
                            <th>状态</th>
                            <th>销售税率</th>
                            <th>采购税率</th>
                            <th>更新时间</th>
                            <th>创建时间</th>
                            <th>数据状态</th>
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


<div id="leadIn" class="modal fade" data-backdrop='static' tabindex="-1" data-width="700px">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
        <h4 class="modal-title" id="colorCardTile">批量添加</h4>
    </div>
    <div class="modal-body form-horizontal">
        <div class="form-body  text-center">
            <form class="form-group text-center" id="upLoadFileForm" method="post" style="margin:15px"
                  enctype="multipart/form-data">
                <label class="control-label col-md-3 col-sm-3">尺码清单：</label>
                <div class="col-md-3 col-sm-3">
                    <div class="fileinput fileinput-new" data-provides="fileinput">
                        <div class="input-group input-large">
                            <div class="form-control uneditable-input input-fixed input-medium"
                                 data-trigger="fileinput">
                                <i class="fa fa-file fileinput-exists"></i>&nbsp;
                                <span class="fileinput-filename"> </span>
                            </div>
                            <span class="input-group-addon btn default btn-file">
                                                                    <span class="fileinput-new"> 选择 </span>
                                                                    <span class="fileinput-exists"> 更改 </span>
                                                                    <input type="file" data-provides="fileinput"
                                                                           name="uploadfile" id="uploadfile"
                                                                           accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"> </span>
                            <a href="javascript:;" class="input-group-addon btn red fileinput-exists"
                               data-dismiss="fileinput"> 移除 </a>
                        </div>
                    </div>
                </div>
            </form>
            <a class="button text-center" href="${base}/static/content/excel/size.xlsx">下载模板</a>
        </div>
    </div>
    <form id="frmDownload" name="frmDownload" style="display:none;">
        <input type="hidden" id="excelpath" name="excelpath" value=""/>
    </form>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark cancel">取消</button>
        <button type="button" class="btn btn-outline green" id="sizeinsert" val="">确认</button>
    </div>
</div>

<div id="export" class="modal fade" data-backdrop='static' tabindex="-1" data-width="900px">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
        <h4 class="modal-title" id="colorCardTile">可选导出</h4>
    </div>
    <div class="modal-body ">
        <div class="form-body  text-center">
            <div class="form-group col-md-6">
                <label class="control-label col-md-3">数据状态</label>
                <div class="col-md-9">
                    <select id="syncstatus" name="syncstatus" class="form-control" tabindex="-1"
                            aria-hidden="true">
                        <option value="-1">默认</option>
                        <option value="2">新增</option>
                        <option value="0">已更新</option>
                        <option value="1">有更新</option>
                    </select>
                </div>
            </div>
            <div class="form-group col-md-6">
                <label class="control-label col-md-3">同步状态</label>
                <div class="col-md-9">
                    <select id="syncrecord" name="syncrecord" class="form-control">
                        <option value="-1">默认</option>
                        <option value="1">同步</option>
                        <option value="0">未同步</option>
                    </select>
                </div>
            </div>
            <div class="form-group col-md-6">
                <label class="control-label col-md-3">线路</label>
                <div class="col-md-9">
                    <select id="line" name="line" class="form-control">
                    </select>
                </div>
            </div>
            <div class="form-group col-md-6">
                <label class="control-label col-md-3">年份</label>
                <div class="col-md-9">
                    <select id="year" name="year" class="form-control">
                    </select>
                </div>
            </div>
            <div class="form-group col-md-6">
                <label class="control-label col-md-3">品种</label>
                <div class="col-md-9">
                    <select id="productclass" name="productclass" class="form-control">
                    </select>
                </div>
            </div>
            <div class="form-group col-md-6">
                <label class="control-label col-md-3">季节</label>
                <div class="col-md-9">
                    <select id="natureseason" name="natureseason" class="form-control">
                    </select>
                </div>
            </div>
            <div class="form-group col-md-6">
                <label class="control-label col-md-3">开发季</label>
                <div class="col-md-9">
                    <select id="devseason" name="devseason" class="form-control">
                    </select>
                </div>
            </div>
            <div class="form-group col-md-6">
                <label class="control-label col-md-3">波</label>
                <div class="col-md-9">
                    <select id="wave" name="wave" class="form-control">
                    </select>
                </div>
            </div>
            <div class="form-group col-md-6">
                <label class="control-label col-md-3">段</label>
                <div class="col-md-9">
                    <select id="band" name="band" class="form-control">
                    </select>
                </div>
            </div>
            <div class="form-group col-md-6">
                <label class="control-label col-md-3">工作组</label>
                <div class="col-md-9">
                    <select id="workgroup" name="workgroup" class="form-control">
                    </select>
                </div>
            </div>
        </div>

        <form id="exportForm" name="exportForm" style="display:none;">
            <input type="hidden" id="columns" name="columns" value=""/>
            <input type="hidden" id="condition" name="condition" value=""/>
        </form>
        <div class="modal-footer" style="clear: both">
            <button type="button" data-dismiss="modal" class="btn btn-outline dark cancel">取消</button>
            <button type="button" class="btn btn-outline green" id="dataexport" val="">确认</button>
        </div>


        <div id="syncProductListDiv" class="modal fade" style="width:600px;height:300px">
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
                                <option selected value="ERP、Cache">同步到ERP、Cache</option>
                                <option selected value="burgeon">同步到伯俊</option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="submit" class="btn btn-success" id="syncProductList" data-tid="" onclick="syncProductList()">同步</button>
                <a data-dismiss="modal" class="btn btn-inverse" href="#">取消</a>
            </div>
        </div>

    </div>
