var cityLevelList = [];
var areaList = [];
var supplierCodeList = [];
var table;
var TableDatatablesButtons = function () {
    var initDataTable = function () {
        table = $('#supplierlist');
        var oTable = table.dataTable({
            buttons: [
                {extend: '', className: 'btn green btn-outline btnSyn', text: '批量同步'},
                {extend: '', className: 'btn blue btn-outline excelExport', text: '导出excel'}
            ],
            "fnServerParams": function (aoData) {
                AddParams(aoData);
            },
            "dom": "<'row' <'col-md-12'B>><'row'<'col-md-6 col-sm-12'l>r><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>",
            "bSort": false,
            "processing": false,
            "ajaxSource": webroot + "/supplier/supplierlist",
            'aoColumns': [
                {
                    "mData": "supplierCode",
                    "sClass": "detail",
                    "bSortable": false,
                    "mRender": function (data, type, row) {
                        var entity = JSON.stringify(row);
                        var html = "<label class=\"mt-checkbox\"><input name='checkbox' type=\"checkbox\" data-entity='" + entity + "' onclick='rememberCheck(this)' value='' /><span></span></label>";

                        return html;
                    }
                },
                {"mData": "supplierCode", "sClass": "detail"},
                {"mData": "supplierSourceCode", "sClass": "detail"},
                {"mData": "supplierName", "sClass": "detail"},
                {"mData": "supplierDescription", "sClass": "detail"},
                {"mData": "supplierCSuppliertypeId", "sClass": "detail"},
                {"mData": "supplierLegalperson", "sClass": "detail"},
                {"mData": "supplierContacter", "sClass": "detail"},
                {"mData": "supplierDataStatusName", "sClass": "detail"},
                 {
                    "mData": "buyerId", "sClass": "detail", "bSortable": false, "mRender": function (data, type, full) {
                        var entiy = commonClass.replaceAllQuotationMarks(JSON.stringify(full));
                        var supplierCode = full.supplierCode;
                        var html = "<a href='" + webroot + "/supplier/viewsupplierdetail?supplierCode=" + supplierCode + "' class='btn btn-xs green btn-outline' rel='tooltip' title='查看详情' data-entiy='" + entiy + "' ><i class='fa fa-file-text-o'></i></a>";
                        return html;
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
}();

function AddParams(aoData) {
    aoData.push({"name": "supplierCodeOrName", "value": commonClass.getStringVal($('#supplierCodeOrName').val())});
    aoData.push({"name": "supplierStatus", "value": commonClass.getStringVal($('#supplierStatus').val())});
    aoData.push({"name": "supplierSourceCode", "value": commonClass.getStringVal($('#supplierSourceCode').val())});
    $('#loading').modal('show');
}

$(function () {
    TableDatatablesButtons.init();

    document.onkeydown = function (e) {
        if (!e) e = window.event;
        if ((e.keyCode || e.which) == 13) {
            event.preventDefault();
            table.fnDraw();
        }
    }
    $(".excelExport").click(function () {
        $("#supplierCodeOrNames").val($('#supplierCodeOrName').val());
        $("#supplierStatuss").val($('#supplierStatus').val());
        $("#supplierSourceCodes").val($('#supplierSourceCode').val());
        $("#frmMoedlDownload").attr("action", webroot + "/supplier/downLoadSupplierExcel");
        $("#frmMoedlDownload").attr("method", "POST");
        $("#frmMoedlDownload").submit();
    });

    $(".btnSyn").click(function () {
        var supplierCodes = supplierCodeList.join(',');
        if (supplierCodeList.length == 0) {
            swal("您没有选择任何选项，无法提交", "", "warning");
        } else {
            swal({
                    title: "确定将所选项目同步?",
                    text: "您一共选择了" + supplierCodeList.length + "项",
                    type: "warning",
                    showCancelButton: true,
                    cancelButtonText: "取消",
                    confirmButtonClass: "btn-danger",
                    confirmButtonText: "确定",
                    closeOnConfirm: false
                },
                function () {
                    $.ajax({
                        type: "POST",
                        url: webroot + "/supplier/syncsupplier",
                        data: {
                            "supplierCodes": supplierCodes
                        },
                        success: function (response) {
                            var message = response.message;
                            if (response.code == 200) {
                                swal({
                                        title: "同步成功",
                                        text: message,
                                        type: "success",
                                        showCancelButton: false,
                                        confirmButtonClass: "btn-success",
                                        confirmButtonText: "确定",
                                        closeOnConfirm: true
                                    },
                                    function () {
                                        //完成后，把所有选中的选项清空
                                        if (supplierCodeList != null && supplierCodeList.length != 0) {
                                            supplierCodeList.splice(0, supplierCodeList.length);
                                        }
                                        table.fnDraw();
                                    });
                            }
                            else {
                                swal(response.message, "", "error");
                            }
                        },
                        beforeSend: function (XMLHttpRequest) {
                            $('#loading').modal('show');
                            $(".btnSyn").attr("disabled", "disabled");
                        },
                        complete: function (XMLHttpRequest, textStatus) {
                            $(".btnSyn").removeAttr("disabled");
                            $('#loading').modal('hide');
                        },
                        error: function () {
                            //程序出现未知错误，请稍候重试
                            swal(ICICLELangUtil.getText("", 1035), "", "error");
                        }
                    });
                });
        }
    });


});



//全选按钮
function selectAllCheckBox(curr) {
    //全选按钮是否被选中
    var flag = $(curr).is(":checked");
    if (flag) {
        supplierCodeList.splice(0, supplierCodeList.length);
        $("[name='checkbox']").prop("checked", true);
        $("[name='checkbox']").each(function (index, element) {
            if (index != 0) {
                var entiy = $(element).data("entity");
                addSelect(entiy.supplierCode);
                $(element).attr("checked", true);
            }
        });
    } else {
        supplierCodeList.splice(0, supplierCodeList.length);
        $("[name='checkbox']").prop("checked", false);
    }
}

function rememberCheck(cur) {
    var entity = $(cur).data("entity");
    if ($(cur).is(':checked')) {
        addSelect(entity.supplierCode);
    } else {
        removeSelect(entity.supplierCode);
    }
    if (supplierCodeList.length == $("[name='datatable_length']")) {
        $("#chooseAll").attr("checked", true);
    } else {
        $("#chooseAll").removeAttr("checked");
    }
}

//判断选中的产品中是否还有另外一个  有则忽视  添加
function addSelect(m) {
    for (var i = 0; i < supplierCodeList.length; i++) {
        if (supplierCodeList[i] == m) {
            return;
        }
    }
    supplierCodeList.push(m);
}

//判断选中的产品中是否还有另外一个  删除
function removeSelect(m) {
    for (var i = 0; i < supplierCodeList.length; i++) {
        if (supplierCodeList[i] == m) {
            supplierCodeList.splice(i, 1);
            return;
        }
    }
}


