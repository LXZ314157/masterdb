<!-- BEGIN PAGE BAR -->
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li><a href="${base}">首页</a><i class="fa fa-angle-right"></i></li>
        <li><a href="javascript:;">产品管理</a><i class="fa fa-angle-right"></i></li>
        <li><span>款式详情页</span></li>
    </ul>
</div>
<div class="portlet light portlet-fit">
    <div class="portlet-body">
        <div class="row">
            <div class="portlet light portlet-body mt-element-card mt-element-overlay">
                <div class="portlet-title" style="margin-bottom: 35px">
                    <div class="caption">
                        <i class="fa fa-table font-green"></i>
                        <span class="caption-subject font-green bold uppercase">款式详情</span>
                    </div>
                </div>
                <div class="col-lg-12">
                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12" id="left" style="">
                        <div class="mt-card-item">
                            <div class="mt-card-avatar mt-overlay-1"
                                 style=" height: 700px;border: 1px solid #e7ecf1;">
                                <img src="${base}/static/content/img/imgself.jpg" id="productSelf">
                                <span class="imgageTitle">ICICLE版权所有</span>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6 col-mg-6 col-sm-6 col-xs-12" id="productDetail">
                        <div class="row message">
                            <div class="row static-info">
                                <div class="col-md-3 name"><label class="control-label col-md-12">产品编码:</label></div>
                                <div class="col-md-7 value" id="productCode"></div>
                            </div>
                            <div class="row static-info">
                                <div class="col-md-3 name"><label class="control-label col-md-12"> 品名:</label></div>
                                <div class="col-md-7 value" id="productName"></div>
                            </div>
                            <div class="row static-info">
                                <div class="col-md-3 name"><label class="control-label col-md-12">波段:</label></div>
                                <div class="col-md-7 value" id="waveAndBand"></div>
                            </div>
                            <div class="row static-info">
                                <div class="col-md-3 name"><label class="control-label col-md-12">价格:</label></div>
                                <div class="col-md-7 value" id="price"></div>
                            </div>
                            <div class="row static-info">
                                <div class="col-md-3 name"><label class="control-label col-md-12">颜色:</label></div>
                                <div class="col-md-7 value" id="color"></div>
                            </div>
                            <div class="row static-info">
                                <div class="col-md-3 name"><label class="control-label col-md-12"> 面料:</label></div>
                                <div class="col-md-7 value" id="materialName"></div>
                            </div>
                            <div class="row static-info">
                                <div class="col-md-3 name"><label class="control-label col-md-12"> 面料特点:</label></div>
                                <div class="col-md-9 value" id="fabic"></div>
                            </div>
                            <div class="row static-info">
                                <div class="col-md-3 name"><label class="control-label col-md-12">款式特点:</label></div>
                                <div class="col-md-9 value" id="styleFeature"></div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <div class="row recommandStyle">
            <div class="col-lg-12" style="margin-top: 10px;border-bottom: 1px solid #8080804f;">
                <div class="portlet light ">
                    <div class="portlet-title" style="margin-bottom: 20px">
                        <div class="caption">
                            <i class="fa fa-table font-green"></i>
                            <span class="caption-subject font-green bold uppercase">替代款式</span>
                        </div>
                    </div>
                    <ul class="vmcarousel-centered-infitine vmc-centered" id="imageContent">
                    </ul>
                </div>
            </div>

        </div>

    </div>
</div>
