<!-- BEGIN PAGE BAR -->
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li><a href="${base}">首页</a><i class="fa fa-angle-right"></i></li>
        <li><a href="javascript:;">产品信息管理</a><i class="fa fa-angle-right"></i></li>
        <li><span>产品属性维护</span></li>
    </ul>
</div>

<div class="portlet light portlet-fit portlet-datatable">
    <div class="portlet-title">
        <div class="caption">
            <i class="fa fa-table font-green"></i>
            <span class="caption-subject font-green bold uppercase">产品属性</span>
        </div>
    </div>

    <div class="row">
        <div class=" col-md-6">
            <div class="portlet light bordered">
                <div class="portlet-title">
                    <div class="caption">
                        <i class="icon-bar-chart font-dark"></i>
                        <span class="caption-subject font-dark bold uppercase">产品</span>
                    </div>
                    <div class="actions">
                        <a href="javascript:;" class="btn blue" onclick="showProductCategory(null)">
                            <i class="fa fa-plus"></i> 新增产品类别 </a>
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
                                <th class="detail text-center">产品编码
                                </th>
                                <th class="detail text-center">产品名
                                </th>
                                </th>
                                <th class="detail text-center">产品描述
                                </th>
                                <th class="detail text-center">操作
                                </th>
                            </tr>
                            </thead>
                            <tbody id="productCategory">
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
                            <i class="fa fa-plus"></i> 新增产品属性</a>
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

<!-- 新增或者编辑产品 -->
<div id="producCategorytArea" class="modal fade" tabindex="-1" data-width="450">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
        <h4 class="modal-title">新增产品类别</h4>
    </div>
    <div class="modal-body">

        <div class="form-group row">
            <label class="control-label col-md-3 text-right"><span class="required">* </span>产品编码：</label>
            <div class="controls col-md-8">
                <input type="text" placeholder="" id="productCategoryCode" class="form-control"/>
            </div>
        </div>


        <div class="form-group row" id="loadUrlDiv">
            <label class="control-label col-md-3 text-right"><span class="required">* </span>列表地址：</label>
            <div class="controls col-md-8">
                <input type="text" placeholder="请输入列表地址,格式：product/xxx" id="loadUrl" class="form-control"/>
            </div>
        </div>


        <div class="form-group row">
            <label class="control-label col-md-3 text-right"><span class="required">* </span>产品名称：</label>
            <div class="controls col-md-8">
                <input type="text" placeholder="" id="categoryName" class="form-control"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="control-label col-md-3 text-right">产品描述：</label>
            <div class="controls col-md-8">
                <textarea placeholder="请输入产品描述" id="categoryDesc" class="form-control"></textarea>
            </div>
        </div>

    </div>
    <div class="modal-footer">
        <button type="submit" class="btn btn-success" id="savaProductCategory">保存</button>
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
        <h4 class="modal-title">产品属性关联</h4>
    </div>
    <div class="modal-body">
        <div class="form-horizontal">
            <input type="hidden" id="connectValue" name="buyerValue" value="1">
            <div class="form-group">
                <label class="col-md-3 control-label">产品名:</label>
                <input type="hidden" id="attributeId"/>
                <input type="hidden" id="attributeCode"/>
                <div class="col-md-8">
                    <input type="text" class="form-control relationgroupname" id="productName" placeholder="属性组名"
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

<!--添加产品属性-->
<div id="attributeDefinedArea" class="modal fade" tabindex="-1" data-width="600">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
        <h4 class="modal-title">编辑产品属性</h4>
    </div>
    <div class="modal-body ">
        <div class="form-horizontal">
            <div class="form-group">
                <label class="control-label col-md-3"><span class="required">* </span>属性主键：</label>
                <div class="controls col-md-7">
                    <input type="text" id="defKey" class="form-control"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-md-3"><span class="required">* </span>属性编码：</label>
                <div class="controls col-md-7">
                    <input id="id" type="hidden"/>
                    <input type="text" placeholder="编码由字母和下划线组成" id="acode" class="form-control"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-md-3"><span class="required">* </span>属性名：</label>
                <div class="controls col-md-7">
                    <input type="text" placeholder="" id="cname" class="form-control"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-3"><span class="required">* </span>属性模板值：</label>
                <div class="controls col-md-7">
                    <input type="text" placeholder="" id="modelValue" class="form-control"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-3">同步提示：</label>
                <div class="controls col-md-7">
                    <select name="isSync" id="isSync" class="form-control">
                        <option value="1" selected>是</option>
                        <option value="0">否</option>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-3">
                    <span class="required">* </span>属性组类型：
                </label>
                <div class="col-md-7" about="">
                    <div class="input-icon right">
                        <i class="fa"></i>
                        <select name="defGroup" id="defGroup" class="form-control" onchange="showAllItemTable()">
                            <option value="0" selected>通用</option>
                            <option value="2">产品分类</option>
                        </select>
                    </div>
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
                            <option value="date">date</option>
                            <option value="datetime">datetime</option>
                            <option value="float">float</option>
                            <option value="money">money</option>
                            <option value="char">char</option>
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
                    <textarea class="form-control" rows="3" name="defdesc" id="defdesc"></textarea>
                </div>
            </div>
            <div class="form-group" id="item_select">
                <label class="control-label col-md-3"><span class="required">* </span>是否有选择项:</label>
                <div class="btn-group col-md-7" id="stateItem" data-toggle="buttons">
                    <label class="btn red no_item" id="noSelect" value="0" onclick="showOrHideItem(false,0)">
                        <input type="radio" name="nameitem" class="toggle" value="0" style="noitem" checked> 无选项
                    </label>
                    <label class="btn green has_item" id="haveSelect" value="1" onclick="showOrHideItem(true,1)">
                        <input type="radio" name="nameitem" class="toggle" value="1" style="existitem"> 单选
                    </label>
                    <label class="btn blue has_item" id="haveMoreSelect" value="2" onclick="showOrHideItem(true,2)">
                        <input type="radio" name="nameitem" class="toggle" value="2" style="existitem"> 多选
                    </label>
                </div>
            </div>

            <div class="form-group" id="attribute_state">
                <label class="control-label col-md-3"><span class="required">* </span>状态：</label>
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

                            <div class="controls col-md-2" id="itemCodeDiv">
                                <input type="text" placeholder="编码" id="itemCode" class="form-control"/>
                            </div>
                            <div class="controls col-md-2" id="itemNameDiv">
                                <input type="text" placeholder="选项名" id="itemName" class="form-control"/>
                            </div>
                            <div class="col-md-4 form-group" style="margin-left: 25px;">
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
                                <#--<a href="javascript:;" class="btn red pull-right" id="addCancel">
                                    取消</a>-->
                            </div>

                        </div>
                        <div class="portlet-body" style="max-height:200px;overflow-y:auto;">
                            <div class="text-center">
                                <table class="table table-bordered table-hover">
                                    <thead>
                                    <tr>
                                        <th class="text-center" id="itemCodeTh">选项编码</th>
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
        <button class="btn btn-success" id="saveProductCategoryAttrib" data-tid="0" value="0">保存</button>
        <a data-dismiss="modal" class="btn btn-inverse" href="#" id="attributeCancel"> 取消</a>
    </div>
<input type="hidden" id="singleOrMuity" value="0"/>
</div>
