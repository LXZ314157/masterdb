var table;
var productPartList = [];
var TableDatatablesButtons = function () {
    var initDataTable = function () {
        table = $('#productList');
        var oTable = table.dataTable({
            buttons: [
                {extend: '', className: 'btn green btn-outline btnSyn', text: '批量同步'},
                {extend: 'colvis', className: 'btn red btn-outline showcolumn', text: '显示隐藏列'},
                {
                    extend: 'excel',
                    text: '导出excel',//定义导出excel按钮的文字
                    title: '款式待处理列表',
                    className: 'btn blue btn-outline',
                    exportOptions: {
                        modifier: {
                            page: 'current'
                        },
                        columns: '2,3,4,5,6,7,8,9,10,11,12,13,14'
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
            "order": [[14, "desc"]],
            "iDisplayLength": 20,
            "ajaxSource": webroot + "/product/messagelist",
            'aoColumns': [
                {
                    "mData": "productCode",
                    "sClass": "detail",
                    "bSortable": false,
                    "mRender": function (data, type, row) {
                        var entity = JSON.stringify(row);
                        var html = "<label class=\"mt-checkbox\"><input name='checkbox' type=\"checkbox\" data-entity='" + entity + "' onclick='rememberCheck(this)' value='' /><span></span></label>";
                        return html;
                    }
                },
                {
                    "mData": "productCode",
                    "sClass": "detail",
                    "bSortable": false,
                    "mRender": function (data, type, full) {
                        var no = full.productCode;
                        var productCategoryCode = full.productCategoryCode;
                        var html = "<a href='" + webroot + "/product/psizeuupdate?productCode=" + no + "&productCategoryCode=" + productCategoryCode + "&id=0' class='btn btn-xs blue btn-outline' target='_blank' rel='tooltip' title='尺寸'><i class='fa fa-adjust'></i></a>";
                        html += "<a href='" + webroot + "/product/pstyleupdate?productCode=" + no + "&productCategoryCode=" + productCategoryCode + "' class='btn btn-xs green btn-outline' target='_blank' rel='tooltip' title='款式'><i class='fa fa-edit'></i></a>";
                        //未同步过
                        if (full.syncRecord == 0) {
                            html += "<a href='javascript:;' class='btn btn-xs red btn-outline' rel='tooltip' title='删除' data-entiy='" + no + "' onclick='deleteSize(this)'><i class='fa fa-remove'></i></a>";
                        }
                        return html;
                    }
                },
                {"mData": "productCode", "sClass": "detail"},
                {"mData": "productName", "sClass": "detail"},
                {"mData": "unitPrice", "sClass": "detail"},
                {"mData": "uom", "sClass": "detail"},
                {"mData": "cateDl", "sClass": "detail"},
                {"mData": "cateZl", "sClass": "detail"},
                {"mData": "cateXl", "sClass": "detail"},
                {"mData": "materialNo", "sClass": "detail"},
                {"mData": "materialName", "sClass": "detail"},
                {"mData": "productDesc", "sClass": "detail"},
                {"mData": "brand", "sClass": "detail"},
                {
                    "mData": "status", "sClass": "detail",
                    'mRender': function (data, type, row) {
                        if (row.status == 1) {
                            return "可用";
                        }
                        else {
                            return "不可用";
                        }
                    }
                },
                {"mData": "saleRate", "sClass": "detail"},
                {"mData": "purchaseRate", "sClass": "detail"},
                {"mData": "lastUpdateDate", "sClass": "detail"},
                {"mData": "creationDate", "sClass": "detail"}
            ],
            "aoColumnDefs": [
                {//排序
                    'targets': [0,1,2,3,4,5,6,7,8,9,10,11,12,13,15],//不排序的列
                    'orderable': false
                },
                {
                    "targets": [5,7,8,11,14,15,17], //隐藏列
                    "visible": false
                },
                {//状态判断翻译
                    'aTargets': [18],
                    'mRender': function (data, type, row) {
                        if (row.syncStatus == 1) {
                            return "有更新";
                        }
                        if (row.syncStatus == 2) {
                            return "新增";
                        }
                        else {
                            return "已更新";
                        }

                    },
                },
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
        aoData.push({"name": "type", "value": 1});
        aoData.push({"name": "batchProductCode", "value": ""});
        aoData.push({"name": "productCodeOrName", "value": commonClass.getStringVal($('#productCodeOrName').val())});
        aoData.push({"name": "startTime", "value": commonClass.getStringVal($('#startTime').val())});
        aoData.push({"name": "endTime", "value": commonClass.getStringVal($('#endTime').val())});
        aoData.push({"name": "syncStatus", "value": commonClass.getStringVal($('#syncStatus').val())});
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
    $("#productList_filter").hide();
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
        var productCodes = productPartList.join(',');
        if (productPartList.length == 0) {
            swal("您没有选择任何选项，无法提交", "", "warning");
        } else {
            $('#syncProductListDiv').modal('show');
            $("#total").text(productPartList.length);
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
        productPartList.splice(0, productPartList.length);
        $("[name='checkbox']").prop("checked", true);
        $("[name='checkbox']").each(function (index, element) {
            if (index != 0) {
                $(element).attr("checked", true);
                var entiy = $(element).data("entity");
                addSelect(entiy.productCode);
            }
        });
    } else {
        productPartList.splice(0, productPartList.length);
        $("[name='checkbox']").prop("checked", false);
    }
}

function rememberCheck(cur) {
    var entity = $(cur).data("entity");
    if ($(cur).is(':checked')) {
        addSelect(entity.productCode);
    } else {
        removeSelect(entity.productCode);
    }
    if (productPartList.length == $("[name='datatable_length']")) {
        $("#chooseAll").attr("checked", true);
    } else {
        $("#chooseAll").removeAttr("checked");
    }
}

//判断选中的产品中是否还有另外一个  有则忽视  添加
function addSelect(m) {
    for (var i = 0; i < productPartList.length; i++) {
        if (productPartList[i] == m) {
            return;
        }
    }
    productPartList.push(m);
}

//判断选中的产品中是否还有另外一个  删除
function removeSelect(m) {
    for (var i = 0; i < productPartList.length; i++) {
        if (productPartList[i] == m) {
            productPartList.splice(i, 1);
            return;
        }
    }
}

//尺码删除功能
function deleteSize(curr) {
    var productCode = $(curr).data("entiy");
    swal({
            title: "确认删除款式信息和所有尺寸?",
            text: "该款式和尺寸将不会显示在您的页面中，请谨慎处理！",
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
                url: webroot + "/product/deletesize/" + productCode,
                success: function (data) {
                    if (data.code == 200) {
                        swal({
                                title: "成功删除该属性，页面上将不再显示！",
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

function syncProductList(){
    $('#syncProductListDiv').modal('hide');
    var productCodes = productPartList.join(',');
    var syncSelect = $("#synSetting").val();
    if (syncSelect == null){
       return swal("您未选择同步平台", "", "warning");
    }
    var data = {
        "productCodes": productCodes,
        "syncSelect": syncSelect
    };
    $.ajax({
        type: "POST",
        url: webroot + "/product/synproduct",
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
                        //完成后，把所有选中的选项清空
                        if (productPartList != null && productPartList.length != 0) {
                            productPartList.splice(0, productPartList.length);
                        }
                        // table.fnDraw();
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
