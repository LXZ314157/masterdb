<!-- BEGIN PAGE BAR -->
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li><a href="${base}">首页</a><i class="fa fa-angle-right"></i></li>
        <li><a href="javascript:;">店铺管理</a><i class="fa fa-angle-right"></i></li>
        <li><span>店铺属性</span></li>
    </ul>
</div>

<div class="portlet light portlet-fit">
    <div class="portlet-body">
        <div class=" col-md-5">
            <div class="portlet light bordered">
                <div class="portlet-title">
                    <div class="caption">
                        <i class="icon-bar-chart font-dark"></i>
                        <span class="caption-subject font-dark bold uppercase">属性组</span>
                    </div>
                    <div class="actions">
                        <a href="javascript:;" class="btn blue" id="addAttributeGroup">
                            <i class="fa fa-plus"></i> 新增属性组 </a>
                    </div>
                </div>
                <div class="portlet-body">
                    <div class="table-scrollable">
                        <table class="table table-striped table-bordered table-hover dataTable"
                               id="attributeGroupTable">
                            <thead>
                            <tr>
                                <th class="detail text-center" width="5%">序号
                                </th>
                                <th class="detail text-center">属性组编码
                                </th>
                                <th class="detail text-center">属性组名
                                </th>
                                <th class="detail text-center" width="25%">操作
                                </th>
                            </tr>
                            </thead>
                            <tbody id="tbCityLevel">
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <div class=" col-md-7">
            <div class="portlet light bordered">
                <div class="portlet-title">
                    <div class="caption">
                        <i class="icon-bar-chart font-dark"></i>
                        <span class="caption-subject font-dark bold uppercase">属性定义</span>
                    </div>
                    <div class="actions">
                        <a href="javascript:;" class="btn blue btnAddArea" id="addAttribute">
                            <i class="fa fa-plus"></i> 新增属性定义</a>
                    </div>
                </div>
                <div class="portlet-body">
                    <div class="table-scrollable">
                        <table class="table table-striped table-bordered table-hover dataTable" id="storeAttributeDef">
                            <thead>
                            <tr>
                                <th class="detail" width="30%">属性名
                                </th>
                                <th class="detail">属性编码
                                </th>
                                <th class="detail">属性类型
                                </th>
                                <th class="detail" width="10%">值类型
                                </th>
                                <th class="detail" width="10%">值长度
                                </th>
                                <th class="detail" width="15%">是否有选择项
                                </th>
                                <th class="detail" width="15%">排序值
                                </th>
                                <th class="detail" width="15%">是否有效
                                </th>
                                <th class="detail" width="10%">操作
                                </th>
                            </tr>
                            </thead>
                            <tbody id="tbArea">
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>


</div>

<!-- 新增店铺浮动框 -->
<div id="supersearch" class="modal fade" tabindex="-1" data-width="450" data-height="130">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
        <h4 class="modal-title" id="modalTitle">新增店铺属性组</h4>
    </div>
    <div class="modal-body">

        <div class="form-group row">
            <label class="control-label col-md-4 text-right">属性编码：</label>
            <div class="controls col-md-7">
                <input type="text" placeholder="编码由字母和下划线组成" id="groupcode" class="form-control"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="control-label col-md-4 text-right">属性名称：</label>
            <div class="controls col-md-7">
                <input type="text" placeholder="请输入属性名称" id="groupname" class="form-control"/>
            </div>
        </div>
    </div>
    <div class="modal-footer">
        <button type="submit" class="btn btn-success" id="savegroup">保存</button>
        <a data-dismiss="modal" class="btn btn-inverse" href="#">取消</a>
    </div>
</div>


<div id="connect" class="modal fade" tabindex="-1" data-width="500" data-height="300">
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
<div id="connectAttribute" class="modal fade" tabindex="-1" data-width="500" data-height="400px">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
        <h4 class="modal-title" id="storeTypeTile">属性组关联</h4>
    </div>
    <div class="modal-body form-horizontal">
        <div class="form-body">
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="control-label col-md-3">
                        属性组名:
                        <span class="required">* </span>
                    </label>
                    <div class="col-md-8">
                        <div class="input-icon right">
                            <i class="fa"></i>
                            <input type="text" class="form-control" disabled name="storeTypeName"
                                   id="storeAttributeGroupName"
                                   placeholder="属性组名"/>
                            <input type="hidden" id="storeAttributeGroupId"/>
                        </div>
                    </div>
                </div>
                <div class="form-group col-md-12">
                    <div class="mt-checkbox-list col-md-offset-5 col-sm-offset-5" id="checklist">
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button type="button" class="btn btn-outline green" id="btnConnect" onclick="attributeRelevant()">确认</button>
    </div>
