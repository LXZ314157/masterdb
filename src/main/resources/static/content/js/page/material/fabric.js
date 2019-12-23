/**
 * Created by liurenhua on 2017/12/1.
 */
var TableDatatablesButtons = function () {

    var initDataTable = function () {
        table = $('#fabricTable');
        var oTable = table.dataTable({
            buttons: [
                {extend: '', className: 'btn red btn-outline addFabric', text: ICICLELangUtil.getText(language, 1555)},
                {
                    extend: '',
                    className: 'btn green btn-outline leadFabric',
                    text: ICICLELangUtil.getText(language, 1051)
                },
                {extend: '', className: 'btn blue btn-outline excelExport', text: '导出excel'}
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
                        swal(ICICLELangUtil.getText(language, 1064), "", "error");
                    }
                });
            },

            "fnServerParams": function (aoData) {
                AddParams(aoData);
            },

            "oLanguage": {
                "sSearch": ICICLELangUtil.getText(language, 1558) + " _INPUT_",
                "sInfoFiltered": ""
            },
            "order": [[5, "desc"]],
            "bSort": true,
            "ajaxSource": webroot + "/material/createdatables",
            'aoColumns': [
                {"mData": "fabricCode", "sClass": "detail", "bSortable": true,}, //面料编码
                {"mData": "fabricName", "sClass": "detail", "bSortable": true,}, //面料名称
                {
                    "mData": "hasFeature", "sClass": "detail", "bSortable": true,
                    "mRender": function (data, type, full) {
                        if (full.hasFeature) {
                            return "<b>✔</b>";
                        } else {
                            return "<b>✘</b>";
                        }
                    }
                }, //是否编辑面料特征
                {"mData": "createdBy", "sClass": "detail", "bSortable": true,}, //创建人
                {
                    "mData": "createdDate", "sClass": "detail", "bSortable": true,
                    "mRender": function (data, type, full) {
                        var date = commonClass.getDateTimeString(full.createdDate);
                        return date;
                    }
                },//创建时间
                {"mData": "lastUpdateBy", "sClass": "detail", "bSortable": true,}, //最后更新人
                {
                    "mData": "lastUpdatedate", "sClass": "detail", "bSortable": true,
                    "mRender": function (data, type, full) {
                        var date = commonClass.getDateTimeString(full.lastUpdateDate);
                        return date;
                    }
                }, //上次更新时间
                {
                    "mData": "fabricCode",
                    "sClass": "detail",
                    "bSortable": false,
                    "mRender": function (data, type, full) {
                        var html = "<a href='javascript:;' class='btn btn-xs blue btn-outline'  title='" + ICICLELangUtil.getText(language, 1107) + "' data-id='" + full.id + "' onclick='showDetailArea(this)'>" +
                            "<i class='fa fa-pencil'></i></a>";
                        return html;
                    }
                } //操作
            ],

            "fnDrawCallback": function (data) {
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
        },
    };

    function AddParams(aoData) {
        $('#loading').modal('show');
    }
}();
$(function () {
    TableDatatablesButtons.init();

    $(".addFabric").click(function () {
        showDetailArea();
    });

    $(".leadFabric").click(function () {
        commonClass.clearFileinput();
        $("#leadFabricArea").modal('show');
    });

    $("#btnLeadCommit").click(function () {
        uploadExcelFile();
    });


    $(".excelExport").click(function () {
        var search = $("input[type='search']").val();
        $("#fabiricNameOrNo").val(search);
        $("#frmMoedlDownload").attr("action", webroot + "/material/downLoadFabricExcel");
        $("#frmMoedlDownload").attr("method", "POST");
        $("#frmMoedlDownload").submit();
    });


    $("#saveFabric").click(function () {
        var fabricCode = commonClass.replaceAllQuotationMarks($.trim($("#fabricCode").val()));
        var fabricName = commonClass.replaceAllQuotationMarks($.trim($("#fabricName").val()));
        var fabricFeature = UE.getEditor('ueditor_input').getContent();
        var hasFeature = UE.getEditor('ueditor_input').hasContents();
        console.log(fabricCode);
        var id = $("#fabricId").val();
        if (fabricCode.length == 0) {
            swal(ICICLELangUtil.getText(language, 1151), ICICLELangUtil.getText(language, 1541), "warning");
            return;
        }
        if (fabricName.length == 0) {
            swal(ICICLELangUtil.getText(language, 1151), ICICLELangUtil.getText(language, 1542), "warning");
            return;
        }

        var requestData = JSON.stringify({
            fabricCode: fabricCode,
            fabricName: fabricName,
            fabricFeature: fabricFeature,
            id: id,
            hasFeature: hasFeature
        });

        var upadte = (id != null && id.length != 0);
        $("#loading").modal('show');
        updateOrAddFabric(requestData, upadte);
    });
});


