/**
 * Created by liurenhua on 2017/10/20.
 */

$(document).ready(function () {

    $.ajax({
        url: webroot + "/store/storetype",
        type: "GET",
        success: function (response) {
            if (response.code == 200) {
                var storeTypeList = response.data;
                for (var i = 0; i < storeTypeList.length; i++) {
                    var storeType = storeTypeList[i];
                    var entiy = commonClass.replaceAllQuotationMarks(JSON.stringify(storeType));
                    var html = "<tr role='row' class='heading'>";
                    html += "<td class='detail text-center' >" + storeType.storeTypeName + "</td>";
                    if (storeType.status == 1)
                        html += "<td class='detail text-center'>有效</td>";
                    else
                        html += "<td class='detail text-center'>无效</td>";

                    html += "<td class='text-center'><a href='javascript:;' data-entiy='" + entiy + "' class='btn btn-xs green btn-outline' rel='tooltip' title='编辑'  onclick='addOrEditStoreType(this);'><i class='fa fa-pencil'></i></a></td></tr>";
                    $("#storeType").append(html);
                }
            } else {
                swal("失败", response.message, "error");
            }
        },
        error: function (response) {
            swal("失败", response.message, "error");
        }
    });

    $.ajax({
        url:  webroot + "/store/levellist",
        type: "POST",
        success: function (response) {
            if (response.code == 200) {
                var list = response.data;
                for (var i = 0; i < list.length; i++) {
                    var storeLevel = list[i];
                    var entiy = commonClass.replaceAllQuotationMarks(JSON.stringify(storeLevel));
                    var html = "<tr role='row' class='heading'>";
                    html += "<td class='detail text-center'>" + storeLevel.storeLevel + "</td>";
                    html += "<td class='detail text-center'>" + storeLevel.storeLevelDesc + "</td>";
                    if (storeLevel.status == 1) {
                        html += "<td class='detail text-center'>有效</td>";
                    } else {
                        html += "<td class='detail text-center'>无效</td>";
                    }
                    html += "<td class='text-center'><a href='javascript:;' class='btn btn-xs green btn-outline' rel='tooltip'  title='编辑' data-entiy='" + entiy + "' onclick='addOrEditLevel(this);'><i class='fa fa-pencil'></i></a></td></tr>";
                    $("#storeLevel").append(html);
                }
            } else {
                swal("失败", response.message, "error");
            }
        },
        error: function (response) {
            swal("失败", response.message, "error");
        }
    });


    $("#addStoreType").click(function () {
        $("#addOrEditStoreType").modal('show');
    });


    $("#saveStoreLevel").click(function () {
        var storeLevelName = commonClass.replaceAllQuotationMarks($('#storeLevelName').val());
        var storeLevelDesc = commonClass.replaceAllQuotationMarks($('#storeLevelDesc').val());
        var url;
        var type;
        var data;
        var status;
        if (storeLevelName == null || storeLevelName.length == 0) {
            swal("店铺级别名不能为空", "", "error");
            return;
        }

        if ($("#storeLevelId").val() == -1) {
            url = webroot + "/store/addstorelevel";
            type = "POST";
            data = {
                "storeLevelName": storeLevelName,
                "storeLevelDesc": storeLevelDesc
            }
        } else {
            status = $("#storeLevelStatus .active input").val();
            console.log(status);
            url = webroot +  "/store/updatestorelevel";
            type = "PUT";
            data = JSON.stringify({
                "storeLevelName": storeLevelName,
                "storeLevelDesc": storeLevelDesc,
                "storeLevelId": $("#storeLevelId").val(),
                "status":status
            });
        }


        $.ajax({
            url: url,
            type: type,
            data: data,
            success: function (response) {
                if (response.code == 200) {
                    swal({
                            title: "成功",
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
                } else {
                    swal("失败", response.message, "error");
                }
            },
            error: function () {
                swal("失败", "网络连接断开，请稍后再试", "error");
            }

        })
    });

    $("#saveStoreType").click(function () {
        var storeTypeName = commonClass.replaceAllQuotationMarks($("#storeTypeName").val());
        var url;
        var type;
        var data;
        var status = $("#storeTypeStatus .active input").val();
        if (storeTypeName == null || storeTypeName.length == 0) {
            swal("提示", "店铺类型名不能为空", "warning");
            return;
        }
        if ($("#storeTypeId").val() == "-1") {
            url = webroot +"/store/addstoretype";
            type = "POST";
            data = {
                "storeTypeName": storeTypeName
            }
        } else {
            url = webroot + "/store/updatestoretype";
            type = "PUT";
            data = JSON.stringify({
                "storeTypeId": $("#storeTypeId").val(),
                "storeTypeName": storeTypeName,
                "storeTypeStatus": $("#status").val(),
                "status":status
            })
        }
        $.ajax({
            url: url,
            type: type,
            data: data,
            success: function (response) {
                if (response.code == 200) {
                    swal({
                            title: "成功",
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
                } else {
                    swal("失败", response.message, "error");
                }
            },
            error: function () {
                ICICLELangUtil.getText(language,-999);
                swal("失败", "网络连接断开，请稍后再试", "error");
            }
        })
    });

});


//编辑属性
function addOrEditLevel(cur) {
    //storeLevelId为1表示新增 0表示更新
    if (cur == null) {
        $("#storeLevelTitle").html("新增店铺级别");
        $("#addOrEditLevel").modal("show");
        $("#storelevelStatusArea").hide();
        $("#storeLevelId").val("-1");
        $("#storeLevelName").val("");
        $("#storeLevelDesc").val("");
    } else {
        $("#storelevelStatusArea").show();
        $("#storeLevelTitle").html("编辑店铺级别");
        $("#addOrEditLevel").modal("show");
        $("#storelevelStatusArea").show();
        var entiy = $(cur).data('entiy');
        var storeLevelStatus = entiy.status;

        $("#storeLevelId").val(entiy.storeLevelId);
        $("#storeLevelName").val(entiy.storeLevel);
        $("#storeLevelDesc").val(entiy.storeLevelDesc);
        $("#storeLevelStatus").children("label").removeClass("active");
        $($("#storeLevelStatus").children("label")[storeLevelStatus]).addClass("active");
    }
}

function addOrEditStoreType(cur) {
    //如果没有传值过来 表示当前为插入操作 否则为更新操作
    if (cur == null) {
        $("#storeTypeName").val("");
        $("#storeTypeTile").html("新增店铺类型");
        $("#addOrEditStoreType").modal('show');
        $("#storeTypeArea").hide();
        $("#storeTypeId").val(-1);
    } else {
        $("#storeTypeTile").html("编辑店铺类型");
        $("#addOrEditStoreType").modal('show');
        $("#storeTypeArea").show();
        $("#storeTypeId").val(0);
        var entiy = $(cur).data('entiy');
        var status = entiy.status;
        console.log(status);
        $('#hideStoreStatus').val(status);
        $("#storeTypeName").val(entiy.storeTypeName);
        $("#storeTypeStatus").children("label").removeClass("active");
        $($("#storeTypeStatus").children("label")[status]).addClass("active");
        $("#storeTypeId").val(entiy.storeTypeId);
    }
}
