<div class="page-bar">
    <ul class="page-breadcrumb">
        <li><a href="${base}">首页</a><i class="fa fa-angle-right"></i></li>
        <li><a href="javascript:;">代理商管理</a><i class="fa fa-angle-right"></i></li>
        <li><span>代理商属性</span></li>
    </ul>
</div>


<div class="portlet light portlet-fit portlet-datatable ">
    <div class="portlet-title">
        <div class="caption">
            <i class="fa fa-table font-green"></i>
            <span class="caption-subject font-green bold uppercase">代理商属性</span>
        </div>

    </div>
    <!-- loading -->
<#--属性组-->
    <div class="col-md-6 col-sm-12" style="border: 1px solid #e7ecf1!important; padding: 20px;">
        <table class="table table-striped table-bordered table-hover table-checkable dataTable no-footer"
               id="attributeGroup">
            <div class="caption" style="min-height: 48px;border-bottom: 1px solid #eef1f5; margin-bottom: 25px">
                <sapn>属性组</sapn>
                <button type="button" class="btn blue btn-sm pull-right" id="addAttributeGroup">
                    <span class="glyphicon glyphicon-plus"></span>添加属性组
                </button>
            </div>
            <tr role="row" class="heading text-center">
                <th class="detail text-center" width="5%">序号
                </th>
                <th class="detail text-center">属性组编码
                </th>
                <th class="detail text-center">属性组名
                </th>
                <th class="detail text-center" width="25%">操作
                </th>
            </tr>
        </table>
    </div>
<#--代理商属性-->
    <div class="col-md-6 col-sm-12 " style="border: 1px solid #e7ecf1!important; padding: 20px;">
        <table class="table table-striped table-bordered table-hover table-checkable dataTable no-footer"
               id="buyerAttributeDef">
            <div class="caption" style="min-height: 48px;border-bottom: 1px solid #eef1f5; margin-bottom: 25px">
                <sapn>代理商属性</sapn>
                <button type="button" class="btn blue btn-sm pull-right" id="addAttribute">
                    <span class="glyphicon glyphicon-plus"></span>添加代理商属性
                </button>
            </div>
            <tr role="row" class="heading">
                <th class="detail text-center" width="30%">属性名
                </th>
                <th class="detail text-center">属性编码
                </th>
                <th class="detail text-center" width="10%">值类型
                </th>
                <th class="detail text-center" width="10%">值长度
                </th>
                <th class="detail text-center" width="15%">是否有选择项
                </th>
                <th class="detail text-center" width="15%">排序值
                </th>
                <th class="detail text-center" width="15%">状态
                </th>

                <th class="detail text-center" width="10%">操作
                </th>
            </tr>
        </table>
    </div>

</div>

<!-- 高级搜索条件浮动层 -->
<div id="AddGroup" class="modal fade" tabindex="-1" data-width="500" data-height="140">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
        <h4 class="modal-title">新增代理商属性组</h4>
    </div>
    <div class="modal-body">
        <form class="form-horizontal">
            <div class="form-group">
                <label class="control-label col-md-3"><span class="required">* </span>属性组编码：</label>
                <div class="col-md-8 controls" about="">
                    <div class="input-icon right">
                        <i class="fa"></i>
                        <input type="text" class="form-control" placeholder="请输入属性组编码" id="groupcode">
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-md-3"><span class="required">* </span>属性组名称：</label>
                <div class="col-md-8 controls" about="">
                    <div class="input-icon right">
                        <i class="fa"></i>
                        <input type="text" class="form-control" placeholder="请输入属性组名称" id="groupname">
                    </div>
                </div>
            </div>
        </form>

    </div>
    <div class="modal-footer">
        <button type="submit" class="btn btn-success" id="savegroup">保存</button>
        <a data-dismiss="modal" class="btn btn-inverse end" href="#">取消</a>
    </div>
