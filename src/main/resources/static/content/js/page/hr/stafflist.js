var cityLevelList = [];
var areaList = [];
var staffNumList = [];
var table;
var TableDatatablesButtons = function () {
    var initDataTable = function () {
        table = $('#stafflist');
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
            "ajaxSource": webroot + "/hr/stafflist",
            'aoColumns': [
                {
                    "mData": "staffNum",
                    "sClass": "detail",
                    "bSortable": false,
                    "mRender": function (data, type, row) {
                        var entity = JSON.stringify(row);
                        var referenceId2 = row.referenceId2;
                        if(referenceId2=="1"){
                            var html = "<label class=\"mt-checkbox\"><input name='checkbox' type=\"checkbox\" data-entity='" + entity + "' onclick='rememberCheck(this)' value='' /><span style='background-color: #d0d0d0'></span></label>";
                        }else{
                            var html = "<label class=\"mt-checkbox\"><input name='checkbox' type=\"checkbox\" data-entity='" + entity + "' onclick='rememberCheck(this)' value='' /><span></span></label>";
                        }

                        return html;
                    }
                },
                {"mData": "staffNum", "sClass": "detail"},
                {"mData": "staffNameLocal", "sClass": "detail"},
                {"mData": "gender", "sClass": "detail"},
                {"mData": "company", "sClass": "detail"},
                {"mData": "departmentName", "sClass": "detail"},
                {"mData": "postName", "sClass": "detail"},
                {"mData": "joinDate", "sClass": "detail"},
                {"mData": "staffStateName", "sClass": "detail"},
                 {
                    "mData": "buyerId", "sClass": "detail", "bSortable": false, "mRender": function (data, type, full) {
                        var entiy = commonClass.replaceAllQuotationMarks(JSON.stringify(full));
                        var staffNum = full.staffNum;
                        var html = "<a href='" + webroot + "/hr/viewstaffdetail?staffNum=" + staffNum + "' class='btn btn-xs green btn-outline' rel='tooltip' title='查看详情' data-entiy='" + entiy + "' ><i class='fa fa-file-text-o'></i></a>";
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
    aoData.push({"name": "staffNumOrName", "value": commonClass.getStringVal($('#staffNumOrName').val())});
    aoData.push({"name": "staffState", "value": commonClass.getStringVal($('#staffState').val())});
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
        $("#staffNumOrNames").val($('#staffNumOrName').val());
        $("#staffStates").val($('#staffState').val());

        $("#frmMoedlDownload").attr("action", webroot + "/hr/downLoadStaffExcel");
        $("#frmMoedlDownload").attr("method", "POST");
        $("#frmMoedlDownload").submit();
    });

    $(".btnSyn").click(function () {
        var staffNums = staffNumList.join(',');
        if (staffNumList.length == 0) {
            swal("您没有选择任何选项，无法提交", "", "warning");
        } else {
            swal({
                    title: "确定将所选项目同步?",
                    text: "您一共选择了" + staffNumList.length + "项",
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
                        url: webroot + "/hr/syncstaff",
                        data: {
                            "staffNums": staffNums
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
                                        if (staffNumList != null && staffNumList.length != 0) {
                                            staffNumList.splice(0, staffNumList.length);
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
        staffNumList.splice(0, staffNumList.length);
        $("[name='checkbox']").prop("checked", true);
        $("[name='checkbox']").each(function (index, element) {
            if (index != 0) {
                var entiy = $(element).data("entity");
                addSelect(entiy.staffNum);
                $(element).attr("checked", true);
            }
        });
    } else {
        staffNumList.splice(0, staffNumList.length);
        $("[name='checkbox']").prop("checked", false);
    }
}

function rememberCheck(cur) {
    var entity = $(cur).data("entity");
    if ($(cur).is(':checked')) {
        addSelect(entity.staffNum);
    } else {
        removeSelect(entity.staffNum);
    }
    if (staffNumList.length == $("[name='datatable_length']")) {
        $("#chooseAll").attr("checked", true);
    } else {
        $("#chooseAll").removeAttr("checked");
    }
}

//判断选中的产品中是否还有另外一个  有则忽视  添加
function addSelect(m) {
    for (var i = 0; i < staffNumList.length; i++) {
        if (staffNumList[i] == m) {
            return;
        }
    }
    staffNumList.push(m);
}

//判断选中的产品中是否还有另外一个  删除
function removeSelect(m) {
    for (var i = 0; i < staffNumList.length; i++) {
        if (staffNumList[i] == m) {
            staffNumList.splice(i, 1);
            return;
        }
    }
}


