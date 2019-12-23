<div class="page-bar">
    <ul class="page-breadcrumb">
        <li><a href="${base}">首页</a><i class="fa fa-angle-right"></i></li>
        <li><a href="javascript:;">产品管理</a><i class="fa fa-angle-right"></i></li>
        <li><span>推广图片处理</span></li>
    </ul>
</div>

<div class="portlet light portlet-fit">
    <div class="portlet-title">
        <div class="caption">
            <i class="fa fa-table font-green"></i>
            <span class="caption-subject font-green bold uppercase">图片处理</span>
        </div>
    </div>
    <div class="portlet-body">
        <div>
            <div class="col-md-1">
                <a href="${base}/static/content/excel/sku.xlsx" class="input-group-addon btn blue fileinput-exists"
                   data-dismiss="fileinput"> 下载模板 </a>
            </div>
        <form class="form-horizontal form-bordered col-md-4" method="post" enctype="multipart/form-data"
        id="upLoadFileForm" style="width: 520px">
            <div class="form-group ">
                <label class="control-label col-md-3">推广图片清单:</label>
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
                                       accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"/>
                            </div>
                            <a href="javascript:;" class="input-group-addon btn red fileinput-exists"
                               data-dismiss="fileinput"> 移除 </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </form>
        </div>
        <div class="clearfix"></div>
        <form action="" multiple="multiple" enctype="multipart/form-data" class="dropzone"
              name="imgfile" id="dropzoneForm">

            <div class="fallback"></div>

                <input type="hidden" name="upload" id="upload"
                      value="1"/>
        </form>
    </div>

    <div class="row text-center">
        <button type="button" class="btn btn-primary btn-md red" id="removeAll">
            <span class="glyphicon glyphicon-trash"></span> 移除所有
        </button>
        <button data-loading-text="上传中，请勿刷新页面..." autocomplete="off" class="btn btn-primary green"
                id="btnUpload">
            <span class="glyphicon glyphicon-upload"></span>
            上传
        </button>
    </div>
</div>
</div>

<div class="clearfix"></div>
<div class="modal fade" id="progressBar" tabindex="-1" data-backdrop='static' data-keyboard='false'
     data-width="350" data-height="100">
    <div class="modal-body" style="margin: auto; text-align: center;">
        <div class="row">
            <div class="span4">
                <div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="60"
                     aria-valuemin="0" aria-valuemax="100" style="width: 80%;height: 20px;margin: 8px" id="proDiv">
                    <span class="light" id="proSpan">40% </span>
                </div>
            </div>
        </div>
    </div>
</div>