<!-- BEGIN PAGE BAR -->
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li><a href="${base}">${home ! "首页"}</a><i class="fa fa-angle-right"></i></li>
        <li><a href="javascript:;">${module ! "主数据维护"}</a><i class="fa fa-angle-right"></i></li>
        <li><span>${title ! "面料列表"}</span></li>
    </ul>
</div>

<div class="portlet light portlet-fit">
    <div class="portlet-body">
        <div class="portlet light bordered">
            <div class="portlet-title">
                <div class="caption">
                            <span class="caption-subject font-dark bold uppercase">
                                  <i class="icon-bar-chart font-dark"></i>
                                <span class="caption-subject font-dark bold uppercase">${title ! "面料列表"}</span>
                            </span>
                </div>
            </div>
            <div class="portlet-body">
                <div class="table-container">
                    <table class="table table-striped table-bordered table-hover table-checkable"
                           id="fabricTable">
                        <thead>
                        <tr role="row" class="heading">
                            <th class="text-center" style="min-width: 120px">${fabricCode ! "面料编码"}</th>
                            <th class="text-center">${fabricName ! "面料名称"}</th>
                            <th class="text-center" style="width: 50px">${fabricStatus ! "是否编辑面料特征"}</th>
                            <th class="text-center">${createdBy ! "创建人"}</th>
                            <th class="text-center">${createdDate ! "创建时间"}</th>
                            <th class="text-center">${lastUpdatedBy ! "上次更新人"}</th>
                            <th class="text-center">${lastUpdatedDate ! "上次更新时间"}</th>
                            <th class="text-center">${operate ! "操作"}</th>
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


<!--编辑或添加面料-->
<div id="fabricArea" class="modal fade" data-backdrop='static' tabindex="-1" data-width="50%">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
        <h4 class="modal-title" id="fabricTitle">${addFabric ! "新增面料"}</h4>
    </div>
    <div class="modal-body form-horizontal">
        <input id="fabricId" type="hidden">
        <div class="form-body">
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="control-label col-md-2">
                    ${fabricCode ! "面料编码"}:
                        <span class="required">* </span>
                    </label>
                    <div class="col-md-5">
                        <div class="input-icon right">
                            <i class="fa"></i>
                            <input type="text" class="form-control" name="fabricCode" id="fabricCode" maxlength="20"
                                   placeholder="${fabricCode ! "面料编码"}"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="control-label col-md-2">
                    ${fabricName ! "面料名称"}:
                        <span class="required">* </span>
                    </label>
                    <div class="col-md-5">
                        <div class="input-icon right">
                            <i class="fa"></i>

                            <input type="text" class="form-control" name="fabricName" id="fabricName" maxlength="50"
                                   placeholder="${fabricName ! "面料名称"}"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <input type="hidden" id="fabricName" name="fabricName" value="0"/>
            </div>
            <div class="row">
                <input type="hidden" id="fabricName" name="fabricName" value="0"/>
                <div class="form-group col-md-12">
                    <label class="control-label col-md-2">
                    ${fabricFeature ! "面料特点"}:
                    </label>
                    <div class="col-md-9">
                        <div class="input-icon right">
                            <i class="fa"></i>
                            <div id="ueditor_input" type="text/plain" style="width:100%;height:300px;"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">${cancle ! "取消"}</button>
        <button type="button" class="btn btn-outline green" id="saveFabric">${sure ! "确定"}</button>
    </div>
</div>

<!--导入面料-->
<div id="leadFabricArea" class="modal fade" data-backdrop='static' tabindex="-1" data-width="30%">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
        <h4 class="modal-title" id="fabricTile">${leadFabric ! "批量导入面料"}</h4>
    </div>
    <div class="modal-body form-horizontal">
        <div class="form-body  text-center">
            <form class="form-group text-center" id="form" method="post" style="margin:15px"
                  enctype="multipart/form-data">
                <label class="control-label col-md-3 col-sm-3">${fabricList ! "面料清单"}：</label>
                <div class="col-md-3 col-sm-3">
                    <div class="fileinput fileinput-new" data-provides="fileinput">
                        <div class="input-group input-large">
                            <div class="form-control uneditable-input input-fixed input-medium"
                                 data-trigger="fileinput">
                                <i class="fa fa-file fileinput-exists"></i>&nbsp;
                                <span class="fileinput-filename"> </span>
                            </div>
                            <span class="input-group-addon btn default btn-file">
                                                                    <span class="fileinput-new"> ${select ! "选择"} </span>
                                                                    <span class="fileinput-exists"> ${change ! "更改"} </span>
                                                                    <input type="file" data-provides="fileinput"
                                                                           name="uploadfile" id="uploadfile"
                                                                           accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"> </span>
                            <a href="javascript:;" class="input-group-addon btn red fileinput-exists"
                               data-dismiss="fileinput">  ${remove ! "移除"} </a>
                        </div>
                    </div>
                </div>
            </form>
            <a class="button text-center"
               href="${base}/static/content/excel/fabric.xlsx"> ${downLoadTemplate ! "下载模板"}</a>
        </div>
    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">${cancle ! "取消"}</button>
        <button type="button" class="btn btn-outline green" id="btnLeadCommit">${sure ! "确认"}</button>
    </div>

    <form id="frmMoedlDownload" name="frmMoedlDownload"  style="display:none;">
        <input type="hidden" id="fabiricNameOrNo" name="fabiricNameOrNo" value=""/>
    </form>

</div>
