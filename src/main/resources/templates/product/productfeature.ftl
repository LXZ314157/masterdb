<!-- BEGIN PAGE BAR -->
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li><a href="${base}">首页</a><i class="fa fa-angle-right"></i></li>
        <li><a href="javascript:;">产品管理</a><i class="fa fa-angle-right"></i></li>
        <li><span>款式特点编辑</span></li>
    </ul>
</div>

<div class="portlet light portlet-fit">
    <div class="portlet-body">
        <div class="row">
            <div class=" col-md-12">
                <div class="portlet light bordered">
                    <div class="portlet-title" style="border-bottom: none">
                        <div class="caption">
                            <span class="caption-subject font-dark bold uppercase">
                                  <i class="icon-bar-chart font-dark"></i>
                                <span class="caption-subject font-dark bold uppercase">特点列表</span>
                            </span>
                        </div>
                    </div>
                    <div class="portlet-body form">
                        <div class="tabbable-line boxless margin-bottom-20">
                            <div class="tab-content">
                                <div class="tab-pane active" id="tab_3">
                                    <div class="row">
                                        <div class="col-md-6 col-sm-12 responsive-1024">
                                            <div class="form-body">
                                                <div class="row">
                                                    <div class="form-group col-md-12">
                                                        <label class="control-label col-md-3"
                                                               style="text-align: right;">
                                                            产品编码：
                                                        </label>
                                                        <div class="col-md-8">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" class="form-control"
                                                                       name="productCode"
                                                                       id="productCode"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="form-group col-md-12">
                                                        <label class="control-label col-md-3"
                                                               style="text-align: right;">
                                                            款式推荐：
                                                        </label>
                                                        <div class="col-md-8">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <textarea class="form-control" rows="6"
                                                                          name="styleRecommend"
                                                                          id="styleRecommend"></textarea>
                                                            <#--<input type="text" class="form-control" name="styleRecommend"-->
                                                            <#--id="styleRecommend"/>-->
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="form-group col-md-12">

                                                        <div class="input-icon right">
                                                            <form class="form-horizontal form-bordered">
                                                                <div class="form-body">
                                                                    <div class="form-group last">
                                                                        <label class="control-label col-md-3">款式特点：</label>
                                                                        <div class="col-md-9">
                                                                            <div name="summernote" id="summernote"
                                                                                 style="display: none;"></div>
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
                                        <div class="col-md-6 col-sm-12 responsive-1024">
                                            <img src="" id="demo3" style="width: 800px;height: 700px" />
                                        </div>

                                    </div>

                                </div>
                            </div>
                            <div class="modal-footer" style="text-align: center">
                                <button type="button" data-dismiss="modal" class="btn btn-outline green">取消</button>
                                <button type="button" class="btn btn-outline red" id="submit">确认</button>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </div>

    </div>
</div>

</div>

</div>
</div>

<div id="enlarge" class="modal fade" data-backdrop='static' tabindex="-1" data-width="1580px" data-hight="1536" >
    <img src="" id="largeimg"/>
</div>
