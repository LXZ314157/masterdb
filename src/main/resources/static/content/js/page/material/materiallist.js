/**
 * Created by liurenhua on 2017/11/1.
 */
var table;
var selectedList = [];
var TableDatatablesButtons = function () {
    var initDataTable = function () {
        table = $('#datatable');
        var oTable = table.dataTable({
            buttons: [
                {extend: '', className: 'btn red btn-outline btnSyn', text: ICICLELangUtil.getText(language, 1073)},
                {extend: '', className: 'btn blue btn-outline excelExport', text: '导出excel'},
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
                        //1064
                        swal(ICICLELangUtil.getText(language, 1064), ICICLELangUtil.getText(language, 1065), "error");
                    }
                });
            },
            "fnServerParams": function (aoData) {
                selectedList.splice(0, selectedList.length);
                AddParams(aoData);
            },
            "oLanguage": {
                //1550
                "sSearch": ICICLELangUtil.getText(language, 1550) + " _INPUT_",
                "sInfoFiltered": ""
            },
            "ajaxSource": webroot + "/material/list",
            'aoColumns': [
                {
                    "mData": "materialCode",
                    "sClass": "detail",
                    "mRender": function (data, type, full) {
                        var entity = JSON.stringify(full);
                        var html = "<label class=\"mt-checkbox\"><input name='checkbox' type=\"checkbox\" data-entity='" + entity + "' onclick='rememberCheck(this)' value='' /><span></span></label>";
                        return html;
                    },
                },
                {"mData": "materialCode", "sClass": "detail"}, //物料编码
                {"mData": "materialName", "sClass": "detail"}, //物料名称
                {"mData": "cateDlName", "sClass": "detail"}, //大类
                {"mData": "cateZlName", "sClass": "detail"}, //中类
                {"mData": "cateXlName", "sClass": "detail"}, //小类
                {"mData": "uom", "sClass": "detail"}, //单位
                {"mData": "synStatus", "sClass": "detail"}, //状态
                {"mData": "lastUpdateDate", "sClass": "detail"}, //更新时间
                {
                    "sClass": "detail", "mRender": function (data, type, full) {
                    var materialCode = full.materialCode;
                    var html = "<a href='javascript:;' class='btn btn-xs green btn-outline' rel='tooltip' title='" + ICICLELangUtil.getText(language, 1107) + "' data-code='" + materialCode + "' onclick='editAttribute(this);'><i class='fa fa-pencil'></i></a>";
                    return html;
                }
                } //操作
            ],
            "bSort": true,
            "order": [[7, "desc"]],
            "aoColumnDefs": [{
                'targets': [0, 9],    //第二列和第九列排序  其余不排序
                'orderable': false
            },
                {
                    'aTargets': [7],
                    'mRender': function (data, type, row) {
                        switch (row.syncStatus) {
                            case 0:
                                return "已更新";
                            case 1:
                                return "有更新";
                            case 2:
                                return "新增";
                            default:
                                return "";
                        }
                    },
                },
                {
                    'aTargets': [8],
                    'mRender': function (data, type, row) {
                        return commonClass.getDateTimeString(row.lastSyncDate);
                    }
                },
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
        aoData.push({"name": "type", "value": $("#type").val()});
        $("#chooseAll").removeAttr("checked");
        $('#loading').modal('show');
    }
}();

//记住按钮状态
function rememberCheck(cur) {
    var entity = $(cur).data("entity");
    if ($(cur).is(':checked')) {
        if (!contain(entity.materialCode)) {
            selectedList.push(entity.materialCode);
        }
    } else {
        removeSelect(entity.materialCode);
    }
    if (selectedList.length == $("[name='datatable_length']")) {
        $("#chooseAll").attr("checked", true);
    } else {
        $("#chooseAll").removeAttr("checked");
    }
    console.log(selectedList);
}

//判断选中的物料中是否有指定的
function contain(m) {
    for (var i = 0; i < selectedList.length; i++) {
        if (selectedList[i] == m) {
            return true;
        }
    }
    return false;
}
//判断选中的物料中是否还有另外一个  有则删除
function removeSelect(m) {
    for (var i = 0; i < selectedList.length; i++) {
        if (selectedList[i] == m) {
            selectedList.splice(i, 1);
            return;
        }
    }
}

