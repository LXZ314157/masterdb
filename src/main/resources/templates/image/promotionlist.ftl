<!-- BEGIN PAGE BAR -->
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li><a href="${base}">首页</a><i class="fa fa-angle-right"></i></li>
        <li><a href="javascript:;">产品管理</a><i class="fa fa-angle-right"></i></li>
        <li><span>推广列表</span></li>
    </ul>
</div>
<!-- END PAGE BAR -->
<!-- BEGIN PAGE AREA-->
<div class="portlet light portlet-fit">
    <div class="portlet-body">
        <div class="row">
            <div class=" col-md-12">
                <div class="portlet light bordered">
                    <div class="portlet-title">
                        <div class="caption">
                            <i class="icon-bar-chart font-dark"></i>
                            <span class="caption-subject font-dark bold">推广列表</span>
                        </div>
                    </div>
                    <div class="portlet-body">
                        <div class="table-container">
                            <table class="table table-striped table-bordered table-hover table-checkable"
                                   id="datatable">
                                <thead>
                                <tr role="row" class="heading">
                                    <th>操作</th>
                                    <th>推广ID</th>
                                    <th>推广图片编码</th>
                                    <th>推广图片名</th>
                                    <th>线路</th>
                                    <th>波段</th>
                                    <th>年份</th>
                                    <th>开发季</th>
                                    <th>图片到期时间</th>
                                    <th>露出类型</th>
                                </tr>
                                </thead>
                                <tbody></tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 新增、编辑浮动层 -->
<div id="addAndEditPromotion" class="modal fade" tabindex="-1" data-width="1000">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
        <h4 class="modal-title" id="lookdetail_title">新增推广信息</h4>
    </div>
    <div class="modal-body">
        <form action="#" id="addpromotion" class="form-horizontal">
            <div class="form-body">
                <div class="alert alert-danger display-hide">
                    <button class="close" data-close="alert"></button>
                    红标字段输入错误, 请检查。
                </div>
                <div class="alert alert-success display-hide">
                    <button class="close" data-close="alert"></button>
                    验证通过
                </div>
                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="control-label col-md-2">
                            推广id
                            <span class="required">* </span>
                        </label>
                        <div class="col-md-3">
                            <div class="input-icon right">
                                <i class="fa"></i>
                                <input type="text" class="form-control" name="id" id="id" placeholder="推广id"/>
                                <label id="errmsg" style="color: red; display: none;"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group col-md-12">
                        <label class="control-label col-md-2">
                            推广编码
                            <span class="required">* </span>
                        </label>
                        <div class="col-md-3">
                            <div class="input-icon right">
                                <i class="fa"></i>
                                <input type="text" class="form-control" name="code" id="code" placeholder="推广编码"/>
                                <label id="errcodemsg" style="color: red; display: none;"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group col-md-12">
                        <label class="control-label col-md-2">
                            推广图片名
                            <span class="required">* </span>
                        </label>
                        <div class="col-md-3">
                            <div class="input-icon right">
                                <i class="fa"></i>
                                <input type="text" class="form-control" name="imgname" id="imgname"
                                       placeholder="推广图片名"/>

                            </div>
                        </div>
                    </div>
                    <div class="form-group col-md-12">
                        <label class="control-label col-md-2">
                            线路
                            <span class="required">* </span>
                        </label>
                        <div class="col-md-3">
                            <div class="input-icon right">
                                <i class="fa"></i>
                                <select name="line" id="line" class="form-control">
                                    <option value="">--请选择--</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="form-group col-md-12">
                        <label class="control-label col-md-2">
                            波段
                            <span class="required">* </span>
                        </label>
                        <div class="col-md-3">
                            <div class="input-icon right">
                                <i class="fa"></i>
                                <select name="waveBand" id="waveBand" class="form-control">
                                    <option value="">--请选择--</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="form-group col-md-12">
                        <label class="control-label col-md-2">
                            年份
                            <span class="required">* </span>
                        </label>
                        <div class="col-md-2">
                            <div class="input-icon right">
                                <i class="fa"></i>
                                <select name="year" id="year" class="form-control">
                                    <option value="">--请选择--</option>
                                    <option value="2015">2015</option>
                                    <option value="2016">2016</option>
                                    <option value="2017">2017</option>
                                    <option value="2018">2018</option>
                                    <option value="2019">2019</option>
                                    <option value="2020">2020</option>
                                    <option value="2021">2021</option>
                                    <option value="2022">2022</option>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="form-group col-md-12">
                        <label class="control-label col-md-2">
                            开发季
                            <span class="required">* </span>
                        </label>
                        <div class="col-md-2">
                            <div class="input-icon right">
                                <i class="fa"></i>
                                <select name="devSeason" id="devSeason" class="form-control">
                                    <option value="">--请选择--</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="form-group col-md-12">
                        <label class="control-label col-md-2">
                            过期时间
                            <span class="required">* </span>
                        </label>
                        <div class="col-md-5">
                            <div class="input-icon right">
                                <i class="fa"></i>
                                <input type="text" name="outofdate" id="outofdate"
                                       class="form-control form-control-inline date-picker" readonly="readonly"
                                       placeholder="(点击选择时间)">
                            </div>
                        </div>
                    </div>
                    <div class="form-group col-md-12">
                        <label class="control-label col-md-2">
                            露出类型
                        </label>
                        <div class="col-md-10">
                            <div class="input-icon right">
                                <select name="showtype" id="showtype" data-placeholder="多选"
                                        class="form-control select2-multiple" multiple>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <div class="modal-footer">
        <input type="hidden" id="type" value="0"/>
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button type="button" class="btn btn-outline green" id="btnSave">确认</button>
    </div>
</div>

<!-- 导入look浮动层 -->
<div id="import_look" class="modal fade" tabindex="-1" data-width="500">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
        <h4 class="modal-title">导入look</h4>
    </div>
    <div class="modal-body">
        <form action="#" id="form_import_look" class="form-horizontal  text-center">
            <div class="form-body">
                <div class="row">
                    <div class="form-group">
                        <label class="control-label col-md-3">
                            excel文档
                            <span class="required">* </span>
                        </label>
                        <div class="col-md-3">
                            <div class="fileinput fileinput-new" data-provides="fileinput">
                                <div class="input-group input-large">
                                    <div class="form-control uneditable-input input-fixed input-medium"
                                         data-trigger="fileinput">
                                        <i class="fa fa-file fileinput-exists"></i>&nbsp;<span
                                            class="fileinput-filename"></span>
                                    </div>
                                    <span class="input-group-addon btn default btn-file">
                                                <span class="fileinput-new">选择文件 </span>
                                                <span class="fileinput-exists">更改 </span>
                                                <input type="file" name="lookfile" id="lookfile"
                                                       accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"/>
                                            </span>
                                    <a href="javascript:;" class="input-group-addon btn red fileinput-exists"
                                       data-dismiss="fileinput">删除 </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <a class="button" href="${base}/static/content/excel/look.xlsx">下载模板</a>
            </div>
        </form>
    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button type="button" class="btn btn-outline green" id="btnImportLook">确认</button>
    </div>
</div>