var productCode = commonClass.getQueryString("productCode").toUpperCase();
var featureSimpleList = [];
var imageurl;
$(function () {
    //编辑器初始化
    UIExtendedModals.init();
    //标签初始化事件
    var elt = $('.test');
    elt.tagsinput({
        itemValue: 'value',
        itemText: 'text',
    });
    $("#productCode").val(productCode);
    $("#productCode").attr("disabled", "disabled");
    //加载页面数据
    editFeatureDetial();
    ajaxImage();
    //保存事件
    $("#saveFeature").click(function () {
        var feature = UE.getEditor('ueditor_input').getContent();
        var recommondCodeList = $("#styleRecommend").val();
        var productCode = $("#productCode").val();
        var language = "zhs";
        var requestData = [];
        var codeList = new Array();
        var url = webroot + "/product/stylefeature";
        $.ajax({
                type: "POST",
                url: webroot + "/product/checkproductcode",
                data: {"productCode": productCode},
                success: function (response) {
                    if (response.code == 200) {
                        requestData = {
                            "productCode": productCode,
                            "productFeature": feature,
                            "language": language
                        }
                        if (recommondCodeList != null && recommondCodeList != "") {
                            var str = new Array();
                            str = recommondCodeList.split(",");
                            for (var i = 0; i < str.length; i++) {
                                if (codeList != null && codeList.indexOf(str[i]) >= 0) {
                                    continue;//去除重复
                                }
                                codeList.push(str[i]);
                            }
                            if (codeList.length > 20) {
                                swal("替代款添加最多为20项！", "", "warning");
                                return;
                            }
                            var productRecommand;
                            productRecommand = codeList.join(',')
                            requestData["productRecommend"] = productRecommand;
                        }
                        if ((recommondCodeList != null && recommondCodeList != "") || (feature != null && feature != "")) {
                            savaAjax(url, requestData);
                        }
                        else {
                            swal("未填写任何数据", "", "warning");
                        }
                    }
                    else {
                        swal("该款号不存在，请勿随意更改数据", "", "warning");
                    }
                }
            }
        );


    })
    //图片放大显示事件
    $("#productImage").click(function () {
        $("#enlarge").modal("show");
        var src = imageUrl + 'normal/' + imageurl + "?v" + new Date().getTime();
        $("#largeimg").attr("src", src);
    })
    //款号添加触发事件
    $("#state_tagsinput_style").keyup(function () {
        if (event.keyCode == "13") {//keyCode=13是回车键
            var productRecommend = commonClass.replaceAllQuotationMarks($("#state_tagsinput_style").val()).toUpperCase();
            if (productRecommend.indexOf(productCode) >= 0) {
                swal("替代款不能为原款，请重新输入！", "", "error");
                return;
            }
            else {
                $.ajax({
                    type: "POST",
                    url: webroot + "/product/checkproductcode",
                    data: {"productCode": productRecommend},
                    success: function (response) {
                        if (response.code == 200) {
                            elt.tagsinput('add', {
                                "value": productRecommend,
                                "text": productRecommend,
                                "continent": productRecommend
                            });
                        }
                        else {
                            swal(response.message, "", "error");
                        }
                    }
                });
                $('#state_tagsinput_style').val("");
            }
        }
    });

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
                        title: "添加成功！",
                        text: "",
                        type: "success",
                        showCancelButton: false,
                        confirmButtonClass: "btn-success",
                        confirmButtonText: "确定",
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

function editFeatureDetial() {
    $.ajax({
        type: "POST",
        url: webroot + "/product/featurefinddetail?productCode=" + productCode + "",
        contentType: "application/json;charset=utf-8",
        success: function (response) {
            if (response.code == 200) {
                featureSimpleList = response.data;
                loadData(featureSimpleList);
                loadTag(featureSimpleList);
            }
        }
    });


}

function ajaxImage() {
    $.ajax({
        type: "POST",
        url: webroot + "/product/featureimagefind?imageName=" + productCode + "",
        contentType: "application/json;charset=utf-8",
        success: function (response) {
            if (response.code == 200) {
                imageurl = response.message;
                loadImage(imageurl);
            }
            else {
                $("#proImage").css("display", "none");
            }
        }
    });
}

function loadData(featureSimpleList) {
    var html = featureSimpleList.productFeature;
    console.log(html);
    var ue = UE.getEditor('ueditor_input');
    ue.ready(function () {
        ue.setContent(html);
    });

}

function loadImage(imageurl) {
    if (imageurl != null) {
        $("#proImage").css("display", "block");
        var src = imageUrl + 'normal/' + imageurl + "?v" + new Date().getTime();
        $("#productImage").attr("src", src);
    }
    else {
        $("#proImage").css("display", "none");
    }

}

function loadTag(featureSimpleList) {
    var tags = featureSimpleList.productRecommend;
    if (tags != null && tags != "") {
        var str = new Array();
        str = tags.split(",");
        var elt = $('.test');
        for (var i = 0; i < str.length; i++) {
            var value = str[i];
            elt.tagsinput({
                itemValue: 'value',
                itemText: 'text',
            });
            elt.tagsinput('add', {
                "value": value,
                "text": value,
                "continent": value
            });
        }
    }

}