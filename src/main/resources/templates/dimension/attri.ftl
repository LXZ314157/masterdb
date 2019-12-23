<!-- BEGIN PAGE BAR -->
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li><a href="${base}">首页</a><i class="fa fa-angle-right"></i></li>
        <li><a href="javascript:;">维度管理</a><i class="fa fa-angle-right"></i></li>
        <li><span>维度属性</span></li>
    </ul>
</div>

<div class="portlet light portlet-fit portlet-datatable">
    <div class="portlet-title">
        <div class="caption">
            <i class="fa fa-table font-green"></i>
            <span class="caption-subject font-green bold uppercase">维度属性</span>
        </div>
    </div>

    <div class="row">
        <div class=" col-md-6">
            <div class="portlet light bordered">
                <div class="portlet-title">
                    <div class="caption">
                        <i class="icon-bar-chart font-dark"></i>
                        <span class="caption-subject font-dark bold uppercase">维度</span>
                    </div>
                    <div class="actions">
                        <a href="javascript:;" class="btn blue" onclick="showDimension(null)">
                            <i class="fa fa-plus"></i> 新增维度 </a>
                    </div>
                </div>
                <div class="portlet-body">
                    <div class="text-center">
                        <table class="table table-striped table-bordered table-hover dataTable"
                               id="attributeGroupTable">
                            <thead>
                            <tr>
                                <th class="detail text-center" width="8%">序号
                                </th>
                                <th class="detail text-center">维度编码
                                </th>
                                <th class="detail text-center">维度名
                                </th>
                                </th>
                                <th class="detail text-center">维度描述
                                </th>
                                <th class="detail text-center">操作
                                </th>
                            </tr>
                            </thead>
                            <tbody id="dimension">
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <div class=" col-md-6">
            <div class="portlet light bordered">
                <div class="portlet-title">
                    <div class="caption">
                        <i class="icon-bar-chart font-dark"></i>
                        <span class="caption-subject font-dark bold uppercase">属性定义</span>
                    </div>
                    <div class="actions">
                        <a href="javascript:;" class="btn blue btnAddArea" id="addAttribute">
                            <i class="fa fa-plus"></i> 新增维度属性</a>
                    </div>
                </div>
                <div class="portlet-body">
                    <div class="text-center">
                        <table class="table table-striped table-bordered table-hover dataTable">
                            <thead>
                            <tr>
                                <th class="detail text-center">属性编码
                                </th>
                                <th class="detail text-center">属性名
                                </th>
                                <th class="detail text-center" width="10%">值类型
                                </th>
                                <th class="detail text-center" width="10%">值长度
                                </th>
                                <th class="detail text-center" width="15%">是否有选择项
                                </th>
                                <th class="detail text-center" width="15%">是否有效
                                </th>
                                <th class="detail text-center" width="10%">操作
                                </th>
                            </tr>
                            </thead>
                            <tbody id="attrDefined">
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 新增或者编辑维度 -->
<div id="dimensionArea" class="modal fade" tabindex="-1" data-width="450">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
        <h4 class="modal-title">新增维度</h4>
    </div>
    <div class="modal-body">

        <div class="form-group row">
            <label class="control-label col-md-3 text-right"><span class="required">* </span>维度编码：</label>
            <div class="controls col-md-8">
                <input type="text" placeholder="" id="dimensionCode" class="form-control" maxlength="50"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="control-label col-md-3 text-right"><span class="required">* </span>维度名称：</label>
            <div class="controls col-md-8">
                <input type="text" placeholder="" id="dimensionName" class="form-control" maxlength="50"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="control-label col-md-3 text-right">维度描述：</label>
            <div class="controls col-md-8">
                <textarea placeholder="请输入维度描述" id="dimensionDesc" class="form-control" maxlength="150"></textarea>
            </div>
        </div>

        <div class="row" hidden id="dimensionStatus">
            <label class="control-label col-md-3 text-right"><span class="required">* </span>是否有效:</label>
            <div class="btn-group col-md-8" id="state" data-toggle="buttons">
                <label class="btn btn-default " id="inValid">
                    <input type="radio" class="toggle" value="0"> 无效
                </label>
                <label class="btn btn-default statedefault active" id="valid">
                    <input type="radio" class="toggle" value="1"> 有效
                </label>
            </div>
        </div>

    </div>
    <div class="modal-footer">
        <button type="submit" class="btn btn-success" id="savaDeminsion">保存</button>
        <a data-dismiss="modal" class="btn btn-inverse" href="#">取消</a>
    </div>
</div>


<div id="connect" class="modal fade" tabindex="-1" data-width="500" data-height="350">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
        <h4 class="modal-title">属性关联</h4>
    </div>

    <div class="modal-body">
        <div class="form-group form-horizontal">

            <label class="col-md-3 control-label">属性组名</label>
            <div class="col-md-8">
                <input type="text" class="form-control" disabled="disabled" placeholder="属性组名" readonly>
            </div>

        </div>
    </div>
    <div class="modal-footer">
        <button type="submit" class="btn btn-success" id="savegroup">保存</button>
        <a data-dismiss="modal" class="btn btn-inverse" href="#">取消</a>
    </div>
</div>


