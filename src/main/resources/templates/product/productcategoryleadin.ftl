productcategoryleadin<!-- BEGIN PAGE BAR -->
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li><a href="${base}">首页</a><i class="fa fa-angle-right"></i></li>
        <li><a href="javascript:;">产品信息管理</a><i class="fa fa-angle-right"></i></li>
        <li><a href="javascript:;">产品信息导入</a></li>
    </ul>
</div>
<div class="portlet light portlet-fit portlet-datatable">



    <div class="portlet-title">
        <div class="caption">
            <i class="fa fa-table font-green"></i>
            <span class="caption-subject font-green bold uppercase">产品类别信息导入</span>
        </div>
    </div>

    <div class="portlet  portlet-body form col-md-12" id="stepfirst">
        <form action="#" class="form-horizontal form_completejob form_complete_date">
            <div class="form-body">
                <div class="row col-md-9">

                    <div class="form-group col-md-5">
                        <label class="control-label col-md-4">
                            产品类别:
                        </label>
                        <div class="col-md-7" about="">
                            <div class="input-icon right">
                                <i class="fa"></i>
                                <select name="productCategory" id="productCategory" class="form-control" onclick="changeModel()">
                                    <option value="0">---请选择---</option>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-1">
                        <a  onclick="loadmodel(this)" class="input-group-addon btn blue fileinput-exists" data-dismiss="fileinput" id="loadmodel"> 下载模板 </a>
                    </div>


                </div>
            </div>
        </form>
    </div>

    <div class="portlet-body col-md-12" style="padding-bottom: 5px;">
        <form class="form-horizontal form-bordered col-md-4" method="post" enctype="multipart/form-data" id="upLoadFileForm" style="width: 520px">
            <div class="form-group ">
                <label class="control-label col-md-3">产品类别文件:</label>
                <div class="col-md-4" style="margin-left: -40px">
                    <div class="fileinput fileinput-new col-md-5" data-provides="fileinput">
                        <div class="input-group input-large">
                            <div class="form-control uneditable-input input-fixed input-medium"
                                 data-trigger="fileinput">
                                <i class="fa fa-file fileinput-exists"></i>&nbsp;
                                <span class="fileinput-filename"></span>
                            </div>
                            <div class="input-group-addon btn default btn-file ">
                                <span class="fileinput-new"> 选择文件 </span>
                                <span class="fileinput-exists"> 更改</span>
                                <input type="" value="" name="..."  id="styleinsertfile">
                                <input type="file" name="uploadcategoryfile" id="uploadcategoryfile" accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet">
                            </div>
                            <a href="javascript:;" class="input-group-addon btn red fileinput-exists"
                               data-dismiss="fileinput"> 移除 </a>
                        </div>
                    </div>
                </div>
            </div>
        </form>

        <div class="col-md-2">
            <input type="button" name="styleInsert" class="btn green" value="提交" id="styleinsert">
        </div>
        <form id="frmDownload" name="frmDownload"  style="display:none;">
            <input type="hidden" id="excelpath" name="excelpath" value=""/>
        </form>
        <form id="frmMoedlDownload" name="frmMoedlDownload"  style="display:none;">
            <input type="hidden" id="productCategoryId" name="productCategoryId" value=""/>
            <input type="hidden" id="tableName" name="tableName" value=""/>
        </form>

        <form id="productInfoFrm" name="productInfoFrm"  style="display:none;">
        </form>
    </div>



    <div class="portlet-title" style="margin-top: 200px">
        <div class="caption">
            <i class="fa fa-table font-green"></i>
            <span class="caption-subject font-green bold uppercase">产品条码信息导入</span>
        </div>
    </div>


    <div class="portlet-body col-md-12" style="margin-top: 20px">
        <div class="col-md-1">
            <a onclick="downloadProductInfoModel()" class="input-group-addon btn blue fileinput-exists" data-dismiss="fileinput"> 下载模板 </a>
        </div>

        <form class="form-horizontal form-bordered col-md-4" method="post" enctype="multipart/form-data"
              id="upLoadFileForm1" style="width: 520px">
            <div class="form-group ">
                <label class="control-label col-md-3">产品条码:</label>
                <div class="col-md-4" style="margin-left: -40px">
                    <div class="fileinput fileinput-new col-md-5" data-provides="fileinput">
                        <div class="input-group input-large">
                            <div class="form-control uneditable-input input-fixed input-medium"
                                 data-trigger="fileinput">
                                <i class="fa fa-file fileinput-exists"></i>&nbsp;
                                <span class="fileinput-filename"></span>
                            </div>
                            <div class="input-group-addon btn default btn-file ">
                                <span class="fileinput-new"> 选择文件 </span>
                                <span class="fileinput-exists"> 更改</span>
                                <input type="" value="" name="..."
                                       id="styleinsertfile">
                                <input type="file" name="uploadfile" id="uploadfile"
                                       accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet">
                            </div>
                            <a href="javascript:;" class="input-group-addon btn red fileinput-exists"
                               data-dismiss="fileinput"> 移除 </a>
                        </div>
                    </div>
                </div>
            </div>
        </form>
        <div class="col-md-2">
            <input type="button" name="styleInsert" class="btn green" value="提交" onclick="productinfoSubmit()">
        </div>
    </div>
</div>

