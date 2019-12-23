var featureList = [];
var productCode = commonClass.getQueryString("productCode");


$(function () {
    $.ajax({
        url: webroot + "/product/featurefind?productCode=" + productCode + "",
        type: "post",
        contentType: "application/json;charset=utf-8",
        success: function (response) {
            if (response.code == 200) {
                console.log(response.data);
                featureList = response.data;
                loadDetailMessage();
            }
        },
        error: function () {
            swal("数据拉取失败", "", "error");
        }
    });

    commonClass.intiSummerNote();
    $("#productCode").val(productCode);
    $("#productCode").attr("disabled", "disabled");
    $("#submit").click(function () {
        var feature = $('#summernote').summernote('code');
        var recommondCodeList = $("#styleRecommend").val();
        var productCode = $("#productCode").val();
        var language = "zhs";
        if (recommondCodeList.length == 0 && feature.length == 0) {
            swal("您还没有输入任何文本，无法提交", "", "warning");
        }
        var requestData = [];
        var codeList = new Array();
        var url = webroot + "/product/stylefeature";
        if (recommondCodeList != null && recommondCodeList != "") {
            var str = new Array();
            str = recommondCodeList.split(",");
            for (var i = 0; i < str.length; i++) {
                if (codeList != null && codeList.indexOf(str[i]) >= 0) {
                    continue;//去除重复
                }
                codeList.push(str[i]);
            }
            requestData = {
                "productCode": productCode,
                "productFeature": feature,
                "productRecommend": codeList.join(','),
                "language": language
            }
            $.ajax({
                type: "POST",
                url: webroot + "/product/checkproductcode",
                data: {"str": codeList, "productCode": productCode},
                success: function (response) {
                    if (response.code == 200) {
                        savaAjax(url, requestData);
                    }
                    else {
                        swal(response.message, "", "error");
                    }
                }
            });
        }
    });
    $("#demo3").click(function () {
        $("#enlarge").modal("show");
        var src = webroot + "/static/upload/images/" + featureSimpleList.imageUrl;
        $("#largeimg").attr("src", src);
    })
});

function savaAjax(url, requestData) {
    $.ajax({
        type: "POST",
        url: url,
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(requestData),
        success: function (response) {
            if (response.code == 200) {
                swal({
                        title: "添加成功,可前往特点维护界面查看",
                        text: "",
                        type: "success",
                        showCancelButton: false,
                        confirmButtonClass: "btn-success",
                        confirmButtonText: "确定",
                        closeOnConfirm: true
                    },
                    function () {
                        location.reload();
                        // window.location.href = webroot + "/product/productfeature?productCode=" + productCode;
                    });
            }
            else {
                swal(response.message, "", "error");
            }
        }
    });
}


function loadDetailMessage() {
    var markupStr = featureList.productFeature;
    $("#summernote").summernote('code', markupStr);
    $("#styleRecommend").val(featureList.productRecommend);
    var src = webroot + "/static/upload/images/" + featureList.imageUrl;
    $("#demo3").attr("src", src);
}