</div>

<!--添加店铺属性-->

<div id="addStoreAttr" class="modal fade" tabindex="-1" data-width="600">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
        <h4 class="modal-title" id="groupTitle">编辑店铺属性</h4>
    </div>
    <div class="modal-body">
        <div class="form-horizontal">
            <div class="form-group">
                <label class="control-label col-md-3">属性编码：</label>
                <div class="controls col-md-7">
                    <input type="text" placeholder="编码由字母和下划线组成" id="acode" class="form-control"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-md-3">属性名：</label>
                <div class="controls col-md-7">
                    <input type="text" placeholder="请输入属性名" id="cname" class="form-control"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-md-3">
                    属性值类型：
                </label>
                <div class="col-md-5" about="">
                    <div class="input-icon right">
                        <i class="fa"></i>
                        <select name="way" id="itemType" class="form-control">
                            <option value="Int">Int</option>
                            <option value="Float">Float</option>
                            <option value="Money">Money</option>
                            <option value="Decimal">Decimal</option>
                            <option value="Varchar">Varchar</option>
                            <option value="Nvarchar">Nvarchar</option>
                            <option value="Datetime">Datetime</option>
                        </select>
                    </div>
                </div>
                <div class="controls col-md-2">
                    <input type="text" placeholder="值长度" id="itemLength" class="form-control number"/>
                </div>
                <input type="hidden" id="storeAttribDefId">
            </div>
            <div class="form-group">
                <label class="control-label col-md-3">同步提示：</label>
                <div class="controls col-md-7">
                    <select name="isSycn" id="isSycn" class="form-control">
                        <option value="0">否</option>
                        <option value="1">是</option>
                    </select>
                </div>
            </div>

            <#--<div class="form-group">-->
                <#--<label class="control-label col-md-3">属性组类型：</label>-->
                <#--<div class="controls col-md-7">-->
                    <#--<select name="storeAttribNature" id="storeAttribNature" class="form-control">-->
                        <#--<option value="0">基础属性</option>-->
                        <#--<option value="1">扩展属性</option>-->
                    <#--</select>-->
                <#--</div>-->
            <#--</div>-->

            <div class="form-group">
                <label class="control-label col-md-3">排序值：</label>
                <div class="controls col-md-7">
                    <input type="text" id="defineOrder" class="form-control"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-md-3">是否有值选项:</label>
                <div class="btn-group col-md-7" id="state" data-toggle="buttons">
                    <label class="btn red no_item" id="noSelect">
                        <input type="radio" name="nameitem" class="toggle" value="0" style="noitem" checked> 无选项
                    </label>
                    <label class="btn green has_item" id="haveSelect">
                        <input type="radio" name="nameitem" class="toggle" value="1" style="existitem"> 有选项
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
            <div class="form-group" id="itemcontent" style="display: none;">
                <div class="row">
                    <div class="portlet light ">
                        <div class="portlet-title">
                            <div class="controls col-md-2">
                                <input type="text" placeholder="编码" id="itemCode" class="form-control"/>
                            </div>
                            <div class="controls col-md-2">
                                <input type="text" placeholder="选项名" id="itemName" class="form-control"/>
                            </div>
                            <div class="controls col-md-3">
                                <input type="text" placeholder="选项描述" id="itemDesc" class="form-control"/>
                            </div>
                            <div class="col-md-3 form-group">
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

                            <div class=" col-md-1">
                                <a href="javascript:;" class="btn blue pull-left" id="btnAddAttribute"
                                   style="margin-right: 50px">
                                    <i class="fa fa-plus"></i> 添加 </a>
                            </div>
                        </div>
                        <div class="portlet-body" style="max-height:200px;overflow-y:auto; ">
                            <div class="table-scrollable">
                                <table class="table table-bordered table-hover">
                                    <thead>
                                    <tr>
                                        <th class="text-center">选项编码</th>
                                        <th class="text-center">选项名</th>
                                        <th class="text-center">选项描述</th>
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
        <button class="btn btn-success" id="saveStoreAttrib" data-tid="0" value="0">保存</button>
        <a data-dismiss="modal" class="btn btn-inverse" href="#">取消</a>
    </div>

</div>
