/**
 * Created by liurenhua on 2017/11/14.
 */
Dropzone.autoDiscover = false;
//最大选择文件数量
var MAX_FILE_NUMBER = 4000;
//最大文件同步上传数量
MAX_PARALLEL_UPLOADS = 100;
//最大的文件名长度
MAX_FILE_NAME_LENGTH = 20;
var successCount = 0;
var flag = false;
var url = "";
$(function () {
    var dropzone = new Dropzone("#dropzoneForm", {
        url: webroot + "/promotion/upimg",
        method: "post",
        paramName: "imgfile",
        autoProcessQueue: false,
        createImageThumbnails: false,
        uploadMultiple: true,
        addRemoveLinks: true,
        parallelUploads: MAX_PARALLEL_UPLOADS,
        maxFiles: MAX_FILE_NUMBER,
        dictCancelUploadConfirmation: '你确定要取消上传吗？',
        dictCancelUpload: "取消",
        dictCancelUploadText: "此文件上传失败",
        dictDefaultMessage: "将图片拖拽到这里上传(图片名中不能包含中文，最多选择" + MAX_FILE_NUMBER + "张)",
        dictInvalidInputType: "不支持此种文件类型",
        acceptedFiles: "image/*",
        dictRemoveFile: "删除",
        dictInvalidFileType: "不支持上传这种文件",
        dictMaxFilesExceeded: "每次最多只能上传" + MAX_FILE_NUMBER + "张图片",
        dictResponseError: "服务器没有响应",
        success: function (file, response) {
            dropzone.removeFile(file);
            if (response.code == 200) {
                successCount = successCount + Number(response.data);
                console.log();
                if (dropzone.getAcceptedFiles().length > 1) {
                    setTimeout(function () {
                        dropzone.processQueue();
                    }, 500);
                } else {
                    $("#btnUpload").button("reset");
                    $("#loading").modal("hide");
                    $("#removeAll").removeAttr("disabled");
                    if (successCount > 0) {
                        swal("成功", "图片上传成功", "success");
                    } else {
                        swal("失败", "您未能上传任何图片,请检查文件是否是可用图片、图片类型是否正确", "error");
                    }
                }
            } else {
                $("#btnUpload").button("reset");
                $("#loading").modal("hide");
                $("#removeAll").removeAttr("disabled");
                swal("失败", response.message, "error");
            }
        },
        failed: function () {
            $("#loading").modal('hide');
            $("#btnUpload").button("reset");
            $("#removeAll").removeAttr("disabled");
            swal("失败", "您的网络似乎出了点问题", "error");
        },
        init: function () {
            var dropzone = this;
            $("#btnUpload").click(function () {
                if (checkExcel()) {
                    return;
                }
                if (dropzone.getAcceptedFiles().length == 0) {
                    swal("您还没有选择任何合法图片", "", "warning");
                }
                else {
                    perseExcel(dropzone);
                }
            })

            $("#removeAll").click(function () {
                dropzone.removeAllFiles(true);
            });
        }
    });
});


function checkExcel() {
    if (jQuery("input[type='file']").val() == "") {
        swal("警告", "您还没有选择文件", "warning");
        return true;
    }

}

function perseExcel(dropzone) {
    var data = new FormData($("#upLoadFileForm")[0]);
    $.ajax({
        type: "POST",
        url: webroot + "/promotion/parsedata",
        data: data,
        processData: false,
        contentType: false,
        success: function (response) {
            if (response.code == 200) {
                if (response.data != null) {
                    dropzone.processQueue();
                    $("#loading").modal('show');
                    $("#btnUpload").button("loading");
                    $("#removeAll").attr("disabled", true);
                }
                else {
                    swal(response.message, "", "error");
                }
            }
            else {
                swal(response.message, "", "error");
            }
        },
        beforeSend: function (XMLHttpRequest) {
            $('#loading').modal('show');
            $("#btnUpload").attr("disabled", "disabled");
        },
        complete: function (XMLHttpRequest, textStatus) {
            $("#btnUpload").removeAttr("disabled");
            $('#loading').modal('hide');
        },
        error: function () {
            //程序出现未知错误，请稍候重试
            swal(ICICLELangUtil.getText("", 1035), "", "error");
        }

    })

}