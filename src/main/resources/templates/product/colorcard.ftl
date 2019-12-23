<!-- BEGIN PAGE BAR -->
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li><a href="${base}">首页</a><i class="fa fa-angle-right"></i></li>
        <li><a href="javascript:;">产品信息管理</a><i class="fa fa-angle-right"></i></li>
        <li><span>色卡列表</span></li>
    </ul>
</div>

<div class="portlet light portlet-fit">
    <div class="portlet-body">
        <div class="row" style="margin-top: 25px;">
            <div class=" col-md-12">
                <div class="portlet light bordered">
                    <div class="portlet-title">
                        <div class="caption">
                            <i class="icon-bar-chart font-dark"></i>
                            <span class="caption-subject font-dark bold uppercase">数据列表</span>
                        </div>
                    </div>
                    <div class="portlet-body">
                        <div class="table-container">
                            <table class="table table-striped table-bordered table-hover table-checkable"
                                   id="colorCardList">
                                <thead>
                                <tr role="row" class="heading">
                                    <th>色卡编码</th>
                                    <th>色卡名称</th>
                                    <th>状态</th>
                                    <th>创建时间</th>
                                    <th>更新时间</th>
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
        </div>

    </div>
</div>

<div id="editColorCard" class="modal fade" data-backdrop='static' tabindex="-1" data-width="600px">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
        <h4 class="modal-title" id="colorCardTile">编辑色卡</h4>
    </div>
    <div class="modal-body form-horizontal">
        <input id="fabricId" type="hidden">
        <div class="form-body">
            <div class="row">
                <div class="form-group col-md-12 text-center">
                    <label class="control-label col-md-3">
                        <span class="required">* </span>色卡编码:
                    </label>
                    <div class="col-md-8">
                        <div class="input-icon right">
                            <i class="fa"></i>
                            <input type="text" class="form-control" name="colorCardCode" id="colorCardCode" maxlength="20"
                                   placeholder="请输入色卡编码"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="control-label col-md-3">
                        <span class="required">* </span> 色卡名称:

                    </label>
                    <div class="col-md-8">
                        <div class="input-icon right">
                            <i class="fa"></i>
                            <input type="text" class="form-control" name="colorCardName" id="colorCardName" maxlength="100"
                                   placeholder=""/>
                        </div>
                    </div>
                </div>
            </div>

            <input class="hidden" value="" id="id"/>
        </div>
    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button type="button" class="btn btn-outline green" id="saveColorCard" val="">确认</button>
    </div>
</div>

<div id="leadIn" class="modal fade" data-backdrop='static' tabindex="-1" data-width="700px">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
        <h4 class="modal-title" id="colorCardTile">批量添加</h4>
    </div>
    <div class="modal-body form-horizontal">
        <div class="form-body  text-center">
            <form class="form-group text-center" id="colorcardfile" method="post" style="margin:15px"
                  enctype="multipart/form-data">
                <label class="control-label col-md-3 col-sm-3">色卡清单：</label>
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
                                                                    <input type="file" data-provides="fileinput" name="uploadfile" id="uploadfile"
                                                                           accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"> </span>
                            <a href="javascript:;" class="input-group-addon btn red fileinput-exists"
                               data-dismiss="fileinput"> 移除 </a>
                        </div>
                    </div>
                </div>
            </form>
            <a class="button text-center" href="${base}/static/content/excel/colorcard.xlsx">下载模板</a>
        </div>
    </div>

    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark cancel">取消</button>
        <button type="button" class="btn btn-outline green" id="upLoadFile" val="">确认</button>
    </div>

    <form id="frmMoedlDownload" name="frmMoedlDownload"  style="display:none;">
        <input type="hidden" id="colorCardNameOrNo" name="colorCardNameOrNo" value=""/>
    </form>

</div>
