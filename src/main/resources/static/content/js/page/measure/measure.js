/**
 * Created by liurenhua on 2017/11/1.
 */
var currencyList = [];
var measureList = [];
var conversionRateList = [];

$(function () {
    $.ajax({
        type: "POST",
        url: webroot + "/unitofmeasure/getmesaureandcurrency",
        success: function (response) {
            if (response.code == 200) {
                currencyList = response.data.currencyVOList;
                measureList = response.data.unitOfMeasureVOList;
                conversionRateList = response.data.conversionRateVOList;
                loadCurrencyAndMeasure();
            }
            else {
                swal(response.message, "", "error");

            }
        }
    });

    $("#btnAddCurrency").click(function () {
        $("#save").val(1);
        $(".modal-title").html("新增币种");
        $("#id").val("");
        $("#currencyCode").val("");
        $("#currencyCode").removeAttr("disabled");
        $("#currencyName").val("");
        $("#currencyDesc").val("");
        $("#currencyPrecision").val("");
        $("#addArea").modal('show');
        $("#statusArea").hide();
        $("#status_1").addClass("active");
    });

    $("#btnAddRate").click(function () {
        $("#rateSave").val(1);//新增
        $("#fromCurrency").val(0);
        $("#toCurrency").val(0);
        $("#conversionDate").val(commonClass.getDateString(new Date()));
        $("#conversionDate").attr("disabled", "disabled");
        $("#fromCurrency").removeAttr("disabled");
        $("#toCurrency").removeAttr("disabled");
        $("#conRate").val('');
        $("#addConversionRate").modal('show');
    });

    $("#btnAddMeasure").click(function () {
        $("#uomSave").val(1);//新增
        $("#uomCode").val('');
        $("#uomName").val('');
        $("#measurement").val('');
        $("#uomDesc").val('');
        $("#uomCode").removeAttr("disabled");
        $("#uomLanguage").val(0);
        $(".modal-title").html("新增单位");
        $("#measureStatus").hide();
        $("#addUom").modal('show');
    });
});

function saveOrEdit() {
    var requestType;
    var requestData;
    var requestUrl;
    //根据按钮上的值来判断是更新还是插入操作 1 表示插入操作 0 表示更新操作
    if ($("#save").val() == 1) {
        requestUrl = webroot + "/unitofmeasure/addcurrency";
        requestType = "POST";
    } else {
        requestUrl = webroot + "/unitofmeasure/updatecurrency";
        requestType = "PUT";
    }
    requestData = {
        id: commonClass.replaceAllQuotationMarks($("#id").val()),
        currencyCode: commonClass.replaceAllQuotationMarks($("#currencyCode").val()),
        currencyName: commonClass.replaceAllQuotationMarks($("#currencyName").val()),
        currencyDesc: commonClass.replaceAllQuotationMarks($("#currencyDesc").val()),
        currencyPrecision: commonClass.replaceAllQuotationMarks($("#currencyPrecision").val()),
        status: $("#status .active input").val()
    };
    if (requestData.currencyCode.length == 0
        || requestData.currencyName.length == 0
        || requestData.currencyPrecision.length == 0) {
//"数据未填写完整，无法提交！"
        swal(ICICLELangUtil.getText("", 1151), ICICLELangUtil.getText("", 1099), "warning");
        return;
    }
    var reg = /^[a-zA-Z]\w*$/;
    if (!reg.test($("#currencyCode").val())) {
        //币种编码不符合格式要求
        swal(ICICLELangUtil.getText("", 1384), "", "warning");
        return;
    }
    var rex = /^\+?[0-9][0-9]*$/;
    if (!rex.test(requestData.currencyPrecision) || requestData.currencyPrecision.length > 5) {
        //精确度必须为数字，且长度受限，请重新填写后提交
        swal(ICICLELangUtil.getText("", 1151), ICICLELangUtil.getText("", 1428), "warning");
        return;
    }

    $.ajax({
        type: requestType,
        url: requestUrl,
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(requestData),
        success: function (response) {
            var message = response.message==null?"":response.message;
            if (response.code == 200) {
                swal({
                        //成功
                        title: ICICLELangUtil.getText("", 1005),
                        text: message,
                        type: "success",
                        showCancelButton: false,
                        confirmButtonClass: "btn-success",
                        confirmButtonText: ICICLELangUtil.getText("", 1007),
                        closeOnConfirm: true
                    },
                    function () {
                        location.reload();
                    });
            }
            else {
                swal(response.message, "", "error");
            }
        }
    });


}