<!--属性关联-->
<div id="connectAttribute" class="modal fade" tabindex="-1" data-width="600" data-height="350px">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
        <h4 class="modal-title">维度属性关联</h4>
    </div>
    <div class="modal-body">
        <div class="form-horizontal">
            <input type="hidden" id="connectValue" name="buyerValue" value="1">
            <div class="form-group">
                <label class="col-md-3 control-label">维度名:</label>
                <input type="hidden" id="AttributeDimensionId"/>
                <input type="hidden" id="AttributeDimensionCode"/>
                <div class="col-md-8">
                    <input type="text" class="form-control relationgroupname" id="classDimensionName" placeholder="属性组名"
                           readonly>
                </div>
            </div>
            <div class="form-group">
                <div class="" id="checklist" style="width:350px;margin:0 auto; margin-top: 10px;">

                </div>
            </div>

        </div>
    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button type="button" class="btn btn-outline green" id="btnConnect" onclick="attributeRelevant()">确认</button>
    </div>
</div>

<!--添加维度属性-->
<div id="attributeDefinedArea" class="modal fade" tabindex="-1" data-width="600">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
        <h4 class="modal-title">编辑维度属性</h4>
    </div>
    <div class="modal-body ">
        <div class="form-horizontal">
            <div class="form-group">
                <label class="control-label col-md-3"><span class="required">* </span>属性编码：</label>
                <div class="controls col-md-7">
                    <input id="id" type="hidden"/>
                    <input type="text" placeholder="编码由字母和下划线组成" id="acode" class="form-control" maxlength="50"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-md-3"><span class="required">* </span>属性名：</label>
                <div class="controls col-md-7">
                    <input type="text" placeholder="" id="cname" class="form-control" maxlength="50"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-md-3">
                    <span class="required">* </span>属性值类型：
                </label>
                <div class="col-md-5" about="">
                    <div class="input-icon right">
                        <i class="fa"></i>
                        <select name="way" id="itemType" class="form-control" onchange="showAllItemTable()">
                            <option value="int">int</option>
                            <option value="bit">bit</option>
                            <option value="varchar">varchar</option>
                            <option value="nvarchar">nvarchar</option>
                        </select>
                    </div>
                </div>
                <div class="controls col-md-2">
                    <input type="text" placeholder="值长度" id="itemLength" class="form-control number"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-md-3">属性描述：</label>
                <div class="controls col-md-7">
                    <textarea class="form-control" rows="3" name="defdesc" id="defdesc" maxlength="150"></textarea>
                </div>
            </div>
            <div class="form-group" id="item_select">
                <label class="control-label col-md-3"><span class="required">* </span>是否有选择项:</label>
                <div class="btn-group col-md-7" id="stateItem" data-toggle="buttons">
                    <label class="btn red no_item" id="noSelect" value="0" onclick="showOrHideItem(false)">
                        <input type="radio" name="nameitem" class="toggle" value="0" style="noitem" checked> 无选项
                    </label>
                    <label class="btn green has_item" id="haveSelect" value="1" onclick="showOrHideItem(true)">
                        <input type="radio" name="nameitem" class="toggle" value="1" style="existitem"> 单选
                    </label>
                    <label class="btn blue has_item" id="haveMoreSelect" value="2" onclick="showOrHideItem(true)">
                        <input type="radio" name="nameitem" class="toggle" value="2" style="existitem"> 多选
                    </label>
                </div>
            </div>

            <div class="form-group" id="attribute_state">
                <label class="control-label col-md-3">状态：</label>
                <div class="btn-group col-md-7" id="status" data-toggle="buttons">
                    <label class="btn btn-default " id="nostate">
                        <input type="radio" name="rstate" class="toggle" value="0"> 无效
                    </label>
                    <label class="btn btn-default statedefault active" id="hastate">
                        <input type="radio" name="rstate" class="toggle" value="1" checked> 有效
                    </label>
                </div>
            </div>

            <div class="form-group" id="dataSelectTable" style="display: none">
                <label class="control-label col-md-3">
                    数据可选表：
                </label>
                <div class="col-md-5" about="">
                    <div class="input-icon right">
                        <i class="fa"></i>
                        <select name="itemTable" id="itemTable" class="form-control">

                        </select>
                    </div>
                </div>
            </div>
            <div class="form-group" id="itemcontent" style="display: none">
                <div class="row">
                    <div class="portlet light ">
                        <div class="portlet-title ">
                            <div class="controls col-md-4" style="margin-left: 45px;">
                                <input type="text" placeholder="选项名" id="itemName" class="form-control"/>
                            </div>

                            <div class="col-md-3 form-group" style="margin-left: 25px;">
                                <div class="btn-group" id="itemStatus" data-toggle="buttons">
                                    <label class="btn btn-default " id="itemStatus_0">
                                        <input type="radio" name="rstate" class="toggle" value="0"> 无效
                                    </label>
                                    <label class="btn btn-default statedefault active" id="itemStatus_1" id="">
                                        <input type="radio" name="rstate" class="toggle" value="1" checked> 有效
                                    </label>
                                </div>
                            </div>
                            <input type="hidden" id="itemId" value="0">

                            <div class=" col-md-4 form-group pull-right">
                                <a href="javascript:;" class="btn blue " id="btnAddAttribute"
                                   style="margin-left: 30px">
                                    添加 </a>
                                <a href="javascript:;" class="btn red pull-right" id="addCancel">
                                    取消</a>
                            </div>

                        </div>
                        <div class="portlet-body" style="max-height:200px;overflow-y:auto;">
                            <div class="text-center">
                                <table class="table table-bordered table-hover">
                                    <thead>
                                    <tr>
                                        <th class="text-center">选项名</th>
                                        <th class="text-center">状态</th>
                                        <th class="text-center">操作</th>
                                    </tr>
                                    </thead>
                                    <tbody id="edititem">
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
    <div class="modal-footer">
        <button class="btn btn-success" id="saveDimensionAttrib" data-tid="0" value="0">保存</button>
        <a data-dismiss="modal" class="btn btn-inverse" href="#" id="attributeCancel"> 取消</a>
    </div>

</div>
