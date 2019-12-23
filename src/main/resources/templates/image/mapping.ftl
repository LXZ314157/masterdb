<!-- BEGIN PAGE BAR -->
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li><a href="${base}">首页</a><i class="fa fa-angle-right"></i></li>
        <li><a href="javascript:;">产品管理</a><i class="fa fa-angle-right"></i></li>
        <li><span>产品模特对应关系列表</span></li>
    </ul>
</div>

<div class="portlet light portlet-fit">
    <div class="portlet-body">
        <div class="portlet light bordered">
            <div class="portlet-title">
                <div class="caption">
                    <i class="icon-bar-chart font-dark"></i>
                    <span class="caption-subject font-dark bold uppercase">产品模特对应关系列表</span>
                </div>
            </div>
            <div class="portlet-body">
                <div class="table-container">
                    <table class="table table-striped table-bordered table-hover table-checkable"
                           id="mappingTable">
                        <thead>
                        <tr role="row" class="heading">
                            <th>编号</th>
                            <th>模特编码</th>
                            <th>产品编码</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <div class="portlet-foot"></div>
</div>
</div>

<#--批量导入的模态框-->
<div id="leadArea" class="modal fade" data-backdrop='static' tabindex="-1" data-width="30%">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
        <h4 class="modal-title" id="fabricTile">批量导入模特产品对应关系</h4>
    </div>
    <div class="modal-body form-horizontal">
        <div class="form-body  text-center">
            <form class="form-group" id="excelForm" method="put" style="margin:15px"
                  enctype="multipart/form-data">
                <label class="control-label col-md-3 col-sm-3">选择文件：</label>
                <div class="col-md-3 col-sm-3">
                    <div class="fileinput fileinput-new" data-provides="fileinput">
                        <div class="input-group">
                            <div class="form-control uneditable-input input-fixed input-medium"
                                 data-trigger="fileinput">
                                <i class="fa fa-file fileinput-exists"></i>&nbsp;
                                <span class="fileinput-filename"> </span>
                            </div>
                            <span class="input-group-addon btn default btn-file">
                                                                    <span class="fileinput-new"> 选择 </span>
                                                                    <span class="fileinput-exists"> 更改 </span>
                                                                    <input type="file" name="uploadfile" id="uploadfile"
                                                                           accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"> </span>
                            <a href="javascript:;" class="input-group-addon btn red fileinput-exists"
                               data-dismiss="fileinput"> 移除 </a>
                        </div>
                    </div>
                </div>
            </form>
            <a class="button text-center" href="${base}/static/content/excel/mapping.xlsx">下载模板</a>
        </div>
    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button type="button" class="btn btn-outline green" id="btnLeadCommit">确认</button>
    </div>
</div>


<#--浮动框高级查询条件-->

<div id="supperSearchArea" class="modal fade" tabindex="-1" data-width="30%">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
        <h4 class="modal-title" id="fabricTile">高级查找</h4>
    </div>
    <div class="modal-body form-horizontal">
        <div class="form-body  text-center">
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="control-label col-md-3">
                        线:
                    </label>
                    <div class="col-md-8">
                        <select id="select_line" class="form-control input-sm">
                            <option value="">全部</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="control-label col-md-3">
                        年份:
                    </label>
                    <div class="col-md-8">
                        <select id="year" class="form-control input-sm">
                            <option value="">全部</option>
                            <option value="11">2011</option>
                            <option value="12">2012</option>
                            <option value="13">2013</option>
                            <option value="14">2014</option>
                            <option value="15">2015</option>
                            <option value="16">2016</option>
                            <option value="17">2017</option>
                            <option value="18">2018</option>
                            <option value="19">2019</option>
                            <option value="20">2020</option>
                            <option value="21">2021</option>
                            <option value="22">2022</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="control-label col-md-3">
                        季节:
                    </label>
                    <div class="col-md-8">
                        <select id="select_dev_season" class="form-control input-sm">
                            <option value="">全部</option>
                        </select>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
            <button type="button" class="btn btn-outline green" id="btnSearch">确认</button>
        </div>
    </div>




<#--更新对应关系的模态框-->
    <div id="editMapping" class="modal fade" tabindex="-1" data-width="30%">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
            <h4 class="modal-title" id="fabricTile">更改对应关系</h4>
        </div>
        <div class="modal-body form-horizontal">
            <div class="form-body  text-center">
                <input type="hidden" value="" id="mappingId">
                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="control-label col-md-3">
                            产品编码:
                        </label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="productCode">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="control-label col-md-3">
                            模特编码:
                        </label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="modelCode">
                        </div>
                    </div>
                </div>
                <small style="color: red" hidden id="info">提示:</small>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
                <button type="button" class="btn btn-outline green" onclick="commitUpdate();" id="btnUpdate">
                    确认
                </button>
            </div>
        </div>