function uomSave() {
    var requestType;
    var requestData = {};
    var requestUrl;
    requestData = {
        uomCode: commonClass.replaceAllQuotationMarks($("#uomCode").val()),
        unitOfMeasure: commonClass.replaceAllQuotationMarks($("#uomName").val()),
        uomClass: commonClass.replaceAllQuotationMarks($("#measurement").val()),
        uomDescription: commonClass.replaceAllQuotationMarks($("#uomDesc").val())
    };
    if (requestData.uomCode.length == 0
        || requestData.unitOfMeasure.length == 0
    ) {
        //"警告", "数据未填写完整，无法提交！"
        swal(ICICLELangUtil.getText("", 1151), ICICLELangUtil.getText("", 1099), "warning");
        return;
    }

    if ($("#uomCode").val().length >= 3) {
        //单位编码不能过长，请重新输入
        swal(ICICLELangUtil.getText("", 1383), "", "warning");
        return;
    }
    if ($("#uomSave").val() == 1) {
        requestUrl = webroot + "/unitofmeasure/adduom";
        requestType = "POST";
    } else {
        requestUrl = webroot + "/unitofmeasure/updateuom";
        requestData["id"] = commonClass.replaceAllQuotationMarks($("#uomId").val());
        requestData["status"] = commonClass.replaceAllQuotationMarks($("#uomStatus .active input").val());
        if (requestData.status.length == 0) {
            //"警告", "数据未填写完整，无法提交！"
            swal(ICICLELangUtil.getText("", 1151), ICICLELangUtil.getText("", 1099), "warning");
            return;
        }
        requestType = "PUT";
    }
    loadAjax(requestUrl, requestType, requestData);
}

function loadCurrencyAndMeasure() {
    for (var i = 0; i < currencyList.length; i++) {
        var currency = currencyList[i];
        var entiy = JSON.stringify(currency);
        var html = "<tr>";
        html += "<td>" + currency.id + "</td>";
        html += "<td>" + currency.currencyCode + "</td>";
        html += "<td>" + currency.currencyName + "</td>";
        html += "<td>" + currency.currencyDesc + "</td>";
        html += "<td>" + currency.currencyPrecision + "</td>";

        if (currency.status == 1) {
            html += "<td>有效</td>";
        } else {
            html += "<td>无效</td>";
        }
        html += "<td><a href='javascript:;' class='btn btn-xs green btn-outline' rel='tooltip' title='编辑' data-entiy='" + entiy + "' onclick='editCurrency(this)'><i class='fa fa-pencil'></i></a></td>";
        html += "</tr>"
        $("#currency").append(html);


    }
    for (var i = 0; i < measureList.length; i++) {
        var measure = measureList[i];
        var entiy = JSON.stringify(measure);
        var html = "<tr>";
        html += "<td>" + measure.id + "</td>";
        html += "<td>" + measure.uomCode + "</td>";
        html += "<td>" + measure.unitOfMeasure + "</td>";
        html += "<td>" + measure.uomClass + "</td>";
        html += "<td>" + measure.uomDescription + "</td>";
        if (measure.status == 1) {
            html += "<td>有效</td>";
        } else {
            html += "<td>无效</td>";
        }
        html += "<td><a href='javascript:;' class='btn btn-xs green btn-outline' rel='tooltip' title='编辑' data-entiy='" + entiy + "' onclick='editUom(this)'><i class='fa fa-pencil'></i></a></td>";
        html += "</tr>";
        $("#measure").append(html);
    }
}

function loadAjax(requestUrl, requestType, requestData) {
    $.ajax({
        url: requestUrl,
        type: requestType,
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(requestData),
        success: function (response) {
            if (response.code == 200) {
                swal({
                        title: ICICLELangUtil.getText("", 1005),
                        text: "",
                        type: "success",
                        showCancelButton: false,
                        confirmButtonClass: "btn-success",
                        confirmButtonText: ICICLELangUtil.getText("", 1007),
                        closeOnConfirm: true
                    },
                    function () {
                        location.reload();
                    });
            }
            else {
                swal(response.message, "", "error");
            }
        }
    });
}

function editCurrency(curr) {
    var entiy = $(curr).data("entiy");
    $("#id").val(entiy.id);
    $("#save").val(0);
    $(".modal-title").html("编辑币种")
    $("#status_0").removeClass("active");
    $("#status_1").removeClass("active");
    if (entiy.status) {
        $("#status_1").addClass("active");
    } else {
        $("#status_0").addClass("active");
    }

    $("#currencyCode").val(entiy.currencyCode);
    $("#currencyCode").attr("disabled", "disabled");
    $("#currencyName").val(entiy.currencyName);
    $("#currencyDesc").val(entiy.currencyDesc);
    $("#currencyPrecision").val(entiy.currencyPrecision);
    $("#addArea").modal('show');
    $("#statusArea").show();
}


function editUom(curr) {
    var entiy = $(curr).data("entiy");
    $("#measureStatus").show();
    $("#uomSave").val(0);//代表更新操作
    $(".modal-title").html("编辑单位");
    $("#uomId").val(entiy.id);
    $("#uomCode").val(entiy.uomCode);
    $("#uomCode").attr("disabled", "disabled");
    $("#uomName").val(entiy.unitOfMeasure);
    $("#measurement").val(entiy.uomClass);
    $("#uomDesc").val(entiy.uomDescription);
    $("#uomLanguage").val(entiy.language);
    if (entiy.status == 1) {
        $("#hasStatus").addClass("active");
        $("#noStatus").removeClass("active");
    }
    else {
        $("#hasStatus").removeClass("active");
        $("#noStatus").addClass("active");
    }
    $("#addUom").modal('show');
}