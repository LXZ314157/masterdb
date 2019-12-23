<!-- BEGIN PAGE BAR -->
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li><a href="${base}">首页</a><i class="fa fa-angle-right"></i></li>
        <li><a href="javascript:;">产品管理</a><i class="fa fa-angle-right"></i></li>
        <li><span>图片展示</span></li>
    </ul>
</div>

<div class="portlet light portlet-fit">
    <div class="portlet-title">
        <div class="caption">
            <i class="fa fa-table font-green"></i>
            <span class="caption-subject font-green bold uppercase">图片预览</span>
        </div>
    </div>

    <div class="portlet light portlet-body mt-element-card mt-element-overlay">

        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12" id="left">
            <div class="mt-card-item">
                <div class="mt-card-avatar mt-overlay-1">
                    <img src="${base}/static/content/img/imgself.jpg">
                </div>
                <div class="mt-card-content"><h3 class="mt-card-name" id="imageCode"></h3>
                    <div class="mt-card-social">
                        <ul>
                            <li><a href="javascript:;"><i class="icon-pencil"></i></a></li>
                            <li><a href="javascript:;">
                                <i class="glyphicon glyphicon-trash red"></i>
                            </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-6 col-lg-6 col-sm-6 col-xs-12" id="right">
        </div>
    </div>
    <div class="row " id="subImg">
        <div class="col-lg-12" style="margin-top: 10px;border-bottom: 1px solid #8080804f;">
            <div class="portlet light ">
                <div class="portlet-title" style="margin-bottom: 20px">
                    <div class="caption">
                        <i class="fa fa-table font-green"></i>
                        <span class="caption-subject font-green bold uppercase">副图</span>
                    </div>
                </div>
                <ul class="vmcarousel-centered-infitine vmc-centered" id="imageContent">
                </ul>
            </div>
        </div>

    </div>
</div>

<!--更新图片信息-->
<div id="updateArea" class="modal fade" data-backdrop='static' tabindex="-1" data-width="400px">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
        <h4 class="modal-title" id="fabricTile">更改图片</h4>
    </div>
    <div class="modal-body form-horizontal text-center">
        <form id="imageForm" action="${base}/image/updateimage">
            <div class="form-body">
                <input type="hidden" id="imageId" name="imageId">
                <input type="hidden" id="imageName" name="imageName">
                <input type="hidden" id="imageType" name="imageType">
                <input type="hidden" id="imageUrl" name="imageUrl">
                <div class="fileinput fileinput-new " data-provides="fileinput">
                    <div class="fileinput-new thumbnail" style="width: 200px; height: 150px;">
                        <img id="selectImage"
                             src="http://www.placehold.it/200x150/EFEFEF/AAAAAA&amp;text=no+image"
                             alt="图片不见鸟"/>
                    </div>
                    <div class="fileinput-preview fileinput-exists thumbnail"
                         style="max-width: 200px; max-height: 150px;"></div>
                    <div>
                      <span class="btn default btn-file">
                          <span class="fileinput-new"> 更换此图片 </span>
                          <span class="fileinput-exists"> 更改 </span>
                          <input type="file" name="uploadfile" id="uploadfile" accept="image/*">
                      </span>
                        <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 移除 </a>
                    </div>
                </div>
                <h4 class="text-center" id="hCode">编号：</h4>
            </div>
        </form>
    </div>

    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button type="button" class="btn btn-outline green" id="commitUpdate">确认</button>
    </div>
</div>










