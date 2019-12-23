var table;
var allList = [];
var TableDatatablesButtons = function () {
    var initDataTable = function () {
        table = $('#merchandList');
        var oTable = table.dataTable({
            buttons: [
                {extend: '', className: 'btn blue btn-outline addMerchand', text: '新增企划属性'},
                {extend: '', className: 'btn green btn-outline btnLeadIn', text: '批量导入'},
                {extend: 'colvis', className: 'btn red btn-outline showcolumn', text: '显示隐藏列'},
                {
                    extend: 'excel',
                    text: '导出excel',//定义导出excel按钮的文字
                    title: '企划信息列表',
                    className: 'btn purple btn-outline',
                    exportOptions: {
                        modifier: {
                            page: 'current'
                        },
                        columns: '1,2,3,4,5,6,7,8,9'
                    }
                }
            ],
            "fnServerParams": function (aoData) {
                AddParams(aoData);
            },
            "processing": false,
            "language": {
                "search": "搜索产品编码：",
                "sInfoFiltered": ""
            },
            "ordering": false,
            "iDisplayLength": 20,
            "ajaxSource": webroot + "/dimension/listmerchand",
            'aoColumns': [
                {
                    "mData": "productCode",
                    "sClass": "detail",
                    "bSortable": false,
                    "mRender": function (data, type, full) {
                        var entiy = commonClass.replaceAllQuotationMarks(JSON.stringify(full));
                        var no = full.productCode;
                        var html = "<a href='" + webroot + "/dimension/addproductattribute?productCode=" + no + "' class='btn btn-xs green btn-outline' target='_blank' rel='tooltip' title='编辑'><i class='fa fa-pencil'></i></a>";
                        return html;
                    }
                },
                {"mData": "productCode", "sClass": "detail"},
                {
                    "mData": "planningLocation", "sClass": "detail", "mRender": function (data, type, full) {
                    if (data === 0) {
                        return "";
                    }
                    else {
                        return getItem(data, allList, "planning_location");
                    }
                }
                },
                {
                    "mData": "purchaseLocation", "sClass": "detail", "mRender": function (data, type, full) {
                    if (data === 0) {
                        return "";
                    }
                    else {
                        return getItem(data, allList, "purchase_location");
                    }
                }
                },
                {
                    "mData": "displayLocation", "sClass": "detail", "mRender": function (data, type, full) {
                    if (data === 0) {
                        return "";
                    }
                    else {
                        return getItem(data, allList, "display_location");
                    }
                }
                },
                {
                    "mData": "series", "sClass": "detail", "mRender": function (data, type, full) {
                    if (data === 0) {
                        return "";
                    }
                    else {
                        return getItem(data, allList, "series");
                    }
                }
                },
                {
                    "mData": "ecoWayMaterial", "sClass": "detail", "mRender": function (data, type, full) {
                    if (data === 0) {
                        return "";
                    }
                    else {
                        return getItem(data, allList, "eco_way_material");
                    }
                }
                },
                {
                    "mData": "ecoWayColor", "sClass": "detail", "mRender": function (data, type, full) {
                    if (data === 0) {
                        return "";
                    }
                    else {
                        return getItem(data, allList, "eco_way_color");
                    }
                }
                },
                {
                    "mData": "ecoWayTechnics", "sClass": "detail", "mRender": function (data, type, full) {
                    if (data === 0) {
                        return "";
                    }
                    else {
                        return getItem(data, allList, "eco_way_technics");
                    }
                }
                },
                {
                    "mData": "officialWeibo", "sClass": "detail", "mRender": function (data, type, full) {
                    if (data == null) {
                        return "";
                    }
                    else {
                        if (data) {
                            return "是";
                        }
                        else {
                            return "否";
                        }
                        // return getItem(data, allList, "officialweibo");
                    }
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
            $(".btnLeadIn").click(function () {
                commonClass.clearFileinput();
                $("#leadIn").modal("show");
            });
            $('.addMerchand').click(function () {
                location.href = webroot + "/dimension/addproductattribute";
            });
        }
    };

    function AddParams(aoData) {
        $('#loading').modal('show');
    }
}();

$('.colvis').on('change', function (e) {
    e.preventDefault();
    console.log($(this).attr('data-column'));
    var column = table.column($(this).attr('data-column'));
    column.visible(!column.visible());
});

$(function () {
    $.ajax({
        type: "POST",
        url: webroot + "/dimension/listitem",
        success: function (response) {
            if (response.code == 200) {
                allList = response.data;
            }
            else {
                allList = [];
            }
            TableDatatablesButtons.init();
        },
        error: function () {
            allList = [];
            TableDatatablesButtons.init();
        }
    });

    $(".close").click(function () {
        $("#errorShow").modal('hide');
    });
    $(".cancel").click(function () {
        $("#leadIn").modal('hide');
    });
    //企划属性值上传
    $("#merchandUpLoad").click(function () {
        if (jQuery("input[type='file']").val() == "") {
            //您还没有选择文件
            swal(ICICLELangUtil.getText("", 1067), "", "warning");
            return;
        }
        uploadFile();
    });
})

function getItem(data, list, codeName) {
    var arr = [];
    var resultList = $.grep(list, function (value) {
        return value.defCode == codeName;
    });
    $.each(resultList, function (id, obj) {
        if ((data & obj.code) === obj.code) {
            arr.push(obj.name);
        }
    })
    return arr.join(",");
}

function uploadFile() {
    var formdata = new FormData(document.getElementById("upLoadFileForm"));
    $.ajax({
        type: "POST",
        url: webroot + "/dimension/importmerchandising",
        data: formdata,
        processData: false,
        contentType: false,
        success: function (response) {
            if (response.code == 200) {
                swal({
                        //成功,确定
                        title: ICICLELangUtil.getText("", 1005),
                        text: "本次成功导入数据" + response.data + "条",
                        type: "success",
                        showCancelButton: false,
                        confirmButtonClass: "btn-success",
                        confirmButtonText: ICICLELangUtil.getText(language,1013),
                        closeOnConfirm: true
                    },
                    function () {
                        table.fnDraw();
                        $("#leadIn").modal('hide');
                    });
            }
            else {
                swal(response.message, "", "error");
            }
        },
        beforeSend: function (XMLHttpRequest) {
            $('#loading').modal('show');
            $("#merchandUpLoad").attr("disabled", "disabled");
        },
        complete: function (XMLHttpRequest, textStatus) {
            $("#merchandUpLoad").removeAttr("disabled");
            $('#loading').modal('hide');
        },
        error: function () {
            //程序出现未知错误，请稍候重试
            swal(ICICLELangUtil.getText("",1035), "", "error");
        }
    })
}