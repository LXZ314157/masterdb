<div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <a href="${base}">首页</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a href="javascript:;">HR管理</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <span>岗位信息</span>
        </li>
    </ul>
</div>
<div class="row portlet light bordered" style=" margin: 10px;margin-bottom: 30px;height: 150px;margin-top: 10px;">
    <div class="col-md-12">
        <div class="portlet light portlet-fit ">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-table font-green"></i>
                    <span class="caption-subject font-green bold uppercase">查询条件</span>
                </div>
            </div>
            <div class="portlet-body" style="padding-bottom: 5px;">

                <div class="row col-md-6">
                    <div class="form-group ">
                        <label class="control-label col-md-4 "
                               style="text-align: right;line-height: 33px;">岗位编号/名称:</label>
                        <div class="col-md-6" about="">
                            <div class="input-icon right">
                                <i class="fa"></i>
                                <input type="text" name="postId" id="postId" class="form-control" placeholder=""/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row col-md-6">
                    <div class="form-group  ">
                        <label class="control-label col-md-4" style="text-align: right;line-height: 33px;">语言类别:</label>
                        <div class="col-md-6" about="">
                            <div class="input-icon right">
                                <i class="fa"></i>
                                <select name="lanCode" id="lanCode" class="form-control">
                                    <option value="">---请选择---</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="row portlet light bordered" style="margin:5px">
    <div class="col-md-12">
        <div class="portlet light portlet-fit portlet-datatable">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-table font-green"></i>
                    <span class="caption-subject font-green bold uppercase">岗位信息列表</span>
                </div>
                <div class="dt-buttons" style="text-align: right;">
                </div>
            </div>
            <div class="portlet-body" style="padding-bottom: 5px;">
                <div class="table-container">
                    <table class="table table-striped table-bordered table-hover table-checkable" id="postlist">
                        <thead>
                        <tr>
                            <th>
                                <label class="mt-checkbox">
                                    <input id="chooseAll" name='checkbox' type="checkbox"
                                           onclick="selectAllCheckBox(this)"/>
                                    <span></span>
                                </label>
                            </th>

                            <th style="min-width:80px;">岗位编号</th>
                            <th>语言</th>
                            <#--<th>源数据编号</th>-->
                            <th>岗位名称</th>
                            <th>部门名称</th>
                            <th>上级岗位编号</th>
                            <th>管理者</th>
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
    <form id="frmMoedlDownload" name="frmMoedlDownload"  style="display:none;">
        <input type="hidden" id="postIdOrNames" name="postIdOrNames" value=""/>
        <input type="hidden" id="lanCodes" name="lanCodes" value=""/>
    </form>

</div>


