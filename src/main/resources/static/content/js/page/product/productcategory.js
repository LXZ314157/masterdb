var table;
var keyList = [];
var tableName = "";
var columns = "";
var TableDatatablesButtons = function () {
    var initDataTable = function () {
        table = $('#productcategorylist');
        var oTable = table.dataTable({
            buttons: [
                // {extend: '', className: 'btn blue btn-outline excelExport', title: tableName+"信息列表",text: '导出excel'}
                {
                    extend: 'excel',
                    text: '导出excel',//定义导出excel按钮的文字

                    title: tableName+"信息列表",

                    className: 'btn purple btn-outline',
                    exportOptions: {
                        modifier: {
                            page: 'current'
                        },
                        columns: columns
                    }
                }
            ],
            "fnServerParams": function (aoData) {
                AddParams(aoData);
            },
            "processing": false,
            "bAutoWidth":false,
            "language": {
                "search": "搜索产品编号/名称：",
                "sInfoFiltered": ""
            },
            "ordering": false,
            "iDisplayLength": 20,
            "ajaxSource": webroot + "/product/getcategorytable",
            'aoColumns': keyList,
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
        aoData.push({"name": "productCategoryCode", "value": $("#loadUrl").val()});
        $('#loading').modal('show');
    }
}();


$(function () {
    var loadUrl = $("#loadUrl").val();
    $.ajax({
        type: "POST",
        url: webroot + "/product/listmapkey",
        data:{
            "loadUrl":loadUrl
        },
        success: function (response) {
            if (response.code == 200) {

                var datalist = response.data;
                for(var i=0;i<datalist.length-1;i++){
                    keyList.push(datalist[i]);
                    columns+=i+",";
                }
                tableName=datalist[datalist.length-1].productName;
                $("#datalist").text("数据列表--"+tableName);
            }
            TableDatatablesButtons.init();
        },
        error: function () {
            keyList = [];
            TableDatatablesButtons.init();
        }
    });

    // $(".excelExport").click(function () {
    //     var sSearch =  $("input[type='search']").val();
    //     $("#productCodeOrName").val(sSearch);
    //     $("#frmMoedlDownload").attr("action", webroot + "/product/downLoadProductCategoryExcel");
    //     $("#frmMoedlDownload").attr("method", "POST");
    //     $("#frmMoedlDownload").submit();
    // });

})

