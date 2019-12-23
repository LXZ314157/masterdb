var table;
var buyerVOList = [];
var storeLevelVOList = [];
var storeTypeVOList = [];
var TableDatatablesButtons = function () {
    var initDataTable = function () {
        table = $('#datatable');
        var oTable = table.dataTable({
            buttons: [
                {extend: '', className: 'btn red btn-outline newStore', text: '新建店铺'},
                {extend: '', className: 'btn green btn-outline superSearch', text: '高级查询'},
                {extend: '', className: 'btn blue btn-outline excelExport', text: '导出excel'},
                ],
            "fnServerParams": function (aoData) {
                AddParams(aoData);
            },
            "language": {
                "search": "搜索店铺名/店铺ID",
            },
            "bSort": false,
            "ajaxSource": webroot + "/store/list",
            'aoColumns': [
                {"mData": "storeId", "sClass": "detail", "sWidth": "10%"}, //店铺no
                {"mData": "storeName", "sClass": "detail"}, //店铺名
                {"mData": "buyerName", "sClass": "detail"}, //代理商
                {"mData": "zoneName", "sClass": "detail"}, //所属分区
                {"mData": "storeContact", "sClass": "detail"}, //联系人
                {"mData": "storePhone", "sClass": "detail"}, //联系电话
                {"mData": "storeLevelName", "sClass": "detail"}, //店铺级别
                {"mData": "storeTypeName", "sClass": "detail"}, //店铺类型
                {"mData": "storeState", "sClass": "detail"}, //店铺状态

                {
                    "mData": "storeId", "sClass": "detail", "bSortable": false, "mRender": function (data, type, full) {
                        var storeNo = full.storeNo;
                        var html = "<a href='" + webroot + "/store/addstore?id=" + storeNo + "' class='btn btn-xs green btn-outline' rel='tooltip' title='店铺编辑'  d><i class='fa fa-pencil'></i></a>"+
                            "<a href='" + webroot + "/store/storeaddr?id=" + storeNo + "' class='btn btn-xs purple-seance btn-outline' rel='tooltip' title='地址编辑'  d><i class='fa fa-map'></i></a>";
                        return html;
                    }
                } //操作
            ],
            "aoColumnDefs": [
                {
                    'aTargets': [8],
                    'mRender': function (data, type, row) {
                        switch (row.storeState) {
                            case 0:
                                return "新店待开";
                            case 1:
                                return "正常";
                            case 2:
                                return "闭店未关";
                            case 3:
                                return "关闭";
                            default:
                                return "";
                        }

                    }
                },
            ],
            "fnDrawCallback": function () {
                $('#loading').modal('hide');
                $("#conditionArea").modal('hide');
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


$(function () {
    TableDatatablesButtons.init();

    $.ajax({
        url: webroot + "/store/getsearchcondition",
        type: "POST",
        success: function (response) {
            if (response.code == 200) {
                buyerVOList = response.data.buyerVOList;
                storeLevelVOList = response.data.storeLevelVOList;
                storeTypeVOList = response.data.storeTypeVOList;
                loadDropDownList();
                console.log(response.data);
            } else {
                console.log("数据拉取失败");
            }
        },
        error: function (response) {
            console.log("数据拉取失败");
        }
    });


//加载店铺级别
    $.ajax({
        url: webroot + "/store/levellist",
        type: "POST",
        success: function (response) {
            if (response.code == 200) {
                var levelList = response.data;
                if (levelList.length > 0) {
                    for (var i = 0; i < levelList.length; i++) {
                        var storeLevel = levelList[i];
                        $("#storeLevel").append("<option value='" + storeLevel.storeLevelId + "'>" + storeLevel.storeLevel + "</option>");
                    }
                }
            } else {
                console.log("数据拉取失败");
            }
        },
        error: function (response) {
            console.log("数据拉取失败");
        }
    });

    $("#btnCheck").click(function () {
        table.fnDraw();
    })


    $('#searchstore').click(function () {
        tb.fnDraw();
    });

    $(".newStore").click(function () {
        location.href = webroot + "/store/addstore";
    });

    $(".superSearch").click(function () {
        $("#conditionArea").modal('show');
        $("[type=search]").text("");
    });

    $(".excelExport").click(function () {
       var sSearch =  $("input[type='search']").val();
        $("#sSearch").val(sSearch);
        $("#buyerId").val($('#selectedBuyer').val());
        $("#storeLevelId").val($('#selectedStoreLevel').val());
        $("#storeTypeId").val($('#selectedStoreType').val());
        $("#anywords").val($('#key').val());
        $("#frmMoedlDownload").attr("action", webroot + "/store/downLoadExcel");
        $("#frmMoedlDownload").attr("method", "POST");
        $("#frmMoedlDownload").submit();
    });

});

$(".btnshow").click(function () {
    $("#conditionArea").modal('show');
});


function AddParams(aoData) {
    aoData.push({"name": "anywords", "value": commonClass.getStringVal($('#key').val())});
    aoData.push({"name": "buyerId", "value": commonClass.getStringVal($("#selectedBuyer").select2("val"))});
    aoData.push({
        "name": "storeTypeId",
        "value": commonClass.getStringVal($("#selectedStoreType").select2("val").trim())
    });
    aoData.push({"name": "storeLevelId", "value": commonClass.getStringVal($("#selectedStoreLevel").select2("val"))});

    $('#loading').modal('show');
}
function loadDropDownList() {
    //加载店铺类型
    if (storeTypeVOList.length > 0) {
        for (var i = 0; i < storeTypeVOList.length; i++) {
            var storeType = storeTypeVOList[i];
            $("#selectedStoreType").append("<option value='" + storeType.storeTypeId + "'>" + storeType.storeTypeName + "</option>");
        }
    }
    //加载店铺级别
    if (storeLevelVOList.length > 0) {
        for (var i = 0; i < storeLevelVOList.length; i++) {
            var storeLevel = storeLevelVOList[i];
            $("#selectedStoreLevel").append("<option value='" + storeLevel.storeLevelId + "'>" + storeLevel.storeLevel + "</option>");
        }
    }

    //加载商家
    if (buyerVOList.length > 0) {
        for (var i = 0; i < buyerVOList.length; i++) {
            var buyer = buyerVOList[i];
            $("#selectedBuyer").append("<option value='" + buyer.buyerId + "'>" + buyer.buyerName + "</option>");
        }
    }

}
