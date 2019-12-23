var table;
var itemList = [];
var TableDatatablesButtons = function () {
    var initDataTable = function () {
        table = $('#colorCardList').dataTable({
            buttons: [
                {extend: '', className: 'dt-button btn red btn-outline addItem', text: '新增选项'},
                {extend: '', className: 'btn green btn-outline btnSyn', text: '批量同步'},
                {extend: '', className: 'btn blue btn-outline excelExport', text: '导出excel'},
            ],
            "fnServerParams": function (aoData) {
                AddParams(aoData);
            },
            "processing": false,
            "ordering": true,
            "order": [[5, "desc"]],
            "iDisplayLength": 20,
            "ajaxSource": webroot + "/product/itemlist",
            'aoColumns': [
                {
                    "mData": "no",
                    "sClass": "detail",
                    "bSortable": false,
                    "mRender": function (data, type, row) {
                        var entity = JSON.stringify(row);
                        var html = "<label class=\"mt-checkbox\"><input name='checkbox' type=\"checkbox\" data-entity='" + entity + "' onclick='rememberCheck(this)' value='' /><span></span></label>";
                        return html;
                    }
                },
                {
                    "mData": "no",
                    "sClass": "detail",
                    "bSortable": false,
                    "mRender": function (data, type, row) {
                        var entity = JSON.stringify(row);
                        html = "";
                        if(row.tblNum ==1){
                            html+=  "<a href='javascript:;' class='btn btn-xs green btn-outline' rel='tooltip' data-entiy='" + entity + "' onclick='editItem(this)'  title='编辑'><i class='fa fa-pencil'></i></a>";
                        }
                        return html;
                    }
                },
                {"mData": "description", "sClass": "detail"},
                {"mData": "itemKey", "sClass": "detail"},
                {"mData": "itemValue", "sClass": "detail"},
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
                    'targets': [1,2,3,4,5],
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
        aoData.push({"name": "itemCategory", "value": commonClass.getStringVal($('#itemCategory').val())});
        aoData.push({"name": "itemSelect", "value": commonClass.getStringVal($('#itemSelect').val())});
        $('#loading').modal('show');
    }
}();
$(function () {
    TableDatatablesButtons.init();
    $("#colorCardList_filter").hide();
    document.onkeydown = function (e) {
        if (!e) e = window.event;
        if ((e.keyCode || e.which) == 13) {
            event.preventDefault();
            table.fnDraw();
        }
    }

    $(".addItem").click(function () {
        $("#editItem").modal("show");
        $("#colorCardTile").text("新增选项");
        $("#id").val("");
        $("#tableName").removeAttr("disabled");
        $("#itemValue").val("");
        $("#itemKey").val("");
        $("#state").hide();
        getItemTable("");
        $("#insertFlag").val(1);
    });

    $(".btnSyn").click(function () {
        if (itemList.length == 0) {
            return swal("您没有选择任何选项，无法提交", "", "warning");
        } else {
            $('#syncItemListDiv').modal('show');
            $("#total").text(itemList.length);
        };
    });


    $('#colorCardList tbody').on('dblclick', 'tr', function () {
        var aData = table.fnGetData(this);
        var tableName = aData.tableName;
        showAddData(tableName);
    });

    $(".excelExport").click(function () {
        $("#itemCategorys").val($("#itemCategory").val());
        $("#itemSelects").val($("#itemSelect").val());
        $("#frmMoedlDownload").attr("action", webroot + "/product/downLoadItemExcel");
        $("#frmMoedlDownload").attr("method", "POST");
        $("#frmMoedlDownload").submit();
    });

    $("#saveItem").click(function () {
        var id = commonClass.replaceAllQuotationMarks($("#id").val());
        var tableName = commonClass.replaceAllQuotationMarks($("#tableName").val()).toLocaleUpperCase();
        var itemKey = commonClass.replaceAllQuotationMarks($("#itemKey").val());
        var itemValue = commonClass.replaceAllQuotationMarks($("#itemValue").val());
        var insertFlag = $("#insertFlag").val();
        if (tableName.length == 0 || itemKey.length == 0 || itemValue.length == 0) {
            swal("数据填写不完整，请继续填写", "", "warning");
            return;
        }
        if (tableName.length > 20) {
            swal("选项类别获取错误，请正确选择填写", "", "warning");
            return;
        }
        var requestType = "";
        var requestUrl = "";
        var requestData = {
            tableName: tableName,
            itemKey: itemKey,
            itemValue: itemValue
        }
        if (insertFlag == 0) {
            requestData["id"] = id;
            requestType = "PUT";
            requestUrl = webroot + "/product/itemupdate";
        }
        else {
            requestType = "POST";
            requestUrl = webroot + "/product/iteminsert";
        }
        save(requestType, requestUrl, requestData);
    });
});

function showAddData(tableName) {
    $("#saveItem").val(1);//新增状态
    $("#editItem").modal("show");
    $("#colorCardTile").text("新增选项");
    $("#id").val("");
    $("#tableName").val(tableName);
    $("#tableName").attr("disabled", "disabled");
    $("#itemValue").val("");
    $("#itemKey").val("");
    $("#state").hide();
    $("#hasStatus").removeClass("active");
    $("#noStatus").removeClass("active");
}

function editItem(cuur) {
    var entiy = $(cuur).data('entiy');
    $("#state").show();
    $("#saveItem").val(0);//更新状态
    $("#editItem").modal("show");
    $("#colorCardTile").text("编辑选项");
    getItemTable(entiy.tableName);
    $("#insertFlag").val(0);
    $("#id").val(entiy.id);
    $("#tableName").attr("disabled", "disabled");
    $("#itemKey").val(entiy.itemKey);
    $("#itemValue").val(entiy.itemValue);
    $("#hasStatus").addClass("active");
    $("#noStatus").removeClass("active");
}

function save(requestType, requestUrl, requestData) {
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
                        table.fnDraw();
                        $("#editItem").modal("hide");
                    });
            }
            else {
                swal(response.message, "", "error");
            }
        },
        beforeSend: function (XMLHttpRequest) {
            $("#saveItem").attr("disabled", "disabled");
            $('#loading').modal('show');
        },
        complete: function (XMLHttpRequest, textStatus) {
            $("#saveItem").removeAttr("disabled");
            $('#loading').modal('hide');
        },
        error: function () {
            //程序出现未知错误，请稍候重试
            swal(ICICLELangUtil.getText("",1035), "", "error");
        }
    });
}


