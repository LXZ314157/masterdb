var featureDetailList = [];
var imageList = [];
var productCode = commonClass.getQueryString("productCode");
$(function () {
//加载页面数据
    $.ajax({
        type: "POST",
        url: webroot + "/product/featurefind?productCode=" + productCode + "",
        contentType: "application/json;charset=utf-8",
        success: function (response) {
            if (response.code == 200) {
                featureDetailList = response.data;
                if (featureDetailList != null) {
                    loadData(featureDetailList);
                }
            }
            else {
                $(".recommandStyle").hide();
            }
        }
    });
//加载推荐款图片信息
    $.ajax({
        type: "POST",
        url: webroot + "/product/recommandimage?productCode=" + productCode + "",
        contentType: "application/json;charset=utf-8",
        success: function (response) {
            if (response.code == 200) {
                imageList = response.data;
                if (imageList != null && imageList.length != 0) {
                    loadImage(imageList);
                    $('.vmcarousel-centered-infitine').vmcarousel({
                        centered: false,
                        start_item: 0,
                        autoplay: false,
                        infinite: false
                    });
                }

            }
            else {
                $(".recommandStyle").hide();
            }
        }
    });

});

function loadData(featureDetailList) {
    $("#productCode").html(featureDetailList.productCode);
    $("#productName").html(featureDetailList.productName);
    var waveAndband = featureDetailList.wave + '-' + featureDetailList.band;
    $("#waveAndBand").html(waveAndband);
    $("#price").html(featureDetailList.unitPrice);
    $("#color").html(featureDetailList.colorName);
    $("#materialName").html(featureDetailList.materialName);
    $("#fabic").html(featureDetailList.fabricFeature);
    $("#styleFeature").html(featureDetailList.productFeature);
    if (featureDetailList.imageUrl != null && featureDetailList.imageUrl.length != 0) {
        var src = imageUrl + 'normal/' + featureDetailList.imageUrl + "?v" + new Date().getTime();
        $("#productSelf").attr("src", src);
    }

}

function loadImage(imageList) {
    var html = '';
    for (var i = 0; i < imageList.length; i++) {
        var message = imageList[i];
        var code = message.productCode;
        if (message.imageUrl != null && message.imageUrl.length != 0) {
            html += " <li data-slide='" + i + "'>"
            html += "<img class=\"linkImg \" src='" + imageUrl + 'normal/' + message.imageUrl + "?v" + new Date().getTime() + "'  width=\"330\" onclick='urlRedict(this)' data-id='" + code + "'></li>"
        }
        else {
            html += " <li data-slide='" + i + "'>"
            html += "<img class=\"linkImg \" src='" + webroot + "/static/content/img/imgrecommand.jpg'  width=\"330\" onclick='urlRedict(this)' data-id='" + code + "'></li>"
        }
    }
    $("#imageContent").append(html);
}


function urlRedict(urr) {
    var productCode = $(urr).data("id");
    location.href = webroot + "/product/productdetail?productCode=" + productCode;
}

