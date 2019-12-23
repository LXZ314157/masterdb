/**
 * Created by liurenhua on 2017/11/15.
 */
var smallImageList = [];
var code = commonClass.getQueryString("code");
var imageType = commonClass.getQueryString("imageType");
$(function () {
    ajaxSmallImage(code, imageType);
    init();
    $("#commitUpdate").click(function () {
        updateProductImage();
    });

    $.ajax({
        type: "GET",
        url: webroot + "/image/getimgnotdefault",
        data: {"code": code},
        contentType: "application/json;charset=utf-8",
        success: function (response) {
            if (response.code == 200) {
                imageList = response.data;
                if (imageList != null && imageList.length != 0) {
                    loadSubImage(imageList);
                    $('.vmcarousel-centered-infitine').vmcarousel({
                        centered: false,
                        start_item: 0,
                        autoplay: false,
                        infinite: false
                    });
                }

            }
            else {
                $("#subImg").hide();
            }
        }
    });
});

init = function () {
    $("#imageCode").html(code);
    $.ajax({
        type: 'get',
        url: webroot + "/image/getimagebyid?code=" + code,
        success: function (response) {
            if (response.code = 200) {
                $("#imageCenter").html("");

                var leftImage = response.data;
                if (leftImage == null) {
                    return;
                }
                var html = loadImage(leftImage, false);
                $("#left").html(html);
            } else {
                swal("失败", response.message, "error");
            }
        },
        error: function () {
            swal("失败", "您的网络似乎出了一点问题", "error");
        }
    });
}

/**
 *
 * @param image
 * @param small 是否加载小图
 */
function loadImage(image, small) {

    var html = "";
    var href = webroot + "/product/listproductimage?code=" + image.imageName + "&imageType=" + image.imageType;
    if (small) {
        html += "<div class='col-lg-4 col-md-5 col-sm-6 col-xs-6'>";
    }
    html += "<div class='mt-card-item'>";
    html += "<div class='mt-card-avatar mt-overlay-1'>";
    var imgSrc;
    if (small) {
        imgSrc = imageUrl + "thumb/" + image.imageUrl + "?v=" + new Date().getTime();
    } else {
        imgSrc = imageUrl + "normal/" + image.imageUrl + "?v=" + new Date().getTime();
    }
    html += "<img alt='图片不存在' style=' width:auto;height:auto;max-width:100%;max-height:100%;' src='" + imgSrc + "'/>";
    html += " <div class='mt-overlay' style='cursor: pointer;' onclick='javascript:location.href =\"" + href + "\"'>";
    html += "</div></div><div class='mt-card-content'>";
    html += "<h3 class='mt-card-name'>" + image.imageName + "</h3>";
    html += "<div class='mt-card-social'>"
    html += "<ul><li><a href='javascript:;' data-image='" + JSON.stringify(image) + "' data-name='" + image.imageName + "' onclick='showUpdateArea(this)'><i class='icon-pencil'></i></a></li>"
    html += "<li><a href='javascript:;'  data-id='" + image.imageId + "'  onclick='deleteImage(this)'><i class='glyphicon glyphicon-trash red'></i></a></li></ul>"

    html += " </div></div></div>";
    if (small) {
        html += "</div>";
    }
    return html;
}

/**
 *
 * @param code 编码
 * @param isSmall 是否是小图
 */
function ajaxSmallImage(code, imageType) {

    if (code == null || code == undefined) {
        return;
    }

    $.ajax({
        type: 'post',
        data: {
            "code": code,
            "imageType": imageType
        },
        url: webroot + "/image/findimagebycode",
        success: function (response) {
            if (response.code = 200) {
                $("#right").html("");
                smallImageList = response.data;
                if (smallImageList == null) {
                    return;
                }
                for (var i = 0; i < smallImageList.length; i++) {
                    var image = smallImageList[i];
                    if (image == null) {
                        continue;
                    }
                    var html = loadImage(image, true);
                    $("#right").append(html);
                }
            } else {
                swal("失败", response.message, "error");
            }
        },
        error: function () {
            swal("失败", "您的网络似乎出了一点问题", "error");
        }
    });
}

