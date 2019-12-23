var cityLevelList = [];
var areaList = [];
var costcenterIdList = [];
var table;
var TableDatatablesButtons = function () {
    var initDataTable = function () {
        table = $('#costcenterlist');
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
            "ajaxSource": webroot + "/finance/costcenterlist",
            'aoColumns': [
                {
                    "mData": "costcenterId",
                    "sClass": "detail",
                    "bSortable": false,
                    "mRender": function (data, type, row) {
                        var entity = JSON.stringify(row);
                        var html = "<label class=\"mt-checkbox\"><input name='checkbox' type=\"checkbox\" data-entity='" + entity + "' onclick='rememberCheck(this)' value='' /><span></span></label>";
                        return html;
                    }
                },
                {"mData": "costcenterId", "sClass": "detail"},
                {"mData": "lanName", "sClass": "detail"},
                {"mData": "costCenterName", "sClass": "detail"},
                {"mData": "costcenterManager", "sClass": "detail"},
                {"mData": "departmentId", "sClass": "detail"},
                {"mData": "costcenterChief", "sClass": "detail"},
                {"mData": "costcenterVp", "sClass": "detail"},
               {
                    "mData": "buyerId", "sClass": "detail", "bSortable": false, "mRender": function (data, type, full) {
                        var entiy = commonClass.replaceAllQuotationMarks(JSON.stringify(full));
                        var costcenterId = full.costcenterId;
                        var lanCode = full.lanCode;
                        var html = "<a href='" + webroot + "/finance/costcenterinfo?costcenterId=" + costcenterId + "&lanCode="+ lanCode + "' class='btn btn-xs green btn-outline' rel='tooltip' title='查看详情' data-entiy='" + entiy + "' ><i class='fa fa-file-text-o'></i></a>";
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
    aoData.push({"name": "costcenterId", "value": commonClass.getStringVal($('#costcenterId').val())});
    aoData.push({"name": "lanCode", "value": commonClass.getStringVal($('#lanCode').val())});
    $('#loading').modal('show');
}

$(function () {
    loadLanguage();
    TableDatatablesButtons.init();

    document.onkeydown = function (e) {
        if (!e) e = window.event;
        if ((e.keyCode || e.which) == 13) {
            event.preventDefault();
            table.fnDraw();
        }
    }


    $(".excelExport").click(function () {
        $("#costcenterIdOrName").val($('#costcenterId').val());
        $("#lanCodes").val($('#lanCode').val());

        $("#frmMoedlDownload").attr("action", webroot + "/finance/downLoadCostcenterExcel");
        $("#frmMoedlDownload").attr("method", "POST");
        $("#frmMoedlDownload").submit();
    });

    $(".btnSyn").click(function () {
        var costcenterIds = costcenterIdList.join(',');
        if (costcenterIdList.length == 0) {
            swal("您没有选择任何选项，无法提交", "", "warning");
        } else {
            swal({
                    title: "确定将所选项目同步?",
                    text: "您一共选择了" + costcenterIdList.length + "项",
                    type: "warning",
                    showCancelButton: true,
                    cancelButtonText: "取消",
                    confirmButtonClass: "btn-danger",
                    confirmButtonText: "确定",
                    closeOnConfirm: false
                },
                function () {
                    var langage = $("#lanCode").val();
                    $.ajax({
                        type: "POST",
                        url: webroot + "/finance/syncostcenter",
                        data: {
                            "costcenterIds": costcenterIds,
                            "lanCode": langage
                        },
                        success: function (response) {
                            var message = response.message==null?"":response.message;
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
                                        if (costcenterIdList != null && costcenterIdList.length != 0) {
                                            costcenterIdList.splice(0, costcenterIdList.length);
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


function loadLanguage() {
    $.ajax({
        type: "GET",
        url: webroot + "/hr/getlanguage",
        data: {
        },
        success: function (response) {
            if(response.code==200){
                var ele = "";
                var languagelist = response.data;
                for(var i = 0 ;i < languagelist.length; i++){
                    var language = languagelist[i];
                    ele+= "<option value='"+language.lanCode+"'>"+language.lanName+"</option>";
                }
                $("#lanCode").append(ele);
            }else{
                swal(ICICLELangUtil.getText("", 1163), ICICLELangUtil.getText("", 1064), "error");
            }

        },
        error: function () {
            swal(ICICLELangUtil.getText("", 1163), ICICLELangUtil.getText("", 1064), "error");
        },
        beforeSend: function (XMLHttpRequest) {
            $('#loading').modal('show');
        },
        complete: function (XMLHttpRequest, textStatus) {
            $('#loading').modal('hide');
        }
    });
}

//全选按钮
function selectAllCheckBox(curr) {
    //全选按钮是否被选中
    var flag = $(curr).is(":checked");
    if (flag) {
        costcenterIdList.splice(0, costcenterIdList.length);
        $("[name='checkbox']").prop("checked", true);
        $("[name='checkbox']").each(function (index, element) {
            if (index != 0) {
                var entiy = $(element).data("entity");
                addSelect(entiy.costcenterId);
                $(element).attr("checked", true);
            }
        });
    } else {
        costcenterIdList.splice(0, costcenterIdList.length);
        $("[name='checkbox']").prop("checked", false);
    }
}

function rememberCheck(cur) {
    var entity = $(cur).data("entity");
    if ($(cur).is(':checked')) {
        addSelect(entity.costcenterId);
    } else {
        removeSelect(entity.costcenterId);
    }
    if (costcenterIdList.length == $("[name='datatable_length']")) {
        $("#chooseAll").attr("checked", true);
    } else {
        $("#chooseAll").removeAttr("checked");
    }
}

//判断选中的产品中是否还有另外一个  有则忽视  添加
function addSelect(m) {
    for (var i = 0; i < costcenterIdList.length; i++) {
        if (costcenterIdList[i] == m) {
            return;
        }
    }
    costcenterIdList.push(m);
}

//判断选中的产品中是否还有另外一个  删除
function removeSelect(m) {
    for (var i = 0; i < costcenterIdList.length; i++) {
        if (costcenterIdList[i] == m) {
            costcenterIdList.splice(i, 1);
            return;
        }
    }
}