function showDetailArea(curr) {
    $("#fabricArea").modal('show');
    UE.getEditor('ueditor_input').setContent("");
    if (curr == null) {
        $("#fabricTitle").html(ICICLELangUtil.getText(language, 1555));
        $("#fabricCode").removeAttr("disabled");
        $("#fabricId").val("");
        $("#fabricCode").val("");
        $("#fabricName").val("");
        return;
    } else {
        var id = $(curr).data("id");
        $("#fabricTitle").html(ICICLELangUtil.getText(language, 1553));
        $.ajax({
            url: webroot + "/material/findfabricbyid",
            type: "get",
            data: {
                "id": id
            },
            success: function (response) {
                if (response.code == 200) {
                    var fabric = response.data;
                    if (fabric == null) {
                        swal(ICICLELangUtil.getText(language, 1151), ICICLELangUtil.getText(language, 1548), "warning");
                    }
                    $("#fabricCode").attr("disabled", "disabled");
                    $("#fabricId").val(fabric.id);
                    $("#fabricCode").val(fabric.fabricCode);
                    $("#fabricName").val(fabric.fabricName);
                    setTimeout(function () {
                        UE.getEditor('ueditor_input').setContent(fabric.fabricFeature);
                    }, 800);

                } else {
                    swal(ICICLELangUtil.getText(language, 1179), "", "error");
                }
            },
            error: function () {
                swal(ICICLELangUtil.getText(language, 1064), "", "error");
            },
            beforeSend: function (XMLHttpRequest) {
                $('#loading').modal('show');
            },
            complete: function (XMLHttpRequest, textStatus) {
                $('#loading').modal('hide');
            }
        });
    }
}


/**
 * @param requestData
 * @param update 当前是否为更新操作
 */
function updateOrAddFabric(requestData, update) {
    var requestUrl;
    var requestType;
    //如果fabricId不为空串 表示当前是在更新操作 反之  为新增操作
    if (update) {
        requestUrl = webroot + "/material/updatefabric";
        requestType = "PUT";
    } else {
        requestUrl = webroot + "/material/addfabric";
        requestType = "POST";
    }
    console.log(requestData);
    $.ajax({
        url: requestUrl,
        type: requestType,
        contentType: "application/json;charset=utf-8",
        data: requestData,
        success: function (response) {
            if (response.code == 200) {
                swal({
                        title: ICICLELangUtil.getText(language, 1554),
                        text: "",
                        type: "success",
                        showCancelButton: false,
                        confirmButtonClass: "btn-success",
                        confirmButtonText: ICICLELangUtil.getText(language, 1007),
                        closeOnConfirm: true
                    },
                    function () {
                        $("#fabricArea").modal('hide');
                        table.fnDraw();
                    });
            }
            //1148
            else {
                swal(ICICLELangUtil.getText(language, 1148), response.message, "error");
            }
        },
        error: function () {
            swal(ICICLELangUtil.getText(language, 1148), ICICLELangUtil.getText(language, 1154), "error");
        },
        beforeSend: function (XMLHttpRequest) {
            $('#loading').modal('show');
            $("#saveFabric").attr("disabled", "disabled");
        },
        complete: function (XMLHttpRequest, textStatus) {
            $("#saveFabric").removeAttr("disabled");
            $('#loading').modal('hide');
        }
    });
}

function uploadExcelFile() {
    var formdata = new FormData(document.getElementById("form"));
    if (jQuery("input[type='file']").val() == "") {//1067
        swal(ICICLELangUtil.getText(language, 1151), ICICLELangUtil.getText(language, 1067), "warning");
        return;
    }
    $.ajax({
        type: "post",
        url: webroot + "/material/leadfabricexcel",
        data: formdata,
        processData: false,
        contentType: false,
        success: function (response) {
            if (response.code == 200) {
                var count = response.data;
                if (count == 0) {
                    //1168
                    swal(ICICLELangUtil.getText(language, 1148), ICICLELangUtil.getText(language, 1168), "success");
                } else if (count == -1) {
                    swal(ICICLELangUtil.getText(language, 1005), "面料修改成功", "success");
                    $("#leadFabricArea").modal('hide');
                }
                else {
                    swal(ICICLELangUtil.getText(language, 1005), ICICLELangUtil.getText(language, 1180) + count + "条数据", "success");
                    $("#leadFabricArea").modal('hide');
                }
            }
            else {
                swal(response.message, "", "error");
            }
        },
        error: function () {
            $("#commit").removeAttr("disabled");
            $('#loading').modal('hide');
            swal(ICICLELangUtil.getText(language, 1148), ICICLELangUtil.getText(language, 1154), "error");
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
