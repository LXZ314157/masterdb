var languageList = [];
var featureSimpleList = [];
var TableDatatablesButtons = function () {
    var initDataTable = function () {
        table = $('#featureList');
        var oTable = table.dataTable({
            buttons: [
                {
                    extend: 'excel',
                    text: '导出excel',//定义导出excel按钮的文字
                    title: '款式特点列表',
                    className: 'btn blue btn-outline',
                    exportOptions: {
                        modifier: {
                            page: 'current'
                        },
                        columns: '0,1,2'
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
            "bSort": false,
            "iDisplayLength": 20,
            "ajaxSource": webroot + "/dimension/featurelist",
            'aoColumns': [
                {
                    "mData": "productCode", "sClass": "detail text-center", "mRender": function (data, type, full) {
                    if (data != null && data != "") {
                        var html = "<a href='" + webroot + "/product/productdetail?productCode=" + data + "' target='_blank' class='green'  rel='tooltip' title='点击查看详情'  >" + data + "</a>";
                        return html;
                    }
                }
                },
                {
                    "mData": "productRecommend",
                    "sClass": "detail left",
                    "bSortable": false,
                    "mRender": function (data, type, full) {
                        if (data != null && data != "") {
                            return getItem(data);
                        }
                        else{
                            return "无";
                        }
                    }
                },
                {
                    "mData": "productCode",
                    "sClass": "detail",
                    "bSortable": false,
                    "mRender": function (data, type, full) {
                        var html = "<a href='" + webroot + "/product/editfeature?productCode=" + data + "' class='btn btn-xs green btn-outline' target='_blank' rel='tooltip' title='编辑' ><i class='fa fa-pencil'></i></a>";
                        return html;
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
        aoData.push({"name": "productCode", "value": commonClass.getStringVal($('#search').val())});
        $('#loading').modal('show');
    }
}();
$(function () {
    TableDatatablesButtons.init();
});

function getItem(data) {
    var arr = [];
    var str = new Array();
    str = data.split(",");
    var html = ""
    for (var i = 0; i < str.length; i++) {
        var value = str[i];
        html = "<a href='" + webroot + "/product/productdetail?productCode=" + value + "' class='green ' target='_blank' rel='tooltip' title='编辑' data-entiy='" + value + "' >" + value + "</a>";
        arr.push(html);
    }
    return arr.join(",");
}
