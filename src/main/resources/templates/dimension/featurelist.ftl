<!-- BEGIN PAGE BAR -->
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li><a href="${base}">首页</a><i class="fa fa-angle-right"></i></li>
        <li><a href="javascript:;">产品管理</a><i class="fa fa-angle-right"></i></li>
        <li><span>产品特点列表</span></li>
    </ul>
</div>
<div class="portlet light portlet-fit portlet-datatable">
    <div class="portlet-title">
        <div class="caption">
            <i class="fa fa-table font-green"></i>
            <span class="caption-subject font-green bold uppercase">产品特点列表</span>
        </div>
    </div>
</div>

<div class="portlet light portlet-fit">
    <div class="portlet-body">
        <div class="row">
            <div class=" col-md-12">
                <div class="portlet light bordered">
                    <div class="portlet-title">
                        <div class="caption">
                            <span class="caption-subject font-dark bold uppercase">
                                  <i class="icon-bar-chart font-dark"></i>
                                <span class="caption-subject font-dark bold uppercase">特点列表</span>
                            </span>
                        </div>
                    </div>
                    <div class="portlet-body">
                        <div class="table-container">
                            <input type="hidden" value="" id="search">
                            <table class="table table-striped table-bordered table-hover table-checkable"
                                   id="featureList">
                                <thead>
                                <tr role="row" class="heading">
                                    <th style="min-width:130px;" >产品编码</th>
                                    <th>替代款式</th>
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
<#--编辑产品特点-->
<div id="editFeature" class="modal fade" tabindex="-1" data-width="1200">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
        <h4 class="modal-title" id="fabricTile">编辑特点</h4>
    </div>
    <div class="modal-body form-horizontal">
        <div class="form-body">
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="control-label col-md-3">
                        <span class="required">* </span>产品编码:
                    </label>
                    <div class="col-md-8">
                        <div class="input-icon right">
                            <i class="fa"></i>
                            <input type="text" class="form-control" name="productCode" id="productCode"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="control-label col-md-3">
                        款式推荐：
                    </label>
                    <div class="col-md-8">
                        <div class="input-icon right">
                            <i class="fa"></i>
                            <input type="text" class="form-control" name="styleRecommend" id="styleRecommend"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <div class="col-md-11">
                        <div class="input-icon right">
                            <form class="form-horizontal form-bordered">
                                <div class="form-body">
                                    <div class="form-group last">
                                        <label class="control-label col-md-2">款式特点：</label>
                                        <div class="col-md-10">
                                            <div name="summernote" id="summernote_1" style="display: none;"></div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        <#--<textarea id="productFeature" placeholder="请输入该款式特点的详细" rows="8"-->
                        <#--class="form-control"></textarea>-->
                        </div>
                    </div>
                </div>
            </div>


        </div>
    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline green">取消</button>
        <button type="button" class="btn btn-outline red" id="saveFeature">确认</button>
    </div>
</div>
