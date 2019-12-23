/**
 * Created by liurenhua on 2017/12/7.
 */
var table;
var MIN_CODE_LENGTH = 10;
var MAX_CODE_LENGTH = 20;
var TableDatatablesButtons = function () {
    var initDataTable = function () {
        table = $('#mappingTable');
        var oTable = table.dataTable({
                buttons: [
                    // {extend: '', className: 'btn red btn-outline supperSearch', text: '显示高级查询条件'},
                    {extend: '', className: 'btn blue btn-outline lead', text: '批量导入'}
                ],
                "language": {
                    "search": "搜索模特编号/产品编号",
                    "sInfoFiltered": ""
                },
                "fnServerParams": function (aoData) {
                    AddParams(aoData);
                },
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
                "bSort": false,
                "ajaxSource": webroot + "/image/createdatetables",
                'aoColumns': [
                    {"mData": "id", "sClass": "detail"}, //
                    {
                        "mData": "modelCode", "sClass": "detail", "mRender": function (data, type, full) {
                        var href = webroot + "/image/listproductimage?code=" + full.modelCode + "&imageType=2";
                        return "<a href='" + href + "' target='_blank'>" + full.modelCode + "</a>"
                    }
                    }, //城市名称
                    {
                        "mData": "productCode", "sClass": "detail", "mRender": function (data, type, full) {
                        var href = webroot + "/image/listproductimage?code=" + full.productCode+ "&imageType=1";
                        return "<a href='" + href + "' target='_blank'>" + full.productCode + "</a>"
                    }
                    }, //城市编码
                    {
                        "mData": "cityId", "sClass": "detail", "bSortable": false, "mRender": function (data, type, full) {
                        var entiy = commonClass.replaceAllQuotationMarks(JSON.stringify(full));
                        var html = "<a href='javascript:;' class='btn btn-xs green btn-outline' rel='tooltip' title='编辑' data-entiy='" + entiy + "' onclick='editMapping(this);'><i class='fa fa-pencil'></i></a>";
                        html += "<a href='javascript:;' class='btn btn-xs red btn-outline' rel='tooltip' title='删除' data-id='" + full.id + "' onclick='deleteMapping(this);'><i class='fa fa-remove'></i></a>";
                        return html;
                    }
                    } //操作
                ],
                "fnDrawCallback": function () {
                    $('#loading').modal('hide');
                }
            })
        ;
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
    TableDatatablesButtons.init();
    $('.supperSearch').click(function () {
        $("#supperSearchArea").modal('show');
    });
    $("#btnSearch").click(function () {
        table.fnDraw();
        $("#supperSearchArea").modal('hide');
    });

    $(".lead").click(function () {
        commonClass.clearFileinput();
        $("#leadArea").modal('show');
    });

    $("#btnLeadCommit").click(function () {
        if (jQuery("input[type='file']").val() == "") {
            swal("警告", "您还没有选择文件", "warning");
            return;
        }
        var formdata = new FormData(document.getElementById("excelForm"));
        $.ajax({
            type: "post",
            url: webroot + "/image/leadmodelproductmapping",
            data: formdata,
            processData: false,
            contentType: false,
            success: function (response) {
                if (response.code == 200) {
                    var count = response.data;
                    if (count == 0) {
                        swal("失败", "您没能导入任何数据，原因可能是：\n1.您的excel不符合模板要求\n2.您选择excel文件中的数据已经被导入过", "error");
                    } else {
                        swal("成功", "您此次成功导入" + count + "条数据", "success");
                        $('#leadArea').modal('hide');
                        table.fnDraw();
                    }
                }
                else {
                    swal(response.message, "", "error");
                }
            },
            beforeSend: function (XMLHttpRequest) {
                $('#loading').modal('show');
                $("#btnLeadCommit").attr("disabled", "disabled");
            },
            complete: function (XMLHttpRequest, textStatus) {
                $("#btnLeadCommit").removeAttr("disabled");
                $('#loading').modal('hide');

            }
        })
    });

});

//deletemapping
function deleteMapping(curr) {
    var id = $(curr).data('id');
    swal({
            title: "删除后无法恢复，您确定删除此对应关系?",
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
                url: webroot + "/image/deletemapping/" + id,
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
                                table.fnDraw();
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
function AddParams(aoData) {
    aoData.push({"name": "imageType", "value": commonClass.getStringVal($('#imageType').val())});
    aoData.push({"name": "season", "value": $("#select_dev_season").val()});
    aoData.push({"name": "year", "value": $('#year').val()});
    aoData.push({"name": "line", "value": $('#select_line').val()});
    aoData.push({"name": "imageType", "value": commonClass.getStringVal($('#imageType').val())});
    $('#loading').modal('show');
}

function editMapping(curr) {
    var m = $(curr).data('entiy');
    $("#info").hide();
    $("#productCode").val(m.productCode);
    $("#modelCode").val(m.modelCode);
    $("#mappingId").val(m.id);
    $("#editMapping").modal('show');
}


function checkModelCode() {
    var modelCode = $("#modelCode").val();
    if (modelCode.length == 0) {
        showOrHideInfo(true, "模特编码不能为空");
        return false;
    } else if (!/^[a-zA-Z0-9]*$/g.test(modelCode)) {
        showOrHideInfo(true, "模特编码只能是英文和数字");
        return false;
    } else if (modelCode.length < MIN_CODE_LENGTH || modelCode.length > MAX_CODE_LENGTH) {
        showOrHideInfo(true, "模特编码的长度在" + MIN_CODE_LENGTH + "-" + MAX_CODE_LENGTH + "之间");
        return false;
    } else {
        showOrHideInfo(false, null);
        return true;
    }
}

function showOrHideInfo(show, message) {
    if (show) {
        $("#info").show();
        $("#info").html(message);
    } else {
        $("#info").hide();
    }
}


function commitUpdate() {

    if (!checkModelCode()) {
        return;
    }

    var productCode = $("#productCode").val();
    var modelCode = $("#modelCode").val();
    var id = $("#mappingId").val();
    var data = {
        "productCode": productCode,
        "modelCode": modelCode,
        "id": id
    }
    $.ajax({
        type: "post",
        url: webroot + "/image/updatemapping",
        data: data,
        success: function (response) {
            if (response.code == 200) {
                swal("更新成功", "", "success");
                table.fnDraw();
                $("#info").hide();
                $("#editMapping").modal('hide');
            } else {
                showOrHideInfo(true, response.message);
                // swal("失败", response.message, "error");
            }
        },
        error: function () {
            swal("失败", "您的网络似乎断开了", "error");
        },
        beforeSend: function (XMLHttpRequest) {
            $("#btnUpdate").attr("disabled", "disabled");
            $('#loading').modal('show');
        },
        complete: function (XMLHttpRequest, textStatus) {
            $("#btnUpdate").removeAttr("disabled");
            $('#loading').modal('hide');
        }
    });
}

