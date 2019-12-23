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
var data = 0;
var id = 0;
$(function () {
    var dropzone = new Dropzone("#dropzoneForm", {
        url: webroot + "/image/uploadimage",
        method: "post",
        paramName: "uploadfile",
        autoProcessQueue: false,
        createImageThumbnails: false,
        uploadMultiple: true,
        addRemoveLinks: true,
        parallelUploads: MAX_PARALLEL_UPLOADS,
        maxFiles: MAX_FILE_NUMBER,
        dictCancelUploadConfirmation: '你确定要取消上传吗？',
        dictCancelUpload: "取消",
        dictCancelUploadText: "此文件上传失败",
        dictDefaultMessage: "将图片拖拽到这里上传(文件名中不能包含中文，最多选择" + MAX_FILE_NUMBER + "张)",
        dictInvalidInputType: "不支持此种文件类型",
        acceptedFiles: "image/*",
        dictRemoveFile: "删除",
        dictInvalidFileType: "不支持上传这种文件",
        dictMaxFilesExceeded: "每次最多只能上传" + MAX_FILE_NUMBER + "张图片",
        dictResponseError: "服务器没有响应",
        success: function (file, response) {
            dropzone.removeFile(file);
            if (response.code == 200) {
                data = Number(response.data);
                if (dropzone.getAcceptedFiles().length > 1) {
                    setTimeout(function () {
                        dropzone.processQueue();
                    }, 500);
                }
            } else {
                $("#btnUpload").button("reset");
                $("#loading").modal("hide");
                $("#removeAll").removeAttr("disabled");
                swal("失败", response.message, "error");
            }
        },
        successmultiple: function () {
            successCount = successCount + data;
        },
        queuecomplete: function () {
            if (id != 0) {
                $("#btnUpload").button("reset");
                $("#loading").modal("hide");
                $("#removeAll").removeAttr("disabled");
                if (successCount > 0) {
                    swal("成功", "图片上传成功" + successCount + "张", "success");
                    successCount = 0;
                } else {
                    swal("失败", "您未能上传任何图片,请检查图片的命名是否合法、文件是否是可用图片、图片类型是否正确", "error");
                }
                getEndtime(id);
            }
        },
        accept: function (file, done) {
            if (checkFileName(file.name, $("#codeRule").val())) {
                done();
            } else {
                done("文件名不合法");
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
                if ($("#imageType").val().length == 0) {
                    swal("警告", "您还有没有选择上传的图片类型", "warning");
                    return;
                }
                else if ($("#codeRule").val().length == 0) {
                    swal("警告", "您还有没有选择编码规则", "warning");
                    return;
                }
                else if (dropzone.getAcceptedFiles().length == 0) {
                    swal("您还没有选择任何合法图片", "", "warning");
                } else {
                    successCount = 0;
                    data = 0;
                    getStartTime();
                    dropzone.processQueue();
                    $("#loading").modal('show');
                    $("#btnUpload").button("loading");
                    $("#removeAll").attr("disabled", true);
                }
            });

            $("#removeAll").click(function () {
                dropzone.removeAllFiles(true);
            });
        }
    });
});

/**
 * 验证产品和模特编码是否符合要求
 * AAAT2C117B0051_01
 * 产品编码以AAAA开头 第6 、7 位是年份必须是数字 其余只允许数字和大写字母，下划线最多只允许出现一次
 * 模特编码以AAA开头 第8 、9位是年份，必须为数字 其余只允许数字和大写字母，下划线最多只允许出现一次
 * 推广图片编码以AAA开头，第4、5是年份，其余允许数字和大写字母AAA18B070100001
 * @param fileName
 * @returns {boolean}
 */

function checkFileName(fileName, codeRule) {
    if (fileName.indexOf(".") < 0) {
        return false;
    }
    var arr = fileName.split(".");
    if (arr.length != 2) {
        return false;
    }

    var name = arr[0];

    if (name.indexOf("_") >= 0) {
        var t = name.split("_");
        if (t.length > 2) {
            return false;
        }

        name = t[0];
    }

    if (name > MAX_FILE_NAME_LENGTH) {
        return false;
    }

    if (codeRule == 1) {
        return /^([A]{3}[A-Z0-9]{2}\d{2}[A-Z0-9]{7})/.test(name)
            || /^([A]{3}[A-Z0-9]{4}\d{2}[A-Z0-9]{5})/.test(name) || /^([A]{3}\d{2}[A-Z0-9]{10})/.test(name);
    }
    else if (codeRule == 2) {
        return /^([123456]{1}[CFIEJU]{1}[A-Z0-9]{1}[0-9]{1}\d{2}[1-3]{1}\d{4}[A-F]{1})/.test(name);
    }
    else {
        return false;
    }
}

function getStartTime() {
    $.ajax({
        type: "GET",
        url: webroot + "/time/getstarttime/",
        success: function (response) {
            if (response.code == 200) {
                id = response.message;
                console.log("时间开始日志记录成功");
            }
            else {
                console.log(response.message);
            }
        },
        error: function () {
            swal("程序出现错误，请联系IT处理", "", "error");
            $("#loading").modal("hide");
        }
    });
}

function getEndtime() {
    if (id == null || id == 0) {
        console.log("开始时间获取失败");
    } else {
        $.ajax({
            type: "GET",
            data: {"id": id},
            url: webroot + "/time/getendtime/",
            success: function (response) {
                if (response.code == 200) {
                    console.log("时间日志记录成功");
                    id = 0;
                }
                else {
                    console.log(response.message);
                }
            },
            error: function () {
                swal("程序出现错误，请联系IT处理", "", "error");
                $("#loading").modal("hide");
            }
        });
    }


}
