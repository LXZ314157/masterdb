var table;
var currencyList = [];
var TableDatatablesButtons = function () {
    var initDataTable = function () {
        table = $('#rateList');
        var oTable = table.dataTable({
            buttons: [
                {extend: '', className: 'btn green btn-outline btnAddRate', text: '新增汇率'}
            ],
            "fnServerParams": function (aoData) {
                AddParams(aoData);
            },
            "processing": false,
            "language": {
                "search": "搜索币种：",
                "sInfoFiltered": ""
            },
            "bSort": true,
            "order": [[3, "desc"]],
            "aoColumnDefs": [{
                'aTargets': [0, 1, 2],
                'bSortable': false
            }],
            "ajaxSource": webroot + "/unitofmeasure/listexchangerate",
            'aoColumns': [
                {
                    "mData": "productCode",
                    "sClass": "detail",
                    "bSortable": false,
                    "mRender": function (data, type, row) {
                        var entiy = JSON.stringify(row);
                        var html = "<a href='javascript:;' class='btn btn-xs green btn-outline' rel='tooltip' title='编辑' data-entiy='" + entiy + "' onclick='editConversionRate(this)'><i class='fa fa-pencil'></i></a>";
                        return html;
                    }
                },
                {"mData": "fromCurrency", "sClass": "detail"},
                {"mData": "toCurrency", "sClass": "detail"},
                {"mData": "conversionDate", "sClass": "detail"},
                {"mData": "conversionEndDate", "sClass": "detail"},
                {"mData": "conversionRate", "sClass": "detail"}

            ],
            "fnDrawCallback": function () {
                $('#loading').modal('hide');
            }
        });
    }
    return {
        init: function () {
            if (!jQuery().dataTable) {
                return;
            }
            initDataTable();
        }
    };

    function AddParams(aoData) {
        $('#loading').modal('show');
    }
}();
$(function () {
    TableDatatablesButtons.init();
    $.ajax({
        type: "POST",
        url: webroot + "/unitofmeasure/getmesaureandcurrency",
        success: function (response) {
            if (response.code == 200) {
                currencyList = response.data.currencyVOList;
                loadCurrency();
            }
            else {
                //失败
                swal(ICICLELangUtil.getText("", 1148), response.message, "error");

            }
        }
    });
    $(".btnAddRate").click(function () {
        $("#rateSave").val(1);//新增
        //新增汇率
        $(".modal-title").html(ICICLELangUtil.getText("", 1435));
        $("#fromCurrency").val(0);
        $("#toCurrency").val(0);
        $("#conversionDate").val(commonClass.getDateString(new Date()));
        $("#conversionDate").attr("disabled", "disabled");
        $("#fromCurrency").removeAttr("disabled");
        $("#toCurrency").removeAttr("disabled");
        $("#conRate").val('');
        $("#addConversionRate").modal('show');
    });
});

function rateSave() {
    var requestType;
    var requestData = {};
    var requestUrl;
    requestData = {
        fromCurrency: commonClass.replaceAllQuotationMarks($("#fromCurrency").val()),
        toCurrency: commonClass.replaceAllQuotationMarks($("#toCurrency").val()),
        conversionDate: commonClass.replaceAllQuotationMarks($("#conversionDate").val()),
        conversionEndDate: commonClass.replaceAllQuotationMarks($("#conversionEndDate").val()),
        conversionRate: commonClass.replaceAllQuotationMarks($.trim($("#conRate").val()))
    };
    if ($("#rateSave").val() == 1) {
        requestUrl = webroot + "/unitofmeasure/addconversion";
        requestType = "POST";
    } else {
        requestUrl = webroot + "/unitofmeasure/updateconversionrate";
        requestData["id"] = commonClass.replaceAllQuotationMarks($("#code").val());
        requestType = "PUT";
    }

    if (requestData.fromCurrency == 0
        || requestData.toCurrency == 0
        || requestData.conversionDate.length == 0
        || requestData.conversionEndDate.length == 0
        || requestData.conversionRate.length == 0) {

        //"警告", "数据未填写完整，无法提交！"
        swal(ICICLELangUtil.getText("", 1151), ICICLELangUtil.getText("", 1099), "warning");
        return;
    }
    if (requestData.fromCurrency == requestData.toCurrency) {
        //"转换币种不能相同！"
        swal(ICICLELangUtil.getText("", 1151), ICICLELangUtil.getText("", 1435), "warning");
        return;
    }
    var match = /^[0-9]+\.{0,1}[0-9]{0,2}$/;

    if (!isCurrency(requestData.conversionRate)) {
        //"汇率输入必须为小数或整数，且长度受限,请重新填写后提交"
        swal(ICICLELangUtil.getText("", 1151), ICICLELangUtil.getText("", 1438), "warning");
        return;
    }
    $.ajax({
        url: requestUrl,
        type: requestType,
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(requestData),
        success: function (response) {
            if (response.code == 200) {
                var message = response.message == null?"":response.message;
                swal({
                        //成功
                        title: ICICLELangUtil.getText("", 1005),
                        text: message,
                        type: "success",
                        showCancelButton: false,
                        confirmButtonClass: "btn-success",
                        //确定
                        confirmButtonText: ICICLELangUtil.getText("", 1007),
                        closeOnConfirm: true
                    },
                    function () {
                        $("#addConversionRate").modal("hide");
                        table.fnDraw();
                    });
            }
            else {
                swal(response.message, "", "error");
            }
        },
        beforeSend: function (XMLHttpRequest) {
            $("#rateSave").attr("disabled", "disabled");
            $('#loading').modal('show');
        },
        complete: function (XMLHttpRequest, textStatus) {
            $("#rateSave").removeAttr("disabled");
            $('#loading').modal('hide');
        }
    });


}

function isCurrency(rate) {
    rate = $.trim(rate);
    // var p = /^[1-9](\d+(\.\d{1,2})?)?$/;
    // var p1 = /^[0-9]+(\.\d{1,8})?$/;
    var p2 = /^[0-9]{1,10}([.][0-9]{1,8})?$/;
    return p2.test(rate);
//|| p1.test(rate)
}

function editConversionRate(curr) {
    var entiy = $(curr).data("entiy");
    $("#rateSave").val(0);//代表更新操作
    //编辑汇率
    $(".modal-title").html(ICICLELangUtil.getText("", 1440));
    $("#code").val(entiy.id);
    $("#fromCurrency").val(entiy.fromCurrency);
    $("#toCurrency").val(entiy.toCurrency);
    $("#conversionDate").val(entiy.conversionDate);
    $("#conRate").val(entiy.conversionRate);
    $("#fromCurrency").attr("disabled", "disabled");
    $("#toCurrency").attr("disabled", "disabled");
    $("#conversionDate").removeAttr("disabled");
    $("#addConversionRate").modal('show');
}

function loadCurrency() {
    for (var i = 0; i < currencyList.length; i++) {
        var currency = currencyList[i];
        if (currency.status == 1) {
            var htmls = "<option value='" + currency.currencyCode + "'>" + currency.currencyCode + "</option>";
            $("#fromCurrency").append(htmls);
            $("#toCurrency").append(htmls);
        }
    }
}