</div>
<div id="AddAtribute" class="modal fade" tabindex="-1" data-width="600">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
        <h4 class="modal-title">编辑代理商属性</h4>
    </div>
    <div class="modal-body ">
        <form class="form-horizontal form_completejob">
            <input type="hidden" id="buyerValue" name="buyerValue" value="1">
            <div class="form-group">
                <label class="control-label col-md-3"><span class="required">* </span>属性编码:</label>
                <div class="controls col-md-7">
                    <input type="text" placeholder="编码由字母和下划线组成" id="acode" name="acode" class="form-control"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-md-3"><span class="required">* </span>属性名：</label>
                <div class="col-md-7 controls" about="">
                    <div class="input-icon right">
                        <i class="fa"></i>
                        <input type="text" id="cname" name="cname" class="form-control" placeholder="">
                    </div>
                </div>

            </div>

            <div class="form-group">
                <label class="control-label col-md-3">
                    <span class="required">* </span>属性值类型
                </label>
                <div class="col-md-5" about="">
                    <div class="input-icon right">
                        <i class="fa"></i>
                        <select name="way" id="way" class="form-control">
                            <option value="">--选择值类型--</option>
                            <option value="Int">Int</option>
                            <option value="Float ">Float</option>
                            <option value="Money">Money</option>
                            <option value="Decimal">Decimal</option>
                            <option value="Varchar">Varchar</option>
                            <option value="Nvarchar">Nvarchar</option>
                            <option value="Datetime">Datetime</option>
                        </select>
                    </div>
                </div>
                <div class="controls col-md-2">
                    <input type="text" placeholder="值长度" id="len" class=" form-control number" value="0"/>
                </div>
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
            <div class="form-group">
                <label class="control-label col-md-3">排序值:</label>
                <div class="controls col-md-7">
                    <input type="text" placeholder="请输入排序值" id="defineOrder" name="defineOrder" class="form-control"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-md-3"><span class="required">* </span>是否有值选项:</label>
                <div class="btn-group col-md-7" id="sitem" data-toggle="buttons">
                    <label class="btn red no_item">
                        <input type="radio" name="nameitem" class="toggle" value="0" id="noitem"> 无选项
                    </label>
                    <label class="btn green has_item">
                        <input type="radio" name="nameitem" class="toggle" value="1" id="existitem"> 有选项
                    </label>
                </div>
            </div>
            <div class="form-group" id="attribute_state">
                <label class="control-label col-md-3"><span class="required">* </span>状态</label>
                <div class="btn-group col-md-7" id="state" data-toggle="buttons">
                    <label class="btn btn-default " id="nostate">
                        <input type="radio" name="rstate" class="toggle" value="0"> 无效
                    </label>
                    <label class="btn btn-default statedefault" id="hastate">
                        <input type="radio" name="rstate" class="toggle" value="1"> 有效
                    </label>
                </div>
            </div>
            <div class="form-group" id="itemcontent" style="display: none">
                <div class="row">
                    <div class="portlet light ">
                        <div class="portlet-title col-md-12">
                            <div class="controls col-md-3">
                                <input type="text" placeholder="选项编码" id="icode" class="form-control"/>
                            </div>
                            <div class="controls col-md-4">
                                <input type="text" placeholder="选项名" id="iname" class="form-control"/>
                            </div>


                            <div class="controls col-md-3" data-toggle="buttons" id="itemState">
                                <label class="btn btn-default " id="state_no">
                                    <input type="radio" name="itemstate" class="toggle" value="0"> 失效
                                </label>
                                <label class="btn btn-default statedefault" id="state_has">
                                    <input type="radio" name="itemstate" class="toggle" value="1"> 正常
                                </label>
                            </div>
                            <div class="controls col-md-2 pull-right" style="text-align: right">
                                <a href="javascript:;" class="btn blue btnAddAttribute">
                                    <i class="fa fa-plus"></i> 添加 </a>
                            </div>


                        </div>
                        <div class="portlet-body" style="max-height:200px;overflow-y:auto; ">
                            <input type="hidden" id="itemValue" name="buyerValue" value="0">
                            <div class="table-scrollable">
                                <table class="table table-bordered table-hover">
                                    <thead>
                                    <tr>
                                        <th class="text-center">选项编码</th>
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
        </form>

    </div>
    <div class="modal-footer">
        <button type="submit" class="btn btn-success" id="savetype" data-tid="0">保存</button>
        <a data-dismiss="modal" class="btn btn-inverse end" href="#">取消</a>
    </div>

</div>
<div id="connect" class="modal fade" tabindex="-1" data-width="500" data-height="350">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
        <h4 class="modal-title">属性关联</h4>
    </div>

    <div class="modal-body">
        <div class="form-horizontal">
            <input type="hidden" id="connectValue" name="buyerValue" value="1">
            <div class="form-group">
                <label class="col-md-3 control-label">属性组名:</label>
                <input type="hidden" id="buyerAttributeGroupId" value="">
                <div class="col-md-8">
                    <input type="text" class="form-control relationgroupname" placeholder="属性组名" readonly>
                </div>
            </div>
            <div class="form-group">
                <div class="" id="attribute_list" style="width:350px;margin:0 auto; margin-top: 10px;">

                </div>
            </div>

        </div>
    </div>
    <div class="modal-footer">
        <button type="submit" class="btn btn-success" id="savegroup" onclick="attributeRelevant()">保存</button>
        <a data-dismiss="modal" class="btn btn-inverse end" href="#">取消</a>
    </div>
</div>
<div class="clearfix"></div>

</div>