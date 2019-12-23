<!-- BEGIN PAGE BAR -->
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li><a href="${base}">${home ! "首页"}</a><i class="fa fa-angle-right"></i></li>
        <li><a href="javascript:;">${module ! "主数据维护"}</a><i class="fa fa-angle-right"></i></li>
        <li><span>${title ! "物料列表"}</span></li>
    </ul>
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
                                <div class="btn-group">
	                                <a style="margin-left: 10px" id="tableTitle"
                                       data-toggle="dropdown"> ${allMaterial ! "全部物料"} <span
                                            class="caret"></span></a>
	                                    <ul class="dropdown-menu" role="menu">
		                                    <li><a href="javascript:;" class="btnAll">${allMaterial ! "全部物料"}</a></li>
		                                    <li><a href="javascript:;"
                                                   class="btnAboutToUpdate">${notUpdateMaterial ! "待更新物料"}</a></li>
	                                    </ul>
                                </div>
                            </span>
                        </div>
                    </div>
                    <input type="hidden" value="-1" id="type">
                    <div class="portlet-body">
                        <div class="table-container">
                            <table class="table table-striped table-bordered table-hover table-checkable"
                                   id="datatable">
                                <thead>
                                <tr role="row" class="heading">
                                    <th>
                                        <label class="mt-checkbox">
                                            <input id="chooseAll" name='checkbox' type="checkbox"
                                                   onclick="selectAllCheckBox(this)"/>
                                            <span></span>
                                        </label>
                                    </th>
                                    <th>${materialCode ! "物料编码"}</th>
                                    <th>${materialName ! "物料名称"}</th>
                                    <th>${dlName ! "物料名称"}</th>
                                    <th>${zlName ! "物料名称"}</th>
                                    <th>${xlName ! "物料名称"}</th>
                                    <th>${uom ! "计量单位"}</th>
                                    <th>${status ! "状态"}</th>
                                    <th>${lastSynDate ! "同步时间"}</th>
                                    <th>${operate ! "操作"}</th>
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
        <input type="hidden" id="materialNameOrNo" name="materialNameOrNo" value=""/>
    </form>

</div>