//全选按钮
function selectAllCheckBox(curr) {
    //全选按钮是否被选中
    var flag = $(curr).is(":checked");
    if (flag) {
        selectedList.splice(0, selectedList.length);
        $("[name='checkbox']").prop("checked", true);
        $("[name='checkbox']").each(function (index, element) {
            if (index != 0) {
                $(element).attr("checked", true);
                var entiy = $(element).data("entity");
                if (!contain(entiy.materialCode)) {
                    selectedList.push(entiy.materialCode);
                }
            }
        });
    } else {
        selectedList.splice(0, selectedList.length);
        $("[name='checkbox']").prop("checked", false);
    }
}

function editAttribute(curr) {
    location.href = webroot + "/material/updatematerial?materialCode=" + $(curr).data("code");
}
$(function () {
    TableDatatablesButtons.init();

    $(".excelExport").click(function () {
        var search = $("input[type='search']").val();
        $("#materialNameOrNo").val(search);
        $("#frmMoedlDownload").attr("action", webroot + "/material/downLoadMaterialExcel");
        $("#frmMoedlDownload").attr("method", "POST");
        $("#frmMoedlDownload").submit();
    });

    $(".btnSyn").click(function () {
        if (selectedList.length == 0) {
            swal(ICICLELangUtil.getText(language, 1151), ICICLELangUtil.getText(language, 1080), "warning");
        } else {
            swal({
                    title: ICICLELangUtil.getText(language, 1081),
                    text: ICICLELangUtil.getText(language, 1082) + selectedList.length + ICICLELangUtil.getText(language, 1083),
                    type: "warning",
                    showCancelButton: true,
                    cancelButtonText: ICICLELangUtil.getText(language, 1150),
                    confirmButtonClass: "btn-danger",
                    confirmButtonText: ICICLELangUtil.getText(language, 1149),
                    closeOnConfirm: false
                },
                function () {
                    $(".btn-danger").attr("disabled", "disabled");
                    $(".cancel").attr("disabled", "disabled");

                    var data = selectedList.join(",");
                    $.ajax({
                        type: "POST",
                        url: webroot + "/material/synmaterial",
                        data: {
                            "materialCodeList": data
                        },
                        success: function (response) {
                            if (response.code == 200) {
                                swal({
                                        title: ICICLELangUtil.getText(language, 1084),
                                        text: "",
                                        type: "success",
                                        showCancelButton: false,
                                        confirmButtonClass: "btn-success",
                                        confirmButtonText: ICICLELangUtil.getText(language, 1149),
                                        closeOnConfirm: true
                                    },

                                    function () {
                                        //更新完成后，把所有选中的选项清空
                                        if (selectedList != null && selectedList.length != 0) {
                                            selectedList.splice(0, selectedList.length);
                                        }
                                        table.fnDraw();
                                    }
                                );
                            }
                            else {
                                swal(ICICLELangUtil.getText(language, 1148), response.message, "error");
                            }
                        },
                        beforeSend: function (XMLHttpRequest) {
                            $(".btnSyn").attr("disabled", "disabled");
                            $('#loading').modal('show');
                        },
                        complete: function (XMLHttpRequest, textStatus) {
                            $(".btnSyn").removeAttr("disabled");
                            $('#loading').modal('hide');
                        },
                        error:function () {
                            console.log($(curr).data("code"));
                            swal(ICICLELangUtil.getText(language, 1064), ICICLELangUtil.getText(language, 1065), "error");
                        }
                    });
                });
        }
    });

    $(".btnAll").click(function () {
        if ($(this).attr("disabled") != undefined)
            return;
        $(this).attr("disabled", true);
        $("#type").val(-1);
        $(".btnAboutToUpdate").removeAttr("disabled");
        $("#tableTitle").html(ICICLELangUtil.getText(language, 1552) + " <span class=\"caret\"></span>");
        table.fnDraw();
    });

    $(".btnAboutToUpdate").click(function () {
        $("#tableTitle").html(ICICLELangUtil.getText(language, 1551) + " <span class=\"caret\"></span>");
        if ($(this).attr("disabled") != undefined)
            return;
        $(this).attr("disabled", true);
        // $(this).a
        $("#type").val(0);
        $(".btnAll").removeAttr("disabled");
        table.fnDraw();
    });
});
