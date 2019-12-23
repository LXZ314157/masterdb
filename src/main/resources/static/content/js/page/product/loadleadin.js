$(function () {
    //新建style上传
    $("#styleinsert").click(function () {
        if (checkExcel()) {
            return;
        }
        var url = webroot + "/product/uploadstyle";
        var sub = "styleinsert";
        ajaxHtml("POST", url, sub, true);
    })
    //更新style
    $("#updatestyle").click(function () {
        if (checkExcel()) {
            return;
        }
        var url = webroot + "/product/updatestyleup";
        var sub = "updatestyle";
        ajaxHtml("PUT", url, sub, false);
    });
//新建size上传
    $("#sizeinsert").click(function () {
        if (checkExcel()) {
            return;
        }
        var url = webroot + "/product/sizeinsert";
        var sub = "sizeinsert";
        ajaxHtml("POST", url, sub, false);

    });

    $(".close").click(function () {
        $("#errorShow").modal('hide');
        $("#detailError").html("");
    })

    $(".excel").click(function () {
        submit();
    })
    $("#errorTitle").css({"width": "100px", "word-wrap": "break-word"});
})

function checkExcel() {
    if (jQuery("input[type='file']").val() == "") {
        swal("警告", "您还没有选择文件", "warning");
        return true;
    }

}
function downloadModel() {
    $("#productCategoryId").val('4');
    $("#tableName").val('costume');
    $("#frmMoedlDownload").attr("action", webroot + "/product/exportexcel");
    $("#frmMoedlDownload").attr("method", "POST");
    $("#frmMoedlDownload").submit();
}
function ajaxHtml(type, url, sub, flag) {
    var formdata = new FormData(document.getElementById("upLoadFileForm"));
    $.ajax({
        type: type,
        url: url,
        data: formdata,
        processData: false,
        contentType: false,
        success: function (response) {
            if (response.code == 200) {
                var data = response.data;
                var path = response.message;
                if (data == null || data.length == 0) {
                    swal({
                            title: "导入数据存在错误，需下载观看，请点击确定",
                            text: "",
                            type: "warning",
                            showCancelButton: true,
                            cancelButtonText: "取消",
                            cancelButtonClass: "btn-cancel",
                            confirmButtonClass: "btn-danger excel",
                            confirmButtonText: "确定",
                            closeOnConfirm: false
                        },
                        function () {
                            submit(path);
                        })
                } else {
                    var count = response.data.count;
                    if (flag) {
                        getAlertContent("成功导入" + count + "条", "已导入的数据不第二次导入");
                    }
                    else {
                        getAlertContent();
                    }
                }
            }
            else {
                swal(response.message, "", "error");
            }
        },
        beforeSend: function (XMLHttpRequest) {
            $('#loading').modal('show');
            $("#" + sub).attr("disabled", "disabled");
        },
        complete: function (XMLHttpRequest, textStatus) {
            $("#" + sub).removeAttr("disabled");
            $('#loading').modal('hide');
        },
        error: function () {
            //程序出现未知错误，请稍候重试
            swal(ICICLELangUtil.getText("", 1035), "", "error");
        }

    })


}

function getAlertContent(content) {
    swal({
            title: "成功",
            text: content,
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

function submit(path) {
    $("#excelpath").val(path);
    $("#frmDownload").attr("action", webroot + "/product/download");
    $("#frmDownload").attr("method", "POST");
    $("#frmDownload").submit();
}