//全选按钮
function selectAllCheckBox(curr) {
    //全选按钮是否被选中
    var flag = $(curr).is(":checked");
    if (flag) {
        itemList.splice(0, itemList.length);
        $("[name='checkbox']").prop("checked", true);
        $("[name='checkbox']").each(function (index, element) {
            if (index != 0) {
                $(element).attr("checked", true);
                var entiy = $(element).data("entity");
                addSelect(entiy.no);
            }
        });
    } else {
        itemList.splice(0, itemList.length);
        $("[name='checkbox']").prop("checked", false);
    }
}

function rememberCheck(cur) {
    var entity = $(cur).data("entity");
    if ($(cur).is(':checked')) {
        addSelect(entity.no);
    } else {
        removeSelect(entity.no);
    }
    if (itemList.length == $("[name='datatable_length']")) {
        $("#chooseAll").attr("checked", true);
    } else {
        $("#chooseAll").removeAttr("checked");
    }
}

//判断选中的产品中是否还有另外一个  有则忽视  添加
function addSelect(m) {
    for (var i = 0; i < itemList.length; i++) {
        if (itemList[i] == m) {
            return;
        }
    }
    itemList.push(m);
}

//判断选中的产品中是否还有另外一个  删除
function removeSelect(m) {
    for (var i = 0; i < itemList.length; i++) {
        if (itemList[i] == m) {
            itemList.splice(i, 1);
            return;
        }
    }
}


function getItemTable(value){
    $.ajax({
        url: webroot + "/product/getProductSelectItem",
        method: "GET",
        success: function (response) {
            if(response.code == 200){
                var tableList = response.data.itemTableList;
                if(tableList!=null){
                    $("#tableName").html("");
                    for(var i = 0; i < tableList.length; i++){
                        var tableName = tableList[i];
                        var option = "<option value='"+tableName+"'>"+tableName+"</option>";
                        $("#tableName").append(option);
                        if(tableName == value){
                            $("#tableName").val(value).select2();
                        }
                    }
                }
            }else{
                return swal("获取选项类别列表失败，请稍后再试", "", "warning");
            }
        },
        error:function(){
            return swal("获取选项类别列表异常", "", "warning");
        }
    });
}

function syncItemList(){
    $('#syncProductListDiv').modal('hide');
    var items = itemList.join(',');
    var syncSelect = $("#synSetting").val();
    var data = {
        'items': items,
        'syncSelect': syncSelect
    };
    $.ajax({
        type: "POST",
        url: webroot + "/product/syncItem",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(data),
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
                        if (itemList != null && itemList.length != 0) {
                            itemList.splice(0, itemList.length);
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