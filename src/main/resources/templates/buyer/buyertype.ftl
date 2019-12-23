<div class="page-bar">
    <ul class="page-breadcrumb">
        <li><a href="${base}">首页</a><i class="fa fa-angle-right"></i></li>
        <li><a href="javascript:;">代理商管理</a><i class="fa fa-angle-right"></i></li>
        <li><span>代理商级别</span></li>
    </ul>
</div>
<div id="main">
    <div class="row">
        <div class="col-md-12">
            <div class="portlet light portlet-fit portlet-datatable">
                <div class="portlet-title">
                    <div class="caption">
                        <i class="fa fa-table font-green"></i>
                        <span class="caption-subject font-green bold uppercase">代理商级别</span>
                    </div>
                    <button type="button" class="btn blue pull-right" id="newtype">
                        <span class="glyphicon glyphicon-plus"></span>新增代理商级别
                    </button>
                </div>
                <div class="portlet-body" style="padding-bottom: 5px;">
                    <div class="table-container">
                        <table class="table table-striped table-bordered table-hover table-checkable dataTable no-footer"
                               id="">
                            <thead>
                            <tr>
                                <th style="width: 10%;" class="typerow">操作</th>
                                <th style="width: 30%;" class="typerow">代理商级别</th>
                                <th style="width: 40%;" class="typerow">类型说明</th>
                                <th class="typerow">状态</th>
                            </tr>
                            </thead>
                            <tbody id="buyertype">
                            <#list list as item>
                            <tr>
                                <td class="typerow">
                                    <button class="edittype btn btn-xs green btn-outline"
                                            data-levelid="${item.buyerTypeId}"
                                            data-name="${item.buyerTypeName}" data-state="${item.status}"
                                            data-desc="${item.buyerTypeDesc}"><i class="fa fa-pencil"></i></button>
                                </td>
                                <td class="typerow">${item.buyerTypeName}</td>
                                <td class="typerow">${item.buyerTypeDesc}</td>
                                <#if item.status=1>
                                    <td class="typerow">正常</td>
                                <#else>
                                    <td class="typerow">无效</td>
                                </#if>
                            </tr>
                            </#list>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="addType" class="modal fade">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
            <h4 class="modal-title">编辑代理商级别</h4>
        </div>
        <div class="modal-body" style="min-height: 260px;">
            <div class="form-horizontal">
                <div class="form-group col-md-12">
                    <label class="control-label col-md-4" style="line-height: 30px;"><span class="required">* </span>代理商级别:</label>
                    <div class="controls col-md-8">
                        <input type="text" placeholder="代理商级别" id="typename" class="form-control"/>
                    </div>
                </div>
                <div class="form-group col-md-12">
                    <label class="control-label col-md-4">类型说明 :</label>
                    <div class="controls col-md-8">
                        <textarea class="form-control" id="typedesc"></textarea>
                    </div>
                </div>
                <div class="form-group col-md-12">
                    <label class="control-label col-md-4"><span class="required">* </span>状态 :</label>
                    <div class="btn-group col-md-8" id="state" data-toggle="buttons">
                        <label class="btn red ">
                            <input type="radio" class="toggle" value="0"> 失效
                        </label>
                        <label class="btn green active statedefault">
                            <input type="radio" class="toggle" value="1"> 有效
                        </label>
                    </div>
                </div>


            </div>
        </div>
        <div class="modal-footer">
            <button type="submit" class="btn btn-success" id="savetype" data-tid="0">保存</button>
            <a data-dismiss="modal" class="btn btn-inverse" href="#">取消</a>
        </div>
    </div>
    <div id="insertType" class="modal fade">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
            <h4 class="modal-title">新增代理商级别</h4>
        </div>
        <div class="modal-body">
            <div class="form-horizontal">
                <div class="form-group">
                    <label class="control-label col-md-4" style="line-height: 30px;"><span class="required">* </span>代理商级别
                        :</label>
                    <div class="controls col-md-7">
                        <input type="text" placeholder="代理商级别" id="typenameadd" class="form-control"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-4" style="line-height: 30px;"> 类型说明 :</label>
                    <div class="controls col-md-7">
                        <textarea class="form-control" id="typedescadd"></textarea>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <button type="submit" class="btn btn-success" id="inserttype" data-tid="">保存</button>
            <a data-dismiss="modal" class="btn btn-inverse" href="#">取消</a>
        </div>
    </div>
</div>
