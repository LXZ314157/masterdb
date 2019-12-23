var showTypeList = [];
var currentData = [];
var table;
var selectItemList = [];
var attributeSelectList = [];

var FormValidation = function () {
    var handleValidationRegister = function () {
        var formreg = $('#addpromotion');
        var errorreg = $('.alert-danger', formreg);
        var successreg = $('.alert-success', formreg);

        formreg.validate({
            errorElement: 'span',
            errorClass: 'help-block help-block-error',
            focusInvalid: false,
            ignore: "",
            rules: {
                code: {
                    required: true
                },
                imgname: {
                    required: true
                },
                line: {
                    required: true
                },
                waveBand: {
                    required: true
                },
                year: {
                    required: true
                },
                devSeason: {
                    required: true
                }
            },
            invalidHandler: function (event, validator) {
                successreg.hide();
                errorreg.show();
                App.scrollTo(errorreg, -200);
            },
            errorPlacement: function (error, element) {
                var icon = $(element).parent('.input-icon').children('i');
                icon.removeClass('fa-check').addClass("fa-warning");
                icon.attr("data-original-title", error.text()).tooltip({'container': 'body'});
            },
            highlight: function (element) {
                $(element)
                    .closest('.form-group').removeClass("has-success").addClass('has-error');
            },
            unhighlight: function (element) {

            },
            success: function (label, element) {
                var icon = $(element).parent('.input-icon').children('i');
                $(element).closest('.form-group').removeClass('has-error').addClass('has-success');
                icon.removeClass("fa-warning").addClass("fa-check");
            },
            submitHandler: function (form) {
                successreg.show();
                errorreg.hide();
            }
        });
    }
    return {
        init: function () {
            handleValidationRegister();
        }
    };
}();

var TableDatatablesButtons = function () {
    var initDataTable = function () {
        table = $('#datatable').dataTable({
            buttons: [
                {extend: '', className: 'btn green btn-outline btnAdd', text: '新增推广'},
                {extend: '', className: 'btn purple btn-outline btnImport', text: '导入推广'},
                {extend: '', className: 'btn purple btn-outline btnImportConnection', text: '导入推广产品关联'}
            ],
            "bSort": false,
            'language': {
                "search": "快速搜索"
            },
            "ajaxSource": webroot + "/promotion/promotionlist",
            'aoColumns': [
                {
                    "mData": "promotionId",
                    "sClass": "detail",
                    "bSortable": false,
                    "mRender": function (data, type, full) {
                        var html = "<a href='javascript:;' class='btn btn-xs green btn-outline' data-promotionId='" + data + "' onclick='editPromotion(this);' rel='tooltip' title='编辑'><i class='fa fa-pencil'></i></a>";
                        return html;
                    }
                }, //操作
                {"mData": "promotionId", "sClass": "detail"},
                {"mData": "promotionCode", "sClass": "detail"},
                {"mData": "promotionImgName", "sClass": "detail"},
                {"mData": "line", "sClass": "detail"}, //线路
                {"mData": "waveBand", "sClass": "detail"}, //波段
                {"mData": "year", "sClass": "detail"}, //年份
                {
                    "mData": "devSeason", "sClass": "detail", "mRender": function (data, type, full) {
                    var devSeason = "";
                    for (var i = 0; i < selectItemList.length; i++) {
                        var item = selectItemList[i];
                        if (item.itemKey == data && item.tableName == 'select_dev_season') {
                            devSeason = item.itemValue;
                            break;
                        }
                    }
                    return devSeason;
                }
                },
                {
                    "mData": "outOfDate", "sClass": "detail", "mRender": function (data, type, full) {
                    if (data === 0) {
                        return "";
                    }
                    else {
                        return commonClass.getDateTimeString(data);
                    }

                }
                },
                {
                    "mData": "showType", "sClass": "detail", "mRender": function (data, type, full) {
                    if (data === 0) {
                        return "";
                    }
                    else {
                        var len = showTypeList.length;
                        if (len > 0) {
                            var arr = [];
                            for (var i = 0; i < len; i++) {
                                if ((data & showTypeList[i].code) === showTypeList[i].code) {
                                    arr.push(showTypeList[i].name);
                                }
                            }
                            return arr.join(",");
                        }
                        else {
                            return data;
                        }
                    }
                }
                } //露出类型
            ],
            "fnDrawCallback": function (oSettings) {
                var json = JSON.parse(oSettings.jqXHR.responseText);
                currentData = json.aaData;
                $('#loading').modal('hide');
            },
            "initComplete": function () {
                $('.btnAdd').click(function () {
                    loadPromotionDetail();
                });

                $('.btnImport').click(function () {
                    commonClass.clearFileinput();
                    $('#import_look').modal("show");
                });
            }
        });
    }
    return {
        init: function () {
            if (!jQuery().dataTable) {
                return;
            }
            initDataTable();
        }
    };
}();

