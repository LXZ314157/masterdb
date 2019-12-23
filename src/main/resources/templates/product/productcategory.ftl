<div class="page-bar">
    <ul class="page-breadcrumb">
        <li><a href="${base}">首页</a><i class="fa fa-angle-right"></i></li>
        <li><a href="javascript:;">主数据维护</a><i class="fa fa-angle-right"></i></li>
        <li><span>产品分类列表</span></li>
    </ul>
</div>
<input type="hidden" id="loadUrl" value=${Request.loadUrl}>
<div class="portlet light portlet-fit">
    <div class="portlet-body">
        <div class="row" style="margin-top: 25px;">
            <div class=" col-md-12">
                <div class="portlet light bordered">
                    <div class="portlet-title">
                        <div class="caption">
                            <i class="icon-bar-chart font-dark"></i>
                            <span class="caption-subject font-dark bold uppercase" id="datalist">数据列表</span>
                        </div>
                    </div>
                    <div class="portlet-body">
                        <div class="table-container">
                            <table class="table table-striped table-bordered table-hover table-checkable" id="productcategorylist">
                                <thead>
                               <tr role="row" class="heading">
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

    <form id="frmMoedlDownload" name="frmMoedlDownload"  style="display:none;">
        <input type="hidden" id="productCodeOrName" name="productCodeOrName" value=""/>
    </form>

</div>