function loadSubImage(imageList) {
    var html = '';
    for (var i = 0; i < imageList.length; i++) {
        var message = imageList[i];
        if (message.imageUrl != null && message.imageUrl.length != 0) {
            var index = message.imageUrl.lastIndexOf('/');
            var name = message.imageUrl.substring(index + 1, message.imageUrl.length - 4)
            html += " <li data-slide='" + i + "'>"
            html += "<img class=\"linkImg \" src='" + imageUrl + 'normal/' + message.imageUrl + "?v" + new Date().getTime() + "'  width=\"330\">"
            html += "<h3 class='mt-card-name' style='    font-size: 16px;font-weight: 600;text-align: center;'>" + name + "</h3>";
            html += "<div class='center' style='text-align: center; margin: 10px;'>"
            html += "<label data-image='" + JSON.stringify(message) + "' data-name='" + name + "' onclick='showUpdateArea(this)'><i class='icon-pencil'></i></label>"
            html += "<label   data-id='" + message.imageId + "'  onclick='deleteImage(this)'><i class='glyphicon glyphicon-trash red'></i></label></div></li>"
        }
        else {
            html += " <li data-slide='" + i + "'>"
            html += "<img class=\"linkImg \" src='" + webroot + "/static/content/img/imgrecommand.jpg'  width=\"330\" ></li>"
        }
    }
    $("#imageContent").append(html);
}

//看大图
function showDetail(curr) {
    var imageName = $(curr).data('name');
    $(".item").removeClass("active");
    $("#" + imageName).addClass("active");
    $("#bigImage").modal('show');
}

function deleteImage(curr) {
    var id = $(curr).data('id');

    if (id == null || id == 'undefined') {
        swal("警告", "无法删除", "warning");
        return;
    }

    swal({
            title: "删除后无法恢复，您确定删除此图片?",
            text: "",
            type: "warning",
            showCancelButton: true,
            cancelButtonText: "取消",
            confirmButtonClass: "btn-danger",
            confirmButtonText: "确定",
            closeOnConfirm: false
        },
        function () {
            $.ajax({
                type: "DELETE",
                url: webroot + "/image/deleteimage/" + id,
                success: function (response) {
                    if (response.code == 200) {
                        swal({
                                title: "删除成功",
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
                        swal("删除失败!", response.message, "error");
                    }
                },
                error: function () {
                    swal("失败", "您的网络似乎断开了", "error");
                }
            });
        });
}

function showUpdateArea(curr) {
    var image = $(curr).data("image");
    var name = $(curr).data("name");
    $("#imageId").val(image.imageId);
    $("#imageName").val(image.imageName);
    $("#imageType").val(image.imageType);
    $("#imageUrl").val(image.imageUrl);
    $("#selectImage").attr("src", imageUrl + "thumb/" + image.imageUrl + "?v=" + new Date().getTime());
    $("#hCode").html(name);
    $("#updateArea").modal('show');
}

//如果图片上传错误，在此处更新图片
function updateProductImage() {

    var formdata = new FormData(document.getElementById("imageForm"));

    if (jQuery("input[type='file']").val() == "") {
        swal("警告", "您还没有选择文件", "warning");
        return;
    }

    $.ajax({
        type: "post",
        url: webroot + "/image/updateimage",
        async: false,
        data: formdata,
        processData: false,
        contentType: false,
        success: function (response) {
            if (response.code == 200) {
                swal({
                        title: "图片更新成功",
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
        },
        beforeSend: function (XMLHttpRequest) {
            $('#loading').modal('show');
            $("#commit").attr("disabled", "disabled");
        },
        complete: function (XMLHttpRequest, textStatus) {
            $("#commit").removeAttr("disabled");
            $('#loading').modal('hide');
        }
    })
}