$(function () {
    FormValidation.init();
    $.ajax({
        type: "GET",
        url: webroot + "/promotion/getshowtypelist",
        success: function (response) {
            if (response.code == 200) {
                showTypeList = response.data;
            }
            else {
                showTypeList = [];
            }
            TableDatatablesButtons.init();
        },
        error: function () {
            showTypeList = [];
            TableDatatablesButtons.init();
        }
    });
    var defCode = "show_type";
    $.ajax({
        type: "GET",
        data: {'defCode': defCode},
        url: webroot + "/look/getselectdata",
        success: function (response) {
            if (response.code == 200) {
                selectItemList = response.data.selectItemList;
                attributeSelectList = response.data.attributeSelectList;
            }
            randerSelect();
            randerAttrSelect();
        }
    });

    $('#id').change(function () {
        var id = $.trim($(this).val());
        if (id == null || id.length == 0) {
            return;
        }
        $.ajax({
            type: "GET",
            url: webroot + "/promotion/checkpromotionid/" + id,
            success: function (response) {
                if (response.code == 200) {
                    if (response.data > 0) {
                        $("#btnsave").attr("disabled", "disabled");
                        $('#errmsg').text("该推广id已存在");
                        $('#errmsg').show();
                    }
                    else {
                        $("#btnsave").removeAttr("disabled");
                        $('#errmsg').text("");
                        $('#errmsg').hide();
                    }
                }
                else {
                    $("#btnsave").attr("disabled", "disabled");
                    $('#errmsg').text("验证推广id失败，请稍后重试");
                    $('#errmsg').show();
                }
            },
            error: function () {
                $("#btnsave").attr("disabled", "disabled");
                $('#errmsg').text("验证推广id失败，请稍后重试");
                $('#errmsg').show();
            }
        });
    });

    $('#code').change(function () {
        var code = $.trim($(this).val());
        if (code == null || code.length == 0) {
            return;
        }
        $.ajax({
            type: "GET",
            url: webroot + "/promotion/checkpromotioncode/" + code,
            success: function (response) {
                if (response.code == 200) {
                    if (response.data > 0) {
                        $("#btnsave").attr("disabled", "disabled");
                        $('#errcodemsg').text("该推广图片编号已存在");
                        $('#errcodemsg').show();
                    }
                    else {
                        $("#btnsave").removeAttr("disabled");
                        $('#errcodemsg').text("");
                        $('#errcodemsg').hide();
                    }
                }
                else {
                    $("#btnsave").attr("disabled", "disabled");
                    $('#errcodemsg').text("验证推广图片编号失败，请稍后重试");
                    $('#errcodemsg').show();
                }
            },
            error: function () {
                $("#btnsave").attr("disabled", "disabled");
                $('#errcodemsg').text("验证推广图片编号失败，请稍后重试");
                $('#errcodemsg').show();
            }
        });
    });

    $("#btnSave").click(function () {
        if ($("#addpromotion").valid()) {
            var formData = new FormData($("#addpromotion")[0]);
            formData.append("showType", commonClass.getStringVal($('#showtype').val()));
            var requestUrl = webroot + "/promotion/addpromotion";
            var type = $('#type').val();
            if (type == 1) {
                requestUrl = webroot + "/promotion/updatepromotion";
            }
            $.ajax({
                type: "POST",
                url: requestUrl,
                data: formData,
                processData: false,
                contentType: false,
                success: function (response) {
                    if (response.code == 200) {
                        swal({
                                title: "操作成功",
                                text: "",
                                type: "success",
                                showCancelButton: false,
                                confirmButtonClass: "btn-success",
                                confirmButtonText: "确定",
                                closeOnConfirm: true
                            },
                            function () {
                                $('#addAndEditPromotion').modal("hide");
                                table.fnDraw();
                            });
                    }
                    else {
                        swal("操作失败", response.message, "error");
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    swal("操作失败", "[-999]未知错误:" + textStatus, "error");
                },
                beforeSend: function (XMLHttpRequest) {
                    $('#loading').modal('show');
                    $("#btnSave").attr("disabled", "disabled");
                },
                complete: function (XMLHttpRequest, textStatus) {
                    $('#loading').modal('hide');
                    $("#btnSave").removeAttr("disabled");
                }
            });
        }
    });

    $('#btnImportLook').click(function () {
        var fileData = $("#lookfile")[0].files[0];
        if (fileData == null) {
            swal("错误!", "excel文档不能为空", "error");
            return;
        }
        if (commonClass.checkFileSize("lookfile", "excel")) {
            var formData = new FormData($("#form_import_look")[0]);
            $.ajax({
                type: "POST",
                url: webroot + "/look/importlook",
                data: formData,
                processData: false,
                contentType: false,
                success: function (response) {
                    if (response.code == 200) {
                        swal({
                                title: "操作成功",
                                text: response.message,
                                type: "success",
                                showCancelButton: false,
                                confirmButtonClass: "btn-success",
                                confirmButtonText: "确定",
                                closeOnConfirm: true
                            },
                            function () {
                                commonClass.clearFileinput();
                                $('#import_look').modal("hide");
                                table.fnDraw();
                            });
                    }
                    else {
                        swal("操作失败!", response.message, "error");
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    swal("错误!", "[-999]未知错误:" + textStatus, "error");
                },
                beforeSend: function (XMLHttpRequest) {
                    $('#loading').modal('show');
                    $("#btnImportLook").attr("disabled", "disabled");
                },
                complete: function (XMLHttpRequest, textStatus) {
                    $('#loading').modal('hide');
                    $("#btnImportLook").removeAttr("disabled");
                }
            });
        }
    });
});

function editPromotion(curr) {
    var promotionId = $(curr).data("promotionid");
    var currlist = $.grep(currentData, function (value) {
        return value.promotionId == promotionId;
    });
    if (currlist != null && currlist.length > 0) {
        var promotion = currlist[0];
        loadPromotionDetail(promotion);
    }
}

function loadPromotionDetail(promotion) {
    var title = "新增推广";
    var type = 0;
    var promotionId = "";
    var promotionCode = "";
    var promotionImgName = "";
    var line = "";
    var waveBand = "";
    var devSeason = "";
    var outofdate = "";
    var showType = [];
    var year = "";
    if (promotion != null) {
        title = "编辑推广";
        type = 1;
        promotionId = promotion.promotionId;
        promotionCode = promotion.promotionCode;
        promotionImgName = promotion.promotionImgName;
        line = promotion.line;
        waveBand = promotion.waveBand;
        year = promotion.year;
        devSeason = promotion.devSeason;
        outofdate = promotion.outOfDate;
        if (promotion.showType > 0) {
            var len = showTypeList.length;
            if (len > 0) {
                showtype = [];
                for (var i = 0; i < len; i++) {
                    if ((promotion.showType & showTypeList[i].code) == showTypeList[i].code) {
                        showType.push(showTypeList[i].code);
                    }
                }
            }
        }
        else {
            showtype = [];
        }

        $('#id').attr("readonly", "readonly");
        $('#code').attr("readonly", "readonly");
    }
    else {
        $("#id").removeAttr("readonly");
        $("#code").removeAttr("readonly");
    }
    $('#type').val(type);
    $('#lookdetail_title').text(title);
    $('#id').val(promotionId);
    $('#code').val(promotionCode);
    $('#imgname').val(promotionImgName);
    $('#line').val(line);
    $('#waveBand').val(waveBand);
    $('#year').val(year);
    if (outofdate != "") {
        $('#outofdate').val(commonClass.getDateString(outofdate));
    }
    $('#devSeason').val(devSeason);
    $("#showtype").val(showType).trigger('change');
    $('#addAndEditPromotion').modal("show");
}

function randerSelect() {
    randerSelectOptions("line", "select_line");
    randerSelectOptions("waveBand", "select_wave_band");
    randerSelectOptions("devSeason", "select_dev_season");

}

function randerSelectOptions(domId, tableName) {
    $("#" + domId).find("option").remove();
    $("<option></option>").val("").text("--请选择--").appendTo($("#" + domId));
    if (selectItemList != null && selectItemList.length > 0) {
        var currlist = $.grep(selectItemList, function (value) {
            return value.tableName === tableName;
        });
        $.each(currlist, function (i, item) {
            var text = "";
            if (domId === "line") {
                text = item["itemValue"] + "[" + item["itemKey"] + "]";
            }
            else {
                text = item["itemValue"];
            }
            $("<option></option>").val(item["itemKey"]).text(text).appendTo($("#" + domId));
        });
    }
}

function randerAttrSelect() {
    $("#showtype").find("option").remove();
    if (attributeSelectList != null && attributeSelectList.length > 0) {
        $.each(attributeSelectList, function (i, item) {
            $("<option></option>").val(item["code"]).text(item["name"]).appendTo($("#showtype"));
        });
    }
}