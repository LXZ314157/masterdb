var positionList = [];
var currentData = [];
var table;
var selectItemList = [];
var attributeSelectList = [];

var FormValidation = function () {
    var handleValidationRegister = function () {
        var formreg = $('#form_addlook');
        var errorreg = $('.alert-danger', formreg);
        var successreg = $('.alert-success', formreg);

        formreg.validate({
            errorElement: 'span',
            errorClass: 'help-block help-block-error',
            focusInvalid: false,
            ignore: "",
            rules: {
                lookId: {
                    minlength: 4,
                    maxlength: 5,
                    digits: true,
                    required: true
                },
                lookCode: {
                    minlength: 5,
                    maxlength: 15,
                    required: true
                },
                lookPhotoName: {
                    required: true
                },
                line: {
                    required: true
                },
                waveBand: {
                    required: true
                },
                currentSeason: {
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
                {extend: '', className: 'btn green btn-outline btnAdd', text: '新增look'},
                {extend: '', className: 'btn purple btn-outline btnImport', text: '导入look'}
            ],
            "bSort": false,
            'language': {
                "search": "快速搜索(look序号)"
            },
            "ajaxSource": webroot + "/look/looklist",
            'aoColumns': [
                {
                    "mData": "lookId", "sClass": "detail", "bSortable": false, "mRender": function (data, type, full) {
                    var html = "<a href='javascript:;' class='btn btn-xs green btn-outline' data-lookid='" + data + "' onclick='editLook(this);' rel='tooltip' title='编辑'><i class='fa fa-pencil'></i></a>";
                    return html;
                }
                }, //操作
                {"mData": "lookId", "sClass": "detail"}, //look序号
                {"mData": "lookCode", "sClass": "detail"}, //look编号
                {"mData": "lookPhotoName", "sClass": "detail"}, //look图片名
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
                }, //年份
                {
                    "mData": "currentSeason", "sClass": "detail", "mRender": function (data, type, full) {
                    return data === true ? "是" : "否";
                }
                }, //当季引用
                {
                    "mData": "position", "sClass": "detail", "mRender": function (data, type, full) {
                    if (data === 0) {
                        return "";
                    }
                    else {
                        var len = positionList.length;
                        if (len > 0) {
                            var arr = [];
                            for (var i = 0; i < len; i++) {
                                if ((data & positionList[i].code) === positionList[i].code) {
                                    arr.push(positionList[i].name);
                                }
                            }
                            return arr.join(",");
                        }
                        else {
                            return data;
                        }
                    }
                }
                } //look定位
            ],
            "fnDrawCallback": function (oSettings) {
                var json = JSON.parse(oSettings.jqXHR.responseText);
                currentData = json.aaData;
                $('#loading').modal('hide');
            },
            "initComplete": function () {
                $('.btnAdd').click(function () {
                    loadLookDetail();
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

    UE.getEditor('ueditor_input').ready(function () {
        UE.getEditor('ueditor_input').setHeight(180);
    });

    $.ajax({
        type: "GET",
        url: webroot + "/look/getpositionlist",
        success: function (response) {
            if (response.code == 200) {
                positionList = response.data;
            }
            else {
                positionList = [];
            }
            TableDatatablesButtons.init();
        },
        error: function () {
            positionList = [];
            TableDatatablesButtons.init();
        }
    });
    var defCode = "position";
    $.ajax({
        type: "GET",
        url: webroot + "/look/getselectdata",
        data: {'defCode': defCode},
        success: function (response) {
            if (response.code == 200) {
                selectItemList = response.data.selectItemList;
                attributeSelectList = response.data.attributeSelectList;
            }

            randerSelect();
            randerAttrSelect();
        }
    });

    $('#lookId').change(function () {
        var id = $.trim($(this).val());
        if (id == null || id.length == 0) {
            return;
        }
        $.ajax({
            type: "GET",
            url: webroot + "/look/checklookid/" + id,
            success: function (response) {
                if (response.code == 200) {
                    if (response.data > 0) {
                        $("#btnsave").attr("disabled", "disabled");
                        $('#errmsg').text("该look序号已存在");
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
                    $('#errmsg').text("验证look序号失败，请稍后重试");
                    $('#errmsg').show();
                }
            },
            error: function () {
                $("#btnsave").attr("disabled", "disabled");
                $('#errmsg').text("验证look序号失败，请稍后重试");
                $('#errmsg').show();
            }
        });
    });

    $('#lookCode').change(function () {
        var code = $.trim($(this).val());
        if (code == null || code.length == 0) {
            return;
        }
        $.ajax({
            type: "GET",
            url: webroot + "/look/checklookcode/" + code,
            success: function (response) {
                if (response.code == 200) {
                    if (response.data > 0) {
                        $("#btnsave").attr("disabled", "disabled");
                        $('#errcodemsg').text("该look编号已存在");
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
                    $('#errcodemsg').text("验证look编号失败，请稍后重试");
                    $('#errcodemsg').show();
                }
            },
            error: function () {
                $("#btnsave").attr("disabled", "disabled");
                $('#errcodemsg').text("验证look编号失败，请稍后重试");
                $('#errcodemsg').show();
            }
        });
    });

    $("#btnSave").click(function () {
        if ($("#form_addlook").valid()) {
            var formData = new FormData($("#form_addlook")[0]);
            var positionDesc = UE.getEditor('ueditor_input').getContent();
            if (positionDesc != null && positionDesc.length > 0) {
                formData.append("positionDesc", positionDesc);
            }
            formData.append("positions", commonClass.getStringVal($('#position').val()));
            var requestUrl = webroot + "/look/addlook";
            var type = $('#type').val();
            if (type == 1) {
                requestUrl = webroot + "/look/updatelook";
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
                                $('#lookdetail').modal("hide");
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

function editLook(curr) {
    var lookId = $(curr).data("lookid");
    var currlist = $.grep(currentData, function (value) {
        return value.lookId === lookId;
    });
    if (currlist != null && currlist.length > 0) {
        var look = currlist[0];
        loadLookDetail(look);
    }
}

function loadLookDetail(look) {
    var title = "新增look";
    var type = 0;
    var lookId = "";
    var lookCode = "";
    var lookPhotoName = "";
    var line = "";
    var waveBand = "";
    var currentSeason = "";
    var positions = [];
    var desc = "";
    var devSeason = "";
    var year = "";
    if (look != null) {
        title = "编辑look";
        type = 1;
        lookId = look.lookId;
        lookCode = look.lookCode;
        lookPhotoName = look.lookPhotoName;
        line = look.line;
        waveBand = look.waveBand;
        currentSeason = look.currentSeason === true ? "true" : "false";
        year = look.year;
        devSeason = look.devSeason;
        if (look.position > 0) {
            var len = positionList.length;
            if (len > 0) {
                positions = [];
                for (var i = 0; i < len; i++) {
                    if ((look.position & positionList[i].code) === positionList[i].code) {
                        positions.push(positionList[i].code);
                    }
                }
            }
        }
        else {
            positions = [];
        }
        desc = look.positionDesc;


        $('#lookId').attr("readonly", "readonly");
        $('#lookCode').attr("readonly", "readonly");
    }
    else {
        $("#lookId").removeAttr("readonly");
        $("#lookCode").removeAttr("readonly");
    }
    $('#type').val(type);
    $('#lookdetail_title').text(title);
    $('#lookId').val(lookId);
    $('#lookCode').val(lookCode);
    $('#lookPhotoName').val(lookPhotoName);
    $('#line').val(line);
    $('#waveBand').val(waveBand);
    $('#currentSeason').val(currentSeason);
    $('#year').val(year);
    $('#devSeason').val(devSeason);

    $("#position").val(positions).trigger('change');

    $('#lookdetail').modal("show");
    setTimeout(function () {
        UE.getEditor('ueditor_input').setContent(desc);
    }, 666);
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
    $("#position").find("option").remove();
    if (attributeSelectList != null && attributeSelectList.length > 0) {
        $.each(attributeSelectList, function (i, item) {
            $("<option></option>").val(item["code"]).text(item["name"]).appendTo($("#position"));
        });
    }
}