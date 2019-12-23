<!-- BEGIN PAGE BAR -->
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li><a href="${base}">首页</a><i class="fa fa-angle-right"></i></li>
        <li><a href="javascript:;">产品管理</a><i class="fa fa-angle-right"></i></li>
        <li><span>图片上传</span></li>
    </ul>
</div>

<div class="portlet light portlet-fit">
    <div class="portlet-title">
        <div class="caption">
            <i class="fa fa-table font-green"></i>
            <span class="caption-subject font-green bold uppercase">图片上传</span>
        </div>
    </div>
    <div class="portlet-body">
        <form action="${base}/image/uploadimage" multiple="multiple" enctype="multipart/form-data" class="dropzone"
              name="uploadfile" id="dropzoneForm">
            <div class="fallback"></div>
            <div class="input-icon right">
                <i class="fa"></i>
                <select id="imageType" name="imageType"
                        class="form-control input-sm">
                    <option value="">-- 请选择 --</option>
                    <option value="1">产品图片</option>
                    <option value="2">模特图片</option>
                    <option value="3">推广图片</option>
                </select>
            </div>
            <div class="input-icon right margin-top-10">
                <i class="fa"></i>
                <select id="codeRule" name="codeRule"
                        class="form-control input-sm">
                    <option value="">-- 请选择 --</option>
                    <option value="1">老编码规则</option>
                    <option value="2">20新编码</option>
                </select>
            </div>
        </form>
    </div>

    <div class="row text-center">
        <button type="button" class="btn btn-primary btn-md red" id="removeAll">
            <span class="glyphicon glyphicon-trash"></span> 移除所有
        </button>
        <button data-loading-text="上传中，请勿刷新页面..." autocomplete="off" class="btn btn-primary"
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

