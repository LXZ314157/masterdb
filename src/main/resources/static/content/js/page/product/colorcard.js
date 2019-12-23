var TableDatatablesButtons = function () {
    var initDataTable = function () {
        table = $('#colorCardList');
        var oTable = table.dataTable({
            buttons: [
                {
                    extend: '',
                    text: '批量导入',
                    className: 'btn green btn-outline bulkload'
                },
                {
                    extend: '',
                    text: '新增色卡',
                    className: 'btn red btn-outline addColorCard'
                },
                {extend: '', className: 'btn blue btn-outline excelExport', text: '导出excel'}
            ],
            "fnServerParams": function (aoData) {
                AddParams(aoData);
            },
            "processing": false,
            "language": {
                "search": "搜索色卡编码/色卡名称：",
                "sInfoFiltered": ""
            },
            "bSort": true,
            "order": [[3, "desc"]],
            "iDisplayLength": 20,
            "ajaxSource": webroot + "/product/colorcardlist",
            'aoColumns': [
                {"mData": "colorCardCode", "sClass": "detail"},
                {"mData": "colorCardName", "sClass": "detail"},
                {"mData": "status", "sClass": "detail"},
                {"mData": "createdDate", "sClass": "detail"},
                {"mData": "lastUpdateDate", "sClass": "detail"},
                {
                    "mData": "colorCardCode",
                    "sClass": "detail",
                    "bSortable": false,
                    "mRender": function (data, type, full) {
                        var entiy = commonClass.replaceAllQuotationMarks(JSON.stringify(full));
                        var no = full.colorCardCode;
                        var html = "";
                        html += "<a href='javascript:;' class='btn btn-xs green btn-outline' rel='tooltip' title='编辑' data-entiy='" + entiy + "' onclick='editCard(this)'><i class='fa fa-pencil'></i></a>";
                        return html;
                    }
                }
            ],
            "fnServerData": function (sSource, aoData, fnCallback, oSettings) {
                oSettings.jqXHR = $.ajax({
                    "dataType": 'json',
                    "type": "POST",
                    "url": sSource,
                    "data": aoData,
                    "success": fnCallback,
                    "error": function () {
                        $("#loading").modal('hide');
                        swal("网络中断", "拉取数据失败，请检查您的网络配置", "error");
                    }
                });
            },
            "aoColumnDefs": [
                {
                    'aTargets': [2],
                    'mRender': function (data, type, row) {
                        if (row.status == 1) {
                            return "有效";
                        }
                        else {
                            return "无效";
                        }
                    }


                },
                {
                    'targets': [0, 1, 2],
                    'orderable': false
                },
                {
                    'aTargets': [3],
                    'mRender': function (data, type, row) {
                        var time = row.createdDate;
                        if (time != null) {
                            var len = new Date(time);
                            var result = commonClass.date_time_long(len);
                            return result;
                        }
                        else {
                            return '';
                        }
                    }
                },
                {
                    'aTargets': [4],
                    'mRender': function (data, type, row) {
                        var time = row.lastUpdateDate;
                        if (time != null) {
                            var len = new Date(time);
                            var result = commonClass.date_time_long(len);
                            return result;
                        }
                        else {
                            return '';
                        }
                    }
                }
            ],
            "fnDrawCallback": function () {
                $('#loading').modal('hide');
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

    function AddParams(aoData) {
        $('#loading').modal('show');
    }
}();
$(function () {
    TableDatatablesButtons.init();
    $("#saveColorCard").click(function () {
        var id = commonClass.replaceAllQuotationMarks($("#id").val());
        var CardCode = commonClass.replaceAllQuotationMarks($("#colorCardCode").val());
        var CardName = commonClass.replaceAllQuotationMarks($("#colorCardName").val());
        if (CardCode.length == 0 || CardName.length == 0) {
            swal("数据填写不完整，请继续填写", "", "warning");
            return;
        }
        var flag = $("#saveColorCard").val();
        var requestType = "";
        var requestUrl = "";
        var requestData = {
            colorCardCode: CardCode,
            colorCardName: CardName
        }
        if (flag == 0) {
            requestData["id"] = id;
            requestType = "PUT";
            requestUrl = webroot + "/product/updatecolorcard";
        }
        else {
            requestType = "POST"
            requestUrl = webroot + "/product/insertcolorcard";
        }
        saveCard(requestType, requestUrl, requestData);

    });

    $(".excelExport").click(function () {
        var search = $("input[type='search']").val();
        $("#colorCardNameOrNo").val(search);
        $("#frmMoedlDownload").attr("action", webroot + "/product/downLoadColorCardExcel");
        $("#frmMoedlDownload").attr("method", "POST");
        $("#frmMoedlDownload").submit();
    });

    $(".addColorCard").click(function () {
        $("#saveColorCard").val(1);//新增状态
        $(".modal-title").html("新增色卡");
        $("#editColorCard").modal("show");
        $("#id").val("");
        $("#colorCardCode").val("");
        $("#colorCardName").val("");
        $("#hasStatus").removeClass("active");
        $("#noStatus").removeClass("active");
    });
    $(".bulkload").click(function () {
        commonClass.clearFileinput();
        $("#detailError").html("");
        $("#leadIn").modal("show");
    });

    $("#upLoadFile").click(function () {
        if (jQuery("input[type='file']").val() == "") {
            swal("您还没有选择文件", "", "warning");
            return;
        }
        var url = webroot + "/product/colorcardupload";
        var sub = "upLoadFile";
        var opt = ajaxHtml("POST", url, sub);
    })
    $(".cancel").click(function () {
        $("#leadIn").modal('hide');
    });
});

function editCard(cuur) {
    var entiy = $(cuur).data('entiy');
    $("#saveColorCard").val(0);//更新状态
    $(".modal-title").html("编辑色卡");
    $("#editColorCard").modal("show");
    $("#id").val(entiy.id);
    $("#colorCardCode").val(entiy.colorCardCode);
    $("#colorCardName").val(entiy.colorCardName);
    $("#hasStatus").addClass("active");
    $("#noStatus").removeClass("active");
}

function saveCard(requestType, requestUrl, requestData) {
    $.ajax({
        type: requestType,
        url: requestUrl,
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(requestData),
        success: function (response) {
            if (response.code == 200) {
                swal({
                        title: "成功！",
                        text: "",
                        type: "success",
                        showCancelButton: false,
                        confirmButtonClass: "btn-success",
                        confirmButtonText: "确定",
                        closeOnConfirm: true
                    },
                    function () {
                        window.location.reload(true);
                    });
            }
            else {
                swal(response.message, "", "error");
            }
        },
        beforeSend: function (XMLHttpRequest) {
            $("#saveColorCard").attr("disabled", "disabled");
            $('#loading').modal('show');
        },
        complete: function (XMLHttpRequest, textStatus) {
            $("#saveColorCard").removeAttr("disabled");
            $('#loading').modal('hide');
        },
        error: function () {
            //程序出现未知错误，请稍候重试
            swal(ICICLELangUtil.getText("",1035), "", "error");
        }
    });
}

function ajaxHtml(type, url, sub) {
    var formdata = new FormData(document.getElementById("colorcardfile"));
    $.ajax({
        type: type,
        url: url,
        data: formdata,
        processData: false,
        contentType: false,
        success: function (response) {
            if (response.code == 200) {
                swal({
                        title: "操作成功",
                        text: "共导入成功" + response.data + "条数据",
                        type: "success",
                        showCancelButton: false,
                        confirmButtonClass: "btn-success",
                        confirmButtonText: "确定",
                        closeOnConfirm: true
                    },
                    function () {
                        commonClass.clearFileinput();
                        $('#leadIn').modal("hide");
                        table.fnDraw();
                    });
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
            swal(ICICLELangUtil.getText("",1035), "", "error");
        }
    })
}


