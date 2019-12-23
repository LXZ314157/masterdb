<!-- BEGIN PAGE BAR -->
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li><a href="${base}">${home!"首页"}</a><i class="fa fa-angle-right"></i></li>
        <li><a href="javascript:;">公共数据管理</a><i class="fa fa-angle-right"></i></li>
        <li><span>汇率信息列表</span></li>
    </ul>
</div>

<div class="portlet light portlet-fit">

    <div class="portlet-body">

        <div class="row" style="margin-top: 25px;">
            <div class=" col-md-12">
                <div class="portlet light bordered">
                    <div class="portlet-title">
                        <div class="caption">
                            <i class="icon-bar-chart font-dark"></i>
                            <span class="caption-subject font-dark bold uppercase">汇率列表</span>
                        </div>
                    </div>
                    <div class="portlet-body">
                        <div class="table-container">
                            <table class="table table-striped table-bordered table-hover table-checkable" id="rateList">
                                <thead>
                                <tr role="row" class="heading">
                                    <th>${exec!"操作"}</th>
                                    <th>${fromExchangeRate!"币种从"}</th>
                                    <th>${toExchangeRate!"币种至"}</th>
                                    <th>${exchangeRateDate!"汇率日期"}</th>
                                    <th>${conversionEndDate!"结束日期"}</th>
                                    <th>${exchangeRate!"汇率"}</th>
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
</div>

<div id="addConversionRate" class="modal fade" tabindex="-1" data-width="500" data-height="250">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
        <h4 class="modal-title">编辑币种</h4>
    </div>

    <div class="modal-body">
        <div class="form-group col-md-12">
            <label class="control-label col-md-3 text-right">
                <span class="required">* </span>
            ${fromExchangeRate!"币种从"}:
            </label>
            <div class="col-md-8">
                <input type="hidden" id="code">
                <div class="input-icon right">
                    <i class="fa"></i>
                    <select name="way" id="fromCurrency" class="form-control">
                        <option value="0">--${chose!"请选择"}--</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="form-group col-md-12">
            <label class="control-label col-md-3 text-right">
                <span class="required">* </span>${toExchangeRate!"币种至"}:
            </label>
            <div class="col-md-8">
                <div class="input-icon right">
                    <i class="fa"></i>
                    <select name="way" id="toCurrency" class="form-control">
                        <option value="0">--${chose!"请选择"}--</option>
                    </select>
                </div>
            </div>
        </div>

        <div class="form-group col-md-12">
            <label class="control-label col-md-3 text-right">
                <span class="required">* </span>${exchangeRateDate!"汇率日期"}:
            </label>
            <div class="col-md-8">
                <div class="input-icon right">
                    <i class="fa"></i>
                    <input type="text" name="conversionDate" id="conversionDate"
                           class="form-control form-control-inline date-picker-fromnow" readonly="readonly"
                           placeholder="(点击选择时间)">
                    <input type="hidden"/>
                </div>
            </div>
        </div>

        <div class="form-group col-md-12">
            <label class="control-label col-md-3 text-right">
                <span class="required">* </span>${conversionEndDate!"结束日期"}:
            </label>
            <div class="col-md-8">
                <div class="input-icon right">
                    <i class="fa"></i>
                    <input type="text" name="conversionEndDate" id="conversionEndDate"
                           class="form-control form-control-inline date-picker-fromnow"
                           placeholder="(点击选择时间)">
                    <input type="hidden"/>
                </div>
            </div>
        </div>


        <div class="form-group col-md-12">
            <label class="control-label col-md-3 text-right">
                <span class="required">* </span>${exchangeRate!"汇率"}:
            </label>
            <div class="col-md-8">
                <div class="input-icon right">
                    <i class="fa"></i>
                    <input type="text" class="form-control" placeholder="" id="conRate"/>
                    <input type="hidden"/>
                </div>
            </div>
        </div>
    </div>
    <div class="modal-footer">
        <button type="button" class="btn btn-success" onclick="rateSave()" id="rateSave" value="1">${save!"保存"}</button>
        <a data-dismiss="modal" class="btn btn-inverse" href="#">${cancel!"取消"}</a>
    </div>
</div>

