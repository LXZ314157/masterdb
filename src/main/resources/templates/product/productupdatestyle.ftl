<!-- BEGIN PAGE BAR -->
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li><a href="${base}">首页</a><i class="fa fa-angle-right"></i></li>
        <li><a href="javascript:;">产品信息管理</a><i class="fa fa-angle-right"></i></li>
        <li><a href="javascript:;">款式信息更新</a></li>
    </ul>
</div>
<div class="portlet light portlet-fit portlet-datatable">
    <div class="portlet-title">
        <div class="caption">
            <i class="fa fa-table font-green"></i>
            <span class="caption-subject font-green bold uppercase">款式信息更新</span>
        </div>
    </div>
    <div class="portlet-body col-md-12" style="padding-bottom: 5px;">
        <div class="col-md-1">
            <#--<a href="${base}/static/content/excel/sku.xlsx" class="input-group-addon btn blue fileinput-exists" data-dismiss="fileinput"> 下载模板 </a>-->
            <a onclick="downloadModel()" class="input-group-addon btn blue fileinput-exists" data-dismiss="fileinput"> 下载模板 </a>
        </div>
        <form action="#" class="form-horizontal form-bordered col-md-4" method="post" enctype="multipart/form-data"
              style="width: 520px"
              id="upLoadFileForm">
            <div class="form-group">
                <label class="control-label col-md-3">款式清单:</label>
                <div class="col-md-4" style="margin-left: -40px">
                    <div class="fileinput fileinput-new col-md-5" data-provides="fileinput">
                        <div class="input-group input-large">
                            <div class="form-control uneditable-input input-fixed input-medium"
                                 data-trigger="fileinput">
                                <i class="fa fa-file fileinput-exists"></i>&nbsp;
                                <span class="fileinput-filename"></span>
                            </div>
                            <span class="input-group-addon btn default btn-file">
                                                                    <span class="fileinput-new"> 选择文件 </span>
                                                                    <span class="fileinput-exists"> 更改</span>
                                                                    <input type="hidden" value="" name="..."><input
                                    type="file" name="uploadfile" id="uploadfile"
                                    accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"> </span>

                            <a href="javascript:;" class="input-group-addon btn red fileinput-exists"
                               data-dismiss="fileinput"> 移除 </a>
                        </div>
                    </div>
                </div>
            </div>
        </form>
        <div class="col-md-2">
            <input type="button" name="styleInsert" class="btn green" value="提交" id="updatestyle">
        </div>
        <form id="frmDownload" name="frmDownload" style="display:none;">
            <input type="hidden" id="excelpath" name="excelpath" value=""/>
        </form>
    </div>
</div>
 <form id="frmMoedlDownload" name="frmMoedlDownload"  style="display:none;">
     <input type="hidden" id="productCategoryId" name="productCategoryId" value=""/>
     <input type="hidden" id="tableName" name="tableName" value=""/>
 </form>
</div>
