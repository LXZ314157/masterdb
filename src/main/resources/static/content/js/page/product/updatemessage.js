var list = [];
var productCode = commonClass.getQueryString("productCode").toUpperCase();
var productCategoryCode = commonClass.getQueryString("productCategoryCode").toUpperCase();
var color = commonClass.getQueryChineseString("color");
var id = commonClass.getQueryChineseString("id");
$(function () {
    $("#productcode").val(productCode);
    loadskudata();
    if(color!=null && color.length>0){
        $("#color").val(color);
    }else{
        $.ajax({
            type: "POST",
            url: webroot + "/product/sizebyproductcode?id=" + id,
            success: function (response) {
                if (response.code == 200) {
                    var color = response.message;
                    if (color != null && color.length != 0) {
                        $("#color").val(color);
                    }
                }
            }
        });
    }

    $("#submit").click(function () {
        var productcode = commonClass.replaceAllQuotationMarks($("#productcode").val()).toUpperCase();
        var color = commonClass.replaceAllQuotationMarks($("#color").val());
        var size = $("#size").val();
        var sku = $("#sku").val();
        var batch = commonClass.replaceAllQuotationMarks($("#batch").val());
        var standardPrice = commonClass.replaceAllQuotationMarks($("#standardPrice").val());
        var standardCost = commonClass.replaceAllQuotationMarks($("#standardCost").val());
        var saleCost = commonClass.replaceAllQuotationMarks($("#saleCost").val());
        var securityCode = commonClass.replaceAllQuotationMarks($("#securityCode").val());
        if (checkProductCode(productCode)) {
            return false;
        }
        if (size == null || size.length <= 0) {
            return swal("请选择尺码", "", "warning");
        }
        if (sku == null || sku.length <= 0) {
            return swal("请输入条码", "", "warning");
        }
        var item = [];
        item = {
            "id": id,
            "productCode": productcode,
            "productCategoryCode": productCategoryCode,
            "color": color,
            "size": size,
            "sku": sku,
            "batch": batch,
            "standardPrice": standardPrice,
            "standardCost": standardCost,
            "saleCost": saleCost,
            "securityCode": securityCode
        }
        insertData(item)
    });
    $("#cancle").click(function () {
        window.location.href = "/product/sizebyproductcode";
    });
    $("#cancel").click(function () {
        $("#color").val("");
        $("#standardPrice").val("");
        $("#standardCost").val("");
        $("#saleCost").val("");
        $("#securityCode").val("");
        $("#sku").val("");
        $("#sku").removeAttr("disabled");
        $("#size").val("").select2();
        $("#batch").val("").select2();
    });
});


function insertData(item) {
    $('#loading').modal('show');
    var message = id ==0?"添加成功":"修改成功";
    $.ajax({
        type: "PUT",
        url: webroot + "/product/insertsize",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(item),
        success: function (response) {
            if (response.code == 200) {
                swal({
                        title: message,
                        text: "",
                        type: "success",
                        showCancelButton: false,
                        confirmButtonClass: "btn-success",
                        confirmButtonText: "确定",
                        closeOnConfirm: true
                    },
                    function () {
                        window.location.reload(true);
                        $("#color").val("");
                        $("#standardPrice").val("");
                        $("#standardCost").val("");
                        $("#saleCost").val("");
                        $("#securityCode").val("");
                        $("#sku").val("");
                        $("#sku").removeAttr("disabled");
                        $("#size").val("").select2();
                        $("#batch").val("").select2();
                    });
            }
            else {
                swal(response.message, "", "error");
            }
        },
        beforeSend: function (XMLHttpRequest) {
            $('#loading').modal('show');
            $("#submit").attr("disabled", "disabled");
        },
        complete: function (XMLHttpRequest, textStatus) {
            $("#submit").removeAttr("disabled");
            $('#loading').modal('hide');
        },
        error: function () {
            //程序出现未知错误，请稍候重试
            swal(ICICLELangUtil.getText("", 1035), "", "error");
            $('#loading').modal('hide');
        }
    });
}

function checkProductCode(productCode) {
    if (productcode == null || productcode.length <= 0) {
        swal("款号获取错误!", "", "warning");
        return false;
    }
    $.ajax({
        type: "POST",
        url: webroot + "/product/checkproductcode",
        data: {"productCode": productCode},
        success: function (response) {
            if (response.code == 200) {
                var bacthList = response.data;
                if(bacthList!=null && bacthList.length>0){
                    for(var i = 0; i<bacthList.length;i++){
                        var batch = bacthList[i];
                        var html = "<option value='" + batch.item_key + "'>" + batch.item_value + "</option>";
                        $("#batch").append(html);
                    }
                }
                return true;
            }
            else {
                swal("款号获取错误!", "", "warning");
                return false;
            }

        }
    });
}

function clearNoNum(obj){
    obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符  
    obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的  
    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
    obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d\d\d).*$/,'$1$2.$3');//只能输入两个小数  
    if(obj.value.indexOf(".")< 0 && obj.value !=""){//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额 
        obj.value= parseFloat(obj.value);
    }
}


function loadskudata() {
    $.ajax({
        method: "GET",
        dataType: "json",
        data: {
          "id": id,
          "productCode": productCode,
          "productCategoryCode": productCategoryCode
        },
        url:webroot + "/product/getskudata",
        success:function(response){
            var batchlist = response.data.batchList;
            var sizeList = response.data.sizeList;
            var productInfo = response.data.productInfo;

            if(batchlist!=null && batchlist.length>0){
                for(var i = 0; i< batchlist.length; i++){
                    var batch = batchlist[i];
                    var html = "<option value='"+batch.item_key+"'>"+batch.item_value+"</option>";
                    $("#batch").append(html);
                }
            };
            if(sizeList!=null && sizeList.length>0){
                if(sizeList.length == 1){
                    var size = sizeList[0];
                    var html = "<option value='"+size.size_code+"'>"+size.size_code+"</option>";
                    $("#size").append(html);
                    if(size.size_code!=""){
                        $("#size").val(size.size_code).select2();
                    }
                    $("#size").attr("disabled","disabled");
                }else{
                    for(var i = 0; i< sizeList.length; i++){
                        var size = sizeList[i];
                        var html = "<option value='"+size.size_code+"'>"+size.size_code+"</option>";
                        $("#size").append(html);
                    }
                }
            }

            if(productInfo!=null){
                $("#sku").val(productInfo.sku);
                $("#standardPrice").val(productInfo.standardPrice);
                $("#standardCost").val(productInfo.standardCost);
                $("#saleCost").val(productInfo.saleCost);
                $("#securityCode").val(productInfo.securityCode);
                $("#size").val(productInfo.size).select2();
                $("#batch").val(productInfo.batch).select2();
                $("#productcode").removeAttr("disabled");
            }
        },
        error:function (response) {
            return swal(response.message, "", "error");
        }

    });
}
