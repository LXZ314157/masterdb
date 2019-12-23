var table;
var skuIdList = [];
var TableDatatablesButtons = function () {
    var initDataTable = function () {
        table = $('#skulist');
        var oTable = table.dataTable({
            buttons: [
                {extend: '', className: 'btn green btn-outline btnSyn', text: '批量同步'},
                // {extend: '', className: 'btn red btn-outline btnDelete', text: '批量删除'},
                {
                    extend: 'excel',
                    text: '导出excel',
                    title: '条码信息列表',
                    className: 'btn blue btn-outline',
                    exportOptions: {
                        modifier: {
                            page: 'current'
                        },
                        columns: '2,3,4,5,6,7,8,9,10,11,12'
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
            "fnServerParams": function (aoData) {
                AddParams(aoData);
            },
            "processing": false,
            "ordering": true,
            "order": [[12, "desc"]],
            "iDisplayLength": 20,
            "ajaxSource": webroot + "/product/skulist",
            'aoColumns': [
                {
                    "mData": "id",
                    "sClass": "detail",
                    "bSortable": false,
                    "mRender": function (data, type, row) {
                        var entity = JSON.stringify(row);
                        var html = "<label class=\"mt-checkbox\"><input name='checkbox' type=\"checkbox\" data-entity='" + entity + "' onclick='rememberCheck(this)' value='' /><span></span></label>";
                        return html;
                    }
                },
                {
                    "mData": "id",
                    "sClass": "detail",
                    "bSortable": false,
                    "mRender": function (data, type, full) {
                        var productCode = full.productCode;
                        var id = full.id;
                        var productCategoryCode = full.productCategoryCode;
                        var html = "<a href='" + webroot + "/product/psizeuupdate?productCode=" + productCode + "&productCategoryCode=" + productCategoryCode + "&id=" + id + "' class='btn btn-xs blue btn-outline' target='_blank' rel='tooltip' title='编辑'><i class='fa fa-edit'></i></a>";
                        return html;
                    }
                },

                {"mData": "productCode", "sClass": "detail"},
                {"mData": "sku", "sClass": "detail"},
                {"mData": "color", "sClass": "detail"},
                {"mData": "batch", "sClass": "detail"},
                {"mData": "size", "sClass": "detail"},
                {"mData": "standardPrice", "sClass": "detail"},
                {"mData": "standardCost", "sClass": "detail"},
                {"mData": "saleCost", "sClass": "detail"},
                {"mData": "securityCode", "sClass": "detail"},
                {
                    "mData": "status", "sClass": "detail",
                    'mRender': function (data, type, row) {
                        if (row.status == 1) {
                            return "有效";
                        }
                        else {
                            return "无效";
                        }
                    }
                },
                {"mData": "lastUpdateDate", "sClass": "detail"}
            ],
            "aoColumnDefs": [
                {//排序
                    'targets': [0,1,2,3,4,5,6,7,8,9,10,11],//不排序的列
                    'orderable': false
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
        aoData.push({"name": "batchSku", "value": commonClass.getStringVal($('#batchSku').val())});
        aoData.push({"name": "productCodeOrSku", "value": commonClass.getStringVal($('#productCodeOrSku').val())});
        aoData.push({"name": "startTime", "value": commonClass.getStringVal($('#startTime').val())});
        aoData.push({"name": "endTime", "value": commonClass.getStringVal($('#endTime').val())});
        $('#loading').modal('show');
        $("#chooseAll").removeAttr("checked");
    }
}();

$('.colvis').on('change', function (e) {
    e.preventDefault();
    console.log($(this).attr('data-column'));
    var column = table.column($(this).attr('data-column'));
    column.visible(!column.visible());
});

function checkTime(){
    var startTime =  $('#startTime').val();
    var endTime =  $('#endTime').val();
    if(startTime!="" && endTime!=""){
        if(startTime>endTime){
            return swal("起始更新时间不能大于结束更新时间", "", "warning");
        }
    }

}

$(function () {
    TableDatatablesButtons.init();
    $("#skulist_filter").hide();
    commonClass.initDateTimePicker();
    $('#startime').datetimepicker({format:"yyyy-mm-dd hh:ii:ss",language:"zh-CN",todayHighlight:true,todayBtn:true});
    $('#endTime').datetimepicker({format:"yyyy-mm-dd hh:ii:ss",language:"zh-CN",todayHighlight:true,todayBtn:true});
    document.onkeydown = function (e) {
        if (!e) e = window.event;
        if ((e.keyCode || e.which) == 13) {
            event.preventDefault();
            checkTime();
            table.fnDraw();
        }
    }

    $(".btnSyn").click(function () {
        if (skuIdList.length == 0) {
            return swal("您没有选择任何选项，无法提交", "", "warning");
        } else {
            $('#syncSkuListDiv').modal('show');
            $("#total").text(skuIdList.length);
        };
    });

    $(".btnDelete").click(function () {
        if (skuIdList.length == 0) {
            return swal("您没有选择任何选项，无法删除", "", "warning");
        } else {
            batchDeleteSku();
        };
    });

    $('#productList tbody').on('dblclick', 'tr', function () {
        var aData = table.fnGetData(this);
        var no = aData.productCode;
        var color = aData.colorName;
        location.href = webroot + "/product/psizeuupdate?productCode=" + no + "&color=" + color + "";
    });
});

//全选按钮
function selectAllCheckBox(curr) {
    //全选按钮是否被选中
    var flag = $(curr).is(":checked");
    if (flag) {
        skuIdList.splice(0, skuIdList.length);
        $("[name='checkbox']").prop("checked", true);
        $("[name='checkbox']").each(function (index, element) {
            if (index != 0) {
                $(element).attr("checked", true);
                var entiy = $(element).data("entity");
                addSelect(entiy.id);
            }
        });
    } else {
        skuIdList.splice(0, skuIdList.length);
        $("[name='checkbox']").prop("checked", false);
    }
}

function rememberCheck(cur) {
    var entity = $(cur).data("entity");
    if ($(cur).is(':checked')) {
        addSelect(entity.id);
    } else {
        removeSelect(entity.id);
    }
    if (skuIdList.length == $("[name='datatable_length']")) {
        $("#chooseAll").attr("checked", true);
    } else {
        $("#chooseAll").removeAttr("checked");
    }
}

//判断选中的产品中是否还有另外一个  有则忽视  添加
function addSelect(m) {
    for (var i = 0; i < skuIdList.length; i++) {
        if (skuIdList[i] == m) {
            return;
        }
    }
    skuIdList.push(m);
}

//判断选中的产品中是否还有另外一个  删除
function removeSelect(m) {
    for (var i = 0; i < skuIdList.length; i++) {
        if (skuIdList[i] == m) {
            skuIdList.splice(i, 1);
            return;
        }
    }
}


function syncSkuList(){
    $('#syncSkuListDiv').modal('hide');
    var ids = skuIdList.join(',');
    var syncSelect = $("#synSetting").val();
    if(syncSelect==null){
        return swal("您还未选择同步平台", "", "warning");
    }
    var data = {
        "ids": ids,
        "syncSelect": syncSelect
    };
    $.ajax({
        type: "POST",
        url: webroot + "/product/syncsku",
        data: JSON.stringify(data),
        contentType: "application/json;charset=utf-8",
        success: function (response) {
            if (response.code == 200) {
                swal({
                        title: response.message,
                        text: "",
                        type: "success",
                        showCancelButton: false,
                        confirmButtonClass: "btn-success",
                        confirmButtonTeaxt: "确定",
                        closeOnConfirm: true
                    },
                    function () {
                        if (skuIdList != null && skuIdList.length != 0) {
                            skuIdList.splice(0, skuIdList.length);
                        }
                        window.location.reload(true);
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
}

//条码批量删除功能
function batchDeleteSku() {
    var ids = skuIdList.join(',');
    var len = skuIdList.length;
    swal({
            title: "确认删除该"+len+"个条码信息?",
            text: "该条码将不会显示在您的页面中，请谨慎处理！",
            type: "warning",
            showCancelButton: true,
            confirmButtonClass: "btn-success",
            confirmButtonText: "删除",
            cancelButtonText: "取消",
            closeOnConfirm: false
        },
        function () {
            $.ajax({
                type: "DELETE",
                url: webroot + "/product/batchdeletesku/"+ids,
                success: function (data) {
                    if (data.code == 200) {
                        swal({
                                title: "成功删除，页面上将不再显示！",
                                text: "",
                                type: "success",
                                showCancelButton: false,
                                confirmButtonClass: "btn-success",
                                confirmButtonText: "确定",
                                closeOnConfirm: true
                            },
                            function () {
                                if (skuIdList != null && skuIdList.length != 0) {
                                    skuIdList.splice(0, skuIdList.length);
                                }
                                window.location.reload(true);
                            });
                    } else {
                        swal(data.message, "", "warning");
                    }
                },
                beforeSend: function (XMLHttpRequest) {
                    $('#loading').modal('show');
                },
                complete: function (XMLHttpRequest, textStatus) {
                    $('#loading').modal('hide');
                },
                error: function () {
                    //程序出现未知错误，请稍候重试
                    swal(ICICLELangUtil.getText("", 1035), "", "error");
                }

            })
        });
}